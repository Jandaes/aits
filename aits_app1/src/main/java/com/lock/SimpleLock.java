package com.lock;

public class SimpleLock {


    public static void main(String[] args) {
        Th1 th1=new Th1();
       Thread th=new Thread(th1);
        Thread th2=new Thread(th1);
       th.start();
       th2.start();
    }
}

class Th1 implements Runnable{
    public  int number = 10000;
    @Override
    public void run() {
        for (int i = 0; i <5000 ; i++) {
            System.out.println("当前线程为："+Thread.currentThread().getName()+"  当前number:" +number--);
            System.out.println("1");
        }

    }
}