package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
 * �����ƣ�DoLogin 
 * �������� �û���¼Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:59:03
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:59:03
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class DoLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username=request.getParameter("uName");
		String Userpwd=Md5Encrypt.MD5(request.getParameter("uPwd"));
		PrintWriter out=response.getWriter();
		AdminInfoService checkLogin=new AdminInfoService();
		Adminuser admins=new Adminuser();
		admins.setAdminusername(Username);
		admins.setAdminuserpwd(Userpwd);
		//��ȡ�û���д����֤��
		String userSrand=request.getParameter("uSrand").toUpperCase();
		//��ȡsession�е���֤��
		HttpSession session=request.getSession();
		String srand=((String)session.getAttribute("rand")).toUpperCase();
		//ע����ڵĸ���
		admins=checkLogin.selectMsg(admins);
		if(userSrand.equals(srand)) {
			if(admins==null) {
				out.print(1);
			}
			else {
				session.setAttribute("admins", admins);
				session.setAttribute("name", "likai");
				out.print(2);
			}
		}
		else {
			out.print(0);
		}
	}

	@Override
	public void init() throws ServletException {
		
	}
	
}
