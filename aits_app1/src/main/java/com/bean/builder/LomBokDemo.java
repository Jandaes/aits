package com.bean.builder;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LomBokDemo {

    private String name;
    private Integer age;
    public static void main(String[] args) {
        LomBokDemo wyz = LomBokDemo.builder().age(12).name("张三").build();
        System.out.println(wyz);

    }

}
