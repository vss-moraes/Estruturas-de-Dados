package Trabalho;

public class MatrizTeste {
    public static void main(String[] args){
        Matriz a = new Matriz();
        Matriz b = new Matriz();

        Matriz c = Matriz.matrizMultiplicar(a, b);
        c.imprimirMatriz();
    }
}
