package batalhanaval;

import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //Inicializacao do vetor de objetos Navio
        Navio[] navios = Tabuleiro.prepararNavios();

        //Preparacao do tabuleiro principal
        char[][] matrizA = Tabuleiro.prepararTabuleiro(navios);

        //Inicializacao do tabuleiro visivel para o jogador
        char[][] matrizB = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrizB[i][j] = ' ';
            }
        }

        //Inicializacao da matriz de controle das casas atacadas
        boolean[][] coordenadasAtacadas = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                coordenadasAtacadas[i][j] = false;
            }
        }

        int jogadas = 0;

        String input;
        String[] coordenadas = new String[2];
        int linha;
        int coluna;

        boolean ataqueValido;
        
        int naviosAfundados = 0;
        
        System.out.println("Batalha Naval");
        System.out.println();
        System.out.println("Como jogar:");
        System.out.println("Escolha uma coordenada para atacar utilizando o formato 'Linha Coluna'");
        System.out.println();

        while (naviosAfundados < 15) {
            jogadas++;
            linha = -1;
            coluna = -1;
            ataqueValido = false;

            Tabuleiro.mostrarTabuleiro(matrizB);

            while (ataqueValido == false) {
                System.out.print("Informe a linha e a coluna para ataque: ");

                try {
                    //Recebe uma String com a linha e a coluna a ser atacada
                    input = scan.nextLine();
                    //Divide a informacao da linha e da coluna em um vetor
                    coordenadas = input.split(" ");
                    //Transforma a String com o numero da linha em um numero inteiro
                    linha = Integer.parseInt(coordenadas[0]) - 1;
                    //Transforma a String com o numero da coluna em um numero inteiro
                    coluna = Integer.parseInt(coordenadas[1]) - 1;
                    //Verifica se o ataque e valido
                    ataqueValido = Ataque.ataqueValido(coordenadasAtacadas, linha, coluna);

                    if (ataqueValido == false) {
                        System.out.println("Voce ja atacou essa coordenada");
                        System.out.println();
                    }

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Digite apenas numeros inteiros entre 1 e 10 para a linha e coluna do ataque. (linha coluna)");
                    System.out.println();
                }
            }

            //Ataque na posicao selecionada
            Ataque.atacarCoordenadas(matrizA, matrizB, navios, linha, coluna);

            //Atualiza a quantidade de navios afundados
            naviosAfundados = Ataque.naviosAfundados;

        }

        System.out.println();
        System.out.println("Fim de jogo!");
        System.out.println("Numero total de tiros = " + jogadas);
        System.out.println("Tabuleiro final: ");

        Tabuleiro.mostrarTabuleiro(matrizB);
    }
}
