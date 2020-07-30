public class Bullet {


    private boolean shot;
    private double damage , dimensionX, dimensionY , extraDamage;
    private final double SPEED = 8;


    //
    public void setDimensionX(double dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(double dimensionY) {
        this.dimensionY = dimensionY;
    }

    public double getDimensionX() {
        return dimensionX;
    }

    public double getDimensionY() {
        return dimensionY;
    }
    //


    //

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public boolean isShot() {
        return shot;
    }
//

    public Bullet(){
        shot = false;
        extraDamage = 0;
    }



}
