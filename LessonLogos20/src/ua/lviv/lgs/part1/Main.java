package ua.lviv.lgs.part1;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String text = "\nEnter  the amount of numbers:";
        int n = readText(text);
        MyThread myThread = new MyThread(1000, n);
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread runnableThread = new Thread(new RunnableThread(1000, n));
        runnableThread.start();
    }

    public static int readText(String text) {
        Scanner scan = new Scanner(System.in);
        System.out.println(text);
        int input = scan.nextInt();
        return input;
    }

    public static synchronized int[] fibonacci(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; ++i) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;

    }
}

class MyThread extends Thread {
    private int delay;
    private int n;

    public MyThread(int delay, int n) {
        super();

        this.delay = delay;
        this.n = n;
    }

    @Override
    public void run() {
        int[] arr = Main.fibonacci(n);
        System.out.print("Потік Thread : ");
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Warm" + currentThread().getName() + " was interrupted ");
            }

        }
    }

}

class RunnableThread implements Runnable {
    private int delay;
    private int n;

    public RunnableThread(int delay, int n) {
        this.delay = delay;
        this.n = n;
    }

    @Override
    public void run() {
        int[] arr = Main.fibonacci(n);
        System.out.print("\nПотік Runnable : ");
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Warm" + Thread.currentThread().getName() + " was interrupted ");
            }
        }
    }
}
