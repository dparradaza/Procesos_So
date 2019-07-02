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
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame implements ActionListener {

    private final DefaultTableModel modeloTabla;
    private Controlador ctrl;

    private JPanel panIzquierdo, panDerecho, panDibujo;
    private JButton btnInicio, btnPausa, btnBloq, btnDesb, btnAgregar;
    private JTable tblProcesos;
    private JLabel lblTitulo, lblNombres, lblBloq;
    private Font fuente, fuente1;
    private JTextArea areaBloq;

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
        setSize(1200, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setTitle("SRTF (Short Remaining Time First)");
    }

    private void PanelIzq() {
        panIzquierdo = new JPanel();
        panIzquierdo.setBounds(0, 0, (getWidth() / 2) - 300, getHeight());
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
        lblNombres.setBounds(25, 420, 300, 20);
        lblNombres.setForeground(new Color(0, 104, 139));
        panIzquierdo.add(lblNombres);
        // Botones
        btnInicio = new JButton("INICIAR");
        btnInicio.setBounds(60, 100, 90, 20);
        btnInicio.addActionListener(this);
        panIzquierdo.add(btnInicio);

        btnAgregar = new JButton("AGREGA");
        btnAgregar.setBounds(60, 150, 90, 20);
        btnAgregar.addActionListener(this);
        panIzquierdo.add(btnAgregar);

        /*btnPausa = new JButton("PAUSAR");
        btnPausa.setBounds(60, 150, 90, 20);
        btnPausa.addActionListener(this);
        panIzquierdo.add(btnPausa);*/

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
        scpArriba.setBounds((getWidth() / 2) - 280, 20, 600, 183);
        tblProcesos.updateUI();
        panDerecho.add(scpArriba);
        tblProcesos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "P.", "T.Llegada", "T.Rafaga", "T.Comienzo", "T.Final", "T. Retorno", "T.Espera"
                }
        ));
        //lbl bloqueados
        lblBloq = new JLabel("Bloqueados");
        lblBloq.setFont(fuente1);
        lblBloq.setBounds((getWidth() / 2) + 330, 10, 100, 40);
        lblBloq.setForeground(new Color(0, 104, 139));
        panDerecho.add(lblBloq);
        //bloqueados
        areaBloq = new JTextArea();
        areaBloq.setBackground(new Color(184, 207, 229));
        areaBloq.setBounds((getWidth() / 2) + 330, 45, 250, 157);
        panDerecho.add(areaBloq);

        //Canvas
        JScrollPane scpAbajo = new JScrollPane();
        panDibujo = new JPanel();
        panDibujo.setBackground(new Color(254, 249, 231));
        scpAbajo.setViewportView(panDibujo);
        scpAbajo.setBounds((getWidth() / 2) - 280, 220, 850, 300);
        panDerecho.add(scpAbajo);
        
        javax.swing.GroupLayout panelDibujoLayout = new javax.swing.GroupLayout(panDibujo);
        panDibujo.setLayout(panelDibujoLayout);
        panelDibujoLayout.setHorizontalGroup(
            panelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1341, Short.MAX_VALUE)
        );
        panelDibujoLayout.setVerticalGroup(
            panelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        scpAbajo.setViewportView(panDibujo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInicio) {
            iniciar();
            btnInicio.setEnabled(false);
        }

        if (e.getSource() == btnPausa) {

        }
        if (e.getSource() == btnBloq) {

        }
        if (e.getSource() == btnDesb) {

        }
        if (e.getSource() == btnAgregar) {
            agregar();
        }

    }

    public void iniciar() {
        if (ctrl == null) {
            ctrl = new Controlador(this, panDibujo, panDibujo);
        }
        for (int i = 0; i < 3; i++) {
            ctrl.crearProceso(ctrl.tiempoTranscurrido(), numeroProceso);
            numeroProceso++;
            llenarTabla();
        }
    }

    public void agregar() {
        if (ctrl == null) {
            ctrl = new Controlador(this, panDibujo, panDibujo);
        }
        ctrl.crearProceso(ctrl.tiempoTranscurrido(), numeroProceso);
        numeroProceso++;
        llenarTabla();
    }

    public void llenarTabla() {
        obj = new Object[]{ctrl.retornoProceso().numProceso,
            ctrl.retornoProceso().horaLlegada,
            ctrl.retornoProceso().tmpRafaga,
            ctrl.retornoProceso().tmpInicio,
            ctrl.retornoProceso().tmpFinal,
            ctrl.retornoProceso().tmpRetorno,
            ctrl.retornoProceso().tmpEspera};
        modeloTabla.addRow(obj);
    }

    public void modificarTabla(ArrayList<Integer> lista, int fila, int columna) {
        tblProcesos.setValueAt(lista, fila, columna);

    }

    public void modificarTabla(int valor, int fila, int columna) {
        tblProcesos.setValueAt(valor, fila, columna);

    }

    public JTable getTblProcesos() {
        return tblProcesos;
    }

}
