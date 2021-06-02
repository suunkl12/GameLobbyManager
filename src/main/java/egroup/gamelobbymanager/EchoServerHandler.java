/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egroup.gamelobbymanager;

import com.google.protobuf.MessageLite;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.util.CharsetUtil;

/**
 *
 * @author Khang
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    //public Player p;
    public ChannelHandlerContext ctx = null;
    
    //public EchoServerHandler(Player p) {
     //   this.p = p;
    //}
    
    
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        
        //System.out.println ("Player " + p.getId () + " connected to the server!");
       
    }
    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
        .addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println (" A server has disconnected!");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
    Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
