package my.denispavlov.robofinance.util;

import my.denispavlov.robofinance.domain.Sex;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SexConverter implements AttributeConverter<Sex, String> {
    @Override
    public String convertToDatabaseColumn(Sex attribute) {
        return attribute.toString().toLowerCase();
    }

    @Override
    public Sex convertToEntityAttribute(String dbData) {
        return Sex.valueOf(dbData.toUpperCase());
    }
}