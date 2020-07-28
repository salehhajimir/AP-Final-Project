/*** In The Name of Allah ***/
package game.sample.ball;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

    public double tankX, tankY, diam ,bulletX , bulletY;
    public boolean gameOver;

    //extra
    public int angle;
    public int bulletAngle;
    private boolean keyA , keyD;
    //
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT , keySPACE ;
    public boolean shot , shot1;
    private KeyHandler keyHandler;





    public GameState() {
        Random random = new Random();
        tankX = random.nextInt(GameFrame.GAME_WIDTH);
        tankY = random.nextInt(GameFrame.GAME_HEIGHT);
        //extra
        bulletX = tankX;
        bulletY = tankY;
        keySPACE = false;
        shot = false;
        shot1 = false;
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
        angle = 0;
        bulletAngle = angle;
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
            angle-= 4;

        if (keyD)
            angle+= 4;

        tankX = Math.max(tankX, 30);
        tankX = Math.min(tankX, GameFrame.GAME_WIDTH - 30);
        tankY = Math.max(tankY, 50);
        tankY = Math.min(tankY, GameFrame.GAME_HEIGHT - 40);



        // update bullets' states.
        if (!keySPACE){
            bulletX = tankX + 16;
            bulletY = tankY + 16;
            bulletAngle = angle;
        }
        if (keySPACE){
            shot = true;
            bulletAngle += 0;
            bulletX += 16 * Math.cos(Math.abs(Math.toRadians(bulletAngle)));
            bulletY += 16 * Math.sin(Math.toRadians(bulletAngle));
        }
        //
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
                    keySPACE = true;
                    System.out.println("bullet" + bulletX + "-----" + bulletY);
                    System.out.println("tank" + tankX + "--------" + tankY);
                    System.out.println("angle" + bulletAngle);
                    System.out.println(keySPACE);
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

