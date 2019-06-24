package Controlador;

import Modelo.Procesador;
import Modelo.Proceso;
import Vista.Ventana;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;

public final class Controlador extends JComponent {

    Proceso proceso = null;
    private Procesador Procesador;

    public Controlador(Ventana vtn, JComponent contenedor, JPanel j) {
        crearProcesador(vtn, j);
        this.setBounds(0, 0, contenedor.getWidth(), contenedor.getHeight());
        contenedor.add(this);
    }

    public void crearProcesador(Ventana vtn, JPanel j) {
        Procesador = new Procesador(vtn, j);
        Procesador.start();
    }

    public void crearProceso(int horaLlegada, int numeroProceso) {
        int rafaga = (int) (Math.random() * 10) + 1;
        proceso = new Proceso(horaLlegada, rafaga, numeroProceso);
        añadirProceso(proceso, Procesador);
    }

    public void añadirProceso(Proceso proceso, Procesador procesador) {
        procesador.añadirProceso(proceso);
    }

    public int tiempoTranscurrido() {
        return Procesador.tiempoEjecucion;
    }

    public Proceso retornoProceso() {
        return proceso;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Procesador.paint(g);
    }
}
