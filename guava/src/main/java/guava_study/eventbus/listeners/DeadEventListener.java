package guava_study.eventbus.listeners;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class DeadEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeadEventListener.class);

    /**
     * DeadEvent listener 要求 参数必须是 DeadEvent 类型
     *
     * @param deadEvent
     */
    @Subscribe
    public void handle(DeadEvent deadEvent) {

        LOGGER.info("{}", deadEvent.getSource());
        LOGGER.info("{}", deadEvent.getEvent());
    }
}
