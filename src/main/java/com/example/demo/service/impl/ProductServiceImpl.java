package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    @Override
    public void deleteproduct(Long id) {
        productRepository.deleteById(id);   
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Product getProduct(Long id) {
        return this.productRepository.getById(id);

    }

    @SuppressWarnings("deprecation")
    @Override
    public void updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.getById(id);

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(id);
        productRepository.save(product);
    }

}
