public class BatalhaNaval {
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": ");
            char[][] tabuleiro = Utilitarios.prepararTabuleiro();
            char[][] matrizB = new char[10][10];
            Utilitarios.mostarTabuleiro(tabuleiro);
            
            System.out.println();
            System.out.println();
        }
    }
}