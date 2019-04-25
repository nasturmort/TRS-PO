package lr5.v4;

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

/*
 * Дано рядок лiтер приблизно такого виду "ABCDEFABCDEF:".
 * Написати паралельну програму з двома робочими процесами.
 * Кожен робочий процес пiд час роботи програми повинен звертатися до цього рядка та виконувати у ньому такi замiни:
 * Перший процес додає свiй номер пiсля кожної знайденої лiтери A.
 * Другий процес додає свiй номер пiсля кожної знайденої лiтери B.
 * За одне звернення процес може здiйснити тiльки одне додавання.
 * Процеси повиннi працювати паралельно i незалежно один вiд одного.
 * Програма повинна працювати вiрно навiть якщо пiд час виконання операцiї додавання ввести випадкову часову затримку.
 */
public class Lab5 {

    private Lock lock = new Lock();
    public int positionOne = 0;
    public int positionTwo = 0;
    public StringBuilder buffer = new StringBuilder("ABCDEFABCDEF:");
    private int newCountOne;
    private int newCountTwo;

    public boolean firstEnded = true;
    public boolean secondEnded = true;

    public int inc(int id) {
        try {
            lock.lock();
            if (id == 1) {
                newCountOne = ++positionOne;
            } else if (id == 2) {
                newCountTwo = ++positionTwo;
            }
            lock.unlock();
        } catch (InterruptedException ie) {
        }
        return id == 1 ? newCountOne : newCountTwo;
    }

    public String makeChanges(int id) {
        try {
            lock.lock();
            firstEnded = positionOne < buffer.length();
            secondEnded = positionTwo < buffer.length();
            if (firstEnded && id == 1 && buffer.charAt(positionOne) == 'A') {
                buffer.insert(positionOne + 1, "1");
            } else if (secondEnded && id == 2 && buffer.charAt(positionTwo) == 'B') {
                buffer.insert(positionTwo + 1, "2");
            }
            lock.unlock();
        } catch (InterruptedException ie) {
        }
        return buffer.toString();
    }

    public static void main(String argc[]) {
        System.out.println("Main process started");
        Lab5 cnt = new Lab5();
        TestThread t[] = new TestThread[2];
        for (int i = 0; i < t.length; ++i) {
            t[i] = new TestThread("Proc(" + (i + 1) + "):", i + 1, cnt);
            t[i].start();
        }
        System.out.println("\nMain process ended");
    }
}