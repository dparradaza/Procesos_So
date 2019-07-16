package Vista;

import fifo.Fifo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import roundRobin.RoundRobin;
import srtf.Srtf;

public class Interfaz extends JFrame implements ActionListener {

    private JButton btnAgregaFIFO, btnAgregaRR, btnAgregaSFJ, btnBloqFIFO, btnBloqRR, btnBloqSJF, btnIniciar;
    private JLabel lblIotalInteractivos, lblNumRafEje, lblNumRafTotal,
            lblNumTotFinal, lblPBloq, lblPEjecucion, lblProEjecucion,
            lblQuantum, lblRafEje, lblRafTot, lblSemaforo, lblSuspendido,
            lblTiempoReal, lblTotFinal, lblTotalBloqueados, lblTotalCola1,
            lblTotalCola2, lblTotalCola3, lblTotalSistema, lblTotalSuspendidos,
            lblTotalUsuarios, lblTranscurrido, lblTitulo, lblTituloAtendidos, lblTituloBloqueados, lblTituloRR, lblTituloSJF, lblTituloFIFO;
    private JPanel panVentana, panEjecucion;
    private JScrollPane scpBloqueados, scpFIFO, scpFinalizados, scpGantt, scpRoundRobin, scpSFJ;
    private JTable tblGantt;
    private JTextArea texto_atendidos, texto_bloqueados, txaFIFO, txaRoundRobin, txaSJF;

    private Font fuente, fuente1;

    int x = 0;
    public Fifo fifo = new Fifo(this);
    public Srtf srtf = new Srtf(this, fifo);
    public RoundRobin round_robin = new RoundRobin(this, srtf);

