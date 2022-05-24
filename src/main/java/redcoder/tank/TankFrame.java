package redcoder.tank;

import redcoder.tank.action.LoadAction;
import redcoder.tank.action.SaveAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TankFrame extends JFrame {

    private JLabel progressLabel;
    private TankPanel tankPanel;

    public TankFrame() {
        super("坦克大战");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(906, 678);
        setLocationRelativeTo(null);
    }

    public void startup() {
        progressLabel = new JLabel("loading...");
        progressLabel.setOpaque(true);
        progressLabel.setBackground(Color.GRAY);
        progressLabel.setForeground(Color.GREEN);
        progressLabel.setFont(new Font(null, Font.BOLD, 20));

        tankPanel = new TankPanel(this);

        setLayout(new BorderLayout());
        add(progressLabel, BorderLayout.NORTH);
        add(tankPanel, BorderLayout.CENTER);

        configureMenuBar(tankPanel);

        setVisible(true);
    }

    private void configureMenuBar(TankPanel tankPanel) {
        JMenu menu = new JMenu("工具");
        addMenuItem(menu, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), new SaveAction(tankPanel));
        addMenuItem(menu, KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), new LoadAction(tankPanel));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu menu, KeyStroke keyStroke, Action action) {
        JMenuItem item = menu.add(action);
        item.setAccelerator(keyStroke);
    }

    public JLabel getProgressLabel() {
        return progressLabel;
    }

    public TankPanel getTankPanel() {
        return tankPanel;
    }
}
