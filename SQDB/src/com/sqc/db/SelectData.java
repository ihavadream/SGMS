package com.sqc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

//������ݲ�ѯ
public class SelectData {
	 //��ǰ������ֱ���������
	//��������������ʼ��
	static String driver="oracle.jdbc.driver.OracleDriver";
	//ͳһ��Դ��λ������ʼ����4Ҫ��
	static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	//�����û�����ʼ������5Ҫ��
	static String user="xishi";
	//�������벢��ʼ������6Ҫ��
	static String password="123456";
	 //���䶨����������
	static Connection conn=null;//���Ӷ��󣬳�ʼ��Ϊ��
	static Statement st=null;//�����󣬳�ʼ��Ϊ��
	static ResultSet rs=null;//��������󣬳�ʼ��Ϊ��
	//���䶨��sql���
	static String sql1="select * from goods";
	
	//����������������
	public static void main(String[] args) {
		//��һ������������
		try {
			Class.forName(driver);
			System.out.println("���������ɹ���");
		} catch (ClassNotFoundException e) {
			//�����쳣1
			System.out.println("��������ʧ�ܣ�");
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("�׹������ӳɹ���");
		} catch (SQLException e) {
			//�����쳣2
			System.out.println("�׹��İ�װʧ�ܣ�");
			e.printStackTrace();
		}
		
		//��������������䣬��������st
		try {
			st=conn.createStatement();
			System.out.println("�������ɹ�!");
		} catch (SQLException e) {
			//�����쳣3
			System.out.println("�������ʧ�ܣ�");
			e.printStackTrace();
		}
		
		//���Ĳ���ִ����䣬��ѯ���������rs
		
		try {
			rs=st.executeQuery(sql1);
			System.out.println("ִ�в�ѯ�ɹ����������");
		} catch (SQLException e) {
			//�����쳣4
			System.out.println("��ѯʧ�ܣ�");
			e.printStackTrace();
		}
		
		//���岽��������
		//���������rs��ѭ����������ÿһ����¼��
		try {
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+
						"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
		} catch (SQLException e) {
			//�����쳣5
			e.printStackTrace();
		}
		
		//���������رն���
		//�ͷ�ռ�õ�ϵͳ��Դ-CPU/MEMORY
		//Ҫ������conn-rst-rs     rs-st-conn��
		try {
			if(rs!=null){
				rs.close();//�رս��������
				System.out.println("����������ѹر�");
			}
			if(st!=null){
				st.close();//�رս��������
				System.out.println("�������ѹر�");
			}
			if(conn!=null){
				conn.close();//�رս��������
				System.out.println("���Ӷ����ѹر�");
			}
			
		} catch (SQLException e) {
			//�����쳣6
			e.printStackTrace();
		}
		
	}
	
}
