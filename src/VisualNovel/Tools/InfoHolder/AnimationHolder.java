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
    WORK1(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work1/Work1.png",
            "res/Pictures/Animations/Work1/Work2.png",
            "res/Pictures/Animations/Work1/Work3.png"
    ))),
    WORK2(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work2/Work2-1.png",
            "res/Pictures/Animations/Work2/Work2-2.png",
            "res/Pictures/Animations/Work2/Work2-3.png"
    ))),
    OUTSIDE1(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Outside1/Outside1.png",
            "res/Pictures/Animations/Outside1/Outside2.png",
            "res/Pictures/Animations/Outside1/Outside3.png"
    ))),
    TRANSITION1(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work-1ToWork-2/Transition1.png",
            "res/Pictures/Transitions/Work-1ToWork-2/Transition2.png",
            "res/Pictures/Transitions/Work-1ToWork-2/Transition3.png",
            "res/Pictures/Transitions/Work-1ToWork-2/Transition4.png",
            "res/Pictures/Transitions/Work-1ToWork-2/Transition5.png"
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
