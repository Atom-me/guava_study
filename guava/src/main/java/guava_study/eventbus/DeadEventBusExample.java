package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import guava_study.eventbus.listeners.DeadEventListener;

/**
 * @author Atom
 */
public class DeadEventBusExample {
    public static void main(String[] args) {
        final DeadEventListener deadEventListener = new DeadEventListener();
        final EventBus eventBus = new EventBus("DeadEventBus"){
            @Override
            public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        eventBus.register(deadEventListener);
        eventBus.post("hello");

        // unregister 之后 ，在post消息，listener就收不到了
        eventBus.unregister(deadEventListener);
        eventBus.post("hello");
        eventBus.post("hello");
        eventBus.post("hello");
        eventBus.post("hello");


    }
}
