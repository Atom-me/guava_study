package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import guava_study.eventbus.listeners.ExceptionListener;

/**
 * @author Atom
 */
public class ExceptionEventBusExample {

    public static void main(String[] args) {
        // 构造EventBus的时候传递 异常处理器，对应的listener如果出现异常，event这边就可以监听到
        final EventBus eventBus = new EventBus(new ExceptionHandler());
        eventBus.register(new ExceptionListener());
        eventBus.post("exception post");
    }

    static class ExceptionHandler implements SubscriberExceptionHandler {

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        }
    }
}
