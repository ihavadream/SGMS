package com.sqc.goods;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;

public class MyDeleteDiag extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public MyDeleteDiag(){}//无参构造函数
	
	public MyDeleteDiag(Integer id){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// 重写方法，响应
		
	}	

}
