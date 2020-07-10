package com.smart.dao.impl;

import com.smart.dao.CartsDao;
import com.smart.dao.UserDao;
import com.smart.entity.Carts;
import com.smart.entity.Shop;
import com.smart.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CartsDaoImpl implements CartsDao {


//    public List<Carts> selectAll(int pageSize, int size) {
//        Connection connection = null;
//        ResultSet rs = null;
//        List<Carts> cartsList = null;
//        // 解决SQL
//        PreparedStatement ps = null;
//        String sql = "SELECT *  FROM carts WHERE is_del=0 LIMIT ?,?";
//
//        try {
//            connection = DbUtils.getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, pageSize);
//            ps.setInt(2, size);
//            rs = ps.executeQuery();
//            if (rs != null) {
//                int cartsId = rs.getInt("carts_id");
//                int uid = rs.getInt("uid");
//                int shopId = rs.getInt("shop_id");
//                int num = rs.getInt("num");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DbUtils.close(connection, ps, rs);
//        }
//        return cartsList;
//    }

    /**
     * 多表查询方式实现
     * select  *
     * from carts c left join  shop s  on c.shop_id = s.shop_id
     * where c.uid = 1 and c.is_del=0
     * limit 0,10
     *
     * @param pageSize
     * @param size
     * @return
     */
    @Override
    public List<Carts> selectAll(int uid, int pageSize, int size) {
        Connection connection = null;
        ResultSet rs = null;
        List<Carts> cartsList = null;
        // 解决SQL
        PreparedStatement ps = null;
        String sql = "select c.carts_id,c.uid,c.shop_id,c.num,s.name,s.price,s.img,s.num stock  from carts c left join  shop s  on c.shop_id = s.shop_id " +
                "where c.uid = ? and c.is_del=0 " +
                " limit ?, ?";
        try {
            connection = DbUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, pageSize);
            ps.setInt(3, size);
            rs = ps.executeQuery();
            if (rs != null) {
                cartsList = new ArrayList<>();
                while (rs.next()) {
                    int cartsId = rs.getInt("carts_id");
                    int userId = rs.getInt("uid");
                    int shopId = rs.getInt("shop_id");
                    int num = rs.getInt("num");
                    //购物车对象
                    Carts carts = new Carts(cartsId, num, shopId, userId);
                    // 商品的信息
                    Shop shop = new Shop();
                    shop.setName(rs.getString("name"));
                    shop.setImg(rs.getString("img"));
                    shop.setShopId(shopId);
                    shop.setNum(rs.getInt("stock"));
                    shop.setPrice(rs.getBigDecimal("price"));
                    carts.setShop(shop);
                    cartsList.add(carts);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.close(connection, ps, rs);
        }
        return cartsList;
    }

    /**
     * 添加操作
     * 如果已经存在修改的操作
     *
     * @param uid shop_id
     *            num
     * @return
     */
    @Override
    public int insertCarts(int uid, int shopId, int num) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 查询商品是否已经存在用户的购物车
        //  如果存在在修改购物车的商品数量
        // 如果不存在创建该条记录

        String sql = "select  *  from  carts where uid=? and shop_id=?";
        try {
            connection = DbUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, shopId);
            rs = ps.executeQuery();
            if (rs.next()){
                // update 修改操作

            }else {
                // 增加操作
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.close(connection, ps, rs);
        }

        return 0;
    }

    @Override
    public int updateNum(int cartsId, int num) {
        return 0;
    }

    @Override
    public int deleteCarts(int cartsId) {
        return 0;
    }
}
