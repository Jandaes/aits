package com.aits.controller;

import com.aits.dao.PersonRepository;
import com.aits.dao.UserRepository;
import com.aits.entity.Person;
import com.aits.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author janda
 * Created by Janda on 2017/10/24.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    UserRepository userRepository;


    @RequestMapping("/findAll")
    public List<Person> findAll(){
        List<Person> personList = personRepository.findAll();
        return personList;
    }

    @RequestMapping("/query")
    public List<Person> query(@RequestBody Person person){
        List<Person> personList = personRepository.findByNameContainingAndAgeAndAddressStartingWith(person.getName(),person.getAge(),person.getAddress());
        return personList;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/insert" , method = RequestMethod.POST)
    public Map<String,Object> insert(@Valid User user, BindingResult bindingResult){
        Map<String,Object> map = new HashMap<>(3);
        if(bindingResult.hasErrors()){
            map.put("code","500");
            map.put("message",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        try {
            user = userRepository.save(user);
            map.put("code","200");
            map.put("message","添加成功");
            map.put("user",user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code","201");
            map.put("message","添加失败");
        }
        return map;
    }

}
