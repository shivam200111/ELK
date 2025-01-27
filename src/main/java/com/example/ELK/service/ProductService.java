package com.example.ELK.service;

import com.example.ELK.model.Product;
import com.example.ELK.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product saveProduct(Product product) {
        log.info("Saving a new product: {}", product);
        return productRepository.save(product);
    }


    public Product getProduct(Long id) {
        log.info("Retrieving the product with ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    public Iterable<Product> getAllProducts() {
        log.info("Retrieving all products");
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        log.info("Updating the product with ID: {}", id);
        return productRepository.findById(id).map(existingProduct -> {
            // Update the existing product fields
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            // Save the updated product
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        log.info("Deleting the product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}