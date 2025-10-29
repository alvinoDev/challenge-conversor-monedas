package com.alvinodev.currencyconverter.main;

import com.alvinodev.currencyconverter.model.ConversionHistory;
import com.alvinodev.currencyconverter.model.ConversionResponse;
import com.alvinodev.currencyconverter.service.ConversionService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Matriz de dos dimensiones
    private static String[][]  conversion_codes = {
            {"USD", "ARS"}, // Opción 1
            {"ARS", "USD"}, // Opción 2
            {"ARS", "BOB"}, // Opción 3
            {"BOB", "ARS"}, // Opción 4
            {"USD", "BRL"}, // Opción 5
            {"BRL", "USD"}, // Opción 6
            {"USD", "COP"}, // Opción 7
            {"COP", "USD"}  // Opción 8
    };

    public static void main(String[] args) {
        ConversionService conversion_service = new ConversionService(); //conversion_service.convertCurrency
        Scanner inputData = new Scanner(System.in);

        int option = -1;
        while (option != 9) {
            String menuMessage = """
                        \n
                        ╔═════════════════════════════════════════════════════
                        ║              💵 CONVERSOR DE MONEDA 💵                     
                        ╠═════════════════════════════════════════════════════
                        ║ 1] Dólar ==> Peso argentino
                        ║ 2] Peso argentino ==> Dólar
                        ║ 3] Peso argentino ==> Boliviano boliviano
                        ║ 4] Boliviano boliviano ==> Peso argentino
                        ║ 5] Dólar ==> Real brasileño
                        ║ 6] Real brasileño ==> Dólar
                        ║ 7] Dólar ==> Peso colombiano
                        ║ 8] Peso colombiano ==> Dólar
                        ║ 9] SALIR
                        ╠══════════════════════════════════════════════════════
                        ║ 10] Mostrar Historial de Conversiones
                    \n""";
            System.out.printf(menuMessage);

            try {

                System.out.print("    Elije una opción válida: ");
                option = inputData.nextInt();
                inputData.nextLine();// Para consumir el salto de línea que queda en el buffer

                if (option >= 1 && option <= 8) {

                    System.out.print("    Ingrese el monto que desea convertir: ");
                    String amountString = inputData.nextLine();
                    double dataAmount = Double.valueOf(amountString);

                    String baseCode = conversion_codes[option - 1][0];
                    String targetCode = conversion_codes[option - 1][1];

                    ConversionResponse conversionResp = conversion_service.convertCurrency(baseCode, targetCode, dataAmount);

                    if (conversionResp != null) {
                        System.out.printf("\n    El valor %.2f [%s] %s%n", dataAmount, baseCode, conversionResp.toString());
                    } else {
                        System.out.println("    No se pudo realizar la conversión.");
                    }

                } else if (option == 9) {
                    System.out.println("    Saliendo del conversor. ¡Gracias por usarlo!");
                } else if (option == 10 ) {

                    List<ConversionHistory> history = conversion_service.getHistory();

                    if (history.isEmpty()) {
                        System.out.println("\n    Historial vacío. ¡Realice su primera conversión!");
                    } else {
                        System.out.println("\n    ************** HISTORIAL DE CONVERSIONES **************");
                        // Iterar e imprimir los registros
                        for (ConversionHistory record : history) {
                            System.out.println(record);
                        }
                        System.out.println("    ******************************************************\n");
                    }

                } else {
                    System.out.println("    Opción no válida. Por favor, elija un número válido.");
                }

            } catch (InputMismatchException e) {
                System.out.println("    ERROR: Ingrese un número para seleccionar la opción.");
                inputData.nextLine();
                option = -1;
            } catch (NumberFormatException e) {
                System.out.println("    ERROR: Ingrese un valor numérico válido para la cantidad a convertir.");
            }
        }
    }
}