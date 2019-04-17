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
 * ��ʦ���Servlet
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�TeachTypeServlet 
 * �������� 
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��7�� ����3:07:31
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��7�� ����3:07:31
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��7��
 */
public class TeachTypeServlet extends HttpServlet{
	private int result=0;
	TeachTypeService setTchType=new TeachTypeService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡa��ǩ�������ķ���
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
	public void selectTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		//��ȡ��ѯ�������Ϣ
		String tyName=request.getParameter("teachtypename");
		
		TeachType ty=new TeachType();
		
		ty.setTeachtypename(tyName);
		//���ڻ���
		request.setAttribute("tyName",tyName);
		
		List<TeachType> tchTypeList=setTchType.selectTchType(ty,page);
		int count=setTchType.selectTchTypeCount(ty);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//�����Ϸ�������������
		request.setAttribute("tchTypeList", tchTypeList);
		//����ת��
		request.getRequestDispatcher("pages/jsp/TeachTypeInfo/TeachTypeList.jsp").forward(request, response);
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
	public void AddTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ҳ�������Ϣ
		String teachtypename=request.getParameter("teachtypename");
		TeachType ty=new TeachType();
		ty.setTeachtypename(teachtypename);
		result=setTchType.addTchType(ty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('��ʦ�����ӳɹ�');location.href='pages/jsp/TeachTypeInfo/AddTeachType.jsp'</script>");
		}
		else {
			out.print("<script>alert('��ʦ������ʧ��');location.href='pages/jsp/TeachTypeInfo/AddTeachType.jsp'</script>");
		}
	}
	/**
	 * �༭ҳ����Ⱦ��Servlet
	* @Title: XREditTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void XREditTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û��༭�Ľ�ʦ���ID
		String typId=request.getParameter("typid");
		List<TeachType> xRtypList=setTchType.XRTchtype(typId);
		request.setAttribute("xRtypList", xRtypList);
		//������ת�����༭ҳ��
		request.getRequestDispatcher("pages/jsp/TeachTypeInfo/EditTeachType.jsp").forward(request, response);
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
	public void DetTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ���Ľ�ʦ���ID
		String typId=request.getParameter("typid");
		result=setTchType.detTchType(typId);
		PrintWriter out=response.getWriter();
		if(result!=0) {
		
			out.print("<script>alert('��ʦ���ɾ���ɹ�');location.href='teachType.do?method=List'</script>");
		}
		else {
			out.print("<script>alert('��ʦ���ɾ��ʧ��');location.href='teachType.do?method=List'</script>");
		}
	}
	/**
	 *�༭��Servlet
	* @Title: EditTchType 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditTchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༭ҳ��������Ϣ
		String typId=request.getParameter("typid");
		String typName=request.getParameter("teachtypename");
		TeachType ty=new TeachType();
		ty.setTeachtypeid(typId);
		ty.setTeachtypename(typName);
		result=setTchType.editTchType(ty);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('��ʦ���༭�ɹ�');location.href='teachType.do?method=List'</script>");
		}
		else {
			out.print("<script>alert('��ʦ���༭ʧ��');location.href='teachType.do?method=List'</script>");
		}
	}
}
