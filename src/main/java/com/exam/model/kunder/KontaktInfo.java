package com.exam.model.kunder;

import com.exam.service.LejeAftale;

public class KontaktInfo {

    private LejeAftale KundensAftale;
    private Kunde Kunden;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Postnr;
    private String By;
    private String Email;
    private int Mobil;
    private int Counter;


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMobilnumber() {
        return Mobilnumber;
    }

    public void setMobilnumber(int mobilnumber) {
        Mobilnumber = mobilnumber;
    }

    private int Mobilnumber;

    @Override
    public String toString() {
        return "KontaktInfo{" +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobilnumber=" + Mobilnumber +
                '}';
    }
}
