package com.stu.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.stu.bean.Classinfo;
import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.bean.Spilinfo;
import com.stu.service.ClassInfoService;
import com.stu.service.SpInfoService;
//映射URL地址
@WebServlet("/ClassInfo.newDo")
public class ClassInfoServlet extends HttpServlet{
	//创建班级服务类对象和院系服务类对象
	ClassInfoService classInfoSve=new ClassInfoService();
	SpInfoService setSp=new SpInfoService();
	private int result=0;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectClass(request, response);
		}
		else if("ajaxCKCode".equals(method)) {
			CKCode(request, response);
		}
		else if("ajaxList".equals(method)) {
			//查询班级的ajax
			ajaxClassList(request,response);
		}
		else if("DepSpList".equals(method)) {
			//院系和专业查询
			depAndSpList(request, response);
		}
		else if("Add".equals(method)) {
			addClass(request,response);
		}
		else if("Edit".equals(method)) {
			//编辑渲染
			xREditClass(request,response);
		}
		else if("Det".equals(method)) {
			detClass(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectClass(request, response);
		}
		else if("Add".equals(method)) {
			addClass(request,response);
		}
		else if("Edit".equals(method)) {
			EditClass(request,response);
		}
		else if("Det".equals(method)) {
			detClass(request,response);
		}
	}
	public void CKCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的代号
		String CKClsInfoCode=request.getParameter("clsInfoCode");
		result=classInfoSve.doAjaxCKCode(CKClsInfoCode);
		PrintWriter out=response.getWriter();
		if(result==1) {
			//代号已存在
			out.print(0);
		}
		else {
			//代号不存在
			out.print(1);
		}
	}
	/**
	 * 班级信息查询Servlet
	* @Title: selectClass 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的相关信息
		String classInfoName=request.getParameter("classinfoname");
		//分页类的对象
		Paging page = new Paging();
		String depinfoId=request.getParameter("Depselect");
		String spilinfoid=request.getParameter("Spselect");
		//获取当前页 
		String pagenum = request.getParameter("paging");	
		//判断当前是否传页码，如果没有传就是当前页就是1
		page.setPage(pagenum==null?1:Integer.parseInt(pagenum));
		//每页多少行
		page.setPagesize(10);
		Classinfo classInfo=new Classinfo();
	
		//初始化班级对象
		classInfo.setClassinfoname(classInfoName);
		classInfo.setDepinfoId(depinfoId);
		classInfo.setSpilinfoid(spilinfoid);
		//将对象放入 request中，用于回填
		request.setAttribute("HTClassInfo", classInfo);
		//根据查询条件获取当前返回查询结果条数
		int count = classInfoSve.selectClassInfoCount(classInfo);
		//计算当前总页数
		int totalNum = count/page.getPagesize();
		//如果记录条数%分页大小不为0，则总页数加1
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		//设置总页数
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//承装查询结果的集合
		List<Classinfo> classInfoList=classInfoSve.selectClassInfo(classInfo,page);
		//院系和专业信息查询
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		//将集合 放入request中
		request.setAttribute("ClassList",classInfoList);
		request.setAttribute("DepInfoList", depInfoList);
		//request.setAttribute("SpList", spInfoList);
		//将请求转发到List页面
		request.getRequestDispatcher("pages/jsp/ClassInfo/ClassList.jsp").forward(request, response);
	}
	/**
	 * 院系和专业查询Servlet
	* @Title: depAndSpList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void depAndSpList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		request.setAttribute("depInfoList", depInfoList);
		//request.setAttribute("spList", spInfoList);
		request.getRequestDispatcher("pages/jsp/ClassInfo/AddCls.jsp").forward(request, response);
	}
	/**
	 * 班级添加Servlet
	* @Title: addClass 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用于填写的信息
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classcode=request.getParameter("classcode");
		String classInfoName=request.getParameter("classname");
		String classPnum=request.getParameter("pnum");
		String classmk=request.getParameter("classmk");
		Classinfo clsInfo=new Classinfo();
		//初始化班级对象
		clsInfo.setDepinfoId(depInfoId);
		clsInfo.setSpilinfoid(spInfoId);
		clsInfo.setClassinfocode(classcode);
		clsInfo.setClassinfoname(classInfoName);
		clsInfo.setClassinfosum(classPnum);
		clsInfo.setClassinformk(classmk);
		//调用班级信息添加的方法
		result=classInfoSve.addClassInfo(clsInfo);
		//获取响应流对象
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('班级添加成功');location.href='pages/jsp/ClassInfo/AddCls.jsp'</script>");
		}
		else {
			out.print("<script>alert('班级添加失败');location.href='pages/jsp/ClassInfo/AddCls.jsp'</script>");
		}
		
	}
	/**
	 * 渲染编辑页面的Servlet
	* @Title: xREditClass 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void xREditClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取编辑ID
		String classid=request.getParameter("classid");
		Classinfo classinfo=new Classinfo();
		classinfo.setClassinfoid(classid);
		//通过ID查询班级信息
		List<Classinfo> classList=classInfoSve.selectClassInfo(classinfo,null);
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		//将集合放入request域
		request.setAttribute("depInfoList", depInfoList);
		//request.setAttribute("spList", spInfoList);
		request.setAttribute("classList", classList);
		//将请求转发到编辑页面
		request.getRequestDispatcher("pages/jsp/ClassInfo/EditCls.jsp").forward(request, response);
	}
	/**
	 * 班级信息编辑的Servlet
	* @Title: EditClass 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的编辑信息
		String classId=request.getParameter("clsid");
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classinfocode=request.getParameter("classinfocode");
		String classinfoname=request.getParameter("classinfoname");
		String classinfosum=request.getParameter("classinfosum");
		String classinformk=request.getParameter("classinformk");
		Classinfo clsInfo=new Classinfo();
		//初始化班级对象
		clsInfo.setClassinfoid(classId);
		clsInfo.setDepinfoId(depInfoId);
		clsInfo.setSpilinfoid(spInfoId);
		clsInfo.setClassinfocode(classinfocode);
		clsInfo.setClassinfoname(classinfoname);
		clsInfo.setClassinfosum(classinfosum);
		clsInfo.setClassinformk(classinformk);
		//调用编辑方法
		result=classInfoSve.editClassInfo(clsInfo);
		PrintWriter out=response.getWriter();
		if(result==0) {
			out.print("<script>alert('编辑失败!');location.href='ClassInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('编辑成功!');location.href='ClassInfo.newDo?method=List'</script>");
		}
	}
	/**
	 * 班级删除的Servlet
	* @Title: detClass 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void detClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取删除的班级ID
		String classId=request.getParameter("classid");
		//调用删除的方法
		result=classInfoSve.detClassInfo(classId);
		//获取响应流对象
		PrintWriter out=response.getWriter();
		if(result==0) {
			out.print("<script>alert('删除失败!');location.href='ClassInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('删除成功!');location.href='ClassInfo.newDo?method=List'</script>");
		}
	}
	/**
	 * 班级信息查询的Servlet（ajax动态显示）
	* @Title: ajaxClassList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void ajaxClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取专业ID
		String spInfoId=request.getParameter("spInfoId");
		Classinfo clsInfo=new Classinfo();
		clsInfo.setSpilinfoid(spInfoId);
		//承装查询结果的结果集
		List<Classinfo> clsInfoList=classInfoSve.selectClassInfo(clsInfo,null);
		//将json对象转化成json字符串
		String classInfo=JSON.toJSONString(clsInfoList);
		out.print(classInfo);
	}
	
}
