package org.example;

public class CalculadoraFrete {

    // 1. O método principal deve receber os parâmetros conforme o requisito
    public double calcular(double pesoKg, String regiao) {



        if (pesoKg <= 0) {
            throw new IllegalArgumentException("O peso (em Kg) não pode ser menor ou igual a zero.");
        }

        // Validação da região
        if (regiao == null || regiao.trim().isEmpty()) {
            throw new IllegalArgumentException("A região não pode ser nula ou vazia.");
        }

        //  Lógica de Cálculo (Separando em Custo Fixo e Custo por Kg Adicional)

        double custoFixo;
        double valorPorKgAdicional;

        // Usamos switch/case para clareza
        switch (regiao) {
            case "Sudeste":
                custoFixo = 10.00;
                valorPorKgAdicional = 2.00;
                break;
            case "Sul":
                custoFixo = 15.00;
                valorPorKgAdicional = 2.50;
                break;
            case "Centro-Oeste":
                custoFixo = 20.00;
                valorPorKgAdicional = 3.00;
                break;
            default: // Outras Regiões
                custoFixo = 30.00;
                valorPorKgAdicional = 5.00;
                break;
        }

        // Regra: Custo por quilo adicional acima de 1 kg.
        // Pacotes com 1 kg ou menos têm apenas o custo fixo (custoAdicional = 0).
        double pesoAdicional = Math.max(0, pesoKg - 1);

        double custoAdicional = pesoAdicional * valorPorKgAdicional;

        return custoFixo + custoAdicional;
    }


}