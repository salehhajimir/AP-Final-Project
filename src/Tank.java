import com.sun.xml.internal.bind.v2.TODO;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Tank {



    // vars fot tank's coordinates , angle , amount of health and indicating color of tank.
    private int dimensionX, dimensionY, angle, health , tankColor;
    // booleans for controlling shooting 2 bullets in a second and aliveness.
    private boolean shot1 , shot2 , alive , healthGift , damageGift;
    // path of image's file.
    private final String TANK_IMAGE = ".\\images\\tank and bullet\\";
    // image file.
    private BufferedImage tankImage , deadTankImage;
    private String[] image = new String[8];
    // tank's width and length.
     static final int TANK_WIDTH = 42;
     static final int TANK_LENGTH = 46;
    // tank's speed.
    private static final int SPEED = 8;
    //tank's bullet which saves bullet's features.
    private Bullet bullet = new Bullet();


    public Tank() {
        summonTank();


        health = 100;

        alive = true;
        shot1 = false;
        shot2 = false;
        healthGift = false;
        damageGift = false;


        image[0] = TANK_IMAGE + "tank_blue.png";
        image[1] = TANK_IMAGE + "tank_dark.png";
        image[2] = TANK_IMAGE + "tank_green.png";
        image[3] = TANK_IMAGE + "tank_sand.png";
        image[4] = TANK_IMAGE + "tank_red.png";
        image[5] = TANK_IMAGE + "tank_bigRed.png";
        image[6] = TANK_IMAGE + "tank_darkLarge.png";
        image[7] = TANK_IMAGE + "tank_huge.png";


        Random random = new Random();
        tankColor = random.nextInt(6);
        try{
            tankImage = ImageIO.read(new File(image[tankColor]));
            deadTankImage = ImageIO.read(new File(TANK_IMAGE + "skull1.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }


    }


    /**
     * getter and setter methods.
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @param healthGift
     */
    public void setHealthGift(boolean healthGift) {
        this.healthGift = healthGift;
    }

    /**
     *
     * @param damageGift
     */
    public void setDamageGift(boolean damageGift) {
        this.damageGift = damageGift;
    }

    /**
     *
     * @param bullet
     */
    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    /**
     *
     * @return
     */
    public Bullet getBullet() {
        return bullet;
    }

    /**
     *
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
     * @param tankColor
     */
    public void setTankColor(int tankColor) {
        this.tankColor = tankColor;
    }

    /**
     *
     * @return
     */
    public boolean isDamageGift() {
        return damageGift;
    }

    /**
     *
     * @return
     */
    public boolean isHealthGift() {
        return healthGift;
    }

    /**
     *
     * @param angle
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }

    /**
     *
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     *
     * @return
     */

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
    public int getAngle() {
        return angle;
    }


    /**
     * killing tank by checking its health
     */
    public void destruction(){
        if(health <= 0)
            alive = false;
    }


    /**
     *  tank's moving methods.
      */
    public void moveUp(){
        int tmpX , tmpY;
        tmpX = dimensionX;
        tmpY = dimensionY - SPEED;
        if (checkMove(tmpX , tmpY))
        dimensionY = tmpY;
    }

    /**
     *
     */
    public void moveDown(){
        int tmpX , tmpY;
        tmpX = dimensionX;
        tmpY = dimensionY + SPEED;
        if (checkMove(tmpX , tmpY))
            dimensionY = tmpY;
    }

    /**
     *
     */
    public void moveRight(){
        int tmpX , tmpY;
        tmpX = dimensionX + SPEED;
        tmpY = dimensionY;
        if (checkMove(tmpX , tmpY))
            dimensionX = tmpX;
    }

    /**
     *
     */
    public void moveLeft(){
        int tmpX , tmpY;
        tmpX = dimensionX - SPEED;
        tmpY = dimensionY;
        if (checkMove(tmpX , tmpY))
            dimensionX = tmpX;
    }

    /**
     * tank's turning methods.
     */
    public void turnClockwise(){
        angle -= 10;
        angle = angle % 360;
        if (angle < 0)
            angle += 360;
    }

    /**
     *
     */
    public void turnAntiClockwise(){
        angle += 10;
        angle = angle % 360;

    }


    /**
     * tank's shooting method.
     */
    public void fire() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                if(!fireAvalable())
                    return;
                Bullet bullett = new Bullet();
                bullett.setDamage(bullet.getDamage());
                bullett.setShootingX(dimensionX);
                bullett.setShootingY(dimensionY);
                bullett.setDimensionX(dimensionX + (int)(20 * Math.cos(Math.abs(Math.toRadians(angle)))));
                bullett.setDimensionY(dimensionY + (int)(20 * Math.sin(Math.toRadians(angle))));
                bullett.setAngle(angle);
                long time = System.currentTimeMillis();
                while (bullett.isAlive() && (System.currentTimeMillis() - time) <= 4000){
                    bullett.move();
                    bullett.reflectBullet();

                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if ((System.currentTimeMillis() - time) > 4000)
                    bullett.kill();
            }
        };
        thread.start();
    }

    /**
     * controlling number of tank's shots in a second.
     */
    public boolean fireAvalable(){
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
            return  true;
        }
        else if (!shot2){
            shot2 = true;
            return  true;
        }

        return false;
    }






    /**
     * a method for random movement. being used for computer.
     */
    public void randomMove() {
        Random random = new Random();
        int rand1 = random.nextInt(4);

        switch (rand1){
            case 0 :
                moveUp();
                break;
            case 1 :
                moveDown();
                break;
            case 2 :
                moveRight();
                break;
            case 3 :
                moveLeft();
                break;
        }

        int rand2 = random.nextInt(2);
        switch (rand2){
            case 0 :
                turnClockwise();
                break;
            case 1 :
                turnAntiClockwise();
                break;
        }

    }

    /**
     * controlling the starting coordinates of tank.
     */
    public void checkBound() {
        this.setDimensionX(Math.max(this.getDimensionX(), Map.WIDTH_CONSTANT));
        this.setDimensionX(Math.min(this.getDimensionX(), GameFrame.GAME_WIDTH - Map.WIDTH_CONSTANT));
        this.setDimensionY(Math.max(this.getDimensionY(), Map.HEIGHT_CONSTANT));
        this.setDimensionY(Math.min(this.getDimensionY(), GameFrame.GAME_HEIGHT - Map.HEIGHT_CONSTANT));
    }

    /**
     * rendering tank's image in game frame.
     * @param graphics2D
     */
    public void renderTank(Graphics2D graphics2D){
        AffineTransform trans = AffineTransform.getTranslateInstance(dimensionX, dimensionY);
        trans.rotate(Math.toRadians(angle + 180));
        trans.translate(-TANK_WIDTH/2 , -TANK_LENGTH/2);
        graphics2D.drawImage(tankImage , trans , null);
        //graphics2D.drawRect(dimensionX , dimensionY , side , side);

    }


    /**
     * controlling tank's movement in playGround.
     * @param tmpX
     * @param tmpY
     * @return
     */
    public boolean checkMove(int tmpX , int tmpY){
        for (Wall wall : Data.walls){
            if (wall.checkOverlap(tmpX, tmpY))
                return false;
        }
        return true;
    }


    /**
     * initializing coordinates of tank
     */
    public void summonTank(){
        Random random = new Random();
        int tmpX;
        int tmpY;

        while (true) {
             tmpX  = random.nextInt(GameFrame.GAME_WIDTH);
             tmpY = random.nextInt(GameFrame.GAME_HEIGHT);
            boolean breaking=false;
            for (FloorBlock floorBlock : Data.floor) {
                if ((floorBlock.checkOverlap(tmpX,tmpY))) {
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


    /**
     * checking if the tank was being harmed or not.
     */
    public void encounter(){
        //Rectangle rect1 = new Rectangle(dimensionX,dimensionY , TANK_WIDTH,TANK_LENGTH );
        for (Bullet bullett : Data.bullets){
            //Rectangle rect2 = new Rectangle(bullett.getDimensionX(),bullett.getDimensionY() ,12 , 16);
            if (checkOverlap(bullett.getDimensionX() , bullett.getDimensionY()) && this.isAlive()){
                this.health -= bullett.getDamage();
                if (health <= 0){
                    destruction();
                }
                bullett.kill();
            }
        }
    }

    /**
     * cheking the overlap of other objects with tank.
     * @param x
     * @param y
     * @return
     */
    public boolean checkOverlap(int x , int y){
        if (x > dimensionX - 15 && x < dimensionX + 15 && y > dimensionY - 15 && y < dimensionY + 15)
            return true;
        return false;
    }

    public void renderDeadTank(Graphics2D graphics2D){
        AffineTransform trans = AffineTransform.getTranslateInstance(dimensionX, dimensionY);
        graphics2D.drawImage(deadTankImage , trans , null);
    }




}










