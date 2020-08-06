import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall {
    // suitable width and height for rendering wall's image and coordinates of the center of walls block.
    private int width, height , centerX , centerY;
    // coordinates of top left corner of each block and amount of the health of the wall.
    private int dimensionX, dimensionY , health;
    // checking if the wall is alive or not and vulnerability of wall.
    private boolean alive , destructive;
    // wall image files.
    private BufferedImage destructiveWall, reflectorWall;
    // margin used for controlling the encounters.
    private final int MARGIN = 5;

    // image's path.
    public static final String WALL = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\wall\\";


    /**
     * getter and setter methods.
     * @param dimensionX
     */
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
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
     * @param destructive
     */
    public void setDestructive(boolean destructive) {
        this.destructive = destructive;
    }

    /**
     *
     * @return
     */
    public boolean isDestructive() {
        return destructive;
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
    public int getHealth() {
        return health;
    }

    /**
     *
     * @return
     */
    public boolean isAlive() {
        return alive;
    }






    public Wall(){
        width = Map.WIDTH_CONSTANT;
        height = Map.HEIGHT_CONSTANT;

        alive = true;
        health = 200;

        centerX = dimensionX + (width / 2);
        centerY = dimensionY + (height / 2);


        try{
            destructiveWall = ImageIO.read(new File(WALL + "crateWood.png"));
            reflectorWall = ImageIO.read(new File(WALL + "crateMetal.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * destructing  a wall.
     */
    public void wallDestruction(){
            alive = false;
    }

    /**
     * rendering wall in game frame.
     * @param graphics2D
     */
    public void renderWall(Graphics2D graphics2D){
        AffineTransform trans = AffineTransform.getTranslateInstance(dimensionX, dimensionY);
        BufferedImage image;
        if (destructive) {
            image = destructiveWall;
        }
        else{
            image = reflectorWall;
        }
        trans.scale(width  / 28 , height /28);
        graphics2D.drawImage(image , trans , null);
    }

    /**
     * check if an object overlaps walls or not.
     * @param x
     * @param y
     * @return
     */
    public boolean checkOverlap(double x , double y){
        if (x > dimensionX - MARGIN && x < dimensionX + width + MARGIN && y > dimensionY - MARGIN && y < dimensionY + height + MARGIN)
            return true;
        return false;
    }


    /**
     * controlling shots which encounter wall and controlling health changes.
     */
    public void encounter(Bullet bullett){
            if (destructive){
                this.health -= bullett.getDamage();


                if (this.health <= 0) {
                    wallDestruction();
                    bullett.kill();
                }
            }
        }



    public int getTop()
    {
        return dimensionY;
    }
    public int getDown()
    {
        return dimensionY+height;
    }
    public int getLeft()
    {
        return dimensionX;
    }
    public int getRight()
    {
        return dimensionX+width;
    }

    public int getWidth()
    {
        return  width;
    }
    public  int getHeight()
    {
        return  height;
    }
}
