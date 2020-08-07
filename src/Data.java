import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class contains the information of all objects in game.
 */
public class Data {

    // arraylist for tanks.
    public static ArrayList<Tank> tanks = new ArrayList<Tank>();
    // arraylist for bullets.
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    // arraylist for walls.
    public static ArrayList<Wall> walls = new ArrayList<Wall>();
    // arraylist for empty blocks.
    public static ArrayList<FloorBlock> floor = new ArrayList<FloorBlock>();
    // arraylist for players.
    public static ArrayList<Player> players = new ArrayList<Player>();
    // arraylist for gifts.
    public static ArrayList<Gift> gifts = new ArrayList<Gift>();
    // arraylist for dead tanks.
    public static ArrayList<Tank> deadTanks = new ArrayList<Tank>();


    /**
     * add gifts which should be rendered in game frame.
      */
    public static void addGift(){
        gifts.add(0 , new Gift());
        gifts.add(1 , new Gift());

        gifts.get(0).setType(0);
        gifts.get(1).setType(1);
    }


    /**
     * removing bullet if it's not alive.
     */
    public static void removeBullet(){
        for (int i = 0 ; i < bullets.size(); i++){
            if (!bullets.get(i).isAlive()){
                bullets.remove(i);
                i--;
            }
        }
    }


    /**
     * removing wall if it's not alive.
     */
    public static void removeWall(){
        for (int i = 0 ; i < walls.size(); i++){
            if (!walls.get(i).isAlive()){
                walls.remove(i);
                i--;
            }
        }
    }


    /**
     * removing tank if it's not alive.
     */
    public static void removeTank(){
        for (int i = 0 ; i < tanks.size(); i++){
            if (!tanks.get(i).isAlive()){
                deadTanks.add(tanks.get(i));
                tanks.remove(i);
                i--;
            }
        }
    }

    // removing gift if it's not alive.
    public static void removeGift(){
        for (int i = 0 ; i < gifts.size(); i++){
            if (!gifts.get(i).isActive()){
                gifts.remove(i);
                i--;
            }
        }
    }



}
