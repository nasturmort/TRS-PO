package sr2;

import java.util.concurrent.CountDownLatch;

class StopLatchedThread extends Thread {

    private final CountDownLatch stopLatch;
    private final int minIndex;
    private final int maxIndex;
    private final IndividualTask2 lab;

    public StopLatchedThread(CountDownLatch s, int minI, int maxI, IndividualTask2 l) {
        stopLatch = s;
        minIndex = minI - 1;
        maxIndex = maxI;
        lab = l;
    }

    @Override
    public void run() {
        boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = minIndex; i < maxIndex - 1; i++) {
                if(lab.array[i] > lab.array[i+1]){
                    isSorted = false;
                    buf = lab.array[i];
                    lab.array[i] = lab.array[i+1];
                    lab.array[i+1] = buf;
                }
            }
        }
        stopLatch.countDown();
    }
}
