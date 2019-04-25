package lr17;

import static java.lang.System.out;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Lab17 {

    public static double pos() {
        return 2.0 * Math.random() - 1.0;
    }

    public static double range() {
        double x = pos();
        double y = pos();
        double z = pos();
        return Math.sqrt(x * x + y * y + z * z);
    }

    public static double test(int numThrows) {
        long inSphere = DoubleStream.generate(() -> range())
                .limit(numThrows)
                .parallel()
                .filter(r -> r < 1)
                .count();
        return (double)inSphere / (double)numThrows;
    }

    public static void main(String[] args) {
        IntStream.of( 10000, 100000, 1000000)
                .mapToDouble(Lab17::test)
                .forEach(out::println);
    }
}
