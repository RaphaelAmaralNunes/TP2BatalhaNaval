
import java.util.Random;

public class Utilitarios {

    public static char[][] prepararTabuleiro() {
        Random gerador = new Random();

        int linha = 0;
        int coluna = 0;

        boolean indiceValido;

        char[][] tabuleiro = new char[10][10];

        //preenche inicialmente o tabuleiro com o char '0';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = '0';
            }
        }

        //Preenchimento dos Submarinos;
        for (int i = 0; i < 4; i++) {
            indiceValido = false;
            while (indiceValido == false) {
                linha = gerador.nextInt(10);
                coluna = gerador.nextInt(10);

                indiceValido = indiceValido(tabuleiro, linha, coluna);
            }
            tabuleiro[linha][coluna] = 'S';
        }

        //Preenchimento dos Hidroaviões;
        for (int i = 0; i < 5; i++) {
            indiceValido = false;
            while (indiceValido == false) {
                linha = gerador.nextInt(10);
                coluna = gerador.nextInt(10);

                indiceValido = indiceValido(tabuleiro, linha, coluna);
            }
            tabuleiro[linha][coluna] = 'A';
        }

        //Verifica as posicoes que nao possuem armas e as preenche com o char '.';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == '0') {
                    tabuleiro[i][j] = '.';
                }
            }
        }

        return tabuleiro;
    }

    public static boolean indiceValido(char[][] tabuleiro, int linha, int coluna) {
        boolean indiceValido;

        if (tabuleiro[linha][coluna] == '0') {
            if (linha == 0 && coluna == 0) {
                if (tabuleiro[linha + 1][coluna] == '0' && tabuleiro[linha][coluna + 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 9 && coluna == 9) {
                if (tabuleiro[linha - 1][coluna] == '0' && tabuleiro[linha][coluna - 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 0 && coluna == 9) {
                if (tabuleiro[linha + 1][coluna] == '0' && tabuleiro[linha][coluna - 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 9 && coluna == 0) {
                if (tabuleiro[linha - 1][coluna] == '0' && tabuleiro[linha][coluna + 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 0) {
                if (tabuleiro[linha + 1][coluna] == '0' && tabuleiro[linha][coluna + 1] == '0' && tabuleiro[linha][coluna - 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (coluna == 0) {
                if (tabuleiro[linha + 1][coluna] == '0' && tabuleiro[linha - 1][coluna] == '0' && tabuleiro[linha][coluna + 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 9) {
                if (tabuleiro[linha - 1][coluna] == '0' && tabuleiro[linha][coluna + 1] == '0' && tabuleiro[linha][coluna - 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (coluna == 9) {
                if (tabuleiro[linha + 1][coluna] == '0' && tabuleiro[linha - 1][coluna] == '0' && tabuleiro[linha][coluna - 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else {
                if (tabuleiro[linha + 1][coluna] == '0' && tabuleiro[linha - 1][coluna] == '0' && tabuleiro[linha][coluna + 1] == '0' && tabuleiro[linha][coluna - 1] == '0') {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            }
        } else {
            indiceValido = false;
        }

        return indiceValido;
    }

    public static void mostarTabuleiro(char[][] tabuleiro) {
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}
