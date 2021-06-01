/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egroup.gamelobbymanager;

import com.mongodb.*;
import gameserver.HotMessage;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;  
import java.io.File;

/**
 *
 * @author Khang
 */
public class Main {
    public static int port = 25000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        try{
//            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//            DB database = mongoClient.getDB("AnimalShootingDatabase");
//            DBCollection collection = database.getCollection("Users");
//            
//            List<String> userList = Arrays.asList("suunkl12@gmail.com","suunlk123@gmail.com");
//            DBObject user = new BasicDBObject("username", "suunkl12@gmail.com")
//                    .append("password", "testpassword");
//            
//           collection.insert(user);
//        }catch (Exception ex){
//            System.out.print(ex.getMessage());
//        }

        try{
                File file = new File("E:\\Documents\\NetBeansProjects\\GameServer\\Java\\GameServer\\target\\GameServer-1.0-jar-with-dependencies.jar");
                if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
                {  
                    System.out.println("not supported");  
                    return;  
                }  
                Desktop desktop = Desktop.getDesktop();  
                
                //checks file exists or not  
                if(file.exists())         
                    //opens the specified file  
                    desktop.open(file);
        }
        catch (Exception ex){
        }
    }
    
    public void start() {
        
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    //.localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast((new ProtobufVarint32FrameDecoder()));
                            ch.pipeline().addLast(new ProtobufDecoder(HotMessage.Packet.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            
                            Random r = new Random();
                            
                            final EchoServerHandler serverHandler = new EchoServerHandler();
                            
                            ch.pipeline().addLast(serverHandler);
                            
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .bind(port).sync().channel().closeFuture().sync();;
            
            
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}
