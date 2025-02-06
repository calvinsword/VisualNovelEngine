package VisualNovel.Screens;

import VisualNovel.Config;
import VisualNovel.Tools.AnimationHandler;

import javax.swing.*;

public class IntroScreen {
    JLayeredPane panel;
    public IntroScreen(JLayeredPane panel){this.panel = panel;}

    public static void playIntro(JLayeredPane panel){
        Config config = Config.getInstance();
        panel.removeAll();
        AnimationHandler.playIntro(config.introAnimation,config.introImage,config.introImageDurationInMilliseconds,config.introSound,panel);
    }
}
