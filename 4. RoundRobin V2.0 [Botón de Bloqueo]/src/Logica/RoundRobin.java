/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Cristian
 */
public class RoundRobin extends Thread {

    public Cola listos = new Cola();
    public Cola bloqueados = new Cola();
    public Cola suspendidos = new Cola();
    public Cola atendidos = new Cola();

    private Interfaz interfaz;
    int conteo_tiempo = 0;//variable usada para graficar el primer diagrama de gantt
    String diagramaGant1 = "";//variable usada para graficar el primer diagrama de gantt
    int cont = 0;
    int np = 0;
    Random r = new Random();
    int rnd = 2 + r.nextInt(3);
    boolean b;
    boolean bandera = false;

    public RoundRobin(Interfaz interfaz) {
        this.interfaz = interfaz;
    }

    @Override
    public void run() {//atender los procesos
        //da un número aleatorio de acuerdo al promedio de los tiempos que se dan
        listos.nuevo();//crea la lista
        suspendidos.nuevo();//crea la lista
        bloqueados.nuevo();//crea la lista
        atendidos.nuevo();//crea la lista
        listos.procesos();//asigna procesos aleatorios a la cola de listos

        listos.imprimir();
        listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());

        while (true)//comprueba que queden procesos por atender
        {
            try {
                //sleep(2000);
                if (listos.p.sig != listos.p) {
                    if (hayRecurso()) {//verifica que tenga recursos el proceso en curso para proceder a ejecutarlo

                        interfaz.getProcesos_en_ejecucion().setText(listos.p.sig.proceso + "");
                        //sleep(2000);
                        int f = listos.p.sig.tiempo_estimado;

                        for (int i = 1; i <= f; i++) {
                            //JOptionPane.showMessageDialog(null, "F = "+f);
                            sleep(1000);
                            interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");
                            cont = cont + 1;
                            if (cont == 90) {
                                JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                                interfaz.getjButton10().setEnabled(false);
                                interfaz.getjButton11().setEnabled(false);
                                stop();
                            }
                            interfaz.getCpu_nuevo().setText(listos.p.sig.tiempo_final + "");
                            listos.p.sig.setTiempo_cpu(listos.p.sig.tiempo_cpu + 1);
                            interfaz.getTiempo_cpu().setText(listos.p.sig.tiempo_cpu + "");
                            interfaz.getProcesos_en_ejecucion().setText(listos.p.sig.proceso + "");
                            listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());

                            if (listos.num > 1) {//revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
                                Nodo auxlis = listos.p.sig.sig;
                                for (int li = 2; li <= listos.num; li++) {

                                    auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
                                    diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                    auxlis = auxlis.sig;
                                    listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                                }
                            }

                            if (suspendidos.p.sig != suspendidos.p) {//revisa si hay algo en cola de suspendidos y actualiza su tiempo                                
                                Nodo auxiliar = suspendidos.p.sig;
                                int x = suspendidos.num;
                                for (int k = 1; k <= x; k++) {
                                    auxiliar.setTiempo_suspendido(auxiliar.getTiempo_suspendido() + 1);
                                    auxiliar.tiempo_en_espera = auxiliar.tiempo_en_espera + 1;
                                    diagramaGantt(auxiliar, 4, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                    suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
           
                                    if (auxiliar.getTiempo_suspendido() == auxiliar.quantum_suspendido) {
                                        Random rnds = new Random();
                                        //sleep(1000);
                                        auxiliar.setQuantum_suspendido(auxiliar.quantum_suspendido);
                                        listos.agregar(auxiliar);
                                        suspendidos.eliminarNodo(auxiliar);
                                        suspendidos.imprimir();
                                        JOptionPane.showMessageDialog(null, "Proceso " + auxiliar.proceso + " suspendido.");
                                        suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());                                        
                                        listos.imprimir();
                                        listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                                    }
                                    auxiliar = auxiliar.sig;
                                }
                            }

                            if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                                Nodo auxiliar_b = bloqueados.p.sig;

                                for (int j = 1; j <= bloqueados.num; j++) {
                                    auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                                    auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;
                                    diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                    //JOptionPane.showMessageDialog(null, "Proceso " + auxiliar_b.proceso + " bloqueado.");
                                    bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
                                    auxiliar_b = auxiliar_b.sig;
                                }
                                if (hayRecurso2()) {
                                    //sleep(3000);                                                                                                            
                                    listos.agregar(bloqueados.p.sig);
                                    if (bloqueados.p.sig.sig == bloqueados.p) {
                                        bloqueados.eliminarPrimero();
                                    } else {
                                        bloqueados.eliminarPrimeroListos();
                                    }
                                    bloqueados.imprimir();
                                    bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
                                    listos.imprimir();
                                    listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                                }
                            }

                            diagramaGantt(listos.p.sig, 1, Integer.parseInt(interfaz.getTiempo_real().getText()));

                            if (listos.p.sig.tiempo_cpu % 5 == 0) {
                                sleep(1000); ///ojo cambie el sleep de 500 a 1000
                            }

                            if (cumpleQuantum(listos.p.sig)) {//verifica que cumpla el quantum y agregue a cola de atendidos                                
                                terminar(listos.p.sig.proceso, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                interfaz.getTiempo_cpu().setText(listos.p.sig.tiempo_cpu + "");
                                //sleep(2000);
                                atendidos.agregar(listos.p.sig);
                                if (listos.p.sig.sig == listos.p) {
                                    listos.eliminarPrimero();
                                } else {
                                    listos.eliminarPrimeroListos();
                                }
                                listos.imprimir();
                                listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                                //sleep(2000);
                                i = f;
                                atendidos.imprimir();
                                interfaz.getTiempo_cpu().setText("");
                                interfaz.getProcesos_en_ejecucion().setText("");
                                interfaz.getCpu_nuevo().setText("");

                            } else {
                                if (i == listos.p.sig.tiempo_estimado) {//revisa si un proceso no ha sido ejecutado en su limite de quantum y lo agregaa suspendidos
                                    Random rndt = new Random();
                                    listos.p.sig.setTiempo_estimado(listos.p.sig.tiempo_estimado);
                                    terminar(listos.p.sig.proceso, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                    interfaz.getTiempo_cpu().setText(listos.p.sig.tiempo_cpu + "");
                                    //sleep(3000);
                                    suspendidos.agregar(listos.p.sig);
                                    if (listos.p.sig.sig == listos.p) {
                                        listos.eliminarPrimero();
                                    } else {
                                        listos.eliminarPrimeroListos();
                                    }
                                    listos.imprimir();
                                    listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                                    suspendidos.imprimir();
                                    suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                    interfaz.getTiempo_cpu().setText("");
                                    interfaz.getProcesos_en_ejecucion().setText("");
                                    interfaz.getCpu_nuevo().setText("");
                                }
                            }

                            //-------------------------------> PRUEBA <------------------------------/  
                            if (bandera) {
                                if (!hayRecurso3() && i > 1 && i < 5 && listos.p.sig.tiempo_cpu < 100 && (listos.p != null || suspendidos.p != null || bloqueados.p != null)) {
                                    i = 5;
                                    Nodo impr;
                                    impr = listos.p.sig;
                                    bloqueados.agregar(listos.p.sig);
                                    if (listos.p.sig.sig == listos.p) {
                                        listos.eliminarPrimero();
                                    } else {
                                        listos.eliminarPrimeroListos();
                                    }
                                    bloqueados.imprimir();
                                    bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
                                    listos.imprimir();
                                    listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());

                                    JOptionPane.showMessageDialog(null, "El proceso " + impr.proceso + " ha sido bloqueado.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se puede bloquear el proceso en este momento.");
                                }
                                bandera = false;
                            }

                            // Se ingresan nuevos procesos de forma dinámica y aleatoria
                            b = r.nextBoolean();
                            System.out.println("b: " + b + ", np: " + np + ", rnd: " + rnd);
                            if (b) {
                                if (np < rnd) {
                                    JOptionPane.showMessageDialog(null, "Llega un nuevo proceso.");
                                    interfaz.obj.agregarProceso();
                                    np++;
                                    if (np == rnd && cont > 40) {
                                        np = 0;
                                        rnd = 1 + r.nextInt(4);
                                    }
                                }
                            }

                            /// ---------------------------> FIN PRUEBA  <---------------------------//
                        }

                    } else {//si no hay recurso para un proceso lo envía a cola de bloqueados
                        //JOptionPane.showMessageDialog(null, "No hubo recurso 1");
                        //sleep(1000);                                                
                        bloqueados.agregar(listos.p.sig);
                        if (listos.p.sig.sig == listos.p) {
                            listos.eliminarPrimero();
                        } else {
                            listos.eliminarPrimeroListos();
                        }
                        bloqueados.imprimir();
                        bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
                        listos.imprimir();
                        listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                        //sleep(500);
                    }
                } else {//si no hay nada en listos actualiza el tiempo para las otras colas                   
                    sleep(1000);
                    interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");
                    cont = cont + 1;
                    if (cont == 46) {
                        JOptionPane.showMessageDialog(null, "Tiempo Cumplido");
                        stop();
                    }
                    if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                        Nodo auxiliar_b = bloqueados.p.sig;

                        for (int j = 1; j <= bloqueados.num; j++) {
                            auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                            auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;
                            diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
                            auxiliar_b = auxiliar_b.sig;
                        }
                        if (hayRecurso2()) {
                            //sleep(2000);
                            listos.agregar(bloqueados.p.sig);
                            if (bloqueados.p.sig.sig == bloqueados.p) {
                                bloqueados.eliminarPrimero();
                            } else {
                                bloqueados.eliminarPrimeroListos();
                            }
                            bloqueados.imprimir();
                            bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados());
                            listos.imprimir();
                            listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                        }
                    }

