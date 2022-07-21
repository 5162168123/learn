package cn.meng.dfs.namenode.server;

public class NameNode {

    private volatile boolean shouldRun;

    private NameNodeRpcServer nameNodeRpcServer;
    private FSNameSystem fsNameSystem;

    public NameNode(){
        shouldRun = true;
    }
    public void initialize(){
        this.fsNameSystem = new FSNameSystem();
        this.nameNodeRpcServer = new NameNodeRpcServer(fsNameSystem);

    }

    public void run(){
        try{
            while (shouldRun){
                Thread.sleep(10 * 1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        NameNode nameNode = new NameNode();
        nameNode.initialize();
    }
}
