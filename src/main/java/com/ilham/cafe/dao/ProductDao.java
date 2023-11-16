package com.ilham.cafe.dao;

import com.ilham.cafe.POJO.Product;
import com.ilham.cafe.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    List<ProductWrapper> getAllProduct();
}
