package com.iris.appointment.model;

import jakarta.persistence.AttributeConverter;

public class AppointmentReasonConverter implements AttributeConverter<AppointmentReason, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AppointmentReason reason) {
        return reason.getValue();
    }

    @Override
    public AppointmentReason convertToEntityAttribute(Integer value) {
        return AppointmentReason.fromValue(value);
    }
}
