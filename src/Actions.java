import javax.swing.*;
import java.awt.event.ActionListener;

public class Actions {

    public static void detailsButtonAction (JButton jButton, JTable jTable){
        for (ActionListener x : jButton.getActionListeners())
            jButton.removeActionListener(x);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            System.out.print("Row " + row + " ");
            Object id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            System.out.println("Imie: " + name + ", ID: " + id);
        });
    }

    public static void detailsClientBA ( JButton jButton, JTable jTable) {
        for (ActionListener x : jButton.getActionListeners())
            jButton.removeActionListener(x);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            Object id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String last = jTable.getValueAt(row, 1).toString();
            String email = jTable.getValueAt(row, 2).toString();
            String password = jTable.getValueAt(row, 3).toString();
            String birthDate = jTable.getValueAt(row, 4).toString();
            //new ClientDetailsFrame();
            new ClientDetailsFrame(name,last,email,password,birthDate);
        });
    }



}
