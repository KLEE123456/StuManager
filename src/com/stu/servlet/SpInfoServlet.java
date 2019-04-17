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
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�EditSp 
 * �������� ��רҵ��Ϣ������ɾ�Ĳ��Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����11:11:00
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����11:11:00
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
@WebServlet("/Spinfo.newDo")
public class SpInfoServlet extends HttpServlet{
	//����ʵ�ַ����������
	SpInfoService setSp=new SpInfoService();
	
	ClassInfoService setCs=new ClassInfoService();
	
	 //�нӷ��ؽ��
	 private int result;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡa��ǩ�������ķ���
		String method=request.getParameter("method");
		PrintWriter out=response.getWriter();
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
	 * רҵ���ż��Servlet����
	* @Title: CKCode 
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
		String CKspInfoCode=request.getParameter("spilcode");
		result=setSp.doAjaxCKCode(CKspInfoCode);
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
	* @Title: selectSp 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void selectSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Spilinfo spinfo=new Spilinfo();
		Paging page=new Paging();
		//��ȡ��ǰҳ
		String paging=request.getParameter("paging");
		//����Ϊ���ж�
		int num=paging==null?1:Integer.parseInt(paging);
		page.setPage(num);
		page.setPagesize(10);
		//��ȡ����Ϣ
		String depId=request.getParameter("select");
		String spName=request.getParameter("spinfoname");
		request.setAttribute("HTSpInfoName", spName);
		//��ӵ�ʵ����
		spinfo.setDepinfoid(depId);
		spinfo.setSpilinfoname(spName);
		request.setAttribute("spinfos", spinfo);
		//��������service�ķ��񷽷� 
		List<Spilinfo> splist=setSp.selectSpInfo(spinfo,page);
		//��ȡ��¼����
		int count=setSp.selectSpInfoCount(spinfo);
		//������ҳ��
		int pageNum=count/page.getPagesize();
		if(count%page.getPagesize()!=0) {
			pageNum++;
		}
		page.setPagenum(pageNum);
		request.setAttribute("paging", page);
		//�����ع�����list����request������
		request.setAttribute("Splist", splist);
		//�����洢Ժϵ��Ϣ��list�нӷ��ص�list
		List<Depinfo> deplist=setSp.selectDepName();
		//��deplist��������������
		request.setAttribute("Deplist",deplist);
		//������ת��
		request.getRequestDispatcher("pages/jsp/SpInfo/SpList.jsp").forward(request, response);
		
	}
	/**
	 * method=Addʵ�ַ���
	* @Title: AddSp 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void AddSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����Ϣ
		String spilinfocode=request.getParameter("spilinfocode");
		String spilinfoname=request.getParameter("spilinfoname");
		String spilinfoaim=request.getParameter("spilinfoaim");
		String spilinfoprodire=request.getParameter("spilinfoprodire");
		String depinfoid=request.getParameter("select");
		//����רҵʵ�����
		Spilinfo sp=new Spilinfo();
		sp.setSpilinfocode(spilinfocode);
		sp.setSpilinfoname(spilinfoname);
		sp.setSpilinfoaim(spilinfoaim);
		sp.setSpilinfoprodire(spilinfoprodire);
		sp.setDepinfoid(depinfoid);
		//��������ʵ�ַ���
		result=setSp.addSpInfo(sp);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('רҵ��ӳɹ�!');location.href='pages/jsp/SpInfo/AddSp.jsp'</script>");
		}
		else {
			out.print("<script>alert('רҵ���ʧ��!');location.href='pages/jsp/SpInfo/AddSp.jsp'</script>");
		}
	}
	/**
	 * method=Editʵ�ַ�����get���������
	* @Title: XREditSp 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void XREditSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡרҵID
		String spId=request.getParameter("spid");
		Spilinfo sp=new Spilinfo();
		sp.setSpilinfoid(spId);
		//����ʵ�ַ���
		List<Spilinfo> splist=setSp.selectSpInfo(sp,null);
		//�����Ϸ���request����
		request.setAttribute("spList",splist);
		//������ת�����༭����
		request.getRequestDispatcher("pages/jsp/SpInfo/EditSp.jsp").forward(request, response);	
	}
	/**
	 * Ժϵɾ������
	* @Title: DetSp 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void DetSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡURL����
		String spInfoId=request.getParameter("spid");
		PrintWriter out=response.getWriter();
		result=setCs.selectClassNum(spInfoId);
		if(result!=0) {
			out.print("<script>alert('��רҵ����ɾ��');location.href='Spinfo.newDo?method=List'</script>");
		}
		else {
			int flag;
			flag=setSp.DetSpInfo(spInfoId);
			if(flag==0) {
				out.print("<script>alert('ɾ��ʧ��');location.href='Spinfo.newDo?method=List'</script>");
			}
			else {
				out.print("<script>alert('ɾ���ɹ�');location.href='Spinfo.newDo?method=List'</script>");

			}
		}
	}
	/**
	 * method=Editʵ�ַ�����post���������
	* @Title: EditSp 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����Ϣ
		String spilinfoid=request.getParameter("Spid");
		String spilinfocode=request.getParameter("spilinfocode");
		String spilinfoname=request.getParameter("spilinfoname");
		String spilinfoaim=request.getParameter("spilinfoaim");
		String spilinfoprodire=request.getParameter("spilinfoprodire");
		//������Ϣ���봴����רҵʵ�����
		Spilinfo sp=new Spilinfo();
		sp.setSpilinfoid(spilinfoid);
		sp.setSpilinfocode(spilinfocode);
		sp.setSpilinfoname(spilinfoname);
		sp.setSpilinfoaim(spilinfoaim);
		sp.setSpilinfoprodire(spilinfoprodire);
		//���þ���ʵ�ַ���
		result=setSp.editSpInfo(sp);
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('רҵ�༭�ɹ�!');location.href='Spinfo.newDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('רҵ�༭ʧ��!');location.href='Spinfo.newDo?method=List'</script>");
		}
	}
	/**
	 *����רҵҳ���Ժϵ�б�ʵ�ַ���
	* @Title: AddJspDepName 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void AddJspDepName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ò�ѯԺϵ���Ƶ�ʵ�ַ���
		List<Depinfo> Deplist=setSp.selectDepName();
		request.setAttribute("Deplist", Deplist);
		//������ת��������ҳ��
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
	

