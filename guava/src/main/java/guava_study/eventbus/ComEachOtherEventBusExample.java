package guava_study.eventbus;

import com.google.common.eventbus.EventBus;
import guava_study.eventbus.service.QueryService;
import guava_study.eventbus.service.RequestQureyHandler;

/**
 * @author Atom
 */
public class ComEachOtherEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        eventBus.register(new RequestQureyHandler(eventBus));
        queryService.query("alsdfjlasfdk");

    }
}
