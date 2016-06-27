package Trabalho;

import java.util.Scanner;

public class Matriz {

    private Celula cabeca;
    private int totalColunas;
    private int totalLinhas;

    private Matriz(){
        cabeca = new Celula(-1, -1, -1, null, null);
    }

    public int getTotalColunas(){
        return totalColunas;
    }

    public int getTotalLinhas(){
        return totalLinhas;
    }

    public Celula getCabeca(){
        return this.cabeca;
    }

    private void criarMatriz(int linhas, int colunas){
        Celula atual = this.cabeca;
        totalLinhas = linhas;
        totalColunas = colunas;

        for (int i = 1; i <= linhas; i++) {
            atual.setAbaixo(new Celula(i, -1, 0, null, null));
            atual = atual.getAbaixo();
            atual.setDireita(atual);
        }
        atual.setAbaixo(cabeca);

        atual = this.cabeca;
        for (int i = 1; i <= colunas; i++) {
            atual.setDireita(new Celula(-1, i, 0, null, null));
            atual = atual.getDireita();
            atual.setAbaixo(atual);
        }
        atual.setDireita(cabeca);
    }

    // Ta funcionando pra entradas válidas. Se tiver linha ou coluna igual a 0 da pau.
    private void lerMatriz(){
        Scanner leitor = new Scanner(System.in);

        try {
            this.criarMatriz(leitor.nextInt(), leitor.nextInt());

            int proximo = leitor.nextInt();
            while (proximo != 0){
                this.matrizSetElemento(proximo, leitor.nextInt(), leitor.nextFloat());
                proximo = leitor.nextInt();
            }
        } catch (Exception e){
            System.out.println("Entrada Inválida");
            leitor.close();
        }

        leitor.close();
        this.matriz_print();

    }

    private void matrizSetElemento (int linha, int coluna, float elemento){
        Celula colunaAtual = this.cabeca;
        Celula proxElementoColuna = null;
        Celula linhaAtual = cabeca;
        Celula proxElementoLinha = null;
        Celula nova = new Celula(linha, coluna, elemento, null, null);

        while (colunaAtual.getColuna() != coluna){
            colunaAtual = colunaAtual.getDireita();
        }
        while (linhaAtual.getLinha() != linha){
            linhaAtual = linhaAtual.getAbaixo();
        }

        proxElementoColuna = colunaAtual.getAbaixo();
        proxElementoLinha = linhaAtual.getDireita();

        //Verifica se a coluna atual está vazia
        if (colunaAtual.equals(proxElementoColuna)){
            colunaAtual.setAbaixo(nova);
            nova.setAbaixo(colunaAtual);
        } else {
            while ((proxElementoColuna.getLinha() < linha) && (proxElementoColuna.getLinha() != -1)){
                colunaAtual = proxElementoColuna;
                proxElementoColuna = proxElementoColuna.getAbaixo();
            }
            colunaAtual.setAbaixo(nova);
            nova.setAbaixo(proxElementoColuna);
        }

        //Verifica se a linha atual está vazia
        if (linhaAtual.equals(proxElementoLinha)){
            linhaAtual.setDireita(nova);
            nova.setDireita(linhaAtual);
        } else {
            while ((proxElementoLinha.getColuna() < coluna) && (proxElementoLinha.getColuna() != -1)){
                linhaAtual = proxElementoLinha;
                proxElementoLinha = proxElementoLinha.getDireita();
            }
            linhaAtual.setDireita(nova);
            nova.setDireita(proxElementoLinha);
        }
        // System.out.println("Elemento inserido com sucesso.");
    }

    private float matrizGetElemento (int linha, int coluna){
        Celula atual = this.cabeca;
        Celula proxima = null;

        while (atual.getColuna() != coluna){
            atual = atual.getDireita();
        }
        proxima = atual.getAbaixo();

        while (!atual.equals(proxima)){
            if (proxima.getLinha() == linha)
                return proxima.getInfo();
            else if (proxima.getLinha() == -1){
                return 0;
            } else {
                atual = proxima;
                proxima = proxima.getAbaixo();
            }
        }
        return 0;
    }

    private void matriz_print(){
        Celula atualLinha = this.cabeca;
        Celula atualColuna = null;
        Celula proximaColuna = null;

        System.out.println(totalLinhas + " " + totalColunas);
        while(atualLinha.getColuna() < totalColunas){
            atualLinha = atualLinha.getDireita();
            atualColuna = atualLinha;
            proximaColuna = atualColuna.getAbaixo();

            while(!atualLinha.equals(proximaColuna)){
                System.out.println(proximaColuna.getLinha() + " " + proximaColuna.getColuna() + " " + proximaColuna.getInfo());
                atualColuna = atualColuna.getAbaixo();
                proximaColuna = atualColuna.getAbaixo();
            }
        }
    }

