package redcoder.tank.pauseresume;

import java.io.Serializable;
import java.util.EventListener;

public interface PauseResumeListener extends EventListener, Serializable {

    void onPauseResume(PauseResumeEvent event);
}
