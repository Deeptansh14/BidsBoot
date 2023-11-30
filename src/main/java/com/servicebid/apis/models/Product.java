package com.servicebid.apis.models;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "askingPrice")
    private float askingPrice;

    @Column(name = "productType")
    private int productType;

    @ManyToOne
    @JoinColumn(name="categoryID")
    private ProductCategory productCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "productAgeNewPurchaseDateTime")
    private ZonedDateTime productAgeNewPurchaseDateTime;

    @Column(name="qtyTotal")
    private int qtyTotal;

    @Column(name="qtySold")
    private int qtySold;

    @Column(name="imagePath")
    private String imagePath;

    @Column(name="videoPath")
    private String videoPath;

    public Product(String name, float askingPrice, int productType, ProductCategory productCategory, String description,
            ZonedDateTime productAgeNewPurchaseDateTime, int qtyTotal, int qtySold, String imagePath,
            String videoPath) {
        this.name = name;
        this.askingPrice = askingPrice;
        this.productType = productType;
        this.productCategory = productCategory;
        this.description = description;
        this.productAgeNewPurchaseDateTime = productAgeNewPurchaseDateTime;
        this.qtyTotal = qtyTotal;
        this.qtySold = qtySold;
        this.imagePath = imagePath;
        this.videoPath = videoPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(float askingPrice) {
        this.askingPrice = askingPrice;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getProductAgeNewPurchaseDateTime() {
        return productAgeNewPurchaseDateTime;
    }

    public void setProductAgeNewPurchaseDateTime(ZonedDateTime productAgeNewPurchaseDateTime) {
        this.productAgeNewPurchaseDateTime = productAgeNewPurchaseDateTime;
    }

    public int getQtyTotal() {
        return qtyTotal;
    }

    public void setQtyTotal(int qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

    public int getQtySold() {
        return qtySold;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", askingPrice=" + askingPrice + ", productType=" + productType
                + ", productCategory=" + productCategory + ", description=" + description
                + ", productAgeNewPurchaseDateTime=" + productAgeNewPurchaseDateTime + ", qtyTotal=" + qtyTotal
                + ", qtySold=" + qtySold + ", imagePath=" + imagePath + ", videoPath=" + videoPath + "]";
    }

}
