package me.kacper.user;

public class User {

    private String email;
    private String password;
    private Long expiry;
    private String ownerEmail;
    private int purchaseMonths;

    public User(String email, String password, Long expiry, String ownerEmail, int purchaseMonths) {
        this.email = email;
        this.password = password;
        this.expiry = expiry;
        this.ownerEmail = ownerEmail;
        this.purchaseMonths = purchaseMonths;
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

    public Long getExpiry() {
        return expiry;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public int getPurchaseMonths() {
        return purchaseMonths;
    }

    public void setPurchaseMonths(int purchaseMonths) {
        this.purchaseMonths = purchaseMonths;
    }
}
