package arithmetic;

import bean.BMenu;
import bean.BOrder;

import bean.PhoneMenu;
import bean.ShipInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    static BMenu bMenu;
    static ShipInfo shipInfo;

    static{
        //菜品信息是店铺管理人员app里设置的
        bMenu=new BMenu();
        //定位菜品打折信息，和月度统计财务报表
        bMenu.setBShipNo("No001");
        bMenu.setBMenuId("m001");
        bMenu.setBMenuName("羊肉汤");
        bMenu.setBPrice(12.35);

        //设置店铺信息
        shipInfo=new ShipInfo();
        shipInfo.setShipNo("b01");
        shipInfo.setShipName("天上人间饭庄");
    }

    public BOrder getLMenu(final BOrder lOrder){
        /**
         * 模拟吃饭的食客app，前端传过来的数据
         */
        PhoneMenu phoneMenu=new PhoneMenu();
        //前端传过来菜品id
        phoneMenu.setPmenuId("m001");
        phoneMenu.setNum(2);



        //--------模拟菜品结束--------------------


        List<PhoneMenu> phoneMenuList=new ArrayList<>();
        phoneMenuList.add(phoneMenu);


        BOrder bOrder=new BOrder();
        bOrder.setPMenuList(phoneMenuList);
        //食客扫二维码自带信息
        bOrder.setPTableId("第3桌");
        //食客扫二维码自带信息
        bOrder.setShipNo("b01");
        //订单号,这玩意递增生成即可
        bOrder.setOrderNum(014);




        //打印小发票用于食客支付
        List<PhoneMenu> mlist=bOrder.getPMenuList();
        Double sumPri=0.0;
        for (int i = 0; i < mlist.size() ; i++) {
            //数据库关联。此处只是演示  根据bOrder.getShipNo()在店铺信息表查找店铺名称
            System.out.println("欢迎光临 "+ shipInfo.getShipName());
            System.out.println("您的订单号为：" + bOrder.getOrderNum());
            System.out.println("您的桌号为："+bOrder.getPTableId());
            //根据这个菜单id和店铺id 在bmenu里找  mlist.get(i).getPmenuId()
            System.out.println("菜品   数量  单价    价格");
            System.out.println(bMenu.getBMenuName()+"  "+mlist.get(i).getNum()+"    "+bMenu.getBPrice()+"  "+ bMenu.getBPrice()*mlist.get(i).getNum());
            sumPri+=bMenu.getBPrice()*mlist.get(i).getNum();
        }

        System.out.println("总计：              "+ sumPri);

        return null;
    }


    public static void main(String[] args) {
        OrderService orderService=new OrderService();
        //假装里面有参数
        orderService.getLMenu(null);
    }



}
