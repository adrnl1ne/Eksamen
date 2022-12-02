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
    private int Counter;
    private int MobilNumber;

    public KontaktInfo(Kunde kunde) {
        this.Kunden = kunde;
    }

    public KontaktInfo(LejeAftale Lejeaftale) {
        this.KundensAftale = Lejeaftale;
    }

    // Getters and Setters


    public Kunde getKunden() {
        return Kunden;
    }

    public void setKunden(Kunde kunden) {
        Kunden = kunden;
    }

    public LejeAftale getKundensAftale() {
        return KundensAftale;
    }

    public void setKundensAftale(LejeAftale kundensAftale) {
        KundensAftale = kundensAftale;
    }

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

    public int getMobilNumber() {
        return MobilNumber;
    }

    public void setMobilNumber(int mobilNumber) {
        MobilNumber = mobilNumber;
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
                ", Mobilnumber=" + MobilNumber +
                '}';
    }
}
