package com.ilham.cafe.dao;

import com.ilham.cafe.POJO.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository<Bill,Integer> {

}
