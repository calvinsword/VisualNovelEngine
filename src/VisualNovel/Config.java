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
    public String backButton = null;
    public String backPressedButton = null;
    public String optionsButton = null;
    public String optionsPressedButton = null;
    public String exitButton = null;
    public String exitPressedButton = null;
    public String beginButton = null;
    public String beginPressedButton = null;

    /////////
    //SOUND//
    /////////
    public String mainMenuSound = null;
    public String settingsMenuSound = null;

    /////////
    //EXTRA//
    /////////
    public String title = "Visual Novel";
    public int animationSpeedInMilliseconds = 800;

    // Private constructor to prevent instantiation
    private Config() {}

    public static Config getInstance() {
        return instance;
    }
}