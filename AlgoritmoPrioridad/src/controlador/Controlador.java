package controlador;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import vista.Ventana;
import modelo.Modelo;
import modelo.Nodo;

public class Controlador implements Runnable {

    private final Modelo modelo;
    private final Ventana ventana;

    //variables de control de procesos
    private final int ESPERA_PROC = 5;
    private final int RAFAGA_PROC = 10;
    private final int CANT_PROC = 10;

    //variables para control de hilo
    private final Thread hilo;
    private int tiempo;
    private int idProc;

    private boolean pausado = false;

    //inicializa pantalla inicio
    public Controlador(Modelo logic) {
        this.modelo = logic;
        ventana = new Ventana(this);
        ventana.setVisible(true);
        //crea un hilo para pintar más adelante utilizando el run de esta clase
        hilo = new Thread(this);
    }

    //inicia las colas, en caso de ser un algoritmo diferente inicia la cola correspondiente
    public void iniciar() {
        modelo.iniciarPrioridad();
        //La cola de bloqueado siempre es la misma
        modelo.iniciarBloquedos();
        //inicializa el tiempo de ejecución e hilo
        tiempo = 0;
        hilo.start();
    }

    @Override
    public void run() {
        Canvas c = ventana.getGant();

        //se crea el doble buffer
        Image img = c.createImage(c.getWidth(), c.getHeight());
        Graphics g1 = img.getGraphics();

        //dibuoj fondo
        g1.setColor(new Color(254, 249, 231));
        g1.fillRect(0, 0, c.getWidth(), c.getHeight());
        g1.setColor(Color.BLACK);
        //g1.drawRect(1, 1, c.getWidth() - 3, c.getHeight() - 3);

        //Dibujo regla
        for (int i = 10; i < c.getWidth(); i += 10) {
            g1.drawLine(i, 0, i, c.getWidth());
        }

        //inicializo variale de ids
        idProc = 0;

        //variables random para la creación de nuevos procesos
        int raf;
        int newProc = (int) (Math.random() * ESPERA_PROC + 1) * 10;

        //inicia el ciclo de pintar procesos
        while (tiempo < 598) {
            //agregar nuevo proceso random a la cola
            if (newProc == 0 && idProc < CANT_PROC) {
                //Se define aleatoriamente el tiempo de ejecución
                raf = (int) (Math.random() * RAFAGA_PROC + 1) * 10;
                //se agrega un nuevo nodo con el id actual, el tiempo y el tiempo de ejecución
                Nodo A = new Nodo(idProc, tiempo, raf);
                modelo.getColaA().push(A);
                agregarATabla(idProc, A.getPrioridad(), tiempo, raf);
                //se elige aleatoriamente el tiempo de aparición de un siguiente proceso
                newProc = (int) (Math.random() * ESPERA_PROC + 1) * 10;
                //Se aumenta el id de los procesos
                idProc++;
            }

            //si la cola no está vacía 
            if (!modelo.getColaA().isEmpty()) {
                //si el tiempo actual es igual al tiempo de activación del proceso + tiempo de procesamiento
                //Entonces se cumple que el proceso ya terminó su procesamiento
                if (modelo.getColaA().getCabeza().getT_rafaga() <= 0) {
                    //se asigna tiempo final
                    modelo.getColaA().getCabeza().setT_final(tiempo);
                    //se mandan los valores a la tabla
                    actualizarTabla(modelo.getColaA().getCabeza());
                    //se extrae el proceso que termina de la cola
                    modelo.getColaA().pop();
                }
            }

            //actualiza las Prioridades en la tabla de la interfaz;
            actualizarPrioridades();
            //pinta nodo por nodo
            pintar(g1);
            //pinta el doble buffer
            c.getGraphics().drawImage(img, 0, 0, c);
            //fin de ciclo
            tiempo++;
            newProc--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void pintar(Graphics g) {
        Nodo N = modelo.getColaA().getCabeza();
        Nodo M = modelo.getColaB().getCabeza();
        if (N != null) {
            //en azul los que se están ejecutando (la cabeza)
            g.setColor(Color.BLUE);
            g.fillRect(tiempo, (20 * N.getId() + 20), 1, 8);

            //en verde = espera, los demás
            g.setColor(Color.GREEN);
            N = N.getSiguiente();

            //recorrer cola
            while (N != null || M != null) {
                if (N != null) {
                    g.fillRect(tiempo, (20 * N.getId() + 20), 1, 8);
                    N = N.getSiguiente();
                }
                if (M != null) {
                    g.fillRect(tiempo, (20 * M.getId() + 20), 1, 8);
                    M = M.getSiguiente();
                }
            }
            modelo.getColaA().aumentarEsperas();
            modelo.getColaA().ejecutarCabeza();
        } else if (M != null) {
            g.setColor(Color.GREEN);
            while (M != null) {
                g.fillRect(tiempo, (20 * M.getId() + 20), 1, 8);
                M = M.getSiguiente();
            }
        }
        modelo.getColaB().aumentarEsperas();
    }

    //añadir cada proceso nuevo que se cree a la tabla
    public void agregarATabla(int id, int pr, int llegada, int raf) {
        DefaultTableModel tab = (DefaultTableModel) ventana.getTblProcesos().getModel();
        String datos[] = new String[4];
        datos[0] = Character.toString((char) (id + 65));
        if (pr == 0) {
            datos[1] = "-";
        } else {
            datos[1] = "" + pr;
        }
        datos[2] = "" + (llegada / 10);
        datos[3] = "" + (raf / 10);
        tab.addRow(datos);
        ventana.getTblProcesos().setModel(tab);
    }

    //borra todos los valores de la tabla existente
    public void reiniciarTabla() {
        DefaultTableModel tab = (DefaultTableModel) ventana.getTblProcesos().getModel();
        int k = tab.getRowCount();
        for (int i = 0; i < k; i++) {
            tab.removeRow(0);
        }
        ventana.getTblProcesos().setModel(tab);
    }

    //se agregan los valores faltantes de la tabla
    public void actualizarTabla(Nodo N) {
        DefaultTableModel tab = (DefaultTableModel) ventana.getTblProcesos().getModel();

        N.setT_retorno(N.getT_final() - N.getT_llegada());

        tab.setValueAt((N.getT_retorno() / 10) + "," + (N.getT_retorno() % 10), N.getId(), 4);
        tab.setValueAt((N.getT_espera() / 10) + "," + (N.getT_espera() % 10), N.getId(), 5);
        tab.setValueAt((N.getT_final() / 10) + "," + (N.getT_final() % 10), N.getId(), 6);

        ventana.getTblProcesos().setModel(tab);
    }

    public void bloqueadoTab(int row) {
        DefaultTableModel tab = (DefaultTableModel) ventana.getTblProcesos().getModel();
        tab.setValueAt(true, row, 7);
        ventana.getTblProcesos().setModel(tab);
    }

    public void desbloqueadoTab(int row) {
        DefaultTableModel tab = (DefaultTableModel) ventana.getTblProcesos().getModel();
        tab.setValueAt(false, row, 7);
        ventana.getTblProcesos().setModel(tab);
    }

    //Método para bloquear procesos
    public void bloquear(boolean bloquear) {
        if (bloquear) {
            if (!modelo.getColaA().isEmpty()) {
                Nodo P = modelo.getColaA().pop();
                modelo.getColaB().push(P);
                bloqueadoTab(P.getId());
            }
        } else {
            if (!modelo.getColaB().isEmpty()) {
                Nodo P = modelo.getColaB().pop();
                modelo.getColaA().push(P);

                desbloqueadoTab(P.getId());
            }
        }
    }

    public void actualizarPrioridades() {
        DefaultTableModel tab = (DefaultTableModel) ventana.getTblProcesos().getModel();

        Nodo N = modelo.getColaA().getCabeza();
        while (N != null) {
            if (N.getPrioridad() < Integer.parseInt((String) tab.getValueAt(N.getId(), 1))) {
                tab.setValueAt("" + N.getPrioridad(), N.getId(), 1);
            }
            N = N.getSiguiente();
        }

        ventana.getTblProcesos().setModel(tab);
    }

    @SuppressWarnings("deprecation")
    public void pausar() {
        try {
            if (!pausado) {
                hilo.suspend();
                pausado = true;
            } else {
                hilo.resume();
                pausado = false;
            }
        } catch (Exception e) {
        }
    }
}
