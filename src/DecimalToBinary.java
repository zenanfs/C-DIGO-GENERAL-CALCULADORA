package com.Calculator.CalculatorFunctions;
import java.util.Scanner;

public class DecimalToBinary {
    // Constante para validación hexadecimal
    private static final String HEX_REGEX = "^[0-9A-F]+(\\.[0-9A-F]+)?$";

    private DecimalToBinary() {
    }

    // ========== CONVERSIÓN DECIMAL A BINARIO ==========
    public static String convertirDecimalABinario(int decimal) {
        validarNumeroPositivo(decimal);
        return convertirManual(decimal);
    }

    private static String convertirManual(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder binario = new StringBuilder();
        while (decimal > 0) {
            int residuo = decimal % 2;
            binario.insert(0, residuo);
            decimal /= 2;
        }
        return binario.toString();
    }

    // ========== CONVERSIÓN DECIMAL A HEXADECIMAL ==========

    // Método 1: Conversión recursiva (más elegante)
    public static String decimalAHexRecursivo(int decimal) {
        validarNumeroPositivo(decimal);
        if (decimal == 0) {
            return "0";
        }
        return divisionRecursiva(decimal);
    }

    private static String divisionRecursiva(int numero) {
        if (numero == 0) {
            return "";
        } else {
            int resto = numero % 16;
            String digitoHexadecimal;
            if (resto < 10) {
                digitoHexadecimal = String.valueOf(resto);
            } else {
                digitoHexadecimal = String.valueOf((char) (65 + resto - 10));
            }
            String resultadoParcial = divisionRecursiva(numero / 16);
            return resultadoParcial + digitoHexadecimal;
        }
    }

    // Método 2: Conversión iterativa (alternativa)
    public static String decimalAHexIterativo(int decimal) {
        validarNumeroPositivo(decimal);
        if (decimal == 0) {
            return "0";
        }

        StringBuilder hexadecimal = new StringBuilder();
        int numero = decimal;

        while (numero > 0) {
            int resto = numero % 16;
            char digitoHex;
            if (resto < 10) {
                digitoHex = (char) ('0' + resto);
            } else {
                digitoHex = (char) ('A' + resto - 10);
            }
            hexadecimal.insert(0, digitoHex);
            numero /= 16;
        }

        return hexadecimal.toString();
    }

    // ========== CONVERSIÓN BINARIO A DECIMAL ==========
    public static double binarioADecimal(String binario) {
        binario = binario.replace(',', '.');
        String[] partes = binario.split("\\.");
        String parteEntera = partes[0];
        String parteFraccionaria = (partes.length > 1) ? partes[1] : "";

        // Convertir parte entera
        int entero = Integer.parseInt(parteEntera, 2);

        // Convertir parte fraccionaria
        double fraccion = 0;
        for (int i = 0; i < parteFraccionaria.length(); i++) {
            char bit = parteFraccionaria.charAt(i);
            if (bit == '1') {
                fraccion += Math.pow(2, -(i + 1));
            } else if (bit != '0') {
                throw new NumberFormatException("Carácter no válido en la parte fraccionaria: " + bit);
            }
        }
        return entero + fraccion;
    }

    // ========== CONVERSIÓN HEXADECIMAL A DECIMAL ==========
    public static double hexFlotanteADecimal(String hexadecimal) {
        try {
            String[] partes = hexadecimal.split("\\.");
            String parteEntera = partes[0];
            String parteFraccionaria = partes.length > 1 ? partes[1] : "0";

            long entero = Long.parseLong(parteEntera, 16);
            double fraccion = 0.0;
            for (int i = 0; i < parteFraccionaria.length(); i++) {
                char hex = parteFraccionaria.charAt(i);
                int digit = Character.digit(hex, 16);
                if (digit == -1) throw new NumberFormatException();
                fraccion += digit / Math.pow(16, i + 1);
            }
            return entero + fraccion;
        } catch (Exception e) {
            throw new NumberFormatException("Error al convertir número hexadecimal flotante.");
        }
    }

    // ========== MÉTODOS AUXILIARES ==========
    private static void validarNumeroPositivo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número no puede ser negativo.");
        }
    }

    // ========== MÉTODO MAIN PARA PRUEBAS ==========
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione conversión:");
        System.out.println("1. Decimal a Hexadecimal");
        System.out.println("2. Hexadecimal a Decimal");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                System.out.print("Ingrese número decimal: ");
                int decimal = scanner.nextInt();
                try {
                    String hexRecursivo = decimalAHexRecursivo(decimal);
                    String hexIterativo = decimalAHexIterativo(decimal);
                    System.out.printf("Hexadecimal (recursivo): %s\n", hexRecursivo);
                    System.out.printf("Hexadecimal (iterativo): %s\n", hexIterativo);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 2:
                System.out.print("Ingrese número hexadecimal: ");
                String hexString = scanner.nextLine().toUpperCase();

                if (!hexString.matches(HEX_REGEX)) {
                    System.out.println("Formato hexadecimal no válido.");
                    break;
                }

                try {
                    double decimalResult = hexFlotanteADecimal(hexString);
                    System.out.printf("El valor decimal de %s es: %f\n", hexString, decimalResult);
                } catch (NumberFormatException e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}