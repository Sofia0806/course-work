package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class NoteView extends JFrame {

    private JTable table;
    public static NoteTable tableModel=new NoteTable((new PublicationsList()));
    private JPanel panelT=new JPanel();
    private JPanel panelM=new JPanel();

    private JButton buttonAdd=new JButton("Добавить запись");
    private JButton buttonSearch=new JButton("Удалить запись");


    private BoxLayout layout=new BoxLayout(getContentPane() ,BoxLayout.Y_AXIS);

    public NoteView() {
        super("Накладная");
        this.setSize(600,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        tableModel.connectDB();


        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Note_AddView v=new Note_AddView();
                tableModel.fireTableDataChanged();
            }

        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchView_note v=new SearchView_note();
                tableModel.fireTableDataChanged();
            }

        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }

}
