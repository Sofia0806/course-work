package com.company;

public class Book extends Publication {
    private String avtor;
    private String genre;
    public String getAvtor(){
        return this.avtor;
    }
    public String getGenre(){
        return this.genre;
    }
    public void setAvtor(String avtor){
        this.avtor=avtor;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public Book(int id, String name,int quantity, int price, String avtor, String genre){
        super(id,name,quantity, price);
        this.avtor=avtor;
        this.genre=genre;
    }
    public Book(int id, String name, int quantity, int price, int sum){

        super(id, name, quantity, price, sum);
    }


    @Override
    public String print() {
        String str="";
        str+="Название: "+getName().toString()+"\n";
        str+="Количество: "+String.valueOf(getQuantity())+"\n";
        str+="Цена: "+String.valueOf(getPrice())+"\n";
        str+="Автор: "+getAvtor().toString()+"\n";
        str+="Жанр: "+getGenre().toString()+"\n";
        return str;
    }
    @Override
    public String[] getArray() {
        sb[0] = "Id: " + Integer.toString(getId());
        sb[1] = getName().toString();
        sb[2] = Integer.toString(getQuantity());
        sb[3] = Integer.toString(getPrice());
        sb[4] = getAvtor().toString();
        sb[5] = getGenre().toString();
        return sb;
    }


}
