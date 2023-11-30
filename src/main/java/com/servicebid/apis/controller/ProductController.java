package com.servicebid.apis.controller;

import java.time.ZonedDateTime;
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

import com.servicebid.apis.models.Product;
import com.servicebid.apis.models.ProductCategory;
import com.servicebid.apis.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {

            List<Product> products = new ArrayList<Product>();

            productRepository.findAll().forEach(products::add);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductCategoryById(@PathVariable("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product _product = productRepository
                    .save(new Product(product.getName(), (float) product.getAskingPrice(), (int) product.getProductType(),
                            (ProductCategory) product.getProductCategory(), product.getDescription(),
                            (ZonedDateTime) product.getProductAgeNewPurchaseDateTime(), (int) product.getQtyTotal(), (int) product.getQtySold(),
                            product.getImagePath(), product.getVideoPath()));
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
