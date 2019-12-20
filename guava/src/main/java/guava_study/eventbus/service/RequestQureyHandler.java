package guava_study.eventbus.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import guava_study.eventbus.events.Request;
import guava_study.eventbus.events.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atom
 */
public class RequestQureyHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestQureyHandler.class);

    private final EventBus eventBus;

    public RequestQureyHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void doQuery(Request request) {
        LOGGER.info("start query the orderNo [{}]", request.getOrderNo());
        Response response = new Response();
        this.eventBus.post(response);
    }
}
