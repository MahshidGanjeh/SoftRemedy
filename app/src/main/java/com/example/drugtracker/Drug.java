package com.example.drugtracker;

/**
 * Created by Hp on 3/15/2018.
 */

public class Drug {

    private String name;
    private String commercialName;

    public Drug(){

    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommercialName() {

        return commercialName;
    }

    public String getName() {

        return name;
    }
}
