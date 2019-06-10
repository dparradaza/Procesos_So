package modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Cristian
 */
public class MiRender1 extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        table.setBackground(new Color(32, 50, 72));
        table.setForeground(new Color(235, 196, 56));
        if (value instanceof String) {
            String valor = (String) value;
            table.selectAll();

            cell.setBackground(new Color(0, 255, 128));
            cell.setForeground(Color.black);
        }
        return cell;
    }
}
