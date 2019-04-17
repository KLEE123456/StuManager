package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Classinfo;
import com.stu.dbconn.DBConnection;
/**
 * ѧ������ѯ������
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�StuCountService 
 * �������� 
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��11�� ����7:35:41
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��11�� ����7:35:41
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��11��
 */
public class StuCountService extends  DBConnection{
	/**
	 * ���༶ѧ������ѯ����
	* @Title: selectStuCount 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @return    �趨�ļ� 
	* @return List    �������� 
	* @throws
	 */
	 public List selectStuCount() {
		 List<Classinfo> clsSumList=new ArrayList<Classinfo>();
		 conn=getDBconn();
		 StringBuffer sb=new StringBuffer();
		 sb.append("SELECT classinfo.classinfoname,classinfo.classinfosum FROM classinfo");
		 try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Classinfo cls=new Classinfo();
				cls.setClassinfoname(rs.getString(1));
				cls.setClassinfosum(rs.getInt(2)+"");
				clsSumList.add(cls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		 return clsSumList;
	 }
	 /**
	  *���༶��������ѯ����
	 * @Title: selectStuMale 
	 * @Description: TODO(������һ�仰�����������������) 
	 * @param @return    �趨�ļ� 
	 * @return List    �������� 
	 * @throws
	  */
	 public List selectStuMale() {
		 List<Classinfo> MaleList=new ArrayList<Classinfo>();
		 conn=getDBconn();
		 StringBuffer sb=new StringBuffer();
		 sb.append("SELECT count(*) FROM studentinfo");
		 sb.append(" WHERE classinfoid IN (SELECT classinfoid FROM classinfo) AND studentinfo.stdinfosex='��'");
		 sb.append(" GROUP BY studentinfo.classinfoid");
		 try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Classinfo cls=new Classinfo();
				cls.setClsMaleNum(rs.getInt(1)+"");
				MaleList.add(cls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		 return MaleList;
	 }
	 /**
	  * ��ѯ���༶Ů������ʵ�ַ���
	 * @Title: selectStuFemale 
	 * @Description: TODO(������һ�仰�����������������) 
	 * @param @return    �趨�ļ� 
	 * @return List    �������� 
	 * @throws
	  */
	 public List selectStuFemale() {
		 List<Classinfo> FeMaleList=new ArrayList<Classinfo>();
		 conn=getDBconn();
		 StringBuffer sb=new StringBuffer();
		 sb.append("SELECT count(*) FROM studentinfo");
		 sb.append(" WHERE classinfoid IN (SELECT classinfoid FROM classinfo) AND studentinfo.stdinfosex='Ů'");
		 sb.append(" GROUP BY studentinfo.classinfoid");
		 try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Classinfo cls=new Classinfo();
				cls.setClsFeMaleNum(rs.getInt(1)+"");
				FeMaleList.add(cls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		 return FeMaleList;
	 }
}
