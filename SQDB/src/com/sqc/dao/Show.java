package com.sqc.dao;
//完成DAO技术封装方法的调用，实现功能
public class Show {
	public static void main(String[] args) {
		//实例化数据库工具类DBUtil-new对象
		DBUtil db=new DBUtil();
		db.getConn();//调用封装的连接方法
		//db.selects();//调用封装好的方法
		//db.selectsAll();
		db.inserts();
		//db.updates();
		//db.deletes();
		db.getClose();//调用封装的关闭方法
	}
}
