package roundRobin;

import java.util.Random;

public class Nodo {

    Random rnd = new Random();
    public int proceso;//numero del proceso
    public int tiempo_estimado;//quantum en cpu
    public Nodo sig;//apuntador del nodo
    public int tiempo_cpu;//tiempo en el cuál salió de la cola de listos
    public int tiempo_final;//tiempo de ejecución total del proceso
    public int quantum_suspendido;//quantum suspendido
    public int tiempo_suspendido = 0;//tiempo que lleva en cola de suspendido
    public int tiempo_bloqueado = 0;//tiempo que lleva en cola de bloqueado
    public int tiempo_en_espera;//tiempo en espera tanto de suspendidos como en bloqueados y listos

    public int getQuantum_suspendido() {
        return quantum_suspendido;
    }

    public void setQuantum_suspendido(int quantum_suspendido) {
        this.quantum_suspendido = quantum_suspendido;
    }

    public int getTiempo_bloqueado() {
        return tiempo_bloqueado;
    }

    public void setTiempo_bloqueado(int tiempo_bloqueado) {
        this.tiempo_bloqueado = tiempo_bloqueado;
    }

    public int getTiempo_en_espera() {
        return tiempo_en_espera;
    }

    public int getTiempo_suspendido() {
        return tiempo_suspendido;
    }

    public void setTiempo_suspendido(int quantum_suspendidos) {
        this.tiempo_suspendido = quantum_suspendidos;
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

    public int getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(int tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public int getTiempo_restante() {
        return this.tiempo_final - this.tiempo_cpu;
    }

}
