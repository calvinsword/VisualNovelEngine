package VisualNovel.Tools;

import VisualNovel.Config;
import VisualNovel.Game;

import javax.swing.*;
import java.awt.*;

public class Sliders {
    public JSlider newTextSpeedSlider() throws Exception {
        // Text Speed Slider with reversed behavior
        JSlider textSpeedSlider = createCustomSlider(10, 100, 100 - Game.getTextSpeed());
        textSpeedSlider.addChangeListener(e -> {
            Game.setTextSpeed(100 - textSpeedSlider.getValue());
        });
        return textSpeedSlider;
    }
    public JSlider newMainVolumeSlider() throws Exception {
        JSlider volumeSlider = createCustomSlider(0, 100, Game.getMasterVolume());

        volumeSlider.addChangeListener(e -> {
            int newMasterVolume = volumeSlider.getValue();
            Game.setMasterVolume(newMasterVolume);

            // Force immediate volume update
            SoundHandler.setBackgroundMusicVolume(newMasterVolume);
        });

        return volumeSlider;
    }



    private static JSlider createCustomSlider(int min, int max, int value) throws Exception {
        Config config = Config.getInstance();
        JSlider slider = new JSlider(min, max, value);
        slider.setOpaque(false);
        if (config.sliderBackground == null || config.sliderbar == null){
            throw new Exception("CHECH THE OPTIONS CONFIG (slidebar/sliderbackground)");
        }

        // Custom slider UI
        slider.setUI(new javax.swing.plaf.basic.BasicSliderUI(slider) {
            private final ImageIcon thumbIcon = new ImageIcon(config.sliderbar);
            private final ImageIcon trackIcon = new ImageIcon(config.sliderBackground);

            @Override
            public void paintThumb(Graphics g) {
                int x = thumbRect.x + (thumbRect.width - thumbIcon.getIconWidth()) / 2;
                int y = thumbRect.y + (thumbRect.height - thumbIcon.getIconHeight()) / 2;
                g.drawImage(thumbIcon.getImage(), x, y, null);
            }

            @Override
            public void paintTrack(Graphics g) {
                int trackWidth = trackRect.width;
                int trackHeight = trackRect.height;
                g.drawImage(trackIcon.getImage(), trackRect.x, trackRect.y, trackWidth, trackHeight, null);
            }
            @Override
            protected Dimension getThumbSize() {
                return new Dimension(thumbIcon.getIconWidth(), thumbIcon.getIconHeight());
            }

            @Override
            public void paintFocus(Graphics g) {
                // Do nothing to disable the default focus border
            }
        });

        return slider;
    }
}
