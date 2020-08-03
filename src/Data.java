import java.util.ArrayList;
import java.util.Iterator;

public class Data {

    public static ArrayList<Tank> tanks = new ArrayList<Tank>();
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public static ArrayList<Wall> walls = new ArrayList<Wall>();
    public static ArrayList<FloorBlock> floor = new ArrayList<FloorBlock>();




    public static void removeBullet(){
        Iterator it = bullets.iterator();
        while (it.hasNext()){
            Bullet b = (Bullet)it.next();
            if (!b.isAlive())
                bullets.remove(it);
        }
    }



    public static void removeWall(){
        Iterator it = walls.iterator();
        while (it.hasNext()){
            Wall b = (Wall) it.next();
            if (!b.isAlive())
                walls.remove(it);
        }
    }
}
