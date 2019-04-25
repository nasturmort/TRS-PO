package lr10;

import java.util.concurrent.*;

class Summator implements Runnable {
    int id;
    Exchanger<Integer> in, out;

    Summator( int id0, Exchanger<Integer> in0, Exchanger<Integer> out0) {
        id = id0;
        in = in0;
        out = out0;
        System.out.println( SummatorDemo.charGen( id) + "Summator(" + id + "): Created");
    }

    public void run() {
        System.out.println( SummatorDemo.charGen( id) + "Summator(" + id + "): started");
        try {
            while (true) {
                Integer a = in.exchange( null);
                System.out.println( SummatorDemo.charGen( id) + "Summator(" + id + "): in: " + a);
                ++a;
                System.out.println( SummatorDemo.charGen( id) + "Summator(" + id + "): out: " + a);
                out.exchange( a);
            }
        }
        catch (InterruptedException e) {
            System.err.println( e.toString());
        }
        System.out.println( SummatorDemo.charGen( id) + "Summator(" + id + "): stoped");
    }
}

class Producer implements Runnable {
    int id;
    int sampleCount;
    Exchanger<Integer> out;

    Producer( int id0, int sc, Exchanger<Integer> out0) {
        id = id0;
        out = out0;
        sampleCount = sc;
        System.out.println( SummatorDemo.charGen( id) + "Producer(" + id + "): Created");
    }

    public void run() {
        System.out.println( SummatorDemo.charGen( id) + "Producer(" + id + "): started");
        try {
            Integer a = new Integer( 0);
            for (int i = 0; i < sampleCount; ++i) {
                ++a;
                System.out.println( SummatorDemo.charGen( id) + "Producer(" + id + "): out: " + a);
                out.exchange( a);
            }
        }
        catch (InterruptedException e) {
            System.err.println( e.toString());
        }
        System.out.println( SummatorDemo.charGen( id) + "Producer(" + id + "): stoped");
    }
}

class Consumer implements Runnable {
    int id;
    int sampleCount;
    Exchanger<Integer> in;

    Consumer( int id0, int sc, Exchanger<Integer> in0) {
        id = id0;
        in = in0;
        sampleCount = sc;
        System.out.println( SummatorDemo.charGen( id) + "Consumer(" + id + "): Created");
    }

    public void run() {
        System.out.println( SummatorDemo.charGen( id) + "Consumer(" + id + "): started");
        try {
            while (sampleCount > 0) {
                Integer a = in.exchange( null);
                System.out.println( SummatorDemo.charGen( id) + "Consumer(" + id + "): in: " + a + ", Rest: " + sampleCount);
                --sampleCount;
            }
        }
        catch (InterruptedException e) {
            System.err.println( e.toString());
        }
        System.out.println( SummatorDemo.charGen( id) + "Consumer(" + id + "): stoped");
    }
}

