package com.sqc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class DeleteData {

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
		
		String sql="delete from goods where id=6000";
		
		
		//定义变量，跟踪增删改受影响的记录数
		int flag=0;//通过整数变量了解处理结果。
		
		//六步法处理数据
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		st=conn.createStatement();
		//st.executeQuery(sql1);//得到结果集rs
		flag=st.executeUpdate(sql);	//得到整数，有多少行受影响。
		//输出结果（增删改可省略结果查询）
		if(flag>0){
			System.out.println("删除的记录数："+flag);
		}
		else{
			System.out.println("数据删除失败！");
		}
		//rs.close();
		if(st!=null){
			st.close();
		}
		if(conn!=null){
			conn.close();
		}
		
	}

}
