package com.sqc.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQConn {
	public static void main(String[] args) {
		//��һ������������
		String driver="oracle.jdbc.driver.OracleDriver";
		//����������url-4��Ҫ��              user 1��Ҫ��           password 1��Ҫ��
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";//������Դͳһ��λ�����ҵ��׹������ݿ�
		//4��Ҫ�أ��������͡����������˿ڡ����ݿ�
		//���ط�������localhost ��127.0��0.1
		String user="scott";//�����û�����������ʼ��
		String password="123456";//�����������������ʼ����
		try {
			Class.forName(driver);
			System.out.println("oracle�������سɹ�����");
		} catch (ClassNotFoundException e) {
			System.out.println("oracle��������ʧ�ܣ���");
			e.printStackTrace();
		}
		//�ڶ�������������
		//�ؼ���������Ҫ�أ�4+1+1
		try {
			DriverManager.getConnection(url, user, password);
			System.out.println("�׹������ӳɹ�������");
		} catch (SQLException e) {
			System.out.println("�׹������ӳ���������Ҫ�أ���");
			e.printStackTrace();
		}
	}

}
