package lr9.v11;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Lr9 {
    int[] arr={2,1,0,4,11,6,3,12,1,5,3,10,34,2,12,34,3};
    int max=0;
    //int num = 7;
    public static void main(String[] args) {
        System.out.print("Input number of threads: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        Lr9 lr=new Lr9();
        int total = lr.arr.length;
        int p = 0;
        int iter = total / input;
        CountDownLatch stopLatch = new CountDownLatch(input);
        for (int i =0; i<input;i++){
            if(input==1){
                StopLatchedThread t = new StopLatchedThread(stopLatch,  total, lr);
                t.start();
            }
            else {
                StopLatchedThread t = new StopLatchedThread(stopLatch, iter, lr);
                t.start();
            }
        }
        try {
            stopLatch.await();
        } catch (InterruptedException e) {
            System.err.println(e.toString());
        }
        System.out.println("max "+lr.max);
        System.out.println("Main process stoped");
    }
}
