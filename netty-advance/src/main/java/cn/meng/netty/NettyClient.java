package cn.meng.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    final static Integer MAX_RETRY = 5;
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) {
                     ch.pipeline().addLast(new FirstClientHandler());
                    }
<<<<<<< HEAD
                });
        Channel channel = bootstrap.connect().addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败!");
// 重新连接
            }
        }).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
=======
        });
        connect(bootstrap,"127.0.0.1",8000,MAX_RETRY);
//        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
//        while (true) {
//            channel.writeAndFlush(new Date() + ": hello world!");
//            Thread.sleep(2000);
//        }
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");

            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                        bootstrap.config().group().schedule(() -> connect(bootstrap,
                                host, port, retry - 1), delay, TimeUnit
                                .SECONDS);
            }
        });
    }


    static class FirstClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println(new Date() + ": 客户端写出数据");
            // 1. 获取数据
            ByteBuf buffer = getByteBuf(ctx);

            // 2. 写数据
            ctx.channel().writeAndFlush(buffer);

        }
        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
            // 1. 获取二进制抽象 ByteBuf
            ByteBuf buffer = ctx.alloc().buffer();

            // 2. 准备数据，指定字符串的字符集为 utf-8
            byte[] bytes = "你好，闪电侠!".getBytes(StandardCharsets.UTF_8);
            // 3. 填充数据到 ByteBuf
            buffer.writeBytes(bytes);
            return buffer;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf byteBuf = (ByteBuf) msg;
            System.out.println(new Date() + ": 客户端读到数据 -> " +
                    byteBuf.toString(StandardCharsets.UTF_8));
            ctx.close();
>>>>>>> e0c6cf5aafc51d213d4cfe9cae4d6b595b697369
        }
    }
}