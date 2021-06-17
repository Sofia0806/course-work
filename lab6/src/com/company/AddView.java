package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class AddView extends JFrame {
    private JComboBox<String> cbPub;
    private DefaultComboBoxModel<String> cbModel;
    private JButton buttonAdd=new JButton("Добавить запись");
    private JLabel labelName=new JLabel("Введите название:");
    private JLabel labelQuantity=new JLabel("Введите количество:");
    private JLabel labelPrice=new JLabel("Введите цену:");
    private JLabel labelAvtor=new JLabel("Введите автора:");
    private JLabel labelProperty_1=new JLabel("Введите первую характеристику:" +
            "(Если тетрадь - количество страниц, учебник - номер класса, ручка - толщина стержня):");
    private JLabel labelProperty_2=new JLabel("Введите вторую характеристику:" +
            "(Если книга - жанр, тетрадь - вид страниц, учебник - предмет, ручка - цвет стержня):");
    private JTextField textName=new JTextField();
    private JTextField textQuantity=new JTextField();
    private JTextField textPrice=new JTextField();
    private JTextField textAvtor=new JTextField("Свойство");
    private JTextField textProperty_1=new JTextField("0");
    private JTextField textProperty_2=new JTextField("Свойство");
    private GridLayout layoutPanel = new GridLayout(6, 2, 5, 12);
    private GridLayout layout = new GridLayout(3, 1, 5, 12);
    JPanel panel=new JPanel();

    public AddView(){
        super("Добавить");
        this.setSize(500,500);
        setResizable(false);
        setVisible(true);
        cbModel = new DefaultComboBoxModel<String>();
        cbModel.addElement("Книга");
        cbModel.addElement("Тетрадь");
        cbModel.addElement("Учебник");
        cbModel.addElement("Ручка");
        cbPub=new JComboBox<String>(cbModel);
        cbPub.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cbPub.getSelectedItem()=="Тетрадь" || cbPub.getSelectedItem()=="Учебник" || cbPub.getSelectedItem()=="Ручка"){
                    textProperty_1.setVisible(true);
                    textProperty_2.setVisible(true);
                    textAvtor.setVisible(false);
                }
                else{
                    textProperty_1.setVisible(false);
                    textProperty_2.setVisible(true);
                    textAvtor.setVisible(true);
                }
            }
        });



        panel.setLayout(layoutPanel);
        setLayout(layout);

        add(cbPub);
        this.add(panel);
        panel.add(labelName);
        panel.add(textName);

        panel.add(labelQuantity);
        panel.add(textQuantity);

        panel.add(labelPrice);
        panel.add(textPrice);

        panel.add(labelAvtor);
        panel.add(textAvtor);

        panel.add(labelProperty_1);
        panel.add(textProperty_1);

        panel.add(labelProperty_2);
        panel.add(textProperty_2);

        textProperty_1.setVisible(false);
        textProperty_2.setVisible(true);
        textAvtor.setVisible(true);

        AddListener addListener = new AddListener();
        buttonAdd.addActionListener(addListener);
        add(buttonAdd);

        this.pack();
        this.setLocationRelativeTo(null);


    }
    private boolean checkInput(){
        try {
            if (textName.getText().trim().length() == 0 || textQuantity.getText().trim().length() == 0 || textPrice.getText().trim().length() == 0 || textAvtor.getText().trim().length() == 0 || textProperty_1.getText().trim().length() == 0 || textProperty_2.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Введите значения в поля");
                return false;
            } else if (Integer.parseInt(textQuantity.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "Введите корректное количество");
                return false;
            } else if (Integer.parseInt(textPrice.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "Введите корректную цену");
                return false;
            } else if (Integer.parseInt(textProperty_1.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "Введите корректную характеристику");
                return false;
            } else return true;
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Введите корректные данные");
            return false;
        }

    }
    public class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int num=0;
            if (checkInput()) {
                try {
                    if (!DBWorker.checkN(textName.getText())) {
                        num = DBWorker.writeDB(textName.getText(), Integer.parseInt(textQuantity.getText()), Integer.parseInt(textPrice.getText()), Integer.parseInt(textProperty_1.getText()), textProperty_2.getText(), textAvtor.getText(), cbPub.getSelectedItem().toString());
                        // MainView.tableModel.addPub(num,cbPub.getSelectedItem().toString(), textName.getText(), Integer.parseInt(textQuantity.getText()), Integer.parseInt(textPrice.getText()),  Integer.parseInt(textProperty_1.getText()), textProperty_2.getText(),textAvtor.getText());
                        MainView.tableModel.connectDB();
                        MainView.tableModel.fireTableDataChanged();
                    }else JOptionPane.showMessageDialog(null, "Такой товар уже есть");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }
}
