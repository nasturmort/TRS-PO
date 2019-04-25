package lr15;

import java.util.concurrent.*;

public class Lab15 {

    double[] data;
    boolean[] flags;
    boolean stopFlag = false;

    Lab15(double a, double b, double c, double d,
          double e, double f, double g, double h,
          double i, double j, double k, double l,
          double m, double n, double p, double q) {

        data = new double[31];
        flags = new boolean[31];

        data[0] = a;
        data[1] = b;
        data[2] = c;
        data[3] = d;
        data[4] = e;
        data[5] = f;
        data[6] = g;
        data[7] = h;
        data[8] = i;
        data[9] = j;
        data[10] = k;
        data[11] = l;
        data[12] = m;
        data[13] = n;
        data[14] = p;
        data[15] = q;

        for (int step = 0; step <= 15; step++) {
            flags[step] = true;
        }

        for (int step = 16; step < data.length; step++) {
            data[step] = 0.0;
            flags[step] = false;
        }

    }

    public static void main(String argc[]) throws InterruptedException {
        System.out.println("Main started.");
        ExecutorService service = Executors.newFixedThreadPool(15);
        Lab15 lab = new Lab15(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

        // Top fraction
        // a * b
        service.execute(new Calculator("Mul_1", 0, 1, 16, '*', lab));

        // c + d
        service.execute(new Calculator("Sum_1", 2, 3, 17, '+', lab));

        // e - f
        service.execute(new Calculator("Sub_1", 4, 5, 18, '-', lab));

        // g / h
        service.execute(new Calculator("Div_1", 6, 7, 19, '/', lab));

        // (e * f) +(g - h)
        service.execute(new Calculator("Sum_2", 18, 19, 21, '+', lab));

        // ((a * b) / (c + d)) * ((e - f) +(g /h))
        service.execute(new Calculator("Mul_2", 20, 21, 22, '*', lab));

        // Down fraction
        // i+ j
        service.execute(new Calculator("Sum_3", 8, 9, 23, '+', lab));

        // k * l
        service.execute(new Calculator("Mul_3", 10, 11, 24, '*', lab));

        // m / n
        service.execute(new Calculator("Div_4", 12, 13, 25, '/', lab));

        // p / q
        service.execute(new Calculator("Div_5", 14, 15, 26, '/', lab));

        // (i + j) / (k * l)
        service.execute(new Calculator("Div_6", 23, 24, 27, '/', lab));

        // (m / n) +(p / q)
        service.execute(new Calculator("Sum_4", 25, 26, 28, '+', lab));

        // ((i - j) / (k - l)) - ((m / n) - (p / q))
        service.execute(new Calculator("Sub_7", 27, 28, 29, '-', lab));


        // Total result
        service.execute(new Calculator("Div_7", 22, 29, 30, '/', lab));

        while(!lab.flags[30]) {
            Thread.sleep(2);
        }
        lab.stopFlag = true;
        System.out.println("");

        for(int i = 0; i < lab.data.length; i++) {
            System.out.println("(" + (i+1) + "): " + lab.data[i]);
        }

        service.shutdown();
        System.out.println("Main finished.");
    }
}
