package Trabalho;

import java.util.Scanner;

public class Matriz {

    private Celula cabeca;
    private int totalColunas;
    private int totalLinhas;

    public Matriz(){
        cabeca = new Celula(-1, -1, -1, null, null);
        lerMatriz();
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

    public void criarMatriz(int linhas, int colunas){
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

    public void lerMatriz(){
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
    }

    public void matrizSetElemento (int linha, int coluna, float elemento){
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

        if (colunaAtual.isEmpty()){
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

        if (linhaAtual.isEmpty()){
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
    }

    public float matrizGetElemento (int linha, int coluna){
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

    public void imprimirMatriz(){
        Celula atualLinha = this.cabeca.getAbaixo();
        Celula elemento = atualLinha.getDireita();

        System.out.println(totalLinhas + " " + totalColunas);
        while (atualLinha.getLinha() != -1){
            while (elemento != atualLinha) {
                System.out.println(elemento.getLinha() + " " + elemento.getColuna() + " " + elemento.getInfo());
                elemento = elemento.getDireita();
            }
            atualLinha = atualLinha.getAbaixo();
            elemento = atualLinha.getDireita();
        }
        System.out.println(0);
    }

    public static Matriz matrizAdicionar(Matriz a, Matriz b){
        Matriz resultante = new Matriz();
        if (a.getTotalLinhas() <= b.getTotalLinhas() || a.getTotalColunas() <= b.getTotalColunas()) {
            resultante.criarMatriz(b.getTotalLinhas(), b.getTotalColunas());
        } else {
            resultante.criarMatriz(a.getTotalLinhas(), a.getTotalColunas());
        }
        for(int j = 1; j <= resultante.getTotalColunas(); j++) {
            for (int i = 1; i <= resultante.getTotalLinhas(); i++) {
                float h = a.matrizGetElemento(i, j) + b.matrizGetElemento(i, j);
                if (h != 0)
                    resultante.matrizSetElemento(i, j, h);
            }
        }
        return resultante;
    }
    
    public static Matriz matrizMultiplicar(Matriz a, Matriz b){
        Matriz resultado = new Matriz();
        Celula linhaAtual = a.getCabeca().getAbaixo();
        Celula colunaAtual = b.getCabeca().getDireita();
        if (a.getTotalLinhas() != b.getTotalColunas() || a.getTotalColunas() != b.getTotalLinhas()) {
            System.out.print("As matrizes não podem ser diferentes!");
            return null;
        } else {
            resultado.criarMatriz(a.getTotalLinhas(), b.getTotalColunas());
            for (int i = 1; i <= a.getTotalLinhas(); i++){
                for (int j = 1; j<= b.getTotalColunas(); j++){
                    float info = multiplicaListas(linhaAtual, colunaAtual);
                    if (info != 0)
                        resultado.matrizSetElemento(i, j, info);
                    colunaAtual = colunaAtual.getDireita();
                }
                colunaAtual = b.getCabeca().getDireita();
                linhaAtual = linhaAtual.getAbaixo();
            }
        }
        return resultado;
    }

    private static float multiplicaListas(Celula cabecaLinha, Celula cabecaColuna){
        if (cabecaLinha.isEmpty() || cabecaColuna.isEmpty())
            return 0;
        else {
            Celula elementoLinhaAtual = cabecaLinha.getDireita();
            Celula elementoColunaAtual = cabecaColuna.getAbaixo();
            float resultado = 0;

            while (elementoColunaAtual.getLinha() != -1 && elementoLinhaAtual.getColuna() != -1){
                if (elementoLinhaAtual.getColuna() == elementoColunaAtual.getLinha()) {
                    resultado += elementoLinhaAtual.getInfo() * elementoColunaAtual.getInfo();
                    elementoColunaAtual = elementoColunaAtual.getAbaixo();
                    elementoLinhaAtual = elementoLinhaAtual.getDireita();
                } else if (elementoLinhaAtual.getColuna() < elementoColunaAtual.getLinha()){
                    elementoLinhaAtual = elementoLinhaAtual.getDireita();
                } else {
                    elementoColunaAtual = elementoColunaAtual.getAbaixo();
                }
            }
            return resultado;
        }
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
}