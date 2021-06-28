package com.example.nationalvaccinationorganisation.api;

public class AreaInformation {
    private String area;
    private int areaid;
    private int dailydose1;
    private int dailydose2 ;
    private  int daydiff ;
    private  int daytotal ;
    private  String  referencedate ;
    private  int   totaldistinctpersons;
    private  int   totaldose1;
    private  int   totaldose2;
    private  int   totalvaccinations;

    public AreaInformation(){

    }

    public AreaInformation(String area, int areaid, int dailydose1, int dailydose2, int daydiff, int daytotal, String referencedate, int totaldistinctpersons, int totaldose1, int totaldose2, int totalvaccinations) {
        this.area = area;
        this.areaid = areaid;
        this.dailydose1 = dailydose1;
        this.dailydose2 = dailydose2;
        this.daydiff = daydiff;
        this.daytotal = daytotal;
        this.referencedate = referencedate;
        this.totaldistinctpersons = totaldistinctpersons;
        this.totaldose1 = totaldose1;
        this.totaldose2 = totaldose2;
        this.totalvaccinations = totalvaccinations;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
        this.areaid = areaid;
    }

    public int getDailydose1() {
        return dailydose1;
    }

    public void setDailydose1(int dailydose1) {
        this.dailydose1 = dailydose1;
    }

    public int getDailydose2() {
        return dailydose2;
    }

    public void setDailydose2(int dailydose2) {
        this.dailydose2 = dailydose2;
    }

    public int getDaydiff() {
        return daydiff;
    }

    public void setDaydiff(int daydiff) {
        this.daydiff = daydiff;
    }

    public int getDaytotal() {
        return daytotal;
    }

    public void setDaytotal(int daytotal) {
        this.daytotal = daytotal;
    }

    public String getReferencedate() {
        return referencedate;
    }

    public void setReferencedate(String referencedate) {
        this.referencedate = referencedate;
    }

    public int getTotaldistinctpersons() {
        return totaldistinctpersons;
    }

    public void setTotaldistinctpersons(int totaldistinctpersons) {
        this.totaldistinctpersons = totaldistinctpersons;
    }

    public int getTotaldose1() {
        return totaldose1;
    }

    public void setTotaldose1(int totaldose1) {
        this.totaldose1 = totaldose1;
    }

    public int getTotaldose2() {
        return totaldose2;
    }

    public void setTotaldose2(int totaldose2) {
        this.totaldose2 = totaldose2;
    }

    public int getTotalvaccinations() {
        return totalvaccinations;
    }

    public void setTotalvaccinations(int totalvaccinations) {
        this.totalvaccinations = totalvaccinations;
    }
}
