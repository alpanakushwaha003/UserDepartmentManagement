package com.javalearn.learnSpringBoot.controller;
import com.javalearn.learnSpringBoot.dto.UserDTO;
import com.javalearn.learnSpringBoot.entity.User;
import com.javalearn.learnSpringBoot.service.UserService;
import com.javalearn.learnSpringBoot.search.UserSearchService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  UserSearchService userSearchService;

    @PostMapping("/createUser")
    public UserDTO createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/departmentSearch")
    public List<UserDTO> getUserByDepartment(@RequestParam String department) {
        return userService.findByDepartment(department);
    }
//    @GetMapping("/prefix")
//    public List<User> getUserByPrefix(@RequestParam String prefix) {
//        return userService.findByPrefix(prefix);
//    }
    @GetMapping("/search")
    public List<UserDTO> searchUsers(@RequestParam String q) {
        return userSearchService.search(q);
    }
    @GetMapping("/departmentSearchById")
    public List<UserDTO> getUsersByDepartmentId(@RequestParam Long departmentId) {
        return userService.findByDepartmentId(departmentId);
    }

}
