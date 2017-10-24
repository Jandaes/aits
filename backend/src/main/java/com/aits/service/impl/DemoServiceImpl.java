package com.aits.service.impl;

import com.aits.dao.PersonRepository;
import com.aits.entity.Person;
import com.aits.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jared
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;


    /**
     * @CachePut缓存新增或更新数据到缓存，其中缓存名称为people，数据key是people的id
     * @param person
     * @return
     */
    @Override
    @CachePut(value = "people",key = "#person.id")
    public Person save(Person person) {
        Person p=personRepository.save(person);
        return p;
    }

    /**
     * @CacheEvict从缓存people删除key为id的数据
     * @param id
     */
    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        personRepository.delete(id);
    }

    /**
     * @cacheable缓存key为person的id数据到people中（如果没有指明key，则方法参数会做为key保存到缓存中）
     * @param person
     * @return
     */
    @Override
    @Cacheable(value="people",key="#person.id")
    public Person findOne(Person person) {
        Person p=personRepository.findOne(person.id);
        return p;
    }

    /**
     * 遇到异常回滚事务
     * @param person
     * @return
     */
    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithRollBack(Person person) {
        Person p=personRepository.save(person);
        if(person.getName().equals("73")){
            throw new IllegalArgumentException("73已存在，。回滚吧，皮卡丘..");
        }
        return p;
    }

    /**
     * 遇到异常不回滚事物
     * @param person
     * @return
     */
    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithoutRollBack(Person person) {
        Person p=personRepository.save(person);
        if(person.getName().equals("73")){
            throw new IllegalArgumentException("73已存在，。然而并没有什么暖用，不回滚..");
        }
        return p;
    }
}
