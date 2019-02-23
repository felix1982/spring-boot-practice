package com.felix.springbootpractice.domain.service;

import com.felix.springbootpractice.domain.entity.Student;
import com.felix.springbootpractice.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019-2-23.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> searchByName(String name){
      return   studentRepository.findByName(name);
    }

    public List<Student> searchWithPageable(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        return studentRepository.findAllWithPageable(pageRequest).getContent();
    }

    public List<Student> searchWithHighlight(Integer pageNum,Integer pageSize){
        List<Student> result = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        HighlightPage<Student> highlightPage = studentRepository.findWithHighlight(pageRequest);
        // highlightPage.getContent(); 这个是返回不带高亮数据
        /*
          这里处理逻辑是遍历Highlighted数据,然后把指定的字段的高亮数据替换到Entry的数据中，然后再放到返回结果列表中
         */
        for(HighlightEntry<Student>  highlightEntry : highlightPage.getHighlighted()){
            for(HighlightEntry.Highlight highlight : highlightEntry.getHighlights() ){
                if(highlight.getField().equals("name")){
                    highlightEntry.getEntity().setName("");
                    String temp = "";
                    for (String sl : highlight.getSnipplets()){
                        temp+=sl;
                    }
                    highlightEntry.getEntity().setName(temp);
                }
            }
            result.add(highlightEntry.getEntity());
        }
        return result;
    }
}
