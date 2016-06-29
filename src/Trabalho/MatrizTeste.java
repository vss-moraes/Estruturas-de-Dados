package Trabalho;

public class MatrizTeste {
    public static void main(String[] args){
        Matriz a = new Matriz();
        a.criarMatriz(4, 4);
        a.matrizSetElemento(1,1,50);
        a.matrizSetElemento(2,1,10);
        a.matrizSetElemento(2,3,20);
        a.matrizSetElemento(4,1,-30);
        a.matrizSetElemento(4,3, -60);
        a.matrizSetElemento(4,4,5);

        Matriz b = new Matriz();
        b.criarMatriz(4, 4);
        b.matrizSetElemento(1,1,50);
        b.matrizSetElemento(1,2,30);
        b.matrizSetElemento(2,1,10);
        b.matrizSetElemento(2,3,-20);
        b.matrizSetElemento(4,4,-5);

        a.imprimirMatriz();
        System.out.println();
        b.imprimirMatriz();

        Matriz c = new Matriz();
        c = Matriz.matrizMultiplicar(a, b);
        c.imprimirMatriz();
    }
}
