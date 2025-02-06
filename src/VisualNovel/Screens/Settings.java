package VisualNovel.Screens;

import VisualNovel.Config;
import VisualNovel.Game;
import VisualNovel.Tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static VisualNovel.Screens.MainScreen.mainScreen;
import static VisualNovel.Tools.AnimationHandler.timerstopper;

public class Settings {
    JLayeredPane panel;
    static Buttons buttons = new Buttons();
    static Sliders sliders = new Sliders();


    public Settings(JLayeredPane panel) {
        this.panel = panel;
    }

    public static void showSettings(JLayeredPane panel) throws Exception {

        int buttonWidth = 100;
        int buttonHeight = 40;

        Config config = Config.getInstance();
        timerstopper();
        panel.removeAll();

        if (config.optionsImage != null){
            Game.setCurrentBackgroundImage(config.optionsImage);
        } else if (config.optionsAnimation != null) {
            AnimationHandler.animation(config.optionsAnimation,panel);
        }
        else throw new Exception("CHECH THE OPTIONS CONFIG");
        if (config.settingsMenuSound != null) {
            SoundHandler.playBackgroundMusic(config.settingsMenuSound);
        }

        // Middle-Left Panel for Sliders
        JPanel slidersPanel = new JPanel();
        slidersPanel.setOpaque(false);
        slidersPanel.setLayout(new GridLayout(4, 1, 10, 10));
        slidersPanel.setBounds(50, panel.getHeight() / 2 - 100, 300, 200);

        // Text Speed Label
        JLabel textSpeedLabel = new JLabel("Text Speed:");
        textSpeedLabel.setForeground(Color.WHITE);
        textSpeedLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        slidersPanel.add(textSpeedLabel);

        JSlider textSpeedSlider = sliders.newTextSpeedSlider();
        slidersPanel.add(textSpeedSlider);

        // Volume Label
        JLabel volumeLabel = new JLabel("Master Volume:");
        volumeLabel.setForeground(Color.WHITE);
        volumeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        slidersPanel.add(volumeLabel);

        JSlider volumeSlider = sliders.newMainVolumeSlider();
        slidersPanel.add(volumeSlider);

        panel.add(slidersPanel);

        JButton backButton = buttons.newBackButton(10,panel.getHeight() - 60,buttonWidth,buttonHeight,panel);

        panel.add(backButton);
        panel.revalidate();
        panel.repaint();
    }
}
