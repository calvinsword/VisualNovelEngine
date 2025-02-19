package VisualNovel;

public class Config {
    private static final Config instance = new Config();

    /////////
    //INTRO//
    /////////
    public String introImage = null;
    public int introImageDurationInMilliseconds = 0;
    public String introAnimation = null;
    public String introSound = null;

    /////////////
    //MAIN MENU//
    /////////////
    public String mainMenuImage = null;
    public String mainMenuAnimation = "MAIN_MENU";


    ///////////
    //OPTIONS//
    ///////////
    public String sliderBackground = "res/Pictures/TextBox.png";
    public String sliderbar = "res/Pictures/Pictures/img.png";

    public String optionsImage = "res/Pictures/Pictures/MC.png";
    public String optionsAnimation = null;

    ////////
    //TEXT//
    ////////
    public String textBoxImage = "res/Pictures/textbox.png";
    public String letterSound = "res/Sounds/TextSoundEffect.wav";

    /////////
    //STORY//
    /////////
    public String storyJsonFileLink = "res/Story.json";

    ///////////
    //BUTTONS//
    ///////////
    public String buttonSound = "res/Sounds/TextSoundEffect.wav";
    public String backButton = "res/Pictures/Buttons/BackNotPressed.png";
    public String backPressedButton = "res/Pictures/Buttons/BackPressed.png";
    public String optionsButton = "res/Pictures/Buttons/OptionsNotPressed.png";
    public String optionsPressedButton = "res/Pictures/Buttons/OptionsPressed.png";
    public String exitButton = "res/Pictures/Buttons/ExitNotPressed.png";
    public String exitPressedButton = "res/Pictures/Buttons/ExitPressed.png";
    public String beginButton = "res/Pictures/Buttons/BeginNotPressed.png";
    public String beginPressedButton = "res/Pictures/Buttons/BeginPressed.png";

    /////////
    //SOUND//
    /////////
    public String mainMenuSound = null;
    public String settingsMenuSound = null;

    /////////
    //EXTRA//
    /////////
    public boolean ClickableTransistionAnimations = false;
    public String title = "Asocial";
    public int animationSpeedInMilliseconds = 800;
    public int transitionAnimationSpeedInMilliseconds = 80;

    // Private constructor to prevent instantiation
    private Config() {}

    public static Config getInstance() {
        return instance;
    }
}