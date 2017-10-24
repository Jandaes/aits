package com.aits.service;

import com.aits.entity.Person;

/**
 * @author jared
 */
public interface DemoService {
    /**
     * 测试缓存
     * @param person
     * @return
     */
    public Person save(Person person);

    /**
     * 测试缓存
     * @param id
     */
    public void remove(Long id);

    /**
     * 测试缓存
     * @param person
     * @return
     */
    public Person findOne(Person person);

    /**
     * 测试遇到异常回滚事物
     * @param person
     * @return
     */
    public Person savePersonWithRollBack(Person person);

    /**
     * 测试遇到异常不回滚事物
     * @param person
     * @return
     */
    public Person savePersonWithoutRollBack(Person person);

}
