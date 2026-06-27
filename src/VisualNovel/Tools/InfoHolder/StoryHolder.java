package VisualNovel.Tools.InfoHolder;

public class StoryHolder {
    private final String text;
    private final String background;
    private final String animation;
    private final String transitionAnimation;
    private final String music;

    private final String storyOption;
    private final String storyOptionJsonLink;
    private final String storyOptionImage;
    private final String storyOptionImagePressed;
    private final String storyOption1;
    private final String storyOptionJsonLink1;
    private final String storyOptionImage1;
    private final String storyOptionImagePressed1;
    private final String storyOption2;
    private final String storyOptionJsonLink2;
    private final String storyOptionImage2;
    private final String storyOptionImagePressed2;

    private final String diffLetterSound;
    private final boolean autoSkip;

    public StoryHolder(String text, String background, String animation,String transitionAnimation, String music, String storyOption, String storyOptionJsonLink, String storyOptionImage, String storyOptionImagePressed, String storyOption1, String storyOptionJsonLink1, String storyOptionImage1, String storyOptionImagePressed1, String storyOption2, String storyOptionJsonLink2, String storyOptionImage2, String storyOptionImagePressed2, String difflettersound,boolean autoSkip) {
        this.text = text;
        this.background = background;
        this.animation = animation;
        this.transitionAnimation = transitionAnimation;
        this.music = music;
        this.storyOption = storyOption;
        this.storyOptionJsonLink = storyOptionJsonLink;
        this.storyOptionImage = storyOptionImage;
        this.storyOptionImagePressed = storyOptionImagePressed;
        this.storyOption1 = storyOption1;
        this.storyOptionJsonLink1 = storyOptionJsonLink1;
        this.storyOptionImage1 = storyOptionImage1;
        this.storyOptionImagePressed1 = storyOptionImagePressed1;
        this.storyOption2 = storyOption2;
        this.storyOptionJsonLink2 = storyOptionJsonLink2;
        this.storyOptionImage2 = storyOptionImage2;
        this.storyOptionImagePressed2 = storyOptionImagePressed2;
        this.diffLetterSound = difflettersound;
        this.autoSkip = autoSkip;
    }

    public String getText() {
        return text;
    }
    public String getBackground() {
        return background;
    }
    public String getAnimation() {
        return animation;
    }
    public String getTransitionAnimation(){return transitionAnimation;}
    public String getMusic(){
        return music;
    }
    public String getStoryOption() {
        return storyOption;
    }
    public String getStoryOptionJsonLink() {
        return storyOptionJsonLink;
    }
    public String getStoryOption1() {
        return storyOption1;
    }
    public String getStoryOptionJsonLink1() {
        return storyOptionJsonLink1;
    }
    public String getStoryOption2() {
        return storyOption2;
    }
    public String getStoryOptionJsonLink2() {
        return storyOptionJsonLink2;
    }
    public String getStoryOptionImage() {return storyOptionImage;}
    public String getStoryOptionImagePressed() {return storyOptionImagePressed;}
    public String getStoryOptionImage1() {return storyOptionImage1;}
    public String getStoryOptionImagePressed1() {return storyOptionImagePressed1;}
    public String getStoryOptionImage2() {return storyOptionImage2;}
    public String getStoryOptionImagePressed2() {return storyOptionImagePressed2;}
    public String getDiffLetterSound(){return diffLetterSound;}
    public boolean getAutoSkip(){return autoSkip;}
}
