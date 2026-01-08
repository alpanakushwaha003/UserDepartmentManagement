package com.javalearn.learnSpringBoot.repository;

import com.javalearn.learnSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDepartment_Name(String departmentName);
    List<User> findByDepartment_Id(Long departmentId);

}
