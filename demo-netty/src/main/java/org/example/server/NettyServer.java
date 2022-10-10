package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class  NettyServer {

    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try{
            //netty服务器端
            ServerBootstrap serverBootstrap = new ServerBootstrap();


            serverBootstrap.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());//处理每链接的socketChannel

            ChannelFuture bindFuture = serverBootstrap.bind(50070).sync();//同步等待启动服务器监听端口

            bindFuture.channel().closeFuture().sync();//同步等待关闭启动服务器的结果
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        /**
         * w网络请求逻辑
         * @param socketChannel            the {@link Channel} which was registered.
         * @throws Exception
         */
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new NettyServerHandler());
        }
    }
}
