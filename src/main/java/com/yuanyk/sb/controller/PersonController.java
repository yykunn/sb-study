package com.yuanyk.sb.controller;

import com.yuanyk.sb.aop.MyLog;
import com.yuanyk.sb.pojo.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @GetMapping("/get/{id}")
    public Person getPerson(@PathVariable("id")Integer id,@RequestParam(required = false) String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    /**
     * 接收json数据 默认json解析 @link {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}
     * 返回properties数据 @link {@link com.yuanyk.sb.messageConvert.MyMsgConvert writeInternal()}
     * @param person
     * @return
     */
    @PostMapping(value = "/json/to/properties",consumes = "application/json",produces = "application/properties")
    public Person jsonToProperties(@RequestBody Person person){
        return person;
    }

    @PostMapping(value = "/properties/to/json",consumes = "application/properties",produces = "application/json")
    public Person propertiesToJson(@RequestBody Person person){
        return person;
    }

    @MyLog("get cz")
    @GetMapping("/get")
    public void get(String name,Integer id){
        System.out.println(name+" "+id);
    }

    @MyLog("post cz")
    @PostMapping("/post")
    public void post(@RequestBody Person person){
        System.out.println(person);
    }
}
