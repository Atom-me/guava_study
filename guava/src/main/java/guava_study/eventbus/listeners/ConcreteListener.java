package guava_study.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class ConcreteListener extends BaseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcreteListener.class);

    @Subscribe
    public void concreteTask(String event) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] will be handler by {}.{}", event, this.getClass().getSimpleName(), "concreteTask");
        }
    }
}
