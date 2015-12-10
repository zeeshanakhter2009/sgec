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
public class DeathRegistration {

    private int deathDetailsId;
    private int citizenId;
    private String country;//VARCHAR(200) NULL,
    private String city;//VARCHAR(200) NULL,
    private String placeOfDeath;//VARCHAR(200) NULL,
    private String address;//VARCHAR(200) NULL,
    private String hospitalName;//VARCHAR(200) NULL,
    private String other;//VARCHAR(200) NULL,
    private String DateOfDeath;//DATETIME NULL,
    private String ReasonOfDeath;//VARCHAR(200) NULL, -- Natural/ Disease/ Accident
    private String diseaseName;//VARCHAR(200) NULL, -- Natural/ Disease/ Accident
    private String accidentDetails;//VARCHAR(200) NULL, -- Natural/ Disease/ Accident
    private String status;//VARCHAR(45) NULL,
    private String createdDate;//DATETIME NULL DEFAULT NOW(),
    private String modifiedDate;//DATETIME NULL,
    private String createdBy;//VARCHAR(45) NULL,

    public DeathRegistration() {
    }

    public DeathRegistration(int deathDetailsId, int citizenId, String country, String city, String placeOfDeath, String address, String hospitalName, String other, String DateOfDeath, String ReasonOfDeath, String diseaseName, String accidentDetails, String status, String createdDate, String modifiedDate, String createdBy) {
        this.deathDetailsId = deathDetailsId;
        this.citizenId = citizenId;
        this.country = country;
        this.city = city;
        this.placeOfDeath = placeOfDeath;
        this.address = address;
        this.hospitalName = hospitalName;
        this.other = other;
        this.DateOfDeath = DateOfDeath;
        this.ReasonOfDeath = ReasonOfDeath;
        this.diseaseName = diseaseName;
        this.accidentDetails = accidentDetails;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    public int getDeathDetailsId() {
        return deathDetailsId;
    }

    public void setDeathDetailsId(int deathDetailsId) {
        this.deathDetailsId = deathDetailsId;
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

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getDateOfDeath() {
        return DateOfDeath;
    }

    public void setDateOfDeath(String DateOfDeath) {
        this.DateOfDeath = DateOfDeath;
    }

    public String getReasonOfDeath() {
        return ReasonOfDeath;
    }

    public void setReasonOfDeath(String ReasonOfDeath) {
        this.ReasonOfDeath = ReasonOfDeath;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getAccidentDetails() {
        return accidentDetails;
    }

    public void setAccidentDetails(String accidentDetails) {
        this.accidentDetails = accidentDetails;
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
        return "DeathRegistration{" + "deathDetailsId=" + deathDetailsId + ", citizenId=" + citizenId + ", country=" + country + ", city=" + city + ", placeOfDeath=" + placeOfDeath + ", address=" + address + ", hospitalName=" + hospitalName + ", other=" + other + ", DateOfDeath=" + DateOfDeath + ", ReasonOfDeath=" + ReasonOfDeath + ", diseaseName=" + diseaseName + ", accidentDetails=" + accidentDetails + ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + '}';
    }

}
