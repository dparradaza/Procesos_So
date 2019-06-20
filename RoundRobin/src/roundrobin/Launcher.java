package roundrobin;

import Logica.Ventana;

/**
 *
 * @author ADBA
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

}
