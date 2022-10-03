package cn.meng.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class NIOServer {
    private final static CharsetEncoder ENCODER = StandardCharsets.UTF_8.newEncoder();
    private final static CharsetDecoder DECODER = StandardCharsets.UTF_8.newDecoder();

    private static ByteBuffer readBuffer;
    private static Selector selector;

    private static ExecutorService threadPool;
    private static LinkedBlockingQueue<SelectionKey> queue;



    public static void main(String[] args) {
        init();
        listen();
    }

    private static void init(){
        readBuffer = ByteBuffer.allocate(1024);
        ServerSocketChannel servSocketChannel = null;

        try {
            servSocketChannel = ServerSocketChannel.open();
            servSocketChannel.configureBlocking(false);
            servSocketChannel.socket().bind(new InetSocketAddress(9000), 100);
            selector = Selector.open();
            servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        threadPool = Executors.newFixedThreadPool(10);
        queue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);

    }

    private static void listen() {
        while(true){
            try{
                selector.select();
                Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();

                while(keysIterator.hasNext()){
                    SelectionKey key = (SelectionKey) keysIterator.next();
                    keysIterator.remove();
                    queue.offer(key);

                }
            }
            catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    class Worker implements Runnable{

        @Override
        public void run() {
            while(true) {
                try {
                    SelectionKey key = queue.take();
                    handleRequest(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(SelectionKey key)
                throws IOException, ClosedChannelException {
            SocketChannel channel = null;

            try{
                if(key.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    channel = serverChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                }
                else if(key.isReadable()){
                    channel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    int count = channel.read(readBuffer);

                    if(count > 0){
                        readBuffer.flip();
                        CharBuffer charBuffer = DECODER.decode(readBuffer);
                        String request = charBuffer.toString();
                        System.out.println(request);
                        String response = "";
                        channel.write(ENCODER.encode(CharBuffer.wrap(response)));
                    }
                    else{
                        channel.close();
                    }
                }
            }
            catch(Throwable t){
                t.printStackTrace();
                if(channel != null){
                    channel.close();
                }
            }
        }
    }

}
