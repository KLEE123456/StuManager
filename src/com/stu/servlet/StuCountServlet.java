package com.stu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.stu.bean.Classinfo;
import com.stu.service.StuCountService;
@WebServlet("/stuCount.newdo")
public class StuCountServlet extends HttpServlet{
	StuCountService setStuCount=new StuCountService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收班级总人数的集合
		List<Classinfo> clsSum=setStuCount.selectStuCount();
		
		//接收班级男生数的集合
		List<Classinfo> clsMaleSum=setStuCount.selectStuMale();
		
		//接收班级女生数的集合
		List<Classinfo> clsFeMaleSum=setStuCount.selectStuFemale();
		//将集合放入request作用域
		request.setAttribute("clsMaleSumList",JSON.toJSONString(clsMaleSum));
		request.setAttribute("clsFeMaleSumList", JSON.toJSONString(clsFeMaleSum));
		request.setAttribute("clsSumList1", JSON.toJSONString(clsSum));
		request.setAttribute("clsSumList", clsSum);
		//请求转发到stucount页面
		request.getRequestDispatcher("pages/jsp/StuInfo/StuCount.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
