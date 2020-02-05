package com.example.heronation;

//수정 필요
public class UserMyInfo {
    String consumerId;
    String name;
    String email;
    String gender;
    Integer height;
    Integer weight;
    Integer birthYear;
    Integer birthMonth;
    Integer birthDay;
    String termsAdvertisement;

    public UserMyInfo(String consumerId, String name, String email, String gender, Integer height, Integer weight, Integer birthYear, Integer birthMonth, Integer birthDay, String termsAdvertisement) {
        this.consumerId = consumerId;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.termsAdvertisement = termsAdvertisement;
    }


}

/*
  "consumerId": "syh8088",
  "name": "서양훈",
  "email": "syh8088@nate.com",
  "gender": "M",
  "height": 171,
  "weight": 60,
  "birthYear": 1988,
  "birthMonth": 8,
  "birthDay": 8,
  "termsAdvertisement": "Y",
  "lastAccessDt": "2020-02-04T17:00:15",
  "corpType": "normal",
  "wardrobeViewerCount": 0,
  "shoulderLevel": 1,
  "chestLevel": 1,
  "hubLevel": 1,
  "hipLevel": 1,
  "thighLevel": 1,
  "shoulderSensibilityLevel": 3,
  "chestSensibilityLevel": 3,
  "hubSensibilityLevel": 3,
  "hipSensibilityLevel": 3,
  "thighSensibilityLevel": 3
  */