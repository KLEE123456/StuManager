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
 * 项目名称：StuManager
 * 类名称：EditUser 
 * 类描述： 编辑用户真实姓名和性别Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午11:16:31
 * 修改人：kk
 * 修改时间：2018年12月21日 下午11:16:31
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class EditUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取用户填写的编辑信息
		HttpSession session=request.getSession();
		//获取请求信息
		String userId=request.getParameter("userId");
		String truename=request.getParameter("trueName");
		String sex=request.getParameter("sex");
		//取出登录用户
		Adminuser users=(Adminuser)session.getAttribute("admins");
		//对真实姓名和性别进行设置
		users.setTruename(truename);
		users.setSex(sex);
		//调用实现类
		AdminInfoService editUser=new AdminInfoService();
		int result;
		result=editUser.editUser(users);
		if(result!=0) {
			out.println("<script>alert('编辑成功');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
			//request.getRequestDispatcher("pages/jsp/modifyInfo.jsp").forward(request, response);
		}
		else {
			out.println("<script>alert('编辑失败');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
			//request.getRequestDispatcher("pages/jsp/modifyInfo.jsp").forward(request, response);
		}
		
		
	}
	
}
