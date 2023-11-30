package com.servicebid.apis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicebid.apis.models.ProductCategory;
import com.servicebid.apis.repository.ProductCategoryRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class ProductCategoryController {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("/productcategories")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        try {

            List<ProductCategory> productCategories = new ArrayList<ProductCategory>();

            productCategoryRepository.findAll().forEach(productCategories::add);

            if (productCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productcategory/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id") long id) {
        Optional<ProductCategory> productCategoryData = productCategoryRepository.findById(id);
        if (productCategoryData.isPresent()) {
            return new ResponseEntity<>(productCategoryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productcategory/create")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        try {
            ProductCategory _productCategory = productCategoryRepository
                    .save(new ProductCategory(
                            productCategory.getName(),
                            productCategory.getDescription(),
                            productCategory.getStatus(),
                            productCategory.getImagepath()));
            return new ResponseEntity<>(_productCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
