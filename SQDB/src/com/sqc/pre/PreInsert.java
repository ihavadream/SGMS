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
		ps.setString(2, "����");
		ps.setDouble(3, 60.00);
		ps.setString(4, "��Ծ");
		ps.setString(5, "��");
		ps.setString(6, "�����");
		flag=ps.executeUpdate();
		
		if(flag>0){
			System.out.println("��psԤ����sql������ӳɹ���");
		}
		else{
			System.out.println("ʧ�ܣ�");
		}
		if(ps!=null){
			ps.close();
		}
		if(conn!=null){
			conn.close();
		}
	}

}
