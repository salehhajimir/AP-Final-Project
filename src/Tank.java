import com.sun.xml.internal.bind.v2.TODO;


import java.util.Random;



public class Tank {

    private int dimensionX, dimensionY, diam, angle, health, extraHealth;



    public Tank() {
        Random random = new Random();
        //checking if its an empty area.
        dimensionX = random.nextInt(GameFrame.GAME_WIDTH);
        dimensionY = random.nextInt(GameFrame.GAME_HEIGHT);

        diam = 2;
        extraHealth = 0;
        //health = ;

    }


    //
    public void setExtraHealth(int extraHealth) {
        this.extraHealth = extraHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public int getExtraHealth() {
        return extraHealth;
    }

    public int getHealth() {
        return health;
    }
    //

    //
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }


    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }
    //


    //
    public void setAngle(int angle) {
        this.angle = angle;
    }


    public double getAngle() {
        return angle;
    }
    //


    public boolean isAlive(){
        return (health + extraHealth > 0);
    }



}










