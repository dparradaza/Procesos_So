/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Cristian
 */
public class MiRender extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String) {
            String valor = (String) value;
            if (valor.equals("X")) {
                cell.setBackground(Color.MAGENTA);
                cell.setForeground(Color.magenta);
            } else if (valor.equals("X1")) {
                cell.setBackground(Color.YELLOW);
                cell.setForeground(Color.YELLOW);
            }else if(valor.equals("X2")){
                cell.setBackground(Color.RED);
                cell.setForeground(Color.red);                
            } else if (valor.equals("E")) {
                cell.setBackground(Color.green);
                cell.setForeground(Color.green);
            } else if (valor.equals("B")) {
                cell.setBackground(Color.cyan);
                cell.setForeground(Color.cyan);
            } else if (valor.equals("S")) {
                cell.setBackground(Color.orange);
                cell.setForeground(Color.orange);
            } else{
                table.selectAll();
                cell.setBackground(Color.white);
                cell.setForeground(Color.black);
            } 
        }
        return cell;
    }
}
