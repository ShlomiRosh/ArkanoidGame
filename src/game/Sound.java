package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {// Holds one audio file
    private String name;
    private int count;
    private Clip clip;
    private KeyboardSensor keyboard;

    public Sound(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void shouldStop() {
            this.clip.close();
    }

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\User\\IdeaProjects\\אני\\sound\\"+name).getAbsoluteFile());
            // create clip reference
            clip = AudioSystem.getClip();
            // open audioInputStream to the clip
            clip.open(audioInputStream);
            this.clip.loop(count);
            this.clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException aeu) {
            System.out.println(aeu);
        }
    }

}
