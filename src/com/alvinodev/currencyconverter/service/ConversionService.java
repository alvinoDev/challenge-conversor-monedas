package com.alvinodev.currencyconverter.service;

import com.alvinodev.currencyconverter.model.ConversionResponse;
import com.google.gson.Gson;

public class ConversionService {
    private final CurrencyApiClient apiClient = new CurrencyApiClient();

    public ConversionResponse convertCurrency(String baseCode, String targetCode, double amount) {
        String jsonResponse = apiClient.fetchConversionRate(baseCode, targetCode, amount);
        if (jsonResponse != null) {
            try {
                return new Gson().fromJson(jsonResponse, ConversionResponse.class);
            } catch (Exception e) {
                System.err.println("Error al analizar JSON: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    // Métodos para el historial (Extra) irían aquí
}
