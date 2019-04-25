package lr4;

class TestThread extends Thread {

    String threadName;
    Lab4 m;

    TestThread(String name, Lab4 mm) {
        threadName = name;
        m = mm;
        System.out.println(threadName + " - Created");
    }

    public void run() {
        m.procIncrement();
        System.out.println(threadName + " - Start of Work");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
        }
        m.procDecrement();
        synchronized (m) {
            m.notify();
            System.out.println(threadName + " - Signal sended");
        }
        System.out.println(threadName + " - End of Work");
    }
}

