package com.company;


import java.util.ArrayList;

public class PublicationsList {
    public ArrayList<Publication> Publications = new ArrayList<Publication>();

    public int getCount(){
        return this.Publications.size();
    }
public int getIndex(Publication p){
        return Publications.indexOf(p);
}
    public Publication getPublication(int index){
        return Publications.get(index);
    }

    public void add(Publication publication) {
        Publications.add(publication);
    }

    public void clear() {
        this.Publications.clear();
    }

    public Publication find(int Index){

        return this.Publications.get(Index);
    }
    public void remove(int Index){

        Publications.remove(Index);
    }
    //Метод поиска
    public Publication search(int number) {
        for (Publication pub : Publications) {
            if (pub.getId() == number) {
                return pub;
            }
        }
        return null;
    }

    public Publication searchN(String name) {
        for (Publication pub : Publications) {
            if (pub.getName() == name) {
                return pub;
            }
        }
        return null;
    }




    public String StringPub(Publication pub){
         return pub.print();
     }
}
