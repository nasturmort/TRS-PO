package lr16;
import java.util.concurrent.*;
import java.util.*;

class Worker extends Thread {

    CyclicBarrier barrier;
    int procId;
    Lab16 lab;

    Worker(CyclicBarrier b, int id, Lab16 d) {
        barrier = b;
        procId = id;
        lab = d;
    }

    @Override
    public void run() {
        try {
            System.out.println("Worer (" + procId + "): " + lab.data[procId]);
            lab.data[procId]++;
            barrier.await();
            lab.data[procId]--;
            System.out.println("Worker (" + procId + "): " + lab.data[procId]);
        } catch (InterruptedException | BrokenBarrierException e) {
        }
    }
}


