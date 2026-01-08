package com.javalearn.learnSpringBoot.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "departments")
@Setting(
        settingPath = "/es-settings.json"
)
public class DepartmentDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ngram_index", searchAnalyzer = "ngram_search")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
