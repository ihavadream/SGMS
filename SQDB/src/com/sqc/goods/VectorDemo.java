package com.sqc.goods;

import java.io.IOException;
import java.util.Vector;

//掌握向量-动态数组技术，为开发扫平技术障碍
public class VectorDemo {
	//向量的增加、删除、修改、查询
	public static void main(String[] args) {
		//1.创建动态的向量对象Vector Object
		Vector v=new Vector();
		
		//2.对向量进行增加操作
		v.add("南京");
		v.add("宿迁");
		v.add("徐州");
		v.add("苏州");
		v.add("扬州");
		
		//3.查询向量对象中的数据-动态数组的元素，元素个数下标从0开始
		int n1=v.size();//向量的对象个数
		String n2=v.get(0).toString();//获取向量中的第一个对象//将obj转换成字符串，调用toString()
		System.out.println("向量长度："+n1);
		System.out.println("向量第一个值为："+n2);
		//全部输出向量中的对象值
		System.out.println("修改前所有值为：");
		for(int i=0;i<v.size();i++){
			String m=v.get(i).toString();
			System.out.println(m+" ");
		}
		
		//4.对向量进行修改操作
		v.set(4, "无锡");//根据数组下标设置新值
		System.out.println("修改后所有值为：");
		for(int i=0;i<v.size();i++){
			String m=v.get(i).toString();
			System.out.println(m+" ");
		}
		
		//5.对向量进行删除操作
		v.remove(3);//移除向量中指定索引对象
		System.out.println("删除后所有值为：");
		for(int i=0;i<v.size();i++){
			String m=v.get(i).toString();
			System.out.println(m+" ");
		}
	}
}
