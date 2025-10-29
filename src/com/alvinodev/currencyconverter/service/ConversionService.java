package com.alvinodev.currencyconverter.service;

import com.alvinodev.currencyconverter.model.ConversionHistory;
import com.alvinodev.currencyconverter.model.ConversionResponse;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversionService {
    private final CurrencyApiClient apiClient = new CurrencyApiClient();
    private final List<ConversionHistory> history = new ArrayList<>();

    public ConversionResponse convertCurrency(String baseCode, String targetCode, double amount) {
        String jsonResponse = apiClient.fetchConversionRate(baseCode, targetCode, amount);

        if (jsonResponse != null) {
            try {
                Gson gson = new Gson();
                ConversionResponse response = gson.fromJson(jsonResponse, ConversionResponse.class);

                // Crear el Registro con Marca de Tiempo
                ConversionHistory record = new ConversionHistory(baseCode, targetCode, amount, response.conversion_result(), LocalDateTime.now());
                history.add(record); // Almacenar en el Historial

                return response;
            } catch (Exception e) {
                System.err.println("Error al analizar JSON: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public List<ConversionHistory> getHistory() {
        return Collections.unmodifiableList(history);// Devolver una copia inmutable (unmodifiable list)
    }
}
