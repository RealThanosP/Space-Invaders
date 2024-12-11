package gui;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class GameAudioPlayer {
    private Clip audioClip;

    /**
     * Loads and prepares the audio file for playback.
     * 
     * @param filePath Path to the .wav file.
     */
    public void load(URL filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Failed to load audio file: " + filePath);
        }
    }

    /**
     * Starts playing the audio. Can be looped indefinitely.
     * 
     * @param loop If true, the audio will loop continuously.
     */
    public void play(boolean loop) {
        if (audioClip != null) {
            if (loop) {
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                audioClip.loop(0);
            }
            audioClip.start();
        }
    }

    /**
     * Stops the currently playing audio.
     */
    public void stop() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.flush(); // Clear the clip's buffer
        }
    }

    /**
     * Closes the audio clip and releases resources.
     */
    public void close() {
        if (audioClip != null) {
            stop();
            audioClip.close();
        }
    }

    /**
     * Adjusts the audio volume.
     * 
     * @param volume Volume level in decibels (e.g., -10.0f for lower, 6.0f for higher).
     */
    public void setVolume(float volume) {
        if (audioClip != null) {
            FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }
}
