package com.example.ELK.controller;

import com.example.ELK.model.Product;
import com.example.ELK.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("Received a product creation request: {}", product);
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        log.info("Received a request to retrieve the product with ID: {}", id);
        return productService.getProduct(id);
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        log.info("Received a request to retrieve all products");
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        log.info("Received a request to update the product with ID: {}", id);
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        log.info("Received a request to delete the product with ID: {}", id);
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}