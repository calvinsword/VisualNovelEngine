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
    WORK3(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work3/Work3-1.png",
            "res/Pictures/Animations/Work3/Work3-2.png",
            "res/Pictures/Animations/Work3/Work3-3.png"
    ))),
    WORK4(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work4/Work4-1.png",
            "res/Pictures/Animations/Work4/Work4-2.png",
            "res/Pictures/Animations/Work4/Work4-3.png"
    ))),
    WORK5(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work5/Work5-1.png",
            "res/Pictures/Animations/Work5/Work5-2.png",
            "res/Pictures/Animations/Work5/Work5-3.png"
    ))),
    WORK6(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work6/Work6-1.png",
            "res/Pictures/Animations/Work6/Work6-2.png",
            "res/Pictures/Animations/Work6/Work6-3.png"
    ))),
    WORK7(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Work7/Work7-1.png",
            "res/Pictures/Animations/Work7/Work7-2.png",
            "res/Pictures/Animations/Work7/Work7-3.png"
    ))),
    OUTSIDE1(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Outside1/Outside1.png",
            "res/Pictures/Animations/Outside1/Outside2.png",
            "res/Pictures/Animations/Outside1/Outside3.png"
    ))),
    OUTSIDE2(new ArrayList<>(Arrays.asList(
            "res/Pictures/Animations/Outside2/Outside2-1.png",
            "res/Pictures/Animations/Outside2/Outside2-2.png",
            "res/Pictures/Animations/Outside2/Outside2-3.png"
    ))),
    TRANSITION1(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work1ToWork2/Transition1.png",
            "res/Pictures/Transitions/Work1ToWork2/Transition2.png",
            "res/Pictures/Transitions/Work1ToWork2/Transition3.png",
            "res/Pictures/Transitions/Work1ToWork2/Transition4.png",
            "res/Pictures/Transitions/Work1ToWork2/Transition5.png"
    ))),
    TRANSITION2(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work2ToWork3/Transition2-1.png",
            "res/Pictures/Transitions/Work2ToWork3/Transition2-2.png",
            "res/Pictures/Transitions/Work2ToWork3/Transition2-3.png",
            "res/Pictures/Transitions/Work2ToWork3/Transition2-4.png",
            "res/Pictures/Transitions/Work2ToWork3/Transition2-5.png"
    ))),
    TRANSITION3(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work3ToWork2/Transition3-1.png",
            "res/Pictures/Transitions/Work3ToWork2/Transition3-2.png",
            "res/Pictures/Transitions/Work3ToWork2/Transition3-3.png",
            "res/Pictures/Transitions/Work3ToWork2/Transition3-4.png",
            "res/Pictures/Transitions/Work3ToWork2/Transition3-5.png"
    ))),
    TRANSITION4(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work2ToWork4/Transition4-1.png",
            "res/Pictures/Transitions/Work2ToWork4/Transition4-2.png",
            "res/Pictures/Transitions/Work2ToWork4/Transition4-3.png",
            "res/Pictures/Transitions/Work2ToWork4/Transition4-4.png",
            "res/Pictures/Transitions/Work2ToWork4/Transition4-5.png"
    ))),
    TRANSITION5(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work4ToWork5/Transition5-1.png",
            "res/Pictures/Transitions/Work4ToWork5/Transition5-2.png",
            "res/Pictures/Transitions/Work4ToWork5/Transition5-3.png",
            "res/Pictures/Transitions/Work4ToWork5/Transition5-4.png",
            "res/Pictures/Transitions/Work4ToWork5/Transition5-5.png"
    ))),
    TRANSITION6(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work5ToWork6/Transition6-1.png",
            "res/Pictures/Transitions/Work5ToWork6/Transition6-2.png",
            "res/Pictures/Transitions/Work5ToWork6/Transition6-3.png",
            "res/Pictures/Transitions/Work5ToWork6/Transition6-4.png",
            "res/Pictures/Transitions/Work5ToWork6/Transition6-5.png"
    ))),
    TRANSITION7(new ArrayList<>(Arrays.asList(
            "res/Pictures/Transitions/Work6ToWork7/transition7-1.png",
            "res/Pictures/Transitions/Work6ToWork7/transition7-2.png",
            "res/Pictures/Transitions/Work6ToWork7/transition7-3.png",
            "res/Pictures/Transitions/Work6ToWork7/transition7-4.png",
            "res/Pictures/Transitions/Work6ToWork7/transition7-5.png"
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
