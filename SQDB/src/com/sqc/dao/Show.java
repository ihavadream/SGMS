package com.sqc.dao;
//���DAO������װ�����ĵ��ã�ʵ�ֹ���
public class Show {
	public static void main(String[] args) {
		//ʵ�������ݿ⹤����DBUtil-new����
		DBUtil db=new DBUtil();
		db.getConn();//���÷�װ�����ӷ���
		//db.selects();//���÷�װ�õķ���
		//db.selectsAll();
		db.inserts();
		//db.updates();
		//db.deletes();
		db.getClose();//���÷�װ�Ĺرշ���
	}
}