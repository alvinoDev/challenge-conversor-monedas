package com.alvinodev.currencyconverter.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyApiClient {
    // private | solo puede ser accedida y utilizada dentro de la clase donde está declarada.
    // static | variable pertenece a la clase misma, no a una instancia específica de esta clase.
    // final | Este valor no puede cambiarse durante la ejecución del programa.
    private static final String api_key = "606602a0cc81ae5d7ae46ca6";
    private static final String base_url = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String fetchConversionRate(String baseCode, String targetCode, double amount) {
        //
        String url = base_url + api_key + "/pair/" + baseCode + "/" + targetCode + "/" + amount;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("Error al obtener la conversión: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error de solicitud HTTP: " + e.getMessage());
            return null;
        }
    }
}
