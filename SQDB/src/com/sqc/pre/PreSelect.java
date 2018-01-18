package com.sqc.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreSelect {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="xishi";
		String password="123456";
		
		Connection conn=null;
		//static Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql1="select * from goods where price>?";
		
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		ps=conn.prepareStatement(sql1);
		ps.setDouble(1, 100.00);
		rs=ps.executeQuery();//给预编译sql语句占位符赋值
		while(rs.next()){
			System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDouble("price")+"\t"+
					rs.getString("brand")+"\t"+rs.getString("color")+"\t"+rs.getString("place"));
		}
		if(rs!=null){
			rs.close();
		}
		if(ps!=null){
			ps.close();
		}
		if(conn!=null){
			conn.close();
		}
	}

}
