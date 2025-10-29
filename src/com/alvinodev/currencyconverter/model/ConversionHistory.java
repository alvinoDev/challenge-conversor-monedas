package com.alvinodev.currencyconverter.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ConversionHistory(String baseCode, String targetCode, double entryAmount, double convertedAmount, LocalDateTime timestamp) {
    @Override
    public String toString() {

        // Formatear la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = timestamp.format(formatter);

        return String.format(
                "    [%s] %.2f %s => %.2f %s", formattedTime, entryAmount, baseCode, convertedAmount, targetCode
        );
    }
}
