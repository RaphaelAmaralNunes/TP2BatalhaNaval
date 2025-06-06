
import java.util.Random;

public class Tabuleiro {
    
    final static char VAZIO = '0';

    public static char[][] prepararTabuleiro() {
        Random gerador = new Random();
        int tentativas = 0;

        int linha = 0;
        int coluna = 0;
        int orientacao = 0;

        boolean indiceValido;
        boolean espacoValido;

        char[][] tabuleiro = new char[10][10];

        //preenche inicialmente o tabuleiro com o char '0';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }

        //Preenchimento do Porta-avioes;
        orientacao = gerador.nextInt(2);
        if (orientacao == 0) {
            linha = gerador.nextInt(10);
            coluna = gerador.nextInt(7);
            for (int i = 0; i < 4; i++) {
                tabuleiro[linha][coluna + i] = 'P';
            }
        } else {
            linha = gerador.nextInt(7);
            coluna = gerador.nextInt(10);
            for (int i = 0; i < 4; i++) {
                tabuleiro[linha + i][coluna] = 'P';
            }
        }

        //Preenchimento dos Encouracados;
        for (int i = 0; i < 2; i++) {
            espacoValido = false;
            while (espacoValido == false) {
                indiceValido = false;
                while (indiceValido == false) {
                    orientacao = gerador.nextInt(2);
                    if (orientacao == 0) {
                        linha = gerador.nextInt(10);
                        coluna = gerador.nextInt(8);
                    } else {
                        linha = gerador.nextInt(8);
                        coluna = gerador.nextInt(10);
                    }
                    indiceValido = indiceValido(tabuleiro, linha, coluna);
                }
                if (orientacao == 0) {
                    for (int j = 1; j < 3; j++) {
                        indiceValido = indiceValido(tabuleiro, linha, coluna + j);
                        if (indiceValido == false) {
                            j = 3;
                            espacoValido = false;
                        } else {
                            espacoValido = true;
                        }
                    }
                } else {
                    for (int j = 1; j < 3; j++) {
                        indiceValido = indiceValido(tabuleiro, linha + j, coluna);
                        if (indiceValido == false) {
                            j = 3;
                            espacoValido = false;
                        } else {
                            espacoValido = true;
                        }
                    }
                }
            }
            if (orientacao == 0) {
                for (int j = 0; j < 3; j++) {
                    tabuleiro[linha][coluna + j] = 'E';
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    tabuleiro[linha + j][coluna] = 'E';
                }
            }
        }
        
        //Preenchimento dos Cruzadores;
        for (int i = 0; i < 3; i++) {
            espacoValido = false;
            while (espacoValido == false) {
                indiceValido = false;
                while (indiceValido == false) {
                    orientacao = gerador.nextInt(2);
                    if (orientacao == 0) {
                        linha = gerador.nextInt(10);
                        coluna = gerador.nextInt(9);
                    } else {
                        linha = gerador.nextInt(9);
                        coluna = gerador.nextInt(10);
                    }
                    indiceValido = indiceValido(tabuleiro, linha, coluna);
                }
                if (orientacao == 0) {
                    indiceValido = indiceValido(tabuleiro, linha, coluna + 1);
                    if (indiceValido == false) {
                        espacoValido = false;
                    } else {
                        espacoValido = true;
                    }
                } else {
                    indiceValido = indiceValido(tabuleiro, linha + 1, coluna);
                    if (indiceValido == false) {
                        espacoValido = false;
                    } else {
                        espacoValido = true;
                    }
                }
            }
            if (orientacao == 0) {
                for (int j = 0; j < 2; j++) {
                    tabuleiro[linha][coluna + j] = 'C';
                }
            } else {
                for (int j = 0; j < 2; j++) {
                    tabuleiro[linha + j][coluna] = 'C';
                }
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
                if (tabuleiro[i][j] == VAZIO) {
                    tabuleiro[i][j] = '.';
                }
            }
        }

        return tabuleiro;
    }

    public static boolean indiceValido(char[][] tabuleiro, int linha, int coluna) {
        boolean indiceValido;

        if (tabuleiro[linha][coluna] == VAZIO) {
            if (linha == 0 && coluna == 0) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 9 && coluna == 9) {
                if (tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 0 && coluna == 9) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 9 && coluna == 0) {
                if (tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 0) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (coluna == 0) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (linha == 9) {
                if (tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else if (coluna == 9) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceValido = true;
                } else {
                    indiceValido = false;
                }
            } else {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
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

    public static void mostrarTabuleiro(char[][] tabuleiro) {
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
