package com.example.nationalvaccinationorganisation.forFirebase;


public class AppointmentInfo {
    private String name;
    private String surname;
    private String AMKA;
    private String phoneNum;
    private String email;
    private String appointment_id;


    public AppointmentInfo(){

    }

    //getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAMKA() {
        return AMKA;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getAppointment_id() {
        return appointment_id;
    }


    //setters


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAMKA(String AMKA) {
        this.AMKA = AMKA;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

}
