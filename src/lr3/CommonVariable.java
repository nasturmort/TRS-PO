package lr3;

public class CommonVariable {
    boolean state = false;
    boolean state1 = false;
    boolean state2 = false;
    CommonVariable() {
        state = false;
        state1 = false;
        state2= false;
    }

    public static void main( String argc[]) {
        System.out.println( "Main process started");
        CommonVariable cv = new CommonVariable();
        TestThread t1 = new TestThread( "First", cv);
        TestThread t2 = new TestThread( "Second", cv);
        TestThread t3 = new TestThread( "Third", cv);

        t1.start();
        t2.start();
        t3.start();

        System.out.println( "Main process: " + cv.state);
        try {
            Thread.sleep( 60);
        }
        catch (InterruptedException ie) {
        }
        cv.state = true;
        System.out.println( "Main process: " + cv.state);
        System.out.println( "Main process ended");
    }
}