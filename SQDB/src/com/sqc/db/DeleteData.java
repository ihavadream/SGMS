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
		
		
		//���������������ɾ����Ӱ��ļ�¼��
		int flag=0;//ͨ�����������˽⴦������
		
		//��������������
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		st=conn.createStatement();
		//st.executeQuery(sql1);//�õ������rs
		flag=st.executeUpdate(sql);	//�õ��������ж�������Ӱ�졣
		//����������ɾ�Ŀ�ʡ�Խ����ѯ��
		if(flag>0){
			System.out.println("ɾ���ļ�¼����"+flag);
		}
		else{
			System.out.println("����ɾ��ʧ�ܣ�");
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
