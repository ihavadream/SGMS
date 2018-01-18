package com.sqc.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreUpdate {

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
		int flag=0;
		String sql1="update goods set price=80 where id=?";
		
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		ps=conn.prepareStatement(sql1);
		ps.setInt(1, 5);
		flag=ps.executeUpdate();
		if(flag>0){
			System.out.println("用ps预编译sql语句更新成功！");
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
