package com.guud.company.library.core.payload;

import java.util.ArrayList;

public class EntityFilterResponse {

    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private ArrayList<Object> aaData;

    public int compareTo(EntityFilterResponse o) {
        return 0;
    }

    public void init() {
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public ArrayList<Object> getAaData() {
        return aaData;
    }

    public void setAaData(ArrayList<Object> aaData) {
        this.aaData = aaData;
    }
}
