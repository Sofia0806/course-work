package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchView_note extends JFrame {

    private JButton buttonDelete_note=new JButton("Удалить запись");
    //private JButton buttonSearch_note=new JButton("Найти запись");
    private JTextField textId=new JTextField();
    private JLabel label=new JLabel("Введите id:");
    private GridLayout layout = new GridLayout(4, 1, 5, 12);
    private JTextArea infoText=new JTextArea();
    public SearchView_note() {
        super("Найти");
        this.setSize(350,550);
        setVisible(true);
        this.setLayout(layout);
        this.add(label);
        this.add(textId);
        this.add(infoText);
        infoText.setVisible(false);

        this.add(buttonDelete_note);

        buttonDelete_note.addActionListener(new DeleteListener());

        this.setLocationRelativeTo(null);

    }
    private boolean checkInput(){
        try {
            if(Integer.parseInt(textId.getText())<0){
                JOptionPane.showMessageDialog(null, "Такого ID нет");
                return false;
            }
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Такого ID нет");
            return false;
        }
        return true;
    }

    public class DeleteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //  if(checkInput()) {
            //    int ind = Integer.parseInt(textId.getText());
            // MainView.tableModel.DeletePub(ind);
            //MainView.tableModel.fireTableDataChanged();
            //JOptionPane.showMessageDialog(null, "Запись удалена");
            //}
            if(checkInput()) {
                try {
                    int ind = Integer.parseInt(textId.getText());
                    DBWorkerNote.delete_note(ind);

                    JOptionPane.showMessageDialog(null, "Запись успешно удалёна");
                    NoteView.tableModel.connectDB();
                    NoteView.tableModel.fireTableDataChanged();

                    textId.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }

}