                    if (suspendidos.p.sig != suspendidos.p) {//revisa si hay algo en cola de suspendidos y actualiza su tiempo
                        Nodo auxiliar = suspendidos.p.sig;

                        int x = suspendidos.num;
                        for (int k = 1; k <= x; k++) {
                            auxiliar.setTiempo_suspendido(auxiliar.getTiempo_suspendido() + 1);
                            auxiliar.tiempo_en_espera = auxiliar.tiempo_en_espera + 1;
                            diagramaGantt(auxiliar, 4, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                            if (auxiliar.getTiempo_suspendido() == auxiliar.quantum_suspendido) {
                                Random rnds = new Random();
                                //sleep(1000);
                                auxiliar.setQuantum_suspendido(auxiliar.quantum_suspendido /*- rnds.nextInt(auxiliar.quantum_suspendido - 1)*/);
                                //sleep(2000);
                                listos.agregar(auxiliar);
                                suspendidos.eliminarNodo(auxiliar);
                                suspendidos.imprimir();
                                suspendidos.imprimir(interfaz.getTexto_suspendidos(), interfaz.getTotal_suspendidos());
                                listos.imprimir();
                                listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
                            }
                            auxiliar = auxiliar.sig;
                        }
                    }
                }

                if ((listos.p == listos.p.sig) && (bloqueados.p == bloqueados.p.sig) && (suspendidos.p == suspendidos.p.sig)) {
                    JOptionPane.showMessageDialog(null, "Procesos Finalizados");
                    interfaz.getjButton10().setEnabled(false);
                    interfaz.getjButton11().setEnabled(false);
                    this.suspend();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
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
        int nuevo_num = listos.num + atendidos.num + bloqueados.num + suspendidos.num + 1;
        listos.agregarProceso(nuevo_num);
        listos.imprimirListos(interfaz.getTexto_listos(), interfaz.getTotal_listos());
        //JOptionPane.showMessageDialog(null, "Se agrega un nuevo proceso.");
    }

