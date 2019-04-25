package lr14;

import java.util.*;
import java.util.concurrent.*;
class Data {
    int number;
    Data(int n) {
        number = n;
    }

    @Override
    public String toString() {
        return "{ " + number +  " }";
    }
}

class CallableImpl implements Callable<List<Data>> {

    int min;
    int max;
    int threadId;

    CallableImpl(int min, int max, int threadId) {
        this.min = min;
        this.max = max;
        this.threadId = threadId;
    }

    @Override
    public List<Data> call() throws Exception {
        System.out.println( "Callable (" + threadId + ") begin");
        List<Data> list = new ArrayList<>();
        List<Data> list1 = new ArrayList<>();

        int x,y,z;
        int min_ = -3;
        int max_ = 3;
        double rt;
        double r=Math.sqrt(2);

        for (int i = min; i <= max; i++) {
            x=min_ + (int)(Math.random() * ((max_ - min_) + 1));
            y=min_ + (int)(Math.random() * ((max_ - min_) + 1));
            z=min_ + (int)(Math.random() * ((max_ - min_) + 1));
            rt=Math.sqrt(x*x+y*y+z*z);
            if(rt<1)
                list.add(new Data(i));
            if(-r<x && x<r){list.add(new Data(i));}
            if(-r<y && y<r){list.add(new Data(i));}
            if(-r<z && z<r){list.add(new Data(i));}
        }
        System.out.println( "Callable (" + threadId + ") end");

        return list;
    }

}
