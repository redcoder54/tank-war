package redcoder.tank.pauseresume;

import redcoder.tank.utils.ScheduledUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PauseResumeListeners implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PauseResumeListeners.class.getName());

    private AtomicInteger count = new AtomicInteger();
    private Map<Integer, PauseResumeListener> listeners = new HashMap<>();

    /**
     * 添加一个暂停/恢复监听器
     *
     * @param listener 暂停/恢复监听器
     * @return 监听器所在位置
     */
    public synchronized int addPauseResumeListener(PauseResumeListener listener) {
        int i = count.incrementAndGet();
        listeners.put(i, listener);
        return i;
    }

    /**
     * 添加一个暂停/恢复监听器
     *
     * @param index 监听器所在位置
     */
    public synchronized void removePauseResumeListener(int index) {
        listeners.remove(index);
    }

    public void firePauseResumeEvent(PauseResumeEventType eventType) {
        ScheduledUtils.schedule(() -> {
            try {
                PauseResumeEvent event = new PauseResumeEvent(this, eventType);
                for (PauseResumeListener listener : listeners.values()) {
                    listener.onPauseResume(event);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "PauseResumeListeners.firePauseResumeEvent", e);
            }
        }, 0, TimeUnit.SECONDS);
    }
}
