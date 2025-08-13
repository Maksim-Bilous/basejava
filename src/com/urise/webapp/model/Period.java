package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period {
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String description;

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = Objects.requireNonNull(startDate, " Start date must be not null");
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public Period() {
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
        return getStartDate() + " - " + getEndDate() + " " + getTitle() + "\n" + "    " + getDescription() + "\n";
    }
}
