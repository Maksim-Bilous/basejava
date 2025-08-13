package com.urise.webapp.model;

public class TextSection extends Section {
    private static final long serialVersionUID =  1L;

    public String text;

    public TextSection(String s) {
        this.text = s;
    }

    public TextSection() {
    }

    public String getText() {
        return text;
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
