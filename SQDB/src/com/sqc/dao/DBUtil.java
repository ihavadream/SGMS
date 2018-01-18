package com.sqc.dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//ͨ�����ݿ⹤�����װ���ֹ���
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
public class DBUtil {
//��ǰ������ֱ���������
//���ݿⳣ������
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String user="xishi";
	String password="123456";
	
//JDBC�г�������
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int flag=0;
//�������SQL���
	String sql1="select * from goods";
	String sql2="select * from goods where name=?";
    String sql3="insert into goods values(?,?,?,?,?,?)";
    String sql4="update goods set name=?,price=?,brand=?,color=?,place=? where id=?";
    String sql5="delete from goods where id=?";
//��һ�����������ӷ���
	public Connection getConn() {
		//����͵���ʱҪ�����׳��쳣throws Exception
		try {
			Class.forName(driver);
			System.out.println("���������ɹ���");
			try {
				conn=DriverManager.getConnection(url, user, password);
				System.out.println("�׹������ݿ����ӳɹ���");
			} catch (SQLException e) {
				//����2-���ݿ��쳣
				System.out.println("�׹������ݿ�����ʧ�ܣ�");
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			//����1-�Ҳ���������쳣
			System.out.println("��������ʧ�ܣ�");
			e.printStackTrace();
		}
		return conn;//�������Ӷ���
	}
	
//�ڶ����������رշ���
	public void getClose(){
		try {
				if(rs!=null){
					rs.close();
					System.out.println("����������Ѿ��رգ�");
				}	
				if(ps!=null){
					ps.close();
					System.out.println("Ԥ��������Ѿ��رգ�");
				}
				if(conn!=null){
					conn.close();
					System.out.println("�������ݿ�����Ѿ��رգ�");
				}
			} catch (SQLException e) {
				//�������ݿ�SQL�쳣
				e.printStackTrace();
			}
		}

	
//��������������ѯ����	-����Ʒ��������
	public void selects() {
		//����������
		//��ͬ����ֱ�ӵ��ã��ڲ�ͬ������new�������
		getConn();//�������ӣ�ֱ�ӵ��÷�װ�õķ���
		try {
			ps=conn.prepareStatement(sql2);//��Ʒ����ѯ
			ps.setString(1, "�׾�");
			rs=ps.executeQuery();
			System.out.println("��Ʒ����ѯ�������");
			
			while(rs.next()){
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDouble("price")+"\t"+
						rs.getString("brand")+"\t"+rs.getString("color")+"\t"+rs.getString("place"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//getClose();//�رն���ֱ�ӵ��÷�װ�õķ���
		
	}
//���䣺��ѯ����
	public void selectsAll() {
		//����������
		//��ͬ����ֱ�ӵ��ã��ڲ�ͬ������new�������
		getConn();//�������ӣ�ֱ�ӵ��÷�װ�õķ���
		try {
			ps=conn.prepareStatement(sql1);
			rs=ps.executeQuery();
			System.out.println("��Ʒ����ѯ�������");
			while(rs.next()){
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDouble("price")+"\t"+
						rs.getString("brand")+"\t"+rs.getString("color")+"\t"+rs.getString("place"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//getClose();//�رն���ֱ�ӵ��÷�װ�õķ���
		
	}
	
//���ĸ����������ӷ���	
	public void inserts() {	
		try {
			ps=conn.prepareStatement(sql3);
			ps.setInt(1, 6);
			ps.setString(2, "����");
			ps.setDouble(3, 20);
			ps.setString(4, "����");
			ps.setString(5, "��");
			ps.setString(6, "����");
			flag=ps.executeUpdate();
			if(flag>0){
				System.out.println("���Ӽ�¼�ɹ���");
			}
			else{
				System.out.println("ʧ�ܣ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//������������޸ķ���	
	public void updates(){
		try {
			ps=conn.prepareStatement(sql4);
			ps.setString(1, "����");
			ps.setInt(2, 33);
			ps.setString(3, "����");
			ps.setString(4, "��");
			ps.setString(5, "����");
			ps.setInt(6, 5);
			flag=ps.executeUpdate();
			if(flag>0){
				System.out.println("��psԤ����sql�����³ɹ���");
			}
			else{
				System.out.println("ʧ�ܣ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//������������ɾ������	
	public void deletes(){
		try {
			ps=conn.prepareStatement(sql5);
			ps.setInt(1, 5);
			flag=ps.executeUpdate();
			if(flag>0){
				System.out.println("��psԤ����sql���ɾ���ɹ���");
			}
			else{
				System.out.println("ʧ�ܣ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
