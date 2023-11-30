package com.servicebid.apis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "productcategories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    @Column(name = "imagepath")
    private String imagepath;

    public ProductCategory() {
    }

    public ProductCategory(String name, String description, boolean status, String imagepath) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.imagepath = imagepath;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString() {
        return "ProductCategory [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
                + ", imagepath=" + imagepath + "]";
    }

}
