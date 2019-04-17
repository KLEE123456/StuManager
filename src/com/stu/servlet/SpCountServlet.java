package com.stu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.stu.bean.Depinfo;
import com.stu.service.DepInfoService;
import com.stu.service.SpCountService;
import com.stu.service.SpInfoService;
@WebServlet("/spcount.do")
public class SpCountServlet extends HttpServlet{
	SpCountService setCount=new SpCountService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Depinfo> spCountList=setCount.selectSpCount();
		
		request.setAttribute("spCountList", JSON.toJSONString(spCountList));
		
		request.getRequestDispatcher("pages/jsp/DepInfo/DepSpCount.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
