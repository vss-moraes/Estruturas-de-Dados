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
        Celula colunaAtual = cabeca;
        Celula proximaColuna = null;
        Celula linhaAtual = cabeca;
        Celula proximaLinha = null;
        Celula nova = new Celula(linha, coluna, elemento, null, null);

        while (colunaAtual.getColuna() != coluna){
            colunaAtual = colunaAtual.getDireita();
        }
        while (linhaAtual.getLinha() != linha){
            linhaAtual = linhaAtual.getAbaixo();
        }

        proximaColuna = colunaAtual.getAbaixo();
        proximaLinha = linhaAtual.getDireita();

        if (colunaAtual.getAbaixo().equals(colunaAtual)){
            colunaAtual.setAbaixo(nova);
            nova.setAbaixo(colunaAtual);
        } else {
            while ((proximaColuna.getColuna() < coluna) && (proximaColuna.getColuna() != -1)){
                colunaAtual = proximaColuna;
                proximaColuna = proximaColuna.getAbaixo();
            }
            colunaAtual.setAbaixo(nova);
            nova.setAbaixo(proximaColuna);
        }

        if (linhaAtual.getDireita().equals(linhaAtual)){
            linhaAtual.setDireita(nova);
            nova.setDireita(linhaAtual);
        } else {
            while ((proximaLinha.getLinha() < linha) && (proximaLinha.getLinha() != -1)){
                linhaAtual = proximaLinha;
                proximaLinha = proximaLinha.getDireita();
            }
            linhaAtual.setDireita(nova);
            nova.setDireita(proximaLinha);
        }
    }

    private float matrizGetElemento (int linha, int coluna){
        Celula atual = this.cabeca;

        while (!atual.getDireita().equals(this.cabeca)){
            if (atual.getColuna() == coluna) {
                while (atual.getAbaixo() != null) {
                    if (atual.getLinha() == linha)
                        return atual.getInfo();
                    else if (atual.getAbaixo() != null)
                        atual = atual.getAbaixo();
                }
                return 0;
            } else if (atual.getDireita() != null)
                atual = atual.getDireita();
            else
                return 0;

        }
        return 0;
    }

    public static void main(String[] args){
        Matriz teste = new Matriz();

        teste.criarMatriz(4, 4);
        teste.matrizSetElemento(2,2,2);
        System.out.println();
        teste.matrizSetElemento(1,2,3);

        System.out.println(teste.matrizGetElemento(1,2));
        System.out.println(teste.matrizGetElemento(2,2));
    }
}