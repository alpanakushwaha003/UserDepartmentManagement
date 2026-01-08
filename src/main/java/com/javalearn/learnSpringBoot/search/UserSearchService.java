package com.javalearn.learnSpringBoot.search;
import com.javalearn.learnSpringBoot.dto.UserDTO;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSearchService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    public List<UserDTO> search(String searchText) {

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.boolQuery()
                                .should(QueryBuilders.matchQuery("name", searchText))
                                .should(QueryBuilders.matchQuery("departmentName", searchText))
                )
                .build();

        SearchHits<UserDocument> searchHits =
                elasticsearchOperations.search(searchQuery, UserDocument.class);

        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private UserDTO mapToDTO(UserDocument doc) {
        UserDTO dto = new UserDTO();
        dto.setId(doc.getId());
        dto.setName(doc.getName());
        dto.setEmail(doc.getEmail());
        dto.setDepartmentName(doc.getDepartmentName());
        return dto;
    }
}




