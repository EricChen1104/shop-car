package com.smart;

import com.smart.dao.CartsDao;
import com.smart.dao.impl.CartsDaoImpl;
import com.smart.entity.Carts;
import sun.text.normalizer.UBiDiProps;

import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        list();
    }

    public static void list() {
        CartsDao dao = new CartsDaoImpl();
        List<Carts> carts = dao.selectAll(1, 0, 10);
        System.out.println(carts);
    }
}
