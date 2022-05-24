package redcoder.tank.stage;

import java.io.Serializable;

public interface StageSwitchTask extends Serializable {

    void start();

    void stop();
}
