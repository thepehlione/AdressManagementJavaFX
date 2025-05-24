package com.example.project;

public class Address {
    private String name;
    private String surname;
    private String address;

    // Boş constructor (Jackson için gerekli)
    public Address() {}

    public Address(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    // Getter ve Setter'lar
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + surname + " - " + address;
    }
}
