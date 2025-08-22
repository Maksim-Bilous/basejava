package com.urise.webapp.model;

public class DeadLock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void deadLock1 () {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " захватил lock1 в deadlock1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " захватил lock2 в deadlock1");
            }
        }
    }

    public void deadLock2 () {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " захватил lock2 в deadlock2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " захватил lock1 в deadlock2");
            }
        }
    }
    
}
