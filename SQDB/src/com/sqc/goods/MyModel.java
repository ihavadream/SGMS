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
//1.模型类继承抽象的表格模型
//2.添加类的序列化标识UID
//3.添加未实现的方法（3个方法：行数、列数、行列交叉值）
//4.提前定义类中各种变量、对象
//5.编写构造方法或自定义方法，完成初始化。
 
	//方法的重载overload -方法名相同，但类型或参数个数不同
	//方法的重写override-方法名相同，但类型或参数个数相同，功能不同
	
	//提前定义两个向量-类似于动态数组-控制行列的值
	//Vector rowData,columnData;//行向量，列向量
	Vector rowData=new Vector();//定义且实例化
	Vector columnData=new Vector();
	
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
	String sql1="select * from goods";
	//String sql2="select * from goods where name=?";
	//提前规定列向量中的对象-字符串-列标题
	
	
	public MyModel() {
		//构造方法1 -无参数
		//控制列标题
		//最后补充的获取列标题方法与之对应的
		columnData.add("编号");
		columnData.add("名字");
		columnData.add("价格");
		columnData.add("品牌");
		columnData.add("颜色");
		columnData.add("产地");
		
		try {
			Class.forName(driver);
			try {
				conn=DriverManager.getConnection(url,user,password);
				ps=conn.prepareStatement(sql1);
				rs=ps.executeQuery();
				while(rs.next()){
					Vector my=new Vector();//创建临时向量，存储每行各列的值
					my.add(rs.getInt(1));//获得结果集中的第一列的值-编号id-将第一列的值临时加到向量my中
					my.add(rs.getString(2));
					my.add(rs.getDouble(3));
					my.add(rs.getString(4));
					my.add(rs.getString(5));
					my.add(rs.getString(6));//将6个值对应数据表6个列，统一加入到向量My
					rowData.add(my);//将临时的向量my数据加到行向量中
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
				//构造方法2 -有参数-方法的重载overload
				//控制列标题
				//最后补充的获取列标题方法与之对应的
				columnData.add("编号");
				columnData.add("名字");
				columnData.add("价格");
				columnData.add("品牌");
				columnData.add("颜色");
				columnData.add("产地");
				
				try {
					Class.forName(driver);
					try {
						conn=DriverManager.getConnection(url,user,password);
						ps=conn.prepareStatement(sql2);
						//ps.setString(1, sql);
						rs=ps.executeQuery();
						while(rs.next()){
							Vector my=new Vector();//创建临时向量，存储每行各列的值
							my.add(rs.getInt(1));//获得结果集中的第一列的值-编号id-将第一列的值临时加到向量my中
							my.add(rs.getString(2));
							my.add(rs.getDouble(3));
							my.add(rs.getString(4));
							my.add(rs.getString(5));
							my.add(rs.getString(6));
							rowData.add(my);//将临时的向量my数据加到行向量中
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
		// 重写方法，获取列数
		return this.columnData.size();
	}

	@Override
	public int getRowCount() {
		//重写方法，获取行数
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// 重写方法，获取表格行列交叉定位的值-表格单元格取值
		return ((Vector)(this.rowData.get(rowIndex))).get(columnIndex);
	}
	//补充一个方法：获取并显示列标题
	public String getColumnName(int column){
		return (String) this.columnData.get(column);//将列向量中获得的列的值对象强制转换为字符串
		
	}
}
