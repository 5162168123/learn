package cn.meng.socket;

import javax.jnlp.SingleInstanceListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class NIOServerSimple {
    public static void main(String[] args) {
        NIOServerSimple nioServerSimple = new NIOServerSimple();
        nioServerSimple.init();;
    }

    void init() {
        try (
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(9999));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("bind 9999 succeed");
                while (!Thread.interrupted()){
                    connectionAccept(selector,serverSocketChannel);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void connectionAccept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        selector.select();
        System.out.println("selector");
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while(iterator.hasNext()){
            SelectionKey next = iterator.next();
            System.out.println("has");
            if(next.isAcceptable()){
                System.out.println("new connection is coming");
                ServerSocketChannel channel = (ServerSocketChannel)next.channel();
                SocketChannel socketChannel  = channel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
                iterator.remove();
            }else if(next.isReadable()){

                System.out.println("something ready to be read");
                SocketChannel channel = (SocketChannel) next.channel();
                ByteBuffer allocate = ByteBuffer.allocate(1024);

                int count;

                StringBuilder sb = new StringBuilder();
                while ((count = channel.read(allocate)) > 0){
                    sb.append(new String(allocate.array(),0,count));
                    System.out.println(count);
                    System.out.println("position is "+allocate.position() +" and capacity is " + allocate.capacity() + " and limit is " + allocate.limit());

                    allocate.flip();
                    LockSupport.parkUntil(System.currentTimeMillis()+500);
                    channel.write(allocate);
                    allocate.clear();
                }
                System.out.printf("收到%s\n",sb);
                channel.close();
                iterator.remove();
            }
        }


    }





}
