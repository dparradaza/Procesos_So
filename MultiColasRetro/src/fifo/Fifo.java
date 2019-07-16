package fifo;

import roundRobin.Cola;
import roundRobin.Nodo;
import roundRobin.RoundRobin;
import Vista.Interfaz;
import Vista.MiRender;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Fifo extends Thread {

    public Cola listos = new Cola();
    public Cola bloqueados = new Cola();
    public Cola atendidos = new Cola();
    public Cola atendidos_total = new Cola();;
    
    public boolean agregar_proceso = false;
    public boolean encurso = false;
    public RoundRobin robin;
    

    public Interfaz interfaz;
    int conteo_tiempo = 0;//variable usada para graficar el primer diagrama de gantt

    Random r = new Random();
    int nu;

    public boolean bandera = false;

    public void setAtendidos_total(Cola atendidos_total) {
        this.atendidos_total = atendidos_total;
    }

    public Fifo(Interfaz interfaz) {
        this.interfaz = interfaz;
        listos.nuevo();//crea la lista
        bloqueados.nuevo();//crea la lista
        atendidos.nuevo();//crea la lista
        listos.procesos(3);//asigna procesos aleatorios a la cola de listos
    }

    public void setRobin(RoundRobin ro) {
        this.robin = ro;
    }

    @Override
    public void run() {//atender los procesos
        //da un número aleatorio de acuerdo al promedio de los tiempos que se dan

        Nodo en_ejecucion;

        listos.imprimir();
        listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());

        while (true) {//comprueba que queden procesos por atender
            encurso = true;
            try {
                robin.srtf.envejecimiento();
                comprobarPrioridad();
                if (listos.p.sig != listos.p) {
                    en_ejecucion = listos.p.sig;
                    if (hayRecurso()) {//verifica que tenga recursos el proceso en curso para proceder a ejecutarlo
                        interfaz.getProcesos_en_ejecucion().setText(en_ejecucion.proceso + "");
                        int f = en_ejecucion.tiempo_final;
                        for (int i = 1; i <= f; i++) {
                            robin.srtf.envejecimiento();
                            if (comprobarPrioridad()) {
                                sleep(1000);
                                interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");

                                //--------------------------- Finalizar --------------------------
                                if (Integer.parseInt(interfaz.getTiempo_real().getText()) == 90) {
                                    JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                                    interfaz.getlblSemaforo().setBackground(Color.green);
                                    interfaz.getlblSemaforo().setText("Vacía");
                                    this.stop();
                                    robin.stop();
                                    robin.srtf.stop();
                                }
                                //----------------------------------------------------

                                interfaz.getCpu_nuevo().setText(listos.p.sig.tiempo_final + "");
                                en_ejecucion.setTiempo_cpu(en_ejecucion.tiempo_cpu + 1);
                                interfaz.getTiempo_cpu().setText(en_ejecucion.tiempo_cpu + "");
                                interfaz.getProcesos_en_ejecucion().setText(en_ejecucion.proceso + "");
                                listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());

                                if (listos.num > 1) {//revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
                                    Nodo auxlis = en_ejecucion.sig;
                                    for (int li = 2; li <= listos.num; li++) {
                                        if (auxlis == listos.p) {
                                            auxlis = listos.p.sig;
                                        }
                                        auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
                                        diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                        auxlis = auxlis.sig;
                                        listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
                                    }
                                }

                                if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                                    Nodo auxiliar_b = bloqueados.p.sig;

                                    for (int j = 1; j <= bloqueados.num; j++) {
                                        auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                                        auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;

                                        //------------------ Semáforo -------------------------
                                        interfaz.getlblSemaforo().setBackground(Color.green);
                                        interfaz.getlblSemaforo().setText("Vacía");
                                        //-----------------------------------------------------

                                        diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                        bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), robin.bloqueados, robin.srtf.bloqueados, this.bloqueados);
                                        auxiliar_b = auxiliar_b.sig;
                                    }

                                    if (hayRecurso2()) {
                                        /*sleep(3000);
                                         sleep(2000);*/
                                        listos.agregar(bloqueados.p.sig);
                                        bloqueados.eliminarNodo(bloqueados.p.sig);
                                        bloqueados.imprimir();
                                        bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), robin.bloqueados, robin.srtf.bloqueados, this.bloqueados);
                                        listos.imprimir();
                                        listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
                                    }

                                }

                                //------------------ Semáforo -------------------------
                                interfaz.getlblSemaforo().setBackground(Color.red);
                                interfaz.getlblSemaforo().setText("En uso");
                                //-----------------------------------------------------

                                diagramaGantt(en_ejecucion, 1, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                robin.aumentarListos();
                                robin.srtf.aumentarListos();
                                if (en_ejecucion.tiempo_cpu == en_ejecucion.tiempo_final) {//verifica que cumpla el quantum y agregue a cola de atendidos                                   

                                    interfaz.getTiempo_cpu().setText(en_ejecucion.tiempo_cpu + "");
                                    /*sleep(2000);
                                     sleep(1000);*/
                                    //JOptionPane.showMessageDialog(null, "Finaliza proceso " + listos.p.sig.proceso);

                                    //------------------ Semáforo -------------------------
                                    interfaz.getlblSemaforo().setBackground(Color.green);
                                    interfaz.getlblSemaforo().setText("Vacía");
                                    //-----------------------------------------------------

                                    actualizarAtendidos(en_ejecucion);
                                    atendidos.agregar(en_ejecucion);
                                    listos.eliminarNodo(en_ejecucion);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());

                                    //----------------------- Despachador ------------------------------------
                                    if (this.robin.getTotalNumNuevo() != 0) {
                                        //JOptionPane.showMessageDialog(null, "Entrara el proceso " + this.robin.listos.p.sig.proceso);
                                    } else {
                                        if (this.robin.srtf.getTotalNumNuevo() != 0) {
                                            //JOptionPane.showMessageDialog(null, "Entrará el proceso de Cola 2");
                                        } else {
                                            if (this.listos.p.sig.proceso != 0) {
                                                //JOptionPane.showMessageDialog(null, "Entrará el proceso " + this.listos.p.sig.proceso);
                                            }
                                        }
                                    }
                                    //------------------------------------------------------------------------

                                    sleep(1000);
                                    i = f;
                                    interfaz.getTiempo_cpu().setText("");
                                    interfaz.getProcesos_en_ejecucion().setText("");
                                    interfaz.getCpu_nuevo().setText("");
                                }
                                this.agregarProceso();
                            } else {
                                i = f;
                            }

                            if (bandera) {
                                Nodo impr;
                                impr = en_ejecucion;
                                if (i > 1 && i < impr.tiempo_final && listos.p.sig.tiempo_cpu < 100 && (listos.p != null || bloqueados.p != null)) {
                                    i = impr.tiempo_final;
                                    //System.out.print("\ntiempo final fifo_: " + impr.tiempo_final + " ->i: " + i);

                                    bloqueados.agregar(en_ejecucion);
                                    listos.eliminarNodo(en_ejecucion);
                                    bloqueados.imprimir();
                                    bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, robin.bloqueados, robin.srtf.bloqueados);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
                                    this.agregarProceso();

                                    JOptionPane.showMessageDialog(null, "Proceso" + impr.proceso + " bloqueado.");
                                } else {
                                    //JOptionPane.showMessageDialog(null, "No se puede bloquear el proceso en este momento.");
                                }
                                bandera = false;
                            }

                            // -------------------- Llegan nuevos procesos de forma dinámica--------
