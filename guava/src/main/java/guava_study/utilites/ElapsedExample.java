package guava_study.utilites;

import java.util.concurrent.TimeUnit;

/**
 * @author atom
 */
public class ElapsedExample {

    public static void main(String[] args) throws InterruptedException {
        process("234234234");

    }

    private static void process(String orderNo) throws InterruptedException {

        System.out.printf("start process the order is %s\n", orderNo);
        long startNano = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("the orderNo %s process successful and elasped %d ns.\n", orderNo, (System.nanoTime() - startNano));
    }
}
