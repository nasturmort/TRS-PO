package lr7.test;

import java.io.*;

class ReadWriteThread extends Thread {
    BufferedReader reader;
    BufferedWriter writer;
    String name;
    Data data;
    int id;

    public ReadWriteThread(BufferedReader r, BufferedWriter w, int n, Data d) {
        reader = r;
        writer = w;
        name = n == 1 ? "Reader" : "Writer";
        id = n;
        data = d;
        System.out.println(name + " created!");
    }

    @Override
    public void run() {
        System.out.println();
        try {
            while (data.getStr() != null) {
                if (id == 1) {
                    data.setStr(reader.readLine());
                    Thread.sleep(15);
                }
                else {
                    Thread.sleep(15);
                    if(data.getStr() == null) break;
                    String result1 = data.sumStr();
                    String result2 = data.sumStr1();
                    writer.write("(" + data.getStr() +  "): четные " + result1 +" \tнечетные "+ result2+"\n");
                }
                if(data.getStr() == null) break;
                Thread.sleep(5);
                System.out.println(name +  ": " + data.getStr());
            }
        }
        catch (IOException | InterruptedException ex) {}
        System.out.println(name + " end of work!");
    }
}

class Data {
    private final Lock lock;
    private String str;

    public Data() {
        lock = new Lock();
        str = "";
    }

    public void setStr(String str) {
        try {
            lock.lock();
            this.str = str;
            lock.unlock();
        } catch (InterruptedException ie) {}
    }

    public String getStr() {
        return str;
    }

    public String sumStr() {
        int sum = 0;
        try {
            lock.lock();
            String[] info = str.split(" ");
            for (String elem : info) {
                if(Integer.parseInt(elem)%2==0)
                sum += Integer.parseInt(elem);
            }
            lock.unlock();
        }
        catch (InterruptedException ie) {}
        return Integer.toString(sum);
    }

    public String sumStr1() {
        int sum = 0;
        try {
            lock.lock();
            String[] info = str.split(" ");
            for (String elem : info) {
                if(Integer.parseInt(elem)%2!=0)
                    sum += Integer.parseInt(elem);
            }
            lock.unlock();
        }
        catch (InterruptedException ie) {}
        return Integer.toString(sum);
    }
}
class Lock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

