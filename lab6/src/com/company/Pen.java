package com.company;

public class Pen extends Publication{
    private int kernel;
    private String color;
    public int getKernel(){
        return this.kernel;
    }
    public String getColor(){
        return this.color;
    }
    public void setKernel(int kernel){
        this.kernel=kernel;
    }
    public void setColor(String color){
        this.color=color;
    }
    public Pen(int id,String name,int quantity, int price, int kernel, String color){
        super(id,name,quantity, price);
        this.kernel=kernel;
        this.color=color;
    }

    public Pen(int id, String name, int quantity, int price, int sum){
        super(id, name, quantity, price, sum);
    }

    @Override
    public String print() {
        String str="";
        str+="Название: "+getName().toString()+"\n";
        str+="Количество: "+String.valueOf(getQuantity())+"\n";
        str+="Цена: "+String.valueOf(getPrice())+"\n";
        str+="Стержень: "+String.valueOf(getKernel())+"\n";
        str+="Цвет: "+getColor().toString()+"\n";
        return str;
    }
    @Override
    public String[] getArray() {
        sb[0] = "Id: " + Integer.toString(getId());
        sb[1] = getName().toString();
        sb[2] = Integer.toString(getQuantity());
        sb[3] = Integer.toString(getPrice());
        sb[4] = Integer.toString(getKernel());
        sb[5] = getColor().toString();
        return sb;
    }

}
