package lr5.recursiveExample;

class Lock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;
    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while(isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if(lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}

public class CounterReentrant {
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
        CounterReentrant cnt = new CounterReentrant();
        TestThread t[] = new TestThread[10];
        for (int i = 0; i < 10; ++i) {
            t[i] = new TestThread( "Proc:" + i, i, cnt);
            t[i].start();
        }
        System.out.println( "Main process ended");
    }
}
