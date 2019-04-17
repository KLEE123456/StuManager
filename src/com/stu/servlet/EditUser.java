package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import com.stu.bean.Adminuser;
import com.stu.service.AdminInfoService;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�EditUser 
 * �������� �༭�û���ʵ�������Ա�Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����11:16:31
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����11:16:31
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class EditUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡ�û���д�ı༭��Ϣ
		HttpSession session=request.getSession();
		//��ȡ������Ϣ
		String userId=request.getParameter("userId");
		String truename=request.getParameter("trueName");
		String sex=request.getParameter("sex");
		//ȡ����¼�û�
		Adminuser users=(Adminuser)session.getAttribute("admins");
		//����ʵ�������Ա��������
		users.setTruename(truename);
		users.setSex(sex);
		//����ʵ����
		AdminInfoService editUser=new AdminInfoService();
		int result;
		result=editUser.editUser(users);
		if(result!=0) {
			out.println("<script>alert('�༭�ɹ�');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
			//request.getRequestDispatcher("pages/jsp/modifyInfo.jsp").forward(request, response);
		}
		else {
			out.println("<script>alert('�༭ʧ��');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
			//request.getRequestDispatcher("pages/jsp/modifyInfo.jsp").forward(request, response);
		}
		
		
	}
	
}
