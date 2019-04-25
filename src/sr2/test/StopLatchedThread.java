package sr2.test;



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
        while(!isSorted) {
            isSorted = true;
            for (int i = minIndex; i < maxIndex - 1; i++) {
                for (int j = i+1; j < maxIndex - 1; j++) {
                    int temp =lab.array[i][j];
                    lab.array[i][j] = lab.array[j][i];
                    lab.array[j][i] = temp;
                }
            }
        }
        stopLatch.countDown();
    }
}
