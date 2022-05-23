package redcoder.tank;

import redcoder.utils.ScheduledUtils;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TankPanel extends JPanel {

    private final JLabel progressLabel;

    public TankPanel(JLabel progressLabel) {
        this.progressLabel = progressLabel;
        ScheduledUtils.scheduleAtFixedRate(this::repaint, 50, 50, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TGC.getTGC().paint(g, progressLabel);
    }
}
