package lr12;

import java.util.concurrent.*;

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    int procNumber;

    Producer( int p, BlockingQueue<Integer> q) {
        queue = q;
        procNumber = p;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; ++i) {
                queue.put( (Integer)produce( i));
            }
            queue.put( 0);
            queue.put( 0);
        }
        catch (InterruptedException ex) {
            System.out.println( "Process (" + procNumber + "): " + ex.toString());
        }
    }

    Object produce( int i) {
        System.out.println( "Producer(" + procNumber + "): " + i);
        return( i + 10);
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    int procNumber;

    Consumer( int p, BlockingQueue<Integer> q) {
        queue = q;
        procNumber = p;
    }

    public void run() {
        try {
            while (true) {
                consume( queue.take());
            }
        }
        catch (InterruptedException ex) {
            System.out.println( "Process (" + procNumber + "): " + ex.toString());
        }
    }

    void consume( Integer x) throws InterruptedException {
        System.out.println( "Result(" + procNumber + "): " + x);
        if (x == 0) {
            throw new InterruptedException();
        }
    }
}

