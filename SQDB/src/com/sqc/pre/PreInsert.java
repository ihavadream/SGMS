package com.sqc.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreInsert {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="xishi";
		String password="123456";
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int flag=0;
		String sql1="insert into goods values(?,?,?,?,?,?)";
		
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		ps=conn.prepareStatement(sql1);
		
		ps.setInt(1, 5000);
		ps.setString(2, "鲤鱼");
		ps.setDouble(3, 60.00);
		ps.setString(4, "活跃");
		ps.setString(5, "红");
		ps.setString(6, "骆马湖");
		flag=ps.executeUpdate();
		
		if(flag>0){
			System.out.println("用ps预编译sql语句增加成功！");
		}
		else{
			System.out.println("失败！");
		}
		if(ps!=null){
			ps.close();
		}
		if(conn!=null){
			conn.close();
		}
	}

}
