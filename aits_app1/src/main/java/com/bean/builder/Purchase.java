package com.bean.builder;

/**
 * @author jared
 * 构造器模式
 */
public class Purchase {
    private final String shipNo;
    private final String menuId;
    private final String menuName;
    private final Double price;

    public static class Builder {
        private final String shipNo;
        private String menuId;
        private String menuName;
        private final Double price = 0.0;

        public Builder(String shipNo) {
            this.shipNo = shipNo;
        }

        public Builder menuId(String val) {
            menuId = val;
            return this;
        }

        public Builder menuName(String val){
            menuName=val;
            return this;
        }

        public Purchase build() {
            return new Purchase(this);
        }
    }

    private Purchase(Builder builder) {
        shipNo = builder.shipNo;
        menuId = builder.menuId;
        menuName = builder.menuName;
        price = builder.price;
    }

    public static void main(String[] args) {
        Purchase p=new Builder("S0001").menuId("11").menuName("宫保").build();

        System.out.println(p.menuName);
    }
}
