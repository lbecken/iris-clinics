package com.iris.jsf;

// jakarta.*
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named
@ViewScoped
public class HelloBean implements Serializable {

    private String name = "World";
    private String message = "";
    private int localClicks = 0;

    // CDI (Java EE)
    @Inject
    private CounterService counterService;

    // action
    public void greet() {
        localClicks++;
        int total = counterService.incrementAndGet();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        message = "Hello, " + name + "! Local clicks in this view: " + localClicks
                + " | Application total: " + total
                + " | Generated at: " + time;
    }

    // action
    public String reset() {
        name = "World";
        message = "";
        localClicks = 0;
        return null;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public int getLocalClicks() {
        return localClicks;
    }

    public int getApplicationClicks() {
        return counterService.getTotalGreetings();
    }
}
