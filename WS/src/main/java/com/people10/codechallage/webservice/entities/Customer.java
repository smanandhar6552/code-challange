package com.people10.codechallage.webservice.entities;


import javax.persistence.*;

@Entity
@Table (name="Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="email")
    private String email;
    @Column (name="firstName")
    private String firstName;
    @Column (name="lastName")
    private String lastName;
    @Column (name="IP")
    private String ip;
    @Column (name="latitude")
    private String latitude;
    @Column (name="longitude")
    private String longitute;
    @Column (name="created_at")
    private String created_at;
    @Column (name="updated_at")
    private String updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_by(String created_by) {
        this.created_at = created_by;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }




}
