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
    private final double SPEED = 2;
    // coordinates of the place where bullet hit a wall.

    private final int SIDE = 16;


    private Wall touchWall;

    // image's path.
    private final String BULLET = ".\\images\\tank and bullet\\cannonball.png";
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

    public double getAngle() {
        if(angle<0)
            angle+=360;
        return angle%360;
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
     * controlling the reflection of bullet when it hits a wall.
     */
    public void reflectBullet() {
        if (isWallTouched()) {
            getRefledtAngleCalculate();
        }

    }



    /**
     * checking if bullet touched a wall or not.
     * @return
     */
    public boolean isWallTouched() {
        for (Wall wall : Data.walls) {
            if (wall.checkOverlap(getHeadX(), getHeadY()))
            {
                touchWall=wall;
                wall.encounter(this);
                return true;
            }

        }
        return false;
    }


    /**
     * calculate angle when bullet touched a wall.
     */
    public void getRefledtAngleCalculate()
    {
        double headX=getHeadX();
        double headY=getHeadY();
        int safeX=touchWall.getWidth()/100;
        int safey=touchWall.getHeight()/100;

        angle=getAngle();

        if(headX>touchWall.getLeft()+safeX && headX< touchWall.getRight()-safeX) {
            angle=360-angle;
        }
        else if (headY>touchWall.getTop()+safey && headY< touchWall.getDown()-safey)
        {
            angle=180-angle;
        }
        else {
            angle += 180;
        }
    }


    /**
     * calculate bullet's head's coordinates.
     * @return
     */
    public double getHeadX()
    {
        double cos=Math.cos(Math.toRadians(angle))* SIDE;
        double headX=dimensionX+cos;
        return headX;

    }

    /**
     * calculate bullet's head's coordinates.
     * @return
     */
    public double getHeadY()
    {
        double sin=Math.sin(Math.toRadians(angle))*SIDE;
        return dimensionY+sin;
    }


}
