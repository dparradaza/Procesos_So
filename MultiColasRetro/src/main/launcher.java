package main;


import Vista.Ventana;

public class launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Ventana().setVisible(true);
        });
    }
}
