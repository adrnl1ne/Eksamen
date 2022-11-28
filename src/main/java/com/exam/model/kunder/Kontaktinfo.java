package com.exam.model.kunder;

public class Kontaktinfo {

    private int Lejeaftaleid;
    private int cprnummer;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Email;

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
