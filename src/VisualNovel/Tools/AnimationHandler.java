package VisualNovel.Tools;

import VisualNovel.Config;
import VisualNovel.Game;
import VisualNovel.Screens.GamePlayer;
import VisualNovel.Screens.MainScreen;
import VisualNovel.Tools.InfoHolder.AnimationHolder;
import VisualNovel.Tools.InfoHolder.StoryHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AnimationHandler {
    private static Timer timer;
    private static Timer transitionTimer;
    private static ArrayList<String> animationList = new ArrayList<>();
    public static boolean isTransitionPlaying = false;

    public static void animation(String animationEnum, JLayeredPane panel) {
        int defaultSpeed = Config.getInstance().animationSpeedInMilliseconds;
        playAnimation(animationEnum, panel, defaultSpeed);
    }
    public static void playAnimation(String animationEnum, JLayeredPane panel, int speed) {
        Config config = Config.getInstance();
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        final int[] i = {0};
        animationList = AnimationHolder.valueOf(animationEnum).getFramePaths();
        if (animationList.isEmpty()) return;

        Game.setCurrentBackgroundImage(animationList.get(i[0]));
        panel.repaint();

        timer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i[0]++;
                if (i[0] >= animationList.size()) {
                    i[0] = 0;
                }
                Game.setCurrentBackgroundImage(animationList.get(i[0]));
                panel.repaint();
            }
        });
        timer.start();
    }


    public static void playTransitionAnimation(String transitionEnum, String newBackground, String newAnimation, JLayeredPane panel) {
        if (transitionEnum == null) {
            updateScene(newBackground, newAnimation, panel);
            return;
        }

        isTransitionPlaying = true;
        animationList = AnimationHolder.valueOf(transitionEnum).getFramePaths();

        if (animationList.isEmpty()) {
            isTransitionPlaying = false;
            updateScene(newBackground, newAnimation, panel);
            return;
        }

        int frameDuration = Config.getInstance().transitionAnimationSpeedInMilliseconds;
        int totalAnimationTime = animationList.size() * frameDuration;

        playAnimation(transitionEnum, panel, frameDuration);

        transitionTimer = new Timer(totalAnimationTime, evt -> {
            isTransitionPlaying = false;
            updateScene(newBackground, newAnimation, panel);
        });
        transitionTimer.setRepeats(false);
        transitionTimer.start();
    }

    public static void playIntro(String introAnimation, String newBackground, int imageTimer, String introSound, JLayeredPane panel) {
        // Play intro sound if provided
        if (introSound != null && !introSound.isEmpty()) {
            SoundHandler.playBackgroundMusic(introSound);
        }

        // If no animation but there's a background image, show it for the given time
        if (introAnimation == null || introAnimation.isEmpty()) {
            updateScene(newBackground, null, panel);

            // If imageTimer > 0, wait before transitioning to MainScreen
            if (imageTimer > 0) {
                Timer imageDisplayTimer = new Timer(imageTimer, evt -> showMainScreen(panel));
                imageDisplayTimer.setRepeats(false);
                imageDisplayTimer.start();
            } else {
                showMainScreen(panel);
            }
            return;
        }

        try {
            // Try fetching animation frames
            AnimationHolder animationHolder = AnimationHolder.valueOf(introAnimation);
            List<String> animationList = animationHolder.getFramePaths();

            if (animationList.isEmpty()) {
                // No frames available, treat as an image instead
                updateScene(newBackground, null, panel);
                if (imageTimer > 0) {
                    Timer imageDisplayTimer = new Timer(imageTimer, evt -> showMainScreen(panel));
                    imageDisplayTimer.setRepeats(false);
                    imageDisplayTimer.start();
                } else {
                    showMainScreen(panel);
                }
                return;
            }

            // Calculate total animation duration
            int totalAnimationTime = (imageTimer > 0) ? imageTimer : animationList.size() * Config.getInstance().animationSpeedInMilliseconds;
            if (totalAnimationTime <= 0) totalAnimationTime = animationList.size() * 100; // Default to 100ms per frame

            // Play the animation
            AnimationHandler.animation(introAnimation, panel);

            // Timer to transition after animation ends
            Timer transitionTimer = new Timer(totalAnimationTime, evt -> {
                isTransitionPlaying = false;
                updateScene(newBackground, null, panel);
                showMainScreen(panel);
            });

            transitionTimer.setRepeats(false);
            transitionTimer.start();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid animation name: " + introAnimation);
            updateScene(newBackground, null, panel);
            if (imageTimer > 0) {
                Timer imageDisplayTimer = new Timer(imageTimer, evt -> showMainScreen(panel));
                imageDisplayTimer.setRepeats(false);
                imageDisplayTimer.start();
            } else {
                showMainScreen(panel);
            }
        }
    }

    public static void skipTransition(JLayeredPane panel) {
        System.out.println(isTransitionPlaying);
        if (isTransitionPlaying) {
            isTransitionPlaying = false;
            if (transitionTimer != null && transitionTimer.isRunning()) {
                transitionTimer.stop();
            }
            timerstopper();
            isTransitionPlaying = false;

            StoryHolder storypart = GamePlayer.getCurrentStoryHolder();


            if (storypart.getAnimation() != null) {
                updateScene(null, storypart.getAnimation(), panel);
            } else if (storypart.getBackground() != null) {
                updateScene(storypart.getBackground(), null, panel);
            }
        }
    }

    public static void updateScene(String newBackground, String newAnimation, JLayeredPane panel) {
        if (newBackground != null && !newBackground.equals(Game.getCurrentBackgroundImage())) {
            Game.setCurrentBackgroundImage(newBackground);
            panel.repaint();
            timerstopper();
        } else if (newAnimation != null) {
            animation(newAnimation, panel);
        }
    }

    public static void timerstopper() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public static void animationReset() {
        animationList.clear();
    }
    public static void setIsTransitionPlaying(boolean state){
        isTransitionPlaying = state;
    }
    private static void showMainScreen(JLayeredPane panel) {
        try {
            MainScreen.mainScreen(panel);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load main screen.");
        }
    }
}
