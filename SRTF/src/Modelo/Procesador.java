package Modelo;

import Vista.Ventana;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Procesador extends Thread {

    public int cantidadProcesos = 1, lineaX = 1;
    public int tiempoEjecucion = 0, cambioProceso;
    private final Cola colaListo, colaAtendidos;
    private Proceso proceso;
    Ventana vista;
    //Atributos para poder pintar}
    private JPanel panel = null;
    int x = 10, y = 0, borde;
    Graphics g2 = null;
    private Point inicioArrastre;
    private Point finArrastre;
    private ArrayList<Shape> rectangulos = new ArrayList<>();
    ArrayList<Proceso> listaProcesos = new ArrayList<>();

    public Procesador(Ventana vista, JPanel j) {
        this.vista = vista;
        colaListo = new Cola();
        colaAtendidos = new Cola();
        this.panel = j;
        borde = j.getWidth() - 100;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        rectangulos.forEach((rectangulo) -> {
            // dibuja todos los rectangulos            
            g2.fill(rectangulo);
        });
        g2.setColor(Color.BLACK);
        //linea horizontal
        g2.drawLine(20, 10, lineaX, 10);
        //linea vertical
        g2.drawLine(20, 10, 20, cantidadProcesos);

    }

    public void pintar(int x, int y) {
        inicioArrastre = new Point(x, y);
        finArrastre = new Point(x + 20, y + 20);
        Shape rectangulo = crearRectangulo(inicioArrastre.x, inicioArrastre.y, finArrastre.x, finArrastre.y);
        rectangulos.add(rectangulo);
        panel.repaint();
    }

    private Rectangle2D.Float crearRectangulo(int x1, int y1, int x2, int y2) {
        // ajusta el rectangulo
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    @Override
    public void run() {
        System.out.println("run");
        while (true) {
            try {
                if (!colaListo.vacia()) {
                    //ordena la cola 
                    ordenarCola();
                    //extraigo el primer proceso de la cola organizada
                    proceso = colaListo.extraer();
                    if (cambioProceso != proceso.numeroProceso) {
                        proceso.tiempoInicio.add(tiempoEjecucion);
                        vista.modificarTabla(proceso.tiempoInicio, proceso.numeroProceso - 1, 3);
                    }

                    realizarTarea();
                    if (proceso.tiempoAuxiliarEjecucion < 0) {
                        proceso.tiempoFinalizacion.add(tiempoEjecucion);
                        vista.modificarTabla(proceso.tiempoFinalizacion, proceso.numeroProceso - 1, 4);

                        proceso.tiempoRetorno = proceso.tiempoFinalizacion.get(proceso.tiempoFinalizacion.size() - 1) - proceso.horaLlegada;
                        proceso.tiempoEspera = proceso.tiempoRetorno - proceso.tiempoEjecucion;
                        vista.modificarTabla(proceso.tiempoRetorno, proceso.numeroProceso - 1, 5);
                        vista.modificarTabla(proceso.tiempoEspera, proceso.numeroProceso - 1, 6);
                        colaAtendidos.insertar(proceso);
                        cambioProceso = proceso.numeroProceso;
                    } else {
                        colaListo.insertar(proceso);
                        cambioProceso = proceso.numeroProceso;
                    }
                    tiempoEjecucion++;
                    pintar(x + (tiempoEjecucion * 22), y + (proceso.numeroProceso * 22));
                    Procesador.sleep(500);

                } else {
                    System.out.println("colaVacia");
                    Procesador.sleep(500);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void calcularTiempoRetorno() {
        proceso.tiempoRetorno = proceso.tiempoFinalizacion.get(proceso.tiempoFinalizacion.size() - 1) - proceso.horaLlegada;
    }

    public void calcularTiempoEspera() {
        proceso.tiempoEspera = proceso.tiempoRetorno - proceso.tiempoEjecucion;
    }

    public void ordenarCola() {
        Proceso ordenar;
        while (!colaListo.vacia()) {
            ordenar = colaListo.extraer();
            listaProcesos.add(ordenar);
        }
        listaProcesos.sort((o1, o2) -> o1.compareTo(o2));
        for (int i = 0; i < listaProcesos.size(); i++) {
            colaListo.insertar(listaProcesos.get(i));
        }
        listaProcesos.clear();
    }

    //Métodos para el control de los procesos
    public void realizarTarea() {
        System.out.println("Atendiendo al proceso " + proceso.numeroProceso);
        proceso.tiempoAuxiliarEjecucion--;
        System.out.println("TIEMPO INICIO: " + proceso.tiempoInicio);
        System.out.println("TIEMPO FIN: " + proceso.tiempoFinalizacion);
    }

    //Métodos para el cambio de colas en los procesos
    public void añadirProceso(Proceso proceso) {
        colaListo.insertar(proceso);
    }

    public void cambiarListo() {
        colaListo.insertar(proceso);
    }

    //Métodos para actualizar las tablas
    public Cola getColaListo() {
        return colaListo;
    }
}
