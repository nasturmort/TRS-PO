package lr5.nonRecursiveExample;

class Lock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while(isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

public class Counter {
    private Lock lock = new Lock();
    private int count = 0;
    private int newCount;

    public int inc() {
        try {
            lock.lock();
            newCount = ++count;
            lock.unlock();
        }
        catch (InterruptedException ie) {
        }
        return newCount;
    }

    public int dec() {
        try {
            lock.lock();
            newCount = --count;
            lock.unlock();
        }
        catch (InterruptedException ie) {
        }
        return newCount;
    }

    public static void main( String argc[]) {
        System.out.println( "Main process started");
        Counter cnt = new Counter();
        TestThread t[] = new TestThread[10];
        for (int i = 0; i < 10; ++i) {
            t[i] = new TestThread( "Proc:" + i, i, cnt);
            t[i].start();
        }
        System.out.println( "Main process ended");
    }
}

