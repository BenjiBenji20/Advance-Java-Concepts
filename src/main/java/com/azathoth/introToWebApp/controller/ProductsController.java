package com.azathoth.introToWebApp.controller;

import com.azathoth.introToWebApp.model.Product;
import com.azathoth.introToWebApp.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    private final ProductService productService;

    // DI autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    // display all the products on the page
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/specific-product") // accepts parameter using ?id=
    public Product getProductById(@RequestParam int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add-product")
    public void addProduct(@RequestBody Product product) { // this annotation will be able the server to accept data
        productService.addProduct(product);
    }

    @DeleteMapping("/delete-product")
    public void removeProduct(@RequestParam int id) {
        productService.removeProduct(id);
    }

    @PutMapping("/update-product")
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }
}
