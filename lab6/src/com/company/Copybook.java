package com.company;

public class Copybook extends Publication{
    private int thickness;
    private String page_view;
    public int getThickness() { return thickness; }
    public String getPage_view() { return page_view; }
    public void setThickness(int thickness){
        this.thickness=thickness;
    }
    public void setPage_view(String page_view){
        this.page_view=page_view;
    }
    public Copybook(int id, String name, int quantity, int price, int thickness, String page_view){
        super(id,name, quantity, price);
        this.thickness=thickness;
        this.page_view=page_view;
    }

    public Copybook(int id, String name, int quantity, int price, int sum){
        super(id, name, quantity, price, sum);
    }

    @Override
    public String print() {
        String str="";
        str+="Название: "+getName().toString()+"\n";
        str+="Количество: "+String.valueOf(getQuantity())+"\n";
        str+="Цена: "+String.valueOf(getPrice())+"\n";
        str+="Толщина: "+String.valueOf(getThickness())+"\n";
        str+="Вид: "+getPage_view().toString()+"\n";
        return str;
    }
    @Override
    public String[] getArray() {
        sb[0] = "Id: " + Integer.toString(getId());
        sb[1] = getName().toString();
        sb[2] = Integer.toString(getQuantity());
        sb[3] = Integer.toString(getPrice());
        sb[4] = Integer.toString(getThickness());
        sb[5] = getPage_view().toString();
        return sb;
    }
}
