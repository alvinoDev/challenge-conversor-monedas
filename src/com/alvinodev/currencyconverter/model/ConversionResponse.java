package com.alvinodev.currencyconverter.model;

public record ConversionResponse(String base_code, String target_code, double conversion_result) {
    @Override
    public String toString() {
        // Formato para mostrar el resultado: "corresponde al valor final de =>> 20293.75 [ARS]"
        return String.format("corresponde al valor final de =>> %.2f [%s]", conversion_result, target_code);
    }
}
