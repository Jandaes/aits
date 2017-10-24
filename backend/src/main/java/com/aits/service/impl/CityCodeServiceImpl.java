package com.aits.service.impl;

import com.aits.service.CityCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author jared
 */
@Service
public class CityCodeServiceImpl implements CityCodeService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getCityByName(String name) {
        List<Map<String, Object>> list= jdbcTemplate.queryForList("select cname from city where cname like %?%",new Object[]{name});
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllCity() {
        List<Map<String, Object>> list= jdbcTemplate.queryForList("select cityname,cityCode from city");
        return list;
    }

    @Override
    public void saveCity() {

    }
    public static void main(String[] args) throws IOException {
        File f=new File("D:\\workspace\\mabiao.txt");
        InputStreamReader isr=new InputStreamReader(new FileInputStream(f),"GBK");
        BufferedReader br=new BufferedReader(isr);
        String line;
        StringBuilder sb=new StringBuilder();
        while((line=br.readLine())!=null){
            sb.append(line);
        }
        System.out.println(sb.toString());
        String data[]=sb.toString().split("\\|");
        System.out.println(data[0]);
        System.out.println(data.length/4);
        System.out.println(5%4);
        ApplicationContext context= new ClassPathXmlApplicationContext(new String[]{"config/template.xml","config/datasource.xml"});
        JdbcTemplate j=(JdbcTemplate) context.getBean("jdbcTemplate");
        for (int i = 1; i <=data.length/5; i++) {
            j.update("insert into city(cityname,cityCode,citysuoxie,citysx,citymail) values(?,?,?,?,?)",new Object[]{data[i*5-5],data[i*5-4],data[i*5-3],data[i*5-2],data[i*5-1]});
        }
    }
}
