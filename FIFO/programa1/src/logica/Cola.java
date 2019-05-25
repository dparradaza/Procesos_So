/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class Cola {
    
    class Nodo {
        Nodo ant,sig;    
        public int tiempoLlegada;
        public int tiempoRafaga; 
        public int tiempoComienzo;
        public int tiempoFinal;
        public int tiempoEspera;
    }
    
    public Nodo raiz;
    
    public Cola () {
        raiz=null;
    }
      
    public void insertar (int pos, int tiempoLlegada, int tiempoRafaga)
    {
        if (pos <= cantidad () + 1)    {
            Nodo nuevo = new Nodo ();
            nuevo.tiempoLlegada = tiempoLlegada;
            nuevo.tiempoRafaga= tiempoRafaga;
            if (pos == 1){
                nuevo.sig = raiz;
                nuevo.tiempoComienzo=tiempoLlegada;
                nuevo.tiempoFinal=nuevo.tiempoComienzo+tiempoRafaga;
                nuevo.tiempoEspera=nuevo.tiempoComienzo-nuevo.tiempoLlegada;
                
                if (raiz!=null)
                    raiz.ant=nuevo;
                raiz = nuevo;
            } else
                if (pos == cantidad () + 1)    {
                    Nodo reco = raiz;
                    while (reco.sig != null) {
                        reco = reco.sig;
                    }
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = null;
                    //nuevo.tiempoComienzo=nuevo.ant.tiempoFinal;
                    if(nuevo.ant.tiempoFinal<tiempoLlegada){
                        nuevo.tiempoComienzo=tiempoLlegada;
                    }
                    else{
                        nuevo.tiempoComienzo=nuevo.ant.tiempoFinal;
                    }
                    nuevo.tiempoFinal=nuevo.tiempoComienzo+tiempoRafaga;
                    nuevo.tiempoEspera=nuevo.tiempoComienzo-tiempoLlegada;
                } else {
                    Nodo reco = raiz;
                    for (int f = 1 ; f <= pos - 2 ; f++)
                        reco = reco.sig;
                    Nodo siguiente = reco.sig;
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = siguiente;
                    siguiente.ant=nuevo;
                }
        }
        else{
            System.out.println("La posición "+pos+" No existe");
        }
    }

    public int extraer (int pos) {
        if (pos <= cantidad ())    {
            int informacion;
            if (pos == 1) {
                informacion = raiz.tiempoLlegada;
                raiz = raiz.sig;
                if (raiz!=null)
                    raiz.ant=null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                reco.sig = prox.sig;
                Nodo siguiente=prox.sig;
                if (siguiente!=null)
                    siguiente.ant=reco;
                informacion = prox.tiempoLlegada;
            }
            return informacion;
        }
        else
            return Integer.MAX_VALUE;
    }
    public int extraerdeCola(){
     return extraer(1);   
    }
    public void borrar (int pos)
    {
        if (pos <= cantidad ())    {
            if (pos == 1) {
                raiz = raiz.sig;
                if (raiz!=null)
                    raiz.ant=null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                prox=prox.sig;
                reco.sig = prox;
                if (prox!=null)
                    prox.ant=reco;
            }
        }
    }
    
    public void intercambiar (int pos1, int pos2) {
        if (pos1 <= cantidad () && pos2 <= cantidad ())    {
            Nodo reco1 = raiz;
            for (int f = 1 ; f < pos1 ; f++)
                reco1 = reco1.sig;
            Nodo reco2 = raiz;
            for (int f = 1 ; f < pos2 ; f++)
                reco2 = reco2.sig;
            int aux = reco1.tiempoLlegada;
            reco1.tiempoLlegada = reco2.tiempoLlegada;
            reco2.tiempoLlegada = aux;
        }
    }
    
    public int mayor () {
        if (!vacia ()) {
            int may = raiz.tiempoLlegada;
            Nodo reco = raiz.sig;
            while (reco != null) {
                if (reco.tiempoLlegada > may)
                    may = reco.tiempoLlegada;
                reco = reco.sig;
            }
            return may;
        }
        else
            return Integer.MAX_VALUE;
    }
    
    public int posMayor() {
        if (!vacia ())    {
            int may = raiz.tiempoLlegada;
            int x=1;
            int pos=x;
            Nodo reco = raiz.sig;
            while (reco != null){
                if (reco.tiempoLlegada > may) {
                    may = reco.tiempoLlegada;
                    pos=x;
                }
                reco = reco.sig;
                x++;
            }
            return pos;
        }
        else
            return Integer.MAX_VALUE;
    }

    public int cantidad ()
    {
        int cant = 0;
        Nodo reco = raiz;
        while (reco != null) {
            reco = reco.sig;
            cant++;
        }
        return cant;
    }
    
    public boolean ordenada() {
        if (cantidad()>1) {
            Nodo reco1=raiz;
            Nodo reco2=raiz.sig;
            while (reco2!=null) {
                if (reco2.tiempoLlegada<reco1.tiempoLlegada) {
                    return false;
                }
                reco2=reco2.sig;
                reco1=reco1.sig;
            }
        }
        return true;
    }
    
    public boolean existe(int tiempoLlegada) {
        Nodo reco=raiz;
        while (reco!=null) {
            if (reco.tiempoLlegada==tiempoLlegada)
                return true;
            reco=reco.sig;
        }
        return false;
    }
    
    public boolean vacia ()
    {
        if (raiz == null)
            return true;
        else
            return false;
    }
    
    // Se almacena todos los datos en un ArrayList
        public ArrayList<Integer> darLista ()
    {
        ArrayList <Integer> a = new ArrayList<Integer>();
        Nodo reco = raiz;
        while (reco != null) {
            a.add(reco.tiempoLlegada);
            a.add(reco.tiempoRafaga);
            a.add(reco.tiempoComienzo);
            a.add(reco.tiempoFinal);
            a.add(reco.tiempoEspera);            
            reco = reco.sig;
        }
        return a;
    }
}