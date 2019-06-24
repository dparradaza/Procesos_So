// Short Remaining Time First
package Controlador;

import Vista.Ventana;

/**
 * @author Diego Parra - Oscar Bautista
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }   
}
