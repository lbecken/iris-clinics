package com.iris.patient.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// JPA 3.0 => AttributeConverter
// example: https://www.baeldung.com/jpa-attribute-converters
// specs: https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/attributeconverter
//@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(String code) {
        return Gender.fromCode(code);
    }
}
