package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stu.bean.Adminuser;
import com.stu.service.AdminInfoService;

import com.stu.untils.Md5Encrypt;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�EditPwd 
 * ������������༭servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����11:07:42
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����11:07:42
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class EditPwd extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡ����
		String newPwd=request.getParameter("truePwd");
		//����session������
		HttpSession session=request.getSession();
		//��session��ȡ����¼�û�ʵ��
		Adminuser users=(Adminuser)session.getAttribute("admins");
		//��������и���
		users.setAdminuserpwd(Md5Encrypt.MD5(newPwd));
		AdminInfoService editPwd=new AdminInfoService();
		int result;
		result=editPwd.editUserPwd(users);
		//���ĳɹ�
		if(result!=0) {
			out.print(1);
		}
		//����ʧ��
		else {
			out.print(0);
		}
	}
		
}
