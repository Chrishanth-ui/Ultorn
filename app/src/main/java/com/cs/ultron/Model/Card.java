package com.cs.ultron.Model;

public class Card {

    private int image;
    private int id;
    private String title;


        public Card(int id, String title, int image) {
            this.id = id;
            this.title = title;
            this.image = image;
        }

    public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public  int getImage() {
            return image;
        }

    public void add(int notepad) {
    }
}

