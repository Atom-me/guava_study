package guava_study.utilites;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class StopWatchExample {

    private final static Logger LOGGER = LoggerFactory.getLogger(StopWatchExample.class);


    public static void main(String[] args) throws InterruptedException {
        process("234234234");

    }

    private static void process(String orderNo) throws InterruptedException {

        LOGGER.info("start process the order is %s\n", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(100);
        LOGGER.info("the orderNo {} process successful and elasped {} .\n", orderNo, stopwatch.stop());
//        LOGGER.info("the orderNo {} process successful and elasped {} .\n", orderNo, stopwatch.stop().elapsed(TimeUnit.MINUTES));

    }
}
