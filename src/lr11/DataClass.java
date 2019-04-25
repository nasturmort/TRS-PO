package lr11;

import java.util.concurrent.*;

class DataClass {
    int value;
    String message;
    double result;

    DataClass( int v, String s,double r) {
        value = v;
        message = s;
        result=r;
    }
    int getValue() { return( value); }
    String getMassage() { return( message); }
    public double getResult() { return result; }
}

class Loop implements Runnable {
    int counter;
    String name;
    double result;
    int a=5, b=4, x=4;


    public double function(int a, int b, int x){
        //double y=0;
        double y = a / (a / (a / (a / x - b) - b) - b) - b;
        return y;
    }

    Exchanger<DataClass> exchanger;

    Loop( int startValue, String id, Exchanger<DataClass> ex) {
        counter = startValue;
        name = id;
        exchanger = ex;
        System.out.println( name + ": created");
    }

    public void run() {
        System.out.println( name + ": started");
        DataClass data = new DataClass( counter, name,result);
        for (int i = 0; i < 3; ++i) {
            try {
                result=function(a,b,x);
                DataClass newData = exchanger.exchange( data);
                counter += newData.getValue();
                System.out.println( name + ": from " +
                        newData.getMassage() + ": data: " +
                        newData.getValue() + ": state = " + counter+
                        "\tresult:"+result);
            }
            catch (InterruptedException e) {
                System.err.println( e.toString());
            }
        }
        System.out.println( name + ": ended");
    }
}
