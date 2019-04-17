package com.stu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�DepInfoService 
 * �������� Ժϵ��Ϣ������
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����8:15:52
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����8:15:52
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class DepInfoService extends DBConnection{
	private int result=0;
	/**
	 * Ժϵ��Ϣ��ѯ
	* @Title: selectDepInfoList 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param deps
	* @param @return    �趨�ļ� 
	* @return List<Depinfo>    ��������  ���ز�ѯ����¼(ʵ��)
	* @throws
	 */
	public List<Depinfo> selectDepInfoList(Depinfo deps,Paging page){
		//���Ժϵ��Ϣ�ļ���
		List<Depinfo> depList=new ArrayList<Depinfo>();
		//�������ݿ�����
		conn=getDBconn();
		
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("select d.*," + 
				"(select count(*) from specilinfo p where p.depinfoid=d.depinfoid) spnum," + 
				"(SELECT COUNT(*) FROM specilinfo a JOIN classinfo b ON a.spilinfoId=b.spilinfoId WHERE a.depinfoid=d.depinfoid) classnum" + 
				" from depinfo d where 1=1 ");
		//����Ժϵ������Ϊ��ѯ����
		if(CheckStr.isEmpty(deps.getDepinfoname())) {
			sb.append(" and depinfoname like '%"+deps.getDepinfoname()+"%'");
		}
		//����ԺϵID��Ϊ��ѯ����
		if(CheckStr.isEmpty(deps.getDepinfoId())) {
			sb.append(" and depinfoid="+deps.getDepinfoId());
		}
		if(page!=null) {
			sb.append(" limit ?,?");
		}
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, (page.getPage()-1)*page.getPagesize());
			pst.setInt(2,page.getPagesize());
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			while(rs.next()) {
				Depinfo dep=new Depinfo();
				dep.setDepinfoId(rs.getInt(1)+"");
				dep.setDepInfoCode(rs.getString(2));
				dep.setDepinfoname(rs.getString(3));
				dep.setDepinfoPreOfTech(rs.getInt(4)+"");
				dep.setDepinfoAssTeach(rs.getInt(5)+"");
				dep.setSpnum(rs.getInt(6)+"");
				dep.setClassnum(rs.getInt(7)+"");
				//����ѯ���� ��¼��װ��ʵ����뼯����
				depList.add(dep);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		return depList;
	}
	public int selectDepInfoListCount(Depinfo deps){
		//�������ݿ�����
		conn=getDBconn();
		int flag=0;
		StringBuffer sb=new StringBuffer();
		//ƴ��SQL���
		sb.append("SELECT COUNT(*) FROM depinfo WHERE 1=1 ");
		//����Ժϵ������Ϊ��ѯ����
		if(CheckStr.isEmpty(deps.getDepinfoname())) {
			sb.append(" and depinfoname like '%"+deps.getDepinfoname()+"%'");
		}
		//����ԺϵID��Ϊ��ѯ����
		if(CheckStr.isEmpty(deps.getDepinfoId())) {
			sb.append(" and depinfoid="+deps.getDepinfoId());
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
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		return flag;
	}
	/**
	 * Ժϵ���
	* @Title: addDepInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param dep
	* @param @return    �趨�ļ� 
	* @return int    ��������    ����0���ʧ�ܣ�������ӳɹ�
	* @throws
	 */
	public int addDepInfo(Depinfo dep) {
		//�������ݿ�����
		conn=getDBconn();
		String ins="insert into depinfo(depinfocode,depinfoname,depinfopreoftech,depinfoassteach) values(?,?,?,?)";
		try {
			//��ȡԤ�������
			 pst=conn.prepareStatement(ins);
			//����ռλ��
			pst.setString(1, dep.getDepInfoCode());
			pst.setString(2,dep.getDepinfoname());
			pst.setString(3, dep.getDepinfoPreOfTech());
			pst.setString(4,  dep.getDepinfoAssTeach());
			//ִ��SQL���
			result=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		return result;
	}
	/**
	 * Ժϵ�޸�
	* @Title: updateDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param dep
	* @param @return    �趨�ļ� 
	* @return int   ��������   ����0����ʧ�ܣ�������³ɹ�
	* @throws
	 */
	public int updateDep(Depinfo dep) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String upd="update depinfo set depinfoname=?,depinfopreoftech=?,depinfoassteach=?"+
		"where depinfoid=?";
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(upd);
			//����վλ��
			pst.setString(1, dep.getDepinfoname());
			pst.setString(2, dep.getDepinfoPreOfTech());
			pst.setString(3,  dep.getDepinfoAssTeach());
			pst.setInt(4,Integer.parseInt(dep.getDepinfoId()));
			//ִ���޸����
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		return result;
	}
	/**
	 * ɾ��Ժϵ��Ϣ
	* @Title: detDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param depId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detDep(String depId) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String del="delete from depinfo where depinfoid=?";
		try {
			//��ȡԤ�������
			PreparedStatement ps=conn.prepareStatement(del);
			//����վλ��
			ps.setInt(1, Integer.parseInt(depId));
			//ִ��SQL���
			result=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * ���ż��ʵ�ַ���
	* @Title: doAjaxCKCode 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param code
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����1������Ѵ��ڣ�0����Ų�����
	* @throws
	 */
	public int doAjaxCKCode(String code) {
		int b=0;
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from depinfo where depinfocode=?");
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
