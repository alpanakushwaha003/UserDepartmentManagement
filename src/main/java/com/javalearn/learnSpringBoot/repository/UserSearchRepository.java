package com.javalearn.learnSpringBoot.repository;
import com.javalearn.learnSpringBoot.search.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
public interface UserSearchRepository
        extends ElasticsearchRepository<UserDocument, Long> {
}
