package com.urise.webapp.model;

import java.util.List;

public class Organization {
    private final List<Period> periods;
    private String homePage;
    private String orgName;



    public Organization(List<Period> periods) {
        this.periods = periods;
    }

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
}
