package com.example.hospital.Models;

public class PersonalDetailModel {
    String fatherName,motherName,fatherMobileNumber,YourMobileNummber,motherMobileNumber,aadharCard,panCard,drivingLicence;

    public PersonalDetailModel() {
    }

    public PersonalDetailModel(String fatherName, String motherName, String fatherMobileNumber, String yourMobileNummber, String motherMobileNumber, String aadharCard, String panCard, String drivingLicence) {
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.fatherMobileNumber = fatherMobileNumber;
        YourMobileNummber = yourMobileNummber;
        this.motherMobileNumber = motherMobileNumber;
        this.aadharCard = aadharCard;
        this.panCard = panCard;
        this.drivingLicence = drivingLicence;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherMobileNumber() {
        return fatherMobileNumber;
    }

    public void setFatherMobileNumber(String fatherMobileNumber) {
        this.fatherMobileNumber = fatherMobileNumber;
    }

    public String getYourMobileNummber() {
        return YourMobileNummber;
    }

    public void setYourMobileNummber(String yourMobileNummber) {
        YourMobileNummber = yourMobileNummber;
    }

    public String getMotherMobileNumber() {
        return motherMobileNumber;
    }

    public void setMotherMobileNumber(String motherMobileNumber) {
        this.motherMobileNumber = motherMobileNumber;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }
}
