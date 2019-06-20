/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.TextArea;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Cristian
 */
public class Cola {

    public int i, num, t_proceso, primerProceso = 1;
    public Nodo nuevo, cab, p, aux;

    public void nuevo() {//crea la cola
        p = new Nodo();
        p.sig = p;
        cab = p;
    }

    public void procesos() {//asigna elementos aleatorios a esa cola
        Random rnd = new Random();
        Random rnd2 = new Random();
        Random rnd3 = new Random();
        num = 3 + rnd.nextInt(2);

        for (i = 1; i <= num; i++) {
            t_proceso = 2 + rnd.nextInt(3);//genera un numero aleatorio que es el tiempo de cada proceso
            nuevo = new Nodo();//pedir memoria, generar un nodo
            nuevo.proceso = i;//asignar numero de proceso
            nuevo.tiempo_estimado = 5;//t_proceso;//tiempo de cpu 
            nuevo.setTiempo_cpu(0);
            nuevo.setTiempo_final(nuevo.tiempo_estimado * 2 + rnd2.nextInt(nuevo.tiempo_estimado));
            nuevo.quantum_suspendido = 1;//4;

            cab.sig = nuevo;//Actualiza el enlace para apuntar al siguiente nodo 
            nuevo.sig = p; //actualiza el ultimo nodo con la direccion del primero
            cab = nuevo;// actualizar el contenido de la variable para recibir el siguiente nodo

        }
    }

    public void agregarProceso(int nuevo_num) {//Agrega un nuevo proceso aleatorio a esa cola en un instante de tiempo cualquiera 
        Random rnd = new Random();
        Random rnd2 = new Random();
        Random rnd3 = new Random();
        t_proceso = 2 + rnd.nextInt(3);//genera un numero aleatorio que es el tiempo de caca proceso
        nuevo = new Nodo();//pedir memoria, generar un nodo
        nuevo.proceso = nuevo_num;//asignar numero de proceso
        nuevo.tiempo_estimado = 5;//t_proceso;//tiempo de cpu 
        nuevo.setTiempo_cpu(0);
        nuevo.setTiempo_final(nuevo.tiempo_estimado * 2 + rnd2.nextInt(nuevo.tiempo_estimado));
        nuevo.quantum_suspendido = 1;//4; 

        cab.sig = nuevo;//Actualiza el enlace para apuntar al siguiente nodo 
        nuevo.sig = p; //actualiza el ultimo nodo con la direccion del primero
        cab = nuevo;// actualizar el contenido de la variable para recibir el siguiente nodo
        num++;
    }

    //imprimir los nodos de la cola
    public void imprimirListos(JTextArea tx, JLabel label) {//imprime la cola en la interfaz
        String txt = "                                                                               \n"
                + "       No.PROCESO          RÁFAGA           EJECUTADO           ESPERA \n"
                + "                                                                                  \n";
        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        if (p == p.sig) {
            tx.setText("");
        }
        while (impr != p) {//se repite hasta encontrar el ultimo nodo
            if (impr.tiempo_cpu < 10) { //se valida esto para no dañar la tabla q se muestra
                txt = txt + "                   "
                        + impr.proceso + "                      "
                        + impr.tiempo_final + "                     "
                        + "0" + impr.tiempo_cpu + "                       "
                        + impr.tiempo_en_espera + "        \n" + "                                                                                 \n";
            } else{
                txt = txt + "                   "
                        + impr.proceso + "                      "
                        + impr.tiempo_final + "                     "
                        + impr.tiempo_cpu + "                       "
                        + impr.tiempo_en_espera + "        \n" + "                                                                                 \n";
            }
            tx.setText(txt);
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
        label.setText(num + "");
    }
    
    void imprimir(JTextArea tx, JLabel label) {
        String txt = "";
        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        if (p == p.sig) {
            tx.setText("");
        }
        while (impr != p) {//se repite hasta encontrar el ultimo nodo
            txt = txt + "Proceso: " + impr.proceso + "\n";
            tx.setText(txt);
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
        label.setText(num + "");
    }

    public void imprimir() {//imprime la cola en consola

        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        while (impr != p) {//se repiteNo proceso:  "+ hasta encontrar el ultimo nodo
            //System.out.print("Proceso:  " + impr.proceso + " Tiempo: " + impr.tiempo_estimado);

            //System.out.print("\n");
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
//        System.out.print("\n");
//        System.out.println("total de procesos en cola............" + num);//muestra el total de procesos en cola
//        //System.out.println("****************************************************************");
    }

    /**
     * Método para agregar un nodo extra al final de la cola
     *
     * @param time tiempo del proceso
     * @param pro identificador del proceso
     */
    public void eliminarPrimeroListos() {
        Nodo auxx = p.sig;
        if (auxx.sig != null) {
            p.sig = auxx.sig;
            // cab=p.sig;
        } else {
            p.sig = p;
            //cab=p;
        }
        auxx.sig = null;
        num--;
    }

    public void eliminarPrimero() {//elimina primer elemento
        Nodo auxx = p.sig;
        if (auxx.sig != null) {
            p.sig = auxx.sig;
            cab = p.sig;
        } else {
            p.sig = p;
            cab = p;
        }
        // auxx.sig=null;
        num--;

    }

    public void eliminarPrimero2() {
        Nodo auxx = p.sig;
        if (auxx.sig != null) {
            p.sig = auxx.sig;
            cab = p.sig;
        } else {
            p.sig = p;
            cab = p;
        }
        // auxx.sig=null;

    }

//segundo metodo para agregar un proceso, a este se le agrega el tiempo en el cuál salió
    public void agregar(Nodo agregado) {//agrega un nodo a otra cola
        nuevo = new Nodo();
        nuevo.proceso = agregado.proceso;
        nuevo.tiempo_estimado = nuevo.tiempo_estimado + 5;
        nuevo.tiempo_cpu = agregado.tiempo_cpu;
        nuevo.tiempo_final = agregado.tiempo_final;
        nuevo.quantum_suspendido = agregado.quantum_suspendido;
        nuevo.tiempo_en_espera = agregado.tiempo_en_espera;
        cab.sig = nuevo;
        nuevo.sig = p;
        cab = nuevo;
        num++;
        //   System.out.println("agregado proceso num: "+pro);
    }

    public int tamaño() {//calcula tamaño de la cola
        Nodo tam = this.p.sig;
        int t = 0;
        while (tam != this.p) {
            t++;
            tam = tam.sig;
        }
        return t;
    }

    public void eliminarNodo(Nodo eliminar) {//elimina un nodo en cualquier posición
        int c = -1;
        int num_eliminado;
        Nodo auxel = eliminar;
        Nodo asignar = this.p;
        while (auxel != this.p) {
            c++;
            auxel = auxel.sig;
        }

        if (num != 1) {
            num_eliminado = this.num - c;
            //System.out.println("se eliminara el Nodo :" + num_eliminado);
            for (int n = num_eliminado; n > 1; n--) {
                asignar = asignar.sig;
            }
            //System.out.println("se reasignara el proceso :" + asignar.proceso);
            asignar.sig = eliminar.sig;
            if (eliminar.sig == this.p) {
                cab = asignar;
            }
        } else {
            this.eliminarPrimero2();
        }

        num--;
    }
}
