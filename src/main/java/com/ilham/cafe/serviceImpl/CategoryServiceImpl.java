package com.ilham.cafe.serviceImpl;

import com.google.common.base.Strings;
import com.ilham.cafe.JWT.JwtFilter;
import com.ilham.cafe.POJO.Category;
import com.ilham.cafe.constants.CafeConstants;
import com.ilham.cafe.dao.CategoryDao;
import com.ilham.cafe.dto.CategoryResponseDTO;
import com.ilham.cafe.service.CategoryService;
import com.ilham.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<CategoryResponseDTO> addNewCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, false)) {
                    categoryDao.save(getCategoryFromMap(requestMap, false));
                    return new ResponseEntity<>(new CategoryResponseDTO("Category Added Successfully"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(new CategoryResponseDTO(CafeConstants.UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new CategoryResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                log.info("Inside If");
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<CategoryResponseDTO> updateCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, true)) {
                    Optional<Category> optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        categoryDao.save(getCategoryFromMap(requestMap, true));
                        return new ResponseEntity<>(new CategoryResponseDTO("Category Updated Successfully"), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new CategoryResponseDTO("Category id does not exist"), HttpStatus.BAD_REQUEST);
                    }
                }
                return new ResponseEntity<>(new CategoryResponseDTO(CafeConstants.INVALID_DATA), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new CategoryResponseDTO(CafeConstants.UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new CategoryResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Category category = new Category();
        if (isAdd) {
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }
}
