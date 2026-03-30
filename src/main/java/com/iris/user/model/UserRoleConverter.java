package com.iris.user.model;

import jakarta.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        return userRole.getCode();
    }

    @Override
    public UserRole convertToEntityAttribute(Integer code) {
        return UserRole.fromCode(code);
    }
}
