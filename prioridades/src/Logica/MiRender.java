/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Chayder
 */
public class MiRender extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String) {
            String valor = (String) value;
            if (valor.equals("X")) {                
                cell.setBackground(Color.green);
                cell.setForeground(Color.green);
            } else if (valor.equals("E")) {                
                cell.setBackground(new Color(0, 104, 139));
                cell.setForeground(new Color(0, 104, 139));
            } else if (valor.equals("B")) {                
                cell.setBackground(Color.red);
                cell.setForeground(Color.red);
            }else{
                table.selectAll();
                cell.setBackground(Color.white);
                cell.setForeground(Color.black);
            } 
        }
        return cell;
    }
}
