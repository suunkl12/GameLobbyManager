package objects;

import egroup.gamelobbymanager.EchoServerHandler;
import egroup.gamelobbymanager.Main;
import gameserver.utils.Ider;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player{
    
    public static Ider ider = new Ider();
    private Long l;
    private EchoServerHandler h;
    public String username;
    public String password;
    
    
    public EchoServerHandler getHandler() {
        return h;
    }

    public void setHandler(EchoServerHandler h) {
        this.h = h;
    }
    private Integer id;
    private volatile long currentTime = 0;
    
    private ScheduledExecutorService e;
    
    
    public HashMap<Integer, Integer> staying = new HashMap<>();
    
    public Integer getId(){
        return id;
    }
    
    public Player(Integer id) {
        this.id=id;
        
        
        Player p = this;
        this.l = System.currentTimeMillis() - 1000;
        
        e = Executors.newSingleThreadScheduledExecutor();
        e.scheduleAtFixedRate(() -> {
            if (!Main.players.containsKey(p.getId())) {e.shutdown(); return;}
            
            //HashMap<Integer, Integer> map = new HashMap<>();
            
            //List<Integer> remove = new ArrayList<>();
            //for(Integer key : p.staying.keySet()){ if (!map.containsKey(key)) remove.add(key); }
            //for(Integer key : remove){ p.staying.remove(key); }

        }, 100, 100, TimeUnit.MILLISECONDS);
    }
}