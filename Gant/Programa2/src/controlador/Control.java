/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Cola;
import modelo.GeneraRandom;
import modelo.MiRender;
import modelo.MiRender1;
import vista.Ventana;

/**
 *
 * @author diego
 */
public class Control  extends Thread{

    private Cola cola;
    private GeneraRandom gr;    
    private Ventana interfaz;    
    private static int p = 0, k = 0, ctrolE = 0;
    private JTable tabla,tablaGant;
    int cont = 0;
    int fila = 0;
    
    
    public Control(Ventana vista) {
        this.interfaz = vista;        
    }

    public void crearCola() {
        cola = new Cola();
    }
    
    @Override
    public void run() {
        while(true){               
            try {                
            if (ctrolE < tabla.getRowCount()) {            
            for (int j = 0; j < tabla.getColumnCount(); j++) {                
                if (j >= 0 && j < 3) {
                    tabla.getModel().setValueAt(k + 1, k, j); //setValueAt(datoMostrar,numeroFila,numeroColumna)-#PROCESO
                    tabla.getModel().setValueAt(cola.darLista().get(k + p), k, j + 1);//LLEGADA
                    tabla.getModel().setValueAt(cola.darLista().get((k + 1) + p), k, j + 2);//RAFAGA
                    
                    formatoTablaProcesos(k + 1, k, j);
                    formatoTablaProcesos(cola.darLista().get(k + p), k, j + 1);
                    formatoTablaProcesos(cola.darLista().get((k + 1) + p), k, j + 2);
                    
                    j = j + 3;
                    
                    tabla.getModel().setValueAt(cola.darLista().get((k + 2) + p), k, j);//COMIENZO
                    formatoTablaProcesos(cola.darLista().get((k + 2) + p), k, j); 
                }               
                int comienzo = cola.darLista().get((k + 2) + p);
                int finalizacion = cola.darLista().get((k + 3) + p);
                
                int tiempo = cola.darLista().get(2);
                
                for (int i=comienzo; i <= finalizacion;i++){                    
                    sleep(1000); 
                    interfaz.getTiempo_real().setText((Integer.toString(tiempo + cont) + ""));  
                    cont = cont + 1;
                            if (cont == 60) {      
                                cont = cont - 1;
                                JOptionPane.showMessageDialog(null, "Tiempo Cumplido");                                
                                stop();
                            }                               
                    //tablaGant.getModel().setValueAt("X",fila, i);                    
                    diagramaGantt(1, fila, i);
                    if(i==finalizacion){
                        tablaGant.getModel().setValueAt("C",fila, i);               
                        fila=fila+1;      
                        cont = cont - 1;
                    }                       
                }
                
                tabla.getModel().setValueAt(cola.darLista().get((k + 3) + p), k, j + 1);//FINALIZACION
                tabla.getModel().setValueAt(cola.darLista().get((k + 4) + p), k, j + 2);//ESPERA                
                formatoTablaProcesos(cola.darLista().get((k + 3) + p), k, j + 1);
                formatoTablaProcesos(cola.darLista().get((k + 4) + p), k, j + 2);
                j = j + 3;
            }
            p = p + 4;
            k = k + 1;
            ctrolE = ctrolE + 1;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas procesos");
        }
        }
             catch (InterruptedException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertarEnCola() {
        crearCola();
        gr = new GeneraRandom();
        ArrayList<Integer> tc = gr.generarComienzo(50);
        ArrayList<Integer> tr = gr.generarRafaga(50);
        for (int i = 0; i < 50; i++) {
            cola.insertar(i + 1, tc.get(i), tr.get(i));
        }
    }
    
    // Agrega un proceso a la vez siempre y cuando la capacidad de 50 no se excedida
    public void agregarProceso(JTable tabla,JTable tablaGant) {
        this.tabla=tabla;
        this.tablaGant=tablaGant;
    }

    //Borra todos los elementos de la tabla y reinicia el número de procesos en 0
    public void vaciarTabla(JTable tabla) {        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                tabla.getModel().setValueAt(null, i, j);
            }
        }
        ctrolE = 0;
        p = 0;
        k = 0;
    }
    
    public int darTiempoPromedioEspera(JTable tabla){
        int acum=0;        
        int prom;
        for(int i = 0; i < tabla.getRowCount(); i++){
            acum += (int) tabla.getModel().getValueAt(i, 5);              
        }
        prom = acum/tabla.getRowCount();
        return prom;
    }

    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }    
       
    public void diagramaGantt(int estado,int fila,int columna) {//actualiza diagrama de gantt desde la interfaz gráfica
        String fase;

        if (estado == 1) {
            fase = "X";
        } else if (estado == 2) {
            fase = "E";
        } else if (estado == 3) {
            fase = "B";
        } else if (estado == 4) {
            fase = "S";
        } else if (estado == 8) {
            fase = "C";
        } else {
            fase = null;
        }
        interfaz.getjTable1().setValueAt(fase, fila, columna);
        interfaz.getjTable1().setDefaultRenderer(Object.class, new MiRender());
    }
    
    public void formatoTablaProcesos(int estado,int fila,int columna) {//actualiza diagrama de gantt desde la interfaz gráfica
        String fase = Integer.toString(estado); 
        interfaz.getjTableAgregarProcesos().setValueAt(fase, fila, columna);
        interfaz.getjTableAgregarProcesos().setDefaultRenderer(Object.class, new MiRender1());
    }
}
