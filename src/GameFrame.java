import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * The window on which the rendering is performed.
 * actually it performs triple-buffering.
 */
public class GameFrame extends JFrame {

    public static final int ScorePanel_WIDTH = 240;
    public static final int GAME_HEIGHT = 720;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
    // path of the background's image file.
    private final String BACKGROUND = ".\\images\\backGround.png";
    // background's image.
    private BufferedImage bufferedImage;



    private long lastRender;
    private ArrayList<Float> fpsHistory;

    private BufferStrategy bufferStrategy;

    public GameFrame(String title) {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH + ScorePanel_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

        try{
            bufferedImage = ImageIO.read(new File(BACKGROUND));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * This must be called once after the JFrame is shown:
     *    frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {
        // Draw background
        g2d.drawImage(bufferedImage , 0 , 0 ,null);

        // Draw score panel.
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(GAME_WIDTH - 5 , 0 ,ScorePanel_WIDTH , GAME_HEIGHT);


        //drawing walls
        for (Wall wall : Data.walls){
                wall.renderWall(g2d);
        }



        //drawing tanks
        for (Tank tank : Data.tanks){
            if (tank.isAlive())
                tank.renderTank(g2d);
        }

        for (Tank tank : Data.deadTanks){
            tank.renderDeadTank(g2d);
        }



        //drawing bullets
        for (Bullet bullet : Data.bullets){
            if (bullet.isAlive())
                bullet.renderBullet(g2d);

            // drawing gifts.
            for (Gift gift : Data.gifts){
                if (gift.isActive()){
                    gift.renderGift(g2d);
                }
            }
        }








        // Print FPS info
        long currentRender = System.currentTimeMillis();
        if (lastRender > 0) {
            fpsHistory.add(1000.0f / (currentRender - lastRender));
            if (fpsHistory.size() > 100) {
                fpsHistory.remove(0); // remove oldest
            }
            float avg = 0.0f;
            for (float fps : fpsHistory) {
                avg += fps;
            }
            avg /= fpsHistory.size();
            String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
                    avg, (currentRender - lastRender));
            g2d.setColor(Color.CYAN);
            g2d.setFont(g2d.getFont().deriveFont(18.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            int strHeight = g2d.getFontMetrics().getHeight();
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight+50);
        }
        lastRender = currentRender;





        // Draw GAME OVER
        if (state.gameOver) {
            String str = "GAME OVER";
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);

            // display winner.
            String winner = "";
            for (Player player : Data.players){
                if (player.getPlayerTank().isAlive()){
                    winner += player.getUserName() + " won!";
                    break;
                }
            }

            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(32.0f));
            int strWidthh = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(winner, (GAME_WIDTH - strWidthh) / 2, (GAME_HEIGHT + 60) / 2);
        }

        // display players' information in score panel.
        String information = "";

        int height = 100;
        for (Player player : Data.players){
            information += player.getUserName() + " --> health : " + player.getPlayerTank().getHealth();


            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(16.0f));
            int strWidthh = g2d.getFontMetrics().stringWidth(information);
            g2d.drawString(information, (GAME_WIDTH), height);
        }
    }
}