//                            nu = r.nextInt(30);
//                            if (nu == 24 || nu == 28 || nu == 5) {
//                                this.robin.agregar_proceso = true;
//                                this.robin.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 1");
//                                this.robin.listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
//                            } else if (nu == 14) {
//                                this.robin.srtf.agregar_proceso = true;
//                                this.robin.srtf.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 2");
//                                this.robin.srtf.listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
//                            } else if (nu == 3) {
//                                this.agregar_proceso = true;
//                                this.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 3");
//                                this.listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
//                            }
                            //----------------------------------------------------------------------*/
                        }

                    } else {//si no hay recurso para un proceso lo envía a cola de bloqueados
                        /*sleep(1000);
                         sleep(2000);*/
                        bloqueados.agregar(en_ejecucion);
                        listos.eliminarNodo(en_ejecucion);
                        bloqueados.imprimir();
                        bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), robin.bloqueados, robin.srtf.bloqueados, this.bloqueados);
                        listos.imprimir();
                        listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
                        //this.agregarProceso();
                        //sleep(2000);

                    }
                } else {//si no hay nada en listos actualiza el tiempo para las otras colas       

                    sleep(1000);
                    interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");

                    //--------------------------- Finalizar --------------------------
                    if (Integer.parseInt(interfaz.getTiempo_real().getText()) == 90) {
                        //JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                        interfaz.getlblSemaforo().setBackground(Color.green);
                        interfaz.getlblSemaforo().setText("Vacía");
                        this.stop();
                        this.robin.stop();
                        this.robin.srtf.stop();
                    }
                    //----------------------------------------------------

                    if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                        comprobarPrioridad();
                        Nodo auxiliar_b = bloqueados.p.sig;
                        robin.aumentarListos();
                        robin.srtf.aumentarListos();
                        for (int j = 1; j <= bloqueados.num; j++) {
                            auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                            auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;

                            //------------------ Semáforo -------------------------
                            interfaz.getlblSemaforo().setBackground(Color.green);
                            interfaz.getlblSemaforo().setText("Vacía");
                            //-----------------------------------------------------

                            diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), robin.bloqueados, robin.srtf.bloqueados, this.bloqueados);
                            auxiliar_b = auxiliar_b.sig;
                        }

                        if (hayRecurso2()) {
                            /*sleep(3000);
                             sleep(2000);*/
                            listos.agregar(bloqueados.p.sig);
                            bloqueados.eliminarNodo(bloqueados.p.sig);
                            bloqueados.imprimir();
                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), robin.bloqueados, robin.srtf.bloqueados, this.bloqueados);
                            listos.imprimir();
                            listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
                        }
                    }
                }

                if ((listos.p == listos.p.sig) && (bloqueados.p == bloqueados.p.sig)) {
                    if (this.robin.getTotalNumNuevo() != 0 || this.robin.srtf.getTotalNumNuevo() != 0) {
                        if (this.robin.getTotalNumNuevo() != 0) {
                            encurso = false;
                            robin.resume();
                            this.suspend();
                        } else if (this.robin.srtf.getTotalNumNuevo() != 0) {
                            encurso = false;
                            this.robin.srtf.resume();
                            this.suspend();
                        }
                    } else if (this.robin.getTotalNumNuevo() == 0 && this.robin.srtf.getTotalNumNuevo() == 0) {
                        sleep(5000);
                        //JOptionPane.showMessageDialog(null, "Ejecución Finalizada");
                    }
                    this.stop();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Fifo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public boolean hayRecurso() {//verifica si existe el recurso correspondiente a ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        if (x < 19) {
            return true;
        } else {
            return false;
        }
    }

    public void agregarProceso() {//agrega un nuevo proceso en cualquier instante de tiempo
        if (agregar_proceso == true) {
            int nuevo_num = getTotalNum() + robin.getTotalNum() + robin.srtf.getTotalNum() + 1;
            agregar_proceso = false;
            listos.agregarProceso(nuevo_num);
        }
        listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
    }

    public int getTotalNum() {
        return listos.num + bloqueados.num + atendidos.num;
    }

    public boolean hayRecurso2() {//Determina si existe un recurso para ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        return x < 16;
    }

    public Nodo CompararTiempos(Nodo inicial) {
        Nodo comparado;
        Nodo aux_inicial = inicial;
        comparado = aux_inicial.sig;
        while (comparado != listos.p) {
            if (aux_inicial.tiempo_final > comparado.tiempo_final) {
                aux_inicial = comparado;
            }
            comparado = comparado.sig;
        }
        return aux_inicial;
    }

    public void diagramaGantt(Nodo actual, int estado, int transcurrido) {//actualiza diagrama de gantt desde la interfaz gráfica
        String fase;
        switch (estado) {
            case 1:
                fase = "X1";
                break;
            case 2:
                fase = "E";
                break;
            case 3:
                fase = "B";
                break;
            case 4:
                fase = "S";
                break;
            default:
                fase = null;
                break;
        }
        interfaz.getTblGantt().setValueAt(fase, actual.proceso - 1, transcurrido);
        interfaz.getTblGantt().setDefaultRenderer(Object.class, new MiRender());
        interfaz.getTblGantt().setValueAt(actual.tiempo_en_espera, actual.proceso - 1, 98);
        interfaz.getTblGantt().setValueAt(actual.tiempo_en_espera + actual.tiempo_cpu, actual.proceso - 1, 99);
    }

    public void aumentarListos() {
        //revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
        Nodo auxlis = listos.p.sig;
        Nodo auxbloq = bloqueados.p.sig;
        for (int li = 1; li <= listos.num; li++) {
            auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
            diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
            auxlis = auxlis.sig;
            listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
        }
        for (int bloq = 1; bloq <= bloqueados.num; bloq++) {
            auxbloq.tiempo_en_espera = auxbloq.tiempo_en_espera + 1;
            auxbloq.tiempo_bloqueado = auxbloq.tiempo_bloqueado + 1;
            diagramaGantt(auxbloq, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
            auxbloq = auxbloq.sig;
            bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
        }
    }

    public void actualizarAtendidos(Nodo nuevoatendido) {
        atendidos_total.agregar(nuevoatendido);
        atendidos_total.imprimir(interfaz.getTexto_atendidos(), interfaz.getTotal_atendidos());
    }

    public boolean comprobarPrioridad() throws InterruptedException {
        if (robin.getTotalNumNuevo() != 0) {
            //sleep(2000);
            robin.resume();
            this.suspend();
            return false;
        } else if (robin.srtf.getTotalNumNuevo() != 0) {
            //sleep(2000);
            robin.srtf.resume();
            this.suspend();
            return false;
        }
        return true;
    }

    public int getTotalNumNuevo() {
        return listos.num + bloqueados.num;
    }

}
