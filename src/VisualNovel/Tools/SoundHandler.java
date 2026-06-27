package VisualNovel.Tools;

import VisualNovel.Config;
import VisualNovel.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {
    public static String musicPath;
    public SoundHandler(){

    }
    public static void playBackgroundMusic(String musicFilePath) {
        try {
            Clip backgroundMusic = Game.getBackgroundMusic();

            // Prevent the same sound from playing twice
            if (backgroundMusic != null && backgroundMusic.isRunning() && musicFilePath.equals(musicPath)) {
                return;
            }

            // Stop and close any currently playing music
            if (backgroundMusic != null && backgroundMusic.isRunning()) {
                backgroundMusic.stop();
                backgroundMusic.close();
            }

            File soundFile = new File(musicFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

            // Store the new clip in Game and update musicPath
            Game.setBackgroundMusic(backgroundMusic);
            musicPath = musicFilePath;

            // Now set the volume on the newly assigned clip
            setBackgroundMusicVolume(Game.getMasterVolume());

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void setBackgroundMusicVolume(int volume) {
        Clip bgMusic = Game.getBackgroundMusic();
        if (bgMusic != null && bgMusic.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) bgMusic.getControl(FloatControl.Type.MASTER_GAIN);

            float gain = (volume <= 0) ? gainControl.getMinimum() : (float) (20.0 * Math.log10(volume / 100.0));

            gainControl.setValue(gain); // Apply volume change

            // Force immediate processing by briefly stopping & restarting playback
            if (bgMusic.isRunning()) {
                bgMusic.stop();
                bgMusic.start();
            }
        }
    }

    public static void playSoundEffect(String soundLink) {
        try {
            File soundFile = new File(soundLink);

            if (!soundFile.exists()) {
                System.err.println("Sound file not found: " + soundFile.getAbsolutePath());
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Ensure volume control exists before trying to set it
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                int masterVolume = Math.max(0, Math.min(Game.getMasterVolume(), 100)); // Ensure valid range
                float gain = (masterVolume == 0) ? volumeControl.getMinimum() : (float) (20.0 * Math.log10(masterVolume / 100.0));

                volumeControl.setValue(gain);
            } else {
                System.err.println("Volume control not supported for this sound effect.");
            }

            clip.start();

            // Release resources when done
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
