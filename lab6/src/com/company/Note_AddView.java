package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static com.sun.glass.ui.Cursor.setVisible;

public class Note_AddView extends JFrame {

    private JButton buttonAdd_note = new JButton("Добавить");
    private JTextField textId = new JTextField();
    private JTextField textQ = new JTextField();
    private JLabel label = new JLabel("Введите id:");
    private JLabel labelQ = new JLabel("Введите количество:");
    private GridLayout layout = new GridLayout(5, 1, 5, 12);

    public Note_AddView() {
        super("Внести товар в накладную");
        this.setSize(350, 550);
        setVisible(true);
        this.setLayout(layout);
        this.add(label);
        this.add(textId);
        this.add(labelQ);
        this.add(textQ);
        this.add(buttonAdd_note);
        buttonAdd_note.addActionListener(new AddListener());
    }
    private boolean checkInput_note(){
        try {
            if(Integer.parseInt(textId.getText())<0||Integer.parseInt(textQ.getText())<0){
                JOptionPane.showMessageDialog(null, "ID и количество должны быть положительными числамиПоля не заполнены или заполнены неверно");
                return false;
            }
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Поля не заполнены или заполнены неверно");
            return false;
        }
        return true;
    }
    public class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(checkInput_note()) {
                int ind = Integer.parseInt(textId.getText());
                try{
                        System.out.print(DBWorker.select(Integer.parseInt(textId.getText())));
                        if (DBWorker.select(Integer.parseInt(textId.getText())) > Integer.parseInt(textQ.getText())) {
                            DBWorkerNote.writeDBNote(Integer.parseInt(textId.getText()), Integer.parseInt(textQ.getText()));
                            NoteView.tableModel.connectDB();
                            MainView.tableModel.connectDB();
                        } else {
                            JOptionPane.showMessageDialog(null, "Введите другое количество");
                        }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                NoteView.tableModel.fireTableDataChanged();
                MainView.tableModel.fireTableDataChanged();
            }
        }
    }
}
