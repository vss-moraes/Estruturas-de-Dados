package Trabalho;

public class Matriz {

    private Celula cabeca;

    private Matriz(){
        cabeca = new Celula(-1, -1, -1, null, null);
    }

    private void criarMatriz(int linhas, int colunas){
        Celula atual = this.cabeca;

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
        Celula cabecaMat = cabeca;
        Celula colunaAtual = cabecaMat;
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
            while ((proxElementoColuna.getLinha() < linha) && (proxElementoColuna.getColuna() != -1)){
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
            while ((proxElementoLinha.getColuna() < coluna) && (proxElementoLinha.getLinha() != -1)){
                linhaAtual = proxElementoLinha;
                proxElementoLinha = proxElementoLinha.getDireita();
            }
            linhaAtual.setDireita(nova);
            nova.setDireita(proxElementoLinha);
        }
        System.out.println("Elemento inserido com sucesso.");
    }

    private float matrizGetElemento (int linha, int coluna){
        Celula cabecaMat = this.cabeca;
        Celula atual = cabecaMat;
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

    public static void main(String[] args){
        Matriz teste = new Matriz();

        teste.criarMatriz(4, 4);
        teste.matrizSetElemento(1,1,1);
        teste.matrizSetElemento(2,2,2);
        teste.matrizSetElemento(3,3,3);
        teste.matrizSetElemento(4,4,4);

//        System.out.println(teste.matrizGetElemento(2,2));
//        System.out.println(teste.matrizGetElemento(2,3));
//        System.out.println(teste.matrizGetElemento(4,3));
        System.out.println(teste.matrizGetElemento(3,3));
//        System.out.println(teste.matrizGetElemento(1,2));
//        System.out.println(teste.matrizGetElemento(2,2));
//        System.out.println(teste.matrizGetElemento(2,1));
//        System.out.println(teste.matrizGetElemento(4,2));
        System.out.println(teste.matrizGetElemento(3,4));
        System.out.println(teste.matrizGetElemento(4,4));
    }
}