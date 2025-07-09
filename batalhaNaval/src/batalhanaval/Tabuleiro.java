package batalhanaval;

import java.util.Random;

public class Tabuleiro {

    final static char VAZIO = '0';

    public static char[][] prepararTabuleiro(Navio[] navios) {

        Random gerador = new Random();

        int count = 0;
        boolean indiceDisponivel;
        boolean espacoDisponivel;

        char[][] tabuleiro = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }

        //Porta-Aviao
        navios[count].simbolo = 'P';
        navios[count].tamanho = 4;
        navios[count].vida = navios[count].tamanho;
        
        //Gera aleatoriamente a orientacao do navio
        navios[count].orientacao = gerador.nextInt(2);

        //Gera uma posicao aleatoria para o comeco do Navio
        if (navios[count].orientacao == 0) {
            navios[count].linha = new int[1];
            navios[count].coluna = new int[navios[count].tamanho];
            navios[count].linha[0] = gerador.nextInt(10);
            navios[count].coluna[0] = gerador.nextInt(11 - navios[count].tamanho);

            //Prenche o vetor de posicoes do navio (linha ou coluna, depende da orientacao)
            for (int i = 1; i < navios[count].tamanho; i++) {
                navios[count].coluna[i] = navios[count].coluna[i - 1] + 1;
            }

        } else {
            navios[count].linha = new int[navios[count].tamanho];
            navios[count].coluna = new int[1];
            navios[count].linha[0] = gerador.nextInt(11 - navios[count].tamanho);
            navios[count].coluna[0] = gerador.nextInt(10);

            for (int i = 1; i < navios[count].tamanho; i++) {
                navios[count].linha[i] = navios[count].linha[i - 1] + 1;
            }
        }

        //Escreve o navio no tabuleiro principal
        if (navios[count].orientacao == 0) {
            for (int i = 0; i < navios[count].tamanho; i++) {
                tabuleiro[navios[count].linha[0]][navios[count].coluna[i]] = navios[count].simbolo;
            }

        } else {
            for (int i = 0; i < navios[count].tamanho; i++) {
                tabuleiro[navios[count].linha[i]][navios[count].coluna[0]] = navios[count].simbolo;
            }
        }
        count++;

