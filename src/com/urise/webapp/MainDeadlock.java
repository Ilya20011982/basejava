package com.urise.webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        final String lock1 = "lock1";
        final String lock2 = "lock2";
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);

    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            Thread thread = Thread.currentThread();
            System.out.println("Waiting " + lock1 + " " + thread.getName());
            synchronized (lock1) {
                System.out.println("Holding " + lock1 + " " + thread.getName());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2 + " " + thread.getName());
                synchronized (lock2) {
                    System.out.println("Holding " + lock2 + " " + thread.getName());
                }
            }
        }).start();
    }
}
