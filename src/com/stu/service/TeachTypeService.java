package com.stu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�TeachTypeService 
 * �������� ��ʦ��������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��7�� ����2:41:02
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��7�� ����2:41:02
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��7��
 */
public class TeachTypeService extends DBConnection{
	
	private int result=0;
	
	/**
	 * ��ʦ����ѯ����
	* @Title: selectTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param typ
	* @param @return    �趨�ļ� 
	* @return List    ��������  ���ش洢��ʦ������ļ���
	* @throws
	 */
	public List selectTchType(TeachType typ,Paging page) {
		//�����洢��ʦ������ļ���
		List<TeachType> tchTypeList=new ArrayList<TeachType>();
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT teachtype.*,COUNT(teachinfo.teachtypeid) FROM teachtype");
		sb.append(" left JOIN teachinfo ON teachtype.teachtypeid=teachinfo.teachtypeid WHERE 1=1");
		//�����ʦ������Ʋ�Ϊ�գ������׷��
		if(CheckStr.isEmpty(typ.getTeachtypename())) {
			sb.append(" and teachtypename like '%"+typ.getTeachtypename()+"%'");
		}
		sb.append(" GROUP BY teachtype.teachtypeid");
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
				TeachType ty=new TeachType();
				ty.setTeachtypeid(rs.getInt(1)+"");
				ty.setTeachtypename(rs.getString(2));
				ty.settNum(rs.getInt(3)+"");
				
				tchTypeList.add(ty);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return tchTypeList;
	}
	public int selectTchTypeCount(TeachType typ) {
		int flag=0;
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM teachtype WHERE 1=1");
		//�����ʦ������Ʋ�Ϊ�գ������׷��
		if(CheckStr.isEmpty(typ.getTeachtypename())) {
			sb.append(" and teachtypename like '%"+typ.getTeachtypename()+"%'");
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
	* @Title: addTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param typ
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0���ʧ�ܣ�������ӳɹ�
	* @throws
	 */
	public int addTchType(TeachType typ) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("insert into teachtype(teachtypename) values(?)");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setString(1, typ.getTeachtypename());
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
	* @Title: XRTchtype 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param tchTypeId
	* @param @return    �趨�ļ� 
	* @return List    �������� 
	* @throws
	 */
	public List XRTchtype(String tchTypeId) {
		List<TeachType> typList=new ArrayList<TeachType>();
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("select * from teachtype where teachtypeid=?");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(tchTypeId));
			rs=pst.executeQuery();
			while(rs.next()) {
				TeachType ty=new TeachType();
				ty.setTeachtypeid(rs.getInt(1)+"");
				ty.setTeachtypename(rs.getString(2));
				typList.add(ty);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return typList;
	}
	/**
	 *�༭��ʦ���ķ���
	* @Title: editTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param typ
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editTchType(TeachType typ) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("update teachtype set teachtypename=? where teachtypeid=?");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setString(1, typ.getTeachtypename());
			pst.setInt(2, Integer.parseInt(typ.getTeachtypeid()));
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
	* @Title: detTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param typId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0��ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detTchType(String typId) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("delete from teachtype where teachtypeid=?");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(typId));
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
