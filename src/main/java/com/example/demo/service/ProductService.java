package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;

@Service
public interface ProductService {
    void addProduct(ProductDto productDto);

    Product getProduct(Long id);

    List<Product> getAllProduct();

    void updateProduct(ProductDto productDto, Long id);

    void deleteproduct(Long id);
}
