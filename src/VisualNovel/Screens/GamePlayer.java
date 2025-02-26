package VisualNovel.Screens;

import VisualNovel.Config;
import VisualNovel.Tools.AnimationHandler;
import VisualNovel.Game;
import VisualNovel.Tools.Buttons;
import VisualNovel.Tools.InfoHolder.StoryHolder;
import VisualNovel.Tools.SoundHandler;
import VisualNovel.Tools.TextDisplay;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static VisualNovel.Tools.AnimationHandler.*;

public class GamePlayer {
    JLayeredPane panel;
    private static MouseListener textMouseListener;
    static Buttons buttons = new Buttons();
    private static String JsonLink;
    static boolean firstTime = true;
    static int[] currentTextIndex = {0};
    public static List<StoryHolder> storyEntries = new ArrayList<>();


    public GamePlayer(JLayeredPane panel) {
        this.panel = panel;
    }

    public static void showGameContent(JLayeredPane panel) {
        panel.removeAll();
        Config config = Config.getInstance();
        if(firstTime) {
            JsonLink = config.storyJsonFileLink;
            firstTime = false;
        }


        // Load story texts from JSON
        storyEntries = loadStoryTexts(JsonLink);

        // Apply the background of the first story entry if it exists
        String initialBackground = storyEntries.get(currentTextIndex[0]).getBackground();
        String currentTransitionAnimation = storyEntries.get(currentTextIndex[0]).getTransitionAnimation();
        String currentanimation = storyEntries.get(currentTextIndex[0]).getAnimation();
        String currentMusic = storyEntries.get(currentTextIndex[0]).getMusic();
        if ((initialBackground != null && !initialBackground.equals(Game.getCurrentBackgroundImage())) || currentanimation != null) {
            if (currentMusic != null){
                SoundHandler.playBackgroundMusic(currentMusic);
            }
            timerstopper();
            Game.setCurrentBackgroundImage(initialBackground);
            if (currentTransitionAnimation != null) {
                AnimationHandler.playTransitionAnimation(currentTransitionAnimation, null, currentanimation, panel);
            }
            else {
                updateScene(initialBackground, currentanimation, panel);
            }
            panel.repaint();
        }


        JPanel textBoxPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (config.textBoxImage == null){
                    try {
                        throw new Exception("CHECH THE TEXTBOX CONFIG");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                super.paintComponent(g);
                ImageIcon textBoxBackground = new ImageIcon(config.textBoxImage);
                g.drawImage(textBoxBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        textBoxPanel.setLayout(null);

        int margin = (int) (panel.getWidth() * 0.1);
        int textBoxWidth = panel.getWidth() - (2 * margin);
        int textBoxHeight = 150;
        int textBoxY = panel.getHeight() - textBoxHeight - 20;

        textBoxPanel.setBounds(margin, textBoxY, textBoxWidth, textBoxHeight);

        TextDisplay[] textDisplay = {new TextDisplay(storyEntries.get(currentTextIndex[0]).getText(), Game.getTextSpeed(),storyEntries.get(currentTextIndex[0]).getDiffLetterSound())};
        textDisplay[0].setBounds(10, 10, textBoxWidth - 20, textBoxHeight - 20);
        textBoxPanel.add(textDisplay[0]);

        // Shared action to advance or skip text
        Runnable advanceText = () -> {
             {
                 if(config.ClickableTransistionAnimations) {
                     if (isTransitionPlaying) {
                         AnimationHandler.skipTransition(panel);
                         isTransitionPlaying = false;
                     }
                 }

                if (!textDisplay[0].isAnimationComplete()) {
                    textDisplay[0].completeAnimation();

                } else if (currentTextIndex[0] < storyEntries.size() - 1) {
                    textBoxPanel.setEnabled(false);

                    currentTextIndex[0]++;
                    textBoxPanel.remove(textDisplay[0]);

                    // Check if the background needs to be updated
                    String newBackground = storyEntries.get(currentTextIndex[0]).getBackground();
                    String newAnimation = storyEntries.get(currentTextIndex[0]).getAnimation();
                    String newTransitionAnimation = storyEntries.get(currentTextIndex[0]).getTransitionAnimation();
                    String newMusic = storyEntries.get(currentTextIndex[0]).getMusic();

                    //Changes in game
                    String storyOption = storyEntries.get(currentTextIndex[0]).getStoryOption();
                    String storyOptionJsonLink = storyEntries.get(currentTextIndex[0]).getStoryOptionJsonLink();
                    String storyOptionImage = storyEntries.get(currentTextIndex[0]).getStoryOptionImage();
                    String storyOptionPressedImage = storyEntries.get(currentTextIndex[0]).getStoryOptionImagePressed();
                    String storyOption1 = storyEntries.get(currentTextIndex[0]).getStoryOption1();
                    String storyOption1JsonLink = storyEntries.get(currentTextIndex[0]).getStoryOptionJsonLink1();
                    String storyOption1Image = storyEntries.get(currentTextIndex[0]).getStoryOptionImage1();
                    String storyOption1PressedImage = storyEntries.get(currentTextIndex[0]).getStoryOptionImagePressed1();
                    String storyOption2 = storyEntries.get(currentTextIndex[0]).getStoryOption2();
                    String storyOption2JsonLink = storyEntries.get(currentTextIndex[0]).getStoryOptionJsonLink2();
                    String storyOption2Image = storyEntries.get(currentTextIndex[0]).getStoryOptionImage2();
                    String storyOption2PressedImage = storyEntries.get(currentTextIndex[0]).getStoryOptionImagePressed2();

                    int numberOfChoices = 0;
                    if (storyOption != null) numberOfChoices++;
                    if (storyOption1 != null) numberOfChoices++;
                    if (storyOption2 != null) numberOfChoices++;
                    int choiceRank = 0;

                    if (storyOption != null) {
                        panel.add(buttons.newStoryOptionButton(storyOption, storyOptionJsonLink, numberOfChoices, choiceRank, storyOptionImage, storyOptionPressedImage, panel));
                        choiceRank++;
                    }
                    if (storyOption1 != null) {
                        panel.add(buttons.newStoryOptionButton(storyOption1, storyOption1JsonLink, numberOfChoices, choiceRank, storyOption1Image, storyOption1PressedImage, panel));
                        choiceRank++;
                    }
                    if (storyOption2 != null) {
                        panel.add(buttons.newStoryOptionButton(storyOption2, storyOption2JsonLink, numberOfChoices, choiceRank, storyOption2Image, storyOption2PressedImage, panel));
                    }
                    panel.revalidate();
                    panel.repaint();

                    if (newMusic != null) {
                        SoundHandler.playBackgroundMusic(newMusic);
                    }
                    if (newTransitionAnimation != null) {
                        AnimationHandler.playTransitionAnimation(newTransitionAnimation, newBackground, newAnimation, panel);
                    } else {
                        // If no transition animation, update immediately
                        updateScene(newBackground, newAnimation, panel);
                    }

                    textDisplay[0] = new TextDisplay(storyEntries.get(currentTextIndex[0]).getText(), Game.getTextSpeed(),storyEntries.get(currentTextIndex[0]).getDiffLetterSound());
                    textDisplay[0].setBounds(10, 10, textBoxWidth - 20, textBoxHeight - 20);
                    textBoxPanel.add(textDisplay[0]);

                    textBoxPanel.revalidate();
                    textBoxPanel.repaint();
                }
            }
        };

        textMouseListener = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                    advanceText.run();
            }
        };

        textBoxPanel.addMouseListener(textMouseListener);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                    advanceText.run();
            }
        });

        textBoxPanel.setFocusable(true);
        textBoxPanel.requestFocusInWindow();

        JButton backButton = buttons.newGameBackButton(10,textBoxHeight - 40,80,30,panel,textDisplay);

        textBoxPanel.add(backButton);

        panel.setLayout(null);
        panel.add(textBoxPanel);
        panel.revalidate();
        panel.repaint();
        textBoxPanel.requestFocusInWindow();
    }

    private static List<StoryHolder> loadStoryTexts(String filePath) {
        try {
            storyEntries.clear();

            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray storyArray = jsonObject.getJSONArray("story");

            for (int i = 0; i < storyArray.length(); i++) {
                JSONObject entry = storyArray.getJSONObject(i);
                String text = entry.getString("text");
                String background = entry.optString("background", null);
                String animation = entry.optString("animation",null);
                String transitionAnimation = entry.optString("transitionAnimation", null);
                String music = entry.optString("music", null);

                String storyOption = entry.optString("story option",null);
                String storyOptionLink = entry.optString("story option link",null);
                String storyOptionImage = entry.optString("story option image",null);
                String storyOptionPressedImage = entry.optString("story option pressed image",null);
                String storyOption1 = entry.optString("story option1",null);
                String storyOptionLink1 = entry.optString("story option link1",null);
                String storyOptionImage1 = entry.optString("story option image1",null);
                String storyOptionPressedImage1 = entry.optString("story option pressed image1",null);
                String storyOption2 = entry.optString("story option2",null);
                String storyOptionLink2 = entry.optString("story option link2",null);
                String storyOptionImage2 = entry.optString("story option image2",null);
                String storyOptionPressedImage2 = entry.optString("story option pressed image2",null);

                String diffLetterSound = entry.optString("letterSound",null);

                storyEntries.add(new StoryHolder(text, background,animation,transitionAnimation, music,storyOption,storyOptionLink,storyOptionImage,storyOptionPressedImage,storyOption1,storyOptionLink1,storyOptionImage1,storyOptionPressedImage1,storyOption2,storyOptionLink2,storyOptionImage2,storyOptionPressedImage2,diffLetterSound));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storyEntries;
    }

    public static void setJsonLink(String jsonLink) {
        JsonLink = jsonLink;
    }
    public static String getJsonLink() {
        return JsonLink;
    }
    public static void reloadStory(JLayeredPane panel) {
        if (JsonLink == null || JsonLink.isEmpty()) {
            System.err.println("Error: JsonLink is not set.");
            return;
        }

        // Remove all mouse listeners to prevent duplicates
        for (MouseListener ml : panel.getMouseListeners()) {
            panel.removeMouseListener(ml);
        }

        // Preserve the current text index before clearing
        storyEntries.clear();

        // Fully reset the story list to avoid leftover entries
        storyEntries = new ArrayList<>(); // Replace the list instead of clearing

        // Load the new story content
        storyEntries = loadStoryTexts(JsonLink);

        if (storyEntries.isEmpty()) {
            System.err.println("Error: No story entries found in the new JSON file.");
            return;
        }

        currentTextIndex[0] = 0;

        // Refresh UI
        panel.removeAll();
        showGameContent(panel);

        panel.revalidate();
        panel.repaint();
    }



    public static void setCurrentTextIndex(int[] currenttextindex) {
        currentTextIndex = currenttextindex;
    }

    public static StoryHolder getCurrentStoryHolder(){
        Config config = Config.getInstance();
        List<StoryHolder> allStorys = loadStoryTexts(config.storyJsonFileLink);
        return allStorys.get(currentTextIndex[0]);
    }
}
