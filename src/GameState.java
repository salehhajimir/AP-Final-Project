import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 */
public class GameState {

    public int tankX, tankY, diam ,bulletX , bulletY;
    public double tankAngle , bulletAngle;

    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT , keySPACE1 , keySPACE2 , keyA , keyD;
    public boolean shot1 , shot2 , gameOver;
    private KeyHandler keyHandler;

    private Tank tank;
    private Bullet bullet;




    public GameState(Tank gameTank , Bullet gameBullet) {

        tank = gameTank;
        bullet = gameBullet;

        //dar methode update bayad mokhtasate tank (tankX va tankY) ra dar class hayeshan vared konam.
        tankX = tank.getDimensionX();
        tankY = tank.getDimensionY();


        bulletX = tankX;
        bulletY = tankY;


        keySPACE1 = false;
        keySPACE2 = false;
        shot1 = false;
        shot2 = false;
        //
        diam = 32;
        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //extra
        keyA = false;
        keyD = false;
        tankAngle = 0;
        bulletAngle = tankAngle;
        //
        keyHandler = new KeyHandler();
    }

    /**
     * The method which updates the game state.
     */
    public void update() {

        //update tank states.
        if (keyUP)
            tankY -= 8;

        if (keyDOWN)
            tankY += 8;

        if (keyLEFT)
            tankX -= 8;

        if (keyRIGHT)
            tankX += 8;

        if (keyA)
            tankAngle-= 4;

        if (keyD)
            tankAngle+= 4;

        tankX = Math.max(tankX, 30);
        tankX = Math.min(tankX, GameFrame.GAME_WIDTH - 30);
        tankY = Math.max(tankY, 50);
        tankY = Math.min(tankY, GameFrame.GAME_HEIGHT - 40);



        // update bullets' states.
        if (!keySPACE1){
            bulletX = tankX + 16;
            bulletY = tankY + 16;
            bulletAngle = tankAngle;
        }
        if (keySPACE1){
            shot1 = true;
            Bullet bullet1 = new Bullet();
            bulletAngle += 0;
            bulletX += 16 * Math.cos(Math.abs(Math.toRadians(bulletAngle)));
            bulletY += 16 * Math.sin(Math.toRadians(bulletAngle));
        }
        if (keySPACE2 && shot1){
            Bullet bullet2 = new Bullet();
            shot2 = true;
        }


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               shot1 = false;
               shot2 = false;
            }
        } , 1000 - System.currentTimeMillis());



    }


    public KeyListener getKeyListener() {
        return keyHandler;
    }




    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = true;
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
                case KeyEvent.VK_ESCAPE:
                    gameOver = true;
                    break;

                //extra
                case KeyEvent.VK_A:
                    keyA = true;
                    break;
                case KeyEvent.VK_D:
                    keyD = true;
                    break;
                case KeyEvent.VK_SPACE: {
                    keySPACE1 = true;
                    System.out.println("bullet" + bulletX + "-----" + bulletY);
                    System.out.println("tank" + tankX + "--------" + tankY);
                    System.out.println("angle" + bulletAngle);
                    System.out.println(keySPACE1);
                    //
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = false;
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

                //extra
                case KeyEvent.VK_A:
                    keyA = false;
                    break;
                case KeyEvent.VK_D:
                    keyD = false;
                    break;
                //
            }
        }

    }


}

