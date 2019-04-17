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
import com.stu.bean.Spilinfo;
import com.stu.bean.Stuinfo;
import com.stu.service.ClassInfoService;
import com.stu.service.SpInfoService;
import com.stu.service.StuService;
//映射RUL
@WebServlet("/StuInfo.newDO")
/**
 *	学生Servlet
 * 项目名称：StuManager
 * 类名称：StuServlet 
 * 类描述： 
 * 创建人：kk
 * 创建时间：2019年1月1日 下午8:48:57
 * 修改人：kk
 * 修改时间：2019年1月1日 下午8:48:57
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月1日
 */
public class StuServlet extends HttpServlet{
	//创建班级 的服务类对象
	ClassInfoService classInfoSve=new ClassInfoService();
	//创建院系的服务类对象
	SpInfoService setSp=new SpInfoService();
	private int result=0;
	//创建学生的服务类对象
	StuService setStu=new StuService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取method
		String method=request.getParameter("method");
		if("List".equals(method)) {
			selectStuInfo(request, response);
		}
		else if("ajaxCKCode".equals(method)) {
			CKCode(request, response);
		}
		else if("DepSpClsList".equals(method)) {
			depSpClsList(request, response);
		}
		else if("Add".equals(method)) {
			addStuInfo(request,response);
		}
		else if("Edit".equals(method)) {
			//渲染编辑页面
			xREditStuInfo(request,response);
		}
		else if("Det".equals(method)) {
			detStuInfo(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取method
		String method=request.getParameter("method");
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectStuInfo(request, response);
		}
		else if("Add".equals(method)) {
			addStuInfo(request,response);
		}
		else if("Edit".equals(method)) {
			EditStuInfo(request,response);
		}
		else if("Det".equals(method)) {
			detStuInfo(request,response);
		}
	}
	public void CKCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的代号
		String CKStuInfoCode=request.getParameter("stucode");
		result=setStu.doAjaxCKCode(CKStuInfoCode);
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
	 * 根据获取的学生信息，执行查询的方法
	* @Title: selectStuInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		//获取当前页
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		
		//获取用户传来的相关信息
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classInfoId=request.getParameter("classselect");
		String stuName=request.getParameter("stuName");
		//创建学生实体对象
		Stuinfo stuInfo=new Stuinfo();
		//将获取到的信息设置到实体对象
		stuInfo.setDepinfoId(depInfoId);
		stuInfo.setSpilinfoid(spInfoId);
		stuInfo.setClassinfoid(classInfoId);
		stuInfo.setStdinfoname(stuName);
		//将学生对象放入request作用域，用于回填
		request.setAttribute("HTStuInfo", stuInfo);
		//调用查询学生信息的方法
		List<Stuinfo> stuList=setStu.selectStuInfo(stuInfo,page);
		int count=setStu.selectStuInfoCount(stuInfo);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//将返回的集合放入request中
		request.setAttribute("stuList", stuList);
		//调用院系和专业名称的查询方法
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		//将存放院系实体对象的集合放入request中
		request.setAttribute("DepInfoList", depInfoList);
		
