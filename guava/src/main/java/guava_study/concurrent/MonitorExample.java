package guava_study.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

/**
 * @author Atom
 * <p>
 * 生产者 消费者 模式 三种实现方式（Synchronized,Lock Condition,Monitor)
 */
public class MonitorExample {

    public static void main(String[] args) {

//        final Synchronized sync = new Synchronized();
//        final LockCondition sync = new LockCondition();
        final MonitorGuard sync = new MonitorGuard();

        final AtomicInteger COUNTER = new AtomicInteger(0);

        // 生产者 放数据
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (; ; )
                    try {
                        int data = COUNTER.getAndIncrement();
                        System.out.println(currentThread() + " offer " + data);
                        sync.offer(data);
                        TimeUnit.MILLISECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }).start();
        }

        // 消费者 放数据
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (; ; )
                    try {
                        int data = sync.take();
                        System.out.println(currentThread() + " take " + data);
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }).start();
        }
    }


    /**
     * 使用guava 的 monitor guard 实现 生产者 消费者模式，
     * Monitor Guard 是指封装了Lock Condition ，
     * <p>
     * Monitor 作者认为 JDK 的lock condition  可读性不好，不清晰。不需要使用while循环（for循环）
     */
    static class MonitorGuard {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;
        private Monitor monitor = new Monitor();
        private final Monitor.Guard CAN_OFFER = monitor.newGuard(() -> queue.size() < MAX);
        private final Monitor.Guard CAN_TAKE = monitor.newGuard(() -> !queue.isEmpty());

        public void offer(int value) {
            try {
                monitor.enterWhen(CAN_OFFER);
                queue.addLast(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.leave();
            }
        }

        public int take() {
            try {
                monitor.enterWhen(CAN_TAKE);
                return queue.removeFirst();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                monitor.leave();
            }
        }

    }

    /**
     * 使用 LOCK 和 Condition 实现 生产者 消费者 模式
     */
    static class LockCondition {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition FULL_CONDITION = lock.newCondition();
        private final Condition EMPTY_CONDITION = lock.newCondition();
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;

        /**
         * 生产数据（放数据）
         *
         * @param value
         */
        public void offer(int value) {
            try {
                lock.lock();
                while (queue.size() >= MAX) {
                    try {
                        FULL_CONDITION.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(value);
                EMPTY_CONDITION.signalAll();// 通知 EMPTY_CONDITION 干活
            } finally {
                lock.unlock();
            }
        }


        /**
         * 消费数据（取数据）
         *
         * @return
         */
        public int take() {
            try {
                lock.lock();
                while (queue.isEmpty()) {
                    EMPTY_CONDITION.await();
                }
                Integer data = queue.removeFirst();
                FULL_CONDITION.signalAll(); //  通知 FULL_CONDITION 干活，不是EMPTY_CONDITION
                return data;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

    }


    /**
     * Synchronized 方式实现的 生产者 消费者模式
     */
    static class Synchronized {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10; // 队列最大容量

        /**
         * 生产数据（放数据）
         *
         * @param value
         */
        public void offer(int value) {
            synchronized (queue) {
                while (queue.size() >= MAX) { //如果大于等于 MAX,表示队列满了 就等待，使用while 不要使用if
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(value);
                queue.notifyAll();// 唤醒其他线程
            }
        }

        /**
         * 消费数据（取数据）
         *
         * @return
         */
        public int take() {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer value = queue.removeFirst();
                queue.notifyAll();
                return value;
            }
        }
    }
}
