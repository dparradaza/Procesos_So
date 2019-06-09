package main;

import controlador.Control;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ADBA
 */
public class Ventana extends JFrame implements ActionListener {

    private JPanel panelIzquierdo, panelDerecho;
    private JLabel texto, texto1, autor, tiempo_real, pal, pal2;
    private JTable procesos, gant;
    private JComboBox combo;
    private Font fuente, fuente1, fuente2;

    Control controlador = new Control(this);

    public Ventana() {
        crearVentana();
        PanelIzq();
        PanelDer();
        Componentes();
    }

    private void crearVentana() {
        setSize(1100, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setUndecorated(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (combo.getSelectedItem().equals("✔ Iniciar Simulación")) {
            controlador.vaciarTabla(procesos);
            controlador.insertarEnCola();
            if (controlador.getCola() != null) {
                gant.selectAll();
                controlador.agregarProceso(procesos, gant);
                controlador.start();
            }
        }
        if (combo.getSelectedItem().equals("✔ Reanudar")) {
            controlador.resume();
        }
        if (combo.getSelectedItem().equals("✔ Pausar")) {
            controlador.suspend();
        }
    }

    private void PanelIzq() {
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBounds(0, 0, (getWidth() / 2) - 190, getHeight());
        panelIzquierdo.setBackground(new Color(234, 237, 237));
        panelIzquierdo.setLayout(null);
        panelIzquierdo.updateUI();
        this.add(panelIzquierdo);
    }

    private void PanelDer() {
        panelDerecho = new JPanel();
        panelDerecho.setBounds((getWidth() / 2) + 150, 0, getWidth(), getHeight());
        panelDerecho.setBackground(new Color(250, 162, 96));
        panelDerecho.setLayout(null);
        panelDerecho.updateUI();
        this.add(panelDerecho);
    }

    private void Componentes() {
        fuente = new Font("Berlin Sans FB", Font.BOLD, 28);
        fuente1 = new Font("Comic Sans MS", Font.BOLD, 11);
        fuente2 = new Font("Berlin Sans FB", Font.BOLD, 16);
        //------------------------
        texto = new JLabel("Algoritmo de prioridad");
        texto.setFont(fuente);
        texto.setBounds(getWidth() / 25 - 25, getHeight() / 2 - 20, 500, 30);
        texto.setForeground(new Color(0, 104, 139));
        panelIzquierdo.add(texto);
        //------------------------
        texto1 = new JLabel("Opciones: ");
        texto1.setFont(fuente2);
        texto1.setForeground(new Color(32, 50, 72));
        texto1.setBounds(getWidth() / 2, 40, 390, 20);
        texto1.updateUI();
        panelDerecho.add(texto1);
        //tiempo 
        tiempo_real = new JLabel();
        tiempo_real.setText("0");
        tiempo_real.setFont(fuente);
        panelIzquierdo.add(tiempo_real);
        tiempo_real.setBounds(getWidth() / 25 + 140, getHeight() / 2 + 80, 500, 30);
        //Tabla procesos
        JScrollPane scroll = new JScrollPane();
        procesos = new JTable();
        procesos.setBackground(new Color(184, 207, 229));
        procesos.setForeground(new Color(235, 196, 56));
        scroll.setViewportView(procesos);
        scroll.setBounds((getWidth() / 2) - 160, 150, 660, 183);
        procesos.updateUI();
        panelDerecho.add(scroll);

        procesos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[10][8],
                new String[]{
                    "Proceso", "T. Llegada", "T. Ráfaga", "Prioridad",
                    "T. comienzo", "T. finalización", "T. retorno", "T. espera"
                }));

        //Tabla Gant
        JScrollPane scroll2 = new JScrollPane();
        gant = new JTable();
        gant.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        gant.setBackground(new Color(184, 207, 229));
        gant.setForeground(Color.BLACK);
        scroll2.setViewportView(gant);
        scroll2.setBounds((getWidth() / 2) - 160, 350, 660, 200);
        gant.updateUI();
        panelDerecho.add(scroll2);

        gant.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"1", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"2", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"3", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"4", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"5", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"6", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"7", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"8", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"9", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"10", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},},
                new String[]{
                    "P", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "TE", "TR"
                }) {

        });
        for (int i = 0; i < gant.getColumnCount(); i++) {
            gant.getColumnModel().getColumn(i).setPreferredWidth(20);
        }

        combo = new JComboBox();
        combo.addItem("Seleccione...");
        combo.addItem("✔ Iniciar Simulación");
        combo.addItem("✔ Pausar");
        combo.addItem("✔ Reanudar");
        combo.setBounds(getWidth() / 2 + 100, 40, 250, 22);
        combo.setLayout(null);
        combo.setBackground(new Color(24, 100, 167));
        combo.setForeground(new Color(235, 196, 56));
        combo.addActionListener(this);
        combo.updateUI();
        panelDerecho.add(combo);
    }

    public JTable getProcesos() {
        return procesos;
    }

    public JTable getGant() {
        return gant;
    }

    public void setGant(JTable gant) {
        this.gant = gant;
    }

    public JLabel getTiempo_real() {
        return tiempo_real;
    }

    public void setTiempo_real(JLabel tiempo_real) {
        this.tiempo_real = tiempo_real;
    }

}
