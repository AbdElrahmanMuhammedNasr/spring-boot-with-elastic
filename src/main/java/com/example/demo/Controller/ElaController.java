package com.example.demo.Controller;

import com.example.demo.Model.Enumeration.Gender;
import com.example.demo.Model.Enumeration.PhoneProvider;
import com.example.demo.Model.Person;
import com.example.demo.Repo.ElasticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;


@RestController
public class ElaController {

    @Autowired
    private ElasticRepo elasticRepo;


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
    public Object get_person_status() {
        return elasticRepo.getPeronTrue(false);
    }
}
