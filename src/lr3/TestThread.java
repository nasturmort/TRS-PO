package lr3;

class TestThread extends Thread {
    String threadName;
    CommonVariable parentClass;
    TestThread( String name, CommonVariable c) {
        threadName = name;
        parentClass = c;
        System.out.println( threadName + " - Created");
    }

    public void run() {
        System.out.println( threadName + " - Start of Work");
        while(parentClass.state != true) {
            System.out.println( threadName + " - " + parentClass.state);
            try {
                Thread.sleep( 10);
            }
            catch (InterruptedException ie) {
            }
        }
        if(parentClass.state == true && threadName=="Second" && parentClass.state1!=true) {
            parentClass.state1=true;
            System.out.println( threadName + " - " + parentClass.state);
            System.out.println( threadName + " - End of Work");
        }
        /*if(parentClass.state == true && threadName=="First" && parentClass.state2!=true) {
            parentClass.state2 = true;
            System.out.println( threadName + " - " + parentClass.state);
            System.out.println( threadName + " - End of Work");
        }*/
        if(parentClass.state1 == true && parentClass.state2 == true && parentClass.state == true && (threadName=="First" || threadName=="Third")) {
            System.out.println( threadName + " - " + parentClass.state);
            System.out.println( threadName + " - End of Work");
        }
    }
}