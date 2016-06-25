package Trabalho;

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

   private void matriz_print2(){
        Celula atualLinha = this.cabeca;

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


    public Matriz matriz_adicionar(Matriz a, Matriz b, Matriz resultante){
        if (a.getTotalLinhas() <= b.getTotalLinhas() || a.getTotalColunas() <= b.getTotalColunas()) {
            resultante.criarMatriz(b.getTotalLinhas(), b.getTotalColunas());
        } else {
            resultante.criarMatriz(a.getTotalLinhas(), a.getTotalColunas());
        }
        int i = 1;
        int j = 1;
        while (j <= resultante.getTotalColunas()) {
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

    public Matriz matriz_multiplicar(Matriz a, Matriz b, Matriz resultante){
        if (a.getTotalLinhas() <= b.getTotalLinhas() || a.getTotalColunas() <= b.getTotalColunas()) {
            resultante.criarMatriz(b.getTotalLinhas(), b.getTotalColunas());
        } else {
            resultante.criarMatriz(a.getTotalLinhas(), a.getTotalColunas());
        }
        int i = 1;
        int j = 1;
        while (j <= resultante.getTotalColunas()) {
            resultante.matrizSetElemento(i, j, (a.matrizGetElemento(i,j) * b.matrizGetElemento(i,j)));
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


    public static void main(String[] args){
        Matriz teste = new Matriz();
        teste.criarMatriz(4, 4);

        Matriz a = new Matriz();
        a.criarMatriz(4, 4);
        Matriz b = new Matriz();
        b.criarMatriz(4, 4);
        for (int i = 1; i <= a.getTotalColunas(); i++) {
            for (int j = 1; j <= a.getTotalColunas(); j++) {
                a.matrizSetElemento(i,j, 4);
                b.matrizSetElemento(i,j, 1);
            }
        }
        Matriz c;
        c = a.matriz_multiplicar(a, b, teste);
        c.matriz_print();
    }
}
