package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import guava_study.eventbus.listeners.ConcreteListener;
import guava_study.eventbus.listeners.MultipleEventListeners;

/**
 * @author Atom
 */
public class InheritListenersEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event");
        eventBus.post("I am string event");// listener的父类都会处理


        System.out.println("post the int event");
        eventBus.post(2000);

    }
}
