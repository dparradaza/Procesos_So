package roundRobin;

import Vista.Interfaz;
import Vista.MiRender;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import srtf.Srtf;

public class RoundRobin extends Thread {

    public Cola listos = new Cola();
    public Cola bloqueados = new Cola();
    public Cola suspendidos = new Cola();
    public Cola atendidos = new Cola();
    public Cola atendidos_total = new Cola();
    public Srtf srtf;

    public Interfaz interfaz;
    int conteo_tiempo = 0;//variable usada para graficar el primer diagrama de gantt    
    public boolean agregar_proceso = false;
    public boolean encurso = false;

    Random r = new Random();
    int nu;

    public boolean bandera = false;

    public RoundRobin(Interfaz interfaz, Srtf srtf) {
        this.interfaz = interfaz;
        this.srtf = srtf;
        listos.nuevo();//crea la lista
        suspendidos.nuevo();//crea la lista
        bloqueados.nuevo();//crea la lista
        atendidos.nuevo();//crea la lista
        atendidos_total.nuevo();
        listos.procesos(1);//asigna procesos aleatorios a la cola de listos
        srtf.setAtendidos_total(atendidos_total);
        srtf.fifo.setAtendidos_total(atendidos_total);
    }

    @Override
    public void run() {//atender los procesos
        //da un número aleatorio de acuerdo al promedio de los tiempos que se dan
        this.srtf.listos.imprimir(this.interfaz.getTexto_interactivos(), this.interfaz.getTotal_interactivos());
        this.srtf.fifo.listos.imprimir(this.interfaz.getTexto_usuarios(), this.interfaz.getTotal_usuarios());
        listos.imprimir();
        listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
        while (true) {//comprueba que queden procesos por atender
            encurso = true;
            srtf.suspend();
            srtf.fifo.suspend();
            try {
                //sleep(1000);
                //this.agregarProceso();
                if (listos.p.sig != listos.p) {
                    envejecimiento();
                    if (hayRecurso()) {//verifica que tenga recursos el proceso en curso para proceder a ejecutarlo

                        interfaz.getProcesos_en_ejecucion().setText(listos.p.sig.proceso + "");
                        int f = listos.p.sig.tiempo_estimado;

                        for (int i = 1; i <= f; i++) {

                            envejecimiento();
                            sleep(1000);
                            interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");

                            //--------------------------- Finalizar --------------------------
                            if (Integer.parseInt(interfaz.getTiempo_real().getText()) == 90) {
                                JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                                interfaz.getlblSemaforo().setBackground(Color.green);
                                interfaz.getlblSemaforo().setText("Vacía");
                                this.stop();
                                this.srtf.stop();
                                this.srtf.fifo.stop();
                            }
                            //----------------------------------------------------

                            interfaz.getCpu_nuevo().setText(listos.p.sig.tiempo_final + "");

                            listos.p.sig.setTiempo_cpu(listos.p.sig.tiempo_cpu + 1);
                            interfaz.getTiempo_cpu().setText(listos.p.sig.tiempo_cpu + "");
                            interfaz.getProcesos_en_ejecucion().setText(listos.p.sig.proceso + "");
                            listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());

                            if (listos.num > 1) {//revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
                                Nodo auxlis = listos.p.sig.sig;
                                for (int li = 2; li <= listos.num; li++) {

                                    auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
                                    diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                    auxlis = auxlis.sig;
                                    listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
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
                                    bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, srtf.bloqueados, srtf.fifo.bloqueados);
                                    auxiliar_b = auxiliar_b.sig;
                                }

                                if (hayRecurso2()) {
                                    /*sleep(3000);
                                     sleep(2000);*/
                                    listos.agregar(bloqueados.p.sig);
                                    bloqueados.eliminarNodo(bloqueados.p.sig);
                                    bloqueados.imprimir();
                                    bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, srtf.bloqueados, srtf.fifo.bloqueados);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                                }

                            }

                            if (suspendidos.p.sig != suspendidos.p) {//revisa si hay algo en cola de suspendidos y actualiza su tiempo
                                Nodo auxiliar = suspendidos.p.sig;

                                int x = suspendidos.num;
                                for (int k = 1; k <= x; k++) {
                                    auxiliar.setTiempo_suspendido(auxiliar.getTiempo_suspendido() + 1);
                                    auxiliar.tiempo_en_espera = auxiliar.tiempo_en_espera + 1;

                                    //---------------- Semáforo ---------------------------
                                    interfaz.getlblSemaforo().setBackground(Color.green);
                                    interfaz.getlblSemaforo().setText("Vacía");
                                    //-----------------------------------------------------

                                    diagramaGantt(auxiliar, 4, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                    suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                    if (auxiliar.getTiempo_suspendido() == auxiliar.quantum_suspendido) {
                                        Random rnds = new Random();
                                        auxiliar.setQuantum_suspendido(auxiliar.quantum_suspendido /*- rnds.nextInt(auxiliar.quantum_suspendido - 1)*/);
                                        listos.agregar(auxiliar);
                                        suspendidos.eliminarNodo(auxiliar);
                                        suspendidos.imprimir();
                                        suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                        listos.imprimir();
                                        listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                                    }
                                    auxiliar = auxiliar.sig;

                                }

                            }
                            //---------------------- Semáforo -------------------------------------
                            interfaz.getlblSemaforo().setBackground(Color.red);
                            interfaz.getlblSemaforo().setText("En uso");
                            //---------------------------------------------------------------------

                            diagramaGantt(listos.p.sig, 1, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            this.srtf.aumentarListos();
                            this.srtf.fifo.aumentarListos();
                            if (cumpleQuantum(listos.p.sig)) {//verifica que cumpla el quantum y agregue a cola de atendidos

                                //   tiempo_real.Asignar(listos.p.sig.tiempo_transcurrido);
                                // if(listos.p.sig.tiempo_cpu<=listos.p.sig.tiempo_estimado)nuevo_cpu2=listos.p.sig.tiempo_cpu;
                                //else nuevo_cpu2=listos.p.sig.tiempo_cpu-listos.p.sig.tiempo_estimado;
                                interfaz.getTiempo_cpu().setText(listos.p.sig.tiempo_cpu + "");
                                /*sleep(3000);
                                 sleep(2000);*/
                                JOptionPane.showMessageDialog(null, "Finaliza proceso " + listos.p.sig.proceso);

                                //---------------- Semáforo ---------------------------
                                interfaz.getlblSemaforo().setBackground(Color.green);
                                interfaz.getlblSemaforo().setText("Vacía");
                                //-----------------------------------------------------

                                actualizarAtendidos(listos.p.sig);
                                atendidos.agregar(listos.p.sig);
                                listos.eliminarNodo(listos.p.sig);
                                listos.imprimir();
                                listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());

                                //----------------------- Despachador ------------------------------------
                                if (this.getTotalNumNuevo() != 0) {
                                    JOptionPane.showMessageDialog(null, "Entrará el proceso " + listos.p.sig.proceso);
                                } else {
                                    if (this.srtf.getTotalNumNuevo() != 0) {
                                        JOptionPane.showMessageDialog(null, "Entrará el proceso de Cola 2");
                                    } else {
                                        if (this.srtf.fifo.listos.p.sig.proceso != 0) {
                                            JOptionPane.showMessageDialog(null, "Entrará el proceso " + this.srtf.fifo.listos.p.sig.proceso);
                                        }
                                    }
                                }
                                //------------------------------------------------------------------------

                                sleep(1000);
                                i = f;
                                interfaz.getTiempo_cpu().setText("");
                                interfaz.getProcesos_en_ejecucion().setText("");
                                interfaz.getCpu_nuevo().setText("");

                            } else {
                                if (i == listos.p.sig.tiempo_estimado) {//revisa si un proceso no ha sido ejecutado en su limite de quantum y lo agregaa suspendidos
                                    Random rndt = new Random();
                                    listos.p.sig.setTiempo_estimado(listos.p.sig.tiempo_estimado /**
                                     * (1 + rndt.nextInt(3)) + 1
                                     */
                                    );
                                    //  tiempo_real.Asignar(listos.p.sig.tiempo_transcurrido);

                                    //  if(listos.p.sig.tiempo_cpu<=listos.p.sig.tiempo_estimado)nuevo_cpu=listos.p.sig.tiempo_cpu;
                                    //else nuevo_cpu=listos.p.sig.tiempo_cpu-listos.p.sig.tiempo_estimado;                                    
                                    interfaz.getTiempo_cpu().setText(listos.p.sig.tiempo_cpu + "");
                                    //sleep(3000);
                                    suspendidos.agregar(listos.p.sig);
                                    listos.eliminarNodo(listos.p.sig);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                                    suspendidos.imprimir();
                                    suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                    interfaz.getTiempo_cpu().setText("");
                                    interfaz.getProcesos_en_ejecucion().setText("");
                                    interfaz.getCpu_nuevo().setText("");
                                }
                            }

                            if (bandera) {
                                if (i > 1 && i < 5 && listos.p.sig.tiempo_cpu < 100 && (listos.p != null || suspendidos.p != null || bloqueados.p != null)) {
                                    i = 5;
                                    Nodo impr;
                                    impr = listos.p.sig;
                                    bloqueados.agregar(listos.p.sig);
                                    listos.eliminarNodo(listos.p.sig);
                                    bloqueados.imprimir();
                                    bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, srtf.bloqueados, srtf.fifo.bloqueados);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());

                                    JOptionPane.showMessageDialog(null, "El proceso " + impr.proceso + " ha sido bloqueado.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se puede bloquear el proceso en este momento.");
                                }
                                bandera = false;
                            }

                            //this.agregarProceso();
                            // -------------------- Llegan nuevos procesos de forma dinámica--------
//                            nu = r.nextInt(30);
//                            if (nu == 45) {
//                                this.agregar_proceso = true;
//                                this.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 1");
//                                this.listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
//                            } else if (nu == 1) {
//                                this.srtf.agregar_proceso = true;
//                                this.srtf.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 2");
//                                this.srtf.listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
//                            } else if (nu == 25) {
//                                this.srtf.fifo.agregar_proceso = true;
//                                this.srtf.fifo.agregarProceso();
//                                JOptionPane.showMessageDialog(null, "Llega nuevo proceso a Cola 3");
//                                this.srtf.fifo.listos.imprimir(interfaz.getTexto_usuarios(), interfaz.getTotal_usuarios());
//                            }
                            //----------------------------------------------------------------------*/
                        }

                    } else {//si no hay recurso para un proceso lo envía a cola de bloqueados
                        /*sleep(1000);

                         sleep(1000);*/
                        bloqueados.agregar(listos.p.sig);
                        listos.eliminarNodo(listos.p.sig);
                        bloqueados.imprimir();
                        bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, srtf.bloqueados, srtf.fifo.bloqueados);
                        listos.imprimir();
                        listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                        //this.agregarProceso();
                        //sleep(500);

                    }
                } else {//si no hay nada en listos actualiza el tiempo para las otras colas       
                    envejecimiento();
                    sleep(1000);
                    interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");

                    //--------------------------- Finalizar --------------------------
                    if (Integer.parseInt(interfaz.getTiempo_real().getText()) == 90) {
                        JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                        interfaz.getlblSemaforo().setBackground(Color.green);
                        interfaz.getlblSemaforo().setText("Vacía");
                        this.stop();
                        this.srtf.stop();
                        this.srtf.fifo.stop();
                    }
                    //----------------------------------------------------

                    if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                        Nodo auxiliar_b = bloqueados.p.sig;
                        this.srtf.aumentarListos();
                        this.srtf.fifo.aumentarListos();
                        for (int j = 1; j <= bloqueados.num; j++) {
                            auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                            auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;

                            //---------------- Semáforo ---------------------------
                            interfaz.getlblSemaforo().setBackground(Color.green);
                            interfaz.getlblSemaforo().setText("Vacía");
                            //-----------------------------------------------------

                            diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, srtf.bloqueados, srtf.fifo.bloqueados);
                            auxiliar_b = auxiliar_b.sig;
                        }

                        if (hayRecurso2()) {
                            /*sleep(2000);
                             sleep(2000);*/
                            listos.agregar(bloqueados.p.sig);
                            bloqueados.eliminarNodo(bloqueados.p.sig);
                            bloqueados.imprimir();
                            bloqueados.imprimirBloqueados(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), this.bloqueados, srtf.bloqueados, srtf.fifo.bloqueados);
                            listos.imprimir();
                            listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                        }
                    }

                    if (suspendidos.p.sig != suspendidos.p) {//revisa si hay algo en cola de suspendidos y actualiza su tiempo
                        Nodo auxiliar = suspendidos.p.sig;
                        this.srtf.aumentarListos();
                        this.srtf.fifo.aumentarListos();
                        int x = suspendidos.num;
                        for (int k = 1; k <= x; k++) {
                            auxiliar.setTiempo_suspendido(auxiliar.getTiempo_suspendido() + 1);
                            auxiliar.tiempo_en_espera = auxiliar.tiempo_en_espera + 1;

                            //---------------- Semáforo ---------------------------
                            interfaz.getlblSemaforo().setBackground(Color.green);
                            interfaz.getlblSemaforo().setText("Vacía");
                            //-----------------------------------------------------

                            diagramaGantt(auxiliar, 4, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                            if (auxiliar.getTiempo_suspendido() == auxiliar.quantum_suspendido) {
                                Random rnds = new Random();
                                /*sleep(1000);
                                 sleep(1000);*/
                                auxiliar.setQuantum_suspendido(auxiliar.quantum_suspendido /*- rnds.nextInt(auxiliar.quantum_suspendido - 1)*/);
                                listos.agregar(auxiliar);
                                suspendidos.eliminarNodo(auxiliar);
                                suspendidos.imprimir();
                                suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                listos.imprimir();
                                listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                            }
                            auxiliar = auxiliar.sig;
                        }
                    }
                }
                if ((listos.p == listos.p.sig) && (bloqueados.p == bloqueados.p.sig) && (suspendidos.p == suspendidos.p.sig)) {
                    if (this.srtf.getTotalNumNuevo() == 0 && this.srtf.fifo.getTotalNumNuevo() == 0) {
                        JOptionPane.showMessageDialog(null, "Ejecución Finalizada");
                        this.stop();
                    } else if (this.srtf.getTotalNumNuevo() != 0) {
                        this.srtf.resume();
                        encurso = false;
                        this.suspend();
                    } else if (this.srtf.fifo.getTotalNumNuevo() != 0) {
                        this.srtf.fifo.resume();
                        encurso = false;
                        this.suspend();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public boolean hayRecurso() {//verifica si existe el recurso correspondiente a ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        return x < 19;
    }

    public void agregarProceso() {//agrega un nuevo proceso en cualquier instante de tiempo
        System.out.print(agregar_proceso);
        if (agregar_proceso == true) {
            int nuevo_num = getTotalNum() + srtf.getTotalNum() + srtf.fifo.getTotalNum() + 1;
            agregar_proceso = false;
            listos.agregarProceso(nuevo_num);
        }
        listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
    }

    public int getTotalNum() {
        return listos.num + bloqueados.num + suspendidos.num + atendidos.num;
    }

    public int getTotalNumNuevo() {
        return listos.num + bloqueados.num + suspendidos.num;
    }

    public void actualizarAtendidos(Nodo nuevoatendido) {
        atendidos_total.agregar(nuevoatendido);
        atendidos_total.imprimir(interfaz.getTexto_atendidos(), interfaz.getTotal_atendidos());
    }

    public boolean hayRecurso2() {//Determina si existe un recurso para ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        return x < 15;
    }

    public boolean cumpleQuantum(Nodo nodo) { //verifica si se ha terminado el tiempo de ejecución de un proceso
        return nodo.tiempo_final == nodo.tiempo_cpu;
    }

    public void aumentarListos() {
        //revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
        Nodo auxlis = listos.p.sig;
        for (int li = 1; li <= listos.num; li++) {
            auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
            diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
            auxlis = auxlis.sig;
            listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
        }
    }
    
    //actualiza diagrama de gantt desde la interfaz gráfica
    public void diagramaGantt(Nodo actual, int estado, int transcurrido) {
        String fase;
        switch (estado) {
            case 1:
                fase = "X";
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

    public void envejecimiento() throws InterruptedException {
        srtf.envejecimiento();
        Nodo auxsrtf = srtf.listos.p.sig;
        for (int i = 1; i <= srtf.listos.num; i++) {
            if (auxsrtf.tiempo_en_espera > 30) {
                //sleep(2500);                
                //JOptionPane.showMessageDialog(null, "El proceso " + auxsrtf.proceso + " por envejecimiento pasará a Cola 1");
                srtf.listos.eliminarNodo(auxsrtf);
                listos.agregar(auxsrtf);
                listos.imprimir(interfaz.getTexto_sistema(), interfaz.getTotal_sistema());
                srtf.listos.imprimir(interfaz.getTexto_interactivos(), interfaz.getTotal_interactivos());
            }
            auxsrtf = auxsrtf.sig;
        }
    }
}
