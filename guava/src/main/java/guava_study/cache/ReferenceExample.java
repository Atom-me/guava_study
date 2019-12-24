package guava_study.cache;

import org.omg.CORBA.TIMEOUT;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Atom
 * -Xmx128M -Xms64M -XX:+PrintGCDetails
 */
public class ReferenceExample {
    public static void main(String[] args) throws InterruptedException {

        /* // Strong Reference
        int counter = 0;
        List<Ref> container = new ArrayList<>();
        for (; ; ) {
            int current = counter++;
            container.add(new Ref(current));
            System.out.println("the " + current + " Ref will be insert into the container");
            TimeUnit.MILLISECONDS.sleep(500);
        }*/


        /**
         * SoftReference 其实照样会引起OOM ，并不是GC的时候立马把它回收，GC 探测到 JVM 内存 快溢出的时候，会尝试 回收 SoftReference。
         *
         * WeakReference 当然比他还脆弱
         */
     /*   int counter = 0;
        List<SoftReference<Ref>> container = new ArrayList<>();
        for (; ; ) {
            int current = counter++;
            container.add(new SoftReference<>(new Ref(current)));
            System.out.println("the " + current + " Ref will be insert into the container");
            TimeUnit.MILLISECONDS.sleep(500);
        }*/

        /**
         * WeakReference GC 的时候都会被释放，回收。
         *
         * WeakReference 当然比他还脆弱
         */
       /* int counter = 0;
        List<WeakReference<Ref>> container = new ArrayList<>();
        for (; ; ) {
            int current = counter++;
            container.add(new WeakReference<>(new Ref(current)));
            System.out.println("the " + current + " Ref will be insert into the container");
            TimeUnit.MILLISECONDS.sleep(500);
        }*/


        /**
         * SoftReference 如何使用
         */
        /*Ref ref = new Ref(10);
        SoftReference<Ref> refSoftReference = new SoftReference<>(ref);
        ref = null;
        // 调用GC方法到时候并不一定会立即执行GC，即使GC执行了并不是说我的SoftReference 会立马被GC掉。
        // 只有GC进程发现JVM快要OOM了才会 GC掉SoftReference
        System.gc();
//        Runtime.getRuntime().gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(ref); // 这里肯定是null
        System.out.println(refSoftReference.get().index); // 软引用必须通过get这种方式拿到这个对象*/

        /**
         * WeakReference 如何使用
         */
       /* Ref ref = new Ref(10);
        WeakReference<Ref> refWeakReference = new WeakReference<>(ref);
        ref = null;
        System.out.println(refWeakReference.get().index); // 手动GC 之前 可能会拿到值（但是JVM自己也可能会触发一次GC，会回收到我们到对象）
        // 调用GC方法到时候并不一定会立即执行GC，一旦执行了GC，WeakReference 会立马被GC掉。
        System.gc();
//        Runtime.getRuntime().gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(ref); // 这里肯定是null
        System.out.println(refWeakReference.get().index); // GC之后 weakReference 就会被回收掉。这里就拿不到值了
*/

        /**
         * PhantomReference 如何使用
         * PhantomReference 必须和 ReferenceQueue 结合使用，GC的时候把GC的PhantomReference对象会先放到这个  ReferenceQueue里面。
         * 我们就可以从这个queue里拿到这个对象，然后做一些处理。
         * apache commons 做文件上传的时候删除临时文件的 FileCleaningTracker 就是使用的虚引用 PhantomReference
         */
        Ref ref = new Ref(10);
        ReferenceQueue queue = new ReferenceQueue<>();
        MyPhantomReference refPhantomReference = new MyPhantomReference(ref, queue, 10);
        ref = null;
        System.out.println(refPhantomReference.get()); // PhantomReference 是拿不到值的

        System.gc();
        TimeUnit.SECONDS.sleep(2);
        Reference object = queue.remove();
        ((MyPhantomReference) object).doAction();


    }

    private static class MyPhantomReference extends PhantomReference<Object> {

        // 传递 Ref 类的 属性
        private int index;

        public MyPhantomReference(Object referent, ReferenceQueue<? super Object> q, int index) {
            super(referent, q);
            this.index = index;
        }


        // 定义一个方法对 回收的对象做处理
        public void doAction() {
            System.err.println("the object " + index + " is GC .");
        }
    }

    private static class Ref {
        private byte[] data = new byte[1024 * 1024];
        private final int index;

        public Ref(int index) {
            this.index = index;
        }

        /**
         * JVM 在做GC的是会调用finalize方法
         *
         * @throws Throwable
         */
        @Override
        protected void finalize() throws Throwable {
            System.out.println(" the index [ " + index + "] will be GC");
        }
    }
}
