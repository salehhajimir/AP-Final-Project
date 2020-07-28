public class Bullet {


    private boolean shot1, shot2;
    private int damage;
    private int extraDamage;
    private final int SPEED = 8;
    private int dimensionX, dimensionY;

    //
    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }
    //


    //
    public boolean isShot1() {
        return shot1;
    }

    public boolean isShot2() {
        return shot2;
    }

    public void setShot1(boolean shot1) {
        this.shot1 = shot1;
    }

    public void setShot2(boolean shot2) {
        this.shot2 = shot2;
    }
    //

    public Bullet(){

    }



}
