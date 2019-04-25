package lr12;

import java.util.concurrent.SynchronousQueue;

public class BlockingQueueDemo {
    public static void main( String args[]) {
        System.out.println( "Main process started");
        SynchronousQueue<Integer> q = new SynchronousQueue<Integer>( true);
        Producer p = new Producer( 1, q);
        Consumer c1 = new Consumer( 1, q);
        Consumer c2 = new Consumer( 2, q);
        new Thread( p).start();
        new Thread( c1).start();
        new Thread( c2).start();
    }
}
