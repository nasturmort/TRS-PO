package lr12.test;

import java.util.concurrent.*;

class Thread1 implements Runnable {

    BlockingQueue<Integer> queue1;
    int n;

    Thread1(BlockingQueue<Integer> q1, int n) {
        queue1 = q1;
        this.n = n;
    }


    @Override
    public void run() {
        for(int i = 0; i < n ; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(50);
            try {
                queue1.put(rnd);

            } catch (InterruptedException ex) {
            }
        }
    }
}

class Thread2 implements Runnable {

    BlockingQueue<Integer> queue2;
    int n;

    Thread2(BlockingQueue<Integer> q2, int n) {
        queue2 = q2;
        this.n = n;
    }


    @Override
    public void run() {
        for(int i = 0; i < n ; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(50);
            try {
                queue2.put(rnd);
            } catch (InterruptedException ex) {
            }
        }
    }
}


class Thread3 implements Runnable {
    BlockingQueue<Integer> queue3;
    int n;

    Thread3(BlockingQueue<Integer> q3, int n) {
        queue3 = q3;
        this.n = n;
    }


    @Override
    public void run() {
        for(int i = 0; i < n ; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(50);
            try {
                queue3.put(rnd);
            } catch (InterruptedException ex) {
            }
        }
    }
}

class Thread4 implements Runnable {
    //BlockingQueue<Integer> queue;
    BlockingQueue<Integer> queue1;
    BlockingQueue<Integer> queue2;
    BlockingQueue<Integer> queue3;
    int n;

    Thread4(BlockingQueue<Integer> q1,BlockingQueue<Integer> q2,BlockingQueue<Integer> q3, int n) {
        queue1 = q1;
        queue2 = q2;
        queue3 = q3;
        this.n = n;
    }


    @Override
    public void run() {
        for(int i = 0; i < n ; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(50);
            try {
                int value1 = queue1.take();
                int value2 = queue2.take();
                int value3 = queue3.take();
                System.out.print(value1+" "+value2+" "+value3+" ");
            } catch (InterruptedException ex) {
            }
        }
    }
}