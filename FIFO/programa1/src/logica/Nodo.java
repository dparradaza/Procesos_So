/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author diego
 */
public class Nodo {
    
    private String id;
    private String nombre;
    private int numServ;
    private Nodo sig;
    
    public Nodo(String id,String nombre, int numS){
        this.id = id;
        this.nombre = nombre;
        this.numServ = numS;
        sig = null;
               
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumServ() {
        return numServ;
    }

    public void setNumServ(int numServ) {
        this.numServ = numServ;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}
