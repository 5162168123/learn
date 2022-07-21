package cn.meng.dfs.namenode.server;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;
import java.util.List;

public class FSEditLog {
    /**
     * 自增的事务id
     */
    private long txidSeq = 0L;
    /**
     * 标志位，是否正在同步磁盘
     */
    private volatile Boolean isSyncRunning = false;

    /**
     * 标志位，是否有线程等待同步磁盘
     */
    private volatile Boolean isWaitSync = false;
    private long syncMaxTxid;

    ThreadLocal<Long> localTxid = new ThreadLocal<>();
    private DoubleBuffer logBuffer = new DoubleBuffer();

    public void logEdit(String content){
        synchronized (this){
            txidSeq++;
            long txid = txidSeq;
            localTxid.set(txid);
            EditLog log = new EditLog(txid,content);
            logBuffer.write(log);
        }
        logSync();
        System.out.println(content);
    }

    /**
     * 写磁盘网络
     */
    public void logSync(){
        synchronized (this){
            //判断是否有线程在同步磁盘网络
            if(isSyncRunning){
                //如果有开始判断
                long txid = localTxid.get();
                if(txid <= syncMaxTxid){
                    return;
                }

                if(isWaitSync){
                    return;
                }

                //开始等待正在同步磁盘的线程，并将等待位置成true，同步位为false后被唤醒，并跳出循环将等待位重置
                isWaitSync = true;
                while (isSyncRunning){
                    try{
                        wait(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                //跳出wait循环后，重置等待位
                isWaitSync = false;
            }
            //如果没有开始交换缓冲区并获取同步缓冲区最大txid
            isSyncRunning=true;
            logBuffer.setReadyToSync();
            syncMaxTxid = logBuffer.getMaxSyncTxid();
        }
        //同步磁盘操作
        logBuffer.flush();
        //同步完磁盘后将刷盘标志位重置，并唤醒可能存在的等待中的线程
        synchronized (this){
            isSyncRunning = false;
            notifyAll();
        }
    }


    class EditLog{
        long txid;
        String content;
        public EditLog(long txid,String content){
            this.txid = txid;
            this.content = content;
        }
    }
    /**
     * 内存双缓冲
     */
    private class DoubleBuffer{
        /**
         * 用作线程写入的缓存
         */
        private LinkedList<EditLog> currentBuffer = new LinkedList<>();

        /**
         * 同步磁盘缓冲
         */
        private LinkedList<EditLog> syncBuffer = new LinkedList<>();

        /**
         * log写入内存
         * @param log
         */
        public void write(EditLog log){
            currentBuffer.add(log);
        }

        /**
         * 交换缓冲区
         */
        public void setReadyToSync(){
            LinkedList<EditLog> temp = currentBuffer;
            currentBuffer = syncBuffer;
            syncBuffer = temp;
        }

        public void flush(){
            syncBuffer.forEach((x)->{
                System.out.println("写入一条日志" + x.content);
            });
            syncBuffer.clear();
        }

        public long getMaxSyncTxid() {
            return syncBuffer.getLast().txid;
        }
    }
}
