package lr10;

import java.util.concurrent.Exchanger;

public class SummatorDemo {

    SummatorDemo() {
        int sampleCount = 3;
        System.out.println( SummatorDemo.charGen( 0) + "Main process started");
        System.out.println( "The number of CPUs: " + Runtime.getRuntime().availableProcessors());

        Exchanger<Integer> ex1 = new Exchanger<Integer>();
        Exchanger<Integer> ex2 = new Exchanger<Integer>();
        Exchanger<Integer> ex3 = new Exchanger<Integer>();
        Exchanger<Integer> ex4 = new Exchanger<Integer>();

        Producer p = new Producer( 1, sampleCount, ex1);
        Summator s1 = new Summator( 2, ex1, ex2);
        Summator s2 = new Summator( 3, ex2, ex3);
        Summator s3 = new Summator( 4, ex3, ex4);
        Consumer c = new Consumer( 5, sampleCount, ex4);

        Thread ss1 = new Thread( s1);
        ss1.setDaemon( true);
        ss1.start();
        Thread ss2 = new Thread( s2);
        ss2.setDaemon( true);
        ss2.start();
        Thread ss3 = new Thread( s3);
        ss3.setDaemon( true);
        ss3.start();
        new Thread( c).start();
        new Thread( p).start();

        System.out.println( SummatorDemo.charGen( 0) + "Main process stoped");
    }

    static public String charGen( int count) {
        String s = "";
        for (int i = 0; i < count; ++i) {
            s += ";";
        }
        return( s);
    }

    public static void main( String args[]) {
        new SummatorDemo();
    }
}
