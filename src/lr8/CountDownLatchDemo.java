package lr8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main( String args[]) {
        System.out.println( "Main process started");
        CountDownLatch startLatch = new CountDownLatch(1);
        for (int i = 0; i < 4; ++i) {
            LatchedThread t = new LatchedThread(startLatch, i+1);
            t.start();
        }
        try {
            Thread.sleep( 200);
        }
        catch( InterruptedException e) {
            System.err.println( e.toString());
        }
        startLatch.countDown();
        System.out.println( "Main process stoped");
    }
}
