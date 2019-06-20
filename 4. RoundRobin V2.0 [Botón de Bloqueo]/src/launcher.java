
import Logica.Cola;
import Logica.Interfaz;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian
 */
public class launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Interfaz().setVisible(true);
            }
        });
//        Cola listos = new Cola();
//        Cola bloqueados = new Cola();
//        listos.nuevo();
//        bloqueados.nuevo();
//        listos.procesos();
//        System.out.println("agregado: "+listos.p.sig.sig.sig.sig.sig.proceso);
//                
//        bloqueados.agregar(listos.p.sig.sig.sig.sig.sig);
//        listos.eliminarNodo(listos.p.sig.sig.sig.sig.sig);
//        System.out.println("listos:");
//        listos.imprimir();
//        System.out.println("bloqueados: ");
//        bloqueados.imprimir();
//        
//        System.out.println("comprobacion: ");
//        listos.agregar(bloqueados.p.sig);
//        bloqueados.eliminarNodo(bloqueados.p.sig);
//        System.out.println("listos:");
//        listos.imprimir();
//        System.out.println("bloqueados: ");
//        bloqueados.imprimir();
       
        
   
    }
}
