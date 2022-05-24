package redcoder.tank.action;

import redcoder.tank.GameModelFactory.GameModelWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoadAction extends AbstractAction {

    public LoadAction() {
        super("加载");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameModelWrapper.getGameModel().load();
    }
}
