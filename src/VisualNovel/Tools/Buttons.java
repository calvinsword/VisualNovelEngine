package VisualNovel.Tools;

import VisualNovel.Config;
import VisualNovel.Screens.GamePlayer;
import VisualNovel.Screens.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static VisualNovel.Screens.GamePlayer.setCurrentTextIndex;
import static VisualNovel.Screens.MainScreen.mainScreen;

public class Buttons {
    //ONLY FOR OPTIONS AND GAMEITSELF (IT MAKES THE TEXTINDEX GO TO 0)
    public JButton newGameBackButton(int x, int y, int buttonWidth, int buttonHeight, JLayeredPane panel, TextDisplay[] textDisplay) {
        Config config = Config.getInstance();
        ImageIcon backButtonImage = new ImageIcon(new ImageIcon(config.backButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));
        ImageIcon backPressedButtonImage = new ImageIcon(new ImageIcon(config.backPressedButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));


        // Back Button
        JButton backButton = new JButton();
        if (config.optionsButton == null) {
            backButton.setText("Back");
        }
        backButton.setFont(new Font("Serif", Font.PLAIN, (int) (buttonHeight * 0.4)));
        backButton.setBounds(x, y, buttonWidth, buttonHeight);

        // Change image on press/release
        if (config.backPressedButton != null) {
            backButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    backButton.setIcon(backPressedButtonImage);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    backButton.setIcon(backButtonImage);
                }
            });
        }
        backButton.addActionListener(e -> {
            try {
                for (MouseListener ml : panel.getMouseListeners()) {
                    panel.removeMouseListener(ml);
                }
                textDisplay[0].stopAnimation();
                AnimationHandler.timerstopper();
                AnimationHandler.isTransitionPlaying = false;
                SoundHandler.playSoundEffect(config.buttonSound);
                mainScreen(panel);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        backButton.setIcon(backButtonImage);

        return backButton;
    }
    public JButton newBackButton(int x, int y, int buttonWidth, int buttonHeight, JLayeredPane panel) {
        Config config = Config.getInstance();
        ImageIcon backButtonImage = new ImageIcon(new ImageIcon(config.backButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));
        ImageIcon backPressedButtonImage = new ImageIcon(new ImageIcon(config.backPressedButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));


        // Back Button
        JButton backButton = new JButton();
        if (config.optionsButton == null) {
            backButton.setText("Back");
        }
        backButton.setFont(new Font("Serif", Font.PLAIN, (int) (buttonHeight * 0.4)));
        backButton.setBounds(x, y, buttonWidth, buttonHeight);

        // Change image on press/release
        if (config.backPressedButton != null) {
            backButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    backButton.setIcon(backPressedButtonImage);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    backButton.setIcon(backButtonImage);
                }
            });
        }
        backButton.addActionListener(e -> {
            try {
                for (MouseListener ml : panel.getMouseListeners()) {
                    panel.removeMouseListener(ml);
                }
                AnimationHandler.timerstopper();
                AnimationHandler.isTransitionPlaying = false;
                SoundHandler.playSoundEffect(config.buttonSound);
                mainScreen(panel);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        backButton.setIcon(backButtonImage);

        return backButton;
    }

    public JButton newSettingsButton(int x, int y, int buttonWidth, int buttonHeight, JLayeredPane panel){
        Config config = Config.getInstance();
        ImageIcon optionsButtonImage = new ImageIcon(new ImageIcon(config.optionsButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));
        ImageIcon optionsPressedButtonImage = new ImageIcon(new ImageIcon(config.optionsPressedButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));

        JButton settingsButton = new JButton();
        if (config.optionsButton == null){
            settingsButton.setText("Settings");
        }
        settingsButton.setFont(new Font("Serif", Font.PLAIN, (int) (buttonHeight * 0.4)));
        settingsButton.setBounds(x, y, buttonWidth, buttonHeight
        );
        if(config.optionsPressedButton != null) {
            settingsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    settingsButton.setIcon(optionsPressedButtonImage);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    settingsButton.setIcon(optionsButtonImage);
                }
            });

        }
        settingsButton.addActionListener(e -> {
            try {
                SoundHandler.playSoundEffect(config.buttonSound);
                Settings.showSettings(panel);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        settingsButton.setIcon(optionsButtonImage);
        return settingsButton;
    }
    public JButton newBeginButton(int x, int y, int buttonWidth, int buttonHeight, JLayeredPane panel){
        Config config = Config.getInstance();
        ImageIcon beginButtonImage = new ImageIcon(new ImageIcon(config.beginButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));
        ImageIcon beginPressedButtonImage = new ImageIcon(new ImageIcon(config.beginPressedButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));

        // Begin Button - Centered and aligned
        JButton beginButton = new JButton();
        if (config.beginButton == null) {
            beginButton.setText("Begin");
        }
        beginButton.setFont(new Font("Serif", Font.PLAIN, (int) (buttonHeight * 0.4)));
        beginButton.setBounds(x, y, buttonWidth, buttonHeight
        );
        if(config.beginPressedButton != null) {
            beginButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    beginButton.setIcon(beginPressedButtonImage);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    beginButton.setIcon(beginButtonImage);
                }
            });
        }
        beginButton.addActionListener(e -> {
            GamePlayer.setJsonLink(config.storyJsonFileLink);
            SoundHandler.playSoundEffect(config.buttonSound);
            GamePlayer.setCurrentTextIndex(new int[]{0}); // Start from the first text
            GamePlayer.storyEntries = new ArrayList<>(); // Clear old story entries
            GamePlayer.reloadStory(panel); // Reload from config file

        });
        beginButton.setIcon(beginButtonImage);
        return beginButton;
    }
    public JButton newExitButton(int x, int y, int buttonWidth, int buttonHeight, JLayeredPane panel){
        Config config = Config.getInstance();
        ImageIcon exitButtonImage = new ImageIcon(new ImageIcon(config.exitButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));
        ImageIcon exitPressedButtonImage = new ImageIcon(new ImageIcon(config.exitPressedButton).getImage().getScaledInstance(buttonWidth,buttonHeight,Image.SCALE_SMOOTH));

        // Exit Button - Right of Begin
        JButton exitButton = new JButton();
        if (config.exitButton == null){
            exitButton.setText("Exit");
        }
        exitButton.setFont(new Font("Serif", Font.PLAIN, (int) (buttonHeight * 0.4)));
        exitButton.setBounds(x, y, buttonWidth, buttonHeight
        );
        if(config.exitPressedButton != null) {
            exitButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    exitButton.setIcon(exitPressedButtonImage);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    exitButton.setIcon(exitButtonImage);
                }
            });
        }
        exitButton.setIcon(exitButtonImage);
        exitButton.addActionListener(e -> System.exit(0));
        return exitButton;
    }
    public JButton newStoryOptionButton(String buttonText,String newJsonLink, int amountOfChoices, int choicesRank, String imageUrl, String imagePressedUrl, JLayeredPane panel) {
        Config config = Config.getInstance();
        SoundHandler.playSoundEffect(config.storyButtonSound);
        int screenHeight = panel.getHeight();
        int screenWidth = panel.getWidth();
        int buttonWidth = 200;
        int buttonHeight = 40;
        int verticalSpacing = 10;

        int totalButtonHeight = (buttonHeight + verticalSpacing) * amountOfChoices - verticalSpacing;
        int startY = (screenHeight - totalButtonHeight) / 2;
        int buttonY = startY + (choicesRank * (buttonHeight + verticalSpacing));
        int buttonX = (screenWidth - buttonWidth) / 2;

        ImageIcon storyOptionButtonImage = new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
        ImageIcon storyOptionPressedButtonImage = new ImageIcon(new ImageIcon(imagePressedUrl).getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
        JButton storyOptionButton = new JButton();
        if (imageUrl == null) {
            storyOptionButton.setText(buttonText);
        }
        storyOptionButton.setFont(new Font("Serif", Font.PLAIN, (int) (buttonHeight * 0.4)));
        storyOptionButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        if(imageUrl != null) {
            storyOptionButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    storyOptionButton.setIcon(storyOptionPressedButtonImage);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    storyOptionButton.setIcon(storyOptionButtonImage);
                }
            });
        }
        storyOptionButton.setIcon(storyOptionButtonImage);

        storyOptionButton.addActionListener(e -> {
            if (config.storyButtonPressedSound != null) {
                SoundHandler.playSoundEffect(config.storyButtonPressedSound);
            }
            GamePlayer.setJsonLink(newJsonLink);
            GamePlayer.reloadStory(panel);
        });

        return storyOptionButton;
    }

}
