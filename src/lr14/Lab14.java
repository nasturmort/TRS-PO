package lr14;
import java.util.*;
import java.util.concurrent.*;

public class Lab14 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CallableImpl[] callable = new CallableImpl[16];
        Future[] future = new Future[16];
        ExecutorService service = Executors.newFixedThreadPool(4);

        int iter = 3;
        int min = -3;
        int max = 3;


        for (int i = 0;i < 4; i++) {
            callable[i] = new CallableImpl(min, max, i + 1);
            future[i] = service.submit(callable[i]);
            min = max;
            max += iter;
        }

        for(int i = 0;i < 4; i++) {
            System.out.println("Future (" + (i + 1) + ") result: " + future[i].get());
        }


        service.shutdown();
    }
}