package com.example.demo.Model;

import com.example.demo.Model.Enumeration.Gender;
import com.example.demo.Model.Enumeration.PhoneProvider;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;


@Document(indexName = "person",createIndex = true)
@Setting(settingPath = "static/setting.json")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    @Id
    private String id;
//    @Field(type = FieldType.Text, name = "Person_name")
    private String name;
    private  int age;
    private Double price;
    private Boolean is_success;
    private Boolean is_enable;
    private Boolean is_active;
    private Boolean is_healthy;
    private PhoneProvider phoneProvider;
    private Gender gender;
    private Date login_data = new Date();


}

