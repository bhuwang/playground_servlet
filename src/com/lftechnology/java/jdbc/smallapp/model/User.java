package com.lftechnology.java.jdbc.smallapp.model;

/**
 * POJO for {@link User} object
 * 
 * 
 * @author Bhuwan Guatam <bhuwangautam@lftechnology.com>
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean isTermintaed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTermintaed() {
        return isTermintaed;
    }

    public void setTermintaed(boolean isTermintaed) {
        this.isTermintaed = isTermintaed;
    }

}
