package lr10.lr10_;
import java.util.concurrent.CountDownLatch;
public class Lab10 {

    CountDownLatch countDownLatch;
    double result[];

    Lab10(double xMin, double xMax, double yMin, double yMax, int procN, double stepX, double stepY) {
        result = new double[procN];
        countDownLatch = new CountDownLatch(procN);

        double hx = (xMax - xMin) / stepX;
        double hy = (yMax - yMin) / stepY;


        int total = (int) stepX;
        int next = 0;
        int prev = 0;
        int iter = total / procN;

        for (int i = 0; i < procN; i++) {
            if (i == procN - 1) {
                IntegralThread thread = new IntegralThread(prev + 1, total, hx, hy, stepY,  this, i, xMin, yMin);
                thread.start();

            } else {
                next = (i + 1) * iter;
                IntegralThread thread = new IntegralThread(prev + 1, next, hx, hy, stepY,  this, i, xMin, yMin);
                thread.start();
                prev = next;
            }
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }

        double s = 0.0;

        for (int i = 0; i < result.length; i++) {
            s += result[i];
        }

        System.out.println("result\nF = " + s);
    }

    public static void main(String args[]) {
        double root = Math.sqrt(2.0);
        Lab10 lab = new Lab10(-root, root, 0.0, 4.0, 5, 20, 20);
    }
}