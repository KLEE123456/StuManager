package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.CourseInfo;
import com.stu.bean.Paging;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�CourseInfoService 
 * �������� �γ���Ϣ������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��7�� ����8:55:32
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��7�� ����8:55:32
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��7��
 */
public class CourseInfoService extends DBConnection{
	private int result=0;
	/**
	 * �γ���Ϣ��ѯ����
	* @Title: selectCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cInfo
	* @param @return    �趨�ļ� 
	* @return List    ��������   ���ش�ſγ�ʵ�����ļ���
	* @throws
	 */
	public List selectCourseInfo(CourseInfo cInfo,Paging page) {
		//������ſγ�ʵ�����ļ���
		List<CourseInfo> CInfoList=new ArrayList<CourseInfo>();
		conn=getDBconn();//��ȡ���ݿ�����
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("SELECT courseinfo.*,COUNT(teachcourse.courseinfoid), coursetype.coursetypename FROM coursetype");
		sb.append(" JOIN courseinfo ON coursetype.coursetypeid=courseinfo.coursetypeid");
		sb.append(" LEFT JOIN teachcourse ON courseinfo.courseinfoid=teachcourse.courseinfoid where 1=1");
		if(CheckStr.isEmpty(cInfo.getCourseinfoname())) {
			sb.append(" AND courseinfo.courseinfoname like '%"+cInfo.getCourseinfoname()+"%'");
		}
		if(CheckStr.isEmpty(cInfo.getCourseinfoid())) {
			sb.append(" and courseinfo.courseinfoid="+cInfo.getCourseinfoid());
		}
		sb.append(" GROUP BY courseinfo.courseinfoid");
		if(page!=null) {
			sb.append(" limit ?,?");
		}
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			while(rs.next()) {
				CourseInfo courseInfo=new CourseInfo();
				courseInfo.setCourseinfoid(rs.getInt(1)+"");
				courseInfo.setCoursetypeid(rs.getInt(2)+"");
				courseInfo.setCourseinfocode(rs.getString(3));
				courseInfo.setCourseinfoname(rs.getString(4));
				courseInfo.setCourseinfoproj(rs.getString(5));
				courseInfo.setCourseinforstper(rs.getInt(6)+"");
				courseInfo.setCourseinfopraper(rs.getInt(7)+"");
				courseInfo.setCourseinfocrehor(rs.getInt(8)+"");
				courseInfo.setCourseinformk(rs.getString(9));
				courseInfo.setTchNum(rs.getInt(10)+"");
				courseInfo.setCoursetypename(rs.getString(11));
				//���γ�ʵ�����׷�ӵ�����
				CInfoList.add(courseInfo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return CInfoList;
	}
	public int selectCourseInfoCount(CourseInfo cInfo) {
		int flag=0;
		conn=getDBconn();//��ȡ���ݿ�����
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("SELECT COUNT(*) FROM courseinfo WHERE 1=1");
		if(CheckStr.isEmpty(cInfo.getCourseinfoname())) {
			sb.append(" AND courseinfo.courseinfoname like '%"+cInfo.getCourseinfoname()+"%'");
		}
		if(CheckStr.isEmpty(cInfo.getCourseinfoid())) {
			sb.append(" and courseinfo.courseinfoid="+cInfo.getCourseinfoid());
		}
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			if(rs.next()) {
				flag=rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return flag;
	}
	/**
	 * ���γ̴��ŷ���
	* @Title: checkCode 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param code
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����0���Ų����ڣ�1���Ŵ���
	* @throws
	 */
	public int checkCode(String code) {
		int b=0;
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("select * from courseinfo where courseinfocode=?");
		
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setString(1, code);
			rs=pst.executeQuery();
			//������Ŵ��ڣ���b��ֵ����Ϊ1
			if(rs.next()) {
				b=1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return b;
	}
	/**
	 * �γ���������
	* @Title: addCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cInfo
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0����ʧ�ܣ����������ɹ�
	* @throws
	 */
	public int addCourseInfo(CourseInfo cInfo) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("insert into courseinfo(coursetypeid,courseinfocode,courseinfoname,courseinfoproj,courseinforstper,courseinfopraper,courseinfocrehor,courseinformk)");
		sb.append(" values(?,?,?,?,?,?,?,?)");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1,Integer.parseInt(cInfo.getCoursetypeid()));
			pst.setString(2, cInfo.getCourseinfocode());
			pst.setString(3, cInfo.getCourseinfoname());
			pst.setString(4,cInfo.getCourseinfoproj());
			pst.setInt(5, Integer.parseInt(cInfo.getCourseinforstper()));
			pst.setInt(6, Integer.parseInt(cInfo.getCourseinfopraper()));
			pst.setInt(7, Integer.parseInt(cInfo.getCourseinfocrehor()));
			pst.setString(8, cInfo.getCourseinformk());
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
	 * �γ̱༭����
	* @Title: editCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cInfo
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editCourseInfo(CourseInfo cInfo) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("update courseinfo set coursetypeid=?,courseinfocode=?,courseinfoname=?,courseinfoproj=?,courseinforstper=?");
		sb.append(",courseinfopraper=?,courseinfocrehor=?,courseinformk=?");
		sb.append(" where courseinfoid=?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(cInfo.getCoursetypeid()));
			pst.setString(2, cInfo.getCourseinfocode());
			pst.setString(3, cInfo.getCourseinfoname());
			pst.setString(4,cInfo.getCourseinfoproj());
			pst.setInt(5, Integer.parseInt(cInfo.getCourseinforstper()));
			pst.setInt(6, Integer.parseInt(cInfo.getCourseinfopraper()));
			pst.setInt(7, Integer.parseInt(cInfo.getCourseinfocrehor()));
			pst.setString(8, cInfo.getCourseinformk());
			pst.setInt(9, Integer.parseInt(cInfo.getCourseinfoid()));
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
	 * �γ���Ϣɾ������
	* @Title: detCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param courseId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detCourseInfo(String courseId) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("delete from courseinfo where courseinfoid=?");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(courseId));
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