    public Interfaz() {
        inicializar();
        componentes();
        dibujaGantt();
        dibujaColas();
        crearVentana();
        tblGantt.selectAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            iniciar();
        } else if (e.getSource() == btnAgregaRR) {
            agregaRR();
        } else if (e.getSource() == btnAgregaSFJ) {
            agregaSFJ();
        } else if (e.getSource() == btnAgregaFIFO) {
            agregaFIFO();
        } else if (e.getSource() == btnBloqRR) {
            bloqRR();
        } else if (e.getSource() == btnBloqSJF) {
            bloqSFJ();
        } else if (e.getSource() == btnBloqFIFO) {
            bloqFIFO();
        }
    }

    private void iniciar() {
        if (x == 0) {
            lblSemaforo.setOpaque(true);//rellena de color el label
            lblSemaforo.setForeground(Color.black);
            btnIniciar.setEnabled(false);
            fifo.setRobin(round_robin);
            round_robin.start();
            srtf.start();
            srtf.fifo.start();
        } else {
            JOptionPane.showMessageDialog(null, "Imposible comenzar nuevamente");
        }
        x = x + 1;
    }

    private void agregaRR() {
        //JOptionPane.showMessageDialog(null, "Se agrego un proceso a cola1");
        round_robin.agregar_proceso = true;
        round_robin.agregarProceso();
    }

    private void agregaFIFO() {
        //JOptionPane.showMessageDialog(null, "Se agrego un proceso a cola3");
        fifo.agregar_proceso = true;
        fifo.agregarProceso();
    }

    private void agregaSFJ() {
        //JOptionPane.showMessageDialog(null, "Se agrego un proceso a cola2");
        srtf.agregar_proceso = true;
        srtf.agregarProceso();
    }

    private void bloqRR() {
        round_robin.bandera = true;
    }

    private void bloqSFJ() {
        srtf.bandera = true;
    }

    private void bloqFIFO() {
        fifo.bandera = true;
    }

    private void inicializar() {
        panVentana = new JPanel();
        panEjecucion = new JPanel();
        lblSuspendido = new JLabel();
        lblTotalSuspendidos = new JLabel();
        lblTranscurrido = new JLabel();
        lblTiempoReal = new JLabel();
        lblTotalBloqueados = new JLabel();
        lblPBloq = new JLabel();
        lblPEjecucion = new JLabel();
        lblProEjecucion = new JLabel();
        lblQuantum = new JLabel();
        lblTotFinal = new JLabel();
        lblRafTot = new JLabel();
        lblRafEje = new JLabel();
        lblNumTotFinal = new JLabel();
        lblNumRafTotal = new JLabel();
        lblNumRafEje = new JLabel();
        scpGantt = new JScrollPane();
        tblGantt = new JTable();
        scpRoundRobin = new JScrollPane();
        txaRoundRobin = new JTextArea();
        scpSFJ = new JScrollPane();
        txaSJF = new JTextArea();
        scpFIFO = new JScrollPane();
        txaFIFO = new JTextArea();
        scpFinalizados = new JScrollPane();
        texto_atendidos = new JTextArea();
        lblTotalCola1 = new JLabel();
        lblTotalCola2 = new JLabel();
        lblTotalCola3 = new JLabel();
        lblTotalSistema = new JLabel();
        lblIotalInteractivos = new JLabel();
        lblTotalUsuarios = new JLabel();
        lblSemaforo = new JLabel();
        scpBloqueados = new JScrollPane();
        texto_bloqueados = new JTextArea();

        lblTitulo = new javax.swing.JLabel();
        lblTituloRR = new javax.swing.JLabel();
        lblTituloSJF = new javax.swing.JLabel();
        lblTituloFIFO = new javax.swing.JLabel();
        lblTituloAtendidos = new javax.swing.JLabel();
        lblTituloBloqueados = new javax.swing.JLabel();

        btnIniciar = new JButton("INICIAR");
        btnIniciar.addActionListener(this);

        btnAgregaRR = new JButton("\u2713");
        btnAgregaRR.addActionListener(this);

        btnAgregaSFJ = new JButton("\u2713");
        btnAgregaSFJ.addActionListener(this);

        btnAgregaFIFO = new JButton("\u2713");
        btnAgregaFIFO.addActionListener(this);

        btnBloqRR = new JButton("\u2715");
        btnBloqRR.addActionListener(this);

        btnBloqSJF = new JButton("\u2715");
        btnBloqSJF.addActionListener(this);

        btnBloqFIFO = new JButton("\u2715");
        btnBloqFIFO.addActionListener(this);

    }

    private void crearVentana() {
        setTitle("Multicolas Retroalimentadas");
        setSize(1360, 670);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        panVentana.setBounds(0, 0, 1366, 725);
        panVentana.setBackground(new java.awt.Color(229, 231, 233));
    }

    private void dibujaGantt() {
        scpGantt.setMinimumSize(new java.awt.Dimension(1080, 23));
        tblGantt.setAutoCreateRowSorter(true);
        tblGantt.setBorder(BorderFactory.createEtchedBorder());
        tblGantt.setModel(new javax.swing.table.DefaultTableModel(
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
                    {"10", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"11", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"12", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"13", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"14", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"15", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"16", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"17", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"18", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"19", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"20", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"21", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"22", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"23", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"24", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {"25", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "P", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "TE", "TS"
                }
        ));
        tblGantt.setToolTipText("");
        tblGantt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblGantt.setColumnSelectionAllowed(true);
        tblGantt.setGridColor(new java.awt.Color(0, 0, 102));
        tblGantt.setMaximumSize(new java.awt.Dimension(1086, 416));
        tblGantt.setMinimumSize(new java.awt.Dimension(1026, 416));
        tblGantt.setPreferredSize(new java.awt.Dimension(2200, 416));
        scpGantt.setViewportView(tblGantt);
        tblGantt.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    }

    @SuppressWarnings("unchecked")
    private void componentes() {
        fuente = new Font("Consolas", Font.BOLD, 11);
        fuente1 = new java.awt.Font("Tahoma", 1, 14);

        panEjecucion.setBackground(new java.awt.Color(255, 255, 255));
        panEjecucion.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSuspendido.setFont(fuente);
        lblSuspendido.setText("P. Suspendidos:");

        lblTotalSuspendidos.setFont(fuente);
        lblTotalSuspendidos.setText("0");

        lblTranscurrido.setFont(fuente);
        lblTranscurrido.setText("T. Transcurrido:");

        lblTiempoReal.setFont(fuente);
        lblTiempoReal.setText("0");

        lblTotalBloqueados.setFont(fuente);
        lblTotalBloqueados.setText("0");

        lblPBloq.setFont(fuente);
        lblPBloq.setText("P. Bloqueados:");

        lblPEjecucion.setFont(fuente);
        lblPEjecucion.setText("0");

        lblProEjecucion.setFont(fuente);
        lblProEjecucion.setText("P. Ejecucion: ");

        lblQuantum.setFont(fuente);
        lblQuantum.setText("Quantum:            5");

        lblTotFinal.setText("Total Finalizados:");
        lblTotFinal.setFont(fuente);

        lblRafTot.setText("Ráfaga total:");
        lblRafTot.setFont(fuente);

        lblRafEje.setText("Ráfaga ejecutada:");
        lblRafEje.setFont(fuente);

        lblNumTotFinal.setText("0");
        lblNumTotFinal.setFont(fuente);

        lblNumRafTotal.setText("0");
        lblNumRafTotal.setFont(fuente);

        lblNumRafEje.setText("0");
        lblNumRafEje.setFont(fuente);

        diseñoPanEjecucion();

        lblTotalCola1.setText("Total Cola1: ");
        lblTotalCola2.setText("Total Cola2: ");
        lblTotalCola3.setText("Total Cola3: ");

        lblTotalSistema.setText("0");

        lblIotalInteractivos.setText("0");

        lblTotalUsuarios.setText("0");

        lblSemaforo.setBackground(new java.awt.Color(0, 204, 0));
        lblSemaforo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSemaforo.setBorder(BorderFactory.createTitledBorder(""));

        lblTitulo.setFont(fuente1);
        lblTitulo.setText("Multicolas con Retroalimentacion");

        lblTituloRR.setText("Round Robin (Verde) ");

        lblTituloSJF.setText("SJF (Naranja) ");

        lblTituloFIFO.setText("Prioridad (Morado)");

        lblTituloAtendidos.setText("Atendidos");

        lblTituloBloqueados.setText("Bloqueados");

        scpBloqueados.setBackground(new java.awt.Color(0, 204, 204));

        texto_bloqueados.setEditable(false);
        texto_bloqueados.setColumns(20);
        texto_bloqueados.setRows(5);
        texto_bloqueados.setToolTipText("");
        scpBloqueados.setViewportView(texto_bloqueados);

        disenoPanVentana();
        pack();
    }

    private void dibujaColas() {
        scpRoundRobin.setBackground(new java.awt.Color(255, 0, 0));

        txaRoundRobin.setEditable(false);
        txaRoundRobin.setColumns(20);
        txaRoundRobin.setRows(5);
        txaRoundRobin.setToolTipText("");
        scpRoundRobin.setViewportView(txaRoundRobin);

        scpSFJ.setBackground(new java.awt.Color(255, 0, 0));

        txaSJF.setEditable(false);
        txaSJF.setColumns(20);
        txaSJF.setRows(5);
        txaSJF.setToolTipText("");
        scpSFJ.setViewportView(txaSJF);

        scpFIFO.setBackground(new java.awt.Color(255, 0, 0));

        txaFIFO.setEditable(false);
        txaFIFO.setColumns(20);
        txaFIFO.setRows(5);
        txaFIFO.setToolTipText("");
        scpFIFO.setViewportView(txaFIFO);

        scpFinalizados.setBackground(new java.awt.Color(0, 102, 102));
        scpFinalizados.setAutoscrolls(true);
        scpFinalizados.setEnabled(false);

        texto_atendidos.setEditable(false);
        texto_atendidos.setColumns(20);
        texto_atendidos.setRows(5);
        texto_atendidos.setToolTipText("");
        scpFinalizados.setViewportView(texto_atendidos);
    }

    //GET and SET
    public JLabel getlblSemaforo() {
        return lblSemaforo;
    }

    public JTextArea getTexto_sistema() {
        return txaRoundRobin;
    }

    public JTextArea getTexto_usuarios() {
        return txaFIFO;
    }

    public JLabel getTotal_sistema() {
        return lblTotalSistema;
    }

    public JLabel getTotal_usuarios() {
        return lblTotalUsuarios;
    }


    public JLabel getTotal_suspendidos() {
        return lblTotalSuspendidos;
    }

    public JTable getTblGantt() {
        return tblGantt;
    }

    public JLabel getTiempo_real() {
        return lblTiempoReal;
    }

    public JLabel getCpu_nuevo() {
        return lblNumRafTotal;
    }

    public JLabel getProcesos_en_ejecucion() {
        return lblPEjecucion;
    }

    public JTextArea getTexto_atendidos() {
        return texto_atendidos;
    }

    public JTextArea getTexto_bloqueados() {
        return texto_bloqueados;
    }

    public JTextArea getTexto_interactivos() {
        return txaSJF;
    }

    public JLabel getTiempo_cpu() {
        return lblNumRafEje;
    }

    public JLabel getTotal_atendidos() {
        return lblNumTotFinal;
    }

    public JLabel getTotal_bloqueados() {
        return lblTotalBloqueados;
    }

    public JLabel getTotal_interactivos() {
        return lblIotalInteractivos;
    }

    private void diseñoPanEjecucion() {
        javax.swing.GroupLayout panEjecucionLayout = new javax.swing.GroupLayout(panEjecucion);
        panEjecucion.setLayout(panEjecucionLayout);
        panEjecucionLayout.setHorizontalGroup(
                panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblRafTot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblRafEje, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNumRafTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNumRafEje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(lblQuantum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panEjecucionLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(lblSuspendido, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblTranscurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblProEjecucion)
                                                                        .addComponent(lblPBloq, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblTotFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblPEjecucion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblTiempoReal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblTotalBloqueados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblTotalSuspendidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblNumTotFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panEjecucionLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblNumTotFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        panEjecucionLayout.setVerticalGroup(
                panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblPEjecucion)
                                                        .addComponent(lblProEjecucion)))
                                        .addGroup(panEjecucionLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblTranscurrido)
                                                        .addComponent(lblTiempoReal))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTotalBloqueados)
                                        .addComponent(lblPBloq))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSuspendido)
                                        .addComponent(lblTotalSuspendidos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTotFinal)
                                        .addComponent(lblNumTotFinal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRafTot)
                                        .addComponent(lblNumRafTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panEjecucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRafEje)
                                        .addComponent(lblNumRafEje, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQuantum)
                                .addGap(33, 33, 33))
        );
    }

    private void disenoPanVentana() {
        javax.swing.GroupLayout panVentanaLayout = new javax.swing.GroupLayout(panVentana);
        panVentana.setLayout(panVentanaLayout);
        panVentanaLayout.setHorizontalGroup(
                panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panVentanaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                                .addComponent(lblTituloRR)
                                                                .addGap(42, 42, 42)
                                                                .addComponent(lblTotalCola1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblTotalSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(90, 90, 90)
                                                                .addComponent(btnAgregaRR)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnBloqRR))
                                                        .addComponent(scpRoundRobin, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42)
                                                .addComponent(lblTituloSJF)
                                                .addGap(80, 80, 80)
                                                .addComponent(lblTotalCola2)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblIotalInteractivos, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(74, 74, 74)
                                                .addComponent(btnAgregaSFJ)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBloqSJF)
                                                .addGap(28, 28, 28)
                                                .addComponent(lblTituloFIFO)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblTotalCola3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblTotalUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnAgregaFIFO)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBloqFIFO)
                                                .addGap(57, 57, 57))
                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(lblTituloAtendidos)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblTituloBloqueados)
                                                .addGap(397, 397, 397))
                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(lblTitulo))
                                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                                .addComponent(scpGantt, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(panEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblSemaforo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(scpSFJ, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(scpFinalizados, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(scpBloqueados, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                                                        .addComponent(scpFIFO))))
                                                .addContainerGap(18, Short.MAX_VALUE))))
        );
        panVentanaLayout.setVerticalGroup(
                panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panVentanaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitulo)
                                .addGap(14, 14, 14)
                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scpGantt, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(panEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panVentanaLayout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addComponent(lblSemaforo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)
                                                                .addComponent(btnIniciar)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblTotalSistema)
                                                .addComponent(lblTituloRR)
                                                .addComponent(lblTotalCola1))
                                        .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnAgregaSFJ)
                                                .addComponent(lblTotalCola2)
                                                .addComponent(lblIotalInteractivos)
                                                .addComponent(lblTituloSJF)
                                                .addComponent(btnBloqRR)
                                                .addComponent(btnAgregaRR)
                                                .addComponent(btnBloqFIFO)
                                                .addComponent(btnAgregaFIFO)
                                                .addComponent(lblTotalCola3)
                                                .addComponent(lblTotalUsuarios)
                                                .addComponent(lblTituloFIFO)
                                                .addComponent(btnBloqSJF)))
                                .addGap(6, 6, 6)
                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scpRoundRobin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scpFIFO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scpSFJ, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTituloAtendidos)
                                        .addComponent(lblTituloBloqueados))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scpBloqueados, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scpFinalizados, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }
}
