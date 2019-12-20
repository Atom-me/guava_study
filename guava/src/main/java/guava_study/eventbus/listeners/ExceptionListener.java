package guava_study.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class ExceptionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionListener.class);

    @Subscribe
    public void m1(String event) {
        LOGGER.info("============m1==========={}", event);
        throw new RuntimeException();
    }

    @Subscribe
    public void m2(String event) {
        LOGGER.info("============m2==========={}", event);
    }

    @Subscribe
    public void m3(String event) {
        LOGGER.info("============m3==========={}", event);
    }
}
