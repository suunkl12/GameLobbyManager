/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.utils;

import objects.Player;
import egroup.gamelobbymanager.Main;
import gameserver.HotMessage;
import gameserver.packets.Packet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Khang
 */
public class Utils {
    
    /**
     * Create a packet Instance with the Packet class needed to send information to client,
     * Like PlayerShoot packet or PlayerQuit packet
     */
    public static Packet packetInstance(Class<? extends Packet> c, Player p){

        try {
            //Get constructor của Packet type tương ứng
            //Ví dụ, nếu c là PlayerSpawnPacket, nó sẽ lấy constructor của Packet loại đó
            Packet pt = c.getConstructor().newInstance();
            pt.setChannel(p.getHandler().ctx);
            pt.setPlayer(p);
            return pt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
   
    
    
    public static Packet messageToPacket(HotMessage.Packet o, Player p){
        if(!Main.packets.containsKey(o.getId())) return null;

        return packetInstance(Main.packets.get(o.getId()), p);

    }
    
    public static List<Object> fromString(List<Class> types, String s){
        List<Object> objects = new ArrayList<>();

        try {
            String[] ss = s.split("\\|");

            for(int i = 0; i < types.size(); i++){

                if (types.get(i) == String.class) objects.add(ss[i]);
                else if (types.get(i) == Integer.class){

                    try{

                        objects.add(Integer.parseInt(ss[i]));

                    }catch(Exception ignored){return null;}

                }else if (types.get(i) == Float.class){

                    try{

                        objects.add(Float.parseFloat(ss[i]));

                    }catch(Exception ignored){return null;}

                }else if (types.get(i) == Byte.class){

                    try{

                        objects.add(Byte.parseByte(ss[i]));

                    }catch(Exception ignored){return null;}

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return objects;

    }
    
    public static String toString(Object... objects){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < objects.length; i++){
            Object o = objects[i];

            sb.append(o);

            if (i != objects.length - 1) sb.append("|");

        }

        return sb.toString();
    }
    
    
    /** 
     * Check to see the type of the objects by compare the lengths and comparing the type of the objects to the input types
     * @param types types to compare 
     * @param objects objects to compare
     * @return false if size of type is different from object length, or object types and types are not the same
     */
    public static Boolean isItSuitable(List<Class> types, Object... objects){

        try {
            if (objects.length < types.size() || objects.length > types.size()) return false;

            for(int i = 0; i < types.size(); i++){

                if(objects[i].getClass() != types.get(i)) return false;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public static Boolean isItSuitable(List<Class> types, String s){

        try {
            String[] ss = s.split("\\|");

            if (ss.length < types.size() || ss.length > types.size()) return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    /**
     * Send all other players a packet
     * @param c packet class to send
     * @param objects messages objects to send
     */
    public static void writeAll(Class<? extends Packet> c, Object... objects){

        for(Player p : Main.players.values()){

            packetInstance(c, p).write(objects);

        }

    }
}
