package com.example.demo.Controller;

import com.example.demo.Config.ElasticConfig;
import com.example.demo.Model.Enumeration.Gender;
import com.example.demo.Model.Enumeration.PhoneProvider;
import com.example.demo.Model.Person;
import com.example.demo.Repo.ElasticRepo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class ElaController {

    @Autowired
    private ElasticRepo elasticRepo;

    @Autowired
    private ElasticConfig elasticConfig;


    private static final Logger LOG = Logger.getLogger(ElaController.class.getName());

    @RequestMapping(value = "/elk")
    public String helloWorld() {
        String response = "Welcome to JavaInUse" + new Date();
        LOG.log(Level.INFO, response);
        LOG.log(Level.WARNING, response);
        LOG.log(Level.FINER, response);
        return response;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void add_person(@RequestBody Person personBody) {
        // solve this error in pom         <elasticsearch.version>7.15.2</elasticsearch.version> check elastic version first
        final Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName(personBody.getName());
        person.setAge(personBody.getAge());
        person.setPrice(personBody.getPrice());
        person.setIs_success(personBody.getIs_success());
        person.setIs_enable(personBody.getIs_enable());
        person.setIs_active(personBody.getIs_active());
        person.setIs_healthy(personBody.getIs_healthy());
        person.setPhoneProvider(personBody.getPhoneProvider());
        person.setGender(personBody.getGender());
        person.setLogin_data(new Date());
        elasticRepo.save(person);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get_person() {
        return elasticRepo.findAll();
    }

    @RequestMapping(value = "/get-status", method = RequestMethod.GET)
    public Object get_person_status() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("person");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("is_success", false));


        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticConfig.elasticsearchClient().search(searchRequest, RequestOptions.DEFAULT);

        return searchResponse;
//        return elasticRepo.getPeronTrue(false);
    }
}
