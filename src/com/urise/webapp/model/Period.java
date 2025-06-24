package com.urise.webapp.model;

import java.time.LocalDate;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
    private String  title;
    private String description;

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

}
