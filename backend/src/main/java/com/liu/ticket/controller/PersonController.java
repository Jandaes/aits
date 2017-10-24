package com.liu.ticket.controller;

import com.liu.dao.PersonRepository;
import com.liu.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    public List<Person> findAll(@RequestBody Person person){
        Example example = Example.of(person);
        List<Person> personList = personRepository.findAll(example);
        return personList;
    }
}
