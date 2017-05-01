package br.edu.uft.so;

public class Main {

    private Garfo[] garfos;
    private Filosofo[] filosofos;
    private Monitor monitor;



    private void init(){
        garfos = new Garfo[5];
        filosofos = new Filosofo[5];
        monitor = new Monitor();
        for(int i=0; i<5; i++)
            garfos[i] = new Garfo();

        for(int i=0; i<5; i++){
            filosofos[i] = new Filosofo(i, garfos[i], garfos[(i+4)%5], monitor);
            new Thread(filosofos[i]).start();
        }

    }



    public Main(){
        init();
    }

    public static void main(String[] args) {
	// write your code here
        new Main();
    }
}



