package com.nutfreedom.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private Connection connection;

    // 1. เชื่อมต่อฐานข้อมูล
    public Database() {
        try {
            Class.forName(Config.DRIVER);
            connection = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. ดึงข้อมูล
    public List<Map<String, Object>> queryList(String sql) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            // 1. เตรียมคำสั่ง SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // 2. ประมวลผลคำสั่ง SQL
            ResultSet resultSet = preparedStatement.executeQuery();
            // 3. ดึงข้อมูล Metadata
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 4. นำข้อมูลใส่ใน Map
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    map.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
                }
                // 5. นำ Map เก็บใน List
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. เพิ่มข้อมูล
    public int add(String sql, Object... args) {
        int result = 0;
        try {
            // 1. เตรียมคำสั่ง SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // 2. เซ็ตค่า args
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);;
            }
            // 3. สั่งประมวลผลคำสั่ง SQL
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4. ปิดการเชื่อมต่อฐานข้อมูล
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
