package com.azathoth.introToWebApp.controller;

import com.azathoth.introToWebApp.model.Product;
import com.azathoth.introToWebApp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController {

    private final ProductService productService;

    // DI autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    // display all the products on the page
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();

        // checks if products is empty and return response
        return products.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/specific-product") // accepts parameter using ?id=
    public ResponseEntity<Product> getProductById(@RequestParam int id) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        return optionalProduct
                .map(prod -> new ResponseEntity<>(prod, HttpStatus.OK)) // if exists, return 200
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // if not exist, return 404
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) { // this annotation will be able the server to accept data
        Product savedProduct = productService.addProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Void> removeProduct(@RequestParam int id) {
        if (productService.removeProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Optional<Product> optionalProduct = productService.updateProduct(product);

        // map using product's id
        return optionalProduct
                .map(prod -> new ResponseEntity<>(prod, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
