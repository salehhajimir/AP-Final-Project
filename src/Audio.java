import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Audio {
    static final String MenuMusicLocation = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Menu Song\\Hyouhaku+Kokuten.wav";
    static final String TankExplosion = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Tank explosion sound.wav";
    static final String TankDeparture = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Moving tank sound.wav";
    static final String TankShooting = "C:\\Users\\Asus\\Desktop\\AP final project\\Audio\\Tank shooting sound";

    static Tank tank;



    public Audio(Tank intendedTank){
        tank = intendedTank;
    }



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

   static void tankExplosionSound() {

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

    static void tankShootingSound(){

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

    static void tankDepartureSound(){

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

    static void tankDeparture(){
        while (tank.isKeyDOWN() || tank.isKeyLEFT() || tank.isKeyRIGHT() || tank.isKeyUP()){
            tankDepartureSound();
        }
    }

    static void tankShooting(){
    }

    static void tankExplosion() {
    }
}
