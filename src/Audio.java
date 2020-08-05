import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;


/**
 * class for setting audios in game and menu.
 */
public class Audio {

    // Audios paths.
    static final String MenuMusicLocation = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Menu Song\\Hyouhaku+Kokuten.wav";
    static final String TankExplosion = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Tank explosion sound.wav";
    static final String TankDeparture = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Moving tank sound.wav";
    static final String TankShooting = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Tank shooting sound";


    public Audio() {
    }


    /**
     * plays menu music
     */
    static void playMenuMusic() {

        try {
            File musicPath = new File(MenuMusicLocation);

            if (musicPath.exists()) {
                AudioInputStream musicInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(musicInputStream);
                clip.start();

                JOptionPane.showMessageDialog(null, "OK");
            } else {
                System.out.println(System.err);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * plays tank and wall explosion sound.
     */
    static void explosionSound() {
        try {
            File musicPath = new File(TankExplosion);

            if (musicPath.exists()) {
                AudioInputStream musicInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(musicInputStream);
                clip.start();

                JOptionPane.showMessageDialog(null, "OK");
            } else {
                System.out.println(System.err);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * plays shooting sound.
     */
    static void shootingSound() {
        try {
            File musicPath = new File(TankShooting);

            if (musicPath.exists()) {
                AudioInputStream musicInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(musicInputStream);
                clip.start();

                JOptionPane.showMessageDialog(null, "OK");
            } else {
                System.out.println(System.err);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays sound of tank's wheels while moving.
     */
    static void tankDepartureSound() {
        try {
            File musicPath = new File(TankDeparture);

            if (musicPath.exists()) {
                AudioInputStream musicInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(musicInputStream);
                clip.start();

                JOptionPane.showMessageDialog(null, "OK");
            } else {
                System.out.println(System.err);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
