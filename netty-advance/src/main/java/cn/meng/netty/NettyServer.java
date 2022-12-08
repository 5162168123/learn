package cn.meng.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class NettyServer {
    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,false)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    class FirstServerHandler extends ChannelInboundHandlerAdapter {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) {
                            ByteBuf byteBuf = (ByteBuf) msg;
                            System.out.println(new Date() + ": 服务端读到数据 -> " +
                                    byteBuf.toString(StandardCharsets.UTF_8));

                            System.out.println(new Date() + ": 服务端写出数据");
                            ByteBuf out = getByteBuf(ctx);
                            ctx.channel().writeAndFlush(out);
                        }

                        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
                            byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(StandardCharsets.UTF_8);
                            ByteBuf buffer = ctx.alloc().buffer();
                            buffer.writeBytes(bytes);
                            return buffer;
                        }
                    }

                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
                bind(serverBootstrap,8000);
    }


    private static void bind(final ServerBootstrap serverBootstrap, final int
            port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
               public void operationComplete(Future<? super Void> future) {
                   if (future.isSuccess()) {
                       System.out.println("端口[" + port + "]绑定成功!");
                   } else {
                       System.err.println("端口[" + port + "]绑定失败!");
                       bind(serverBootstrap, port + 1);
                   }
               }
            });
    }
}