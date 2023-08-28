package me.kacper.user;

import java.util.Date;

public class User {

    private String email;
    private String password;
    private Date expiry;
    private String ownerEmail;

    public User(String email, String password, Date expiry, String ownerEmail) {
        this.email = email;
        this.password = password;
        this.expiry = expiry;
        this.ownerEmail = ownerEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpiry() {
        return expiry;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
