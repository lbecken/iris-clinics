package com.iris.staff.model;

public enum Specialty {
    OPHTALMOLOGIST(1);

    private final Integer value;

    Specialty(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Specialty fromValue(Integer value) {
        for (Specialty specialty : Specialty.values()) {
            if (specialty.value.intValue() == value.intValue()) {
                return specialty;
            }
        }
        throw new IllegalArgumentException("Invalid specialty value: " + value);
    }
}
