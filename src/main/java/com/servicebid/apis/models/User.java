package com.servicebid.apis.models;

import java.time.ZonedDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "name")
    private String name;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @Column(name = "status")
    private String status;
    
    // sanity two properties below, not really used for now:

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(name = "createdTime")
    private ZonedDateTime createdTime;

    public User() {
    }

    public User(String emailAddress, String name, String mobileNumber, String status, boolean isEnabled) {
        // userID is GeneratedValue with GenerationType AUTO,
        // so doesn't have to be set explicitly for a new User Object
        // as it is set by the database auto increment

        this.emailAddress = emailAddress;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.status = status;

        this.isEnabled = isEnabled;
        this.createdTime = java.time.ZonedDateTime.now();
    }

    // properties that will never change for a given user,
    // need have only get methods:

    public long getId() {
        return userID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    // properties with get and set methods:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "User [id=" + userID
                + ", emailAddress=" + emailAddress
                + ", name=" + name
                + ", mobileNumber=" + mobileNumber
                + ", status=" + status
                + ", isEnabled=" + isEnabled
                + ", createdTime=" + createdTime
                + "]";
    }

}
