package com.image.base;

import com.image.enumType.MediaTypeEnum;
import com.image.enumType.ReturnTypeEnum;
import lombok.Data;

/**
 * @author jared
 * lombok
 * @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 @Setter：注解在属性上；为属性提供 setting 方法
 @Getter：注解在属性上；为属性提供 getting 方法
 @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
 @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 */
@Data
public class BaseRequest {
    /**
     * 输出图片方式：
     * url:http地址（默认方式）
     * base64:base编码
     * stream:直接返回图片
      */
    private String outType;
    /**
     * 返回图片类型
     * jpg|png|webp|gif
     */
    private String mediaType;

    public ReturnTypeEnum returnType(){
        return ReturnTypeEnum.getEnum(outType);
    }

    public MediaTypeEnum mediaType(){
        return MediaTypeEnum.getEnum(mediaType);
    }
}
