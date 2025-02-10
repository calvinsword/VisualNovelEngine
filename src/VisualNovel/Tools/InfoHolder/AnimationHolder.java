package VisualNovel.Tools.InfoHolder;

import java.util.ArrayList;
import java.util.Arrays;

public enum AnimationHolder {
    // Enum constants with their associated frame paths
    //TODO: DONT MAKE THIS HARDCODED
    MAIN_MENU(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/MainScreen/IntroScreen1.png",
            "res/Pictures/Animations/MainScreen/IntroScreen2.png",
            "res/Pictures/Animations/MainScreen/IntroScreen3.png"
    ))),
    INTRO1(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Intro1/img.png",
            "res/Pictures/Animations/Intro1/img_1.png",
            "res/Pictures/Animations/Intro1/img_2.png"
    )));



    // Instance variable to hold the list of frame paths
    private final ArrayList<String> framePaths;

    // Constructor to initialize the framePaths
    AnimationHolder(ArrayList<String> framePaths) {
        this.framePaths = framePaths;
    }

    // Getter method to access the framePaths
    public ArrayList<String> getFramePaths() {
        return framePaths;
    }
}
