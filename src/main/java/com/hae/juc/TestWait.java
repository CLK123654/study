package com.hae.juc;

public class TestWait {

    private static volatile boolean timeToNum = true;
    private static int num = 1;
    private static char letter = 'a';

    public static void main(String[] args) {
        TestWait testWait = new TestWait();
        PrintCharTh printCharTh = new PrintCharTh(testWait);
        PrintNumTh printNumTh = new PrintNumTh(testWait);
        new Thread(printCharTh).start();
        new Thread(printNumTh).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void  printNum() {
        while (true) {
            if (num > 52 ) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (timeToNum) {
                    System.out.println(num++);
                    System.out.println(num++);
                    timeToNum = false;
                    //notify();
                } else {
                   // wait();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }

        }
    }

    public void printChar() {
        while (true) {
            if (letter > 'z') {
                return;
            }
            try {
                if (!timeToNum) {
                    System.out.println(letter++);
                    timeToNum = true;
                   // notify();
                } else {
                    //wait();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public static class PrintNumTh implements  Runnable {
        private TestWait testWait;

        public PrintNumTh(TestWait testWait) {
            this.testWait = testWait;
        }

        @Override
        public void run() {
            testWait.printNum();
        }
    }

    public static class PrintCharTh implements  Runnable {
        private TestWait testWait;

        public PrintCharTh(TestWait testWait) {
            this.testWait = testWait;
        }

        @Override
        public void run() {
            testWait.printChar();
        }
    }
}
