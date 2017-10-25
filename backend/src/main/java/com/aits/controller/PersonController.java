package com.aits.controller;

import com.aits.dao.PersonRepository;
import com.aits.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author janda
 * Created by Janda on 2017/10/24.
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;


    @RequestMapping("/findAll")
    @ResponseBody
    public List<Person> findAll(){
        List<Person> personList = personRepository.findAll();
        return personList;
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<Person> query(@RequestBody Person person){
        List<Person> personList = personRepository.findByNameContainingAndAgeAndAddressStartingWith(person.getName(),person.getAge(),person.getAddress());
        return personList;
    }

}
