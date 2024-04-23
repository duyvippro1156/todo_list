package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto categoryDto) {
        productService.addProduct(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getCategory(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> listProducts = productService.getAllProduct();
        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateCategory(@RequestBody ProductDto productDto,
                                                      @PathVariable("id") Long id) {
        productService.updateProduct(productDto, id);                                            
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        productService.deleteproduct(id);
        return new ResponseEntity<>("Category deleted successfully!.", HttpStatus.OK);
    }

}
