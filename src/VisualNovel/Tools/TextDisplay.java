package VisualNovel.Tools;

import VisualNovel.Config;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TextDisplay extends JPanel {
    private final String text; // Full text to display
    private final Map<Character, ImageIcon> fontMap; // Map characters to images
    private final StringBuilder displayedText; // Stores progressively displayed text
    private int currentIndex = 0; // Tracks the current letter index
    private final Timer timer; // Timer for letter animation
    private static float masterVolume = 0.5f; // Default volume (0.0f - 1.0f)
    private boolean isLoopRunning = true;

    public TextDisplay(String text, int textSpeed) {
        this.text = text.toUpperCase(); // Ensure uppercase consistency
        this.fontMap = loadFontImages();
        this.displayedText = new StringBuilder();

        setOpaque(false); // Make the panel transparent

        // Timer for letter-by-letter animation
        timer = new Timer(textSpeed, e -> displayNextLetter());
        timer.start();
    }

    // Load images for each character
    private Map<Character, ImageIcon> loadFontImages() {
        Map<Character, ImageIcon> map = new HashMap<>();
        String basePath = "res/Pictures/Letters/";

        // Load images for letters A-Z
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(c, new ImageIcon(basePath + c + ".png")); // Ensure files are named A.png, B.png, etc.
        }

        // Load images for special characters
        map.put(' ', new ImageIcon(basePath + "space.png"));  // Space character
        map.put('.', new ImageIcon(basePath + "dot.png"));    // Period
        map.put(',', new ImageIcon(basePath + "comma.png"));  // Comma
        map.put('?', new ImageIcon(basePath + "question.png"));// Question mark

        return map;
    }

    public boolean isAnimationComplete() {
        return currentIndex >= text.length();
    }

    public void completeAnimation() {
        timer.stop();
        currentIndex = text.length();
        displayedText.setLength(0);
        displayedText.append(text); // Display full text instantly
        repaint();
    }

    // Display the next letter
    private void displayNextLetter() {
        if (currentIndex < text.length()) {
            displayedText.append(text.charAt(currentIndex));
            playLetterSound();
            currentIndex++;
            repaint();
        } else {
            timer.stop();
        }
    }

    // Play a sound for each letter with volume control
    private void playLetterSound() {
        Config config = Config.getInstance();
        SoundHandler.playSoundEffect(config.letterSound);
    }


    // Update the master volume (0.0f to 1.0f)
    public static void setMasterVolume(int volumePercentage) {
        masterVolume = volumePercentage / 100.0f;
    }

    public void stopAnimation() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Stop the timer animation
        }
        currentIndex = text.length(); // Ensure the animation completes
        displayedText.setLength(0);
        displayedText.append(text); // Show full text immediately
        repaint(); // Redraw panel
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10; // Starting X position
        int y = 10; // Starting Y position
        final int letterWidth = 15;  // Letter width
        final int letterHeight = 15; // Letter height
        final int letterSpacing = 12; // Space between letters
        final int wordSpacing = 20; // Extra space between words
        final int lineSpacing = 25; // Space between lines

        int panelWidth = getWidth(); // Get the width of the panel

        // Step 1: Precompute word positions **before drawing**
        java.util.List<WordPosition> wordPositions = new java.util.ArrayList<>();
        String[] words = displayedText.toString().split(" ");

        for (String word : words) {
            int wordWidth = word.length() * letterSpacing; // Total width of the word

            // Move to the next line BEFORE drawing anything
            if (x + wordWidth > panelWidth) {
                x = 10; // Reset X to the start
                y += lineSpacing; // Move to the next line
            }

            // Store each letter's position BEFORE drawing
            for (char c : word.toCharArray()) {
                wordPositions.add(new WordPosition(c, x, y));
                x += letterSpacing; // Move right for next letter
            }

            x += wordSpacing; // Space after the word
        }

        // Step 2: Now draw everything **only once** after computing positions
        for (WordPosition wp : wordPositions) {
            ImageIcon icon = fontMap.get(wp.character);
            if (icon != null) {
                g.drawImage(icon.getImage(), wp.x, wp.y, letterWidth, letterHeight, this);
            }
        }
    }

    // Helper class to store word positions
    private static class WordPosition {
        char character;
        int x, y;

        WordPosition(char character, int x, int y) {
            this.character = character;
            this.x = x;
            this.y = y;
        }
    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 200); // Default size for the panel
    }
}

