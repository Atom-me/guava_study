package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import guava_study.eventbus.listeners.SimpleListener;

/**
 * @author Atom
 */
public class SimpleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("simple event1");
        eventBus.post("simple event2");
        eventBus.post(44);

    }
}
