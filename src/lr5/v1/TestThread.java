package lr5.v1;

public class TestThread  extends Thread {
    String threadName;
    CounterReentrant c;
    int delay;
    int id;

    TestThread( String name, int t, CounterReentrant cc) {
        threadName = name;
        c = cc;
        delay = t;
        System.out.println( threadName + " - Created");
    }

    public void run() {
        System.out.println( threadName + " - Start of Work");
        for(int i = 0; i < 5; ++i){
            if (delay % 2 == 0) {
                System.out.println( threadName + " = " + c.m1_());
            } else {
                System.out.println( threadName + " = " + c.m2_());
            }
            try {
                Thread.sleep( (int)((double)delay * 10.0 * Math.random()));
            }
            catch (InterruptedException ie) {
            }
        }

        System.out.println(threadName + " end of work\n");
        System.out.println("\n  result -> " + c.m0+"+"+ (c.m1-1)+"+"+ (c.m2-1));
    }
    public String result(){
        return ("\n  result -> " + c.m0+"+"+ (c.m1-1)+"+"+ (c.m2-1));
    }
}
