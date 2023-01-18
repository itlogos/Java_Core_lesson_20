package ua.lviv.lgs.part1;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainMax {

    public static void main(String[] args) throws InterruptedException {
        String text = "\nEnter  the amount of numbers:";
        int n = readText(text);
        runWithExecutors(1000, n);
        runScheduledExecutor(1000, n);
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

    public static void runWithExecutors(int delay, int n) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int[] arr = Main.fibonacci(n);

                System.out.print("Потік Runnable : ");
                for (int i = n - 1; i >= 0; i--) {
                    System.out.print(arr[i] + " ");
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        System.out.println("Warm" + Thread.currentThread().getName() + " was interrupted ");
                    }
                }
            }
        });

        executorService.shutdown();
    }

    public static void runScheduledExecutor(int delay, int n) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                int[] arr = Main.fibonacci(n);
                System.out.print("\nПотік Thread : ");
                for (int i = 0; i < n; ++i) {
                    System.out.print(arr[i] + " ");
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        System.out.println("Warm" + Thread.currentThread().getName() + " was interrupted ");
                    }
                }
            }
        } , n+1, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }

}
