package com.aits.utils;

import com.aits.entity.Good;
import com.aits.entity.Header;
import com.aits.entity.Person;
import com.aits.entity.XStreamDemo;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

public class XstreamDemo {
    public static void main(String[] args) {
        XStream xstream = new XStream();
        //将别名与xml名字相对应
        //xstream.alias("Person", Person.class);

        xstream.alias("XStreamDemo", XStreamDemo.class);
        // xstream.alias("headers",List.class);
        //  xstream.alias("header", List.class);
        xstream.alias("Header", Header.class);
        // xstream.alias("goods",List.class);
        //    xstream.alias("Good",Good.class);

        //Person person= (Person) xstream.fromXML("<Person><name>673</name><address>上海市</address><age>27</age></Person>");
        //  String xsm="<XStreamDemo><id>x1</id><name>报文1</name><Header><id>h1</id><hName>头部1</hName><Good><id>g1</id><gName>商品1</gName><price>22</price></Good><Good>" +
        //         "<id>g2</id><gName>商品2</gName><price>111</price></Good></Header>" +
        //         "<Header><id>h2</id><hName>头部2</hName><Good><id>g3</id><gName>商品1-1</gName><price>23</price></Good></Header></XStreamDemo>";

        // String xsm="<XStreamDemo><id>111</id><header><id>222</id></header></XStreamDemo>";
        //XStreamDemo xStreamDemo= (XStreamDemo) xstream.fromXML(xsm);

        //System.out.println(xStreamDemo.getId());
        //System.out.println(xStreamDemo);
        XStreamDemo x = new XStreamDemo();
        x.setId("1");
        x.setName("你好");
        Header h = new Header();
        h.setHName("qqq");
        h.setId("111");
        List<Header> list = new ArrayList<>();
        Header h2 = new Header();
        h2.setHName("www");
        h2.setId("222");
        list.add(h);
        list.add(h2);
        x.setHeader(list);
        String haha = xstream.toXML(x);

        XStreamDemo x2= (XStreamDemo) xstream.fromXML(haha);
        System.out.println(haha);
        System.out.println(x2);
    }
}
