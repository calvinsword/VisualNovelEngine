package VisualNovel.Screens;

import VisualNovel.Config;
import VisualNovel.Game;
import VisualNovel.Tools.AnimationHandler;
import VisualNovel.Tools.Buttons;
import VisualNovel.Tools.SoundHandler;
import VisualNovel.Tools.TextDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static VisualNovel.Tools.AnimationHandler.animation;
import static VisualNovel.Tools.SoundHandler.playBackgroundMusic;

public class MainScreen {
    JLayeredPane panel;
    static Buttons buttons = new Buttons();

    public MainScreen(JLayeredPane panel) {
        this.panel = panel;
    }

    public static void mainScreen(JLayeredPane panel) throws Exception {
        Config config = Config.getInstance();
        panel.removeAll(); // Clear previous content
        panel.setLayout(null); // Use absolute positioning for precise control
        if (config.mainMenuSound != null) {
            playBackgroundMusic(config.mainMenuSound);
        }
        if (config.mainMenuImage != null){
            Game.setCurrentBackgroundImage(config.mainMenuImage);
        } else if (config.mainMenuAnimation != null) {
            AnimationHandler.animation(config.mainMenuAnimation,panel);
        }
        else throw new Exception("CHECH THE OPTIONS CONFIG");
        if (config.mainMenuSound != null) {
            SoundHandler.playBackgroundMusic(config.mainMenuSound);
        }

        // Button Dimensions
        int buttonWidth = 200;
        int buttonHeight = 50;

        JButton beginButton = buttons.newBeginButton((panel.getWidth() - buttonWidth) / 2,(int) (panel.getHeight() * 0.85), buttonWidth, buttonHeight,panel);
        panel.add(beginButton);
        JButton settingsButton = buttons.newSettingsButton((int) (panel.getWidth() * 0.35) - buttonWidth / 2,(int) (panel.getHeight() * 0.85),buttonWidth,buttonHeight,panel);
        panel.add(settingsButton);
        JButton exitButton = buttons.newExitButton((int) (panel.getWidth() * 0.65) - buttonWidth / 2, (int) (panel.getHeight() * 0.85), buttonWidth, buttonHeight,panel);
        panel.add(exitButton);

        // Ensure resizing maintains alignment
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = panel.getWidth();
                int panelHeight = panel.getHeight();

                // Set new bounds for each button when the window is resized
                beginButton.setBounds(
                        (panelWidth - buttonWidth) / 2,
                        (int) (panelHeight * 0.85),
                        buttonWidth,
                        buttonHeight
                );

                settingsButton.setBounds(
                        (int) (panelWidth * 0.35) - buttonWidth / 2,
                        (int) (panelHeight * 0.85),
                        buttonWidth,
                        buttonHeight
                );

                exitButton.setBounds(
                        (int) (panelWidth * 0.65) - buttonWidth / 2,
                        (int) (panelHeight * 0.85),
                        buttonWidth,
                        buttonHeight
                );
            }
        });

        panel.revalidate();
        panel.repaint();
    }
}
