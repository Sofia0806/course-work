package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class MainView extends JFrame {
    private JTable table;
    public static MyTableModel tableModel=new MyTableModel((new PublicationsList()));
    private JPanel panelT=new JPanel();
    private JPanel panelM=new JPanel();

    private JButton buttonAdd=new JButton("Добавить запись");
    private JButton buttonSearch=new JButton("Найти/Удалить запись");
    private JButton buttonNote=new JButton("Накладная");
    private JButton buttonFilter=new JButton("Фильтр");

    private BoxLayout layout=new BoxLayout(getContentPane() ,BoxLayout.Y_AXIS);

    public MainView() {
        super("Товары");
        this.setSize(600,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(layout);

        table=new JTable();
        table.setModel(tableModel);
        JScrollPane jScrollPane=new JScrollPane(table);
        panelT.add(jScrollPane);

        this.add(panelT);
        this.add(panelM);
        panelM.setLayout(new BoxLayout(panelM, BoxLayout.X_AXIS));
        panelM.add(buttonAdd);
        panelM.add(buttonSearch);
        panelM.add(buttonNote);

        panelM.add(buttonFilter);

        tableModel.connectDB();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    DBWorker.closeDB();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });




        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddView v=new AddView();
                tableModel.fireTableDataChanged();
            }

        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchView v=new SearchView();
                tableModel.fireTableDataChanged();
            }

        });

        buttonNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteView v=new NoteView();
                tableModel.fireTableDataChanged();
            }

        });

        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterView v=new FilterView();
                tableModel.fireTableDataChanged();
            }

        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }



}
