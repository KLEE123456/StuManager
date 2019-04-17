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
 * �γ����Servlet
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�CourseTypeServlet 
 * �������� 
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��7�� ����3:07:53
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��7�� ����3:07:53
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��7��
 */
@WebServlet("/CourseType.NewDO")
public class CourseTypeServlet extends HttpServlet{
	private int result=0;
	CourseTypeService  setCty=new CourseTypeService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡa��ǩ�������ķ���
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
	 * ��ѯ��Servlet
	* @Title: selectTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		//��ȡ��ѯ�����Ϣ
		String coursetypename=request.getParameter("coursetypename");
		CourseType cty=new CourseType();
		cty.setCoursetypename(coursetypename);
		//���ڻ���
		request.setAttribute("cyName", coursetypename);
		List<CourseType> CourseTypeList=setCty.selectCourseType(cty,page);
		int count=setCty.selectCourseTypeCount(cty);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//�����Ϸ�������������
		request.setAttribute("CourseTypeList", CourseTypeList);
		//����ת��
		request.getRequestDispatcher("pages/jsp/CourseTypeInfo/CourseTypeList.jsp").forward(request, response);
		
	}
	/**
	 * ������Servlet����
	* @Title: AddTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void AddCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����ı���Ϣ
		String coursetypename=request.getParameter("coursetypename");
		CourseType cty=new CourseType();
		cty.setCoursetypename(coursetypename);
		result=setCty.addCourseType(cty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('�γ������ӳɹ�');location.href='pages/jsp/CourseTypeInfo/AddCourseType.jsp'</script>");
		}
		else {
			out.print("<script>alert('�γ�������ʧ��');location.href='pages/jsp/CourseTypeInfo/AddCourseType.jsp'</script>");
		}
		
	}
	/**
	 * �༭ҳ����Ⱦ��Servlet
	* @Title: XREditCourseType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void XREditCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cypid=request.getParameter("cypid");
		List<CourseType> courseList=setCty.XRCoursetype(cypid);
		request.setAttribute("XrcourseList", courseList);
		request.getRequestDispatcher("pages/jsp/CourseTypeInfo/EditCourseType.jsp").forward(request, response);
	}
	/**
	 * ɾ����Servlet
	* @Title: DetTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void DetCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ���ĵ����ID
		String cypid=request.getParameter("cypid");
		result=setCty.detCourseType(cypid);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('ɾ���ɹ�');location.href='CourseType.NewDO?method=List'</script>");
		}else {
			out.print("<script>alert('ɾ��ʧ��');location.href='CourseType.NewDO?method=List'</script>");
		}
	}
	/**
	 *�༭��Servlet
	* @Title: EditCourseType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༭�������Ϣ
		String CourseTypeId=request.getParameter("cypid");
		String coursetypename=request.getParameter("coursetypename");
		CourseType cty=new CourseType();
		cty.setCoursetypename(coursetypename);
		cty.setCoursetypeid(CourseTypeId);
		result=setCty.editCourseType(cty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('�༭�ɹ�');location.href='CourseType.NewDO?method=List'</script>");
		}
		else {
			out.print("<script>alert('�༭ʧ��');location.href='CourseType.NewDO?method=List'</script>");
		}
	}
	
	
}
