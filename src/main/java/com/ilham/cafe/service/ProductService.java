package com.ilham.cafe.service;

import com.ilham.cafe.dto.ProductResponseDTO;
import com.ilham.cafe.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<ProductResponseDTO> addNewProduct(Map<String, String> requestMap);

    ResponseEntity<List<ProductWrapper>> getAllProduct();

    ResponseEntity<ProductResponseDTO> updateProduct(Map<String, String> requestMap);

    ResponseEntity<ProductResponseDTO> deleteProduct(Integer id);

    ResponseEntity<ProductResponseDTO> updateStatus(Map<String, String> requestMap);

    ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

    ResponseEntity<ProductWrapper> getProductById(Integer id);
}
