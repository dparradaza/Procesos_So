package vista;

import modelo.Cola;
import modelo.Nodo;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author diego
 */
public class Ventana extends JFrame implements ActionListener {

    private JTextArea caja1;
    private JTable caja2;
    private JLabel texto, texto1, autor, pal, pal2, nopal, contarL, contarP, eliminar;
    private JButton cerrar, operar;
    private JComboBox combo;
    private JPanel panelIzquierdo, panelDerecho;
    private Font fuente, fuente1, fuente2;
    private Cursor mano;
    private Border bordesitosxd, bordes, bordes1, bordes2;
    private StringTokenizer st;
    //-----------------
    private int contador = 0;
    private String valor;
    private String valor1 = "";
    private char[] arrayChar;
    private boolean validacion = false;

    public Ventana() {   // Creacion de la ventana 
        setSize(1100, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        // Se instancian los metodos de los Paneles xd
        PanelIzq();
        PanelDer();
        Componentes();
        //---------------------
        combo = new JComboBox();
        combo.addItem("Seleccione una opción");
        combo.addItem("✔ Crear Procesos");
        combo.addItem("✔ Iniciar Simulación");
        combo.addItem("✔ Limpiar");
        //combo.addItem("✔ Ver reporte");
        //combo.addItem("✔ Pensum");
        combo.setBounds(getWidth() / 2 + 100, 80, 170, 22);
        combo.setLayout(null);
        combo.setBackground(new Color(24, 100, 167));
        combo.setForeground(new Color(235, 196, 56));
        combo.addActionListener(this);
        panelDerecho.add(combo);
        combo.updateUI();
    }

    public void PanelIzq() {
        // Creacion del JPanel Izquierda
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBounds(0, 0, getWidth() / 2, getHeight());
        panelIzquierdo.setBackground(new Color(187, 187, 187));
        panelIzquierdo.setLayout(null);
        this.add(panelIzquierdo);
        panelIzquierdo.updateUI();
    }

    public void PanelDer() {
        // Creacion del JPanel Derecho
        panelDerecho = new JPanel();
        panelDerecho.setBounds(getWidth() / 2, 0, getWidth(), getHeight());
        panelDerecho.setBackground(new Color(250, 162, 96));
        panelDerecho.setLayout(null);
        this.add(panelDerecho);
        panelDerecho.updateUI();
    }

    public void Componentes() {
        fuente = new Font("Berlin Sans FB", Font.BOLD, 28);
        fuente2 = new Font("Berlin Sans FB", Font.BOLD, 16);
        fuente1 = new Font("Comic Sans MS", Font.BOLD, 11);
        //mano = new Cursor(HAND_CURSOR);
        //----------------------- 
        texto = new JLabel("      ||SIMULADOR \n\t SERVICIOS ||");
        texto.setFont(fuente);
        texto.setBounds(getWidth() / 25 - 25, getHeight() / 2 - 20, 500, 30);
        texto.setForeground(new Color(0, 104, 139));
        panelIzquierdo.add(texto);
        //------------------------
        texto1 = new JLabel("*Seleccione una opción: ");
        texto1.setFont(fuente2);
        texto1.setForeground(new Color(32, 50, 72));
        texto1.setBounds(getWidth() / 2 + 120, 40, 390, 20);
        panelDerecho.add(texto1);
        texto1.updateUI();
        //---------------------
        //bordesitosxd = BorderFactory.createLineBorder(new Color(0,104,139), 2);
        autor = new JLabel("Diego Parra(20141020063)-Oscar Bautista(20142020213)");
        autor.setBounds(new Rectangle(5, 560, 390, 30));
        autor.setFont(fuente1);
        // autor.setBorder(bordesitosxd);
        autor.setForeground(new Color(0, 104, 139));
        autor.setHorizontalAlignment(JLabel.CENTER);
        panelIzquierdo.add(autor);
        //------------------------
        //texto1 = new JLabel("✔ Ingrese un usuario: ");
        //texto1.setFont(fuente1);
        //texto1.setForeground(new Color(32, 50, 72));
        //texto1.setBounds(getWidth() / 2 + 10, 80, 190, 15);
        //panelDerecho.add(texto1);
        //texto1.updateUI();
        //------------------------
        //bordes = BorderFactory.createLineBorder(new Color(180, 180, 180), 2, false);
        //bordes1 = BorderFactory.createLineBorder(new Color(32, 50, 72), 2);
        //bordes2 = BorderFactory.createLineBorder(new Color(71, 161, 188), 2);

        //Cajas de texto
        JScrollPane scroll = new JScrollPane();

        caja1 = new JTextArea();
        // caja1.setBounds(getWidth() / 2 + 10, 200, 400, 120);
        caja1.setBackground(new Color(32, 50, 72));
        caja1.setForeground(new Color(235, 196, 56));
        caja1.setLineWrap(true);
        scroll.setViewportView(caja1);
        scroll.setBounds(getWidth() / 2 + 10, 150, 500, 180);
        panelDerecho.add(scroll);
        caja1.updateUI();

        //Caja 2
        JScrollPane scroll2 = new JScrollPane();
        caja2 = new JTable();
        //caja2.setBounds(getWidth() / 2 + 10, 400, 400, 120);
        //caja.setBorder(bordes);
        //caja.setCursor(mano);
        caja2.setBackground(new Color(32, 50, 72));
        caja2.setForeground(new Color(235, 196, 56));
        scroll2.setViewportView(caja2);
        scroll2.setBounds(getWidth() / 2 + 10, 350, 500, 230);
        panelDerecho.add(scroll2);
        caja2.updateUI();

        //------------------------
        cerrar = new JButton("X");
        cerrar.setBorder(null);
        cerrar.setFont(fuente);
        cerrar.setContentAreaFilled(false);
        cerrar.setForeground(new Color(24, 100, 167));
        //cerrar.setCursor(mano);
        //cerrar.setFocusable(false);
        cerrar.addActionListener(this);
        cerrar.setBounds(1040, 1, 35, 35);
        panelDerecho.add(cerrar);
        cerrar.repaint();
        //------------------------
        operar = new JButton("Aceptar");
        operar.setBounds(getWidth() / 2 + 280, 80, 120, 25);
        //operar.setBorder(bordes1);
        //operar.setCursor(mano);
        operar.setFont(fuente2);
        operar.setContentAreaFilled(false);
        operar.setForeground(new Color(32, 50, 72));
        operar.addActionListener(this);
        panelDerecho.add(operar);
        operar.repaint();

        pal = new JLabel();
        pal.setBounds(10, 20, 470, 490);
        panelIzquierdo.add(pal);
        /*
        String path = "src/imgs/logoUD2.png";
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(path);
        String path2 = "src/imgs/logoUD3.png";
        URL url2 = this.getClass().getResource(path2);
        ImageIcon icon2 = new ImageIcon(path2);
        pal.setIcon(icon);
        pal.updateUI();
         */
        pal2 = new JLabel();
        pal2.setBounds(getWidth() / 2 + 20, 170, 500, 400);
        panelDerecho.add(pal2);

        //pal2.setIcon(icon2);
        pal2.updateUI();
    }

    public void graficar(String matriz[][]) {
        caja2.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Iteración", "Proceso", "Servicios"
                }
        ));
        caja2.setRowHeight(30);

    }
    Cola proceso = new Cola();

    @Override
    public void actionPerformed(ActionEvent e) {
        char c = (char) 65;
        int numero = 0;
        if (e.getSource() == cerrar) {
            System.exit(0);
        }
        if (operar == e.getSource()) {
            String opcion = combo.getSelectedItem().toString();
            if (opcion.equals("✔ Crear Procesos")) {

                // proceso = new Cola2();
                for (int i = 0; i < (int) (Math.random() * 10) + 3; i++) {
                    numero = (int) (Math.random() * 10) + 1;
                    proceso.insertar(numero, "" + c);
                    caja1.append("Proceso: " + c + " --- Servicios: " + numero + "\n");
                    c++;
                }
            } else if (opcion.equals("✔ Iniciar Simulación")) {
                int n = proceso.getTam();
                String matriz[][] = new String[50][4];
                Nodo busca = proceso.obtenerDato(1);
                int i = 0;
                while (i < n) {
                    System.out.println("i: "+i+"    n: "+n);
                    System.out.println(proceso.getCab().getNombre());
                    System.out.println(proceso.getCab().getSig().getNombre());
                    System.out.println(proceso.getCab().getSig().getSig().getNombre());
                    matriz[i][0] = Integer.toString(i);
                    // matriz[i][1] = busca.getId();
                    matriz[i][1] = busca.getSig().getNombre();
                    matriz[i][2] = Integer.toString(busca.getSig().getNumeroServicios());
                    graficar(matriz);

                    if (busca.getSig().getNumeroServicios() > 3) {
                        int calculo = busca.getSig().getNumeroServicios() - 3;
                        busca.getSig().setNumeroServicios(calculo);
                        Nodo ultimo = busca.getSig();
                        busca.setSig(ultimo.getSig());
                        proceso.insertar(ultimo.getNumeroServicios(), ultimo.getNombre());
                        n = proceso.getTam();
                    } else {
                        Nodo ultimo = busca.getSig();
                        busca.setSig(ultimo.getSig());
                        n = proceso.getTam();
                    }
                    i++;
                }
            } else if (opcion.equals("✔ Limpiar")) {
                caja1.setText("");
                proceso = new Cola();
                graficar(null);
            } else {
                caja1.setText("ERROR");
                System.out.println("Debe escoger una opcion");
            }
        }

    }

}
