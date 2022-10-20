package bomberman.Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class LoadSound {
    Clip clip;
    URL soundURL;
    public LoadSound(String path) {
        System.out.println(path);
        soundURL = getClass().getResource(path);
        System.out.println(soundURL);
    }
    public void play() {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
        }
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        if (clip != null) {
            clip.stop();
        }

    }

    public boolean isRunning() {
        if (clip == null)
            return false;
        return clip.isRunning();
    }

    public void breakingLoop() {
    }
}
