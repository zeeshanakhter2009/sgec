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
public class ChildRegistration {

    private int    birthDetailsId;//int(11) NOT NULL AUTO_INCREMENT,
    private String motherName;//varchar(200) DEFAULT NULL,
    private String motherNationality;//varchar(200) DEFAULT NULL,
    private String DateofBirth;//datetime DEFAULT NULL,
    private String fatherName;//varchar(200) DEFAULT NULL,
    private String fatherNationality;//varchar(200) DEFAULT NULL,
    private String grandFatherName;//varchar(200) DEFAULT NULL,
    private String grandMotherName;//varchar(200) DEFAULT NULL,
    private double weight;//double DEFAULT NULL,
    private double height;//double DEFAULT NULL,
    private String skinColor;//varchar(45) DEFAULT NULL,
    private String eyeColor;//varchar(45) DEFAULT NULL,
    private String country;//varchar(45) DEFAULT NULL,
    private String city;//varchar(45) DEFAULT NULL,
    private String hospital;//varchar(45) DEFAULT NULL,
    private String status;//varchar(45) DEFAULT NULL,
    private String createdDate;//datetime DEFAULT CURRENT_TIMESTAMP,
    private String modifiedDate;//datetime DEFAULT NULL,
    private String createdBy;//varchar(45) DEFAULT NULL,

    public ChildRegistration() {
    }

    public ChildRegistration(int birthDetailsId, String motherName, String motherNationality, String DateofBirth, String fatherName, String fatherNationality,
            String grandFatherName, String grandMotherName, double weight, double height, String skinColor, String eyeColor, String country, String city, String hospital, String status, String createdDate, String modifiedDate, String createdBy) {
        this.birthDetailsId = birthDetailsId;
        this.motherName = motherName;
        this.motherNationality = motherNationality;
        this.DateofBirth = DateofBirth;
        this.fatherName = fatherName;
        this.fatherNationality = fatherNationality;
        this.grandFatherName = grandFatherName;
        this.grandMotherName = grandMotherName;
        this.weight = weight;
        this.height = height;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.country = country;
        this.city = city;
        this.hospital = hospital;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
    }

    public int getBirthDetailsId() {
        return birthDetailsId;
    }

    public void setBirthDetailsId(int birthDetailsId) {
        this.birthDetailsId = birthDetailsId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherNationality() {
        return motherNationality;
    }

    public void setMotherNationality(String motherNationality) {
        this.motherNationality = motherNationality;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(String DateofBirth) {
        this.DateofBirth = DateofBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherNationality() {
        return fatherNationality;
    }

    public void setFatherNationality(String fatherNationality) {
        this.fatherNationality = fatherNationality;
    }

    public String getGrandFatherName() {
        return grandFatherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    public String getGrandMotherName() {
        return grandMotherName;
    }

    public void setGrandMotherName(String grandMotherName) {
        this.grandMotherName = grandMotherName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "ChildRegistration{" + "birthDetailsId=" + birthDetailsId + ", motherName=" + motherName + ", motherNationality=" + motherNationality + ", DateofBirth=" + DateofBirth + ", fatherName=" + fatherName + ", fatherNationality=" + fatherNationality + ", grandFatherName=" + grandFatherName + ", grandMotherName=" + grandMotherName + ", weight=" + weight + ", height=" + height + ", skinColor=" + skinColor + ", eyeColor=" + eyeColor + ", country=" + country + ", city=" + city + ", hospital=" + hospital + ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + '}';
    }

}
