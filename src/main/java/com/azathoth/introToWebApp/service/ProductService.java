package com.azathoth.introToWebApp.service;

import com.azathoth.introToWebApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products;

    // insert data to the product
    public ProductService(List<Product> products) {
        products = new ArrayList<>(Arrays.asList(
                new Product(1, "Iphone", 50000),
                new Product(2, "Nike Shoes", 3500),
                new Product(3, "T-shirt", 1500)
        ));

        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(prod -> prod.getId() == id) // filter product by id
                .findFirst()
                .orElse(new Product(404, "Not found", 0));
    }

    public boolean addProduct(Product product) {
        return this.products.add(product);
    }

    public boolean removeProduct(int id) {
        return products.removeIf(prod -> prod.getId() == id);
    }
}
