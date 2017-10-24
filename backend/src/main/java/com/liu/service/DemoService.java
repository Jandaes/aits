package com.liu.service;

import com.liu.entity.Person;

public interface DemoService {
    public Person save(Person person);
    public void remove(Long id);
    public Person findOne(Person person);
}
