package com.iris.appointment.model;

public enum AppointmentStatus {
    NotStarted(1),
    OnGoing(2),
    Canceled(3);

    private final Integer value;

    AppointmentStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static AppointmentStatus fromValue(Integer value) {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
