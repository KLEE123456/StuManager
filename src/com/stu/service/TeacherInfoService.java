package com.stu.service;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�TeacherInfoService 
 * ����������ʦ��Ϣ������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��1�� ����11:11:49
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��1�� ����11:11:49
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��1��
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.bean.TeacherInfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�TeacherInfoService 
 * �������� ��ʦ������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��1�� ����11:23:30
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��1�� ����11:23:30
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��1��
 */
public class TeacherInfoService extends DBConnection{
	private int result=0;
	/**
	 * ��ʦ��ѯ����
	* @Title: selectTeachInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param teach
	* @param @return    �趨�ļ� 
	* @return List    �������� 
	* @throws
	 */
	public List selectTeachInfo(TeacherInfo teach,Paging page) {
		//������Ž�ʦ����ļ��϶���
		List<TeacherInfo> TeachInfoList=new ArrayList<TeacherInfo>();
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL
		sb.append("SELECT teachinfo.*,teachtype.teachtypename,depinfo.depinfoname,(SELECT count(*) FROM teachcourse ");
		sb.append(" JOIN studentcourse ON teachcourse.teachcourseid=studentcourse.teachcourseid");
		sb.append(" JOIN studentinfo ON studentcourse.studentinfoid=studentinfo.stdinfoid");
		sb.append(" JOIN classinfo ON studentinfo.classinfoid=classinfo.classinfoid where teachcourse.teachinfoid=teachinfo.teachinfoid) cnum FROM teachtype");
		sb.append(" JOIN teachinfo ON teachinfo.teachtypeid=teachtype.teachtypeid");
		sb.append(" JOIN depinfo ON teachinfo.depinfoid=depinfo.depinfoid WHERE 1=1");
		//�ж�ԺϵID�Ƿ�Ϊ�գ���Ϊ������� ������׷��
		if(CheckStr.isEmpty(teach.getDepinfoid())) {
			sb.append(" and depinfo.depinfoid="+teach.getDepinfoid());
		}
		//�жϽ�ʦ�����Ƿ�Ϊ�գ���Ϊ�����������׷��
		if(CheckStr.isEmpty(teach.getTeachinfoname())) {
			sb.append(" and teachinfo.teachinfoname LIKE '%"+teach.getTeachinfoname()+"%'");
		}
		//�жϽ�ʦID�Ƿ�Ϊ�գ���Ϊ�����������׷��
		if(CheckStr.isEmpty(teach.getTeachinfoid())) {
			sb.append(" and teachinfo.teachinfoid="+teach.getTeachinfoid());
		}
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
			//������ѯ�����
			while(rs.next()) {
				TeacherInfo tch=new TeacherInfo();
				tch.setTeachinfoid(rs.getInt(1)+"");
				tch.setTeachtypeid(rs.getInt(2)+"");
				tch.setDepinfoid(rs.getInt(3)+"");
				tch.setTeachinfocode(rs.getString(4));
				tch.setTeachinfoname(rs.getString(5));
				tch.setTeachinfosex(rs.getString(6));
				tch.setTeachknowl(rs.getString(7));
				tch.setTeachinfodeg(rs.getString(8));
				tch.setTeachinfospec(rs.getString(9));
				tch.setTeachinfotitle(rs.getString(10));
				tch.setTeachinformk(rs.getString(11));
				tch.setTeachtypename(rs.getString(12));
				tch.setDepinfoname(rs.getString(13));
				tch.setClsNum(rs.getString(14));
				//����ʦ������뼯��
				TeachInfoList.add(tch);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return TeachInfoList;
	}
	public int selectTeachInfoCount(TeacherInfo teach) {
		int flag=0;
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL
		sb.append("SELECT COUNT(*) FROM teachinfo WHERE 1=1");
		//�ж�ԺϵID�Ƿ�Ϊ�գ���Ϊ������� ������׷��
		if(CheckStr.isEmpty(teach.getDepinfoid())) {
			sb.append(" and depinfo.depinfoid="+teach.getDepinfoid());
		}
		//�жϽ�ʦ�����Ƿ�Ϊ�գ���Ϊ�����������׷��
		if(CheckStr.isEmpty(teach.getTeachinfoname())) {
			sb.append(" and teachinfo.teachinfoname LIKE '%"+teach.getTeachinfoname()+"%'");
		}
		//�жϽ�ʦID�Ƿ�Ϊ�գ���Ϊ�����������׷��
		if(CheckStr.isEmpty(teach.getTeachinfoid())) {
			sb.append(" and teachinfo.teachinfoid="+teach.getTeachinfoid());
		}
		
		try {
			//��ȡԤ�������
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
	 * ��ʦ����ѯ����
	* @Title: selectTeachType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @return    �趨�ļ� 
	* @return List    �������� 
	* @throws
	 */
	public List selectTeachType() {
		//��Ž�ʦ��𼯺�
		List<TeachType> tyList=new ArrayList<TeachType>();
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL
		sb.append(" SELECT * FROM teachtype");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			while(rs.next()) {
				TeachType teachType=new TeachType();
				teachType.setTeachtypeid(rs.getInt(1)+"");
				teachType.setTeachtypename(rs.getString(2));
				//��������뼯����
				tyList.add(teachType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return tyList;
	}
	
	/**
	 * ��ʦ��Ϣ��ӷ���
	* @Title: addTeachInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param tchInfo
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����0���ʧ�ܣ�����ɹ�
	* @throws
	 */
	public int addTeachInfo(TeacherInfo tchInfo) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL
		sb.append(" insert into teachinfo(teachtypeid,depinfoid,teachinfocode,teachinfoname,teachinfosex,teachknowl,teachinfodeg,teachinfospec,teachinfotitle,teachinformk)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?)");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(tchInfo.getTeachtypeid()));
			pst.setInt(2, Integer.parseInt(tchInfo.getDepinfoid()));
			pst.setString(3, tchInfo.getTeachinfocode());
			pst.setString(4, tchInfo.getTeachinfoname());
			pst.setString(5, tchInfo.getTeachinfosex());
			pst.setString(6, tchInfo.getTeachknowl());
			pst.setString(7, tchInfo.getTeachinfodeg());
			pst.setString(8, tchInfo.getTeachinfospec());
			pst.setString(9, tchInfo.getTeachinfotitle());
			pst.setString(10, tchInfo.getTeachinformk());
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
	 * �༭��ʦ��Ϣ����
	* @Title: editTeachInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param tchInfo
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editTeachInfo(TeacherInfo tchInfo) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append(" update teachinfo set teachtypeid=?,depinfoid=?,teachinfocode=?,teachinfoname=?,");
		sb.append("teachinfosex=?,teachknowl=?,teachinfodeg=?,teachinfospec=?,teachinfotitle=?,teachinformk=?");
		sb.append(" where teachinfoid=?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(tchInfo.getTeachtypeid()));
			pst.setInt(2, Integer.parseInt(tchInfo.getDepinfoid()));
			pst.setString(3, tchInfo.getTeachinfocode());
			pst.setString(4, tchInfo.getTeachinfoname());
			pst.setString(5, tchInfo.getTeachinfosex());
			pst.setString(6, tchInfo.getTeachknowl());
			pst.setString(7, tchInfo.getTeachinfodeg());
			pst.setString(8, tchInfo.getTeachinfospec());
			pst.setString(9, tchInfo.getTeachinfotitle());
			pst.setString(10, tchInfo.getTeachinformk());
			pst.setInt(11, Integer.parseInt(tchInfo.getTeachinfoid()));
			//ִ���޸�SQL���
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * ��ʦ��Ϣɾ������
	* @Title: detTeachInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param tchId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detTeachInfo(String tchId) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��Sql���
		StringBuffer sb=new StringBuffer();
		sb.append(" delete from  teachinfo where teachinfoid=?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(tchId));
			//ִ��SQL���
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * ��ʦ���ż�ⷽ��
	* @Title: doAjaxCKCode 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param code
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����1��ʾ�����Ѵ��ڣ�0�򲻴���
	* @throws
	 */
	public int doAjaxCKCode(String code) {
		int b=0;
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from teachinfo where teachinfocode=?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setString(1, code);
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			if(rs.next()) {
				b=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return b;
	}
}
