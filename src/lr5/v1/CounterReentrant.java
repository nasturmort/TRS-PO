package lr5.v1;

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
    public int positionOne = 0;
    public int positionTwo = 0;
    private int newCountOne;
    private int newCountTwo;
    public StringBuilder buffer = new StringBuilder("0");

    public int m0=3;
    public int m1=1;
    public int m2=1;


    public int m1_() {
        try {
            lock.lock();
            newCount = m1++;
            lock.unlock();
        }
        catch (InterruptedException ie) {
        }
        return newCount;
    }
    public int m2_() {
        try {
            lock.lock();
            newCount = m2++;
            lock.unlock();
        }
        catch (InterruptedException ie) {
        }
        return newCount;
    }
    public static void main( String argc[]) {
        System.out.println( "Main process started");
        CounterReentrant cnt = new CounterReentrant();
        TestThread t[] = new TestThread[2];
        for (int i = 0; i < t.length; ++i) {
            t[i] = new TestThread( "Proc:" + (i+1), i+1, cnt);
            t[i].start();
        }
        System.out.println( "Main process ended");
        //System.out.println("\n  result -> " + cnt.m0+"+"+ (cnt.m1_()-1)+"+"+ (cnt.m2_()-1));
    }
}