        //Encouracados
        for (int i = 0; i < 2; i++) {

            navios[count].simbolo = 'E';
            navios[count].tamanho = 3;
            navios[count].vida = navios[count].tamanho;

            espacoDisponivel = false;
            while (espacoDisponivel == false) {

                indiceDisponivel = false;
                //Repete ate uma posicai disponivel ser gerada
                while (indiceDisponivel == false) {
                    navios[count].orientacao = gerador.nextInt(2);

                    if (navios[count].orientacao == 0) {
                        navios[count].linha = new int[1];
                        navios[count].coluna = new int[navios[count].tamanho];

                        //Gera aleatoriamente uma posicao
                        navios[count].linha[0] = gerador.nextInt(10);
                        navios[count].coluna[0] = gerador.nextInt(11 - navios[count].tamanho);

                    } else {
                        navios[count].linha = new int[navios[count].tamanho];
                        navios[count].coluna = new int[1];

                        navios[count].linha[0] = gerador.nextInt(11 - navios[count].tamanho);
                        navios[count].coluna[0] = gerador.nextInt(10);
                    }

                    //Verifica se a posicao gerado e disponivel
                    indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[0], navios[count].coluna[0]);
                }

                //Preenche as posicoes do navio no vetor (linha ou coluna, depende da orientacao)
                if (navios[count].orientacao == 0) {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        navios[count].coluna[j] = (navios[count].coluna[j - 1] + 1);
                    }

                } else {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        navios[count].linha[j] = (navios[count].linha[j - 1] + 1);
                    }
                }

                //Verifica se o espaco a ser ocupado pelo navio esta disponivel
                if (navios[count].orientacao == 0) {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[0], navios[count].coluna[j]);
                        if (indiceDisponivel == false) {
                            j = navios[count].tamanho;
                            espacoDisponivel = false;
                        } else {
                            espacoDisponivel = true;
                        }
                    }

                } else {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[j], navios[count].coluna[0]);
                        if (indiceDisponivel == false) {
                            j = navios[count].tamanho;
                            espacoDisponivel = false;
                        } else {
                            espacoDisponivel = true;
                        }
                    }
                }
            }

            //Escreve o navio no tabuleiro principal
            if (navios[count].orientacao == 0) {
                for (int j = 0; j < navios[count].tamanho; j++) {
                    tabuleiro[navios[count].linha[0]][navios[count].coluna[j]] = navios[count].simbolo;
                }
            } else {
                for (int j = 0; j < navios[count].tamanho; j++) {
                    tabuleiro[navios[count].linha[j]][navios[count].coluna[0]] = navios[count].simbolo;
                }
            }
            count++;
        }

        //Cruzadores
        for (int i = 0; i < 3; i++) {

            navios[count].simbolo = 'C';
            navios[count].tamanho = 2;
            navios[count].vida = navios[count].tamanho;

            espacoDisponivel = false;
            while (espacoDisponivel == false) {

                indiceDisponivel = false;
                while (indiceDisponivel == false) {
                    navios[count].orientacao = gerador.nextInt(2);

                    if (navios[count].orientacao == 0) {
                        navios[count].linha = new int[1];
                        navios[count].coluna = new int[navios[count].tamanho];

                        navios[count].linha[0] = gerador.nextInt(10);
                        navios[count].coluna[0] = gerador.nextInt(11 - navios[count].tamanho);

                    } else {
                        navios[count].linha = new int[navios[count].tamanho];
                        navios[count].coluna = new int[1];

                        navios[count].linha[0] = gerador.nextInt(11 - navios[count].tamanho);
                        navios[count].coluna[0] = gerador.nextInt(10);
                    }

                    indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[0], navios[count].coluna[0]);
                }

                if (navios[count].orientacao == 0) {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        navios[count].coluna[j] = (navios[count].coluna[j - 1] + 1);
                    }

                } else {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        navios[count].linha[j] = (navios[count].linha[j - 1] + 1);
                    }
                }

                if (navios[count].orientacao == 0) {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[0], navios[count].coluna[j]);
                        if (indiceDisponivel == false) {
                            j = navios[count].tamanho;
                            espacoDisponivel = false;
                        } else {
                            espacoDisponivel = true;
                        }
                    }

                } else {
                    for (int j = 1; j < navios[count].tamanho; j++) {
                        indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[j], navios[count].coluna[0]);
                        if (indiceDisponivel == false) {
                            j = navios[count].tamanho;
                            espacoDisponivel = false;
                        } else {
                            espacoDisponivel = true;
                        }
                    }
                }
            }

            if (navios[count].orientacao == 0) {
                for (int j = 0; j < navios[count].tamanho; j++) {
                    tabuleiro[navios[count].linha[0]][navios[count].coluna[j]] = navios[count].simbolo;
                }
            } else {
                for (int j = 0; j < navios[count].tamanho; j++) {
                    tabuleiro[navios[count].linha[j]][navios[count].coluna[0]] = navios[count].simbolo;
                }
            }
            count++;
        }

        //Submarinos
        for (int i = 0; i < 4; i++) {

            navios[count].simbolo = 'S';
            navios[count].tamanho = 1;
            navios[count].vida = navios[count].tamanho;

            indiceDisponivel = false;
            while (indiceDisponivel == false) {
                navios[count].orientacao = gerador.nextInt(2);

                if (navios[count].orientacao == 0) {
                    navios[count].linha = new int[1];
                    navios[count].coluna = new int[navios[count].tamanho];

                    navios[count].linha[0] = gerador.nextInt(10);
                    navios[count].coluna[0] = gerador.nextInt(11 - navios[count].tamanho);

                } else {
                    navios[count].linha = new int[navios[count].tamanho];
                    navios[count].coluna = new int[1];

                    navios[count].linha[0] = gerador.nextInt(11 - navios[count].tamanho);
                    navios[count].coluna[0] = gerador.nextInt(10);
                }

                indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[0], navios[count].coluna[0]);
            }

            tabuleiro[navios[count].linha[0]][navios[count].coluna[0]] = navios[count].simbolo;

            count++;
        }

        //Hidroavioes
        for (int i = 0; i < 5; i++) {

            navios[count].simbolo = 'A';
            navios[count].tamanho = 1;
            navios[count].vida = navios[count].tamanho;

            indiceDisponivel = false;
            while (indiceDisponivel == false) {
                navios[count].orientacao = gerador.nextInt(2);

                if (navios[count].orientacao == 0) {
                    navios[count].linha = new int[1];
                    navios[count].coluna = new int[navios[count].tamanho];

                    navios[count].linha[0] = gerador.nextInt(10);
                    navios[count].coluna[0] = gerador.nextInt(11 - navios[count].tamanho);

                } else {
                    navios[count].linha = new int[navios[count].tamanho];
                    navios[count].coluna = new int[1];

                    navios[count].linha[0] = gerador.nextInt(11 - navios[count].tamanho);
                    navios[count].coluna[0] = gerador.nextInt(10);
                }

                indiceDisponivel = indiceDisponivel(tabuleiro, navios[count].linha[0], navios[count].coluna[0]);
            }

            tabuleiro[navios[count].linha[0]][navios[count].coluna[0]] = navios[count].simbolo;

            count++;
        }

        //Preenche as casas restantes com o caractere '.'
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == VAZIO) {
                    tabuleiro[i][j] = '.';
                }
            }
        }

        return tabuleiro;
    }

    //Funcao para verificar se uma posicao no tabuleiro esta disponivel ou nao
    //Para uma posicao ser disponivel ela nao pode ter nenhum navio em cada uma de suas posicoes adjacentes
    private static boolean indiceDisponivel(char[][] tabuleiro, int linha, int coluna) {

        boolean indiceDisponivel;

        //Cada um dos if's serve para cada limite do tabuleiro, pois a funcao nao pode verificar uma 
        //posicao fora da matriz
        
        if (tabuleiro[linha][coluna] == VAZIO) {
            if (linha == 0 && coluna == 0) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (linha == 9 && coluna == 9) {
                if (tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (linha == 0 && coluna == 9) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (linha == 9 && coluna == 0) {
                if (tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (linha == 0) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (coluna == 0) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (linha == 9) {
                if (tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else if (coluna == 9) {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            } else {
                if (tabuleiro[linha + 1][coluna] == VAZIO && tabuleiro[linha - 1][coluna] == VAZIO && tabuleiro[linha][coluna + 1] == VAZIO && tabuleiro[linha][coluna - 1] == VAZIO) {
                    indiceDisponivel = true;
                } else {
                    indiceDisponivel = false;
                }
            }
        } else {
            indiceDisponivel = false;
        }

        return indiceDisponivel;
    }

    //Funcao para apresentar o tabuleiro no terminal
    public static void mostrarTabuleiro(char[][] tabuleiro) {
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print((i+1) + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            if (i == 9) {
                System.out.print((i + 1) + " ");
            } else {
                System.out.print((i + 1) + "  ");
            }
            for (int j = 0; j < 10; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print("- ");
        }
        System.out.println();
    }

    //Funcao para inicializar os objetos Navio do vetor de navios
    public static Navio[] prepararNavios() {

        Navio[] navios = new Navio[15];
        for (int i = 0; i < 15; i++) {
            navios[i] = new Navio();
        }

        return navios;
    }
}
