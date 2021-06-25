package com.example.vouchercreator.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class VoucherForm {

    private String name;
    private String pakiet;

    @DateTimeFormat (pattern = "yyyy-mm-dd")
    private Date date;
    private String email;



    private String insidePakiet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsidePakiet() {
        return insidePakiet;
    }

    public void setInsidePakiet(String insidePakiet) {
        this.insidePakiet = insidePakiet;
    }

    public String getPakiet() {
        return pakiet;
    }

    public void setPakiet(String pakiet) {
        this.pakiet = pakiet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
