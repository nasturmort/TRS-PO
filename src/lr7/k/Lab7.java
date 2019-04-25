package lr7.k;

import java.io.*;

public class Lab7 {
    public static void main(String argc[]) {
        System.out.println("Main created!");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\lr7\\k\\input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\lr7\\k\\output.txt"));
            Data data = new Data();
            ReadWriteThread threads[] = new ReadWriteThread[2];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new ReadWriteThread(reader, writer, i + 1, data);
                threads[i].start();
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
            reader.close();
            System.out.println("\nReader closed!");
            writer.close();
            System.out.println("Writer closed!");
        } catch (InterruptedException | IOException ex) {}
        System.out.println("End of main!");
    }
}
