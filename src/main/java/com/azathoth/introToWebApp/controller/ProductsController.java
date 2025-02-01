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

    // display on the page
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/specific-product") // accepts parameter using ?id=
    public Product getProductById(@RequestParam int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add-product")
    public boolean addProduct(@RequestBody Product product) { // this annotation will be able the server to accept data
        return productService.addProduct(product); // error: annot resolve method 'addProduct' in 'List'
    }

    @DeleteMapping("/delete-product")
    public boolean removeProduct(@RequestParam int id) {
        return productService.removeProduct(id);
    }
}
