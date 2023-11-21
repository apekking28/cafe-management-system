package com.ilham.cafe.service;

import com.ilham.cafe.POJO.Category;
import com.ilham.cafe.dto.CategoryResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    ResponseEntity<CategoryResponseDTO> addNewCategory(Map<String, String> requestMap);

    ResponseEntity<List<Category>> getAllCategory(String filterValue);

    ResponseEntity<CategoryResponseDTO> updateCategory(Map<String, String> requestMap);
}
