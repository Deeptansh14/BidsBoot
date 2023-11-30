package com.servicebid.apis.models;

import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "personname")
    private String personname;

    @Column(name = "emailid")
    private String emailid;

    @Column(name = "preferences")
    private String preferences;

    @Column(name = "createdtime")
    private String createdtime;

    @Column(name = "isenabled")
    private boolean isenabled;

    public Person() {
    }

    // create person with only a few inputs, and created time is set by the system

    public Person(String personname, String emailid) {
        this.personname = personname;
        this.emailid = emailid;
        this.createdtime = java.time.ZonedDateTime.now().toString();
    }

    // properties with only get methods:

    public long getId() {
        return id;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getCreatedTime() {
        return createdtime;
    }

    // properties with get and set methods:

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public boolean getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }

    @Override
    public String toString() {
        return "Person [id=" + id
            + ", personname=" + personname
            + ", emailid=" + emailid
            + ", preferences=" + preferences
            + ", isenabled=" + isenabled
            + ", createdtime=" + createdtime
            + "]";
    }

}
