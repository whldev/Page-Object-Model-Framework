package com.planittesting.jupitertoys.model.data;

public class DeliveryDetails {
    private String forename;
    private String surname;
    private String email;
    private String telephone;
    private String address;

    public DeliveryDetails setForename(String forename) {
        this.forename = forename;
        return this;
    }

    public DeliveryDetails setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public DeliveryDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public DeliveryDetails setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public DeliveryDetails setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getForename() { return this.forename; }

    public String getSurname() { return this.surname; }

    public String getEmail() { return this.email; }

    public String getTelephone() { return this.telephone; }

    public String getAddress() { return this.address; }


}
