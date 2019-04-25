package lr7.k;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteThread extends Thread {
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
                    String result=data.action();
                    writer.write("txt: ("+ data.getStr() +  ")\n"+ result);
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


    public String action(){
        int count=0;
        Map<Character, Integer> map = new HashMap<>();
        try{
            lock.lock();
            //String data = "aaabbbcccdddffffrss";
            char[] charArray = str.toCharArray();

            for (char c : charArray) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
            //System.out.println(map);
            lock.unlock();
        }
        catch (InterruptedException ie) {}
        return map.toString();
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
