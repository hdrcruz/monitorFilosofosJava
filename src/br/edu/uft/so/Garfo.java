package br.edu.uft.so;

/**
 * Created by Helder on 18/04/2017.
 */
class Garfo {
    private boolean disponivel;

    public Garfo(){
        disponivel = true;
    }

    public boolean getDisponivel(){
        return disponivel;
    }

    public void setDisponivel(boolean b){
        disponivel = b;
    }
}
