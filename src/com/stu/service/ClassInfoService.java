package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Classinfo;
import com.stu.bean.Paging;
import com.stu.bean.Spilinfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�ClassInfoService 
 * �������� �༶������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��1�� ����8:32:03
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��1�� ����8:32:03
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��1��
 */
public class ClassInfoService extends DBConnection{
	private int result=0;
	/**
	 * 
	* @Title: selectClassNum 
	* @Description: TODO(רҵ�µİ༶����ѯ����) 
	* @param @param spId
	* @param @return    �趨�ļ� 
	* @return int    ��������    ���ز�ѯ���İ༶�������򷵻�0
	* @throws
	 */
	public int selectClassNum(String spId) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String sel="SELECT count(*) pnum FROM classinfo WHERE spilinfoid=?";
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sel);
			pst.setInt(1,Integer.parseInt(spId));
			rs=pst.executeQuery();
			if(rs.next()) {
				//��ȡ��¼�еİ༶��
				result = rs.getInt(1);
			}
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
	* @Title: selectClassInfo 
	* @Description: TODO(�༶��Ϣ��ѯ����) 
	* @param @param cf
	* @param @return    �趨�ļ� 
	* @return List    ��������   ���ش�Ű༶ʵ�����ļ���
	* @throws
	 */
	public List selectClassInfo(Classinfo cf,Paging page) {
		//������Ű༶ʵ�����ļ���
		List<Classinfo> classList=new ArrayList<Classinfo>();
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT classinfo.*,specilinfo.spilinfoname,depinfo.depinfoname,depinfo.depinfoid FROM classinfo ");
		sb.append(" JOIN specilinfo ON classinfo.spilinfoid=specilinfo.spilinfoid ");
		sb.append(" JOIN depinfo ON depinfo.depinfoid=specilinfo.depinfoid WHERE 1=1 ");
		//�жϰ༶�����Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getClassinfoname())) {
			sb.append(" and classinfoname like '%"+cf.getClassinfoname()+"%'");
		}
		//�ж�ԺϵID�Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+cf.getDepinfoId());
		}
		//�ж�רҵID�Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+cf.getSpilinfoid());
		}
		//�жϰ༶ID�Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getClassinfoid())) {
			sb.append(" and classinfoid="+cf.getClassinfoid());
		}
		if(page!=null)
		sb.append(" limit ?,?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//������ѯ���Ľ����
			while(rs.next()) {
				Classinfo csf=new Classinfo();
				csf.setClassinfoid(rs.getInt(1)+"");
				csf.setSpilinfoid(rs.getInt(2)+"");
				csf.setClassinfocode(rs.getString(3));
				csf.setClassinfoname(rs.getString(4));
				csf.setClassinfosum(rs.getInt(5)+"");
				csf.setClassinformk(rs.getString(6));
				csf.setSpilinfoname(rs.getString(7));
				csf.setDepinfoname(rs.getString(8));
				csf.setDepinfoId(rs.getInt(9)+"");
				//���༶ʵ�������뼯����
				classList.add(csf);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		
		return classList;
	}
	/**
	 * �༶����ѯ
	* @Title: selectClassInfoCount 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param cf
	* @param @return    �趨�ļ� 
	* @return int    ��������  ���ز鵽�İ༶��
	* @throws
	 */
	public int selectClassInfoCount(Classinfo cf) {
		int result = 0;
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT count(*) pnum FROM classinfo ");
		sb.append(" JOIN specilinfo ON classinfo.spilinfoid=specilinfo.spilinfoid ");
		sb.append(" JOIN depinfo ON depinfo.depinfoid=specilinfo.depinfoid WHERE 1=1 ");
		//�жϰ༶�����Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getClassinfoname())) {
			sb.append(" and classinfoname like '%"+cf.getClassinfoname()+"%'");
		}
		//�ж�ԺϵID�Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+cf.getDepinfoId());
		}
		//�ж�רҵID�Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+cf.getSpilinfoid());
		}
		//�жϰ༶ID�Ƿ�Ϊ�գ���Ϊ�������׷��
		if(CheckStr.isEmpty(cf.getClassinfoid())) {
			sb.append(" and classinfoid="+cf.getClassinfoid());
		}
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//������ѯ���Ľ����
			while(rs.next()) {
				result = rs.getInt(1);
			}
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
	* @Title: selectSpName 
	* @Description: TODO(רҵ���Ʋ�ѯ����) 
	* @param @param spinfo
	* @param @return    �趨�ļ� 
	* @return List    ��������  ���ش��רҵʵ�����ļ���
	* @throws
	 */
	public List selectSpName(Spilinfo spinfo) {
		List<Spilinfo> spList=new ArrayList<Spilinfo>();
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM specilinfo where 1=1 ");
		if(CheckStr.isEmpty(spinfo.getDepinfoid())) {
			sb.append(" and depinfoid="+spinfo.getDepinfoid());
		}
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//������ѯ�����
			while(rs.next()) {
				Spilinfo sp=new Spilinfo();
				sp.setSpilinfoid(rs.getInt(1)+"");
				sp.setDepinfoid(rs.getInt(2)+"");
				sp.setSpilinfocode(rs.getString(3));
				sp.setSpilinfoname(rs.getString(4));
				sp.setSpilinfoaim(rs.getString(5));
				sp.setSpilinfoprodire(rs.getString(6));
				//��רҵ������뼯����
				spList.add(sp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return spList;
	}
	public int addClassInfo(Classinfo cls) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("insert into classinfo(spilinfoid,classinfocode,classinfoname,classinfosum,classinformk) ");
		sb.append(" values(?,?,?,?,?)");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(cls.getSpilinfoid()));
			pst.setString(2, cls.getClassinfocode());
			pst.setString(3, cls.getClassinfoname());
			pst.setInt(4, Integer.parseInt(cls.getClassinfosum()));
			pst.setString(5, cls.getClassinformk());
			//ִ�в������
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
	* @Title: editClassInfo 
	* @Description: TODO(�༶��Ϣ�༭����) 
	* @param @param cls
	* @param @return    �趨�ļ� 
	* @return int    ��������   �޸�ʧ�ܷ���0�������޸ĳɹ�
	* @throws
	 */
	public int editClassInfo(Classinfo cls) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("update classinfo set spilinfoid=?,classinfocode=?,classinfoname=?,classinfosum=?,classinformk=?");
		sb.append(" where classinfoid=?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(cls.getSpilinfoid()));
			pst.setString(2, cls.getClassinfocode());
			pst.setString(3, cls.getClassinfoname());
			pst.setString(4, cls.getClassinfosum());
			pst.setString(5, cls.getClassinformk());
			pst.setInt(6, Integer.parseInt(cls.getClassinfoid()));
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
	 * 
	* @Title: detClassInfo 
	* @Description: TODO(�༶��Ϣɾ������) 
	* @param @param classId
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detClassInfo(String classId) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("delete from classinfo where classinfoid=?");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(classId));
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
	public int doAjaxCKCode(String code) {
		int b=0;
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from classinfo where classinfocode=?");
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
