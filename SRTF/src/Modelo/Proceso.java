package Modelo;

import java.util.ArrayList;

public class Proceso implements Comparable<Proceso> {

    public int numeroProceso, tiempoEjecucion, tiempoAuxiliarEjecucion, tiempoRetorno, tiempoEspera;
    public ArrayList<Integer> tiempoInicio, tiempoFinalizacion;
    public int horaLlegada;

    public Proceso(int horaLlegada, int tiempoEjecucion, int numeroProceso) {
        this.horaLlegada = horaLlegada;
        this.tiempoEjecucion = tiempoEjecucion;
        this.numeroProceso = numeroProceso;
        tiempoInicio = new ArrayList<>();
        tiempoFinalizacion = new ArrayList<>();
        this.tiempoAuxiliarEjecucion = tiempoEjecucion;

    }

    @Override
    public int compareTo(Proceso process) {
        if (tiempoAuxiliarEjecucion < process.tiempoAuxiliarEjecucion) {
            return -1;
        }
        if (tiempoAuxiliarEjecucion > process.tiempoAuxiliarEjecucion) {
            return 1;
        }
        return 0;
    }

    public long tiempoLlevado() {
        long tiempo = 0;
        for (int i = 0; i < tiempoInicio.size(); i++) {
            tiempo += tiempoFinalizacion.get(i) - tiempoInicio.get(i);
        }
        return tiempo;
    }

    public long getTiempoRestante() {
        tiempoAuxiliarEjecucion = tiempoEjecucion - tiempoAuxiliarEjecucion;
        return tiempoAuxiliarEjecucion;
    }
}
