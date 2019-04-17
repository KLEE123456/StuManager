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
 * 项目名称：StuManager
 * 类名称：CheckUserName 
 * 类描述： 用户名检测Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:49:23
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:49:23
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class CheckUserName extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取请求内容
		String userName=request.getParameter("adminName");
		AdminInfoService checkName=new AdminInfoService();
		//调用实现类中的方法
		int result=checkName.checkReg(userName);
		//返回给Ajax的data
		out.print(result);
	}
	
}
