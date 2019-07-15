/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author ADBA
 */
public class Ventana extends JFrame {

    private JLabel cpu_nuevo1, jLabel11, jLabel13, jLabel29, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8;
    private JLabel procesos_en_ejecucion, tiempo_cpu, tiempo_real, total_bloqueados, total_listos, total_suspendidos;
    private JButton jButton1, jButton10, jButton11, jButton2, jButton4;
    private JPanel jPanel15, jPanel16, jPanel3, jPanel5;
    private JScrollPane jScrollPane1, jScrollPane6, jScrollPane7, jScrollPane8, jScrollPane9;
    private JTable jTable1;
    private JTextArea texto_bloqueados, texto_diagrama, texto_listos, texto_suspendidos;

    RoundRobin obj = new RoundRobin(this);
    int x = 0;

    public Ventana() {
        componentes();
        jTable1.selectAll();
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
        jButton1.requestFocusInWindow(); //recibe el foco
    }

    public final void componentes() {
        tiempo_cpu = new JLabel();
        jLabel7 = new JLabel();
        jLabel5 = new JLabel();
        cpu_nuevo1 = new JLabel();
        jPanel3 = new JPanel();
        jScrollPane7 = new JScrollPane();
        jTable1 = new JTable();
        jPanel15 = new JPanel();
        jScrollPane1 = new JScrollPane();
        texto_listos = new JTextArea();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton4 = new JButton();
        jButton10 = new JButton();
        jButton11 = new JButton();
        jScrollPane8 = new JScrollPane();
        texto_bloqueados = new JTextArea();
        jScrollPane9 = new JScrollPane();
        texto_suspendidos = new JTextArea();
        jScrollPane6 = new JScrollPane();
        texto_diagrama = new JTextArea();
        jLabel29 = new JLabel();
        tiempo_real = new JLabel();
        total_listos = new JLabel();
        jLabel11 = new JLabel();
        total_bloqueados = new JLabel();
        jLabel4 = new JLabel();
        jLabel8 = new JLabel();
        jLabel13 = new JLabel();
        total_suspendidos = new JLabel();
        jLabel6 = new JLabel();
        procesos_en_ejecucion = new JLabel();
        jPanel5 = new JPanel();
        jPanel16 = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 1365, 740);
        setMinimumSize(new java.awt.Dimension(1365, 720));
        setTitle("Round Robin");
        setLocationRelativeTo(null);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(tiempo_cpu);
        tiempo_cpu.setBounds(420, 770, 63, 14);

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Ráfaga ejecutada: ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(300, 770, 114, 14);

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel5.setText("Tiempo ráfaga proceso:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(120, 770, 148, 14);
        getContentPane().add(cpu_nuevo1);
        cpu_nuevo1.setBounds(260, 770, 62, 14);

        jPanel3.setBackground(new java.awt.Color(255, 102, 51));
        jPanel3.setLayout(null);

        jScrollPane7.setToolTipText("");
        jScrollPane7.setMinimumSize(new java.awt.Dimension(1080, 23));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
                    "P", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "TE", "TR"
                }
        ));
        jTable1.setToolTipText("");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setGridColor(new java.awt.Color(0, 0, 102));
        jTable1.setMaximumSize(new java.awt.Dimension(1086, 416));
        jTable1.setMinimumSize(new java.awt.Dimension(1026, 416));
        jTable1.setPreferredSize(new java.awt.Dimension(2200, 416));
        jScrollPane7.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jPanel3.add(jScrollPane7);
        jScrollPane7.setBounds(510, 30, 810, 320);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(null);
        jPanel3.add(jPanel15);
        jPanel15.setBounds(0, 690, 1370, 10);

        jScrollPane1.setBackground(new java.awt.Color(255, 0, 0));

        texto_listos.setEditable(false);
        texto_listos.setColumns(20);
        texto_listos.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        texto_listos.setRows(5);
        texto_listos.setToolTipText("");
        texto_listos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                texto_listosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(texto_listos);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 480, 320);

        jButton1.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton1.setText("INICIAR");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel3.add(jButton1);
        jButton1.setBounds(10, 580, 200, 60);

        jButton2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton2.setText("PAUSAR");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton2KeyTyped(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(220, 580, 200, 25);

        jButton4.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton4.setText("REANUDAR");
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel3.add(jButton4);
        jButton4.setBounds(220, 620, 200, 25);

        jButton10.setText("Agregar Proceso");
        jButton10.addActionListener(this::jButton10ActionPerformed);
        jPanel3.add(jButton10);
        jButton10.setBounds(20, 360, 190, 50);

        jButton11.setText("Bloquear Proceso");
        jButton11.addActionListener(this::jButton11ActionPerformed);
        jPanel3.add(jButton11);
        jButton11.setBounds(470, 580, 220, 25);

        jScrollPane8.setBackground(new java.awt.Color(0, 204, 204));

        texto_bloqueados.setEditable(false);
        texto_bloqueados.setColumns(20);
        texto_bloqueados.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        texto_bloqueados.setRows(5);
        texto_bloqueados.setToolTipText("");
        jScrollPane8.setViewportView(texto_bloqueados);

        jPanel3.add(jScrollPane8);
        jScrollPane8.setBounds(450, 460, 260, 110);

        jScrollPane9.setBackground(new java.awt.Color(255, 153, 51));

        texto_suspendidos.setEditable(false);
        texto_suspendidos.setColumns(20);
        texto_suspendidos.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        texto_suspendidos.setRows(5);
        texto_suspendidos.setToolTipText("");
        jScrollPane9.setViewportView(texto_suspendidos);

        jPanel3.add(jScrollPane9);
        jScrollPane9.setBounds(750, 460, 260, 110);

        jScrollPane6.setBackground(new java.awt.Color(0, 102, 102));
        jScrollPane6.setAutoscrolls(true);
        jScrollPane6.setEnabled(false);

        texto_diagrama.setEditable(false);
        texto_diagrama.setColumns(20);
        texto_diagrama.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        texto_diagrama.setRows(5);
        texto_diagrama.setToolTipText("");
        jScrollPane6.setViewportView(texto_diagrama);

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(1060, 460, 260, 150);

        jLabel29.setBackground(new java.awt.Color(0, 102, 102));
        jLabel29.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PROCESOS FINALIZADOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel3.add(jLabel29);
        jLabel29.setBounds(1060, 420, 260, 20);

        tiempo_real.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        tiempo_real.setText("0");
        jPanel3.add(tiempo_real);
        tiempo_real.setBounds(100, 500, 90, 50);

        total_listos.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        total_listos.setText("0");
        jPanel3.add(total_listos);
        total_listos.setBounds(270, 370, 31, 30);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel11.setText("Procesos Bloqueados:");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(450, 430, 190, 15);

        total_bloqueados.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        total_bloqueados.setText("0");
        jPanel3.add(total_bloqueados);
        total_bloqueados.setBounds(650, 430, 65, 15);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel4.setText("Quantum:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(250, 520, 90, 15);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel8.setText("5");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(340, 520, 10, 15);

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel13.setText("Procesos Suspendidos:");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(770, 430, 180, 15);

        total_suspendidos.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        total_suspendidos.setText("0");
        jPanel3.add(total_suspendidos);
        total_suspendidos.setBounds(960, 430, 20, 15);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel6.setText("Proceso en ejecucion: ");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(510, 360, 210, 20);

        procesos_en_ejecucion.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        procesos_en_ejecucion.setText("0");
        jPanel3.add(procesos_en_ejecucion);
        procesos_en_ejecucion.setBounds(720, 360, 41, 15);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 10, 1370, 700);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);
        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1370, 10);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(null);
        getContentPane().add(jPanel16);
        jPanel16.setBounds(0, 690, 1370, 10);

        pack();
    }// </editor-fold>                        

    private void formKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

    }

    private void texto_listosKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        char teclaPresionada = evt.getKeyChar();
        if (teclaPresionada == KeyEvent.VK_ENTER) {
            jButton1.doClick();
            jButton2.requestFocusInWindow();
        }
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (x == 0) {
            obj.start();
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            jButton2.requestFocusInWindow();
        } else {
            JOptionPane.showMessageDialog(null, "Imposible comenzar nuevamente");
        }
        x = x + 1;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        obj.suspend();
        jButton2.setEnabled(false);
        jButton4.setEnabled(true);
        jButton4.requestFocusInWindow();
        // TODO add your handling code here:
    }

    private void jButton2KeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        obj.resume();
        jButton2.setEnabled(true);
        jButton4.setEnabled(false);
        jButton2.requestFocusInWindow();
        // TODO add your handling code here:
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        obj.bandera = true;
    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ha agregado un nuevo proceso.");
        obj.agregarProceso();
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public JButton getjButton10() {
        return jButton10;
    }

    public JButton getjButton11() {
        return jButton11;
    }

    public void setjTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }

    public JLabel getTiempo_real() {
        return tiempo_real;
    }

    public void setTiempo_real(JLabel tiempo_real) {
        this.tiempo_real = tiempo_real;
    }

    public JLabel getCpu_nuevo() {
        return cpu_nuevo1;
    }

    public void setCpu_nuevo(JLabel cpu_nuevo) {
        this.cpu_nuevo1 = cpu_nuevo;
    }

    public JTextArea getTexto_diagrama() {
        return texto_diagrama;
    }

    public void setTexto_diagrama(JTextArea texto_diagrama) {
        this.texto_diagrama = texto_diagrama;
    }

    public JLabel getProcesos_en_ejecucion() {
        return procesos_en_ejecucion;
    }

    public void setProcesos_en_ejecucion(JLabel procesos_en_ejecucion) {
        this.procesos_en_ejecucion = procesos_en_ejecucion;
    }

    public JTextArea getTexto_bloqueados() {
        return texto_bloqueados;
    }

    public void setTexto_bloqueados(JTextArea texto_bloqueados) {
        this.texto_bloqueados = texto_bloqueados;
    }

    public JTextArea getTexto_listos() {
        return texto_listos;
    }

    public void setTexto_listos(JTextArea texto_listos) {
        this.texto_listos = texto_listos;
    }

    public JLabel getTiempo_cpu() {
        return tiempo_cpu;
    }

    public void setTiempo_cpu(JLabel tiempo_cpu) {
        this.tiempo_cpu = tiempo_cpu;
    }

    public JTextArea getTexto_suspendidos() {
        return texto_suspendidos;
    }

    public void setTexto_suspendidos(JTextArea texto_suspendidos) {
        this.texto_suspendidos = texto_suspendidos;
    }

    public JLabel getTotal_suspendidos() {
        return total_suspendidos;
    }

    public void setTotal_suspendidos(JLabel total_suspendidos) {
        this.total_suspendidos = total_suspendidos;
    }

    public JLabel getTotal_bloqueados() {
        return total_bloqueados;
    }

    public void setTotal_bloqueados(JLabel total_bloqueados) {
        this.total_bloqueados = total_bloqueados;
    }

    public JLabel getTotal_listos() {
        return total_listos;
    }

    public void setTotal_listos(JLabel total_listos) {
        this.total_listos = total_listos;
    }
}
