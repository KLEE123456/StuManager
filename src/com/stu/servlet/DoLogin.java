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
 * 项目名称：StuManager
 * 类名称：DoLogin 
 * 类描述： 用户登录Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:59:03
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:59:03
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
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
		//获取用户填写的验证码
		String userSrand=request.getParameter("uSrand").toUpperCase();
		//获取session中的验证码
		HttpSession session=request.getSession();
		String srand=((String)session.getAttribute("rand")).toUpperCase();
		//注意后期的更改
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
