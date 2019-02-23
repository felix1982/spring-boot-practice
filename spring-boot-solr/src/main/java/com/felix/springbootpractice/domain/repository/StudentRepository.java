package com.felix.springbootpractice.domain.repository;

import com.felix.springbootpractice.domain.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends SolrCrudRepository<Student,Long> {

    List<Student> findByName(String name);

    @Query("*:*")
    Page<Student> findAllWithPageable(Pageable pageable);

    @Highlight(prefix = "</highlight>",postfix = "</highlight>")
    @Query("*:*")
    HighlightPage<Student> findWithHighlight(Pageable pageable);

}
