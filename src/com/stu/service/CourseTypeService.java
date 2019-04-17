package com.stu.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.CourseType;
import com.stu.bean.Paging;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�CourseTypeService 
 * �������� �γ���������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��7�� ����3:22:36
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��7�� ����3:22:36
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��7��
 */
public class CourseTypeService extends DBConnection{
	private int result=0;
	
	/**
	 * �γ�����ѯ����
	* @Title: selectCourseType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cyp
	* @param @return    �趨�ļ� 
	* @return List    ��������  ���ش洢�γ�������ļ���
	* @throws
	 */
	public List selectCourseType(CourseType cyp,Paging page) {
		//�����洢�γ�������ļ���
		List<CourseType> CorTypeList=new ArrayList<CourseType>();
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT coursetype.*,COUNT(courseinfo.coursetypeid) FROM coursetype");
		sb.append(" LEFT JOIN courseinfo ON coursetype.coursetypeid=courseinfo.coursetypeid where 1=1");
		//�����ʦ������Ʋ�Ϊ�գ������׷��
		if(CheckStr.isEmpty(cyp.getCoursetypename())) {
			sb.append(" and coursetype.coursetypename like '%"+cyp.getCoursetypename()+"%'");
		}
		sb.append(" GROUP BY coursetype.coursetypeid");
		if(page!=null) {
			sb.append(" limit ?,?");
		}
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//������ѯ�����
			while(rs.next()) {
				CourseType cy=new CourseType();
				cy.setCoursetypeid(rs.getInt(1)+"");
				cy.setCoursetypename(rs.getString(2));
				cy.setCourseNum(rs.getInt(3)+"");
				CorTypeList.add(cy);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return CorTypeList;
	}
	public int selectCourseTypeCount(CourseType cyp) {
		int flag=0;
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM coursetype WHERE 1=1");
		//�����ʦ������Ʋ�Ϊ�գ������׷��
		if(CheckStr.isEmpty(cyp.getCoursetypename())) {
			sb.append(" and coursetype.coursetypename like '%"+cyp.getCoursetypename()+"%'");
		}
		
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//������ѯ�����
			if(rs.next()) {
				flag=rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return flag;
	}
	/**
	 * ��ʦ�����ӷ���
	* @Title: addCourseType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cyp
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0���ʧ�ܣ�������ӳɹ�
	* @throws
	 */
	public int addCourseType(CourseType cyp) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("insert into coursetype(coursetypename) values(?)");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setString(1, cyp.getCoursetypename());
			//ִ���������
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * 
	 * �༭ҳ����Ⱦ����
	* @Title: XRCoursetype 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param courseTypeId
	* @param @return    �趨�ļ� 
	* @return List    �������� 
	* @throws
	 */
	public List XRCoursetype(String courseTypeId) {
		List<CourseType> csyList=new ArrayList<CourseType>();
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("select * from coursetype where coursetypeid=?");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(courseTypeId));
			rs=pst.executeQuery();
			while(rs.next()) {
				CourseType cy=new CourseType();
				cy.setCoursetypeid(rs.getInt(1)+"");
				cy.setCoursetypename(rs.getString(2));
				csyList.add(cy);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return csyList;
	}
	/**
	 *�༭��ʦ���ķ���
	* @Title: editCourseType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cyp
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editCourseType(CourseType cyp) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("update coursetype set coursetypename=? where coursetypeid=?");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setString(1, cyp.getCoursetypename());
			pst.setInt(2, Integer.parseInt(cyp.getCoursetypeid()));
			//ִ���޸����
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return result;
	}
	/**
	 * ��ʦ���ɾ������
	* @Title: detCourseType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cypId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0��ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detCourseType(String cypId) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("delete from coursetype where coursetypeid=?");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(cypId));
			//ִ��ɾ�����
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		
		return result;
	}
}
