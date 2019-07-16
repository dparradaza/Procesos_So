package srtf;

import roundRobin.Cola;
import roundRobin.Nodo;
import Vista.Interfaz;
import Vista.MiRender;
import fifo.Fifo;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Srtf extends Thread {

    public Cola listos = new Cola();
    public Cola bloqueados = new Cola();
    public Cola atendidos = new Cola();
    public Cola suspendidos = new Cola();
    public Cola atendidos_total = new Cola();
    public Fifo fifo;
    
    public boolean agregar_proceso = false;
    public boolean encurso = true;

    public Interfaz interfaz;
    int conteo_tiempo = 0;//variable usada para graficar el primer diagrama de gantt

    Random r = new Random();
    int nu;

    public boolean bandera = false;

    public void setAtendidos_total(Cola atendidos_total) {
        this.atendidos_total = atendidos_total;
    }

    public Srtf(Interfaz interfaz, Fifo fif) {
        this.interfaz = interfaz;
        this.fifo = fif;
        listos.nuevo();//crea la lista

        bloqueados.nuevo();//crea la lista
        atendidos.nuevo();//crea la lista
        suspendidos.nuevo();
        listos.procesos(2);

    }

    @Override
    public void run() {//atender los procesos
        //da un número aleatorio de acuerdo al promedio de los tiempos que se dan
//asigna procesos aleatorios a la cola de listos
        this.fifo.listos.imprimir(this.interfaz.getTexto_usuarios(), this.interfaz.getTotal_usuarios());
        Nodo en_ejecucion;

        listos.imprimir();
        listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());

        while (true)//comprueba que queden procesos por atender
        {
            encurso = true;

            try {

                //sleep(1000);
                //this.agregarProceso();
                envejecimiento();
                fifo.robin.envejecimiento();
                comprobarPrioridad();
                if (listos.p.sig != listos.p) {

                    en_ejecucion = this.CompararTiempos(listos.p.sig);

                    if (hayRecurso()) {//verifica que tenga recursos el proceso en curso para proceder a ejecutarlo

                        interfaz.getProcesos_en_ejecucion().setText(en_ejecucion.proceso + "");
                        //sleep(2000);
                        int f = en_ejecucion.tiempo_final;

                        for (int i = 1; i <= f; i++) {

                            //aca va en ejecucion nuevo
                            envejecimiento();
                            fifo.robin.envejecimiento();
                            if (comprobarPrioridad()) {
                                if (CompararRestante(en_ejecucion)) {

                                    sleep(1000);
                                    interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");

                                    //--------------------------- Finalizar --------------------------
                                    if (Integer.parseInt(interfaz.getTiempo_real().getText()) == 90) {
                                        //JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                                        interfaz.getlblSemaforo().setBackground(Color.green);
                                        interfaz.getlblSemaforo().setText("Vacía");
                                        this.stop();
                                        this.fifo.stop();
                                        this.fifo.robin.stop();
                                    }
                                    //----------------------------------------------------

                                    interfaz.getCpu_nuevo().setText(listos.p.sig.tiempo_final + "");
                                    en_ejecucion.setTiempo_cpu(en_ejecucion.tiempo_cpu + 1);
                                    interfaz.getTiempo_cpu().setText(en_ejecucion.tiempo_cpu + "");
                                    interfaz.getProcesos_en_ejecucion().setText(en_ejecucion.proceso + "");
                                    listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());

                                    if (listos.num > 1) {//revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
                                        Nodo auxlis = en_ejecucion.sig;
                                        for (int li = 2; li <= listos.num; li++) {
                                            if (auxlis == listos.p) {
                                                auxlis = listos.p.sig;
                                            }
                                            auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
                                            diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));

                                            auxlis = auxlis.sig;
                                            listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                                        }
                                    }

                                    if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                                        Nodo auxiliar_b = bloqueados.p.sig;

                                        for (int j = 1; j <= bloqueados.num; j++) {
                                            auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                                            auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;

                                            //---------------- Semáforo ---------------------------
                                            interfaz.getlblSemaforo().setBackground(Color.green);
                                            interfaz.getlblSemaforo().setText("Vacía");
                                            //-----------------------------------------------------

                                            diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);
                                            auxiliar_b = auxiliar_b.sig;
                                        }

                                        if (hayRecurso2()) {
                                            /*sleep(3000);
                                             sleep(2000);*/
                                            listos.agregar(bloqueados.p.sig);
                                            bloqueados.eliminarNodo(bloqueados.p.sig);
                                            bloqueados.imprimir();
                                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);
                                            listos.imprimir();
                                            listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                                        }

                                    }

                                    fifo.aumentarListos();
                                    fifo.robin.aumentarListos();

                                    //---------------- Semáforo ---------------------------
                                    interfaz.getlblSemaforo().setBackground(Color.red);
                                    interfaz.getlblSemaforo().setText("En uso");
                                    //-----------------------------------------------------

                                    diagramaGantt(en_ejecucion, 1, Integer.parseInt(interfaz.getTiempo_real().getText()));

                                    if (en_ejecucion.tiempo_cpu == en_ejecucion.tiempo_final) {//verifica que cumpla el quantum y agregue a cola de atendidos                                       

                                        interfaz.getTiempo_cpu().setText(en_ejecucion.tiempo_cpu + "");
                                        /*sleep(2000);
                                         sleep(1000);*/
                                        //.showMessageDialog(null, "Finaliza proceso " + listos.p.sig.proceso);
                                        //---------------- Semáforo ---------------------------
                                        interfaz.getlblSemaforo().setBackground(Color.green);
                                        interfaz.getlblSemaforo().setText("Vacía");
                                        //-----------------------------------------------------

                                        actualizarAtendidos(en_ejecucion);
                                        atendidos.agregar(en_ejecucion);
                                        listos.eliminarNodo(en_ejecucion);
                                        listos.imprimir();
                                        listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());

                                        //----------------------- Despachador ------------------------------------
                                        if (this.fifo.robin.getTotalNumNuevo() != 0) {
                                            //JOptionPane.showMessageDialog(null, "Entrará el proceso " + this.fifo.robin.listos.p.sig.proceso);
                                        } else {
                                            if (this.getTotalNumNuevo() != 0) {
                                                //JOptionPane.showMessageDialog(null, "Entrará el proceso de Cola 2");
                                            } else {
                                                if (this.fifo.listos.p.sig.proceso != 0) {
                                                    //JOptionPane.showMessageDialog(null, "Entrará el proceso " + this.fifo.listos.p.sig.proceso);
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

                                    //this.agregarProceso();
                                }//end if compararrestante
                                else {

                                    i = f;
                                    //sleep(1500);
                                    suspendidos.agregar(en_ejecucion);
                                    listos.eliminarNodo(en_ejecucion);
                                    //suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                    listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                                    //sleep(1500);
                                    listos.agregar(suspendidos.p.sig);
                                    suspendidos.eliminarNodo(suspendidos.p.sig);
                                    //suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                    listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                                    comprobarPrioridad();
                                }
                            } else {
                                i = f;
                            }

                            if (bandera) {
                                Nodo impr;
                                impr = en_ejecucion;
                                if (i > 1 && i < impr.tiempo_final && listos.p.sig.tiempo_cpu < 100 && (listos.p != null || suspendidos.p != null || bloqueados.p != null)) {
                                    i = impr.tiempo_final;

                                    bloqueados.agregar(en_ejecucion);
                                    listos.eliminarNodo(en_ejecucion);
                                    bloqueados.imprimir();
                                    bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());

                                    JOptionPane.showMessageDialog(null, "Proceso " + impr.proceso + " bloqueado.");
                                } else {
                                    //.showMessageDialog(null, "No se puede bloquear el proceso en este momento.");
                                }
                                bandera = false;
                            }

                            // -------------------- Llegan nuevos procesos de forma dinámica--------
//                            nu = r.nextInt(30);
//                            if (nu == 29 || nu == 15 || nu == 2) {
//                                this.fifo.robin.agregar_proceso = true;
//                                this.fifo.robin.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 1");
//                                this.fifo.robin.listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
//                            } else if (nu == 11) {
//                                this.agregar_proceso = true;
//                                this.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 2");
//                                this.listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
//                            } else if (nu == 5) {
//                                this.fifo.agregar_proceso = true;
//                                this.fifo.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 3");
//                                this.fifo.listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
//                            }
                            //----------------------------------------------------------------------*/
                        }

                    } else {//si no hay recurso para un proceso lo envía a cola de bloqueados
                        //sleep(1000);                        
                        //sleep(1000);
                        bloqueados.agregar(en_ejecucion);
                        listos.eliminarNodo(en_ejecucion);
                        bloqueados.imprimir();
                        bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);
                        listos.imprimir();
                        listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                        //this.agregarProceso();
                        //sleep(500);

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
                        this.fifo.stop();
                        this.fifo.robin.stop();
                    }
                    //----------------------------------------------------

                    envejecimiento();
                    if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                        comprobarPrioridad();
                        Nodo auxiliar_b = bloqueados.p.sig;
                        fifo.aumentarListos();
                        fifo.robin.aumentarListos();
                        for (int j = 1; j <= bloqueados.num; j++) {
                            auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                            auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;

                            //---------------- Semáforo ---------------------------
                            interfaz.getlblSemaforo().setBackground(Color.green);
                            interfaz.getlblSemaforo().setText("Vacía");
                            //-----------------------------------------------------

                            diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);
                            auxiliar_b = auxiliar_b.sig;
                        }

                        if (hayRecurso2()) {
                            /*sleep(3000);
                             sleep(2000);*/
                            listos.agregar(bloqueados.p.sig);
                            bloqueados.eliminarNodo(bloqueados.p.sig);
                            bloqueados.imprimir();
                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);
                            listos.imprimir();
                            listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                        }

                    }

                }

                if ((listos.p == listos.p.sig) && (bloqueados.p == bloqueados.p.sig) && (suspendidos.p == suspendidos.p.sig)) {
                    if (this.fifo.robin.getTotalNumNuevo() != 0 || this.fifo.getTotalNumNuevo() != 0) {
                        if (this.fifo.robin.getTotalNumNuevo() != 0) {
                            this.fifo.robin.resume();
                            encurso = false;
                            this.suspend();
                        } else if (this.fifo.getTotalNumNuevo() != 0) {
                            this.fifo.resume();
                            encurso = false;
                            this.suspend();
                        }
                    } else if (this.fifo.robin.getTotalNumNuevo() == 0 && this.fifo.getTotalNumNuevo() == 0) {
                        sleep(5000);
                        JOptionPane.showMessageDialog(null, "Ejecución Finalizada");
                        this.stop();
                    }

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Srtf.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public boolean hayRecurso() {//verifica si existe el recurso correspondiente a ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        return x < 19;
    }

    public void agregarProceso() {//agrega un nuevo proceso en cualquier instante de tiempo
        if (agregar_proceso == true) {
            int nuevo_num = getTotalNum() + fifo.getTotalNum() + fifo.robin.getTotalNum() + 1;
            agregar_proceso = false;
            listos.agregarProceso(nuevo_num);
        }
        listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
    }

    public int getTotalNum() {
        return listos.num + bloqueados.num + suspendidos.num + atendidos.num;
    }

    public int getTotalNumNuevo() {
        return listos.num + bloqueados.num + suspendidos.num;
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

    public boolean CompararRestante(Nodo actual) {

        Nodo comparado;
        Nodo aux_actual = actual;
        Nodo aux_inicial = listos.p.sig;
        comparado = aux_inicial.sig;
        while (comparado != listos.p) {
            if (aux_inicial.getTiempo_restante() > comparado.getTiempo_restante()) {
                aux_inicial = comparado;
            }
            comparado = comparado.sig;
        }

        actual = aux_inicial;
        return aux_actual == actual;

    }

    public boolean comprobarPrioridad() throws InterruptedException {
        if (fifo.robin.getTotalNumNuevo() != 0) {
            //sleep(2000);
            fifo.robin.resume();
            this.suspend();
            return false;
        }
        return true;
    }

    public void aumentarListos() {
        //revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
        Nodo auxlis = listos.p.sig;
        Nodo auxbloq = bloqueados.p.sig;
        for (int li = 1; li <= listos.num; li++) {

            auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
            diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
            auxlis = auxlis.sig;
            listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
        }

        for (int bloq = 1; bloq <= bloqueados.num; bloq++) {

            auxbloq.tiempo_en_espera = auxbloq.tiempo_en_espera + 1;
            auxbloq.tiempo_bloqueado = auxbloq.tiempo_bloqueado + 1;
            diagramaGantt(auxbloq, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
            auxbloq = auxbloq.sig;
            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), fifo.robin.bloqueados, this.bloqueados, fifo.bloqueados);

        }

    }

    public void diagramaGantt(Nodo actual, int estado, int transcurrido) {//actualiza diagrama de gantt desde la interfaz gráfica
        String fase;
        switch (estado) {
            case 1:
                fase = "X2";
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

    public void actualizarAtendidos(Nodo nuevoatendido) {
        atendidos_total.agregar(nuevoatendido);
        atendidos_total.imprimir(interfaz.getTexto_atendidos(), interfaz.getTotal_atendidos());
    }

    public void envejecimiento() throws InterruptedException {
        Nodo auxfifo = fifo.listos.p.sig;
        for (int i = 1; i <= fifo.listos.num; i++) {
            if (auxfifo.tiempo_en_espera > 20) {
                //sleep(2500);
                //JOptionPane.showMessageDialog(null, "El proceso " + fifo.listos.p.sig.proceso + " por envejecimiento pasará a Cola 2");
                fifo.listos.eliminarNodo(auxfifo);
                listos.agregar(auxfifo);
                listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
                fifo.listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
            }
            auxfifo = auxfifo.sig;
        }

    }
}
