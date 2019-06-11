/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Random;

/**
 *
 * @author Chayder
 */
public class Nodo {

    Random rnd = new Random();
    public int proceso;//numero del proceso

    public Nodo sig;//apuntador del nodo
    public int tiempo_cpu;//tiempo en el cuál salió de la cola de listos
    public int tiempo_final;//tiempo de ejecución total del proceso
    public int tiempo_bloqueado = 0;//tiempo que lleva en cola de bloqueado
    public int tiempo_en_espera;//tiempo en espera tanto de suspendidos como en bloqueados y listos
    public int tiempo_suspendido;
    public int prioridad;

    public int getTiempo_bloqueado() {
        return tiempo_bloqueado;
    }

    public void setTiempo_bloqueado(int tiempo_bloqueado) {
        this.tiempo_bloqueado = tiempo_bloqueado;
    }

    public int getTiempo_en_espera() {
        return tiempo_en_espera;
    }

    public int getTiempo_final() {
        return tiempo_final;
    }

    public void setTiempo_final(int tiempo_final) {
        this.tiempo_final = tiempo_final;
    }

    public int getTiempo_cpu() {
        return tiempo_cpu;
    }

    public void setTiempo_cpu(int tiempo_demorado) {
        this.tiempo_cpu = tiempo_demorado;
    }

    public int getTiempo_restante() {
        return this.tiempo_final - this.tiempo_cpu;
    }

    public int getTiempo_suspendido() {
        return tiempo_suspendido;
    }

    public void setTiempo_suspendido(int tiempo_suspendido) {
        this.tiempo_suspendido = tiempo_suspendido;
    }
    
    

}
