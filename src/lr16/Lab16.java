package lr16;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Lab16 {

    int[] data;

    Lab16(int procNum) {

        data = new int[procNum];
        Random rnd = new Random();

        for (int i = 0; i < procNum; i++) {
            data[i] = rnd.nextInt(30);
        }
        CyclicBarrier barrier = new CyclicBarrier(procNum, () -> {
            System.out.println("----------------");
            System.out.println("Broken barrier:");
            for (int i = 0; i < data.length; i++) {
                System.out.println("Worker (" + i + "): " + data[i]);
            }
            System.out.println("----------------");
            System.out.println("After broken barrier:");
        });

        for (int i = 0; i < procNum; i++) {
            new Worker(barrier, i, this).start();
        }
    }

    public static void main(String argc[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number of processes: ");
        int procNum = scanner.nextInt();
        System.out.println("Before broken barrier:");
        Lab16 lab = new Lab16(procNum);
    }
}
