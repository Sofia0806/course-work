package com.company;

public class Textbook extends Publication{
    private int num_class;
    private String object;
    public int getNum_class(){
        return this.num_class;
    }
    public String getObject(){
        return this.object;
    }
    public void setNum_class(int num_class){
        this.num_class=num_class;
    }
    public void setObject(String object){
        this.object=object;
    }
    public Textbook(int id,String name,int quantity, int price, int num_class, String object){
        super(id,name,quantity, price);
        this.num_class=num_class;
        this.object=object;
    }

    public Textbook(int id, String name, int quantity, int price, int sum){
        super(id, name, quantity, price, sum);
    }

    @Override
    public String print() {
       String str="";
        str+="Название: "+getName().toString()+"\n";
        str+="Количество: "+String.valueOf(getQuantity())+"\n";
        str+="Цена: "+String.valueOf(getPrice())+"\n";
        str+="Класс: "+String.valueOf(getNum_class())+"\n";
        str+="Предмет: "+getObject().toString()+"\n";
       return str;
    }
    @Override
    public String[] getArray() {
        sb[0] = "Id: " + Integer.toString(getId());
        sb[1] = getName().toString();
        sb[2] = Integer.toString(getQuantity());
        sb[3] = Integer.toString(getPrice());
        sb[4] = Integer.toString(getNum_class());
        sb[5] = getObject().toString();
        return sb;
    }
}
