import java.util.ArrayList;

public class Data {

    public static ArrayList<Tank> tanks = new ArrayList<Tank>();
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public static ArrayList<Wall> walls = new ArrayList<Wall>();




    public static void removeBullet(){
        for (Bullet bullet : bullets){
            if (!bullet.isAlive())
                bullets.remove(bullet);
        }
    }

    public static void removeWall(){
        return;
        /*
        for (Wall wall : walls){
            if (!wall.isAlive())
                walls.remove(wall);
        }

         */
    }
}
