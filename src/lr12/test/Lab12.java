package lr12.test;

import java.util.*;
import java.util.concurrent.*;
public class Lab12 {

    public static void main(String argc[]) {
        System.out.print("Введите число N: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        SynchronousQueue<Integer> q1 = new SynchronousQueue<>(true);
        SynchronousQueue<Integer> q2 = new SynchronousQueue<>(true);
        SynchronousQueue<Integer> q3 = new SynchronousQueue<>(true);

        Thread1 thread1 = new Thread1(q1, n);
        Thread2 thread2 = new Thread2(q2, n);
        Thread3 thread3 = new Thread3(q3, n);
        Thread4 thread4 = new Thread4(q1,q2,q3, n);

        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();
        new Thread(thread4).start();
    }
}
