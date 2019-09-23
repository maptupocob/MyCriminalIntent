package com.martirosov.sergey.mycriminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID id;
    private String name;
    private Date date;
    private boolean isSolved;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public Crime(){
        id= UUID.randomUUID();
        date= new Date();
    }

}
