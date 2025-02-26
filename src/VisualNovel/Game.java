package VisualNovel;

import VisualNovel.Screens.IntroScreen;
import VisualNovel.Screens.MainScreen;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game {
    ///////
    //IMG//
    ///////
    private static String currentBackgroundImage;
    private static String currentAnimation;
    /////////
    //SOUND//
    /////////
    private static Clip backgroundMusic;
    private static Clip soundEffect;
    private static int masterVolume = 50;

    ////////
    //TEXT//
    ////////
    private static int textSpeed = 30;


    public static void main(String[] args) {
        Config config = Config.getInstance();
        ////////////////
        //JPANEL SETUP//
        //************//
        ////////////////
        JFrame frame = new JFrame(config.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        if(config.cursor != null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image cursorImage = toolkit.getImage(config.cursor);
            Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "Custom Cursor");
            frame.setCursor(customCursor);
        }

        JLayeredPane panel = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon(currentBackgroundImage);
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Add a KeyListener to detect when "Esc" is pressed
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0); // Close the game when "Esc" is pressed
                }
            }
        });
        frame.setFocusable(true);

        ///////////
        //SCREENS//
        //*******//
        ///////////
        if(config.introAnimation == null && config.introImage == null) {
            try {
                MainScreen.mainScreen(panel);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else IntroScreen.playIntro(panel);

        frame.add(panel);
        frame.setVisible(true);
    }

    ///////
    //IMG//
    ///////
    public static String getCurrentBackgroundImage() {
        return currentBackgroundImage;
    }


    /////////
    //SOUND//
    /////////
    public static void setCurrentBackgroundImage(String currentBackgroundImage) {
        Game.currentBackgroundImage = currentBackgroundImage;
    }
    public static Clip getSoundEffect() {
        return soundEffect;
    }
    public static void setSoundEffect(Clip soundEffect) {
        Game.soundEffect = soundEffect;
    }
    public static Clip getBackgroundMusic() {
        return backgroundMusic;
    }
    public static void setBackgroundMusic(Clip backgroundMusic) {
        Game.backgroundMusic = backgroundMusic;
    }
    public static int getMasterVolume() {
        return masterVolume;
    }
    public static void setMasterVolume(int masterVolume) {
        Game.masterVolume = masterVolume;
    }
    public static int getTextSpeed() {
        return textSpeed;
    }
    public static void setTextSpeed(int textSpeed) {
        Game.textSpeed = textSpeed;
    }
    public static String getCurrentAnimation() {return currentAnimation;}
    public static void setCurrentAnimation(String currentAnimation) {Game.currentAnimation = currentAnimation;}

}