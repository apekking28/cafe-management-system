package com.ilham.cafe.dao;

import com.ilham.cafe.POJO.User;
import com.ilham.cafe.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.email =:email")
    User findByEmailId(@Param("email") String email);

    @Query("select new com.ilham.cafe.wrapper.UserWrapper(u.id,u.name,u.email,u.contactNumber,u.status) from User u where " +
            "u.role='user'")
    List<UserWrapper> getAllUser();

    @Query("select u.email  from User u where u.role='admin'")
    List<String> getAllAdmin();

    @Transactional
    @Modifying
    @Query("update User u set u.status=:status where u.id=:id")
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);


    User findByEmail(String email);


}
