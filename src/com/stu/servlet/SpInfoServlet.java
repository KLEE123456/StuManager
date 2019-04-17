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
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.bean.Spilinfo;
import com.stu.service.ClassInfoService;
import com.stu.service.SpInfoService;
/**
 * 
 * 项目名称：StuManager
 * 类名称：EditSp 
 * 类描述： 对专业信息进行增删改查的Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午11:11:00
 * 修改人：kk
 * 修改时间：2018年12月21日 下午11:11:00
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
@WebServlet("/Spinfo.newDo")
public class SpInfoServlet extends HttpServlet{
	//具体实现方法的类对象
	SpInfoService setSp=new SpInfoService();
	
	ClassInfoService setCs=new ClassInfoService();
	
	 //承接返回结果
	 private int result;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取a标签传过来的方法
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//对方法的的值进行判断，跳到相应的方法实现
		if("List".equals(method)) {
			selectSp(request, response);
		}
		else if("ajaxCKCode".equals(method)) {
			CKCode(request, response);
		}
		else if("ajaxList".equals(method)) {
			ajaxSpList(request,response);
		}
		else if("DepNameList".equals(method)) {
			AddJspDepName(request,response);
		}
		else if("Add".equals(method)) {
			AddSp(request,response);
		}
		else if("Edit".equals(method)) {
			XREditSp(request,response);
		}
		else if("Det".equals(method)) {
			DetSp(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String method=request.getParameter("method");
		if("List".equals(method)) {
			selectSp(request, response);
		}
		else if("Add".equals(method)) {
			AddSp(request,response);
		}
		else if("Edit".equals(method)) {
			EditSp(request,response);
		}
	}
	/**
	 * 专业代号检测Servlet方法
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
		String CKspInfoCode=request.getParameter("spilcode");
		result=setSp.doAjaxCKCode(CKspInfoCode);
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
	* @Title: selectSp 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void selectSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Spilinfo spinfo=new Spilinfo();
		Paging page=new Paging();
		//获取当前页
		String paging=request.getParameter("paging");
		//进行为空判断
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		page.setPagesize(10);
		//获取表单信息
		String depId=request.getParameter("select");
		String spName=request.getParameter("spinfoname");
		request.setAttribute("HTSpInfoName", spName);
		//添加到实体中
		spinfo.setDepinfoid(depId);
		spinfo.setSpilinfoname(spName);
		request.setAttribute("spinfos", spinfo);
		//将对象传入service的服务方法 
		List<Spilinfo> splist=setSp.selectSpInfo(spinfo,page);
		//获取记录条数
		int count=setSp.selectSpInfoCount(spinfo);
		//计算总页数
		int pageNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			pageNum++;
		}
		page.setPagenum(pageNum);
		request.setAttribute("paging", page);
		//将返回过来的list放入request作用域
		request.setAttribute("Splist", splist);
		//创建存储院系信息的list承接返回的list
		List<Depinfo> deplist=setSp.selectDepName();
		//将deplist放入请求作用域
		request.setAttribute("Deplist",deplist);
		//将请求转发
		request.getRequestDispatcher("pages/jsp/SpInfo/SpList.jsp").forward(request, response);
		
	}
	/**
	 * method=Add实现方法
	* @Title: AddSp 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void AddSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单信息
		String spilinfocode=request.getParameter("spilinfocode");
		String spilinfoname=request.getParameter("spilinfoname");
		String spilinfoaim=request.getParameter("spilinfoaim");
		String spilinfoprodire=request.getParameter("spilinfoprodire");
		String depinfoid=request.getParameter("select");
		//创建专业实体对象
		Spilinfo sp=new Spilinfo();
		sp.setSpilinfocode(spilinfocode);
		sp.setSpilinfoname(spilinfoname);
		sp.setSpilinfoaim(spilinfoaim);
		sp.setSpilinfoprodire(spilinfoprodire);
		sp.setDepinfoid(depinfoid);
		//调用新增实现方法
		result=setSp.addSpInfo(sp);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('专业添加成功!');location.href='pages/jsp/SpInfo/AddSp.jsp'</script>");
		}
		else {
			out.print("<script>alert('专业添加失败!');location.href='pages/jsp/SpInfo/AddSp.jsp'</script>");
		}
	}
	/**
	 * method=Edit实现方法（get请求过来）
	* @Title: XREditSp 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void XREditSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取专业ID
		String spId=request.getParameter("spid");
		Spilinfo sp=new Spilinfo();
		sp.setSpilinfoid(spId);
		//调用实现方法
		List<Spilinfo> splist=setSp.selectSpInfo(sp,null);
		//将集合放入request域中
		request.setAttribute("spList",splist);
		//将请求转发到编辑界面
		request.getRequestDispatcher("pages/jsp/SpInfo/EditSp.jsp").forward(request, response);	
	}
	/**
	 * 院系删除方法
	* @Title: DetSp 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void DetSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取URL参数
		String spInfoId=request.getParameter("spid");
		PrintWriter out=response.getWriter();
		result=setCs.selectClassNum(spInfoId);
		if(result!=0) {
			out.print("<script>alert('该专业不能删除');location.href='Spinfo.newDo?method=List'</script>");
		}
		else {
			int flag;
			flag=setSp.DetSpInfo(spInfoId);
			if(flag==0) {
				out.print("<script>alert('删除失败');location.href='Spinfo.newDo?method=List'</script>");
			}
			else {
				out.print("<script>alert('删除成功');location.href='Spinfo.newDo?method=List'</script>");

			}
		}
	}
	/**
	 * method=Edit实现方法（post请求过来）
	* @Title: EditSp 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void EditSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单信息
		String spilinfoid=request.getParameter("Spid");
		String spilinfocode=request.getParameter("spilinfocode");
		String spilinfoname=request.getParameter("spilinfoname");
		String spilinfoaim=request.getParameter("spilinfoaim");
		String spilinfoprodire=request.getParameter("spilinfoprodire");
		//将表单信息放入创建的专业实体对象
		Spilinfo sp=new Spilinfo();
		sp.setSpilinfoid(spilinfoid);
		sp.setSpilinfocode(spilinfocode);
		sp.setSpilinfoname(spilinfoname);
		sp.setSpilinfoaim(spilinfoaim);
		sp.setSpilinfoprodire(spilinfoprodire);
		//调用具体实现方法
		result=setSp.editSpInfo(sp);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('专业编辑成功!');location.href='Spinfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('专业编辑失败!');location.href='Spinfo.newDo?method=List'</script>");
		}
	}
	/**
	 *新增专业页面的院系列表实现方法
	* @Title: AddJspDepName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void AddJspDepName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用查询院系名称的实现方法
		List<Depinfo> Deplist=setSp.selectDepName();
		request.setAttribute("Deplist", Deplist);
		//将请求转发到新增页面
		request.getRequestDispatcher("pages/jsp/SpInfo/AddSp.jsp").forward(request, response);
	}
	public void ajaxSpList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String depInfoId=request.getParameter("depInfoId");
		Spilinfo sp=new Spilinfo();
		sp.setDepinfoid(depInfoId);
		List<Spilinfo> spInfoList=setCs.selectSpName(sp);
		String JsnStr=JSON.toJSONString(spInfoList);
		out.print(JsnStr);
		//request.getRequestDispatcher("pages/jsp/ClassInfo/Cls.jsp").forward(request, response);
	}
}
	

