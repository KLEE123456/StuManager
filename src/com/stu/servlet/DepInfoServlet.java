package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.service.DepInfoService;

/**
 * 
 * 项目名称：StuManager
 * 类名称：EditDep 
 * 类描述： 服务院系的Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:01:01
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:01:01
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
@WebServlet("/Depinfo.newDo")
public class DepInfoServlet extends HttpServlet{
	//声明一个做数据库查询的类对象
	 DepInfoService setDep=new  DepInfoService();
	 //承接返回结果
	 private int result;
	@Override
	//所用通过a标签提交的都 进入doget方法
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取a标签传过来的方法
		String method=request.getParameter("method");
	
		PrintWriter out=response.getWriter();
	
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectDep(request, response);
		}
		else if("ajaxCKCode".equals(method)) {
			CKCode(request, response);
		}
		else if("Add".equals(method)) {
			AddDep(request,response);
		}
		else if("Edit".equals(method)) {
			XREditDep(request,response);
		}
		else if("Det".equals(method)) {
			DetDep(request,response);
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String method=request.getParameter("method");
		if("List".equals(method)) {
			selectDep(request, response);
		}
		else if("Add".equals(method)) {
			AddDep(request,response);
		}
		else if("Edit".equals(method)) {
			EditDep(request,response);
		}
	}
	/**
	 * 院系代号检测
	* @Title: ajaxCKCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void CKCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的代号
		String CKdepInfoCode=request.getParameter("depInfoCode");
		result=setDep.doAjaxCKCode(CKdepInfoCode);
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
	 * method=List的实现方法
	* @Title: selectDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户搜索的院系内容
		String depinfoname=request.getParameter("depinfoname");
		//获取当前页
		String pagging=request.getParameter("paging");
		int num=(pagging==null?1:Integer.parseInt(pagging));
		PrintWriter out=response.getWriter();
		Paging page=new Paging();
		//当前页
		page.setPage(num);
		//每页显示的记录行数
		page.setPagesize(10);
		//创建一个院系对象
		Depinfo dep=new Depinfo();
		//设置name属性
		dep.setDepinfoname(depinfoname);//进行列表渲染时传的是一个空的dep
		//将对象传入service的服务方法
		List<Depinfo> DepList=setDep.selectDepInfoList(dep,page);
		//总记录条数
		int count=setDep.selectDepInfoListCount(dep);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		//总页数
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//将返回过来的list放入request作用域
		request.setAttribute("DepList", DepList);
		//用于设置搜索的回填
		request.setAttribute("depName", depinfoname);
		//将请求转发
		request.getRequestDispatcher("pages/jsp/DepInfo/DepList.jsp").forward(request, response);
	}
	/**
	 * method=Add的实现方法
	* @Title: AddDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void AddDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取表单信息
		String depInfoCode=request.getParameter("depinfocode");
		String depinfoname=request.getParameter("depinfoname");
		String depinfoPreOfTech=request.getParameter("depinfoPreOfTech");
		String depinfoAssTeach=request.getParameter("depinfoAssTeach");
		Depinfo addDep=new Depinfo();
		addDep.setDepInfoCode(depInfoCode);
		addDep.setDepinfoname(depinfoname);
		addDep.setDepinfoPreOfTech(depinfoPreOfTech);
		addDep.setDepinfoAssTeach(depinfoAssTeach);
		//调用新增实现类中的方法
		result=setDep.addDepInfo(addDep);
		if(result!=0) {
			out.print("<script>alert('院系添加成功');location.href='pages/jsp/DepInfo/AddDep.jsp'</script>");
		}
		else {
			out.print("<script>alert('院系添加失败');location.href='pages/jsp/DepInfo/AddDep.jsp'</script>");
		}
	}
	/**
	 * method=Edit的实现方法
	* @Title: EditDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String id=request.getParameter("depid");
		String depInfoCode=request.getParameter("depInfoCode");
		String depinfoname=request.getParameter("depinfoname");
		String depinfoPreOfTech=request.getParameter("depinfoPreOfTech");
		String depinfoAssTeach=request.getParameter("depinfoAssTeach");
		Depinfo editDep=new Depinfo();
		editDep.setDepinfoId(id);
		editDep.setDepInfoCode(depInfoCode);
		editDep.setDepinfoname(depinfoname);
		editDep.setDepinfoPreOfTech(depinfoPreOfTech);
		editDep.setDepinfoAssTeach(depinfoAssTeach);
		//调用编辑类中的实现方法
		result=setDep.updateDep(editDep);
		if(result!=0) {
			out.print("<script>alert('院系编辑成功');location.href='Depinfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('院系编辑失败');location.href='Depinfo.newDo?method=List'</script>");
		}
	}
	/**
	 * 动态渲染编辑信息的实现方法
	* @Title: XREditDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void XREditDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取编辑链接的id参数
		String id=request.getParameter("depid");
		Depinfo editDep=new Depinfo();
		editDep.setDepinfoId(id);
		List<Depinfo> DepList=setDep.selectDepInfoList(editDep,null);
		request.setAttribute("editdep",DepList);
		//将请求转发到编辑页面
		request.getRequestDispatcher("pages/jsp/DepInfo/EditDep.jsp").forward(request, response);
	} 
	/**
	 * method=Det的实现方法
	* @Title: DetDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void DetDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取参数ID
		String id=request.getParameter("depid");
		//调用删除实现类
		result=setDep.detDep(id);
		if(result!=0) {
			out.print("<script>alert('院系删除成功');location.href='Depinfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('院系删除失败');location.href='Depinfo.newDo?method=List'</script>");
		}
	} 
}
