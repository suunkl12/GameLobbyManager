
import egroup.gamelobbymanager.EchoServerHandler;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player{
    
    public static Ider ider = new Ider();
    private Long l;
    private EchoServerHandler h;
    
    private final long SHOOT_COOLDOWN = 400;
    private volatile long currentTime = 0;
    
    private ScheduledExecutorService e;
    
    
    public HashMap<Integer, Integer> staying = new HashMap<>();
    
    public Player(Integer id, Vector2 position, Rotation rotation) {
        super(id, position, rotation);
        
        Player p = this;
        this.l = System.currentTimeMillis() - 1000;
        
        e = Executors.newSingleThreadScheduledExecutor();
        e.scheduleAtFixedRate(() -> {
            if (!ServerMainTest.players.containsKey(p.getId())) {e.shutdown(); return;}

            if (getDifference() >= ServerMainTest.TIMEOUT){

                //Nếu như mất quá lâu để kết nói thì disconnect
                //h.ctx.disconnect();
                //return;
            }
            
            //HashMap<Integer, Integer> map = new HashMap<>();
            
            //List<Integer> remove = new ArrayList<>();
            //for(Integer key : p.staying.keySet()){ if (!map.containsKey(key)) remove.add(key); }
            //for(Integer key : remove){ p.staying.remove(key); }

        }, 100, 100, TimeUnit.MILLISECONDS);
    }
}