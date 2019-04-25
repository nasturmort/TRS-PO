package lr6.signaling;

class SendingThread extends Thread {
    Semaphore semaphore = null;

    SendingThread( Semaphore semaphore) {
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
    Semaphore semaphore = null;

    ReceivingThread( Semaphore semaphore) {
        this.semaphore = semaphore;
        System.out.println( "ReceivingThread created");
    }

    public void run() {
        System.out.println( "ReceivingThread started");
        while(true) {
            try {
                int m = this.semaphore.release();
                System.out.println( "ReceivingThread do(" + m + ")");
            }
            catch (InterruptedException ie) {
            }
        }
    }
}

class Semaphore {
    private boolean signal = false;
    private int message = 0;

    public synchronized void take( int m) {
        this.signal = true;
        this.notify();
        message = m;
        System.out.println( "Semaphore take");
    }

    public synchronized int release() throws InterruptedException{
        while(!this.signal) {
            wait();
        }
        this.signal = false;
        System.out.println( "Semaphore release");
        return( message);
    }
}

