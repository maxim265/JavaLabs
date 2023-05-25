package com.example.BSUIRProject.counter;

import org.springframework.stereotype.Component;

@Component
public class CounterThread extends Thread{

    @Override
    public void start() {
        try {
            Counter.increment();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
