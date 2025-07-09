package batalhanaval;

public class Ataque {

    static int naviosAfundados = 0;
    
    //Funcao para atacar uma coordenada
    public static void atacarCoordenadas(char[][] matrizA, char[][] matrizB, Navio[] navios, int linha, int coluna) {

        boolean acertouNavio = false;
        int indiceNavio = -1;

        //Verifica se o ataque acertou algum dos navios existentes
        //Ela percorre por todos os navios verificando se o ataque acertou alguma posicao ocupada
        for (int i = 0; i < 15; i++) {
            if (navios[i].orientacao == 0) {
                for (int j = 0; j < navios[i].tamanho; j++) {
                    if (navios[i].linha[0] == linha && navios[i].coluna[j] == coluna) {
                        acertouNavio = true;
                        indiceNavio = i;
                    }
                }
            } else {
                for (int j = 0; j < navios[i].tamanho; j++) {
                    if (navios[i].linha[j] == linha && navios[i].coluna[0] == coluna) {
                        acertouNavio = true;
                        indiceNavio = i;
                    }
                }
            }
        }

        //Caso algum navio seja acertado, ele perdera uma vida
        if (acertouNavio == true) {
            navios[indiceNavio].vida--;

            //Caso o navio tenha perdido todas as suas vidas, a funcao apresenta a mensagem
            //"Afundou uma embarcacao X"
            if (navios[indiceNavio].vida == 0) {
                
                naviosAfundados++;
                
                switch (navios[indiceNavio].simbolo) {
                    case 'P':
                        System.out.println("Afundou uma embarcacao Porta-Avioes");
                        break;
                    case 'E':
                        System.out.println("Afundou uma embarcacao Encouracado");
                        break;
                    case 'C':
                        System.out.println("Afundou uma embarcacao Cruzador");
                        break;
                    case 'S':
                        System.out.println("Afundou uma embarcacao Submarino");
                        break;
                    case 'A':
                        System.out.println("Afundou uma embarcacao Hidroaviao");
                        break;
                    default:
                        System.out.println("Nao sabemos o que voce afundou, verificar navios[indiceNavio].simbolo");
                }
                
            }
            
            //Caso o navio nao tenha perdido todas as suas vidas a funcao apresenta a mensagem
            //"Acertou uma embarcacao X"
            else {
                switch (navios[indiceNavio].simbolo) {
                    case 'P':
                        System.out.println("Acertou uma embarcacao Porta-Avioes");
                        break;
                    case 'E':
                        System.out.println("Acertou uma embarcacao Encouracado");
                        break;
                    case 'C':
                        System.out.println("Acertou uma embarcacao Cruzador");
                        break;
                    case 'S':
                        System.out.println("Acertou uma embarcacao Submarino");
                        break;
                    case 'A':
                        System.out.println("Acertou uma embarcacao Hidroaviao");
                        break;
                    default:
                        System.out.println("Nao sabemos o que voce acertou, verificar navios[indiceNavio].simbolo");
                }
            }
            
        } 
        
        //Caso o ataque nao tenha acertado nenhum navio, a funcao apresenta a mensagem
        //"Agua"
        else {
            System.out.println("Agua");
        }
        
        //Preenche o tabuleiro visivel com o caractere da posicao do tabuleiro principal
        matrizB[linha][coluna] = matrizA[linha][coluna];
    }

    //Verifica se a posicao do ataque ja foi atacada
    public static boolean ataqueValido(boolean[][] coordenadasAtacadas, int linha, int coluna) {

        boolean ataqueValido;

        if (coordenadasAtacadas[linha][coluna] == false) {
            ataqueValido = true;
        } else {
            ataqueValido = false;
        }
        
        coordenadasAtacadas[linha][coluna] = true;

        return ataqueValido;
    }
}
