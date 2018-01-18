package com.sqc.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQConn {
	public static void main(String[] args) {
		//第一步：加载驱动
		String driver="oracle.jdbc.driver.OracleDriver";
		//三个变量：url-4个要素              user 1个要素           password 1个要素
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";//定义资源统一定位符，找到甲骨文数据库
		//4个要素：驱动类型、服务器、端口、数据库
		//本地服务器：localhost 或127.0。0.1
		String user="scott";//定义用户变量，并初始化
		String password="123456";//定义密码变量，并初始化！
		try {
			Class.forName(driver);
			System.out.println("oracle驱动加载成功！！");
		} catch (ClassNotFoundException e) {
			System.out.println("oracle驱动加载失败！！");
			e.printStackTrace();
		}
		//第二步：创建连接
		//关键是掌握六要素：4+1+1
		try {
			DriverManager.getConnection(url, user, password);
			System.out.println("甲骨文链接成功啦！！");
		} catch (SQLException e) {
			System.out.println("甲骨文链接出错，请检查六要素！！");
			e.printStackTrace();
		}
	}

}
