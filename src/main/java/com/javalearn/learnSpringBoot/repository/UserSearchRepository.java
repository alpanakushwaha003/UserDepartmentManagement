package com.javalearn.learnSpringBoot.repository;
import com.javalearn.learnSpringBoot.search.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
public interface UserSearchRepository
        extends ElasticsearchRepository<UserDocument, Long> {
// List<UserDocument> findByNameContainingIgnoreCase(String name);
  //New method to search by name or department name
//  List<UserDocument> findByNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(
//          String name,
//          String departmentName
//  );
}
