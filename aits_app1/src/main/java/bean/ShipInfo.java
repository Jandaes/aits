package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shipInfo")
public class ShipInfo {
    /**
     * 店铺号
     */
    private String ShipNo;
    /**
     * 店铺名
     */
    private String ShipName;
    /**
     * 店铺地址
     */
    private String address;
    /**
     * 店家logo(提供一个自动截取图片的插件)
     */
    private String icoPath;

    /**
     * 展示店铺信息如，外景、内景，主要菜品
     */
    private String[] showImgPath;




}
