package gameserver.packets;

import io.netty.channel.ChannelHandlerContext;

import java.util.*;
import objects.Player;


/**
 * This class packet contains a read function to read incoming message and a write message to write outgoing message that will be
 * implemented by by other classes that inherit it.
 * @author Khang
 */
public abstract class Packet {

    public static Map<Class<? extends Packet>, List<Class>> values = new HashMap<>();
    private Type t;
    private Player p;
    private ChannelHandlerContext ctx;

    public Packet(Type t){

        this.t = t;
    }

    /**
     * 
     * @return the Type of the packet is being sent
     */
    public Type getType(){
        return t;
    }

    public void setChannel(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }
    public ChannelHandlerContext getChannel(){
        return ctx;
    }

    public void setPlayer(Player p){
        this.p = p;
    }
    public Player getPlayer(){
        return p;
    }

    public List<Class> getTypes(){
        return values.get(getClass());
    }
    public static void setTypes(Class c, Class... cs){
        //Array.asList is a faster way to initialize an array
        values.put((Class<? extends Packet>) c, Arrays.asList(cs));
    }

    public abstract void write(Object... objects);
    
    public abstract void read(String in);

    public enum Type {
        PING(1), PLAYER_SPAWN(2), CLIENT_INFO(3), OBJECT_SPAWN(4),
        PLAYER_MOVE(5), PLAYER_SHOT(6), OBJECT_DESPAWN(7), PLAYER_INFO(8),
        CIRCLE_INFO(9), PLAYER_PICKUP(10), OBJECT_INFO(11),OBJECT_MOVE(12);

        private Integer i;

        Type(Integer i){
            this.i = i;
        }

        public Integer getTag(){
            return i;
        }
    }

}