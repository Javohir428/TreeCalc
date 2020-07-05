package com.example.volgatech;

import java.io.Serializable;

public class Area implements Serializable {
    private int ID;
    private String forestry;
    private String localForestry;

    //квартал
    private int kvartal;
    //выдел, номер пробной площади
    private int videl;

    private int number_pp;
    // площадь
    private float area;
    private String date;

    public Area(String forestry, String localForestry, int kvartal, int videl, int number_pp, float area, String date) {
        this.forestry = forestry;
        this.localForestry = localForestry;
        this.kvartal = kvartal;
        this.videl = videl;
        this.number_pp = number_pp;
        this.area = area;
        this.date = date;
    }

    public Area(int ID, String forestry, String localForestry, int kvartal, int videl, int number_pp, float area, String date) {
        this.ID = ID;
        this.forestry = forestry;
        this.localForestry = localForestry;
        this.kvartal = kvartal;
        this.videl = videl;
        this.number_pp = number_pp;
        this.area = area;
        this.date = date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getForestry() {
        return forestry;
    }

    public void setForestry(String forestry) {
        this.forestry = forestry;
    }

    public String getLocalForestry() {
        return localForestry;
    }

    public void setLocalForestry(String localForestry) {
        this.localForestry = localForestry;
    }

    public int getKvartal() {
        return kvartal;
    }

    public void setKvartal(int kvartal) {
        this.kvartal = kvartal;
    }

    public int getVidel() {
        return videl;
    }

    public void setVidel(int videl) {
        this.videl = videl;
    }

    public int getNumber_pp() {
        return number_pp;
    }

    public void setNumber_pp(int number_pp) {
        this.number_pp = number_pp;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
