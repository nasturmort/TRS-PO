package lr5.v4;

class TestThread extends Thread {

    String threadName;
    Lab5 c;
    int delay;
    int id;

    TestThread(String name, int t, Lab5 cc) {
        threadName = name;
        c = cc;
        delay = t;
        id = t;
        System.out.println("\n" + threadName + " created");
    }

    public void run() {
        System.out.println("\n" + threadName + " start of work\n");
        while (c.firstEnded || c.secondEnded) {
            System.out.println(threadName + " " + c.makeChanges(id));
            c.inc(id);
            try {
                Thread.sleep((int) ((double) delay * 10.0 * Math.random()));
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("\n" + threadName + " result -> " + c.buffer.toString());
        System.out.println(threadName + " end of work\n");
    }
}