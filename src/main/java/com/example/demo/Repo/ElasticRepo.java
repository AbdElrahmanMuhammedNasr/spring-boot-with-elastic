package com.example.demo.Repo;

import com.example.demo.Model.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticRepo extends ElasticsearchRepository<Person, String> {

    @Query("{ \"match\" : {\"is_success\" : \"?0\"} }")

//    @Query(""" dksjdksjd """)
    List<Object> getPeronTrue(boolean status);
}


//        "query": {
//        "bool" : {
//        "must" : [
//        { "query_string" : { "query" : "?", "fields" : [ "name" ] } },
//        { "query_string" : { "query" : "?", "fields" : [ "price" ] } }
//        ]
//        }
//        }
//        }