package com.javalearn.learnSpringBoot.search;

import com.javalearn.learnSpringBoot.repository.DepartmentSearchRepository;
import com.javalearn.learnSpringBoot.search.DepartmentDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class DepartmentSearchService {

    @Autowired
    private DepartmentSearchRepository repository;

    public List<String> suggestDepartments(String query) {

        if (query == null || query.isBlank()) {
            return StreamSupport
                    .stream(repository.findAll().spliterator(), false)
                    .map(DepartmentDocument::getName)
                    .distinct()
                    .toList();
        }
        return repository.findByNameContaining(query)
                .stream()
                .map(DepartmentDocument::getName)
                .distinct()
                .toList();
    }
}
