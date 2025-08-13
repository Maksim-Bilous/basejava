package com.urise.webapp.model;

import java.util.List;

public class Organization extends Section {
    private static final long serialVersionUID =  1L;
    private  List<Period> periods;
    private  String homePage;
    private  String orgName;


    public Organization(List<Period> periods, String orgName , String homePage) {
        this.periods = periods;
        this.orgName = orgName;
        this.homePage = homePage;
    }

    public Organization() {
    }

    public List<Period> getPeriods(){
        return periods;
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
