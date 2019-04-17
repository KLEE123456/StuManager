package com.stu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Paging;
import com.stu.bean.Stuinfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�StuService 
 * �������� ѧ��������
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��1�� ����8:17:18
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��1�� ����8:17:18
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��1��
 */
public class StuService extends DBConnection{
	private int result=0;
	/**
	 * 
	* @Title: selectStuInfo 
	* @Description: TODO(ѧ����Ϣ��ѯʵ�ַ���) 
	* @param @param stu
	* @param @return    �趨�ļ� 
	* @return List    ��������     ��ѯ����¼���ش��ѧ��ʵ�����ļ���
	* @throws
	 */
	public List selectStuInfo(Stuinfo stu,Paging page) {
		List<Stuinfo> stuList=new ArrayList<Stuinfo>();
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT studentinfo.*,depinfo.depinfoname,specilinfo.spilinfoname,classinfo.classinfoname,depinfo.depinfoid,specilinfo.spilinfoid FROM studentinfo ");
		sb.append(" JOIN classinfo ON studentinfo.classinfoid=classinfo.classinfoid ");
		sb.append(" JOIN specilinfo ON classinfo.spilinfoid=specilinfo.spilinfoid");
		sb.append(" JOIN depinfo ON  specilinfo.depinfoid=depinfo.depinfoid where 1=1");
		//�ж�ԺϵID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+stu.getDepinfoId());
		}
		//�ж�רҵID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+stu.getSpilinfoid());
		}
		//�жϰ༶ID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getClassinfoid())) {
			sb.append(" and classinfo.classinfoid="+stu.getClassinfoid());
		}
		//�ж�ѧ�� �����Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getStdinfoname())) {
			sb.append(" and studentinfo.stdinfoname like '%"+stu.getStdinfoname()+"%'");
		}
		//�ж�ѧ��ID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getStdinfoid())) {
			sb.append(" and studentinfo.stdinfoid="+stu.getStdinfoid());
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
			
			//ִ��select���
			rs=pst.executeQuery();
			//���������
			while(rs.next()) {
				//������Ž�����Ķ���
				Stuinfo stuInfo=new Stuinfo();
				stuInfo.setStdinfoid(rs.getInt(1)+"");
				stuInfo.setClassinfoid(rs.getInt(2)+"");
				stuInfo.setStdinfocode(rs.getString(3));
				stuInfo.setStdinfoname(rs.getString(4));
				stuInfo.setStdinfosex(rs.getString(5));
				stuInfo.setStdinfocard(rs.getString(6));
				stuInfo.setStdinfobirthd(rs.getString(7));
				stuInfo.setStdinfonatns(rs.getString(8));
				stuInfo.setStdinfotel(rs.getString(9));
				stuInfo.setStdinfoemail(rs.getString(10));
				stuInfo.setStdinfoyear(rs.getString(11));
				stuInfo.setDepinfoname(rs.getString(12));
				stuInfo.setSpilinfoname(rs.getString(13));
				stuInfo.setClassinfoname(rs.getString(14));
				stuInfo.setDepinfoId(rs.getInt(15)+"");
				stuInfo.setSpilinfoid(rs.getInt(16)+"");
				//��������ӵ�������
				stuList.add(stuInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر����ݿ�����
			super.closeConn();
		}
		return stuList;
	}
	public int selectStuInfoCount(Stuinfo stu) {
		int flag=0;
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM studentinfo WHERE 1=1");
		//�ж�ԺϵID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+stu.getDepinfoId());
		}
		//�ж�רҵID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+stu.getSpilinfoid());
		}
		//�жϰ༶ID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getClassinfoid())) {
			sb.append(" and classinfo.classinfoid="+stu.getClassinfoid());
		}
		//�ж�ѧ�� �����Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getStdinfoname())) {
			sb.append(" and studentinfo.stdinfoname like '%"+stu.getStdinfoname()+"%'");
		}
		//�ж�ѧ��ID�Ƿ�Ϊ�գ���Ϊ����׷��
		if(CheckStr.isEmpty(stu.getStdinfoid())) {
			sb.append(" and studentinfo.stdinfoid="+stu.getStdinfoid());
		}
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//ִ��select���
			rs=pst.executeQuery();
			//���������
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
	 * 
	* @Title: addStuInfo 
	* @Description: TODO(ѧ����ӷ���) 
	* @param @param stu
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0�����ʧ�ܣ�������ӳɹ�
	* @throws
	 */
	public int addStuInfo(Stuinfo stu) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("insert into studentinfo(classinfoid,stdinfocode,stdinfoname,stdinfosex,stdinfocard,stdinfobirthd,stdinfonatns,stdinfotel,stdinfoemail,stdinfoyear) ");
		sb.append("  values(?,?,?,?,?,?,?,?,?,?)");
		//��ȡԤ�������
		try {
			pst=conn.prepareStatement(sb.toString());
			//���ø�ռλ��
			pst.setInt(1, Integer.parseInt(stu.getClassinfoid()));
			pst.setString(2, stu.getStdinfocode());
			pst.setString(3, stu.getStdinfoname());
			pst.setString(4, stu.getStdinfosex());
			pst.setString(5, stu.getStdinfocard());
			pst.setString(6, stu.getStdinfobirthd());
			pst.setString(7, stu.getStdinfonatns());
			pst.setString(8, stu.getStdinfotel());
			pst.setString(9, stu.getStdinfoemail());
			pst.setString(10, stu.getStdinfoyear());
			//ִ�� �������
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
	* @Title: editStuInfo 
	* @Description: TODO(ѧ����Ϣ�༭����) 
	* @param @param stu
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editStuInfo(Stuinfo stu) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("update studentinfo set classinfoid=?,stdinfocode=?,stdinfoname=?,stdinfosex=?,stdinfocard=?,stdinfobirthd=?,stdinfonatns=?,stdinfotel=?,stdinfoemail=?,stdinfoyear=?");
		sb.append(" where stdinfoid=?");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			//����ռλ��
			pst.setInt(1, Integer.parseInt(stu.getClassinfoid()));
			pst.setString(2, stu.getStdinfocode());
			pst.setString(3, stu.getStdinfoname());
			pst.setString(4, stu.getStdinfosex());
			pst.setString(5, stu.getStdinfocard());
			pst.setString(6, stu.getStdinfobirthd());
			pst.setString(7, stu.getStdinfonatns());
			pst.setString(8, stu.getStdinfotel());
			pst.setString(9, stu.getStdinfoemail());
			pst.setString(10, stu.getStdinfoyear());
			pst.setInt(11, Integer.parseInt(stu.getStdinfoid()));
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
	* @Title: detStuInfo 
	* @Description: TODO(ѧ����Ϣɾ������) 
	* @param @param stuId
	* @param @return    �趨�ļ� 
	* @return int    ��������    ����0ɾ��ʧ�ܣ�����ɾ���ɹ�
	* @throws
	 */
	public int detStuInfo(String stuId) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		StringBuffer sb=new StringBuffer();
		sb.append("delete from studentinfo where stdinfoid=?");
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(stuId));
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
	 * ѧ�����ż�ⷽ��
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
		sb.append(" select * from studentinfo where stdinfocode=?");
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
