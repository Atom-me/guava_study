package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import guava_study.eventbus.events.Apple;
import guava_study.eventbus.events.Fruit;
import guava_study.eventbus.listeners.ConcreteListener;
import guava_study.eventbus.listeners.FruitEaterListener;

/**
 * @author Atom
 */
public class InheritEventsEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        System.out.println("post the string event");
        eventBus.post(new Apple("apple")); // 父类也会处理
        System.out.println("================================");
        eventBus.post(new Fruit("apple"));// 子类不会处理


        System.out.println("post the int event");
        eventBus.post(2000);

    }
}
