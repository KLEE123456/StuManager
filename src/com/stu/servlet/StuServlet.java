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
//ӳ��RUL
@WebServlet("/StuInfo.newDO")
/**
 *	ѧ��Servlet
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�StuServlet 
 * �������� 
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��1�� ����8:48:57
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��1�� ����8:48:57
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��1��
 */
public class StuServlet extends HttpServlet{
	//�����༶ �ķ��������
	ClassInfoService classInfoSve=new ClassInfoService();
	//����Ժϵ�ķ��������
	SpInfoService setSp=new SpInfoService();
	private int result=0;
	//����ѧ���ķ��������
	StuService setStu=new StuService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡmethod
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
			//��Ⱦ�༭ҳ��
			xREditStuInfo(request,response);
		}
		else if("Det".equals(method)) {
			detStuInfo(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡmethod
		String method=request.getParameter("method");
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
		//��ȡ�û���д�Ĵ���
		String CKStuInfoCode=request.getParameter("stucode");
		result=setStu.doAjaxCKCode(CKStuInfoCode);
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
	 * ���ݻ�ȡ��ѧ����Ϣ��ִ�в�ѯ�ķ���
	* @Title: selectStuInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paging page=new Paging();
		page.setPagesize(10);
		String paging=request.getParameter("paging");
		//��ȡ��ǰҳ
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		
		//��ȡ�û������������Ϣ
		String depInfoId=request.getParameter("Depselect");
		String spInfoId=request.getParameter("Spselect");
		String classInfoId=request.getParameter("classselect");
		String stuName=request.getParameter("stuName");
		//����ѧ��ʵ�����
		Stuinfo stuInfo=new Stuinfo();
		//����ȡ������Ϣ���õ�ʵ�����
		stuInfo.setDepinfoId(depInfoId);
		stuInfo.setSpilinfoid(spInfoId);
		stuInfo.setClassinfoid(classInfoId);
		stuInfo.setStdinfoname(stuName);
		//��ѧ���������request���������ڻ���
		request.setAttribute("HTStuInfo", stuInfo);
		//���ò�ѯѧ����Ϣ�ķ���
		List<Stuinfo> stuList=setStu.selectStuInfo(stuInfo,page);
		int count=setStu.selectStuInfoCount(stuInfo);
		int totalNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			totalNum++;
		}
		page.setPagenum(totalNum);
		request.setAttribute("paging", page);
		//�����صļ��Ϸ���request��
		request.setAttribute("stuList", stuList);
		//����Ժϵ��רҵ���ƵĲ�ѯ����
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		//�����Ժϵʵ�����ļ��Ϸ���request��
		request.setAttribute("DepInfoList", depInfoList);
		
		//request.setAttribute("SpList", spInfoList);
		//������ת��֮listҳ��
		request.getRequestDispatcher("pages/jsp/StuInfo/StuList.jsp").forward(request, response);
	}
	/**
	 * ѧ����Ϣ��ӵ�Servlet
	* @Title: addStuInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void addStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���д����Ϣ
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
		//��ʼ��ѧ������
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
		//�������ѧ���ķ���
		result=setStu.addStuInfo(stuInfo);
		//��ȡ��Ӧ��
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('ѧ����ӳɹ�');location.href='pages/jsp/StuInfo/AddStu.jsp'</script>");
		}
		else {
			out.print("<script>alert('ѧ�����ʧ��');location.href='pages/jsp/StuInfo/AddStu.jsp'</script>");
		}
	}
	/**
	 * ѧ���༭��Servlet
	* @Title: EditStuInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༭ҳ����Ϣ
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
		//��ʼ��ѧ������
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
		//���ñ༭ѧ����Ϣ�ķ���
		result=setStu.editStuInfo(stuInfo);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('�༭�ɹ�!');location.href='StuInfo.newDO?method=List'</script>");
		}
		else {
			out.print("<script>alert('�༭ʧ��!');location.href='StuInfo.newDO?method=List'</script>");
		}
	}
	/**
	 * ѧ����Ϣɾ����Servlet
	* @Title: detStuInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void detStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ��ѧ����ID
		String stuId=request.getParameter("stuid");
		//ִ��ɾ��ѧ����Ϣ�ķ���
		result=setStu.detStuInfo(stuId);
		//��ȡ��Ӧ��
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('ɾ���ɹ�!');location.href='StuInfo.newDO?method=List'</script>");
		}
		else {
			out.print("<script>alert('ɾ��ʧ��!');location.href='StuInfo.newDO?method=List'</script>");
		}
	}
	/**
	 * ��ȡԺϵ��רҵ��Ϣ��Servlet
	* @Title: depSpClsList 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void depSpClsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ִ��Ժϵ��רҵ��Ϣ��ѯ�ķ���
		List<Depinfo> depInfoList=setSp.selectDepName();
		List<Spilinfo> spInfoList=classInfoSve.selectSpName(new Spilinfo());
		request.setAttribute("depInfoList", depInfoList);
		//������ת����ѧ����ӽ���
		request.getRequestDispatcher("pages/jsp/StuInfo/AddStu.jsp").forward(request, response);
	}
	/**
	 * ѧ���༭��Ⱦ��Servlet
	* @Title: xREditStuInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void xREditStuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�༶ѧ����ID
		String stuInfoId=request.getParameter("stuid");
		Stuinfo stuInfo=new Stuinfo();
		stuInfo.setStdinfoid(stuInfoId);
		//���ò�ѯ����
		List<Stuinfo> stuInfoList=setStu.selectStuInfo(stuInfo,null);
		List<Depinfo> depInfoList=setSp.selectDepName();
		//���������request
		request.setAttribute("depInfoList", depInfoList);
		request.setAttribute("stuInfoList", stuInfoList);
		//������ת�����༭ҳ��
		request.getRequestDispatcher("pages/jsp/StuInfo/EditStu.jsp").forward(request, response);
	}
}
	

