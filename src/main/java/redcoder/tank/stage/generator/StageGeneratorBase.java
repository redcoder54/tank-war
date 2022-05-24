package redcoder.tank.stage.generator;

import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.producer.TankProducer;
import redcoder.tank.utils.ScheduledUtils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class StageGeneratorBase implements StageGenerator {

    private static final Logger LOGGER = Logger.getLogger(StageGeneratorBase.class.getName());

    protected String name;

    public StageGeneratorBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    protected void produceTank() {
        try {
            GameConfig gameConfig = GameConfigFactory.getGameConfig();
            Class<TankProducer> tankProducerClass = gameConfig.getTankProducerClass();
            TankProducer tankProducer = tankProducerClass.newInstance();
            ScheduledUtils.schedule(tankProducer, 0, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "StageGeneratorBase.produceTank", e);
        }
    }
}
