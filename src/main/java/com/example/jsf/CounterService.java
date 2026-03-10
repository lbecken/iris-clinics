package com.example.jsf;

// javarta.*
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

// Singleton
@ApplicationScoped
public class CounterService implements Serializable {

    private final AtomicInteger totalGreetings = new AtomicInteger(0);

    public int incrementAndGet() {
        return totalGreetings.incrementAndGet();
    }

    public int getTotalGreetings() {
        return totalGreetings.get();
    }
}
