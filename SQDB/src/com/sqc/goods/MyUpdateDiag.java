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
	JButton jbok=new JButton("确认修改");
	JButton jbno=new JButton("取消");
	
	//3个面板
	JPanel jp1=new JPanel();//放6个标签
	JPanel jp2=new JPanel();//放6个文本框
	JPanel jp3=new JPanel();//放3个面板
	
    
	public MyUpdateDiag(){}
	
	public MyUpdateDiag(MyModel mm,int rowNum){
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
		
		//调整面板布局，放置嵌套、覆盖
		//使用边框布局
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.EAST);
		this.add(jp3,BorderLayout.SOUTH);
		
		//处理界面
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
			
			//提前定义数据库中的常见对象
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			//提前定义sql语句
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
					
					//修改完数据后关闭
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
