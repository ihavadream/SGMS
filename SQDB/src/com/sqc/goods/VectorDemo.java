package com.sqc.goods;

import java.io.IOException;
import java.util.Vector;

//��������-��̬���鼼����Ϊ����ɨƽ�����ϰ�
public class VectorDemo {
	//���������ӡ�ɾ�����޸ġ���ѯ
	public static void main(String[] args) {
		//1.������̬����������Vector Object
		Vector v=new Vector();
		
		//2.�������������Ӳ���
		v.add("�Ͼ�");
		v.add("��Ǩ");
		v.add("����");
		v.add("����");
		v.add("����");
		
		//3.��ѯ���������е�����-��̬�����Ԫ�أ�Ԫ�ظ����±��0��ʼ
		int n1=v.size();//�����Ķ������
		String n2=v.get(0).toString();//��ȡ�����еĵ�һ������//��objת�����ַ���������toString()
		System.out.println("�������ȣ�"+n1);
		System.out.println("������һ��ֵΪ��"+n2);
		//ȫ����������еĶ���ֵ
		System.out.println("�޸�ǰ����ֵΪ��");
		for(int i=0;i<v.size();i++){
			String m=v.get(i).toString();
			System.out.println(m+" ");
		}
		
		//4.�����������޸Ĳ���
		v.set(4, "����");//���������±�������ֵ
		System.out.println("�޸ĺ�����ֵΪ��");
		for(int i=0;i<v.size();i++){
			String m=v.get(i).toString();
			System.out.println(m+" ");
		}
		
		//5.����������ɾ������
		v.remove(3);//�Ƴ�������ָ����������
		System.out.println("ɾ��������ֵΪ��");
		for(int i=0;i<v.size();i++){
			String m=v.get(i).toString();
			System.out.println(m+" ");
		}
	}
}
