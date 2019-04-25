package lr6.countingSemaphore;

class SendingThread extends Thread {
    CountingSemaphore semaphore = null;

    SendingThread( CountingSemaphore semaphore) {
        this.semaphore = semaphore;
        System.out.println( "SendingThread created");
    }

    public void run() {
        System.out.println( "SendingThread started");
        for (int i = 0; i < 6; ++i) {
            System.out.println( "SendingThread do(" + i + ")");
            this.semaphore.take( i);
        }
        System.out.println( "SendingThread stoped");
    }
}

class ReceivingThread extends Thread {
    CountingSemaphore semaphore = null;

    ReceivingThread( CountingSemaphore semaphore) {
        this.semaphore = semaphore;
        System.out.println( "ReceivingThread created");
    }

    public void run() {
        int sum = 0;
        System.out.println( "ReceivingThread started");
        while(true) {
            try {
                int m = this.semaphore.release();
                sum += m;
                System.out.println( "ReceivingThread do(" + m + "): " + sum);
            }
            catch (InterruptedException ie) {
            }
        }
    }
}

class CountingSemaphore {
    private int signals = 0;
    private int message = 0;

    public synchronized void take( int m) {
        this.signals++;
        message = m;
        this.notify();
        System.out.println( "CountingSemaphore(take): " + this.signals);
    }

    public synchronized int release() throws InterruptedException {
        while(this.signals == 0) {
            wait();
        }
        this.signals--;
        System.out.println( "CountingSemaphore(release): " + this.signals);
        return( message);
    }
}

