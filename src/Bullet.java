import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {


    private boolean alive;
    private int damage , dimensionX, dimensionY , extraDamage , side , shootingX , shootingY , maxDamage;
    private double angle;
    private final double SPEED = 16;

    private final String BULLET = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\bulletBlue2_outline.png";
    private BufferedImage bulletImage;


    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public double getAngle() {
        return angle;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public boolean isAlive() {
        return alive;
    }

    public Bullet(){
        shootingX = getDimensionX();
        shootingY = getDimensionY();
        alive = true;
        extraDamage = 0;

        try{
            bulletImage = ImageIO.read(new File(BULLET));
        }
        catch(IOException e){
            System.out.println(e);
        }

        Data.bullets.add(this);
    }

    public void move(){
        angle += 0;
        dimensionX += SPEED * Math.cos(Math.abs(Math.toRadians(angle)));
        dimensionY += SPEED * Math.sin(Math.toRadians(angle));
    }

    public void kill(){
        alive = false;
    }

    public void renderBullet(Graphics2D graphics2D){
        AffineTransform trans = AffineTransform.getTranslateInstance(dimensionX, dimensionY);
        trans.rotate(Math.toRadians(angle));
        graphics2D.drawImage(bulletImage , trans , null);

    }





    public void reflectBullet(){
        double reflectingAngle;
        for (Wall wall : Data.walls){
            if (wall.checkOverlap(dimensionX , dimensionY)){
                reflectingAngle = Math.atan2(dimensionY - shootingY , dimensionX - shootingX);
                this.setAngle(reflectingAngle);
            }
        }
    }




}
