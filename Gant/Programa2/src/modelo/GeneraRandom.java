/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Mauro
 */
public class GeneraRandom {
    
    public GeneraRandom(){}
    
    
    public ArrayList<Integer> generarComienzo (int noProc){//noProc numero de proceso
        ArrayList<Integer> tiemposLlegada = new ArrayList<Integer>();

        for (int i = 0; i < noProc; i++) {
            int r;
            if (tiemposLlegada.isEmpty()) {
                r = (int) Math.floor(Math.random() * (15) + 1);//Math.random()*ValorLimite+ValorInicial
                tiemposLlegada.add(r);
            } else {
                r = ((int) Math.floor(Math.random() * (15) + 1));
                r = r + tiemposLlegada.get(i - 1);
                tiemposLlegada.add(r);
                
            }
        }
        return(tiemposLlegada);                       
    }
    
    public ArrayList<Integer> generarRafaga(int noProc){
        ArrayList<Integer> tiemposRafaga = new ArrayList<Integer>();
        int tRafaga;
        for (int j = 0; j < noProc; j++) {
            tRafaga = (int)Math.floor(Math.random()*(10)+10);  
            tiemposRafaga.add(tRafaga);
        }
        return tiemposRafaga;
    }
    
}
