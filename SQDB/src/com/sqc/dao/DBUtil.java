package com.sqc.dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//通过数据库工具类封装各种功能
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
public class DBUtil {
//提前定义各种变量，对象
//数据库常见变量
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String user="xishi";
	String password="123456";
	
//JDBC中常见对象
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int flag=0;
//定义各种SQL语句
	String sql1="select * from goods";
	String sql2="select * from goods where name=?";
    String sql3="insert into goods values(?,?,?,?,?,?)";
    String sql4="update goods set name=?,price=?,brand=?,color=?,place=? where id=?";
    String sql5="delete from goods where id=?";
//第一个方法：连接方法
	public Connection getConn() {
		//定义和调用时要两次抛出异常throws Exception
		try {
			Class.forName(driver);
			System.out.println("加载驱动成功！");
			try {
				conn=DriverManager.getConnection(url, user, password);
				System.out.println("甲骨文数据库连接成功！");
			} catch (SQLException e) {
				//捕获2-数据库异常
				System.out.println("甲骨文数据库连接失败！");
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			//捕获1-找不到此类的异常
			System.out.println("加载驱动失败！");
			e.printStackTrace();
		}
		return conn;//返回连接对象
	}
	
//第二个方法：关闭方法
	public void getClose(){
		try {
				if(rs!=null){
					rs.close();
					System.out.println("结果集对象已经关闭！");
				}	
				if(ps!=null){
					ps.close();
					System.out.println("预编译语句已经关闭！");
				}
				if(conn!=null){
					conn.close();
					System.out.println("连接数据库对象已经关闭！");
				}
			} catch (SQLException e) {
				//捕获数据库SQL异常
				e.printStackTrace();
			}
		}

	
//第三个方法：查询方法	-按商品名称来查
	public void selects() {
		//按六步法则
		//在同类中直接调用，在不同类中用new对象调用
		getConn();//创建连接，直接调用封装好的方法
		try {
			ps=conn.prepareStatement(sql2);//按品名查询
			ps.setString(1, "白酒");
			rs=ps.executeQuery();
			System.out.println("按品名查询结果如下");
			
			while(rs.next()){
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDouble("price")+"\t"+
						rs.getString("brand")+"\t"+rs.getString("color")+"\t"+rs.getString("place"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//getClose();//关闭对象，直接调用封装好的方法
		
	}
//补充：查询所有
	public void selectsAll() {
		//按六步法则
		//在同类中直接调用，在不同类中用new对象调用
		getConn();//创建连接，直接调用封装好的方法
		try {
			ps=conn.prepareStatement(sql1);
			rs=ps.executeQuery();
			System.out.println("按品名查询结果如下");
			while(rs.next()){
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDouble("price")+"\t"+
						rs.getString("brand")+"\t"+rs.getString("color")+"\t"+rs.getString("place"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//getClose();//关闭对象，直接调用封装好的方法
		
	}
	
//第四个方法：增加方法	
	public void inserts() {	
		try {
			ps=conn.prepareStatement(sql3);
			ps.setInt(1, 6);
			ps.setString(2, "红枣");
			ps.setDouble(3, 20);
			ps.setString(4, "沭阳");
			ps.setString(5, "红");
			ps.setString(6, "沭阳");
			flag=ps.executeUpdate();
			if(flag>0){
				System.out.println("增加记录成功！");
			}
			else{
				System.out.println("失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//第五个方法：修改方法	
	public void updates(){
		try {
			ps=conn.prepareStatement(sql4);
			ps.setString(1, "红枣");
			ps.setInt(2, 33);
			ps.setString(3, "沭阳");
			ps.setString(4, "红");
			ps.setString(5, "沭阳");
			ps.setInt(6, 5);
			flag=ps.executeUpdate();
			if(flag>0){
				System.out.println("用ps预编译sql语句更新成功！");
			}
			else{
				System.out.println("失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//第六个方法：删除方法	
	public void deletes(){
		try {
			ps=conn.prepareStatement(sql5);
			ps.setInt(1, 5);
			flag=ps.executeUpdate();
			if(flag>0){
				System.out.println("用ps预编译sql语句删除成功！");
			}
			else{
				System.out.println("失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
