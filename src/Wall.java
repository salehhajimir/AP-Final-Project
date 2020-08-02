import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall {
    private int width, height , centerX , centerY;
    // dimensions of top left corner of each blocl
    private int dimensionX, dimensionY , health;
    private boolean alive , destructive;
    private BufferedImage destructiveWall, reflectorWall;

    public static final String WALL = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\wall\\";


    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public void setDestructive(boolean destructive) {
        this.destructive = destructive;
    }

    public boolean isDestructive() {
        return destructive;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public boolean isAlive() {
        return alive;
    }






    public Wall(){
        width = Map.WIDTH_CONSTANT;
        height = Map.HEIGHT_CONSTANT;

        centerX = dimensionX + width / 2;
        centerY = dimensionY + height / 2;

        try{
            destructiveWall = ImageIO.read(new File(WALL + "crateWood.png"));
            reflectorWall = ImageIO.read(new File(WALL + "crateMetal.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public void wallDestruction(){
        if (health <= 0)
            alive = false;
    }

    public void renderWall(Graphics2D graphics2D){
        graphics2D.drawImage(destructiveWall , dimensionX , dimensionY , null);
    }

    public boolean checkOverlap(int x , int y){
        if (x > dimensionX && x < dimensionX + width && y > dimensionY && y < dimensionY + height)
            return true;
        return false;
    }


    public void encounter(){
        for (Bullet bullett : Data.bullets){
            if (this.checkOverlap(bullett.getDimensionX() , bullett.getDimensionY()) && destructive){
                this.health -= bullett.getMaxDamage();
            }
        }
    }
}
