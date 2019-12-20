package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import guava_study.eventbus.listeners.MultipleEventListeners;

/**
 * @author Atom
 */
public class MultipleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event");
        eventBus.post("i am string event");


        System.out.println("post the int event");
        eventBus.post(43);

    }
}