    public void matriz_print2() {
        System.out.println(totalColunas + " " + totalLinhas);
        for (int i = 1; i <= totalLinhas; i++) {
            for (int j = 1; j <= totalColunas; j++) {
                if (this.matrizGetElemento(i, j) != 0) {
                    System.out.println(i + " " + j + " " + this.matrizGetElemento(i, j));
                }
            }
        }
    }

    // O método pode ser estático, por não utilizar nenhum atributo da classe.
    // Matriz resultante é desnecessário se você vai retornar uma matriz no fim
    // do método.
    public Matriz matriz_adicionar(Matriz a, Matriz b, Matriz resultante){
        // A condição está errada. A soma de matrizes só é possível se as duas forem do mesmo tamanho
        if (a.getTotalLinhas() <= b.getTotalLinhas() || a.getTotalColunas() <= b.getTotalColunas()) {
            resultante.criarMatriz(b.getTotalLinhas(), b.getTotalColunas());
        } else {
            resultante.criarMatriz(a.getTotalLinhas(), a.getTotalColunas());
        }
        // Por que não usar dois for aninhados? fica mais fácil de ler e vai ter o mesmo número de
        // iterações.
        int i = 1;
        int j = 1;
        while (j <= resultante.getTotalColunas()) {
            // Tem que checar se a soma não é 0 antes de adicionar, senão a resultante vai ficar cheia de 0s
            resultante.matrizSetElemento(i, j, (a.matrizGetElemento(i,j) + b.matrizGetElemento(i,j)));
            float h = a.matrizGetElemento(i,j) + b.matrizGetElemento(i,j);
            if (i < resultante.getTotalLinhas()) {
                i++;
            } else {
                j++;
                i = 1;
            }
        }
        System.out.print("Terminou");
        return resultante;
    }


    
    public static Matriz matriz_multiplicar(Matriz a, Matriz b) {
        Matriz resultante = new Matriz();
        Celula atualElementoLinha = a.cabeca;
        Celula atualElementoColuna = b.cabeca;
        Celula proximo = null;
        int i = 1;
        int j = 1;
        float result = 0;
        if (a.getTotalColunas() != b.getTotalColunas()) {
            System.out.print("As matrizes não podem ser diferentes!");
        } else {
            resultante.criarMatriz(a.getTotalLinhas(), b.getTotalColunas());
            atualElementoLinha = atualElementoLinha.getDireita().getAbaixo();
            atualElementoColuna = atualElementoColuna.getAbaixo().getDireita();
            while (i <= a.getTotalLinhas() && j <= b.getTotalColunas()) {
                while (atualElementoLinha.getColuna() != -1) {
                    float elemLinha = atualElementoLinha.getInfo();
                    float elemColuna = atualElementoColuna.getInfo();
                    result += elemLinha * elemColuna;
                    atualElementoLinha = atualElementoLinha.getDireita();
                    atualElementoColuna = atualElementoColuna.getAbaixo();
                }
                resultante.matrizSetElemento(i, j, result);
                result = 0;
                i++;
                if (i > a.getTotalLinhas()) {
                    j++;
                    i = 1;
                    atualElementoColuna = atualElementoColuna.getDireita().getAbaixo();
                    atualElementoLinha = atualElementoLinha.getAbaixo().getDireita();
                    atualElementoLinha = atualElementoLinha.getAbaixo();
                } else {
                    atualElementoLinha = atualElementoLinha.getDireita().getAbaixo();
                    atualElementoColuna = atualElementoColuna.getAbaixo();
                }
            }
        }
        return resultante;
    }

    public static Matriz matrizTransposta(Matriz original){
        Matriz resultante = new Matriz();
        resultante.criarMatriz(original.getTotalColunas(), original.getTotalLinhas());

        Celula linhaAtual = original.getCabeca().getAbaixo();
        Celula celulaAtual;

        while (!linhaAtual.getAbaixo().equals(original.getCabeca())){
            celulaAtual = linhaAtual.getDireita();
            while (!celulaAtual.getDireita().equals(linhaAtual)){
                resultante.matrizSetElemento(celulaAtual.getColuna(), celulaAtual.getLinha(), celulaAtual.getInfo());
                celulaAtual = celulaAtual.getDireita();
            }
            linhaAtual = linhaAtual.getAbaixo();
        }

        return resultante;
    }

    public static void main(String[] args){
        Matriz teste = new Matriz();
        teste.lerMatriz();
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
        a.matrizSetElemento(1,1, 2);
        a.matrizSetElemento(1,2, 3);
        a.matrizSetElemento(2,1, 4);
        a.matrizSetElemento(2,2, 5);

        b.matrizSetElemento(1,1, 6);
        b.matrizSetElemento(1,2, 7);
        b.matrizSetElemento(2,1, 8);
        b.matrizSetElemento(2,2, 9);

        Matriz c;
        c = matriz_multiplicar(a, b);
        c.matriz_print();
    }
}
