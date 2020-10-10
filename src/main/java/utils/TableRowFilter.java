package main.java.utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class TableRowFilter {
    public static JTextField create(JTextField jTextField, JTable jTable) {
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update(e);
            }

            private void update(DocumentEvent e) {
                String expression = jTextField.getText();
                if (expression.trim().length() == 0) {
                    ((TableRowSorter) jTable.getRowSorter()).setRowFilter(null);
                } else {
                    ((TableRowSorter) jTable.getRowSorter()).setRowFilter(RowFilter.regexFilter("(?i)" + expression));
                }
            }
        });
        return jTextField;
    }
}
