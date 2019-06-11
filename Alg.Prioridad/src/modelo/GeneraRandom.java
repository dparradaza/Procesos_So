package modelo;

import java.util.ArrayList;

public class GeneraRandom {
    
    public GeneraRandom(){}
    
    
    public ArrayList<Integer> generarComienzo (int noProc){//noProc numero de proceso
        ArrayList<Integer> tiemposLlegada = new ArrayList<>();
        for (int i = 0; i < noProc; i++) {
            int r;
            if (tiemposLlegada.isEmpty()) {
                r = (int) Math.floor(Math.random() * 5);//Math.random()*ValorLimite+ValorInicial
                tiemposLlegada.add(r);
            } else {
                r = ((int) Math.floor(Math.random() * 5));
                r = r + tiemposLlegada.get(i - 1);
                tiemposLlegada.add(r);  
            }
        }
        return(tiemposLlegada);                       
    }
    
    public ArrayList<Integer> generarRafaga(int noProc){
        ArrayList<Integer> tiemposRafaga = new ArrayList<>();
        int tRafaga;
        for (int j = 0; j < noProc; j++) {
            tRafaga = (int)Math.floor(Math.random()* 10 + 1);  
            tiemposRafaga.add(tRafaga);
        }
        return tiemposRafaga;
    }  
    
    public ArrayList<Integer> generarPrioridad(int noProc){
        ArrayList<Integer> tiemposRafaga = new ArrayList<>();
        int priori;
        for (int j = 0; j < noProc; j++) {
            priori = (int)Math.floor(Math.random()* 5 + 1);  
            tiemposRafaga.add(priori);
        }
        return tiemposRafaga;
    } 
}