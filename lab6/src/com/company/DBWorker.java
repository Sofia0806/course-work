package com.company;

import java.sql.*;

public class DBWorker {
   public static Connection pub_1;
    public static Statement state; //используется для выполнения SQL-запросов
    public static ResultSet result;//представляет результирующий набор данных и обеспечивает приложению построчный доступ к результатам запросов
    public static ResultSet resultN;
    public static PublicationsList pubList=new PublicationsList();

    public static PublicationsList getArr() {
        return pubList;
    }
    public static void connectionBD() throws ClassNotFoundException, SQLException {
        pub_1 = null;
        Class.forName("org.sqlite.JDBC");  //("имя движка") вызывает динамическую загрузку класса, org.sqlite.JDBC принадлежит к jar sqlite-jdbc
        pub_1 = DriverManager.getConnection("jdbc:sqlite:dataNew.s3db"); //("протокол:движок:имя_файла_БД")находит java.sql.Driver соответствующей базы данных и вызывает у него метод connect (метод connect всегда создает базу данных заранее)
        System.out.println("БД подключена!");
    }

    //cоздание таблицы БД
    public static void newTable() throws ClassNotFoundException, SQLException {
        //создание экземпляра класса Statement
        state = pub_1.createStatement();
       state.execute("CREATE TABLE if not exists 'pub' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR, 'quantity' INTEGER, 'price' INTEGER, 'property_1' VARCHAR, 'property_2' VARCHAR, 'avtor' VARCHAR, 'type' VARCHAR); ");
       state.execute("CREATE TABLE if not exists 'pub_note'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR, 'quantity' INTEGER, 'price' INTEGER,'sum' VARCHAR) ");
        System.out.println("Таблица товаров существует");
    }
    // заполнение таблицы 1 БД
    public static int writeDB(String name, int quantity,int price, int property_1, String property_2, String avtor, String type) throws SQLException {

        PreparedStatement prep = pub_1.prepareStatement("INSERT INTO 'pub' ('name','quantity','price','property_1', 'property_2','avtor','type') VALUES (?, ?, ?, ?, ?, ?, ?);");
        prep.setString(1, name);
        prep.setInt(2, quantity);
        prep.setInt(3, price);
        prep.setInt(4, property_1);
        prep.setString(5, property_2);
        prep.setString(6, avtor);
        prep.setString(7, type);


        prep.execute();

        result = state.executeQuery("SELECT * FROM pub ORDER BY id DESC LIMIT 1");
        result = state.executeQuery("SELECT * FROM pub_note ORDER BY id DESC LIMIT 1");
        return result.getInt("id");
    }



    // вывод данных из таблицы
    public static void readDB() throws ClassNotFoundException, SQLException {
        Publication publication;
        result = state.executeQuery("SELECT * FROM pub"); //выборки данных с помощью команды SELECT
        while (result.next()) {
            String type = result.getString("type");
            switch (type) {
                case "Книга": {
                    publication = new Book(result.getInt("id"), result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getString("avtor"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Тетрадь": {
                    publication = new Copybook(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("property_1"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Учебник": {
                    publication = new Textbook(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("property_1"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Ручка": {
                    publication = new Pen(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("property_1"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + type);

            }
            pubList.Publications.add(publication);
        }
        System.out.println("Таблица выгружена");
    }
    public static void delete(int id) throws SQLException {
        PreparedStatement del = pub_1.prepareStatement("DELETE FROM pub WHERE id = ?");
        del.setObject(1, id);
        del.execute();
    }
    public static int select(int id) throws SQLException {
        PreparedStatement prep = DBWorker.pub_1.prepareStatement("SELECT quantity FROM pub WHERE id=?");
        prep.setInt(1, id);
        result = prep.executeQuery();
        return result.getInt("quantity");
    }


    //закрытие БД
    public static void closeDB() throws ClassNotFoundException, SQLException {
        result.close();
        state.close();
        pub_1.close();

        System.out.println("Соединения закрыты");
    }

    public static void filter(String type) throws SQLException {
        Publication publication;
        PreparedStatement fltr = pub_1.prepareStatement("SELECT * FROM pub WHERE type = ?");
        fltr.setObject(1, type);

        result = fltr.executeQuery();

        while (result.next()) {
            type = result.getString("type");
            switch (type) {
                case "Книга": {
                    publication = new Book(result.getInt("id"), result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getString("avtor"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Тетрадь": {
                    publication = new Copybook(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("property_1"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Учебник": {
                    publication = new Textbook(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("property_1"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                case "Ручка": {
                    publication = new Pen(result.getInt("id"),result.getString("name"), result.getInt("quantity"), result.getInt("price"), result.getInt("property_1"), result.getString("property_2")){
                        @Override
                        public String[] getArray() {
                            return new String[0];
                        }
                    };

                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + type);

            }
            pubList.Publications.add(publication);
        }
        System.out.println("Таблица отфильтрована");
    }
    public static boolean checkN(String id){
        try {
            PreparedStatement prepN = pub_1.prepareStatement("SELECT name FROM pub WHERE name=?");
            prepN.setString(1, id);
            result = prepN.executeQuery();
            System.out.print(result.getInt("name"));
            return true;
        }
        catch (SQLException ex){
            return false;
        }
    }

}
