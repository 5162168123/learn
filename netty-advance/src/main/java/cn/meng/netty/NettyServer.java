package cn.meng.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;

public class NettyServer {
    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new
                                                      SimpleChannelInboundHandler<String>() {
                                                          @Override
                                                          protected void
                                                          channelRead0(ChannelHandlerContext ctx, String msg) {
                                                              System.out.println(msg);
                                                          }
                                                      });
                    }
                })
                .attr(AttributeKey.newInstance("serviceName"),"nettyServer");
                bind(serverBootstrap,8000);
    }


    private static void bind(final ServerBootstrap serverBootstrap, final int
            port) {
        serverBootstrap.bind(port).addListener(future ->  {
                   if (future.isSuccess()) {
                       System.out.println("端口[" + port + "]绑定成功!");
                   } else {
                       System.err.println("端口[" + port + "]绑定失败!");
                       bind(serverBootstrap, port + 1);
                   }

            });
    }
}