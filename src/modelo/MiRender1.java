package modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiRender1 extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //table.setBackground(new Color(32, 50, 72));
        //table.setForeground(Color.black);
        if (value instanceof String) {
            String valor = (String) value;
            table.selectAll();
            cell.setBackground(new Color(0, 255, 128));
            cell.setForeground(Color.black);
        }
        return cell;
    }
}
