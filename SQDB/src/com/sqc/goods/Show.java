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
	 * ������кű�ʶSVUID  -�����ʶ�࣬�����ࣨ�Զ����ɳ�����long��
	 */
	private static final long serialVersionUID = -8555005295491133459L;

	/**
	 * ������л���ʶ  -��Ĭ��ֵ1L����������
	 * private static final long serialVersionUID = 1L;
	 */
		//1. Ҫ�̳д���JFrame-�����������ɽ���
		//2.ʵ�ֽӿ�ActionListener-����������ʵ�ֹ���
		//3.���������л���ʶUID���⣬���ó����ͳ���
		//4.���δʵ�ֵķ��� -Add unImplements Methods
		//5.��ƽ��棬���Ÿ�����������ִ����Ӿ�Ч��
		//5.1����������,����ͬ����ɳ�ʼ��-�ù��췽��
	
		//5.1.1������� 
	    //JPanel jp1,jp2;δ��ʼ��
	    JPanel jp1=new JPanel();//ʵ����
	    JPanel jp2=new JPanel();
	    
		//5.1.2�ĸ���ť
	    JButton jb1=new JButton("��ѯ");
	    JButton jb2=new JButton("����");
	    JButton jb3=new JButton("�޸�");
	    JButton jb4=new JButton("ɾ��");
	    
		//5.1.3һ���ı���-�޶�����Ϊ8�����֣�16���ַ�
	    JTextField jtf1=new JTextField(16);
	    
		//5.1.4������ǩ
	    JLabel jl1=new JLabel("��������Ʒ���ƣ�");
	    JLabel jl2=new JLabel("��ѡ��������ࣺ");
	    
	    
	    //5.1.5һ�ױ����������һ��ģ�ͣ�һ����һ��������壩
	    MyModel mm,nn;//����ģ��  �û��������
	    JTable jt;//������  �̳д�����JFrame
	    JScrollPane jsp;//����������  �̳д�����JFrame
	    //ͨ����������������ɱ��ṹ�����ݵĴ���
	    //�����������Ⱥ�˳��ģ��-��-�������  
	    
	public static void main(String[] args) {
		Show me=new Show();//ͨ����������ͬ�ķ�����ִ�д��࣬���ù��췽�����Ϳ��Դ�������
	
	}
	//Ϊ�������������ڣ���ʾ���棬��д���췽��
	public Show(){
		//���췽������ɳ�ʼ��
		//���и����Ҳ�ɶ����ڴ˴�
		//��4����ť����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		
		//����������һ�����
		//3���������ǩ1+�ı���+��ѯ��ť
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);
		
		//����������һ�����
		//4���������ǩ2+��3����ť
		jp2.add(jl2);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//������������尴�ϱ����ַ���
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		//�ڴ����г��ֱ��
		mm=new MyModel();//ʵ����
		nn=new MyModel();
		jt=new JTable(mm);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		//����������еı��ӵ�������
		//jt.add(mm);
		
		
		//���ϵͳ�����ڽ���
		this.setSize(800,600);
		this.setTitle("�ǻ���Ʒ����ϵͳ SGMS V1.0");
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Ĭ�Ϲر�ʱ�������˳�
		//this.setLocation(null);//���λ��Ϊ�գ��Զ�����
		
		//���ִ���
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//��д����ִ�з�������Ӧ��¼�
		// ��ť������������Ӧ�¼�
		if(e.getSource()==jb1){
			//��������ť��Դ�ǵ�һ������ѯ��
			//System.out.println("�����˲�ѯ");
			//�����û�ʲôֵ
			String names=this.jtf1.getText().trim();//��ȡ�û����ı����������ֵ
			//System.out.println("��������"+names);
			
			//���µĶԻ������������
			//JOptionPane.showInputDialog("mmm");
			
			String sql2="select * from goods where name='"+names+"'";
			//����ģ���࣬�����¶���,���±�ṹ������
			mm=new MyModel(sql2);
			//������ģ���ؽ��±�
			jt.setModel(mm);
			
		}
		if(e.getSource()==jb2){
//			MyInsertDiag mid=new MyInsertDiag(this, "������Ʒ����", true);
//			mm=new MyModel();
//			jt.setModel(mm);
			
			//�������ӵĴ����࣬�������ڣ�ʵ����
			MyInsert mi=new MyInsert("������Ʒ��Ϣ");
			mm=new MyModel();
			jt.setModel(mm);
		}
		
		else if(e.getSource()==jb3){//�޸�
			//�޸�ǰҪ֪��ɾ����һ����¼
			int rowNum=this.jt.getSelectedRow();//���ѡ�е�����
			//�������Ϊ-1����û��ѡ�У�������ʾ
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "��û��ѡ����!");
				return;
			}
			int id=(Integer) mm.getValueAt(rowNum, 0);
			
			MyUpdateDiag mu=new MyUpdateDiag(mm,rowNum);
			nn=new MyModel();
			jt.setModel(nn);
		}
		
		else if(e.getSource()==jb4){//ɾ��
			//ɾ��ǰҪ֪��ɾ����һ����¼
			int rowNum=this.jt.getSelectedRow();//���ѡ�е�����
			//�������Ϊ-1����û��ѡ�У�������ʾ
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "��û��ѡ����!");
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
