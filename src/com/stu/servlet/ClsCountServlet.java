package com.stu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.stu.bean.Spilinfo;
import com.stu.service.ClsCountService;
@WebServlet("/clsCount.do")
public class ClsCountServlet extends HttpServlet{
	ClsCountService setClsCount=new ClsCountService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Spilinfo> clsCountList=setClsCount.selectClsCount();
		request.setAttribute("clsCountList",JSON.toJSONString(clsCountList));
		request.getRequestDispatcher("pages/jsp/SpInfo/SpClsCount.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
