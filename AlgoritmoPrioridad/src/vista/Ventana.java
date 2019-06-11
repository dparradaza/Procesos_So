package vista;

import controlador.Controlador;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Ventana extends JFrame implements ActionListener {

    private final Controlador control;
    private JPanel panelIzquierdo, panelDerecho;
    private JButton btnInicio, btnPausa, btnBloq, btnDesb;
    private JTable tblProcesos;
    private Canvas gant;
    private JLabel titulo, nombres;
    private Font fuente, fuente1;

    public Ventana(Controlador ctr) {
        crearVentana();
        PanelIzq();
        PanelDer();
        Componentes();
        this.control = ctr;
    }

    private void crearVentana() {
        setSize(1100, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
    }

    private void PanelIzq() {
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBounds(0, 0, (getWidth() / 2) - 190, getHeight());
        panelIzquierdo.setBackground(new Color(229, 231, 233));
        panelIzquierdo.setLayout(null);
        panelIzquierdo.updateUI();
        this.add(panelIzquierdo);
    }

    private void PanelDer() {
        panelDerecho = new JPanel();
        panelDerecho.setBounds((getWidth() / 2) + 150, 0, getWidth(), getHeight());
        panelDerecho.setBackground(new Color(250, 215, 160));
        panelDerecho.setLayout(null);
        panelDerecho.updateUI();
        this.add(panelDerecho);
    }

    private void Componentes() {
        fuente = new Font("Bookman Old Style", Font.BOLD, 22);
        fuente1 = new Font("Consolas", Font.BOLD, 15);
        //-----------------------
        titulo = new JLabel("ALGORITMO DE PRIORIDAD");
        titulo.setFont(fuente);
        titulo.setBounds(getWidth() / 60, getHeight() / 20, 500, 20);
        titulo.setForeground(new Color(0, 104, 139));
        panelIzquierdo.add(titulo);

        nombres = new JLabel("Oscar Bautista & Diego Parra");
        nombres.setFont(fuente1);
        nombres.setBounds(125, 420, 300, 20);
        nombres.setForeground(new Color(0, 104, 139));
        panelIzquierdo.add(nombres);
        // Botones
        btnInicio = new JButton("INICIAR");
        btnInicio.setBounds(60, 100, 90, 20);
        btnInicio.addActionListener(this);
        panelIzquierdo.add(btnInicio);

        btnPausa = new JButton("PAUSAR");
        btnPausa.setBounds(60, 150, 90, 20);
        btnPausa.addActionListener(this);
        panelIzquierdo.add(btnPausa);

        btnBloq = new JButton("BLOQUEAR");
        btnBloq.setBounds(160, 100, 125, 20);
        btnBloq.addActionListener(this);
        panelIzquierdo.add(btnBloq);

        btnDesb = new JButton("DESBLOQUEAR");
        btnDesb.setBounds(160, 150, 125, 20);
        btnDesb.addActionListener(this);
        panelIzquierdo.add(btnDesb);

        //TABLA
        JScrollPane scpArriba = new JScrollPane();
        tblProcesos = new JTable();
        tblProcesos.setBackground(new Color(184, 207, 229));
        tblProcesos.setForeground(Color.BLACK);
        scpArriba.setViewportView(tblProcesos);
        scpArriba.setBounds((getWidth() / 2) - 160, 30, 660, 183);
        tblProcesos.updateUI();
        panelDerecho.add(scpArriba);
        tblProcesos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Proceso", "Prioridad", "T. Llegada", "T. Ráfaga", "T. Retorno", "T. Espera", "T. Final", "✔/✖"
                }
        ));

        //Canvas
        JScrollPane scpAbajo = new JScrollPane();
        gant = new Canvas();
        gant.setBackground(new Color(254, 249, 231));
        scpAbajo.setViewportView(gant);
        scpAbajo.setBounds((getWidth() / 2) - 160, 220, 660, 220);
        panelDerecho.add(scpAbajo);
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInicio) {
            control.iniciar();
        }
        if (e.getSource() == btnPausa) {
            control.pausar();
        }
        if (e.getSource() == btnBloq) {
            control.bloquear(true);
        }
        if (e.getSource() == btnDesb) {
            control.bloquear(false);
        }
    }

    public JTable getTblProcesos() {
        return tblProcesos;
    }

    public Canvas getGant() {
        return gant;
    }

}
