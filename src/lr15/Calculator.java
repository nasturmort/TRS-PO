package lr15;

class Calculator implements Runnable {

    String threadName;
    int op1;
    int op2;
    int res;
    char operator;
    Lab15 lab;

    Calculator(String threadName, int op1, int op2, int res, char operator, Lab15 lab) {
        this.threadName = threadName;
        this.op1 = op1;
        this.op2 = op2;
        this.res = res;
        this.operator = operator;
        this.lab = lab;
        System.out.println(threadName + " created...");
    }

    @Override
    public void run() {
        System.out.println(threadName + " started working...");
        while (!lab.stopFlag) {
            if (lab.flags[op1] && lab.flags[op2]) {
                switch (operator) {
                    case '+':
                        lab.data[res] = lab.data[op1] + lab.data[op2];
                        System.out.println(threadName + ": sum: " + op1 + " & " + op2 + " -> " + res);
                        break;
                    case '-':
                        lab.data[res] = lab.data[op1] - lab.data[op2];
                        System.out.println(threadName + ": sub: " + op1 + " & " + op2 + " -> " + res);
                        break;
                    case '/':
                        lab.data[res] = lab.data[op1] / lab.data[op2];
                        System.out.println(threadName + ": div: " + op1 + " & " + op2 + " -> " + res);
                        break;
                    case '*':
                        lab.data[res] = lab.data[op1] * lab.data[op2];
                        System.out.println(threadName + ": mul: " + op1 + " & " + op2 + " -> " + res);
                        break;
                    default:
                        break;
                }
                lab.flags[op1] = false;
                lab.flags[op2] = false;
                lab.flags[res] = true;
            }
            Thread.yield();
        }
        System.out.println(threadName + " finished.");
    }
}

