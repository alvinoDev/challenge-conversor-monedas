package com.alvinodev.currencyconverter.main;

import com.alvinodev.currencyconverter.model.ConversionResponse;
import com.alvinodev.currencyconverter.service.ConversionService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Matriz de dos dimensiones
    private static String[][]  conversion_codes = {
            {"USD", "ARS"}, // Opción 1
            {"ARS", "USD"}, // Opción 2
            {"USD", "BRL"}, // Opción 3
            {"BRL", "USD"}, // Opción 4
            {"USD", "COP"}, // Opción 5
            {"COP", "USD"}  // Opción 6
    };

    public static void main(String[] args) {
        ConversionService conversion_service = new ConversionService(); //conversion_service.convertCurrency
        Scanner inputData = new Scanner(System.in);

        int option = -1;
        while (option != 7) {
            String menuMessage = """
                        ╔═════════════════════════════════════════════════════
                        ║              💵 CONVERSOR DE MONEDA 💵                     
                        ╠═════════════════════════════════════════════════════
                        ║ 1] Dólar ==> Peso argentino
                        ║ 2] Peso argentino ==> Dólar
                        ║ 3] Dólar ==> Real brasileño
                        ║ 4] Real brasileño ==> Dólar
                        ║ 5] Dólar ==> Peso colombiano
                        ║ 6] Peso colombiano ==> Dólar
                        ║ 7] Salir
                        ══════════════════════════════════════════════════════
                    """;
            System.out.printf(menuMessage);

            try {

                System.out.print("Elije una opción válida: ");
                option = inputData.nextInt();
                inputData.nextLine();// Para consumir el salto de línea que queda en el buffer

                if (option >= 1 && option <= 6) {

                    System.out.print("Ingrese el monto que desea convertir: ");
                    String amountString = inputData.nextLine();
                    double dataAmount = Double.valueOf(amountString);

                    String baseCode = conversion_codes[option - 1][0];
                    String targetCode = conversion_codes[option - 1][1];

                    ConversionResponse conversionResp = conversion_service.convertCurrency(baseCode, targetCode, dataAmount);

                    if (conversionResp != null) {
                        System.out.printf("\n El valor %.2f [%s] %s%n", dataAmount, baseCode, conversionResp.toString());
                    } else {
                        System.out.println("No se pudo realizar la conversión.");
                    }

                } else if (option == 7) {
                    System.out.println("Saliendo del conversor. ¡Gracias por usarlo!");
                } else {
                    System.out.println("Opción no válida. Por favor, elija un número válido.");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Ingrese un número para seleccionar la opción.");
                inputData.nextLine();
                option = -1;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Ingrese un valor numérico válido para la cantidad a convertir.");
            }
        }
    }
}
