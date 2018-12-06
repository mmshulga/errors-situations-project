package my.mmshulga.heapdump;

import java.util.ArrayList;
import java.util.List;

/**
 * Run with the following flags:
 * ___________________________________________________________________
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=~/Desktop/ -Xmx10m
 * ___________________________________________________________________
 * Here we specify that an app should generate a heap dump upon
 * OutOfMemoryError and place this heap dump on the user desktop.
 * Also we limit the maximum allowed memory usage to 10 megabytes.
 *
 * After that you can launch jvisualvm tool and open
 * the heap dump file from desktop.
 */
@SuppressWarnings("unused")
public class Simple {
    public static void main(String[] args) {
        // do not discard any objects at all.
        List<BigClass> list = new ArrayList<>(100);
        while (true) {
            list.add(new BigClass());
        }
    }

    private static class BigClass {
        /**
         * 1 megabyte for faster failure.
         */
        private byte[] array = new byte[1024*1024];
    }
}
