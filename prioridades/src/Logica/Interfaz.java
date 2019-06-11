/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Interfaz extends javax.swing.JFrame {
    private JLabel cpu_nuevo1;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton4;
    private JButton jButton5;
    private JLabel jLabel12;
    private JLabel jLabel15;
    private JLabel jLabel4;
    private JLabel jLabel9;
    private JPanel jPanel13;
    private JPanel jPanel3;
    private JPanel jPanel5;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane7;
    private JScrollPane jScrollPane8;
    private JTable jTable1;
    private JTable jTableProcesos;
    private JLabel procesos_en_ejecucion;
    private JTextArea texto_bloqueados;
    private JTextArea texto_listos;
    private JLabel tiempo_cpu;
    private JLabel tiempo_real;
    private JLabel total_bloqueados;
    private JLabel total_listos;
    Prioridad obj=new Prioridad(this);
    int x = 0;

    public JTable getjTable1() {
        return jTable1;
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
    
    public JTable getjTableProcesos() {
        return jTableProcesos;
    }

    public void setjTableProcesos(JTable jTable1) {
        this.jTable1 = jTableProcesos;
    }

    
    public Interfaz() {       
        initComponents();
        jTable1.selectAll();
        jTableProcesos.selectAll();
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);        
    }

   
                         
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        cpu_nuevo1 = new javax.swing.JLabel();
        tiempo_cpu = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        texto_bloqueados = new javax.swing.JTextArea();
        tiempo_real = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        total_listos = new javax.swing.JLabel();
        total_bloqueados = new javax.swing.JLabel();
        procesos_en_ejecucion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProcesos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto_listos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 23, 1365, 740));
        setMinimumSize(new java.awt.Dimension(1365, 740));
        getContentPane().setLayout(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);
        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1370, 10);

        jPanel3.setBackground(new java.awt.Color(255, 102, 51));
        jPanel3.setMaximumSize(new java.awt.Dimension(800, 416));
        jPanel3.setMinimumSize(new java.awt.Dimension(800, 416));
        jPanel3.setLayout(null);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(null);
        jPanel3.add(jPanel13);
        jPanel13.setBounds(0, 700, 1370, 10);
        jPanel3.add(cpu_nuevo1);
        cpu_nuevo1.setBounds(160, 340, 62, 14);
        jPanel3.add(tiempo_cpu);
        tiempo_cpu.setBounds(160, 360, 63, 14);

        jScrollPane7.setToolTipText("");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
            new String [] {
                "P", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "TE", "TR"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setGridColor(new java.awt.Color(0, 0, 102));
        jTable1.setMaximumSize(new java.awt.Dimension(800, 416));
        jTable1.setMinimumSize(new java.awt.Dimension(800, 416));
        jTable1.setPreferredSize(new java.awt.Dimension(2200, 416));
        jScrollPane7.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("P");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("1");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("2");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("3");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("4");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("5");
            jTable1.getColumnModel().getColumn(6).setHeaderValue("6");
            jTable1.getColumnModel().getColumn(7).setHeaderValue("7");
            jTable1.getColumnModel().getColumn(8).setHeaderValue("8");
            jTable1.getColumnModel().getColumn(9).setHeaderValue("9");
            jTable1.getColumnModel().getColumn(10).setHeaderValue("10");
            jTable1.getColumnModel().getColumn(11).setHeaderValue("11");
            jTable1.getColumnModel().getColumn(12).setHeaderValue("12");
            jTable1.getColumnModel().getColumn(13).setHeaderValue("13");
            jTable1.getColumnModel().getColumn(14).setHeaderValue("14");
            jTable1.getColumnModel().getColumn(15).setHeaderValue("15");
            jTable1.getColumnModel().getColumn(16).setHeaderValue("16");
            jTable1.getColumnModel().getColumn(17).setHeaderValue("17");
            jTable1.getColumnModel().getColumn(18).setHeaderValue("18");
            jTable1.getColumnModel().getColumn(19).setHeaderValue("19");
            jTable1.getColumnModel().getColumn(20).setHeaderValue("20");
            jTable1.getColumnModel().getColumn(21).setHeaderValue("21");
            jTable1.getColumnModel().getColumn(22).setHeaderValue("22");
            jTable1.getColumnModel().getColumn(23).setHeaderValue("23");
            jTable1.getColumnModel().getColumn(24).setHeaderValue("24");
            jTable1.getColumnModel().getColumn(25).setHeaderValue("25");
            jTable1.getColumnModel().getColumn(26).setHeaderValue("26");
            jTable1.getColumnModel().getColumn(27).setHeaderValue("27");
            jTable1.getColumnModel().getColumn(28).setHeaderValue("28");
            jTable1.getColumnModel().getColumn(29).setHeaderValue("29");
            jTable1.getColumnModel().getColumn(30).setHeaderValue("30");
            jTable1.getColumnModel().getColumn(31).setHeaderValue("31");
            jTable1.getColumnModel().getColumn(32).setHeaderValue("32");
            jTable1.getColumnModel().getColumn(33).setHeaderValue("33");
            jTable1.getColumnModel().getColumn(34).setHeaderValue("34");
            jTable1.getColumnModel().getColumn(35).setHeaderValue("35");
            jTable1.getColumnModel().getColumn(36).setHeaderValue("36");
            jTable1.getColumnModel().getColumn(37).setHeaderValue("37");
            jTable1.getColumnModel().getColumn(38).setHeaderValue("38");
            jTable1.getColumnModel().getColumn(39).setHeaderValue("39");
            jTable1.getColumnModel().getColumn(40).setHeaderValue("40");
            jTable1.getColumnModel().getColumn(41).setHeaderValue("41");
            jTable1.getColumnModel().getColumn(42).setHeaderValue("42");
            jTable1.getColumnModel().getColumn(43).setHeaderValue("43");
            jTable1.getColumnModel().getColumn(44).setHeaderValue("44");
            jTable1.getColumnModel().getColumn(45).setHeaderValue("45");
            jTable1.getColumnModel().getColumn(46).setHeaderValue("46");
            jTable1.getColumnModel().getColumn(47).setHeaderValue("47");
            jTable1.getColumnModel().getColumn(48).setHeaderValue("48");
            jTable1.getColumnModel().getColumn(49).setHeaderValue("49");
            jTable1.getColumnModel().getColumn(50).setHeaderValue("50");
            jTable1.getColumnModel().getColumn(51).setHeaderValue("51");
            jTable1.getColumnModel().getColumn(52).setHeaderValue("52");
            jTable1.getColumnModel().getColumn(53).setHeaderValue("53");
            jTable1.getColumnModel().getColumn(54).setHeaderValue("54");
            jTable1.getColumnModel().getColumn(55).setHeaderValue("55");
            jTable1.getColumnModel().getColumn(56).setHeaderValue("56");
            jTable1.getColumnModel().getColumn(57).setHeaderValue("57");
            jTable1.getColumnModel().getColumn(58).setHeaderValue("58");
            jTable1.getColumnModel().getColumn(59).setHeaderValue("59");
            jTable1.getColumnModel().getColumn(60).setHeaderValue("60");
            jTable1.getColumnModel().getColumn(61).setHeaderValue("61");
            jTable1.getColumnModel().getColumn(62).setHeaderValue("62");
            jTable1.getColumnModel().getColumn(63).setHeaderValue("63");
            jTable1.getColumnModel().getColumn(64).setHeaderValue("64");
            jTable1.getColumnModel().getColumn(65).setHeaderValue("65");
            jTable1.getColumnModel().getColumn(66).setHeaderValue("66");
            jTable1.getColumnModel().getColumn(67).setHeaderValue("67");
            jTable1.getColumnModel().getColumn(68).setHeaderValue("68");
            jTable1.getColumnModel().getColumn(69).setHeaderValue("69");
            jTable1.getColumnModel().getColumn(70).setHeaderValue("70");
            jTable1.getColumnModel().getColumn(71).setHeaderValue("71");
            jTable1.getColumnModel().getColumn(72).setHeaderValue("72");
            jTable1.getColumnModel().getColumn(73).setHeaderValue("73");
            jTable1.getColumnModel().getColumn(74).setHeaderValue("74");
            jTable1.getColumnModel().getColumn(75).setHeaderValue("75");
            jTable1.getColumnModel().getColumn(76).setHeaderValue("76");
            jTable1.getColumnModel().getColumn(77).setHeaderValue("77");
            jTable1.getColumnModel().getColumn(78).setHeaderValue("78");
            jTable1.getColumnModel().getColumn(79).setHeaderValue("79");
            jTable1.getColumnModel().getColumn(80).setHeaderValue("80");
            jTable1.getColumnModel().getColumn(81).setHeaderValue("81");
            jTable1.getColumnModel().getColumn(82).setHeaderValue("82");
            jTable1.getColumnModel().getColumn(83).setHeaderValue("83");
            jTable1.getColumnModel().getColumn(84).setHeaderValue("84");
            jTable1.getColumnModel().getColumn(85).setHeaderValue("85");
            jTable1.getColumnModel().getColumn(86).setHeaderValue("86");
            jTable1.getColumnModel().getColumn(87).setHeaderValue("87");
            jTable1.getColumnModel().getColumn(88).setHeaderValue("88");
            jTable1.getColumnModel().getColumn(89).setHeaderValue("89");
            jTable1.getColumnModel().getColumn(90).setHeaderValue("90");
            jTable1.getColumnModel().getColumn(91).setHeaderValue("91");
            jTable1.getColumnModel().getColumn(92).setHeaderValue("92");
            jTable1.getColumnModel().getColumn(93).setHeaderValue("93");
            jTable1.getColumnModel().getColumn(94).setHeaderValue("94");
            jTable1.getColumnModel().getColumn(95).setHeaderValue("95");
            jTable1.getColumnModel().getColumn(96).setHeaderValue("96");
            jTable1.getColumnModel().getColumn(97).setHeaderValue("97");
            jTable1.getColumnModel().getColumn(98).setHeaderValue("TE");
            jTable1.getColumnModel().getColumn(99).setHeaderValue("TR");
        }

        jPanel3.add(jScrollPane7);
        jScrollPane7.setBounds(440, 80, 900, 310);

        jButton1.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton1.setText("INICIAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(30, 550, 90, 50);

        jButton2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton2.setText("PAUSAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(140, 550, 100, 50);

        jButton4.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton4.setText("REANUDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);
        jButton4.setBounds(250, 550, 110, 50);

        jButton5.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton5.setText("X");
        jButton5.setActionCommand("X");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);
        jButton5.setBounds(1290, 20, 50, 25);

        jLabel15.setBackground(new java.awt.Color(0, 204, 255));
        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PROCESOS BLOQUEADOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel3.add(jLabel15);
        jLabel15.setBounds(860, 410, 300, 230);

        jScrollPane8.setBackground(new java.awt.Color(0, 204, 255));

        texto_bloqueados.setEditable(false);
        texto_bloqueados.setColumns(20);
        texto_bloqueados.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        texto_bloqueados.setRows(5);
        texto_bloqueados.setToolTipText("");
        jScrollPane8.setViewportView(texto_bloqueados);

        jPanel3.add(jScrollPane8);
        jScrollPane8.setBounds(870, 440, 280, 180);

        tiempo_real.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        tiempo_real.setText("0");
        jPanel3.add(tiempo_real);
        tiempo_real.setBounds(320, 490, 24, 15);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel9.setText("Número de procesos:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(20, 50, 151, 15);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel12.setText("Procesos Bloqueados:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(880, 650, 180, 20);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel4.setText("Atendiendo: ");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 420, 170, 15);

        total_listos.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        total_listos.setText("0");
        jPanel3.add(total_listos);
        total_listos.setBounds(180, 50, 31, 15);

        total_bloqueados.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        total_bloqueados.setText("0");
        jPanel3.add(total_bloqueados);
        total_bloqueados.setBounds(1070, 650, 65, 15);

        procesos_en_ejecucion.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        procesos_en_ejecucion.setText("0");
        jPanel3.add(procesos_en_ejecucion);
        procesos_en_ejecucion.setBounds(200, 420, 41, 15);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(469, 416));

        jTableProcesos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableProcesos.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jTableProcesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}

            },
            new String [] {
                "#Proceso", "Tiempo Ráfaga", "Ráfaga Ejecutada", "Tiempo Espera", "Prioridad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProcesos.setEnabled(false);
        jTableProcesos.setGridColor(new java.awt.Color(0, 0, 102));
        jTableProcesos.setMaximumSize(new java.awt.Dimension(2147483647, 130));
        jTableProcesos.setMinimumSize(new java.awt.Dimension(750, 130));
        jScrollPane3.setViewportView(jTableProcesos);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(10, 80, 430, 310);

        texto_listos.setColumns(20);
        texto_listos.setRows(5);
        jScrollPane1.setViewportView(texto_listos);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(460, 440, 390, 180);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 20, 1370, 700);

        pack();
    }                    

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(x==0){
            obj.start();

        }else{
          
        }
        x = x+1;
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        obj.suspend();

    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        obj.resume();
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
    }                                                       
                  
}
