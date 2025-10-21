package com.Calculator;
import com.Calculator.CalculatorFunctions.DecimalToBinary;
import java.util.Scanner;

public class Conversiontool{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=============================================");
            System.out.println("           CALCULADORA DE CONVERSIÓN         ");
            System.out.println("=============================================");
            System.out.println("1. Convertir decimal a binario");
            System.out.println("2. Convertir decimal a hexadecimal");
            System.out.println("3. Convertir binario a decimal");
            System.out.println("4. Convertir hexadecimal a decimal");
            System.out.println("5. Salir");
            System.out.println("=============================================");
            System.out.print("Seleccione una opción: ");

            // Validar opción del menú
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor ingrese un número válido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Decimal a Binario
                    System.out.print("Ingrese un número decimal: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Entrada no válida. Ingrese un número entero: ");
                        scanner.next();
                    }
                    int numeroDecimal = scanner.nextInt();
                    try {
                        String resultadoBinario = DecimalToBinary.convertirDecimalABinario(numeroDecimal);
                        System.out.printf("El número binario es: %s\n", resultadoBinario);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Decimal a Hexadecimal
                    System.out.print("Ingrese un número decimal: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Entrada no válida. Ingrese un número entero: ");
                        scanner.next();
                    }
                    int numeroHex = scanner.nextInt();
                    try {
                        String resultadoHex = DecimalToBinary.decimalAHexRecursivo(numeroHex);
                        System.out.printf("El número hexadecimal es: %s\n", resultadoHex);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Binario a Decimal
                    System.out.print("Ingrese un número binario: ");
                    scanner.nextLine(); // Limpiar buffer
                    String binario = scanner.nextLine();
                    try {
                        double resultadoDecimal = DecimalToBinary.binarioADecimal(binario);
                        System.out.printf("El número decimal es: %.2f\n", resultadoDecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Hexadecimal a Decimal
                    System.out.print("Ingrese un número hexadecimal: ");
                    scanner.nextLine(); // Limpiar buffer
                    String hexadecimal = scanner.nextLine().toUpperCase();
                    try {
                        double resultadoHexADecimal = DecimalToBinary.hexFlotanteADecimal(hexadecimal);
                        System.out.printf("El número decimal es: %.6f\n", resultadoHexADecimal);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

            // Pausa antes de mostrar el menú nuevamente (solo si no es salir)
            if (opcion != 5) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine(); // Consumir el newline pendiente
                scanner.nextLine(); // Esperar Enter
            }

        } while (opcion != 5);

        scanner.close();
    }
}