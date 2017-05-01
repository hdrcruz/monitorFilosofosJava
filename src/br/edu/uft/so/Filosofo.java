package br.edu.uft.so;

/**
 * Created by Helder on 18/04/2017.
 */

class Filosofo implements Runnable{
    private Monitor monitor;
    private Garfo garfoesquerda, garfodireita;
    private int id;
    public Filosofo(int id, Garfo e, Garfo d, Monitor m){
        this.monitor = m;
        this.garfoesquerda = e;
        this.garfodireita = d;
        this.id = id;
    }

    private void comer(){
        try{
            monitor.pegarGarfos(id, garfoesquerda, garfodireita);
            Thread.sleep(2000);
        }catch(InterruptedException e){}
    }

    private void pensar(){
        try{
            monitor.devolverGarfos(id, garfoesquerda, garfodireita);
            Thread.sleep(2000);
        }catch(InterruptedException e){}
    }

    public void run(){
        while(true){
            comer();
            pensar();
        }
    }
}