package com.sqc.goods;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
//��д�������ݵĶԻ������ǰ��̨���ݽ���
public class MyInsertDiag extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
//�̳жԻ�����JDialog������JFrame
//ʵ�ֶ��������ӿڣ�������ɽ���
	
//1.��صı�����������ǰ����
	//6����ǩ������ǰʵ����
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
	
	//3�����
	JPanel jp1=new JPanel();//��6����ǩ
	JPanel jp2=new JPanel();//��6���ı���
	JPanel jp3=new JPanel();//��3�����
	
	
//2.����������ݵĽ��棨��ȷ���ø��������
	//�Ի��������3������������Frame,���� String��ģʽModal-ģʽ���ģʽ����
	public MyInsertDiag(Frame owner,String title,boolean modal){
	//��ƴ��๹�췽������ɳ�ʼ��
	//���ø��෽��
		super(owner,title,modal);
	
	//���ò��֣���6����ǩ
		jp1.setLayout(new GridLayout(6,1));//6��1�����񲼾ַű�ǩ
		jp2.setLayout(new GridLayout(6,1));//6��1�����񲼾ַ��ı���
		
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
		
		//�������벢��ʾ�����һ�����Ḳ�ǣ�
//		this.add(jp1);
//		this.add(jp2);
//		this.add(jp3);
		
		//������岼�֣�����Ƕ�ס�����
		//ʹ�ñ߿򲼾�
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.EAST);
		this.add(jp3,BorderLayout.SOUTH);
		
		//�������
		setSize(300,300);
		setVisible(true);
	}
	
//3.ͨ���׹������ݴ���������������ݲ���
	@Override
	public void actionPerformed(ActionEvent e2) {
		// ��д������������ȡ�û���Ϊ��������Ӧ
		if(e2.getSource()==jbok){
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
			String sql3="insert into goods values(?,?,?,?,?,?)";
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
