import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

public class Gift {

    // gift coordinates and an int which indicates the type of a gift.
    private int coordinateX , coordinateY , type;
    // boolean which is used to check if a gift should be summoned or not.
    private boolean active;
    // path of the gift image files.
    private final String giftImage = ".\\images\\gift\\";
    // gift images.
    private BufferedImage extraDamageGift , extraHealthGift;
    // amount of the gift sides.
    private final int GIFT_SIDE = 25;
    // String which shows the type of the gift.
    private String giftType;
    // var which saves the exact time when gift was created.
    private long time;



    /**
     * getter and setter methods.
     * @return
     */
    public String getGiftType() {
        return giftType;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     *
     * @return
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     *
     * @return
     */
    public long getTime() {
        return time;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    public Gift(){
        summonGift();
        time = System.currentTimeMillis();


        Random random = new Random();
        type = random.nextInt(2);
        if(type == 0)
            giftType = "extra health";
        else if (type == 1)
            giftType = "extra damage";


        try {

            extraDamageGift = ImageIO.read(new File(giftImage + "oilSpill_large.png"));
            extraHealthGift = ImageIO.read(new File(giftImage + "sandbagBrown.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * rendering gift image in game frame.
     * @param graphics2D
     */
    public void renderGift(Graphics2D graphics2D){
        AffineTransform trans = AffineTransform.getTranslateInstance(coordinateX, coordinateY);
        BufferedImage image = null;

        if (type == 0){
            image = extraHealthGift;
        }
        else if (type == 1){
            image = extraDamageGift;
        }

        trans.scale(GIFT_SIDE  / 50 , GIFT_SIDE /50);
        graphics2D.drawImage(image , trans , null);
    }


    /**
     * initializing gift's coordinates.
     */
    public void summonGift(){
        Random random = new Random();
        int tmpX;
        int tmpY;

        while (true) {
            tmpX  = random.nextInt(GameFrame.GAME_WIDTH);
            tmpY = random.nextInt(GameFrame.GAME_HEIGHT);
            boolean breaking=false;
            for (FloorBlock floorBlock : Data.floor) {
                if ((floorBlock.checkOverlap(tmpX,tmpY))) {
                    this.coordinateX = tmpX;
                    this.coordinateY = tmpY;
                    breaking=true;
                    break;
                }
            }
            if(breaking)
                break;
        }
    }


    public boolean checkOverlap(Rectangle tank){
        Rectangle gift = new Rectangle(coordinateX, coordinateY, GIFT_SIDE, GIFT_SIDE);
        return tank.intersects(gift);
    }

}
