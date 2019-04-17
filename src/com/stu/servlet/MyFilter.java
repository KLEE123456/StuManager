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
 * 项目名称：StuManager
 * 类名称：MyFilter 
 * 类描述：过滤器Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午11:29:21
 * 修改人：kk
 * 修改时间：2018年12月21日 下午11:29:21
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class MyFilter implements Filter{
	FilterConfig config;
	@Override
	public void destroy() {
		System.out.println("我已销毁");
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
		//获取请求的地址
		String ads=request.getRequestURI();
		//从session中获取用户
		HttpSession session=request.getSession();
		Adminuser user=(Adminuser)session.getAttribute("admins");
		if(ads.indexOf("pages")!=-1) {
			if(user==null) {
				//获取响应流
				PrintWriter out=response.getWriter();
				out.print("<script>alert('会话失效或未进行登录，请重新登录!');window.location.href='../index.jsp'</script>");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config=arg0;
	}
	
}
