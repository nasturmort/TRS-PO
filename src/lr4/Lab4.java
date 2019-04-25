package lr4;

public class Lab4 implements Runnable {

    int procNum = 0;

    Lab4(int procNumber) {
        TestThread t[] = new TestThread[procNumber];
        for (int i = 0; i < procNumber; ++i) {
            t[i] = new TestThread("Proc:" + i, this);
            t[i].start();
        }
    }


    public void run() {
        System.out.println("Monitor - Started: " + getProcNum());
        try {
            while (getProcNum() != 0) {
                synchronized (this) {
                    System.out.println("Monitor - Waiting: " + getProcNum());
                    wait();
                    System.out.println("Monitor - Signal received: " + getProcNum());
                    if (getProcNum() == 3) {
                        System.out.println("\nAnastasiia Kuropiatnyk\n");
                    }
                }
            }
        } catch (InterruptedException ee) {
            System.out.println("Monitor - Interrupted Exception: " + ee.toString());
        }
        System.out.println("Monitor - Ended: " + getProcNum());
    }

    public synchronized void procIncrement() {
        ++procNum;
    }

    public synchronized void procDecrement() {
        --procNum;
    }

    public synchronized int getProcNum() {
        return procNum;
    }

    public static void main(String argc[]) {
        System.out.println("Main process started");
        Lab4 m = new Lab4(5);
        new Thread(m).start();
        System.out.println("Main process ended");
    }
}
