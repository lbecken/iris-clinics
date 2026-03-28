package com.iris.appointment.model;

import jakarta.persistence.AttributeConverter;

public class AppointmentStatusConverter implements AttributeConverter<AppointmentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AppointmentStatus status) {
        return status.getValue();
    }

    @Override
    public AppointmentStatus convertToEntityAttribute(Integer value) {
        return AppointmentStatus.fromValue(value);
    }
}
