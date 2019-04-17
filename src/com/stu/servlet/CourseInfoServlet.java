package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.bean.CourseInfo;
import com.stu.bean.CourseType;
import com.stu.bean.Paging;
import com.stu.service.CourseInfoService;
import com.stu.service.CourseTypeService;
/**
 * 
 * 项目名称：StuManager
 * 类名称：CourseInfoServlet 
 * 类描述： 课程信息Servlet
 * 创建人：kk
 * 创建时间：2019年1月7日 下午8:45:31
 * 修改人：kk
 * 修改时间：2019年1月7日 下午8:45:31
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
@WebServlet("/courseInfo.newDo")
public class CourseInfoServlet extends HttpServlet{
	private int result;
	//课程服务类对象
	CourseInfoService setCInfo=new CourseInfoService();
	CourseTypeService setCty=new CourseTypeService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取a标签传过来的方法
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectCourseInfo(request, response);
		}
		else if("selCourseInfo".equals(method)) {
			selectCourseName(request, response);
		}
		else if("ajaxCKCode".equals(method)) {
			ajaxCKCourseCode(request,response);
		}
		else if("Add".equals(method)) {
			AddCourseInfo(request,response);
		}
		else if("Edit".equals(method)) {
			XREditCourseInfo(request,response);
		}
		else if("Det".equals(method)) {
			DetCourseInfo(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String method=request.getParameter("method");
		if("List".equals(method)) {
			selectCourseInfo(request, response);
		}
		else if("Add".equals(method)) {
			AddCourseInfo(request,response);
		}
		else if("Edit".equals(method)) {
			EditCourseInfo(request,response);
		}
	}
	/**
	 * 查询课程名称方法
	* @Title: selectCourseName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectCourseName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CourseType> courseInfo=setCty.selectCourseType(null,null);
		request.setAttribute("courseInfo", courseInfo);
		request.getRequestDispatcher("pages/jsp/CourseInfo/AddCourseInfo.jsp").forward(request, response);
	}
	/**
	 * ajax代号检测方法
	* @Title: ajaxCKCourseCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void ajaxCKCourseCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取课程代号
		String cId=request.getParameter("courseCode");
		//调用代号检测方法
		result=setCInfo.checkCode(cId);
		PrintWriter out=response.getWriter();
		
		if(result==0) {
			//不存在
			out.print(1);
		}
		else if(result==1) {
			//存在
			out.print(0);
		}
	}
	/**
	 * 课程信息查询Servlet
	* @Title: selectCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		
		//获取课程名称
		String courseinfoname=request.getParameter("courseinfoname");
		CourseInfo cInfo=new CourseInfo();
		cInfo.setCourseinfoname(courseinfoname);
		//用于回填
		request.setAttribute("courseName", courseinfoname);
		//调用服务类中的查询方法
		List<CourseInfo> courseInfoList=setCInfo.selectCourseInfo(cInfo,page);
		int count=setCInfo.selectCourseInfoCount(cInfo);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//将返回的集合放入请求作用域
		request.setAttribute("courseInfoList", courseInfoList);
		//将请求转发至List页面
		request.getRequestDispatcher("pages/jsp/CourseInfo/CourseInfoList.jsp").forward(request, response);
	}
	/**
	 * 添加课程信息Servlet
	* @Title: AddCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void AddCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单信息
		String courseTypeId=request.getParameter("Courseselect");
		String courseinfocode=request.getParameter("courseinfocode");
		String courseinfoname=request.getParameter("courseinfoname");
		String courseinfoproj=request.getParameter("courseinfoproj");
		String courseinforstper=request.getParameter("courseinforstper");
		String courseinfopraper=request.getParameter("courseinfopraper");
		String courseinfocrehor=request.getParameter("courseinfocrehor");
		String courseinformk=request.getParameter("courseinformk");
		CourseInfo courseInfo=new CourseInfo();
		courseInfo.setCoursetypeid(courseTypeId);
		courseInfo.setCourseinfocode(courseinfocode);
		courseInfo.setCourseinfoname(courseinfoname);
		courseInfo.setCourseinfoproj(courseinfoproj);
		courseInfo.setCourseinforstper(courseinforstper);
		courseInfo.setCourseinfopraper(courseinfopraper);
		courseInfo.setCourseinfocrehor(courseinfocrehor);
		courseInfo.setCourseinformk(courseinformk);
		//调用新增方法
		result=setCInfo.addCourseInfo(courseInfo);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('课程新增成功');location.href='pages/jsp/CourseInfo/AddCourseInfo.jsp'</script>");
		}
		else {
			out.print("<script>alert('课程新增失败');location.href='pages/jsp/CourseInfo/AddCourseInfo.jsp'</script>");
		}
	}
	/**
	 * 渲染编辑页面Servlet
	* @Title: XREditCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void XREditCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseId=request.getParameter("cid");
		CourseInfo courseInfo=new CourseInfo();
		courseInfo.setCourseinfoid(courseId);
		List<CourseInfo> xRCInfoList=setCInfo.selectCourseInfo(courseInfo,null);
		request.setAttribute("xRCInfoList", xRCInfoList);
		List<CourseType> cyList=setCty.selectCourseType(null,null);
		request.setAttribute("cyList", cyList);
		request.getRequestDispatcher("pages/jsp/CourseInfo/EditCourseInfo.jsp").forward(request, response);
	}
	/**
	 * 删除课程信息Servlet
	* @Title: DetCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void DetCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取课程Id
		String coureseId=request.getParameter("cid");
		result=setCInfo.detCourseInfo(coureseId);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('删除成功!');location.href='courseInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('删除失败!');location.href='courseInfo.newDo?method=List'</script>");
		}
	}
	/**
	 * 编辑课程信息Servlet
	* @Title: EditCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取编辑页面的信息
		String courseInfoId=request.getParameter("courseid");
		String courseTypeId=request.getParameter("CTypeselect");
		String courseinfocode=request.getParameter("courseinfocode");
		String courseinfoname=request.getParameter("courseinfoname");
		String courseinfoproj=request.getParameter("courseinfoproj");
		String courseinforstper=request.getParameter("courseinforstper");
		String courseinfopraper=request.getParameter("courseinfopraper");
		String courseinfocrehor=request.getParameter("courseinfocrehor");
		String courseinformk=request.getParameter("courseinformk");
		CourseInfo courseInfo=new CourseInfo();
		courseInfo.setCourseinfoid(courseInfoId);
		courseInfo.setCoursetypeid(courseTypeId);
		courseInfo.setCourseinfocode(courseinfocode);
		courseInfo.setCourseinfoname(courseinfoname);
		courseInfo.setCourseinfoproj(courseinfoproj);
		courseInfo.setCourseinforstper(courseinforstper);
		courseInfo.setCourseinfopraper(courseinfopraper);
		courseInfo.setCourseinfocrehor(courseinfocrehor);
		courseInfo.setCourseinformk(courseinformk);
		result=setCInfo.editCourseInfo(courseInfo);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('编辑成功!');location.href='courseInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('编辑失败!');location.href='courseInfo.newDo?method=List'</script>");
		}
	}
	
}
		
