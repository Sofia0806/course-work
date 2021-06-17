package com.company;

public abstract class Publication {
    private int id;
    private String name;
    private int quantity;
    private int price;
    private int sum;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
    public int getQuantity() { return this.quantity; }
    public int getPrice() { return this.price; }

    public int getSum() {
        return sum;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(int price) { this.price = price; }

    public Publication(int id, String name, int quantity, int price) {
        this.id=id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Publication(int id, String name, int quantity, int price,int sum) {
        this.id=id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.sum=sum;
    }
    public abstract String print();
    public String[] sb = new String[5];
    //Абстрактный метод вывода
    public abstract String[] getArray();

    //Абстрактный метод вывода
    public String getString() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            str += sb[i] + "\n";
        }
        return str;
    };


}
