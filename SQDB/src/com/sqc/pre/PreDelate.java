package com.sqc.pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreDelate {

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
		String sql1="delete from goods where id=?";
		
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		ps=conn.prepareStatement(sql1);
		ps.setInt(1, 5000);
		flag=ps.executeUpdate();
		if(flag>0){
			System.out.println("”√ps‘§±‡“Îsql”Ôæ‰…æ≥˝≥…π¶£°");
		}
		else{
			System.out.println(" ß∞‹£°");
		}
		if(ps!=null){
			ps.close();
		}
		if(conn!=null){
			conn.close();
		}
		
	}

}
