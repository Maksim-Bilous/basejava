package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String  title;
    private final  String description;

    public Period(LocalDate startDate , LocalDate endDate, String title, String description){
        this.startDate = Objects.requireNonNull(startDate, " Start date must be not null");
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return getStartDate() + " - " + getEndDate() + " " +
               getTitle() + "\n" +
               "    " + getDescription() + "\n";
    }
}
