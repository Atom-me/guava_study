package guava_study.concurrent;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/**
 * @author Atom
 * <p>
 * ListenableFuture 解决了某个痛点（Future 你要想得到任务执行结果，你需要取主动拿结果 feture.get()，在你主动拿结果的时候会被block住。）
 * ListenableFuture 你不需要主动去拿结果，它执行完成后会回调你，通知你。
 * CompletableFuture Java8 （推荐使用这个）
 */
public class ListenableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        /*
        // JDK Future 的痛点，需要主动去拿任务执行结果，拿的过程还会阻塞。
        Future<Integer> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });


      //Future 你要想得到任务执行结果，你需要取主动拿结果 feture.get()，在你主动拿结果的时候会被block住。
      Object result = future.get();
        System.out.println(result);
        System.out.println("================");

        */


        /*
        //Guava 的 ListenableFuture 解决了 JDK的Future的痛点。不会阻塞，但是 使用监听器 拿不到任务执行结果。
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<?> future = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 这里我们submit是一个runnable 参数，我们在listener里拿不到任务执行结果。
        future.addListener(() -> System.out.println("i am finished."), service); // 这里不会block。
        System.out.println("================");*/

/*
        //Guava 的 ListenableFuture 解决了 JDK的Future的痛点。不会阻塞，Futures.addCallback 使用回调 可以拿到任务执行结果。
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<?> future = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        // 通过Futures 注册 callback ，拿到任务执行结果
        Futures.addCallback(future, new MyCallBack(), service); // 这里也是不会阻塞的。
        System.out.println("================");*/


        // Java8 的 CompletableFuture 更强大，如果使用的是Java8 版本 推荐使用这个
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        }, service).whenComplete((v, t) -> System.out.println("i am finished and the result is " + v));
        System.out.println("============");
    }

    static class MyCallBack implements FutureCallback {

        @Override
        public void onSuccess(@Nullable Object result) {
            System.out.println("i am finished and the result is " + result);
        }

        @Override
        public void onFailure(Throwable t) {
            t.printStackTrace();
        }
    }

}


