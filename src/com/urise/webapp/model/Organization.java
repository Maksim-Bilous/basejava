package com.urise.webapp.model;

import java.util.List;

public class Organization extends Section {
    private final List<Period> periods;
    private final String homePage;
    private final String orgName;


    public Organization(List<Period> periods, String orgName , String homePage) {
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
        return  "Ссылка: " + getHomePage() + "\n" +
                "Название: " + getOrgName() + "\n" +
                String.join("\n", periods.stream().map(Period :: toString).toList());
    }
}
