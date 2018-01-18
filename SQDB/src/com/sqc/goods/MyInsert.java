package com.sqc.goods;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyInsert extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
//1.�̳д�����
//2.ʵ�ֽӿڣ�����Interface�ӿ�ActionListener
//3.����������б�ʶ�ţ�Ĭ�ϻ��Ĭ��ֵ-��������
//4.ʵ�ֽӿ��еķ���(δʵ�֣�Ҫ�ֹ���д��
//5.��ǰ��������������������ͬ�����ʵ������
	//5.1���6+6+2
	
	JLabel jl1=new JLabel("��ţ�");
	JLabel jl2=new JLabel("Ʒ����");
	JLabel jl3=new JLabel("�۸�");
	JLabel jl4=new JLabel("Ʒ�ƣ�");
	JLabel jl5=new JLabel("��ɫ��");
	JLabel jl6=new JLabel("���أ�");
	
	//6���ı���
	JTextField jtf1=new JTextField(20);
	JTextField jtf2=new JTextField(20);
	JTextField jtf3=new JTextField(20);
	JTextField jtf4=new JTextField(20);
	JTextField jtf5=new JTextField(20);
	JTextField jtf6=new JTextField(20);
	
	//2����ť
	JButton jbok=new JButton("���");
	JButton jbno=new JButton("ȡ��");
	
	JPanel jp1=new JPanel();//��6����ǩ
	JPanel jp2=new JPanel();//��6���ı���
	JPanel jp3=new JPanel();//��3�����
	
	//5.2��ǰ�������ݿ��еĳ�������
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String user="xishi";
	String password="123456";
	
	//��ǰ�������ݿ��еĳ�������
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//��ǰ����sql���
	String sql3="insert into goods values(?,?,?,?,?,?)";
	
	//6.������ڷźã�����ʾ��������Ʋ���Layout
	//���췽��
		public MyInsert(String title){
				super(title);  
				//���ò���
		  		jp1.setLayout(new GridLayout(6, 1));
		  		jp2.setLayout(new GridLayout(6, 1));
		 
		  		//���jp1��6����ǩ
				jp1.add(jl1);
				jp1.add(jl2);
				jp1.add(jl3);
				jp1.add(jl4);
				jp1.add(jl5);
				jp1.add(jl6);
				
				//���jp2��6���ı���
				jp2.add(jtf1);
				jp2.add(jtf2);
				jp2.add(jtf3);
				jp2.add(jtf4);
				jp2.add(jtf5);
				jp2.add(jtf6);
				
				//���jp3��������ť
				jp3.add(jbok);
				jp3.add(jbno);
				
				//����ť��Ƽ���**********
				jbok.addActionListener(this);
				jbno.addActionListener(this);
		
				this.add(jp1,BorderLayout.WEST);
				this.add(jp2,BorderLayout.EAST);
				this.add(jp3,BorderLayout.SOUTH);
				
				this.setTitle("�������ݵĴ���");
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Ĭ�Ϲر�ʱ�������˳�
				this.setLocationRelativeTo(null);
				this.setAlwaysOnTop(true);
				this.setSize(600,400);
				this.setBounds(0, 0, 600, 400);
				this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e2) {
		if(e2.getSource()==jbok){
			try {
				Class.forName(driver);
				try {
					conn=DriverManager.getConnection(url,user,password);
					ps=conn.prepareStatement(sql3);
					ps.setString(1, jtf1.getText());
					ps.setString(2, jtf2.getText());
					ps.setString(3, jtf3.getText());
					ps.setString(4, jtf4.getText());
					ps.setString(5, jtf5.getText());
					ps.setString(6, jtf6.getText());
					ps.executeUpdate();
					
					//���������ݺ�ر�
					this.dispose();
					
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
		if(e2.getSource()==jbno){
			this.dispose();
		}
	}

}
