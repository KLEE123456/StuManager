package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.service.AdminInfoService;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�CheckUserName 
 * �������� �û������Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:49:23
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:49:23
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class CheckUserName extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡ��������
		String userName=request.getParameter("adminName");
		AdminInfoService checkName=new AdminInfoService();
		//����ʵ�����еķ���
		int result=checkName.checkReg(userName);
		//���ظ�Ajax��data
		out.print(result);
	}
	
}
