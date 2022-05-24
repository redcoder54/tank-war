package redcoder.tank.action;

import redcoder.tank.TGC;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveAction extends AbstractAction {

    public SaveAction(){
        super("保存");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TGC.getTGC().save();
    }
}
