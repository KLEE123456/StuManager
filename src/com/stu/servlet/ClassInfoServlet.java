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
//ӳ��URL��ַ
@WebServlet("/ClassInfo.newDo")
public class ClassInfoServlet extends HttpServlet{
	//�����༶����������Ժϵ���������
	ClassInfoService classInfoSve=new ClassInfoService();
	SpInfoService setSp=new SpInfoService();
	private int result=0;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
		if("List".equals(method)) {
			selectClass(request, response);
		}
		else if("ajaxCKCode".equals(method)) {
			CKCode(request, response);
		}
		else if("ajaxList".equals(method)) {
			//��ѯ�༶��ajax
			ajaxClassList(request,response);
		}
		else if("DepSpList".equals(method)) {
			//Ժϵ��רҵ��ѯ
			depAndSpList(request, response);
		}
		else if("Add".equals(method)) {
			addClass(request,response);
		}
		else if("Edit".equals(method)) {
			//�༭��Ⱦ
			xREditClass(request,response);
		}
		else if("Det".equals(method)) {
			detClass(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
		//��ȡ�û���д�Ĵ���
		String CKClsInfoCode=request.getParameter("clsInfoCode");
		result=classInfoSve.doAjaxCKCode(CKClsInfoCode);
		PrintWriter out=response.getWriter();
		if(result==1) {
			//�����Ѵ���
			out.print(0);
		}
		else {
			//���Ų�����
			out.print(1);
		}
	}
	/**
	 * �༶��Ϣ��ѯServlet
	* @Title: selectClass 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���д�������Ϣ
		String classInfoName=request.getParameter("classinfoname");
		//��ҳ��Ķ���
		Paging page = new Paging();
		String depinfoId=request.getParameter("Depselect");
		String spilinfoid=request.getParameter("Spselect");
		//��ȡ��ǰҳ 
		String pagenum = request.getParameter("paging");	
		//�жϵ�ǰ�Ƿ�ҳ�룬���û�д����ǵ�ǰҳ����1
		page.setPage(pagenum==null?1:Integer.parseInt(pagenum));
		//ÿҳ������
		page.setPagesize(10);
		Classinfo classInfo=new Classinfo();
	
		//��ʼ���༶����
		classInfo.setClassinfoname(classInfoName);
		classInfo.setDepinfoId(depinfoId);
		classInfo.setSpilinfoid(spilinfoid);
		//��������� request�У����ڻ���
		request.setAttribute("HTClassInfo", classInfo);
		//���ݲ�ѯ������ȡ��ǰ���ز�ѯ�������
		int count = classInfoSve.selectClassInfoCount(classInfo);
		//���㵱ǰ��ҳ��
		int totalNum = count/page.getPagesize();
		//�����¼����%��ҳ��С��Ϊ0������ҳ����1
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		//������ҳ��
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//��װ��ѯ����ļ���
		List<Classinfo> classInfoList=classInfoSve.selectClassInfo(classInfo,page);
		//Ժϵ��רҵ��Ϣ��ѯ
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		//������ ����request��
		request.setAttribute("ClassList",classInfoList);
		request.setAttribute("DepInfoList", depInfoList);
		//request.setAttribute("SpList", spInfoList);
		//������ת����Listҳ��
		request.getRequestDispatcher("pages/jsp/ClassInfo/ClassList.jsp").forward(request, response);
	}
	/**
	 * Ժϵ��רҵ��ѯServlet
	* @Title: depAndSpList 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
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
	 * �༶���Servlet
	* @Title: addClass 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void addClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ������д����Ϣ
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classcode=request.getParameter("classcode");
		String classInfoName=request.getParameter("classname");
		String classPnum=request.getParameter("pnum");
		String classmk=request.getParameter("classmk");
		Classinfo clsInfo=new Classinfo();
		//��ʼ���༶����
		clsInfo.setDepinfoId(depInfoId);
		clsInfo.setSpilinfoid(spInfoId);
		clsInfo.setClassinfocode(classcode);
		clsInfo.setClassinfoname(classInfoName);
		clsInfo.setClassinfosum(classPnum);
		clsInfo.setClassinformk(classmk);
		//���ð༶��Ϣ��ӵķ���
		result=classInfoSve.addClassInfo(clsInfo);
		//��ȡ��Ӧ������
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('�༶��ӳɹ�');location.href='pages/jsp/ClassInfo/AddCls.jsp'</script>");
		}
		else {
			out.print("<script>alert('�༶���ʧ��');location.href='pages/jsp/ClassInfo/AddCls.jsp'</script>");
		}
		
	}
	/**
	 * ��Ⱦ�༭ҳ���Servlet
	* @Title: xREditClass 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void xREditClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༭ID
		String classid=request.getParameter("classid");
		Classinfo classinfo=new Classinfo();
		classinfo.setClassinfoid(classid);
		//ͨ��ID��ѯ�༶��Ϣ
		List<Classinfo> classList=classInfoSve.selectClassInfo(classinfo,null);
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		//�����Ϸ���request��
		request.setAttribute("depInfoList", depInfoList);
		//request.setAttribute("spList", spInfoList);
		request.setAttribute("classList", classList);
		//������ת�����༭ҳ��
		request.getRequestDispatcher("pages/jsp/ClassInfo/EditCls.jsp").forward(request, response);
	}
	/**
	 * �༶��Ϣ�༭��Servlet
	* @Title: EditClass 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���д�ı༭��Ϣ
		String classId=request.getParameter("clsid");
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classinfocode=request.getParameter("classinfocode");
		String classinfoname=request.getParameter("classinfoname");
		String classinfosum=request.getParameter("classinfosum");
		String classinformk=request.getParameter("classinformk");
		Classinfo clsInfo=new Classinfo();
		//��ʼ���༶����
		clsInfo.setClassinfoid(classId);
		clsInfo.setDepinfoId(depInfoId);
		clsInfo.setSpilinfoid(spInfoId);
		clsInfo.setClassinfocode(classinfocode);
		clsInfo.setClassinfoname(classinfoname);
		clsInfo.setClassinfosum(classinfosum);
		clsInfo.setClassinformk(classinformk);
		//���ñ༭����
		result=classInfoSve.editClassInfo(clsInfo);
		PrintWriter out=response.getWriter();
		if(result==0) {
			out.print("<script>alert('�༭ʧ��!');location.href='ClassInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('�༭�ɹ�!');location.href='ClassInfo.newDo?method=List'</script>");
		}
	}
	/**
	 * �༶ɾ����Servlet
	* @Title: detClass 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void detClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ���İ༶ID
		String classId=request.getParameter("classid");
		//����ɾ���ķ���
		result=classInfoSve.detClassInfo(classId);
		//��ȡ��Ӧ������
		PrintWriter out=response.getWriter();
		if(result==0) {
			out.print("<script>alert('ɾ��ʧ��!');location.href='ClassInfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('ɾ���ɹ�!');location.href='ClassInfo.newDo?method=List'</script>");
		}
	}
	/**
	 * �༶��Ϣ��ѯ��Servlet��ajax��̬��ʾ��
	* @Title: ajaxClassList 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void ajaxClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡרҵID
		String spInfoId=request.getParameter("spInfoId");
		Classinfo clsInfo=new Classinfo();
		clsInfo.setSpilinfoid(spInfoId);
		//��װ��ѯ����Ľ����
		List<Classinfo> clsInfoList=classInfoSve.selectClassInfo(clsInfo,null);
		//��json����ת����json�ַ���
		String classInfo=JSON.toJSONString(clsInfoList);
		out.print(classInfo);
	}
	
}
