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

/**
 *
 * @author Cristian
 */
public class Prioridad extends Thread {

    public Cola listos = new Cola();

    public Cola bloqueados = new Cola();
    public Cola suspendidos = new Cola();
    public Cola atendidos = new Cola();
    public boolean agregar_proceso = false;

    private Interfaz interfaz;
    int conteo_tiempo = 0;//variable usada para graficar el primer diagrama de gantt
    String diagramaGant1 = "   ";//variable usada para graficar el primer diagrama de gantt
    int cont = 0;
    int np = 0; //variable para controlar la agregacion de procesos
    int rn = (int) Math.floor(Math.random() * (5) + 1); //variable para controlar la agregacion de procesos;Math.random()*ValorLimite+ValorInicial

    public Prioridad(Interfaz interfaz) {
        this.interfaz = interfaz;
    }

    @Override
    public void run() {//atender los procesos
        //da un número aleatorio de acuerdo al promedio de los tiempos que se dan
        listos.nuevo();//crea la lista

        bloqueados.nuevo();//crea la lista
        atendidos.nuevo();//crea la lista
        suspendidos.nuevo();

        listos.procesos();//asigna procesos aleatorios a la cola de listos
        Nodo en_ejecucion;

        listos.imprimir();
        listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());

        while ((listos.p != listos.p.sig) || (bloqueados.p != bloqueados.p.sig) || (suspendidos.p != suspendidos.p.sig))//comprueba que queden procesos por atender
        {
            try {

                sleep(1000);
                this.agregarProceso();
                if (listos.p.sig != listos.p) {

                    en_ejecucion = this.CompararPrioridades(listos.p.sig);

                    if (hayRecurso()) {//verifica que tenga recursos el proceso en curso para proceder a ejecutarlo

                        //interfaz.getProcesos_en_ejecucion().setText(en_ejecucion.proceso+"");            
                        sleep(1000);
                        int f = en_ejecucion.tiempo_final;

                        for (int i = 1; i <= f; i++) {

                            if (CompararRestante(en_ejecucion)) {

                                sleep(1000);

                                interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");
                                cont = cont + 1;
                                /*if (cont == 46) {
                                 JOptionPane.showMessageDialog(null, "Tiempo Expirado");
                                 stop();
                                 }*/
                                interfaz.getCpu_nuevo().setText(en_ejecucion.tiempo_final + "");
                                en_ejecucion.setTiempo_cpu(en_ejecucion.tiempo_cpu + 1);
                                interfaz.getTiempo_cpu().setText(en_ejecucion.tiempo_cpu + "");
                                interfaz.getProcesos_en_ejecucion().setText(en_ejecucion.proceso + "");
                                listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());

                                if (listos.num > 1) {//revisa si hay algo en cola de listos y no en ejecución y actualiza su tiempo
                                    Nodo auxlis = en_ejecucion.sig;
                                    for (int li = 2; li <= listos.num; li++) {
                                        if (auxlis == listos.p) {
                                            auxlis = listos.p.sig;
                                        }
                                        auxlis.tiempo_en_espera = auxlis.tiempo_en_espera + 1;
                                        diagramaGantt(auxlis, 2, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                        auxlis = auxlis.sig;
                                        listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                                    }
                                }

                                if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                                    Nodo auxiliar_b = bloqueados.p.sig;

                                    for (int j = 1; j <= bloqueados.num; j++) {
                                        auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                                        auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;
                                        diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                                        bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), interfaz.getjTableProcesos());
                                        auxiliar_b = auxiliar_b.sig;
                                    }

                                    if (hayRecurso2()) {

                                        sleep(1000);
                                        sleep(1000);
                                        listos.agregar(bloqueados.p.sig);
                                        bloqueados.eliminarNodo(bloqueados.p.sig);
                                        bloqueados.imprimir();
                                        bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), interfaz.getjTableProcesos());
                                        listos.imprimir();
                                        listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                                    }

                                }

                                diagramaGantt(en_ejecucion, 1, Integer.parseInt(interfaz.getTiempo_real().getText()));

                                if (en_ejecucion.tiempo_cpu == en_ejecucion.tiempo_final) {//verifica que cumpla el quantum y agregue a cola de atendidos                                                

                                    terminados(en_ejecucion.proceso, Integer.parseInt(interfaz.getTiempo_real().getText()));

                                    interfaz.getTiempo_cpu().setText(en_ejecucion.tiempo_cpu + "");
                                    sleep(1000);
                                    sleep(1000);
                                    atendidos.agregar(en_ejecucion);
                                    listos.eliminarNodo(en_ejecucion);
                                    listos.imprimir();
                                    listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                                    sleep(1000);
                                    atendidos.imprimir();
                                    i = f;
                                    interfaz.getTiempo_cpu().setText("");
                                    interfaz.getProcesos_en_ejecucion().setText("");
                                    interfaz.getCpu_nuevo().setText("");
                                }

                                this.agregarProceso();

                            }//end if compararrestante
                            else {

                                i = f;
                                sleep(1000);
                                suspendidos.agregar(en_ejecucion);
                                listos.eliminarNodo(en_ejecucion);
                                listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                                sleep(1000);
                                listos.agregar(suspendidos.p.sig);
                                suspendidos.eliminarNodo(suspendidos.p.sig);
                                listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                            }
                        }

                    } else {//si no hay recurso para un proceso lo envía a cola de bloqueados
                        sleep(1000);

                        sleep(1000);
                        bloqueados.agregar(en_ejecucion);
                        listos.eliminarNodo(en_ejecucion);
                        bloqueados.imprimir();
                        bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), interfaz.getjTableProcesos());
                        listos.imprimir();
                        listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                        this.agregarProceso();
                        sleep(1000);

                    }
                } else {//si no hay nada en listos actualiza el tiempo para las otras colas       

                    interfaz.getTiempo_real().setText((Integer.parseInt(interfaz.getTiempo_real().getText()) + 1) + "");
                    cont = cont + 1;
                    /*if (cont == 46) {
                     JOptionPane.showMessageDialog(null, "Tiempo Expirado");
                     stop();
                     }*/
                    if (bloqueados.p.sig != bloqueados.p) {//revisa si hay algo en cola de bloqueados y actualiza su tiempo
                        Nodo auxiliar_b = bloqueados.p.sig;

                        for (int j = 1; j <= bloqueados.num; j++) {
                            auxiliar_b.setTiempo_bloqueado(auxiliar_b.getTiempo_bloqueado() + 1);
                            auxiliar_b.tiempo_en_espera = auxiliar_b.tiempo_en_espera + 1;
                            diagramaGantt(auxiliar_b, 3, Integer.parseInt(interfaz.getTiempo_real().getText()));
                            bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), interfaz.getjTableProcesos());
                            auxiliar_b = auxiliar_b.sig;
                        }

                        if (hayRecurso2()) {
                            sleep(1000);

                            sleep(1000);
                            listos.agregar(bloqueados.p.sig);
                            bloqueados.eliminarNodo(bloqueados.p.sig);
                            bloqueados.imprimir();
                            bloqueados.imprimir(interfaz.getTexto_bloqueados(), interfaz.getTotal_bloqueados(), interfaz.getjTableProcesos());
                            listos.imprimir();
                            listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
                        }

                    }

                }
                //si np es menor de rn(variable random que me determina la cantidad de procesos a agregar)
                if (np < rn) {
                    interfaz.obj.agregar_proceso = true;
                    JOptionPane.showMessageDialog(null, "Llega nuevo proceso");
                    np++;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Prioridad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Ejecución finalizada");
    }

    public boolean hayRecurso() {//verifica si existe el recurso correspondiente a ese proceso
        Random rnd = new Random();
        int x = rnd.nextInt(20);
        if (x < 14) {
            return true;
        } else {
            return false;
        }
    }

    public void agregarProceso() {//agrega un nuevo proceso en cualquier instante de tiempo
        if (agregar_proceso == true) {
            int nuevo_num = listos.num + atendidos.num + bloqueados.num + 1;
            agregar_proceso = false;
            listos.agregarProceso(nuevo_num);
        }
        listos.imprimir(interfaz.getTexto_listos(), interfaz.getTotal_listos(), interfaz.getjTableProcesos());
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

    public Nodo CompararPrioridades(Nodo inicial) {

        Nodo comparado;
        Nodo aux_inicial = inicial;
        comparado = aux_inicial.sig;
        while (comparado != listos.p) {
            if (aux_inicial.prioridad > comparado.prioridad) {
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
            if (aux_inicial.prioridad > comparado.prioridad) {
                aux_inicial = comparado;
            }
            comparado = comparado.sig;
        }

        actual = aux_inicial;
        if (aux_actual == actual) {
            return true;
        } else {
            return false;
        }

    }

    public void terminados(int num_proceso, int transcurrido) {//actualiza la priemra parte edl diagrama de gantt con el tiempo de los procesos que pasan por cpu
        String espacio;
        //conteo_tiempo=conteo_tiempo+transcurrido;
        conteo_tiempo = transcurrido;
        if ((conteo_tiempo + "").length() == 2) {
            espacio = " ";
        } else {
            espacio = " ";
        }
        diagramaGant1 = diagramaGant1 + "Proceso " + num_proceso + "\n" + espacio;
        interfaz.getTexto_diagrama().setText(diagramaGant1 + "\n"/*+diagramaGant2*/);
    }

    public void diagramaGantt(Nodo actual, int estado, int transcurrido) {//actualiza diagrama de gantt desde la interfaz gráfica
        String fase;
        if (estado == 1) {
            fase = "X";
        } else if (estado == 2) {
            fase = "E";
        } else if (estado == 3) {
            fase = "B";
        } else {
            fase = null;
        }
        interfaz.getjTable1().setValueAt(fase, actual.proceso - 1, transcurrido);
        interfaz.getjTable1().setDefaultRenderer(Object.class, new MiRender());
        interfaz.getjTable1().setValueAt(actual.tiempo_en_espera, actual.proceso - 1, 98);
        interfaz.getjTable1().setValueAt(actual.tiempo_en_espera + actual.tiempo_cpu, actual.proceso - 1, 99);

    }

}
