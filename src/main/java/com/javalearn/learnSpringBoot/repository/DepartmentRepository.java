package com.javalearn.learnSpringBoot.repository;
import com.javalearn.learnSpringBoot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}

