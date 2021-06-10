/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egroup.gamelobbymanager;

import objects.Player;
import com.mongodb.*;
import gameserver.HotMessage;
import gameserver.packets.Packet;
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
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khang
 */
public class Main {
    public static int port = 24300;
    
    public static HashMap<Integer, Player> players = new HashMap<>();
    
    public static HashMap<Integer, Class<? extends Packet>> packets = new HashMap<Integer, Class<? extends Packet>>(){{

        //put(1, PingPacket.class);
        //put(8, PlayerInfoPacket.class);
        //put(9, CircleInfoPacket.class);
        //put(10, PlayerPickupPacket.class);
        //put(11, ObjectInfoPacket.class);

    }};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        new Thread(m::start).start();
        
        new Thread() {
            Process p = new ProcessBuilder("java","-jar","E:\\\\Documents\\\\NetBeansProjects\\\\GameServer\\\\Java\\\\GameServer\\\\target\\\\GameServer-1.0-jar-with-dependencies.jar","4296","24300","localhost" ).start();
        }.start();
        
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

//        try{
//                File file = new File("E:\\Documents\\NetBeansProjects\\GameServer\\Java\\GameServer\\target\\GameServer-1.0-jar-with-dependencies.jar");
//                if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
//                {
//                    System.out.println("not supported");
//                    return;
//                }
//                Desktop desktop = Desktop.getDesktop();
//
//                //checks file exists or not
//                if(file.exists())
//                    //opens the specified file  
//                    desktop.open(file);
//        }
//        catch (Exception ex){
//        }

        
        
        
        
        
    }
    
    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            
                            Random r = new Random();
                            
                            final EchoServerHandler serverHandler = new EchoServerHandler();
                            
                            ch.pipeline().addLast(serverHandler);
                            
                        }
                    }).bind().sync().channel().closeFuture().sync();;
            
            
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
