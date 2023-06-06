package com.example.hospital.Models;

public class HealthDetailModel {
    String Diabetes,HIV,Blood,AIDS;

    public HealthDetailModel() {
    }

    public HealthDetailModel(String diabetes, String HIV, String blood, String AIDS) {
        Diabetes = diabetes;
        this.HIV = HIV;
        Blood = blood;
        this.AIDS = AIDS;
    }


    public String getDiabetes() {
        return Diabetes;
    }

    public void setDiabetes(String diabetes) {
        Diabetes = diabetes;
    }

    public String getHIV() {
        return HIV;
    }

    public void setHIV(String HIV) {
        this.HIV = HIV;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getAIDS() {
        return AIDS;
    }

    public void setAIDS(String AIDS) {
        this.AIDS = AIDS;
    }
}
