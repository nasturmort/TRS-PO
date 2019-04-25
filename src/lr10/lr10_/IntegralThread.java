package lr10.lr10_;
import java.util.concurrent.CountDownLatch;
class IntegralThread extends Thread {

    int id;
    double xMin;
    double xMax;
    double ny;
    double hx;
    double hy;
    double minX;
    double minY;
    Lab10 lab;

    IntegralThread(double xMin, double xMax, double hx, double hy,  double ny, Lab10 lab, int id, double minX, double minY) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.hx = hx;
        this.hy = hy;
        this.ny = ny;
        this.id = id;
        this.minX = minX;
        this.minY = minY;
        this.lab = lab;
    }

    double function(double x) {
        return 10.0*Math.sin(4+Math.PI*x+5);
    }

    @Override
    public void run() {

        double s = 0.0;

        for (double i = xMin; i <= xMax; i++) {
            for (double j = 0; j < ny; j++) {
                    double xi = minX + hx / 2.0 + i * hx;
                    s +=  function(xi);
            }
        }

        lab.result[id] = s;
        lab.countDownLatch.countDown();
    }
}