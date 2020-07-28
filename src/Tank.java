import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.event.*;;
import java.awt.event.*;
import java.util.Random;
import java.util.TimerTask;


public class Tank {

    private int dimensionX , dimensionY , diam, angle , health , extraHealth;


    public boolean alive;
    private boolean    KeyUP ,keyDOWN, keyRIGHT, keyLEFT , KeyA , KeyD , KeySPACE;
    private KeyHandler keyHandler;

    public Tank(){
        Random random = new Random();
        dimensionX = random.nextInt(GameFrame.GAME_WIDTH);
        dimensionY = random.nextInt(GameFrame.GAME_HEIGHT);
        diam = 1;
        extraHealth = 0;
        //
        alive = true;
        KeyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        KeyA = false;
        KeyD = false;
        KeySPACE = false;

        keyHandler = new KeyHandler();
    }


    public void setExtraHealth(int extraHealth) {
        this.extraHealth = extraHealth;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getHealth() {
        return health;
    }

    public int getAngle() {
        return angle;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public boolean isKeyDOWN() {
        return keyDOWN;
    }

    public boolean isKeyLEFT() {
        return keyLEFT;
    }

    public boolean isKeyRIGHT() {
        return keyRIGHT;
    }

    public boolean isKeySPACE() {
        return KeySPACE;
    }

    public boolean isKeyUP() {
        return KeyUP;
    }




    public void update() {
        if (KeySPACE)
            //shootingBullet

        if (KeyUP)
            dimensionY -= 8;

        if (keyDOWN)
            dimensionY += 8;

        if (keyLEFT)
            dimensionX -= 8;

        if (keyRIGHT)
            dimensionX += 8;

        if (KeyA)
            angle -= 4;

        if (KeyD)
            angle += 4;





        dimensionX = Math.max(dimensionX, 30);
        dimensionX = Math.min(dimensionX, GameFrame.GAME_WIDTH - 30);
        dimensionY = Math.max(dimensionY, 50);
        dimensionY = Math.min(dimensionY, GameFrame.GAME_HEIGHT - 40);
    }





    boolean shoot(){
        if (isKeySPACE())
            return true;
        return false;
    }








    //Keyboard action handler.
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    KeyUP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
                case KeyEvent.VK_D:
                    KeyD = true;
                    break;
                case KeyEvent.VK_A:
                    KeyA = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    KeyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
                case KeyEvent.VK_D:
                    KeyD = false;
                    break;
                case KeyEvent.VK_A:
                    KeyA = false;
                    break;
            }
        }

    }


}
