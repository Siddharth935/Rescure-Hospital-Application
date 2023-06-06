package com.example.hospital.Models;

public class ProfileModel {
    String Name,MobileNumber,MobileNumberTwo,emailId,Address,pincode,Image;

    public ProfileModel() {
    }

    public ProfileModel(String name, String mobileNumber, String mobileNumberTwo, String emailId, String address, String pincode, String image) {
        Name = name;
        MobileNumber = mobileNumber;
        MobileNumberTwo = mobileNumberTwo;
        this.emailId = emailId;
        Address = address;
        this.pincode = pincode;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getMobileNumberTwo() {
        return MobileNumberTwo;
    }

    public void setMobileNumberTwo(String mobileNumberTwo) {
        MobileNumberTwo = mobileNumberTwo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
