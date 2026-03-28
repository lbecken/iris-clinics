package com.iris.staff.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// JPA 3.0 => AttributeConverter
// example: https://www.baeldung.com/jpa-attribute-converters
// specs: https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/attributeconverter
//@Converter(autoApply = true)
public class SpecialtyConverter implements AttributeConverter<Specialty, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Specialty specialty) {
        return specialty.getValue();
    }

    @Override
    public Specialty convertToEntityAttribute(Integer value) {
        return Specialty.fromValue(value);
    }
}