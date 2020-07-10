package com.smart.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class Carts implements Serializable {
    private Integer cartsId;
    //购物车的购买数量
    private Integer num;
    private Integer shopId;
    private Integer uid;
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Carts() {
    }

    public Carts(Integer cartsId, Integer num, Integer shopId, Integer uid) {
        this.cartsId = cartsId;
        this.num = num;
        this.shopId = shopId;
        this.uid = uid;
    }

    public Integer getCartsId() {
        return cartsId;
    }

    public void setCartsId(Integer cartsId) {
        this.cartsId = cartsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
