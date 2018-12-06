package my.mmshulga.threaddump;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Run with the following flags:
 * ___________________________________________________________________
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=~/Desktop/ -Xmx6m
 * ___________________________________________________________________
 * Here we specify that an app should generate a heap dump upon
 * OutOfMemoryError and place this heap dump on the user desktop.
 * Also we limit the maximum allowed memory usage to 6 megabytes.
 *
 * After that you can launch jvisualvm and connect to the running process
 * to observe the GC work.
 */
@SuppressWarnings("unused")
public class NeverEnding {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Runnable smallRunnable1 = () -> System.out.println("Runnable #1 executes!");
        Runnable smallRunnable2 = () -> System.out.println("Runnable #2 executes!");

        // consumes more than 3 megabytes of memory.
        Runnable bigRunnable = () -> {
            LargeOne l1 = new LargeOne();
            LargeOne l2 = new LargeOne();
            LargeOne l3 = new LargeOne();
        };

        executor.scheduleWithFixedDelay(smallRunnable1, 5, 10, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(smallRunnable2, 5, 5, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(bigRunnable, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * Large object taking 1 megabyte of memory.
     */
    private static class LargeOne {
        private byte[] array = new byte[1024*1024];
    }
}
