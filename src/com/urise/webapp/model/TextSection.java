package com.urise.webapp.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public String toString() {
        return text;  // Теперь будет выводиться текст секции, а не хэш
    }
}
