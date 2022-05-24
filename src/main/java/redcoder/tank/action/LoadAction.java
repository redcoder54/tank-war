package redcoder.tank.action;

import redcoder.tank.TGC;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoadAction extends AbstractAction {

    public LoadAction(){
        super("加载");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TGC.getTGC().load();
    }
}
