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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyUpdateDiag extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
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
	JButton jbok=new JButton("ȷ���޸�");
	JButton jbno=new JButton("ȡ��");
	
	//3�����
	JPanel jp1=new JPanel();//��6����ǩ
	JPanel jp2=new JPanel();//��6���ı���
	JPanel jp3=new JPanel();//��3�����
	
    
	public MyUpdateDiag(){}
	
	public MyUpdateDiag(MyModel mm,int rowNum){
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
		
		//������岼�֣�����Ƕ�ס�����
		//ʹ�ñ߿򲼾�
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.EAST);
		this.add(jp3,BorderLayout.SOUTH);
		
		//�������
		setSize(300,300);
		setVisible(true);
		this.setAlwaysOnTop(true);

		jtf1.setText((mm.getValueAt(rowNum, 0)).toString());
		jtf2.setText((mm.getValueAt(rowNum, 1)).toString());
		jtf3.setText((mm.getValueAt(rowNum, 2)).toString());
		jtf4.setText((mm.getValueAt(rowNum, 3)).toString());
		jtf5.setText((mm.getValueAt(rowNum, 4)).toString());
		jtf6.setText((mm.getValueAt(rowNum, 5)).toString());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbok){
			String driver="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="xishi";
			String password="123456";
			
			//��ǰ�������ݿ��еĳ�������
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			//��ǰ����sql���
			String sql4="update goods set name=?,price=?,brand=?,color=?,place=? where id=?";
			try {
				Class.forName(driver);
				try {
					conn=DriverManager.getConnection(url,user,password);
					ps=conn.prepareStatement(sql4);
					ps.setString(1, jtf2.getText());
					ps.setString(2, jtf3.getText());
					ps.setString(3, jtf4.getText());
					ps.setString(4, jtf5.getText());
					ps.setString(5, jtf6.getText());
					ps.setString(6, jtf1.getText());
					ps.executeUpdate();
					
					//�޸������ݺ�ر�
					this.dispose();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
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
					}catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
			} catch (ClassNotFoundException e3) {
				e3.printStackTrace();
			}
		}
		if(e.getSource()==jbno){
			this.dispose();
		}
	}

}
