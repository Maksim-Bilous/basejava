package com.urise.webapp.model;

public class DeadLock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void deadLockFinal(Object lock1 , Object lock2) {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " Захватил " + lock1 );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        synchronized (lock2){
            System.out.println(Thread.currentThread().getName() + " Захватил " + lock2 );
        }
    }
    
}
