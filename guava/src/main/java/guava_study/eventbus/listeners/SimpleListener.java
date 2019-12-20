package guava_study.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class SimpleListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);


    @Subscribe
    public void doAction(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("received enent {} and will take a action ", event);
        }
    }

}
