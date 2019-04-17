package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stu.bean.Adminuser;
/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�MyFilter 
 * ��������������Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����11:29:21
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����11:29:21
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class MyFilter implements Filter{
	FilterConfig config;
	@Override
	public void destroy() {
		System.out.println("��������");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String encoding=config.getInitParameter("encoding");
		String contentType=config.getInitParameter("contentType");
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType(contentType);
		//��ȡ����ĵ�ַ
		String ads=request.getRequestURI();
		//��session�л�ȡ�û�
		HttpSession session=request.getSession();
		Adminuser user=(Adminuser)session.getAttribute("admins");
		if(ads.indexOf("pages")!=-1) {
			if(user==null) {
				//��ȡ��Ӧ��
				PrintWriter out=response.getWriter();
				out.print("<script>alert('�ỰʧЧ��δ���е�¼�������µ�¼!');window.location.href='../index.jsp'</script>");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config=arg0;
	}
	
}
