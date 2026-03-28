package com.iris.auth;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn;

    // action
    public void login() {
        loggedIn = true;
    }

    public void logout() {
        loggedIn = false;
    }

    public void reset() {
        username = null;
        password = null;
    }

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
