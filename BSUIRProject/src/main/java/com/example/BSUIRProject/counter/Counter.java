package com.example.BSUIRProject.counter;

import org.springframework.stereotype.Component;

@Component
public class Counter {
    private static int counter = 0;

    public static synchronized void increment() throws InterruptedException {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }
}

