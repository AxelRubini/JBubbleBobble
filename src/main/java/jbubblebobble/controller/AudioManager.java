package jbubblebobble.controller;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUp;
import utility.Config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.*;


/**
 * Audio manager class used to play audio files.
 * The audio manager is a singleton class that is used to play audio files.
 * implements Observer to listen to the player events
 */
public class AudioManager  implements Observer {

    private static AudioManager instance;
    private Clip themeClip;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    private AudioManager() {

    }

    /**
     * Play files sounds
     *
     * @param filename the filename
     */
    public void play(String filename) {

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            clip.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Loop the theme music through levels
     *
     * @param filename the filename
     */
    public void playTheme(String filename) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            themeClip = AudioSystem.getClip();
            themeClip.open(audioIn);
            AudioFormat format = audioIn.getFormat();
            int loopStartFrame = (int) (9 * format.getFrameRate());
            int loopEndFrame = (int) (107 * format.getFrameRate());
            themeClip.setLoopPoints(loopStartFrame, loopEndFrame);
            themeClip.loop(Clip.LOOP_CONTINUOUSLY);
            themeClip.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Stop theme.
     */
    public void stopTheme() {
        if (themeClip != null && themeClip.isRunning())
            themeClip.stop();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Player) {
            if (arg.equals(1)) {
                play(Config.PATH_TO_AUDIO_PLAYER_DEATH);
            } else if (arg.equals(2)) {
                play(Config.PATH_TO_AUDIO_ENEMY_DEATH);

            } else if (arg.equals(3)) {
                play(Config.PATH_TO_AUDIO_POWER_UP);
            }
        }
    }
}

