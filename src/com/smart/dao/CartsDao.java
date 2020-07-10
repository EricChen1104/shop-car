package com.smart.dao;

import com.smart.entity.Carts;

import java.util.List;

/**
 *
 */
public interface CartsDao {
    /**
     * 查询购物车信息
     */
    List<Carts> selectAll(int uid,int pageSize, int size);

    /**
     * 添加购物车
     */
    int insertCarts(int uid, int shopId, int num);

    /**
     * 更新购买商品的数量
     */
    int updateNum(int cartsId, int num);

    /**
     * 删除购物车记录
     */
    int deleteCarts(int cartsId);

}
