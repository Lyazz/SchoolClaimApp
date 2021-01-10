package com.miniprojet.reclamation;

public class History {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String text;
    History(String text){
        this.text=text;
    }
}
