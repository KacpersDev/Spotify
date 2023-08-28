package me.kacper.family;

import java.util.Date;

public class Family {

    private String email;
    private String password;
    private Date expiry;
    private String inviteLink;
    private String address;

    public Family(String email, String password, Date expiry, String inviteLink, String address){
        this.email = email;
        this.password = password;
        this.expiry = expiry;
        this.inviteLink = inviteLink;
        this.address = address;
    }

    public Date getExpiry() {
        return expiry;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }
}