		//request.setAttribute("SpList", spInfoList);
		//将请求转发之list页面
		request.getRequestDispatcher("pages/jsp/StuInfo/StuList.jsp").forward(request, response);
	}
	/**
	 * 学生信息添加的Servlet
	* @Title: addStuInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写的信息
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classInfoId=request.getParameter("classselect");
		String stdinfocode=request.getParameter("stdinfocode");
		String stdinfoname=request.getParameter("stdinfoname");
		String stdinfosex=request.getParameter("stdinfosex");
		String stdinfocard=request.getParameter("stdinfocard");
		String stdinfobirthd=request.getParameter("stdinfobirthd");
		String stdinfonatns=request.getParameter("stdinfonatns");
		String stdinfotel=request.getParameter("stdinfotel");
		String stdinfoemail=request.getParameter("stdinfoemail");
		String stdinfoyear=request.getParameter("stdinfoyear");
		
		Stuinfo stuInfo=new Stuinfo();
		//初始化学生对象
		stuInfo.setDepinfoId(depInfoId);
		stuInfo.setSpilinfoid(spInfoId);
		stuInfo.setClassinfoid(classInfoId);
		stuInfo.setStdinfocode(stdinfocode);
		stuInfo.setStdinfoname(stdinfoname);
		stuInfo.setStdinfosex(stdinfosex);
		stuInfo.setStdinfocard(stdinfocard);
		stuInfo.setStdinfobirthd(stdinfobirthd);
		stuInfo.setStdinfonatns(stdinfonatns);
		stuInfo.setStdinfotel(stdinfotel);
		stuInfo.setStdinfoemail(stdinfoemail);
		stuInfo.setStdinfoyear(stdinfoyear);
		//调用添加学生的方法
		result=setStu.addStuInfo(stuInfo);
		//获取响应流
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('学生添加成功');location.href='pages/jsp/StuInfo/AddStu.jsp'</script>");
		}
		else {
			out.print("<script>alert('学生添加失败');location.href='pages/jsp/StuInfo/AddStu.jsp'</script>");
		}
	}
	/**
	 * 学生编辑的Servlet
	* @Title: EditStuInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取编辑页面信息
		String stuId=request.getParameter("stuid");
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classInfoId=request.getParameter("classselect");
		String stdinfocode=request.getParameter("stdinfocode");
		String stdinfoname=request.getParameter("stdinfoname");
		String stdinfosex=request.getParameter("stdinfosex");
		String stdinfocard=request.getParameter("stdinfocard");
		String stdinfobirthd=request.getParameter("stdinfobirthd");
		String stdinfonatns=request.getParameter("stdinfonatns");
		String stdinfotel=request.getParameter("stdinfotel");
		String stdinfoemail=request.getParameter("stdinfoemail");
		String stdinfoyear=request.getParameter("stdinfoyear");
		Stuinfo stuInfo=new Stuinfo();
		//初始化学生对象
		stuInfo.setStdinfoid(stuId);
		stuInfo.setDepinfoId(depInfoId);
		stuInfo.setSpilinfoid(spInfoId);
		stuInfo.setClassinfoid(classInfoId);
		stuInfo.setStdinfocode(stdinfocode);
		stuInfo.setStdinfoname(stdinfoname);
		stuInfo.setStdinfosex(stdinfosex);
		stuInfo.setStdinfocard(stdinfocard);
		stuInfo.setStdinfobirthd(stdinfobirthd);
		stuInfo.setStdinfonatns(stdinfonatns);
		stuInfo.setStdinfotel(stdinfotel);
		stuInfo.setStdinfoemail(stdinfoemail);
		stuInfo.setStdinfoyear(stdinfoyear);
		//调用编辑学生信息的方法
		result=setStu.editStuInfo(stuInfo);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('编辑成功!');location.href='StuInfo.newDO?method=List'</script>");
		}
		else {
			out.print("<script>alert('编辑失败!');location.href='StuInfo.newDO?method=List'</script>");
		}
	}
	/**
	 * 学生信息删除的Servlet
	* @Title: detStuInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void detStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取删除学生的ID
		String stuId=request.getParameter("stuid");
		//执行删除学生信息的方法
		result=setStu.detStuInfo(stuId);
		//获取响应流
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('删除成功!');location.href='StuInfo.newDO?method=List'</script>");
		}
		else {
			out.print("<script>alert('删除失败!');location.href='StuInfo.newDO?method=List'</script>");
		}
	}
	/**
	 * 获取院系和专业信息的Servlet
	* @Title: depSpClsList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void depSpClsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//执行院系和专业信息查询的方法
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		request.setAttribute("depInfoList", depInfoList);
		//将请求转发到学生添加界面
		request.getRequestDispatcher("pages/jsp/StuInfo/AddStu.jsp").forward(request, response);
	}
	/**
	 * 学生编辑渲染的Servlet
	* @Title: xREditStuInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void xREditStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取班级学生的ID
		String stuInfoId=request.getParameter("stuid");
		Stuinfo stuInfo=new Stuinfo();
		stuInfo.setStdinfoid(stuInfoId);
		//调用查询方法
		List<Stuinfo> stuInfoList=setStu.selectStuInfo(stuInfo,null);
		List<Depinfo> depInfoList=setSp.selectDepName();
		//将结果放入request
		request.setAttribute("depInfoList", depInfoList);
		request.setAttribute("stuInfoList", stuInfoList);
		//将请求转发到编辑页面
		request.getRequestDispatcher("pages/jsp/StuInfo/EditStu.jsp").forward(request, response);
	}
}
	

