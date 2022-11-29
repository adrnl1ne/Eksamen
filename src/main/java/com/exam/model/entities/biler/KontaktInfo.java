package com.exam.model.entities.biler;

public class KontaktInfo {

    private LejeAftale KundensAftale;
    private Kunde Kunden;
    private String FirstName;
    private String LastName;
    private String Address;
    private int Postnr;
    private String City;
    private String Email;
    private int Mobil;
    private int Counter;

    private int Mobilnumber;

    public KontaktInfo(Kunde kunde) {
        this.Kunden = kunde;
    }

    // Getters and Setters

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

    public int getPostnr() {
        return Postnr;
    }

    public void setPostnr(int postnr) {
        this.Postnr = postnr;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
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


    public int getCounter() {
        return Counter;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }

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
