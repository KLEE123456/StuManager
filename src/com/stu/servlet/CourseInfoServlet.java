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
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�CourseInfoServlet 
 * �������� �γ���ϢServlet
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��7�� ����8:45:31
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��7�� ����8:45:31
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��7��
 */
@WebServlet("/courseInfo.newDo")
public class CourseInfoServlet extends HttpServlet{
	private int result;
	//�γ̷��������
	CourseInfoService setCInfo=new CourseInfoService();
	CourseTypeService setCty=new CourseTypeService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡa��ǩ�������ķ���
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
	 * ��ѯ�γ����Ʒ���
	* @Title: selectCourseName 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectCourseName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CourseType> courseInfo=setCty.selectCourseType(null,null);
		request.setAttribute("courseInfo", courseInfo);
		request.getRequestDispatcher("pages/jsp/CourseInfo/AddCourseInfo.jsp").forward(request, response);
	}
	/**
	 * ajax���ż�ⷽ��
	* @Title: ajaxCKCourseCode 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void ajaxCKCourseCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�γ̴���
		String cId=request.getParameter("courseCode");
		//���ô��ż�ⷽ��
		result=setCInfo.checkCode(cId);
		PrintWriter out=response.getWriter();
		
		if(result==0) {
			//������
			out.print(1);
		}
		else if(result==1) {
			//����
			out.print(0);
		}
	}
	/**
	 * �γ���Ϣ��ѯServlet
	* @Title: selectCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		
		//��ȡ�γ�����
		String courseinfoname=request.getParameter("courseinfoname");
		CourseInfo cInfo=new CourseInfo();
		cInfo.setCourseinfoname(courseinfoname);
		//���ڻ���
		request.setAttribute("courseName", courseinfoname);
		//���÷������еĲ�ѯ����
		List<CourseInfo> courseInfoList=setCInfo.selectCourseInfo(cInfo,page);
		int count=setCInfo.selectCourseInfoCount(cInfo);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//�����صļ��Ϸ�������������
		request.setAttribute("courseInfoList", courseInfoList);
		//������ת����Listҳ��
		request.getRequestDispatcher("pages/jsp/CourseInfo/CourseInfoList.jsp").forward(request, response);
	}
	/**
	 * ��ӿγ���ϢServlet
	* @Title: AddCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void AddCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����Ϣ
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
		//������������
		result=setCInfo.addCourseInfo(courseInfo);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('�γ������ɹ�');location.href='pages/jsp/CourseInfo/AddCourseInfo.jsp'</script>");
		}
		else {
			out.print("<script>alert('�γ�����ʧ��');location.href='pages/jsp/CourseInfo/AddCourseInfo.jsp'</script>");
		}
	}
	/**
	 * ��Ⱦ�༭ҳ��Servlet
	* @Title: XREditCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
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
	 * ɾ���γ���ϢServlet
	* @Title: DetCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void DetCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�γ�Id
		String coureseId=request.getParameter("cid");
		result=setCInfo.detCourseInfo(coureseId);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('ɾ���ɹ�!');location.href='courseInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('ɾ��ʧ��!');location.href='courseInfo.newDo?method=List'</script>");
		}
	}
	/**
	 * �༭�γ���ϢServlet
	* @Title: EditCourseInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditCourseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༭ҳ�����Ϣ
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
			out.print("<script>alert('�༭�ɹ�!');location.href='courseInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('�༭ʧ��!');location.href='courseInfo.newDo?method=List'</script>");
		}
	}
	
}
		
