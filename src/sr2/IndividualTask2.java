package sr2;
import java.util.concurrent.CountDownLatch;

public class IndividualTask2 {

    int array[] = new int[20];

    public static void main(String argc[]) throws InterruptedException {
        IndividualTask2 lab = new IndividualTask2();
        System.out.println("Before sort:");
        for(int i = 0; i < lab.array.length;i++) {
            lab.array[i] = (int) Math.round(Math.random() * 30);
            System.out.print(
                    lab.array[i]+" "
            );
        }
        double oneThreadTime = 0.0;
        int num=10;
        for (int numThreads = 1; numThreads <= num; numThreads++) {
            System.out.println("Потік: " + numThreads);
            int total = lab.array.length;
            int next = 0;
            int prev = 0;
            int iter = total / num;
            CountDownLatch stopLatch = new CountDownLatch(numThreads);

            StopLatchedThread[] threads = new StopLatchedThread[numThreads];
            for (int i = 0; i < numThreads; i++) {
                if (i == numThreads - 1) {
                    threads[i] = new StopLatchedThread(stopLatch, prev + 1, total, lab);
                } else {
                    next = (i + 1) * iter;
                    threads[i] = new StopLatchedThread(stopLatch, prev + 1, next, lab);
                    prev = next;
                }
            }
            long begin = System.nanoTime();

            for(int i = 0; i < numThreads;i++) {
                threads[i].start();
            }

            stopLatch.await();

            long end = System.nanoTime();
            double totalTime = (end - begin)  / 1000000.0;

            if(numThreads == 1) {
                oneThreadTime = totalTime;
            }

            System.out.println("");

            //System.out.println("Size: "+(int)(lab.array.length/num));
            System.out.println("Час(ms): " + totalTime);
            System.out.println("Теоретичний коефіцієнт: " + ((double)iter / (double)lab.array.length));
            System.out.println("Практичний коефіцієнт: " + (oneThreadTime / totalTime));
            System.out.println("");
        }
        System.out.println("Vector: ");
        for(int i = 0;i < lab.array.length; i++) {
            System.out.print(lab.array[i] + " ");
        }
    }
}
