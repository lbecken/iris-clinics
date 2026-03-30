package com.iris.user.model;

public enum UserRole {
    ADMIN(1),
    SECRETARY(2),
    DOCTOR(3);

    private final Integer code;

    UserRole(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static UserRole fromCode(Integer code) {
        for (UserRole role : values()) {
            if (role.code.intValue() ==  code.intValue()) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid user role code: " + code);
    }
}
