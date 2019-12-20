package guava_study.eventbus.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Atom
 */
@Data
@AllArgsConstructor
public class Request {
    private final String orderNo;


}
