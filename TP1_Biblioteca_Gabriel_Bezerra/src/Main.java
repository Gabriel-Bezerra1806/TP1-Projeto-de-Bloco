import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dados temporários para exercitar lógica; ainda não há cadastro persistente.
        int totalExemplares = 5;
        int exemplaresEmprestados = 2;
        int emprestimosAtivosDoMembro = 1;
        final int LIMITE_EMPRESTIMOS = 3;
        final int PRAZO_DIAS = 14;
        final double MULTA_POR_DIA = 2.00; // Valor ilustrativo, confirmar com o professor.

        boolean executando = true;

        System.out.println("=== Biblioteca Universitária - Protótipo TP1 ===");

        while (executando) {
            System.out.println("\n1 - Consultar disponibilidade");
            System.out.println("2 - Simular empréstimo");
            System.out.println("3 - Simular devolução e multa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    int disponiveis = totalExemplares - exemplaresEmprestados;
                    System.out.println("Exemplares disponíveis: " + disponiveis);
                    break;

                case 2:
                    if (emprestimosAtivosDoMembro >= LIMITE_EMPRESTIMOS) {
                        System.out.println("Empréstimo não permitido: limite de 3 ativos atingido.");
                    } else if (exemplaresEmprestados >= totalExemplares) {
                        System.out.println("Empréstimo não permitido: não há exemplar disponível.");
                    } else {
                        exemplaresEmprestados++;
                        emprestimosAtivosDoMembro++;
                        System.out.println("Empréstimo simulado com sucesso.");
                        System.out.println("Prazo padrão: " + PRAZO_DIAS + " dias.");
                    }
                    break;

                case 3:
                    System.out.print("Informe quantos dias após o vencimento ocorreu a devolução: ");
                    int diasAtraso = scanner.nextInt();

                    if (diasAtraso < 0) {
                        System.out.println("Quantidade inválida. Informe zero ou um número positivo.");
                    } else {
                        double multa = diasAtraso * MULTA_POR_DIA;
                        System.out.printf("Dias de atraso: %d%n", diasAtraso);
                        System.out.printf("Multa ilustrativa: R$ %.2f%n", multa);

                        if (diasAtraso == 0) {
                            System.out.println("Devolução dentro do prazo.");
                        } else {
                            System.out.println("Devolução em atraso.");
                        }
                    }
                    break;

                case 0:
                    executando = false;
                    System.out.println("Encerrando o protótipo. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
