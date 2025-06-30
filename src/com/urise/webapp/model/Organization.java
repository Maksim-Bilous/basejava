package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;

public class Organization extends Section {
    private final List<LocalDate> periods;
    private final String homePage;
    private final String orgName;


    public Organization(List<LocalDate> periods, String orgName , String homePage) {
        this.periods = periods;
        this.orgName = orgName;
        this.homePage = homePage;
    }


    public String getHomePage() {
        return homePage;
    }


    public String getOrgName() {
        return orgName;
    }

    @Override
    public String toString() {
        return  "HomePage: " + homePage + "\n" +
                "Organization Name: " + orgName + "\n" +
                "Period: " + periods.getFirst()  + " - "+ periods.getLast();
    }
}
