package com.aits.dao;

import com.aits.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jared
 */
public interface PersonRepository extends JpaRepository<Person,Long>{

    //----------------------------根据属性名查询

    /**
     * 通过名字相等查询，参数为name
     * 相当于JPQL：select p from Person p where p.name=?1
     * @param name
     * @return
     */
    List<Person> findByName(String name);

    /**
     * 通过名字like查询，参数为name
     * 相当于JPQL：select p from Person p where p.name like ?1
     * @param name
     * @return
     */
    List<Person> findByNameLike(String name);

    /**
     * 通过名字和地址查询，参数为name和address
     * 相当于JPQL：select p from Person p where p.name=?1 and p.address=?2
     * @param address
     * @param name
     * @return
     */
    List<Person> findByNameAndAddress(String name,String address);

    /**
     * 获得符合条件的前10条数据
     * @param name
     * @return
     */
    List<Person> findFirst10ByName(String name);

    /**
     * 获得符合条件查询的前30条数据
     * @param name
     * @return
     */
    List<Person> findTop30ByName(String name);

    //--------------------------使用@Query查询

    /**
     * 自定义sql语句,使用参数索引
     * @param address
     * @return
     */
    @Query("select p from Person p where p.address=?1")
    List<Person> findByAddress(String address);

    /**
     * 使用命名参数
     * @param address
     * @return
     */
    @Query("select p from Person p where p.address=:address")
    List<Person> findByAddress2(@Param("address") String address);

    /**
     * 更新查询
     * @param name
     * @return
     */
    @Modifying
    @Transactional
    @Query("update Person p set p.name=?1")
    int setName(String name);


    //-----------------------分页、排序

    /**
     * 排序
     * @param name
     * @param sort
     * @return
     */
    List<Person> findByName(String name, Sort sort);

    /**
     * 分页
     * @param name
     * @param pageable
     * @return
     */
    List<Person> findByName(String name, Pageable pageable);
}
