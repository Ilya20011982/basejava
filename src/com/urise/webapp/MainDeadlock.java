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
            String thread = Thread.currentThread().getName();
            System.out.println("Waiting " + lock1 + " " + thread);
            synchronized (lock1) {
                System.out.println("Holding " + lock1 + " " + thread);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2 + " " + thread);
                synchronized (lock2) {
                    System.out.println("Holding " + lock2 + " " + thread);
                }
            }
        }).start();
    }
}
