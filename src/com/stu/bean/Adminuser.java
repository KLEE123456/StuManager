package com.stu.bean;

import java.util.Date;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�Adminuser 
 * �������� �û�ʵ����
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����11:56:02
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����11:56:02
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class Adminuser {
	private String  adminuserid;
	private String  adminusername;
	private String  adminuserpwd;
	private Date  adminusertime;
	private String  truename;
	private String  sex;
	private String  imagepath;
	private String  phone;
	private String  email;
	private String tuerpwd;
	public String getAdminuserid() {
		return adminuserid;
	}
	public void setAdminuserid(String adminuserid) {
		this.adminuserid = adminuserid;
	}
	public String getAdminusername() {
		return adminusername;
	}
	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}
	public String getAdminuserpwd() {
		return adminuserpwd;
	}
	public void setAdminuserpwd(String adminuserpwd) {
		this.adminuserpwd = adminuserpwd;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getAdminusertime() {
		return adminusertime;
	}
	public void setAdminusertime(Date adminusertime) {
		this.adminusertime = adminusertime;
	}
	public String getTuerpwd() {
		return tuerpwd;
	}
	public void setTuerpwd(String tuerpwd) {
		this.tuerpwd = tuerpwd;
	}
}
