package Vista;

import Controlador.Controlador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame implements ActionListener {

    private final DefaultTableModel modeloTabla;
    private Controlador control;

    private JPanel panIzquierdo, panDerecho, panDibujo;
    private JButton btnInicio, btnPausa, btnBloq, btnDesb;
    private JTable tblProcesos;
    private JLabel lblTitulo, lblNombres;
    private Font fuente, fuente1;

    int numeroProceso = 1;
    Object[] obj;

    public Ventana() {
        crearVentana();
        PanelIzq();
        PanelDer();
        Componentes();
        modeloTabla = (DefaultTableModel) tblProcesos.getModel();
        panDibujo.setPreferredSize(new Dimension(20000, 2000));
    }

    private void crearVentana() {
        setSize(1300, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setTitle("SRTF (Short Remaining Time First)");
    }

    private void PanelIzq() {
        panIzquierdo = new JPanel();
        panIzquierdo.setBounds(0, 0, (getWidth() / 2) - 300,getHeight());
        panIzquierdo.setBackground(new Color(229, 231, 233));
        panIzquierdo.setLayout(null);
        panIzquierdo.updateUI();
        this.add(panIzquierdo);
    }

    private void PanelDer() {
        panDerecho = new JPanel();
        panDerecho.setBounds((getWidth() / 2) + 150, 0, getWidth(), getHeight());
        panDerecho.setBackground(new Color(250, 215, 160));
        panDerecho.setLayout(null);
        panDerecho.updateUI();
        this.add(panDerecho);
    }

    private void Componentes() {
        fuente = new Font("Bookman Old Style", Font.BOLD, 22);
        fuente1 = new Font("Consolas", Font.BOLD, 15);
        //-----------------------
        lblTitulo = new JLabel("ALGORITMO SRTF");
        lblTitulo.setFont(fuente);
        lblTitulo.setBounds(getWidth() / 60, getHeight() / 20, 500, 20);
        lblTitulo.setForeground(new Color(0, 104, 139));
        panIzquierdo.add(lblTitulo);

        lblNombres = new JLabel("Oscar Bautista & Diego Parra");
        lblNombres.setFont(fuente1);
        lblNombres.setBounds(125, 420, 300, 20);
        lblNombres.setForeground(new Color(0, 104, 139));
        panIzquierdo.add(lblNombres);
        // Botones
        btnInicio = new JButton("INICIAR");
        btnInicio.setBounds(60, 100, 90, 20);
        btnInicio.addActionListener(this);
        panIzquierdo.add(btnInicio);

        btnPausa = new JButton("PAUSAR");
        btnPausa.setBounds(60, 150, 90, 20);
        btnPausa.addActionListener(this);
        panIzquierdo.add(btnPausa);

        btnBloq = new JButton("BLOQUEAR");
        btnBloq.setBounds(160, 100, 125, 20);
        btnBloq.addActionListener(this);
        panIzquierdo.add(btnBloq);

        btnDesb = new JButton("DESBLOQUEAR");
        btnDesb.setBounds(160, 150, 125, 20);
        btnDesb.addActionListener(this);
        panIzquierdo.add(btnDesb);

        //TABLA
        JScrollPane scpArriba = new JScrollPane();
        tblProcesos = new JTable();
        tblProcesos.setBackground(new Color(184, 207, 229));
        tblProcesos.setForeground(Color.BLACK);
        scpArriba.setViewportView(tblProcesos);
        scpArriba.setBounds((getWidth() / 2) - 160, 30, 660, 183);
        tblProcesos.updateUI();
        panDerecho.add(scpArriba);
        tblProcesos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Proceso", "T.Rafada", "T.Llegada", "T.Comienzo", "T.Final", "T. Retorno", "T.Espera"
                }
        ));

        //Canvas
        JScrollPane scpAbajo = new JScrollPane();
        panDibujo = new JPanel();
        panDibujo.setBackground(new Color(254, 249, 231));
        scpAbajo.setViewportView(panDibujo);
        scpAbajo.setBounds((getWidth() / 2) - 280, 220, 800, 300);
        panDerecho.add(scpAbajo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInicio) {
            iniciar();
        }
        if (e.getSource() == btnPausa) {

        }
        if (e.getSource() == btnBloq) {

        }
        if (e.getSource() == btnDesb) {

        }
    }

    public void iniciar() {
        if (control == null) {
            control = new Controlador(this, panDibujo, panDibujo);
        }
        control.crearProceso(control.tiempoTranscurrido(), numeroProceso);
        numeroProceso++;
        llenarTabla();
    }

    public void llenarTabla() {
        obj = new Object[]{control.retornoProceso().numeroProceso, control.retornoProceso().tiempoEjecucion,
            control.retornoProceso().horaLlegada, control.retornoProceso().tiempoInicio,
            control.retornoProceso().tiempoFinalizacion, control.retornoProceso().tiempoRetorno,
            control.retornoProceso().tiempoEspera};
        modeloTabla.addRow(obj);
    }

    public void modificarTabla(ArrayList<Integer> lista, int fila, int columna) {
        tblProcesos.setValueAt(lista, fila, columna);

    }
    
    public void modificarTabla(int valor,int fila,int columna){
       tblProcesos.setValueAt(valor, fila, columna);
        
    }

    public JTable getTblProcesos() {
        return tblProcesos;
    }

}
