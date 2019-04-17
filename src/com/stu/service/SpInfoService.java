package com.stu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.bean.Spilinfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�SpInfoService 
 * ��������רҵ��Ϣ������ 
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:29:29
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:29:29
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class SpInfoService extends DBConnection{
	//�޸ģ�ɾ�����������ر���
	private int result=0;
	/**
	 * רҵ��Ϣ��ѯʵ�ַ���
	* @Title: selectSpInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param sp
	* @param @return    �趨�ļ� 
	* @return List<Spilinfo>    ��������   ���ر���רҵʵ�����ļ���
	* @throws
	 */
	public List<Spilinfo> selectSpInfo(Spilinfo sp,Paging page){
		//���רҵ��Ϣ�ļ���
		List<Spilinfo> spList=new ArrayList<Spilinfo>();
		//�������ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT s.*,(SELECT d.depinfoname FROM depinfo d WHERE d.depinfoid=s.depinfoid) as '����Ժϵ' FROM specilinfo s WHERE 1=1");
		//����רҵ������Ϊ��ѯ����
		if(CheckStr.isEmpty(sp.getSpilinfoname())) {
			sb.append(" and spilinfoname like '%"+sp.getSpilinfoname()+"%'");
		}
		if(CheckStr.isEmpty(sp.getDepinfoid())) {
			sb.append(" and depinfoid="+sp.getDepinfoid());
		}
		if(CheckStr.isEmpty(sp.getSpilinfoid())) {
			sb.append(" and spilinfoid="+sp.getSpilinfoid());
		}

		if(page!=null) {
			sb.append(" limit ?,?");
		}
		try {
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			rs=pst.executeQuery();
			while(rs.next()) {
				Spilinfo Sp=new Spilinfo();
				Sp.setSpilinfoid(rs.getInt(1)+"");
				Sp.setDepinfoid(rs.getInt(2)+"");
				Sp.setSpilinfocode(rs.getString(3));
				Sp.setSpilinfoname(rs.getString(4));
				Sp.setSpilinfoaim(rs.getString(5));
				Sp.setSpilinfoprodire(rs.getString(6));
				Sp.setDepinfoname(rs.getString(7));
				//����ѯ���� ��¼��װ��ʵ����뼯����
				spList.add(Sp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		return spList;
	}
	public int selectSpInfoCount(Spilinfo sp){
		//��¼�ܼ�¼����
		int flag=0;
		//�������ݿ�����
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM specilinfo WHERE 1=1");
		//����רҵ������Ϊ��ѯ����
		if(CheckStr.isEmpty(sp.getSpilinfoname())) {
			sb.append(" and spilinfoname like '%"+sp.getSpilinfoname()+"%'");
		}
		if(CheckStr.isEmpty(sp.getDepinfoid())) {
			sb.append(" and depinfoid="+sp.getDepinfoid());
		}
		if(CheckStr.isEmpty(sp.getSpilinfoid())) {
			sb.append(" and spilinfoid="+sp.getSpilinfoid());
		}
		try {
			pst=conn.prepareStatement(sb.toString());
			
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
	 * Ժϵ���Ʋ�ѯ
	* @Title: selectDepName 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @return    �趨�ļ� 
	* @return List    ��������   ���ر���Ժϵʵ�����ļ���
	* @throws
	 */
	public List selectDepName() {
		//����List����
		List<Depinfo> depList=new ArrayList<Depinfo>();
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String sel="SELECT * FROM depinfo";
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sel);
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//���������
			while(rs.next()) {
				Depinfo depInfo=new Depinfo();
				depInfo.setDepinfoId(rs.getInt(1)+"");
				depInfo.setDepInfoCode(rs.getString(2));
				depInfo.setDepinfoname(rs.getString(3));
				depInfo.setDepinfoPreOfTech(rs.getInt(4)+"");
				depInfo.setDepinfoAssTeach(rs.getInt(5)+"");
				//����ѯ���� ��¼��װ��ʵ����뼯����
				depList.add(depInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return depList;
	}
	/**
	 *רҵ��Ϣ��ӷ���
	* @Title: addSpInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param sp
	* @param @return    �趨�ļ� 
	* @return int    ��������    ����0���ʾ���ʧ�ܣ�������ӳɹ� 
	* @throws
	 */
	public int addSpInfo(Spilinfo sp) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String ins="insert into specilinfo(depinfoid,spilinfocode,spilinfoname,spilinfoaim,spilinfoprodire)"+
		"values(?,?,?,?,?)";
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(ins);
			//����ռλ��
			pst.setInt(1, Integer.parseInt(sp.getDepinfoid()));
			pst.setString(2, sp.getSpilinfocode());
			pst.setString(3, sp.getSpilinfoname());
			pst.setString(4, sp.getSpilinfoaim());
			pst.setString(5, sp.getSpilinfoprodire());
			//ִ���������
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
	 * Ժϵ�༭����
	* @Title: editSpInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param sp
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0���ʾ�༭ʧ�ܣ�����༭�ɹ� 
	* @throws
	 */
	public int editSpInfo(Spilinfo sp) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String upt="update specilinfo set spilinfoname=?,spilinfoaim=?,spilinfoprodire=?"+
		"where spilinfoid=?";
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(upt);
			//����ռλ��
			pst.setString(1, sp.getSpilinfoname());
			pst.setString(2, sp.getSpilinfoaim());
			pst.setString(3, sp.getSpilinfoprodire());
			pst.setInt(4, Integer.parseInt(sp.getSpilinfoid()));
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
	 *רҵ��Ϣɾ������
	* @Title: DetSpInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param spId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0��ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int DetSpInfo(String spId) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String det="delete from specilinfo where spilinfoid=?";
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(det);
			//����ռλ��
			pst.setInt(1, Integer.parseInt(spId));
			//ִ��ɾ�����
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
	 * רҵ�����ⷽ��
	* @Title: doAjaxCKCode 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param code
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����1��ʾרҵ�����Ѵ��ڣ�0���ʾ������
	* @throws
	 */
	public int doAjaxCKCode(String code) {
		int b=0;
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from specilinfo where spilinfocode=?");
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
