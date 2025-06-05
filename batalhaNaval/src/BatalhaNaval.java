public class BatalhaNaval {
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + ": ");
            char[][] tabuleiro = Tabuleiro.prepararTabuleiro();
            char[][] matrizB = new char[10][10];
            Tabuleiro.mostarTabuleiro(tabuleiro);
            
            System.out.println();
            System.out.println();
        }
    }
}