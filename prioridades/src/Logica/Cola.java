/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.TextArea;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Chayder
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
//        num=3+rnd.nextInt(6) ;
        num = 6;

        for (i = 1; i <= num; i++) {

            nuevo = new Nodo();//pedir memoria, generar un nodo
            nuevo.proceso = i;//asignar numero de proceso
            nuevo.prioridad = 1 + rnd3.nextInt(4);
            //JOptionPane.showMessageDialog(null, nuevo.prioridad);
            nuevo.setTiempo_cpu(0);
            nuevo.setTiempo_final(2 + rnd2.nextInt(10));
            cab.sig = nuevo;//Actualiza el enlace para apuntar al siguiente nodo 
            nuevo.sig = p; //actualiza el ultimo nodo con la direccion del primero
            cab = nuevo;// actualizar el contenido de la variable para recibir el siguiente nodo

        }
    }

    public void agregarProceso(int nuevo_num) {//Agrega un nuevo proceso aleatorio a esa cola en un instante de tiempo cualquiera 

        Random rnd3 = new Random();
        nuevo = new Nodo();//pedir memoria, generar un nodo
        nuevo.proceso = nuevo_num;//asignar numero de proceso     
        //nuevo.prioridad=1+rnd3.nextInt(3);
        nuevo.prioridad = 1 + rnd3.nextInt(4);
        //JOptionPane.showMessageDialog(null, nuevo.prioridad);
        nuevo.setTiempo_cpu(0);
        nuevo.setTiempo_final(2 + rnd3.nextInt(8));
        cab.sig = nuevo;//Actualiza el enlace para apuntar al siguiente nodo 
        nuevo.sig = p; //actualiza el ultimo nodo con la direccion del primero
        cab = nuevo;// actualizar el contenido de la variable para recibir el siguiente nodo
        num++;

    }

    public void agregar(Nodo agregado) {//agrega un nodo a otra cola
        nuevo = new Nodo();
        nuevo.proceso = agregado.proceso;
        nuevo.tiempo_cpu = agregado.tiempo_cpu;
        nuevo.tiempo_final = agregado.tiempo_final;
        nuevo.prioridad = agregado.prioridad;
        nuevo.tiempo_en_espera = agregado.tiempo_en_espera;
        cab.sig = nuevo;
        nuevo.sig = p;
        cab = nuevo;
        num++;
        //   System.out.println("agregado proceso num: "+pro);
    }

    //imprimir los nodos de la cola
    public void imprimir(JTextArea tx, JLabel label, JTable tabla) {//imprime la cola en la interfaz
        String txt = "";
        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        if (p == p.sig) {
            tx.setText("");
        }
        while (impr != p) {//se repite hasta encontrar el ultimo nodo
            //txt = txt + "Proceso: " + impr.proceso + ",  Tiempo de ráfaga:  " + impr.tiempo_final + ":  Tiempo ejecutado:  "
              //      + impr.tiempo_cpu + ",  Tiempo de espera:  " + impr.tiempo_en_espera + " Prioridad: " + impr.prioridad + "\n";
            
            txt = txt + "Proceso: " + impr.proceso + " ->" + " Prioridad: " + impr.prioridad + "\n";
            
            tx.setText(txt);
            
            //codigo para mostrar datos en la tabla de datos de los procesos
            tabla.getModel().setValueAt(impr.proceso, impr.proceso-1, 0); //setValueAt(datoMostrar,numeroFila,numeroColumna)
            tabla.getModel().setValueAt(impr.tiempo_final, impr.proceso-1, 1);
            tabla.getModel().setValueAt(impr.tiempo_cpu, impr.proceso-1, 2);//aca "imp.proceso" se toma para el numero de fila
            tabla.getModel().setValueAt(impr.tiempo_en_espera, impr.proceso-1, 3);
            tabla.getModel().setValueAt(impr.prioridad, impr.proceso-1, 4);
            
            //condicional para cambiar de color la fila
            if(impr.tiempo_final==impr.tiempo_cpu){                
                formatoTablaProcesosAzul(impr.proceso, impr.proceso-1, 0, tabla);
                formatoTablaProcesosAzul(impr.tiempo_final, impr.proceso-1, 1, tabla);
                formatoTablaProcesosAzul(impr.tiempo_cpu, impr.proceso-1, 2, tabla);//aca "imp.proceso" se toma para el numero de fila
                formatoTablaProcesosAzul(impr.tiempo_en_espera, impr.proceso-1, 3, tabla);
                formatoTablaProcesosAzul(impr.prioridad, impr.proceso-1, 4, tabla);
            }
            
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
        label.setText(num + "");

    }

    public void imprimir() {//imprime la cola en consola

        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        while (impr != p) {//se repiteNo proceso:  "+ hasta encontrar el ultimo nodo
            //System.out.print("Proceso:  " + impr.proceso );

            //System.out.print("\n");
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
    }

    /**
     * Método para agregar un nodo extra al final de la cola
     *
     * @param time tiempo del proceso
     * @param pro identificador del proceso
     */
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
    
    public void formatoTablaProcesosAzul(int valor,int fila,int columna, JTable tabla) {//actualiza diagrama de gantt desde la interfaz gráfica
        String dato = Integer.toString(valor); 
        tabla.setValueAt(dato, fila, columna);
        tabla.setDefaultRenderer(Object.class, new MiRender1());
    }
}
