/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
public class FormatoTabla extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String) {
            table.selectAll();
            String valor = (String) value;
            if (valor.equals("X")) {
                cell.setBackground(new Color(255, 0, 0));
                cell.setForeground(new Color(255, 0, 0)); //color de la letra               
            } else if (valor.equals("E")) {
                cell.setBackground(Color.green);
                cell.setForeground(Color.green);
            } else if (valor.equals("B")) {
                cell.setBackground(new Color(32, 50, 72));
                cell.setForeground(new Color(32, 50, 72));
            } else if (valor.equals("S")) {
                cell.setBackground(Color.orange);
                cell.setForeground(Color.orange);
            } else if (valor.equals("C")) {
                table.selectAll();
                cell.setText("");
            } else if (valor != "X") {
                table.selectAll();
                cell.setForeground(new Color(255, 0, 0));
                //cell.setText(null);                
                //cell.setBackground(Color.blue);
            } else if (valor != "E") {
                table.selectAll();
                cell.setForeground(new Color(255, 0, 0));
                //cell.setText(null);
                //cell.setBackground(Color.blue);
            } else if (valor != "B") {
                table.selectAll();
                cell.setForeground(new Color(255, 0, 0));
                //cell.setText(null);
                //cell.setBackground(Color.blue);
            } else if (valor != "S") {
                table.selectAll();
                cell.setForeground(new Color(255, 0, 0));
                //cell.setText(null);
                //cell.setBackground(Color.blue);
            }              
        }
        return cell;
    }
}
