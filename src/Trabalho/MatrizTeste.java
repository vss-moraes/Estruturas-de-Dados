package Trabalho;

import static Trabalho.Matriz.matriz_multiplicar;
import static Trabalho.matrizMultiplicar.multiplicar;

public class MatrizTeste {
    public static void main(String[] args){
//        Matriz teste = new Matriz();
//        teste.lerMatriz();
//
//        teste.criarMatriz(4, 4);
//
//        Matriz a = new Matriz();
//        a.criarMatriz(4, 4);
//        Matriz b = new Matriz();
//        b.criarMatriz(4, 4);
//        for (int i = 1; i <= a.getTotalColunas(); i++) {
//            for (int j = 1; j <= a.getTotalColunas(); j++) {
//                a.matrizSetElemento(i,j, i*10+j);
//                b.matrizSetElemento(i,j, i*10+j);
//            }
//        }
//        Matriz c;
//        c = a.matriz_multiplicar(a, b, teste);
////        c.matriz_print();
//
//        c = matrizTransposta(a);
//        a.matriz_print();
//        c.matriz_print();

        Matriz a = new Matriz();
        a.criarMatriz(2, 2);
        Matriz b = new Matriz();
        b.criarMatriz(2, 2);
//        a.matrizSetElemento(1,1, 2);
//        a.matrizSetElemento(2,1, 4);
        a.matrizSetElemento(2,2, 1);

        b.matrizSetElemento(1,1, 1);
        b.matrizSetElemento(1,2, 2);
        b.matrizSetElemento(2,1, 3);
        b.matrizSetElemento(2,2, 4);

        Matriz c;
        c = multiplicar(a, b);
        c.matriz_print();
    }
}
