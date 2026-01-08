package com.javalearn.learnSpringBoot.service;
import com.javalearn.learnSpringBoot.dto.UserDTO;
import com.javalearn.learnSpringBoot.entity.User;
import javax.transaction.Transactional;
import com.javalearn.learnSpringBoot.entity.Department;
import com.javalearn.learnSpringBoot.repository.UserRepository;
import com.javalearn.learnSpringBoot.repository.DepartmentRepository;
import com.javalearn.learnSpringBoot.search.UserDocument;
import com.javalearn.learnSpringBoot.search.DepartmentDocument;
import com.javalearn.learnSpringBoot.repository.UserSearchRepository;
import com.javalearn.learnSpringBoot.repository.DepartmentSearchRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserSearchRepository userSearchRepository;
    @Autowired
    private DepartmentSearchRepository departmentSearchRepository;

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setDepartmentId(user.getDepartment().getId());
        dto.setDepartmentName(user.getDepartment().getName());
        return dto;
    }


    @Transactional
    @CacheEvict(value = "usersByDepartment", key = "#user.department.name")
    public UserDTO createUser(User user) {
        String deptName = user.getDepartment().getName();
        Department department = departmentRepository.findByName(deptName);

        if (department == null) {
            throw new RuntimeException("Department not found");
        }

        user.setDepartment(department);
        User savedUser = userRepository.save(user);
        // New
        UserDocument doc = new UserDocument();
        doc.setId(savedUser.getId());
        doc.setName(savedUser.getName());
        doc.setEmail(savedUser.getEmail());
        doc.setDepartmentName(savedUser.getDepartment().getName());
        userSearchRepository.save(doc);
        return mapToDTO(savedUser);
    }

    @Cacheable(value = "usersByDepartment", key = "#departmentName")
    public List<UserDTO> findByDepartment(String departmentName) {
        return userRepository.findByDepartment_Name(departmentName).stream().map(this::mapToDTO).toList();
    }
    public List<UserDTO> findByDepartmentId(Long departmentId) {
        return userRepository
                .findByDepartment_Id(departmentId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
}
