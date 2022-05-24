package redcoder.tank.pauseresume;

import java.util.EventObject;

public class PauseResumeEvent extends EventObject {

    private PauseResumeEventType eventType;

    public PauseResumeEvent(Object source, PauseResumeEventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    public PauseResumeEventType getEventType() {
        return eventType;
    }
}
