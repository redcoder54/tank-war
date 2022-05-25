package redcoder.tank.saveload;

import redcoder.tank.model.GameModel;
import redcoder.tank.model.GameModelWrapper;
import redcoder.tank.utils.ScheduledUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadAction extends AbstractAction {

    private static final Logger LOGGER = Logger.getLogger(LoadAction.class.getName());

    public LoadAction() {
        super("读档");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ScheduledUtils.schedule(() -> {
            try {
                GameModel gameModel = GameSaveLoad.load();

                // stop old producer
                GameModelWrapper.getGameModel().getTankProducer().stop();
                // start new producer
                ScheduledUtils.schedule(gameModel.getTankProducer(), 0, TimeUnit.SECONDS);

                GameModelWrapper.setGameModel(gameModel);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "LoadAction", ex);
            }
        }, 0, TimeUnit.SECONDS);
    }
}
