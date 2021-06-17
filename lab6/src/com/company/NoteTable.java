package com.company;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;

public class NoteTable extends AbstractTableModel {

    public static  PublicationsList data=new PublicationsList();
    public NoteTable(PublicationsList pub_note){
        try{
            DBWorker.connectionBD();
            DBWorker.newTable();
           // connectDB();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.data=pub_note;
    }
    public static void connectDB(){
        data.clear();
        try {
            DBWorkerNote.readDBNote();
            data = DBWorkerNote.getArr();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getRowCount() { return data.getCount(); }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Id";
            case 1: return "Название";
            case 2: return "Количество";
            case 3: return "Цена";
            case 4: return "Сумма";

        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Publication p = data.Publications.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getName();
            case 2:
                return p.getQuantity();
            case 3:
                return p.getPrice();
            case 4:
                return p.getSum();

        }
        return "default";
    }

    public void addPub_note(int id,String type,String name, int quantity,int price, int property_1,
                            String property_2,String avtor){
        switch (type){
            case "Книга":{
                data.add(new Book(id,name,quantity,price,avtor,property_2));
                break;
            }
            case "Тетрадь":{
                data.add(new Copybook(id,name,quantity,price,property_1,property_2));
                break;
            }
            case "Учебник":{
                data.add(new Textbook(id,name,quantity,price,property_1,property_2));
                break;
            }
            case "Ручка":{
                data.add(new Pen(id,name,quantity,price,property_1,property_2));
                break;
            }
        }
        fireTableDataChanged();

    }
    // public Publication SearchPub(int index){

    // return data.find(index);
    //}
    public Publication SearchPub_note(String name) {
        Publication searchedPub_note = data.searchN(name);
        return searchedPub_note;
    }

    public void DeletePub_note(int index){
        data.remove(index);
    }

    public static void filterDB_note(String type){
        data.Publications.clear();
        try {
            DBWorker.filter(type);
            data = DBWorker.getArr();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
