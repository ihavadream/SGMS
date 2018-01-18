package com.sqc.goods;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.Media;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Show extends JFrame implements ActionListener{
	/**
	 * 类的序列号标识SVUID  -方便标识类，跟踪类（自动生成长整数long）
	 */
	private static final long serialVersionUID = -8555005295491133459L;

	/**
	 * 类的序列化标识  -是默认值1L（长整数）
	 * private static final long serialVersionUID = 1L;
	 */
		//1. 要继承窗体JFrame-放置组件，完成交互
		//2.实现接口ActionListener-动作监听，实现功能
		//3.解决类的序列化标识UID问题，设置长整型常量
		//4.添加未实现的方法 -Add unImplements Methods
		//5.设计界面，安排各个组件，呈现窗体视觉效果
		//5.1定义各种组件,建议同步完成初始化-用构造方法
	
		//5.1.1两个面板 
	    //JPanel jp1,jp2;未初始化
	    JPanel jp1=new JPanel();//实例化
	    JPanel jp2=new JPanel();
	    
		//5.1.2四个按钮
	    JButton jb1=new JButton("查询");
	    JButton jb2=new JButton("增加");
	    JButton jb3=new JButton("修改");
	    JButton jb4=new JButton("删除");
	    
		//5.1.3一个文本框-限定长度为8个汉字，16个字符
	    JTextField jtf1=new JTextField(16);
	    
		//5.1.4两个标签
	    JLabel jl1=new JLabel("请输入商品名称：");
	    JLabel jl2=new JLabel("请选择操作分类：");
	    
	    
	    //5.1.5一套表格处理的组件（一个模型，一个表，一个滚动面板）
	    MyModel mm,nn;//数据模型  用户补充此类
	    JTable jt;//表格对象  继承窗体类JFrame
	    JScrollPane jsp;//滚动面板对象  继承窗体类JFrame
	    //通过以上三个对象，完成表格结构与数据的处理
	    //三个对象有先后顺序：模型-表-滚动面板  
	    
	public static void main(String[] args) {
		Show me=new Show();//通过与类名相同的方法，执行此类，调用构造方法，就可以打开主窗口
	
	}
	//为方便程序打开主窗口，显示界面，编写构造方法
	public Show(){
		//构造方法，完成初始化
		//类中各组件也可定义在此处
		//给4个按钮监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		
		//添加组件到第一个面板
		//3个组件：标签1+文本框+查询按钮
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);
		
		//添加组件到第一个面板
		//4个组件：标签2+后3个按钮
		jp2.add(jl2);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//将以上两个面板按南北布局放置
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		//在窗体中呈现表格
		mm=new MyModel();//实例化
		nn=new MyModel();
		jt=new JTable(mm);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		//将滚动面板中的表格加到窗体中
		//jt.add(mm);
		
		
		//设计系统主窗口界面
		this.setSize(800,600);
		this.setTitle("智慧商品管理系统 SGMS V1.0");
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//默认关闭时，窗体退出
		//this.setLocation(null);//相对位置为空，自动居中
		
		//呈现窗体
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//重写动作执行方法，响应活动事件
		// 按钮动作监听与响应事件
		if(e.getSource()==jb1){
			//监听到按钮来源是第一个“查询”
			//System.out.println("你点击了查询");
			//跟踪用户什么值
			String names=this.jtf1.getText().trim();//获取用户在文本框中输入的值
			//System.out.println("你输入了"+names);
			
			//在新的对话框或面板中输出
			//JOptionPane.showInputDialog("mmm");
			
			String sql2="select * from goods where name='"+names+"'";
			//调用模型类，创建新对象,更新表结构与数据
			mm=new MyModel(sql2);
			//根据新模型重建新表
			jt.setModel(mm);
			
		}
		if(e.getSource()==jb2){
//			MyInsertDiag mid=new MyInsertDiag(this, "增加商品数据", true);
//			mm=new MyModel();
//			jt.setModel(mm);
			
			//调用增加的窗体类，创建窗口，实例化
			MyInsert mi=new MyInsert("增加商品信息");
			mm=new MyModel();
			jt.setModel(mm);
		}
		
		else if(e.getSource()==jb3){//修改
			//修改前要知道删除哪一条记录
			int rowNum=this.jt.getSelectedRow();//获得选中的行数
			//如果行数为-1，则没有选中，可以提示
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "您没有选中行!");
				return;
			}
			int id=(Integer) mm.getValueAt(rowNum, 0);
			
			MyUpdateDiag mu=new MyUpdateDiag(mm,rowNum);
			nn=new MyModel();
			jt.setModel(nn);
		}
		
		else if(e.getSource()==jb4){//删除
			//删除前要知道删除哪一条记录
			int rowNum=this.jt.getSelectedRow();//获得选中的行数
			//如果行数为-1，则没有选中，可以提示
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "您没有选中行!");
				return;
			}
			int id=(Integer) mm.getValueAt(rowNum, 0);
			
			String driver="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="xishi";
			String password="123456";

			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;

			String sql5="delete from goods where id=?";
			
			try {
				Class.forName(driver);
				try {
					conn=DriverManager.getConnection(url,user,password);
					ps=conn.prepareStatement(sql5);
					ps.setInt(1, id);
					ps.executeUpdate();
					//this.dispose();
					
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
			MyDeleteDiag md=new MyDeleteDiag();
			mm=new MyModel();
			jt.setModel(mm);
		}
	}

}
