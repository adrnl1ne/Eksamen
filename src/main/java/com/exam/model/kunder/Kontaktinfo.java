package com.exam.model.kunder;

public class Kontaktinfo {

    private int Lejeaftaleid; // dette burde være et objekt for en Lejeaftale som blev lavet med denne kontaktinfo
    private int cprnummer; // Dette burde være et objekt for den kunde, som skrev kontaktinformationen
    private String FirstName;
    private String LastName;
    private String Address;
    // Mangler et felt for et int Postnr
    // Mangler et felt for en String By
    private String Email;
    // Der mangler en int Mobil for det telefon nummer en kunde brugte til at lave en lejeaftale
    // Der mangler en int Counter, som bare er et nummer alle kontaktinfoer har, for at se ud fra en kundes CPR nummer, hvilken kontaktinformation er den nye, hvilket bare er den med det højeste nummer i sin counter

    public int getLejeaftaleid() {
        return Lejeaftaleid;
    }

    public void setLejeaftaleid(int lejeaftaleid) {
        Lejeaftaleid = lejeaftaleid;
    }

    public long getCprnummer() {
        return cprnummer;
    }

    public void setCprnummer(int cprnummer) {
        this.cprnummer = cprnummer;
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
        return "Kontaktinfo{" +
                "Lejeaftaleid=" + Lejeaftaleid +
                ", cprnummer=" + cprnummer +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobilnumber=" + Mobilnumber +
                '}';
    }
}
