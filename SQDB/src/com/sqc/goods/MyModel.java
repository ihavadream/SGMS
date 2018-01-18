package com.sqc.goods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//1.ģ����̳г���ı��ģ��
//2.���������л���ʶUID
//3.���δʵ�ֵķ�����3�����������������������н���ֵ��
//4.��ǰ�������и��ֱ���������
//5.��д���췽�����Զ��巽������ɳ�ʼ����
 
	//����������overload -��������ͬ�������ͻ����������ͬ
	//��������дoverride-��������ͬ�������ͻ����������ͬ�����ܲ�ͬ
	
	//��ǰ������������-�����ڶ�̬����-�������е�ֵ
	//Vector rowData,columnData;//��������������
	Vector rowData=new Vector();//������ʵ����
	Vector columnData=new Vector();
	
	//��ǰ�������ݿ��еĳ�������
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String user="xishi";
	String password="123456";
	
	//��ǰ�������ݿ��еĳ�������
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//��ǰ����sql���
	String sql1="select * from goods";
	//String sql2="select * from goods where name=?";
	//��ǰ�涨�������еĶ���-�ַ���-�б���
	
	
	public MyModel() {
		//���췽��1 -�޲���
		//�����б���
		//��󲹳�Ļ�ȡ�б��ⷽ����֮��Ӧ��
		columnData.add("���");
		columnData.add("����");
		columnData.add("�۸�");
		columnData.add("Ʒ��");
		columnData.add("��ɫ");
		columnData.add("����");
		
		try {
			Class.forName(driver);
			try {
				conn=DriverManager.getConnection(url,user,password);
				ps=conn.prepareStatement(sql1);
				rs=ps.executeQuery();
				while(rs.next()){
					Vector my=new Vector();//������ʱ�������洢ÿ�и��е�ֵ
					my.add(rs.getInt(1));//��ý�����еĵ�һ�е�ֵ-���id-����һ�е�ֵ��ʱ�ӵ�����my��
					my.add(rs.getString(2));
					my.add(rs.getDouble(3));
					my.add(rs.getString(4));
					my.add(rs.getString(5));
					my.add(rs.getString(6));//��6��ֵ��Ӧ���ݱ�6���У�ͳһ���뵽����My
					rowData.add(my);//����ʱ������my���ݼӵ���������
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rs!=null){
						rs.close();
					}
					if(ps!=null){
						ps.close();
					}
				if(conn!=null){
						conn.close();
					}
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MyModel(String sql2){
				//���췽��2 -�в���-����������overload
				//�����б���
				//��󲹳�Ļ�ȡ�б��ⷽ����֮��Ӧ��
				columnData.add("���");
				columnData.add("����");
				columnData.add("�۸�");
				columnData.add("Ʒ��");
				columnData.add("��ɫ");
				columnData.add("����");
				
				try {
					Class.forName(driver);
					try {
						conn=DriverManager.getConnection(url,user,password);
						ps=conn.prepareStatement(sql2);
						//ps.setString(1, sql);
						rs=ps.executeQuery();
						while(rs.next()){
							Vector my=new Vector();//������ʱ�������洢ÿ�и��е�ֵ
							my.add(rs.getInt(1));//��ý�����еĵ�һ�е�ֵ-���id-����һ�е�ֵ��ʱ�ӵ�����my��
							my.add(rs.getString(2));
							my.add(rs.getDouble(3));
							my.add(rs.getString(4));
							my.add(rs.getString(5));
							my.add(rs.getString(6));
							rowData.add(my);//����ʱ������my���ݼӵ���������
						}
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						try {
							if(rs!=null){
								rs.close();
							}
							if(ps!=null){
								ps.close();
							}
						if(conn!=null){
								conn.close();
							}
						}catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	}
	
	@Override
	public int getColumnCount() {
		// ��д��������ȡ����
		return this.columnData.size();
	}

	@Override
	public int getRowCount() {
		//��д��������ȡ����
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// ��д��������ȡ������н��涨λ��ֵ-���Ԫ��ȡֵ
		return ((Vector)(this.rowData.get(rowIndex))).get(columnIndex);
	}
	//����һ����������ȡ����ʾ�б���
	public String getColumnName(int column){
		return (String) this.columnData.get(column);//���������л�õ��е�ֵ����ǿ��ת��Ϊ�ַ���
		
	}
}
