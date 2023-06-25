package main.java.com.Tretyak_Marina.javacore.chapter11;

import java.util.concurrent.CompletableFuture;
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
public class Main {
    public static void main(String [] args) {
        Foo foo = new Foo();


        CompletableFuture.runAsync(() -> {
            try {
                foo.second(new Thread());
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                foo.first(new Thread());
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                foo.third(new Thread());
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
    }
}
