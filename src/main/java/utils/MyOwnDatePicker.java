package main.java.utils;

import main.java.utils.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class MyOwnDatePicker {
    private JDatePickerImpl datePicker;
    private String date;

    public MyOwnDatePicker(String date) {
        this.date = date;
        UtilDateModel model = new UtilDateModel();
        String[] x = date.split("-");
        model.setDate(Integer.parseInt(x[0]),Integer.parseInt(x[1]),Integer.parseInt(x[2]));
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setText(date);
    }

    public String getDate() {
        return datePicker.getJFormattedTextField().getText();
    }

    public void setDate() {
        datePicker.getJFormattedTextField().setText(date);
    }

    public void setBackground(Color c) {
        datePicker.setBackground(c);
    }

    public void setBounds(int x, int y, int w, int h) {
        datePicker.setBounds(x,y,w,h);
    }

    public void addTo(JPanel panel) {
        panel.add(datePicker); }
}
