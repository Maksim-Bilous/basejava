package com.urise.webapp.model;

public class TextSection extends Section {
    public  String text;

    public TextSection(String s) {
        this.text = s;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return text;  // Теперь будет выводиться текст секции, а не хэш
    }
}
