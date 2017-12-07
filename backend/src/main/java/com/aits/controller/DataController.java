package com.aits.controller;

import com.aits.dao.PersonRepository;
import com.aits.entity.Person;
import com.aits.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jared
 */
@RestController
public class DataController {
    /**
     * springData Jpa已自动注入bean
     */
    @Autowired
    PersonRepository personRepository;
    @Autowired
    DemoService demoService;

    /**
     * 支持批量保存：<S extends T>  Iterable<S> save(Iterable<s> entities);
     * 删除：
     * 支持id删除对象、批量删除以及删除全部
     * void delete(Id id);
     * void delete(T entity);
     * void delete(Iterable<? extends T> entities);
     * void deleteAll()l
     *
     * @param name
     * @param address
     * @param age
     * @return
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    @RequestMapping("q1")
    public List<Person> q1(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    /**
     * 测试分页、排序
     * @return
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
        return pagePeople;
    }

    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return people;
    }

    /**
     * 缓存测试，查询此方法，若sql只执行一次，则缓存生效
     * @param id
     * @return
     */
    @RequestMapping("/getOne")
    public Person getOne(Long id) {
        Person p = new Person();
        p.setId(id);
        Person person = demoService.findOne(p);
        return person;
    }

    /**
     * 测试事物回滚：http://localhost:8999/rollback?name=73&age=50
     * @param person
     * @return
     */
    @RequestMapping("/rollback")
    public Person rollback(Person person) {
        return demoService.savePersonWithRollBack(person);
    }

    /**
     * 测试事物不回滚：http://localhost:8999/norollback?name=73&age=50
     * @param person
     * @return
     */
    @RequestMapping("/norollback")
    public Person noRollback(Person person) {
        return demoService.savePersonWithoutRollBack(person);
    }



}
