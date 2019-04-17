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
 * 项目名称：StuManager
 * 类名称：EditPwd 
 * 类描述：密码编辑servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午11:07:42
 * 修改人：kk
 * 修改时间：2018年12月21日 下午11:07:42
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class EditPwd extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取请求
		String newPwd=request.getParameter("truePwd");
		//创建session作用域
		HttpSession session=request.getSession();
		//从session中取出登录用户实体
		Adminuser users=(Adminuser)session.getAttribute("admins");
		//对密码进行更改
		users.setAdminuserpwd(Md5Encrypt.MD5(newPwd));
		AdminInfoService editPwd=new AdminInfoService();
		int result;
		result=editPwd.editUserPwd(users);
		//更改成功
		if(result!=0) {
			out.print(1);
		}
		//更改失败
		else {
			out.print(0);
		}
	}
		
}