    public boolean hayRecurso2() {//Determina si existe un recurso para ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        if (x < 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hayRecurso3() {
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        if (x < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cumpleQuantum(Nodo nodo) {//verifica si se ha terminado el tiempo de ejecución de un proceso

        if (nodo.tiempo_final == nodo.tiempo_cpu) {
            return true;
        } else {
            return false;
        }

    }

    public void terminar(int num_proceso, int transcurrido) {//actualiza la priemra parte edl diagrama de gantt con el tiempo de los procesos que pasan por cpu
        if (listos.p.sig.tiempo_cpu == listos.p.sig.tiempo_final) {// Si el tiempo de cpu es igual al tiempo final, el proceso ha sido atendido
            JOptionPane.showMessageDialog(null, "Finaliza Proceso " + num_proceso);
            diagramaGant1 = diagramaGant1 + "Proceso " + num_proceso + "\n";
            interfaz.getTexto_diagrama().setText(diagramaGant1 + "\n");
        }

    }

    public void diagramaGantt(Nodo actual, int estado, int transcurrido) {//actualiza diagrama de gantt desde la interfaz gráfica
        String fase;

        if (estado == 1) {
            fase = "X";
        } else if (estado == 2) {
            fase = "E";
        } else if (estado == 3) {
            fase = "B";
        } else if (estado == 4) {
            fase = "S";
        } else {
            fase = null;
        }
        interfaz.getjTable1().setValueAt(fase, actual.proceso - 1, transcurrido);
        interfaz.getjTable1().setDefaultRenderer(Object.class, new MiRender());
        //interfaz.getjTable1().setValueAt(actual.tiempo_en_espera, actual.proceso - 1, 98);
        //interfaz.getjTable1().setValueAt(actual.tiempo_en_espera + actual.tiempo_cpu, actual.proceso - 1, 99);
    }
}
