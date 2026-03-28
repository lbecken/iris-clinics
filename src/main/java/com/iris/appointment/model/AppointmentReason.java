package com.iris.appointment.model;

public enum AppointmentReason {
    Consultation(1);

    private final Integer value;

    AppointmentReason(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static AppointmentReason fromValue(Integer value) {
        for (AppointmentReason reason: AppointmentReason.values()) {
            if (reason.getValue().equals(value)) {
                return reason;
            }
        }
        throw new IllegalArgumentException("Invalid reason value: " + value);
    }
}
