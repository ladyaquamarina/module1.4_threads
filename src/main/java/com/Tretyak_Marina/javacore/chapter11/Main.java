package main.java.com.Tretyak_Marina.javacore.chapter11;

import java.util.concurrent.Semaphore;

class Foo {
    Semaphore semaphore2, semaphore3;

    public Foo() {
        semaphore2 = new Semaphore(0);
        semaphore3 = new Semaphore(0);
    }
    public void first(Runnable r) throws InterruptedException {
        System.out.print("first");
        semaphore2.release();
    }
    public void second(Runnable r) throws InterruptedException {
        semaphore2.acquire();
        System.out.print("second");
        semaphore3.release();
    }
    public void third(Runnable r) throws InterruptedException {
        semaphore3.acquire();
        System.out.print("third");
    }
}
class FirstThread implements Runnable {
    Foo foo;
    FirstThread (Foo f) {
        foo = f;
    }
    public void run() {
        try {
            foo.first(this);
        } catch (InterruptedException e) {
            System.out.println("Первый поток прерван");
        }
    }
}
class SecondThread implements Runnable {
    Foo foo;
    SecondThread (Foo f) {
        foo = f;
    }
    public void run() {
        try {
            foo.second(this);
        } catch (InterruptedException e) {
            System.out.println("Второй поток прерван");
        }
    }
}
class ThirdThread implements Runnable {
    Foo foo;
    ThirdThread(Foo f) {
        foo = f;
    }
    public void run() {
        try {
            foo.third(this);
        } catch (InterruptedException e) {
            System.out.println("Третий поток прерван");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Foo f = new Foo();
        Thread A = new Thread(new FirstThread(f));
        Thread B = new Thread(new SecondThread(f));
        Thread C = new Thread(new ThirdThread(f));

        C.start();
        B.start();
        A.start();
    }
}
