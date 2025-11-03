package org.example;

public class Main {
    public static void main(String[] args) {

        // 1. Instanciar a classe CalculadoraFrete
        // A palavra 'calculadora' é o nome do seu objeto, o 'Main' anterior estava usando 'calculadora =' sem o tipo.
        CalculadoraFrete calculadora = new CalculadoraFrete();

        System.out.println("--- Testes de Cálculo de Frete ---");

        // --- Teste 1: Sudeste (10,00 fixo + 2,00/kg adicional) ---
        try {
            double freteSudeste = calculadora.calcular(3.0, "Sudeste"); // 3.0 kg (2.0 adicionais)
            // Esperado: 10.00 + (2.0 * 2.00) = 14.00
            System.out.printf("Frete Sudeste (3.0 kg): R$ %.2f\n", freteSudeste);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro no cálculo do Sudeste: " + e.getMessage());
        }

        // --- Teste 2: Sul (Caso de Limite: 1 kg ou menos, apenas custo fixo) ---
        try {
            double freteSulLimite = calculadora.calcular(0.5, "Sul");
            // Esperado: 15.00 (custo fixo, 0 kg adicionais)
            System.out.printf("Frete Sul (0.5 kg - limite): R$ %.2f\n", freteSulLimite);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro no cálculo do Sul (limite): " + e.getMessage());
        }

        // --- Teste 3: Outras Regiões (Regra 'default') ---
        try {
            double freteOutras = calculadora.calcular(10.0, "Norte");
            // Esperado: 30.00 + (9.0 * 5.00) = 75.00
            System.out.printf("Frete Outras Regiões (10.0 kg): R$ %.2f\n", freteOutras);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro no cálculo do Norte: " + e.getMessage());
        }

        System.out.println("\n--- Testes de Exceção ---");

        // --- Teste 4: Exceção de Peso Negativo ---
        try {
            System.out.println("Tentando calcular com peso negativo...");
            calculadora.calcular(-2.0, "Sudeste");
        } catch (IllegalArgumentException e) {
            // Este bloco captura a exceção que você configurou no método calcular
            System.err.println("SUCESSO na exceção: " + e.getMessage());
        }

        // --- Teste 5: Exceção de Região Nula ---
        try {
            System.out.println("Tentando calcular com região nula...");
            calculadora.calcular(5.0, null);
        } catch (IllegalArgumentException e) {
            System.err.println("SUCESSO na exceção: " + e.getMessage());
        }
    }
}