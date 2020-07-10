package com.smart.dao.impl;

import com.smart.dao.ShopDao;
import com.smart.entity.Carts;
import com.smart.entity.Shop;
import com.smart.utils.DbUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 */
public class ShopDaoImpl implements ShopDao {
    @Override
    public Shop selectShopById(int shopId) {
        Shop shop = null;
        Connection connection = null;
        ResultSet rs = null;
        // 解决SQL
        PreparedStatement ps = null;
        String sql = "SELECT *  FROM shop WHERE  shop_id=? AND is_del=0 ";
        try {
            connection = DbUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, shopId);
            rs = ps.executeQuery();
            if (rs != null) {
                shop = new Shop();
                int sid = rs.getInt("shop_id");
                String name = rs.getString("name");
                String img = rs.getString("img");
                int num = rs.getInt("num");
                BigDecimal price = rs.getBigDecimal("price");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop;
    }



    /**
     * 根据商品的ID查询商品信息
     */


}
