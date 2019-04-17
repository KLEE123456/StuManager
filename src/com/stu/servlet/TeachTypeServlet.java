package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.service.TeachTypeService;
@WebServlet("/teachType.do")
/**
 * 教师类别Servlet
 * 项目名称：StuManager
 * 类名称：TeachTypeServlet 
 * 类描述： 
 * 创建人：kk
 * 创建时间：2019年1月7日 下午3:07:31
 * 修改人：kk
 * 修改时间：2019年1月7日 下午3:07:31
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
public class TeachTypeServlet extends HttpServlet{
	private int result=0;
	TeachTypeService setTchType=new TeachTypeService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取a标签传过来的方法
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectTchType(request, response);
		}
		else if("Add".equals(method)) {
			AddTchType(request,response);
		}
		else if("Edit".equals(method)) {
			XREditTchType(request,response);
		}
		else if("Det".equals(method)) {
			DetTchType(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String method=request.getParameter("method");
		if("List".equals(method)) {
			selectTchType(request, response);
		}
		else if("Add".equals(method)) {
			AddTchType(request,response);
		}
		else if("Edit".equals(method)) {
			EditTchType(request,response);
		}
		
	}
	/**
	 * 查询的Servlet
	* @Title: selectTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		//获取查询的相关信息
		String tyName=request.getParameter("teachtypename");
		
		TeachType ty=new TeachType();
		
		ty.setTeachtypename(tyName);
		//用于回填
		request.setAttribute("tyName",tyName);
		
		List<TeachType> tchTypeList=setTchType.selectTchType(ty,page);
		int count=setTchType.selectTchTypeCount(ty);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//将集合放入请求作用域
		request.setAttribute("tchTypeList", tchTypeList);
		//请求转发
		request.getRequestDispatcher("pages/jsp/TeachTypeInfo/TeachTypeList.jsp").forward(request, response);
	}
	/**
	 * 新增的Servlet方法
	* @Title: AddTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void AddTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取添加页面相关信息
		String teachtypename=request.getParameter("teachtypename");
		TeachType ty=new TeachType();
		ty.setTeachtypename(teachtypename);
		result=setTchType.addTchType(ty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('教师类别添加成功');location.href='pages/jsp/TeachTypeInfo/AddTeachType.jsp'</script>");
		}
		else {
			out.print("<script>alert('教师类别添加失败');location.href='pages/jsp/TeachTypeInfo/AddTeachType.jsp'</script>");
		}
	}
	/**
	 * 编辑页面渲染的Servlet
	* @Title: XREditTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void XREditTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户编辑的教师类别ID
		String typId=request.getParameter("typid");
		List<TeachType> xRtypList=setTchType.XRTchtype(typId);
		request.setAttribute("xRtypList", xRtypList);
		//将请求转发到编辑页面
		request.getRequestDispatcher("pages/jsp/TeachTypeInfo/EditTeachType.jsp").forward(request, response);
	}
	/**
	 * 删除的Servlet
	* @Title: DetTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void DetTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取删除的教师类别ID
		String typId=request.getParameter("typid");
		result=setTchType.detTchType(typId);
		PrintWriter out=response.getWriter();
		if(result!=0) {
		
			out.print("<script>alert('教师类别删除成功');location.href='teachType.do?method=List'</script>");
		}
		else {
			out.print("<script>alert('教师类别删除失败');location.href='teachType.do?method=List'</script>");
		}
	}
	/**
	 *编辑的Servlet
	* @Title: EditTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取编辑页面的相关信息
		String typId=request.getParameter("typid");
		String typName=request.getParameter("teachtypename");
		TeachType ty=new TeachType();
		ty.setTeachtypeid(typId);
		ty.setTeachtypename(typName);
		result=setTchType.editTchType(ty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('教师类别编辑成功');location.href='teachType.do?method=List'</script>");
		}
		else {
			out.print("<script>alert('教师类别编辑失败');location.href='teachType.do?method=List'</script>");
		}
	}
}
