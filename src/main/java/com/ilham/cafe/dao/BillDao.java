package com.ilham.cafe.dao;

import com.ilham.cafe.POJO.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillDao extends JpaRepository<Bill, Integer> {
    @Query("select b from Bill b order by b.id desc")
    List<Bill> getAllBills();

    @Query("select b from Bill b where b.createdBy=:username order by b.id desc ")
    List<Bill> getBillByUserName(@Param("username") String username);
}
