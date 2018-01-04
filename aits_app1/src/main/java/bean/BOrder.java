package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BOrder")
public class BOrder {
    /**
     * 商铺号
     */
    private String shipNo;

    /**
     * 订单id
     */
    private int orderNum;

    /**
     * 桌号（用来定位上菜桌号）
     */
    private String pTableId;
    /**
     * 所点菜品
     */
    private List<PhoneMenu> pMenuList;

    /**
     * 菜品总价格
     */
    private Double pSumPrice;


}
