package com.javalearn.learnSpringBoot.repository;

import com.javalearn.learnSpringBoot.search.DepartmentDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DepartmentSearchRepository
        extends ElasticsearchRepository<DepartmentDocument, Long> {

    List<DepartmentDocument> findByNameContaining(String name);
}
