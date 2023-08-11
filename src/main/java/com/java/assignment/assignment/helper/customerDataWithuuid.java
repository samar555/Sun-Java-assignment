package com.java.assignment.assignment.helper;

public class customerDataWithuuid {
    private String uuid;
    private String first_name;
    private String last_name;
    private String street;
    private String address;
    private String city;
    private String email;
    private String phone;
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "customerDataWithuuid [uuid=" + uuid + ", first_name=" + first_name + ", last_name=" + last_name
                + ", street=" + street + ", address=" + address + ", city=" + city + ", email=" + email + ", phone="
                + phone + "]";
    }

    
}
