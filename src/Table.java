import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

public class Table {

    public static JTable showTable(String table) throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTable(table);
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] cells = new String[rsmd.getColumnCount()];

        for (int i = 1 ; i <= rsmd.getColumnCount(); i++) {
            model.addColumn(rsmd.getColumnName(i));
        }
        while (rs.next()) {
            for (int i =1; i <= rsmd.getColumnCount(); i++) {
                cells[i-1] = (rs.getString(rsmd.getColumnName(i)));
            }
            model.addRow(cells);
            Arrays.fill(cells,null);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(model);
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));

        //jTable.getColumnModel().getColumn(0).setMinWidth(0);
        //jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        //jTable.getColumnModel().getColumn(0).setWidth(0);
        return jTable;
    }

    public static void detailsButtonAction(JButton jButton, JTable jTable) {
        for (ActionListener x : jButton.getActionListeners())
            jButton.removeActionListener(x);
        jButton.addActionListener(e-> {
            int row = jTable.getSelectedRow();
            System.out.print("Row "+row+" ");
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            System.out.println("Imie: " + name + ", ID: " + id);
        });
    }


}
