package com.felix.springbootpractice.domain.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.lang.annotation.Documented;

/**
 * Created by Administrator on 2019-2-23.
 */
@SolrDocument(solrCoreName="student")
public class Student {
    @Field("id")
    Long id;
    @Field("name")
    String name;
    @Field("age")
    Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
