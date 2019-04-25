package lr9.v11;

import java.util.concurrent.CountDownLatch;

class StopLatchedThread extends Thread {

    private final CountDownLatch stopLatch;
    //private final int minIndex;
    private final int maxIndex;
    private final Lr9 lab;

    public StopLatchedThread(CountDownLatch s, int maxI, Lr9 l) {
        stopLatch = s;
        maxIndex = maxI;
        lab = l;
    }


    @Override
    public void run() {
        int num=7;

        for (int i=0;i<lab.arr.length;i++){
            if(num<lab.arr[i]){lab.max=i;
                break;}
        }
        stopLatch.countDown();
        //System.out.println( "Main process stoped");
    }
}