package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FilterView extends JFrame {
    private JComboBox<String> cbPub;
    private DefaultComboBoxModel<String> cbModel;
    private JLabel label=new JLabel("Тип товара:");
    private JButton buttonClear=new JButton("Сбросить фильтр");
    private JButton buttonFilter=new JButton("Применить фильтр");

    private GridLayout layout = new GridLayout(4, 1, 5, 12);

    public FilterView() {
        super("Фильтровать");
        this.setSize(300,400);
        setVisible(true);
        this.setLayout(layout);

        cbModel = new DefaultComboBoxModel<String>();
        cbModel.addElement("Книга");
        cbModel.addElement("Тетрадь");
        cbModel.addElement("Учебник");
        cbModel.addElement("Ручка");
        cbPub=new JComboBox<String>(cbModel);

        this.add(label);
        this.add(cbPub);
        this.add(buttonClear);
        this.add(buttonFilter);
        buttonFilter.addActionListener(new FilterView.FilterListener());
        buttonClear.addActionListener(new FilterView.ClearListener());

        this.setLocationRelativeTo(null);

    }

    public class FilterListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String type = cbPub.getSelectedItem().toString();
            MyTableModel.filterDB(type);
            MainView.tableModel.fireTableDataChanged();

        }
    }
    public class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MyTableModel.connectDB();
            MainView.tableModel.connectDB();
            MainView.tableModel.fireTableDataChanged();
        }
    }


}
