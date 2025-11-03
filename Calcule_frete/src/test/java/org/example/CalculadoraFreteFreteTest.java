
package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class CalculadoraFreteFreteTest {

    // Objeto da classe a ser testada, criado antes de cada teste
    private CalculadoraFrete calculadora;

    // A anotação @BeforeEach garante que este método será executado
    // antes de CADA método de teste (@Test)
    @BeforeEach
    void setUp() {
        // ARRANGE: Inicializa o objeto para o teste
        calculadora = new CalculadoraFrete();
    }

    @Test
    @DisplayName("DEVE_RetornarApenasOCustoFixo_QUANDO_PesoForMenorQue1Kg")
    void DEVE_RetornarApenasOCustoFixo_QUANDO_PesoForMenorQue1Kg() {


        // ACT
        double freteSul = calculadora.calcular(0.1, "Sul");
        double freteSudeste = calculadora.calcular(0.8, "Sudeste");
        double freteCentroOeste = calculadora.calcular(0.75, "Centro-Oeste");
        double freteNorte = calculadora.calcular(0.65, "Norte");

        // PRINT (para ver na tela)

        System.out.println("Frete Sudeste: " + freteSudeste);
        System.out.println("Frete Sul: " + freteSul);
        System.out.println("Frete Centro-Oeste: " + freteCentroOeste);
        System.out.println("Frete Norte: " + freteNorte);

        // ASSERT (para garantir que os valores estão corretos)
        // Supondo que o custo fixo seja 10.0 — ajuste conforme sua lógica

        assertEquals(10.0, freteSudeste, 0.01);
        assertEquals(15.0, freteSul, 0.01);
        assertEquals(20.0, freteCentroOeste, 0.01);
        assertEquals(30.0, freteNorte, 0.01);
    }

    @Test
    @DisplayName("DEVE calcular custo fixo + adicional QUANDO peso for maior que 1Kg (Região Sul)")
    void DEVE_CalcularCustoFixoMaisAdicional_QUANDO_PesoForMaiorQue1Kg_Sul() {

        // ARRANGE — Define o cenário do teste
        double peso = 3.0; // acima de 1 kg
        String regiao = "Sul";

        // Suponha que:
        // custo fixo = 10.0
        // adicional por kg excedente = 5.0 por kg acima de 1
        // Fórmula esperada: 10 + (peso - 1) * 5
        double esperado = 10.0 + (peso - 1) * 5.0; // 10 + 2*5 = 20

        // ACT — Executa o método a ser testado
        double resultado = calculadora.calcular(peso, regiao);

        // PRINT — Exibe o valor no console (útil para debug)
        System.out.println("Resultado para peso " + peso + "kg (" + regiao + "): " + resultado);

        // ASSERT — Valida o resultado
        assertEquals(esperado, resultado, 0.01, "O cálculo do frete não está correto para a região Sul");
    }

    @Test
    @DisplayName("DEVE calcular custo fixo + adicional QUANDO peso for maior que 1Kg (todas as regiões)")
    void DEVE_CalcularCustoFixoMaisAdicional_QUANDO_PesoForMaiorQue1Kg_TodasRegioes() {

        // ARRANGE
        double peso = 3.0; // acima de 1 kg

        // Tabela de custos conforme a regra da CalculadoraFrete
        record Regiao(String nome, double custoFixo, double adicional) {}
        Regiao[] regioes = {
                new Regiao("Sudeste", 10.0, 2.0),
                new Regiao("Sul", 15.0, 2.5),
                new Regiao("Centro-Oeste", 20.0, 3.0),
                new Regiao("Norte", 30.0, 5.0),
                new Regiao("Nordeste", 30.0, 5.0)
        };

        // ACT + ASSERT
        for (Regiao r : regioes) {
            double esperado = r.custoFixo() + (peso - 1) * r.adicional();
            double resultado = calculadora.calcular(peso, r.nome());

            System.out.printf("Região: %-12s | Peso: %.2f kg | Esperado: %.2f | Obtido: %.2f%n",
                    r.nome(), peso, esperado, resultado);

            assertEquals(esperado, resultado, 0.001,
                    "Erro no cálculo do frete para a região " + r.nome());
        }
    }

    @Test
    @DisplayName("DEVE_LancarIllegalArgumentException_QUANDO_PesoForZero")
    void DEVE_LancarIllegalArgumentException_QUANDO_PesoForZero() {
        // ARRANGE
        double pesoInvalido = 0.0;
        String regiaoValida = "Sudeste";

        // ACT & ASSERT: Verifica se o método LANÇA a exceção esperada
        // A sintaxe lambda (->) é usada para embrulhar a chamada do método
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(pesoInvalido, regiaoValida);
        });
    }

    @Test
    @DisplayName("DEVE_LancarIllegalArgumentException_QUANDO_PesoForNegativo")
    void DEVE_LancarIllegalArgumentException_QUANDO_PesoForNegativo() {
        // ARRANGE
        double pesoInvalido = -5.0;
        String regiaoValida = "Sul";

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(pesoInvalido, regiaoValida);
        });
    }

    @Test
    @DisplayName("DEVE_LancarIllegalArgumentException_QUANDO_RegiaoForNula")
    void DEVE_LancarIllegalArgumentException_QUANDO_RegiaoForNula() {
        // ARRANGE
        double pesoValido = 2.0;
        String regiaoNula = null;

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(pesoValido, regiaoNula);
        });
    }

    @Test
    @DisplayName("DEVE_LancarIllegalArgumentException_QUANDO_RegiaoForVazia")
    void DEVE_LancarIllegalArgumentException_QUANDO_RegiaoForVazia() {
        // ARRANGE
        double pesoValido = 2.0;
        String regiaoVazia = ""; // OU "  " (apenas espaços)

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(pesoValido, regiaoVazia);
        });
    }

    }







