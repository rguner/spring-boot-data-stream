package com.guner.controller;

import com.guner.entity.Product;
import com.guner.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private ProductService productService;


    // http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllBooksWithStream();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("getAllBooksWithAutoClosableStream")
    public ResponseEntity<List<Product>> getAllBooksWithAutoClosableStream(){
        List<Product> products = productService.getAllBooksWithAutoClosableStream();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/getAllProductsWithList")
    public ResponseEntity<List<Product>> getAllProductsWithList(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
