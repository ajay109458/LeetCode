package sdesheet;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Concurrency {

    private interface MyExecutorService {
        public void submit(Runnable task);
    }

    public static class MyThreadPool implements MyExecutorService {

        public static BlockingQueue<Runnable> queue;
        int capacity;
        int currentCapacity;

        public MyThreadPool(int capacity) {
            this.capacity = capacity;
            this.currentCapacity = 0;
            queue = new LinkedBlockingQueue<>();
        }


        @Override
        public void submit(Runnable task) {
            queue.add(task);

            execute();
        }

        private void execute() {
            if (currentCapacity < capacity) {
                currentCapacity++;
                new Thread(new Execution()).start();
            }
        }
    }

    private static class Execution implements Runnable {

        @Override
        public void run() {
            while(true) {
                if(!MyThreadPool.queue.isEmpty()) {
                    MyThreadPool.queue.poll().run();
                }
            }
        }
    }


}
