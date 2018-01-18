package com.sqc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

//完成数据查询
public class SelectData {
	 //提前定义各种变量、对象
	//定义驱动，并初始化
	static String driver="oracle.jdbc.driver.OracleDriver";
	//统一资源定位符并初始化，4要素
	static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	//定义用户并初始化，第5要素
	static String user="xishi";
	//定义密码并初始化，第6要素
	static String password="123456";
	 //补充定义三个对象
	static Connection conn=null;//连接对象，初始化为空
	static Statement st=null;//语句对象，初始化为空
	static ResultSet rs=null;//结果集对象，初始化为空
	//补充定义sql语句
	static String sql1="select * from goods";
	
	//按六步法则解决问题
	public static void main(String[] args) {
		//第一步，加载驱动
		try {
			Class.forName(driver);
			System.out.println("加载驱动成功！");
		} catch (ClassNotFoundException e) {
			//捕获异常1
			System.out.println("驱动加载失败！");
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("甲骨文连接成功！");
		} catch (SQLException e) {
			//捕获异常2
			System.out.println("甲骨文安装失败！");
			e.printStackTrace();
		}
		
		//第三步：创建语句，产生对象st
		try {
			st=conn.createStatement();
			System.out.println("创建语句成功!");
		} catch (SQLException e) {
			//捕获异常3
			System.out.println("创建语句失败！");
			e.printStackTrace();
		}
		
		//第四步：执行语句，查询后产生对象rs
		
		try {
			rs=st.executeQuery(sql1);
			System.out.println("执行查询成功！结果如下");
		} catch (SQLException e) {
			//捕获异常4
			System.out.println("查询失败！");
			e.printStackTrace();
		}
		
		//第五步：输出结果
		//遍历结果集rs，循环输出结果（每一条记录）
		try {
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+
						"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
		} catch (SQLException e) {
			//捕获异常5
			e.printStackTrace();
		}
		
		//第六步：关闭对象
		//释放占用的系统资源-CPU/MEMORY
		//要逆向处理（conn-rst-rs     rs-st-conn）
		try {
			if(rs!=null){
				rs.close();//关闭结果集对象
				System.out.println("结果集对象已关闭");
			}
			if(st!=null){
				st.close();//关闭结果集对象
				System.out.println("语句对象已关闭");
			}
			if(conn!=null){
				conn.close();//关闭结果集对象
				System.out.println("连接对象已关闭");
			}
			
		} catch (SQLException e) {
			//捕获异常6
			e.printStackTrace();
		}
		
	}
	
}
