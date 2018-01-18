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
//1.继承窗体类
//2.实现接口，借助Interface接口ActionListener
//3.生成类的序列标识号（默认或非默认值-长整数）
//4.实现接口中的方法(未实现，要手工重写）
//5.提前定义变量、对象、组件（可同步完成实例化）
	//5.1组件6+6+2
	
	JLabel jl1=new JLabel("编号：");
	JLabel jl2=new JLabel("品名：");
	JLabel jl3=new JLabel("价格：");
	JLabel jl4=new JLabel("品牌：");
	JLabel jl5=new JLabel("颜色：");
	JLabel jl6=new JLabel("产地：");
	
	//6个文本框
	JTextField jtf1=new JTextField(20);
	JTextField jtf2=new JTextField(20);
	JTextField jtf3=new JTextField(20);
	JTextField jtf4=new JTextField(20);
	JTextField jtf5=new JTextField(20);
	JTextField jtf6=new JTextField(20);
	
	//2个按钮
	JButton jbok=new JButton("添加");
	JButton jbno=new JButton("取消");
	
	JPanel jp1=new JPanel();//放6个标签
	JPanel jp2=new JPanel();//放6个文本框
	JPanel jp3=new JPanel();//放3个面板
	
	//5.2提前定义数据库中的常见向量
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String user="xishi";
	String password="123456";
	
	//提前定义数据库中的常见对象
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//提前定义sql语句
	String sql3="insert into goods values(?,?,?,?,?,?)";
	
	//6.将组件摆放好，并显示出来，设计布局Layout
	//构造方法
		public MyInsert(String title){
				super(title);  
				//设置布局
		  		jp1.setLayout(new GridLayout(6, 1));
		  		jp2.setLayout(new GridLayout(6, 1));
		 
		  		//面板jp1放6个标签
				jp1.add(jl1);
				jp1.add(jl2);
				jp1.add(jl3);
				jp1.add(jl4);
				jp1.add(jl5);
				jp1.add(jl6);
				
				//面板jp2放6个文本框
				jp2.add(jtf1);
				jp2.add(jtf2);
				jp2.add(jtf3);
				jp2.add(jtf4);
				jp2.add(jtf5);
				jp2.add(jtf6);
				
				//面板jp3放两个按钮
				jp3.add(jbok);
				jp3.add(jbno);
				
				//给按钮设计监听**********
				jbok.addActionListener(this);
				jbno.addActionListener(this);
		
				this.add(jp1,BorderLayout.WEST);
				this.add(jp2,BorderLayout.EAST);
				this.add(jp3,BorderLayout.SOUTH);
				
				this.setTitle("增加数据的窗体");
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);//默认关闭时，窗体退出
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
					
					//增加完数据后关闭
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
