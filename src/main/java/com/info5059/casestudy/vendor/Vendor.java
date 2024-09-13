package com.info5059.casestudy.vendor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String type;
    private String email;


    public Long getId() {
        return Id;
    }
    public void setId(long id) {
        this.Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.Id, this.name, this.address, this.city, this.province, this.postalCode, this.phone, this.type, this.email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vendor other = (Vendor) obj;
        return  Objects.equals(Id, other.Id) && 
                Objects.equals(name, other.name) &&
                Objects.equals(address, other.address) &&
                Objects.equals(city, other.city) &&
                Objects.equals(province, other.province) &&
                Objects.equals(postalCode, other.postalCode) &&
                Objects.equals(phone, other.phone) &&
                Objects.equals(type, other.type) &&
                Objects.equals(email, other.email);
    }
    @Override
    public String toString() {
        return "Vendor [Id=" + Id + ", name=" + name + ", address=" + address + ", city=" + city + ", province="
                + province + ", postalCode=" + postalCode + ", phone=" + phone + ", type=" + type + ", email=" + email
                + "]";
    }

    
    

    
}
