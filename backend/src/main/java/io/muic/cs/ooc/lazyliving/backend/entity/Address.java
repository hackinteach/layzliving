package io.muic.cs.ooc.lazyliving.backend.entity;

import javax.persistence.*;

@Entity

@Table(name="address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name="address_id")

    private Long addressId;

    private String streetAddress;
    private String city;
    private String country;
    private int zipCode;



    public Address(){

    }

    public Long getAddressId() {
        return addressId;
    }


    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

}
