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



}
