import com.sun.xml.internal.bind.v2.TODO;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;



public class Tank {



    private int dimensionX, dimensionY, angle, health, extraHealth , maxHealth;
    private boolean shot1 , shot2 , alive;



    public BufferedImage tankImage;
    private Bullet bullet;


    private String[] image = new String[8];

    private final int SIDE = 60;
    private final int TANK_WIDTH = 42;
    private final int TANK_LENGTH = 46;

    public Tank() {
        summonTank();


        extraHealth = 0;
        alive = true;

        shot1 = false;
        shot2 = false;

        image[0] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_blue.png";
        image[1] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_dark.png";
        image[2] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_green.png";
        image[3] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_sand.png";
        image[4] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_red.png";
        image[5] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_bigRed.png";
        image[6] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_darkLarge.png";
        image[7] = "C:\\Users\\Asus\\Desktop\\AP final project\\images\\tank and bullet\\tank_huge.png";


        Random random = new Random();
        int rand = random.nextInt(6);
        try{
            tankImage = ImageIO.read(new File(image[rand]));
        }
        catch(IOException e){
            System.out.println(e);
        }


    }



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


    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public boolean isAlive() {
        return alive;
    }

    public void destruction(){
        if(health + extraHealth <= 0)
            alive = false;
    }



    // tank's moving methods.
    public void moveUp(){
        int tmpX , tmpY;
        tmpX = dimensionX;
        tmpY = dimensionY - 8;
        if (checkMove(tmpX , tmpY))
        dimensionY = tmpY;
    }

    public void moveDown(){
        int tmpX , tmpY;
        tmpX = dimensionX;
        tmpY = dimensionY + 8;
        if (checkMove(tmpX , tmpY))
            dimensionY = tmpY;
    }

    public void moveRight(){
        int tmpX , tmpY;
        tmpX = dimensionX + 8;
        tmpY = dimensionY;
        if (checkMove(tmpX , tmpY))
            dimensionX = tmpX;
    }

    public void moveLeft(){
        int tmpX , tmpY;
        tmpX = dimensionX - 8;
        tmpY = dimensionY;
        if (checkMove(tmpX , tmpY))
            dimensionX = tmpX;
    }


    // tank's turning methods.
    public void turnClockwise(){
        angle += 4;
    }

    public void turnAntiClockwise(){
        angle -= 4;
    }

    public void fire() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                checkFire();
                bullet = new Bullet();
                bullet.setDimensionX(dimensionX);
                bullet.setDimensionY(dimensionY);
                bullet.setAngle(angle);
                long time = System.currentTimeMillis();
                while (bullet.isAlive() && (System.currentTimeMillis() - time) <= 4){
                    bullet.move();
                }
                if (!(bullet.isAlive() && (System.currentTimeMillis() - time) <= 4))
                    bullet.kill();
            }
        };
        thread.start();
    }

    public void checkFire(){
        if (!shot1){
            shot1 = true;
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(1000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    shot1 = false;
                    shot2 = false;

                }
            };
            try {
                t.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        if (!shot2){
            shot2 = true;
        }
    }


    public void randomMove(){
        Random random = new Random();
        int rand1 = random.nextInt(4);

        switch (rand1){
            case 0 :
                moveUp();
                break;
            case 1 :
                moveRight();
                break;
            case 2 :
                moveDown();
                break;
            case 3 :
                moveLeft();
                break;
        }

        int rand2 = random.nextInt(2);

        switch (rand2){
            case 1 :
                turnClockwise();
                break;
            case 2 :
                turnAntiClockwise();
                break;
        }
    }

    public void checkBound() {
        this.setDimensionX(Math.max(this.getDimensionX(), 30));
        this.setDimensionX(Math.min(this.getDimensionX(), GameFrame.GAME_WIDTH - 30));
        this.setDimensionY(Math.max(this.getDimensionY(), 50));
        this.setDimensionY(Math.min(this.getDimensionY(), GameFrame.GAME_HEIGHT - 40));
    }

    public void renderTank(Graphics2D graphics2D){
        AffineTransform trans = AffineTransform.getTranslateInstance(dimensionX, dimensionY);
        trans.rotate(Math.toRadians(angle));
        trans.translate(-TANK_WIDTH/2 , -TANK_LENGTH/2);
        graphics2D.drawImage(tankImage , trans , null);
        //graphics2D.drawRect(dimensionX , dimensionY , side , side);

    }


    public boolean checkMove(int tmpX , int tmpY){
        for (Wall wall : Data.walls){
            if (wall.checkOverlap(tmpX, tmpY))
                return false;
        }
        return true;
    }


    public void summonTank(){
        Random random = new Random();
        int tmpX;
        int tmpY;

        while (true) {
             tmpX  = random.nextInt(GameFrame.GAME_WIDTH) + SIDE/2;
             tmpY = random.nextInt(GameFrame.GAME_HEIGHT) - SIDE/2;
            boolean breaking=false;
            for (Wall wall : Data.walls) {
                if (!(wall.checkOverlap(tmpX,tmpY))) {
                    this.dimensionX = tmpX;
                    this.dimensionY = tmpY;
                    breaking=true;
                    break;
                }

            }
            if(breaking)
                break;
        }
    }


    public void encounter(){
        for (Bullet bullett : Data.bullets){
            if (this.dimensionX - SIDE/2 <= bullett.getDimensionX() && bullett.getDimensionX() <= this.dimensionX + SIDE/2
            && this.dimensionY - SIDE/2 <= bullett.getDimensionY() && bullett.getDimensionY() <= this.dimensionY + SIDE/2){
                this.maxHealth -= bullett.getMaxDamage();
            }
        }
    }
}










