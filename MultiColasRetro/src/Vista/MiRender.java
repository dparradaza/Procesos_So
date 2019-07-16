package Vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String) {
            String valor = (String) value;
            switch (valor) {
                case "X":
                    cell.setBackground(Color.MAGENTA);
                    cell.setForeground(Color.magenta);
                    break;
                case "X1":
                    cell.setBackground(Color.YELLOW);
                    cell.setForeground(Color.YELLOW);
                    break;
                case "X2":
                    cell.setBackground(Color.RED);
                    cell.setForeground(Color.red);
                    break;
                case "E":
                    cell.setBackground(Color.green);
                    cell.setForeground(Color.green);
                    break;
                case "B":
                    cell.setBackground(Color.cyan);
                    cell.setForeground(Color.cyan);
                    break;
                case "S":
                    cell.setBackground(Color.orange);
                    cell.setForeground(Color.orange);
                    break;
                default:
                    table.selectAll();
                    cell.setBackground(Color.white);
                    cell.setForeground(Color.black);
                    break;
            }
        }
        return cell;
    }
}
