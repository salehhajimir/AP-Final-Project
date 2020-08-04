import java.util.ArrayList;
import java.util.Iterator;

public class Data {

    public static ArrayList<Tank> tanks = new ArrayList<Tank>();
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public static ArrayList<Wall> walls = new ArrayList<Wall>();
    public static ArrayList<FloorBlock> floor = new ArrayList<FloorBlock>();




    public static void removeBullet(){
        for (int i = 0 ; i < bullets.size(); i++){
            if (!bullets.get(i).isAlive()){
                bullets.remove(i);
                i--;
            }
        }
    }



    public static void removeWall(){
        for (int i = 0 ; i < walls.size(); i++){
            if (!walls.get(i).isAlive()){
                walls.remove(i);
                i--;
            }
        }
    }
}
