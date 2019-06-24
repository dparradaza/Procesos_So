package Modelo;

import java.util.ArrayList;

public class Proceso implements Comparable<Proceso> {

    public int numProceso, tmpRafaga, tmpAuxEjecucion, tmpRetorno, tmpEspera;
    public ArrayList<Integer> tmpInicio, tmpFinal;
    public int horaLlegada;

    public Proceso(int horaLlegada, int tiempoEjecucion, int numeroProceso) {
        this.horaLlegada = horaLlegada;
        this.tmpRafaga = tiempoEjecucion;
        this.numProceso = numeroProceso;
        tmpInicio = new ArrayList<>();
        tmpFinal = new ArrayList<>();
        this.tmpAuxEjecucion = tiempoEjecucion;

    }

    @Override
    public int compareTo(Proceso process) {
        if (tmpAuxEjecucion < process.tmpAuxEjecucion) {
            return -1;
        }
        if (tmpAuxEjecucion > process.tmpAuxEjecucion) {
            return 1;
        }
        return 0;
    }

    public long tiempoLlevado() {
        long tiempo = 0;
        for (int i = 0; i < tmpInicio.size(); i++) {
            tiempo += tmpFinal.get(i) - tmpInicio.get(i);
        }
        return tiempo;
    }

    public long getTiempoRestante() {
        tmpAuxEjecucion = tmpRafaga - tmpAuxEjecucion;
        return tmpAuxEjecucion;
    }
}
