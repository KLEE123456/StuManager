package com.stu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stu.bean.Adminuser;
import com.stu.dbconn.DBConnection;
import com.stu.untils.Md5Encrypt;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�AdminInfoService 
 * �������� �û���Ϣ�༭��
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:32:50
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:32:50
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class AdminInfoService extends DBConnection{
	private int result=0;
	private ResultSet rs;
	/**
	 * ����û�ʵ�ַ���
	* @Title: addMessage 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param users
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����ֵΪ0���ʧ�ܣ�������ӳɹ�
	* @throws
	 */
	public int addMessage(Adminuser users) {
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String ins="insert into adminuser(adminusername,adminuserpwd,adminusertime,truename,sex,imagepath,phone,email) values(?,?,Now(),?,?,?,?,?)";
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(ins);
			pst.setString(1,users.getAdminusername());
			pst.setString(2,Md5Encrypt.MD5(users.getAdminuserpwd()));
			pst.setString(3, users.getTruename());
			pst.setString(4, users.getSex());
			pst.setString(5, users.getImagepath());
			pst.setString(6, users.getPhone());
			pst.setString(7,users.getEmail());
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
	 * ע���û������ʵ�ַ���
	* @Title: checkReg 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param user
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����0����û�������ӣ�����1����û��Ѿ����ڲ���ע��
	* @throws
	 */
	public int checkReg(String user) {
		int  b=0;
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL��䣬����û���
		String sel="select * from adminuser where adminusername=?";
		try {
			//��ȡԤ�������
			 pst=conn.prepareStatement(sel);
			//����ռλ��
			pst.setString(1, user);
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//��ѯ����¼�򷵻�true
			if(rs.next()) {
				b=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.getDBconn();
		}
		return b;
	}
	/**
	 * �༭�û�����ʵ�ַ���
	* @Title: editUserPwd 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param user
	* @param @return    �趨�ļ� 
	* @return int    ��������  ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editUserPwd(Adminuser user) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String upd="update adminuser set adminuserpwd=? where adminuserid=?";
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(upd);
			//����ռλ��
			pst.setString(1, user.getAdminuserpwd());
			pst.setInt(2, Integer.parseInt(user.getAdminuserid()));
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
	 * �û�����ʵ�������Ա�༭ʵ�ַ���
	* @Title: editUser 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param users
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editUser(Adminuser users) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String upd="update adminuser set truename=?,sex=? where adminuserid=?";
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(upd);
			//����ռλ��
			pst.setString(1, users.getTruename());
			pst.setString(2, users.getSex());
			pst.setInt(3, Integer.parseInt(users.getAdminuserid()));
			//ִ���޸����
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
	 * �༭�û�ͷ��ʵ�ַ���
	* @Title: editUserImg 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param user
	* @param @return    �趨�ļ� 
	* @return int    ��������   ����ֵΪ0�༭ʧ�ܣ�����༭�ɹ�
	* @throws
	 */
	public int editUserImg(Adminuser user) {
		//��ȡ���ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String upd="update adminuser set adminusername=?,imagepath=? where adminuserid=?";
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(upd);
			//����ռλ��
			pst.setString(1, user.getAdminusername());
			pst.setString(2, user.getImagepath());
			pst.setInt(3, Integer.parseInt(user.getAdminuserid()));
			//ִ��update���
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
	 * ��¼���������ⷽ��
	* @Title: selectMsg 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param user
	* @param @return    �趨�ļ� 
	* @return Adminuser    ��������  ����null�򲻴��ڸ��û����������
	* @throws
	 */
	public Adminuser selectMsg(Adminuser user) {
		//����һ���յ��û�����
		Adminuser adminuser=null;
		//�������ݿ�����
		conn=getDBconn();
		//ƴ��SQL���
		String sel="select * from adminuser where adminusername=? and adminuserpwd=?";
		try {
			//��ȡԤ�������
			pst=conn.prepareStatement(sel);
			//����ռλ��
			pst.setString(1, user.getAdminusername());
			pst.setString(2, user.getAdminuserpwd());
			//ִ�в�ѯ���
			rs=pst.executeQuery();
			//�����ѯ�����û��������û�ʵ��
			if(rs.next()) {
				adminuser=new Adminuser();
				//�����û�����
				adminuser.setAdminuserid(rs.getString(1));
				adminuser.setAdminusername(rs.getString(2));
				adminuser.setAdminuserpwd(rs.getString(3));
				adminuser.setAdminusertime(rs.getDate(4));
				adminuser.setTruename(rs.getString(5));
				adminuser.setSex(rs.getString(6));
				adminuser.setImagepath(rs.getString(7));
				adminuser.setPhone(rs.getString(8));
				adminuser.setEmail(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//�Ͽ����ݿ�����
			super.closeConn();
		}
		return adminuser;
	}
}
