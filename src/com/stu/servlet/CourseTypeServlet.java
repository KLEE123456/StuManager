package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.bean.CourseType;
import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.service.CourseTypeService;
/**
 * 课程类别Servlet
 * 项目名称：StuManager
 * 类名称：CourseTypeServlet 
 * 类描述： 
 * 创建人：kk
 * 创建时间：2019年1月7日 下午3:07:53
 * 修改人：kk
 * 修改时间：2019年1月7日 下午3:07:53
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
@WebServlet("/CourseType.NewDO")
public class CourseTypeServlet extends HttpServlet{
	private int result=0;
	CourseTypeService  setCty=new CourseTypeService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取a标签传过来的方法
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectCourseType(request, response);
		}
		else if("Add".equals(method)) {
			AddCourseType(request,response);
		}
		else if("Edit".equals(method)) {
			XREditCourseType(request,response);
		}
		else if("Det".equals(method)) {
			DetCourseType(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String method=request.getParameter("method");
		if("List".equals(method)) {
			selectCourseType(request, response);
		}
		else if("Add".equals(method)) {
			AddCourseType(request,response);
		}
		else if("Edit".equals(method)) {
			EditCourseType(request,response);
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
	public void selectCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		//获取查询相关信息
		String coursetypename=request.getParameter("coursetypename");
		CourseType cty=new CourseType();
		cty.setCoursetypename(coursetypename);
		//用于回填
		request.setAttribute("cyName", coursetypename);
		List<CourseType> CourseTypeList=setCty.selectCourseType(cty,page);
		int count=setCty.selectCourseTypeCount(cty);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//将集合放入请求作用域
		request.setAttribute("CourseTypeList", CourseTypeList);
		//请求转发
		request.getRequestDispatcher("pages/jsp/CourseTypeInfo/CourseTypeList.jsp").forward(request, response);
		
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
	public void AddCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取新增的表单信息
		String coursetypename=request.getParameter("coursetypename");
		CourseType cty=new CourseType();
		cty.setCoursetypename(coursetypename);
		result=setCty.addCourseType(cty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('课程类别添加成功');location.href='pages/jsp/CourseTypeInfo/AddCourseType.jsp'</script>");
		}
		else {
			out.print("<script>alert('课程类别添加失败');location.href='pages/jsp/CourseTypeInfo/AddCourseType.jsp'</script>");
		}
		
	}
	/**
	 * 编辑页面渲染的Servlet
	* @Title: XREditCourseType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void XREditCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cypid=request.getParameter("cypid");
		List<CourseType> courseList=setCty.XRCoursetype(cypid);
		request.setAttribute("XrcourseList", courseList);
		request.getRequestDispatcher("pages/jsp/CourseTypeInfo/EditCourseType.jsp").forward(request, response);
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
	public void DetCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取删除的的类别ID
		String cypid=request.getParameter("cypid");
		result=setCty.detCourseType(cypid);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('删除成功');location.href='CourseType.NewDO?method=List'</script>");
		}else {
			out.print("<script>alert('删除失败');location.href='CourseType.NewDO?method=List'</script>");
		}
	}
	/**
	 *编辑的Servlet
	* @Title: EditCourseType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取编辑的相关信息
		String CourseTypeId=request.getParameter("cypid");
		String coursetypename=request.getParameter("coursetypename");
		CourseType cty=new CourseType();
		cty.setCoursetypename(coursetypename);
		cty.setCoursetypeid(CourseTypeId);
		result=setCty.editCourseType(cty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('编辑成功');location.href='CourseType.NewDO?method=List'</script>");
		}
		else {
			out.print("<script>alert('编辑失败');location.href='CourseType.NewDO?method=List'</script>");
		}
	}
	
	
}
