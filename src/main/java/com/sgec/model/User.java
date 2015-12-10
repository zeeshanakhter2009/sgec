/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgec.model;

/**
 *
 * @author ZA
 */
public class User {

    private String pkUserId;
    private String userName;
    private String password;
    private String userType;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;

    public User() {
    }

    public User(String pkUserId, String userName, String password, String userType, String firstName, String lastName, String email, String contact) {
        this.pkUserId = pkUserId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
    }

    public String getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" + "pkUserId=" + pkUserId + ", userName=" + userName + ", password=" + password + ", userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", contact=" + contact + '}';
    }

}
