package com.abhinav.FullStackProject02.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "details")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private long contactNo;

    public Contact()
    {

    }

    public Contact(String username, String email, long contactNo) {
        this.username = username;
        this.email = email;
        this.contactNo = contactNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }
}
