package com.sqc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class UpdateData {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="xishi";
		String password="123456";
		
		Connection conn=null;
		Statement st=null;
		Result rs=null;
		int flag=0;
		
		String sql="update goods set name='口味虾' where id=6000";
		
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,password);
			st=conn.createStatement();
			flag=st.executeUpdate(sql);
			if(flag>0){
				System.out.println("更新成功的记录数为："+flag);
			}
			else {
				System.out.println("更新失败!");
			}
			if(st!=null){
				st.close();
			}
			if(conn!=null){
				conn.close();
			}
		} 


}
