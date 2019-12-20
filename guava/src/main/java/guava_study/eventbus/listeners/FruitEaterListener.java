package guava_study.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import guava_study.eventbus.events.Apple;
import guava_study.eventbus.events.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class FruitEaterListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void eat(Fruit event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Fruit eat [{}].", event);
        }
    }

    @Subscribe
    public void eat(Apple event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Apple eat [{}].", event);
        }
    }
}
