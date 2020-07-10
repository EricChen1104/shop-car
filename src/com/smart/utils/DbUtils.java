package com.smart.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ctrl + alt + t 快速处理异常
 * 1. 初始化数据库连接池
 * 2. 获取数据库连接对象
 * 3. 释放资源(关闭对象)
 */
public class DbUtils {
    public static final String DB_CONFIG = "db.properties";
    private static DataSource ds;

    static {
        Properties properties = new Properties();
        InputStream is = DbUtils.class.getClassLoader().getResourceAsStream(DB_CONFIG);
        try {
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("初始化连接池对象失败,请检查配置信息!");
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源关闭连接
     */
    public static void close(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("statment 对象关闭失败");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败");
            }
        }
    }

    /**
     * DAO  Data  Access  Object
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet!= null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection, statement);
    }

}
