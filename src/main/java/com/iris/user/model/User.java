package com.iris.user.model;

public class User {
    private Long id;
    private String username;
    private String password; // hash
    private Boolean active;
    private Role role;
}
