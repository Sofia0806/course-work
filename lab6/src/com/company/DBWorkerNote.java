package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBWorkerNote {
    public static ResultSet result;
    public static PublicationsList pubList=new PublicationsList();
    public static PublicationsList getArr() {
        return pubList;
    }
    // заполнение таблицы 2 БД
    public static void writeDBNote(int id, int quantity) throws SQLException {

        PreparedStatement prep = DBWorker.pub_1.prepareStatement("SELECT name, quantity, price FROM pub WHERE id=?");
        prep.setInt(1, id);
        result = prep.executeQuery();
        PreparedStatement prep_1 = DBWorker.pub_1.prepareStatement("INSERT INTO 'pub_note' ('name','quantity','price','sum') VALUES (?, ?, ?, ?);");
        prep_1.setString(1, result.getString("name") );
        prep_1.setInt(2, quantity);
        prep_1.setInt(3, result.getInt("price"));
        int sum=quantity*result.getInt("price");
        prep_1.setInt(4, sum);

        prep_1.execute();

        int res=result.getInt("quantity")-quantity;
        PreparedStatement prep1 = DBWorker.pub_1.prepareStatement("Update pub Set quantity=? WHERE id=?;");
        prep1.setInt(1, res);
        prep1.setInt(2, id);
        prep1.execute(); //выполнение
    }
    public static void readDBNote() throws ClassNotFoundException, SQLException {
        Publication publication;
        result = DBWorker.state.executeQuery("SELECT * FROM pub_note"); //выборки данных с помощью команды SELECT
        while (result.next()) {
            publication = new Publication(result.getInt("id"), result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("sum")) {
                @Override
                public String print() {
                    return null;
                }

                @Override
                public String[] getArray() {
                    return new String[0];
                }
            };
            /*String type = result.getString("type");
            switch (type) {
                case "Книга": {
                    publication = new Book(result.getInt("id"), result.getString("name"), result.getInt("quantity"), result.getInt("price"),result.getInt("sum")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Тетрадь": {
                    publication = new Copybook(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"),result.getInt("sum") ){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Учебник": {
                    publication = new Textbook(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"),result.getInt("sum") ){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Ручка": {
                    publication = new Pen(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("sum") ){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + type);*/


            pubList.Publications.add(publication);
            }
        System.out.println("Таблица выгружена");

        }

    public static void delete_note(int id) throws SQLException {
        PreparedStatement del_1 = DBWorker.pub_1.prepareStatement("DELETE FROM pub_note WHERE id = ?");
        del_1.setObject(1, id);
        del_1.execute();
    }

}
