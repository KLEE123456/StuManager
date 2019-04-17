package com.stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.bean.TeacherInfo;
import com.stu.service.SpInfoService;
import com.stu.service.TeacherInfoService;
@WebServlet("/TeacherInfo.NewDo")
public class TeacherInfoServlet extends HttpServlet{
	TeacherInfoService setTeach=new TeacherInfoService();
	SpInfoService setSp=new SpInfoService();
	private int result;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取method
			String method=request.getParameter("method");
			if("List".equals(method)) {
				selectTeacherInfo(request, response);
			}
			else if("ajaxCKCode".equals(method)) {
				CKCode(request, response);
			}
			else if("DepAndTypeList".equals(method)) {
				DepAndTypeList(request, response);
			}
			else if("Add".equals(method)) {
				addTeacherInfo(request,response);
			}
			else if("Edit".equals(method)) {
				//渲染编辑页面
				xREditTeacherInfo(request,response);
			}
			else if("Det".equals(method)) {
				detTeacherInfo(request,response);
			}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取method
		String method=request.getParameter("method");
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectTeacherInfo(request, response);
		}
		else if("Add".equals(method)) {
			addTeacherInfo(request,response);
		}
		else if("Edit".equals(method)) {
			EditTeacherInfo(request,response);
		}
		else if("Det".equals(method)) {
			detTeacherInfo(request,response);
		}
	}
	/**
	 *教师代号检测Servlet方法
	* @Title: CKCode 
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
		String CKTchInfoCode=request.getParameter("tchcode");
		result=setTeach.doAjaxCKCode(CKTchInfoCode);
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
	 * 教师信息查询的Servlet方法
	* @Title: selectTeacherInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		
		String depInfoId=request.getParameter("Depselect");
		String teachName=request.getParameter("TeachName");
		TeacherInfo tch=new TeacherInfo();
		tch.setDepinfoid(depInfoId);
		tch.setTeachinfoname(teachName);
		List<TeacherInfo> teachList=setTeach.selectTeachInfo(tch,page);
		int count=setTeach.selectTeachInfoCount(tch);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		request.setAttribute("teachList", teachList);
		List<Depinfo> depInfoList=setSp.selectDepName();
		request.setAttribute("DepInfoList", depInfoList);
		request.setAttribute("HTTeachInfo", tch);
		request.getRequestDispatcher("pages/jsp/TeacherInfo/TeacherList.jsp").forward(request, response);
		
	}
	/**
	 * 院系和教师类别查询的Servlet方法
	* @Title: DepAndTypeList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void DepAndTypeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Depinfo> depInfoList=setSp.selectDepName();
		request.setAttribute("depInfoList", depInfoList);
		List<TeachType> teachTypeList=setTeach.selectTeachType();
		request.setAttribute("teachInfoList", teachTypeList);
		request.getRequestDispatcher("pages/jsp/TeacherInfo/AddTeach.jsp").forward(request, response);
	}
	/**
	 * 教师信息编辑的Servlet方法
	* @Title: EditTeacherInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户修改的信息
		String tchId=request.getParameter("tchid");
		String depInfoId=request.getParameter("Depselect");
		String teachTypeId=request.getParameter("tchTypeselect");
		String teachinfocode=request.getParameter("teachinfocode");
		String teachinfoname=request.getParameter("teachinfoname");
		String teachinfosex=request.getParameter("teachinfosex");
		String teachknowl=request.getParameter("teachknowl");
		String teachinfodeg=request.getParameter("teachinfodeg");
		String teachinfospec=request.getParameter("teachinfospec");
		String teachinfotitle=request.getParameter("teachinfotitle");
		String teachinformk=request.getParameter("teachinformk");
		//创建教师实体对象
		TeacherInfo tchInfo=new TeacherInfo();
		tchInfo.setTeachinfoid(tchId);
		tchInfo.setDepinfoid(depInfoId);
		tchInfo.setTeachtypeid(teachTypeId);
		tchInfo.setTeachinfocode(teachinfocode);
		tchInfo.setTeachinfoname(teachinfoname);
		tchInfo.setTeachinfosex(teachinfosex);
		tchInfo.setTeachknowl(teachknowl);
		tchInfo.setTeachinfodeg(teachinfodeg);
		tchInfo.setTeachinfospec(teachinfospec);
		tchInfo.setTeachinfotitle(teachinfotitle);
		tchInfo.setTeachinformk(teachinformk);
		//调用编辑方法
		result=setTeach.editTeachInfo(tchInfo);
		//获取响应流对象
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('编辑成功!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('编辑失败!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
		
	}
	/**
	 *	教师信息添加的servlet方法
	* @Title: addTeacherInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的信息
		String depInfoId=request.getParameter("Depselect");
		String teachTypeId=request.getParameter("TeachTypeselect");
		String teachinfocode=request.getParameter("teachinfocode");
		String teachinfoname=request.getParameter("teachinfoname");
		String teachinfosex=request.getParameter("teachinfosex");
		String teachknowl=request.getParameter("teachknowl");
		String teachinfodeg=request.getParameter("teachinfodeg");
		String teachinfospec=request.getParameter("teachinfospec");
		String teachinfotitle=request.getParameter("teachinfotitle");
		String teachinformk=request.getParameter("teachinformk");
		TeacherInfo tchInfo=new TeacherInfo();
		//初始化教师实体对象
		tchInfo.setDepinfoid(depInfoId);
		tchInfo.setTeachtypeid(teachTypeId);
		tchInfo.setTeachinfocode(teachinfocode);
		tchInfo.setTeachinfoname(teachinfoname);
		tchInfo.setTeachinfosex(teachinfosex);
		tchInfo.setTeachknowl(teachknowl);
		tchInfo.setTeachinfodeg(teachinfodeg);
		tchInfo.setTeachinfospec(teachinfospec);
		tchInfo.setTeachinfotitle(teachinfotitle);
		tchInfo.setTeachinformk(teachinformk);
		result=setTeach.addTeachInfo(tchInfo);
		//获取响应流
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('教师添加成功');location.href='pages/jsp/TeacherInfo/AddTeach.jsp'</script>");
		}
		else {
			out.print("<script>alert('教师添加失败');location.href='pages/jsp/TeacherInfo/AddTeach.jsp'</script>");
		}
		
	}
	/**
	 * 编辑渲染方法
	* @Title: xREditTeacherInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void xREditTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teachId=request.getParameter("tchid");
		TeacherInfo tchInfo=new TeacherInfo();
		tchInfo.setTeachinfoid(teachId);
		List<TeacherInfo> tList=setTeach.selectTeachInfo(tchInfo,null);
		request.setAttribute("tList",tList);
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<TeachType> teachTypeList=setTeach.selectTeachType();
		//将结果放入request
		request.setAttribute("teachInfoList", teachTypeList);
		request.setAttribute("depInfoList", depInfoList);
		request.getRequestDispatcher("pages/jsp/TeacherInfo/EditTeach.jsp").forward(request, response);
	}
	/**
	 * 教师信息删除方法
	* @Title: detTeacherInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void detTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tchInfoId=request.getParameter("Tchid");
		result=setTeach.detTeachInfo(tchInfoId);
		//获取响应流
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('删除成功!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('删除失败!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
	}
	
}
	

