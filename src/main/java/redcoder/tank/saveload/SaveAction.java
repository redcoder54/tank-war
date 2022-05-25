package redcoder.tank.saveload;

import redcoder.tank.model.GameModel;
import redcoder.tank.model.GameModelWrapper;
import redcoder.tank.utils.ScheduledUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveAction extends AbstractAction {

    private static final Logger LOGGER = Logger.getLogger(SaveAction.class.getName());

    public SaveAction(){
        super("存档");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ScheduledUtils.schedule(() -> {
            try {
                GameModel gameModel = GameModelWrapper.getGameModel();
                GameSaveLoad.save(gameModel);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "LoadAction", ex);
            }
        }, 0, TimeUnit.SECONDS);
    }
}
