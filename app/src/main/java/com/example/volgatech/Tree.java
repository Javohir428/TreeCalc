package com.example.volgatech;

import java.io.Serializable;

public final class Tree implements Serializable {
    // Это класс, который содержит в себе параметры дерева
    //лестничество

    private int area_id;
    private String poroda;
    // Диаметр
    private int diametr;
    // Деловые
    private int del_kol;
    //Полуделовые
    private int pol_del_kol;
    // дрова
    private int drova_kol;
    //порода, разряд высот, степень толщины, обьём
    private int rh;
    private int stupen;

    public Tree(String poroda, int diametr, int del_kol, int pol_del_kol, int drova_kol, int rh, int stupen) {
        this.poroda = poroda;
        this.diametr = diametr;
        this.del_kol = del_kol;
        this.pol_del_kol = pol_del_kol;
        this.drova_kol = drova_kol;
        this.rh = rh;
        this.stupen = stupen;
    }


    public Tree(int area_id, String poroda, int diametr, int del_kol, int pol_del_kol, int drova_kol, int rh, int stupen) {
        this.area_id = area_id;
        this.poroda = poroda;
        this.diametr = diametr;
        this.del_kol = del_kol;
        this.pol_del_kol = pol_del_kol;
        this.drova_kol = drova_kol;
        this.rh = rh;
        this.stupen = stupen;
    }

    public String getPoroda() {
        return poroda;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public void setPoroda(String poroda) {
        this.poroda = poroda;
    }

    public int getDiametr() {
        return diametr;
    }

    public void setDiametr(int diametr) {
        this.diametr = diametr;
    }

    public int getDel_kol() {
        return del_kol;
    }

    public void setDel_kol(int del_kol) {
        this.del_kol = del_kol;
    }

    public int getPol_del_kol() {
        return pol_del_kol;
    }

    public void setPol_del_kol(int pol_del_kol) {
        this.pol_del_kol = pol_del_kol;
    }

    public int getDrova_kol() {
        return drova_kol;
    }

    public void setDrova_kol(int drova_kol) {
        this.drova_kol = drova_kol;
    }

    public int getRh() {
        return rh;
    }

    public void setRh(int rh) {
        this.rh = rh;
    }

    public int getStupen() {
        return stupen;
    }

    public void setStupen(int stupen) {
        this.stupen = stupen;
    }
}
