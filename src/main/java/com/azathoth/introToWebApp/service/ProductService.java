package com.azathoth.introToWebApp.service;

import com.azathoth.introToWebApp.model.Product;
import com.azathoth.introToWebApp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean removeProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Product> updateProduct(Product product) {
        if(productRepository.existsById(product.getId())) {
            return Optional.of(productRepository.save(product)); // update product and return it into optional
        }

        return Optional.empty(); // if not found, return empty optional
    }
}
