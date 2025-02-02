package com.azathoth.introToWebApp.service;

import com.azathoth.introToWebApp.model.Product;
import com.azathoth.introToWebApp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    // insert data to the product
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElse(new Product());
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProduct(int id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
