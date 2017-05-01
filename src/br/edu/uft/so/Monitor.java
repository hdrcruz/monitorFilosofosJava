package br.edu.uft.so;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Helder on 18/04/2017.
 */

class Monitor{
    private Lock mutex = null;
    private Condition[] garfosDisponiveis;
    private String[] estado;
    private int[] filosofos;

    private void imprimirEstados(int id){
        StringBuffer str = new StringBuffer();
        for(int i=0; i<5; i++){
            str.append("Filosofo " + i + " : "  + estado[i] + "\n");
        }
        System.out.println(str);
    }

    public Monitor(){
        filosofos = new int[5];        //identificador de cada filosofo
        mutex = new ReentrantLock();
        estado = new String[5];  //estado de cada filosofo (comendo, pensando, garfosDisponiveis)
        garfosDisponiveis = new Condition[5];
        for(int i=0; i<5; i++){
            filosofos[i] = i;
            estado[i] = "Pensando"; //todos os filosofos começam pensando
            garfosDisponiveis[i] = mutex.newCondition();

        }
    }

    public void setEstado(int filosofo, String s){
        estado[filosofo] = s;
    }

    public void pegarGarfos(int id, Garfo esquerda, Garfo direita){
        mutex.lock(); //requisita um bloqueio
        try{
            setEstado(id, "Faminto"); //muda o estado do filosofo para: faminto
            //System.out.println("Filosofo " + id + " agora está faminto!\n");
            while(!esquerda.getDisponivel() || !direita.getDisponivel())
                garfosDisponiveis[id].await();
            esquerda.setDisponivel(false);
            direita.setDisponivel(false);
            setEstado(id, "Comendo"); //muda o estado do filosofo para: comendo
            System.out.println("Filosofo " + id + " Agora está comendo!\n");
            imprimirEstados(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            mutex.unlock(); //desbloqueia
        }
    }


    public void devolverGarfos(int filosofo, Garfo esquerda, Garfo direita){
        mutex.lock();
        try{
            setEstado(filosofo, "Pensando"); //muda o estado do filosofo para pensando
            System.out.println("Filosofo " + filosofo + " Agora está pensando!\n");
            esquerda.setDisponivel(true); //muda o estado do grafo para disponivel
            direita.setDisponivel(true); //muda o estado do garfo para dosponivel
            garfosDisponiveis[(filosofo+1)%5].signal();
            garfosDisponiveis[(filosofo+4)%5].signal();
            imprimirEstados(filosofo);
        }finally{
            mutex.unlock();
        }
    }
}