package org.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;



public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;

        byte[] request = new byte[buffer.readableBytes()];
        buffer.readBytes(request);
        String requestData = new String(request, "UTF-8");
        System.out.println("服务器接受到请求 ：" + requestData);

        String response = "收到你的请求了，返回响应给你";
        ByteBuf responseBuffer = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(responseBuffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
