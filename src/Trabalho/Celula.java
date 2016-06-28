package Trabalho;

class Celula {
    private int linha;
    private int coluna;
    private float info;

    private Celula direita;
    private Celula abaixo;

    Celula(int linha, int coluna, float info, Celula direita, Celula abaixo) {
        this.linha = linha;
        this.coluna = coluna;
        this.info = info;
        this.direita = direita;
        this.abaixo = abaixo;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    float getInfo() {
        return info;
    }

    Celula getDireita() {
        return direita;
    }

    void setDireita(Celula direita) {
        this.direita = direita;
    }

    Celula getAbaixo() {
        return abaixo;
    }

    void setAbaixo(Celula abaixo) {
        this.abaixo = abaixo;
    }

    boolean equals(Celula outra){
        return (this.linha == outra.getLinha()) && (this.coluna == outra.getColuna());
    }

    boolean isEmpty(){
        return (this.getDireita() == this || this.getAbaixo()== this);
    }
}
