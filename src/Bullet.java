import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {

    // checking if bullet is alive or not.
    private boolean alive;
    // bullet's damage , coordinates and shoting coordinates.
    private int damage, dimensionX, dimensionY, shootingX, shootingY;
    // bullet's angle.
    private double angle;
    // bullet's speed.
    private final double SPEED = 16;
    // coordinates of the place where bullet hit a wall.
    ;private int touchedWallX , touchedWallY;

    // image's path.
    private final String BULLET = ".\\images\\tank and bullet\\bulletBlue2_outline.png";
    // image's file.
    private BufferedImage bulletImage;


    /**
     * getter and setter methods.
     * @param dimensionX
     */
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    /**
     *
     * @param shootingX
     */
    public void setShootingX(int shootingX) {
        this.shootingX = shootingX;
    }

    /**
     *
     * @param dimensionY
     */
    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    /**
     *
     * @param shootingY
     */
    public void setShootingY(int shootingY) {
        this.shootingY = shootingY;
    }

    /**
     *
     * @param angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     *
     * @param alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     *
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     *
     * @return
     */
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return
     */
    public int getDimensionX() {
        return dimensionX;
    }

    /**
     *
     * @return
     */
    public int getDimensionY() {
        return dimensionY;
    }

    /**
     *
     * @return
     */
    public double getAngle() {
        return angle;
    }

    /**
     *
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    public Bullet() {
        shootingX = getDimensionX();
        shootingY = getDimensionY();
        alive = true;
        damage = 25;

        try {
            bulletImage = ImageIO.read(new File(BULLET));
        } catch (IOException e) {
            System.out.println(e);
        }

        Data.bullets.add(this);
    }


    /**
     * bullet's moving method due to amount of angle and coordinates.
     */
    public void move() {
        angle += 0;
        dimensionX += SPEED * Math.cos(Math.abs(Math.toRadians(angle)));
        dimensionY += SPEED * Math.sin(Math.toRadians(angle));
        reflectBullet();
    }


    /**
     * killing bullet.
     */
    public void kill() {
        alive = false;
    }

    /**
     *  rendering bullet in game frame.
     * @param graphics2D
     */
    public void renderBullet(Graphics2D graphics2D) {
        AffineTransform trans = AffineTransform.getTranslateInstance(dimensionX, dimensionY);
        trans.rotate(Math.toRadians(angle));
        graphics2D.drawImage(bulletImage, trans, null);

    }


    /**
     * controlling the reflection of bullet by hitting the wall.
     */
    public void reflectBullet() {
        double reflectingAngle;
        if (isWallTouched()) {
            reflectingAngle = Math.atan2(dimensionY - shootingY, dimensionX - shootingX);
            //System.out.println("AANNGGLLEE "+Math.toDegrees(reflectingAngle));
            this.setAngle(Math.abs(Math.toDegrees(reflectingAngle)) - 180);
        }

    }


    /**
     * checking if bullet touched a wall or not.
     * @return
     */
   public boolean isWallTouched() {
        for (Wall wall : Data.walls) {
            if (wall.checkOverlap(dimensionX, dimensionY)) {
                touchedWallX = wall.getDimensionX();
                touchedWallY = wall.getDimensionY();
                return true;
            }
        }
        touchedWallX = -1;
        touchedWallY = -1;
        return false;
    }

    /*public void reflectBullet() {
        double reflectingAngle;
        if (isWallTouched()) {
            getRefledtAngleCalculate();

        }

    }*/

    /*public void getRefledtAngleCalculate()
    {
        if(dimensionY<touchedWallY && dimensionX>=touchedWallX)
        {
            System.out.println("3");
            angle-=90;
            dimensionY-=SPEED;
        }
        else if(dimensionY>touchedWallY && dimensionX>=touchedWallX){
            System.out.println("4");
            angle+=90;
            dimensionY+=SPEED;
        }
        else if(dimensionX<touchedWallX )
        {
//            System.out.println("1");
//            System.out.println(dimensionX+" "+touchWallX);
            angle-=90;
            dimensionX-=SPEED;
        }
        else if(dimensionX>touchedWallX)
        {
//            System.out.println("2");
//            System.out.println(dimensionX+" "+touchWallX);
            angle-=90;
            dimensionX+=SPEED;
        }
    }*/


}
