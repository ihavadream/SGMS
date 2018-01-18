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
//编写增加数据的对话框，完成前后台数据交互
public class MyInsertDiag extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
//继承对话框类JDialog或窗体类JFrame
//实现动作监听接口，方便完成交互
	
//1.相关的变量、对象提前定义
	//6个标签，可提前实例化
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
	
	//3个面板
	JPanel jp1=new JPanel();//放6个标签
	JPanel jp2=new JPanel();//放6个文本框
	JPanel jp3=new JPanel();//放3个面板
	
	
//2.设计增加数据的界面（正确放置各种组件）
	//对话框设计有3个参数：窗体Frame,标题 String，模式Modal-模式或非模式窗口
	public MyInsertDiag(Frame owner,String title,boolean modal){
	//设计此类构造方法，完成初始化
	//调用父类方法
		super(owner,title,modal);
	
	//设置布局，放6个标签
		jp1.setLayout(new GridLayout(6,1));//6行1列网格布局放标签
		jp2.setLayout(new GridLayout(6,1));//6行1列网格布局放文本框
		
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
		
		//将面板加入并显示（最后一个面板会覆盖）
//		this.add(jp1);
//		this.add(jp2);
//		this.add(jp3);
		
		//调整面板布局，放置嵌套、覆盖
		//使用边框布局
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.EAST);
		this.add(jp3,BorderLayout.SOUTH);
		
		//处理界面
		setSize(300,300);
		setVisible(true);
	}
	
//3.通过甲骨文数据处理六步法完成数据插入
	@Override
	public void actionPerformed(ActionEvent e2) {
		// 重写监听方法，获取用户行为，进行响应
		if(e2.getSource()==jbok){
			//提前定义数据库中的常见向量
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
