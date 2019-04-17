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
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�EditDep 
 * �������� ����Ժϵ��Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:01:01
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:01:01
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
@WebServlet("/Depinfo.newDo")
public class DepInfoServlet extends HttpServlet{
	//����һ�������ݿ��ѯ�������
	 DepInfoService setDep=new  DepInfoService();
	 //�нӷ��ؽ��
	 private int result;
	@Override
	//����ͨ��a��ǩ�ύ�Ķ� ����doget����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡa��ǩ�������ķ���
		String method=request.getParameter("method");
	
		PrintWriter out=response.getWriter();
	
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
	 * Ժϵ���ż��
	* @Title: ajaxCKCode 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void CKCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���д�Ĵ���
		String CKdepInfoCode=request.getParameter("depInfoCode");
		result=setDep.doAjaxCKCode(CKdepInfoCode);
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
	 * method=List��ʵ�ַ���
	* @Title: selectDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û�������Ժϵ����
		String depinfoname=request.getParameter("depinfoname");
		//��ȡ��ǰҳ
		String pagging=request.getParameter("paging");
		int num=(pagging==null?1:Integer.parseInt(pagging));
		PrintWriter out=response.getWriter();
		Paging page=new Paging();
		//��ǰҳ
		page.setPage(num);
		//ÿҳ��ʾ�ļ�¼����
		page.setPagesize(10);
		//����һ��Ժϵ����
		Depinfo dep=new Depinfo();
		//����name����
		dep.setDepinfoname(depinfoname);//�����б���Ⱦʱ������һ���յ�dep
		//��������service�ķ��񷽷�
		List<Depinfo> DepList=setDep.selectDepInfoList(dep,page);
		//�ܼ�¼����
		int count=setDep.selectDepInfoListCount(dep);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		//��ҳ��
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//�����ع�����list����request������
		request.setAttribute("DepList", DepList);
		//�������������Ļ���
		request.setAttribute("depName", depinfoname);
		//������ת��
		request.getRequestDispatcher("pages/jsp/DepInfo/DepList.jsp").forward(request, response);
	}
	/**
	 * method=Add��ʵ�ַ���
	* @Title: AddDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void AddDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡ����Ϣ
		String depInfoCode=request.getParameter("depinfocode");
		String depinfoname=request.getParameter("depinfoname");
		String depinfoPreOfTech=request.getParameter("depinfoPreOfTech");
		String depinfoAssTeach=request.getParameter("depinfoAssTeach");
		Depinfo addDep=new Depinfo();
		addDep.setDepInfoCode(depInfoCode);
		addDep.setDepinfoname(depinfoname);
		addDep.setDepinfoPreOfTech(depinfoPreOfTech);
		addDep.setDepinfoAssTeach(depinfoAssTeach);
		//��������ʵ�����еķ���
		result=setDep.addDepInfo(addDep);
		if(result!=0) {
			out.print("<script>alert('Ժϵ��ӳɹ�');location.href='pages/jsp/DepInfo/AddDep.jsp'</script>");
		}
		else {
			out.print("<script>alert('Ժϵ���ʧ��');location.href='pages/jsp/DepInfo/AddDep.jsp'</script>");
		}
	}
	/**
	 * method=Edit��ʵ�ַ���
	* @Title: EditDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
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
		//���ñ༭���е�ʵ�ַ���
		result=setDep.updateDep(editDep);
		if(result!=0) {
			out.print("<script>alert('Ժϵ�༭�ɹ�');location.href='Depinfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('Ժϵ�༭ʧ��');location.href='Depinfo.newDo?method=List'</script>");
		}
	}
	/**
	 * ��̬��Ⱦ�༭��Ϣ��ʵ�ַ���
	* @Title: XREditDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void XREditDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༭���ӵ�id����
		String id=request.getParameter("depid");
		Depinfo editDep=new Depinfo();
		editDep.setDepinfoId(id);
		List<Depinfo> DepList=setDep.selectDepInfoList(editDep,null);
		request.setAttribute("editdep",DepList);
		//������ת�����༭ҳ��
		request.getRequestDispatcher("pages/jsp/DepInfo/EditDep.jsp").forward(request, response);
	} 
	/**
	 * method=Det��ʵ�ַ���
	* @Title: DetDep 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void DetDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡ����ID
		String id=request.getParameter("depid");
		//����ɾ��ʵ����
		result=setDep.detDep(id);
		if(result!=0) {
			out.print("<script>alert('Ժϵɾ���ɹ�');location.href='Depinfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('Ժϵɾ��ʧ��');location.href='Depinfo.newDo?method=List'</script>");
		}
	} 
}
