import com.sun.xml.internal.bind.v2.TODO;

import javax.sql.DataSource;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 */
public class GameState {


    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT, keyA , keyD , keySPACE;
    public boolean  gameOver;
    private KeyHandler keyHandler;

    private Tank tank1 , tank2;

    public Tank getTank1() {
        return tank1;
    }

    public Tank getTank2() {
        return tank2;
    }

    public GameState() {

        gameOver = false;
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        keySPACE = false;
        keyA = false;
        keyD = false;

        tank1 = new Tank();
        tank2 = new Tank();

        Data.tanks.add(tank1);
        Data.tanks.add(tank2);




        keyHandler = new KeyHandler();
    }

    /**
     * The method which updates the game state.
     */
    public void update() {

        //update tank states.
        if (keyUP)
            tank1.moveUp();

        if (keyDOWN)
            tank1.moveDown();

        if (keyLEFT)
             tank1.moveLeft();

        if (keyRIGHT)
            tank1.moveRight();

        if (keyA)
            tank1.turnAntiClockwise();

        if (keyD)
           tank1.turnClockwise();


        //tank1.checkBound();
        tank2.randomMove();
        //tank2.checkBound();







        if (keySPACE){
            tank1.fire();
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Data.removeBullet();
        Data.removeWall();

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
                    break;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP :
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN :
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT :
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT :
                    keyRIGHT = false;
                    break;
                case KeyEvent.VK_A :
                    keyA = false;
                    break;
                case KeyEvent.VK_D :
                    keyD = false;
                    break;
                case KeyEvent.VK_SPACE :
                    keySPACE = false;
                    break;
            }
        }

    }


}

