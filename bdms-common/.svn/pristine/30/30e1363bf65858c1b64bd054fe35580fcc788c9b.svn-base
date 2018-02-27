package com.bdms.common.bean;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class BeanUtils {
 //对变量进行赋值和获取//////////////////////////////////
	int a,b,c;

public int getA() {
	return a;
}

public void setA(int a) {
	this.a = a;
}

public int getB() {
	return b;
}

public void setB(int b) {
	this.b = b;
}

public int getC() {
	return c;
}

public void setC(int c) {
	this.c = c;
}
	//进行连接数据库操作
private static Connection getConnection() {
    String url = "jdbc:mysql://localhost:3306/guestbook";
    String username = "root";
    String password = "hicc";
    Connection conn = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return conn;
}

}
