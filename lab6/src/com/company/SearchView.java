package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchView extends JFrame {

    private JButton buttonDelete=new JButton("Удалить запись");
    private JButton buttonSearch=new JButton("Найти запись");
    private JTextField textId=new JTextField();
    private JLabel label=new JLabel("Введите id:");
    private GridLayout layout = new GridLayout(5, 1, 5, 12);
    private JTextArea infoText=new JTextArea();
    public SearchView() {
        super("Найти");
        this.setSize(350,550);
        setVisible(true);
        this.setLayout(layout);
        this.add(label);
        this.add(textId);
        this.add(infoText);
        infoText.setVisible(false);
        this.add(buttonSearch);
        this.add(buttonDelete);
        buttonSearch.addActionListener(new SearchListener());
        buttonDelete.addActionListener(new DeleteListener());

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
    public class SearchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(checkInput()) {
                int ind = Integer.parseInt(textId.getText());
                Publication p = MainView.tableModel.SearchPub(ind);

                infoText.setText(p.print());
                infoText.setVisible(true);
            }
        }
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
                    DBWorker.delete(ind);

                    JOptionPane.showMessageDialog(null, "Запись успешно удалёна");
                    MainView.tableModel.connectDB();
                    MainView.tableModel.fireTableDataChanged();

                    textId.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }
}
