import com.sun.xml.internal.bind.v2.TODO;

import javax.sql.DataSource;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Map;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 */
public class GameState {


    // booleans using for tank actions.
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT, keyA , keyD , keySPACE;
    // boolean which indicates the game is over or not
    public boolean  gameOver;
    // keyboard handler
    private KeyHandler keyHandler;

    private Player player , computer;
    private Tank tank1 , tank2;
    private Gift gift;

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

        Data.addGift();
        tank1 = new Tank();
        tank2 = new Tank();
        player = new Player("player");
        computer = new Player("computer");
        player.setPlayerTank(tank1);
        computer.setPlayerTank(tank2);

        Data.tanks.add(tank1);
        Data.tanks.add(tank2);
        Data.players.add(player);
        Data.players.add(computer);

        keyHandler = new KeyHandler();
    }



    /**
     * The method which updates the game state.
     */
    public void update() {

        // execute actions by receiving inputs from player.
        if (keyUP)
            tank1.moveUp();

        if (keyDOWN)
            tank1.moveDown();

        if (keyLEFT)
             tank1.moveLeft();

        if (keyRIGHT)
            tank1.moveRight();

        if (keyA)
            tank1.turnClockwise();

        if (keyD)
           tank1.turnAntiClockwise();

        if (keySPACE){
            tank1.fire();
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        tank1.checkBound();
        tank2.randomMove();
        tank2.checkBound();


        // handling bullets' encounters with tanks.
        for (Tank tank : Data.tanks){
            tank.encounter();
            tank.destruction();
        }

        // remove dead objects from the game.
        Data.removeBullet();
        Data.removeWall();
        Data.removeTank();

        if (System.currentTimeMillis() % 100000000 == 0){
            Data.gifts.get(0).setActive(false);
            Data.gifts.get(1).setActive(false);
        }

        if (System.currentTimeMillis() % 100000000 <= 5000){

        }



        checkGameOver();
    }


    /**
     *
     * @return
     */
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


    /**
     * checking if the game is over or not and calculate each player's score.
     */
    public void checkGameOver(){
        if (Data.tanks.size() == 1) {
            gameOver = true;
            for (Player player : Data.players){
                player.calculateScore();
                //System.out.println(player.getUserName() + " --> " + player.getPlayerScore());
            }
        }
    }

}

