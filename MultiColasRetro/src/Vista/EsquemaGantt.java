package Vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EsquemaGantt extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String) {
            String valor = (String) value;
            switch (valor) {
                case "X":
                    cell.setBackground(new java.awt.Color(22, 160, 133));
                    cell.setForeground(new java.awt.Color(22, 160, 133));
                    break;
                case "X1":
                    cell.setBackground(new java.awt.Color(175, 122, 197));
                    cell.setForeground(new java.awt.Color(175, 122, 197));
                    break;
                case "X2":
                    cell.setBackground(new java.awt.Color(240, 178, 122));
                    cell.setForeground(new java.awt.Color(240, 178, 122));
                    break;
                case "E":
                    cell.setBackground(new java.awt.Color(244, 208, 63));
                    cell.setForeground(new java.awt.Color(244, 208, 63));
                    break;
                case "B":
                    cell.setBackground(new java.awt.Color(236, 112, 99));
                    cell.setForeground(new java.awt.Color(236, 112, 99));
                    break;
                case "S":
                    cell.setBackground(new java.awt.Color(244, 208, 63));
                    cell.setForeground(new java.awt.Color(244, 208, 63));
                    break;
                default:
                    table.selectAll();
                    break;
            }
        }
        return cell;
    }
}
