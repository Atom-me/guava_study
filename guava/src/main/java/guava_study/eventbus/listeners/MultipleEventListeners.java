package guava_study.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class MultipleEventListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

    @Subscribe
    public void task1(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==task1===", event);
        }
    }


    @Subscribe
    public void task2(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==task2===", event);
        }
    }

    /**
     * @param event 类型必须是Integer 不能是int
     */
    @Subscribe
    public void intTask(Integer event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==intTask===", event);
        }
    }
}
