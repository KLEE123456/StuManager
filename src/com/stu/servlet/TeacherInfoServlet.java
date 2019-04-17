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
			//��ȡmethod
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
				//��Ⱦ�༭ҳ��
				xREditTeacherInfo(request,response);
			}
			else if("Det".equals(method)) {
				detTeacherInfo(request,response);
			}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡmethod
		String method=request.getParameter("method");
		//�Է����ĵ�ֵ�����жϣ�������Ӧ�ķ���ʵ��
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
	 *��ʦ���ż��Servlet����
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
		String CKTchInfoCode=request.getParameter("tchcode");
		result=setTeach.doAjaxCKCode(CKTchInfoCode);
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
	 * ��ʦ��Ϣ��ѯ��Servlet����
	* @Title: selectTeacherInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
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
	 * Ժϵ�ͽ�ʦ����ѯ��Servlet����
	* @Title: DepAndTypeList 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
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
	 * ��ʦ��Ϣ�༭��Servlet����
	* @Title: EditTeacherInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void EditTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û��޸ĵ���Ϣ
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
		//������ʦʵ�����
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
		//���ñ༭����
		result=setTeach.editTeachInfo(tchInfo);
		//��ȡ��Ӧ������
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('�༭�ɹ�!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('�༭ʧ��!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
		
	}
	/**
	 *	��ʦ��Ϣ��ӵ�servlet����
	* @Title: addTeacherInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void addTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���д����Ϣ
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
		//��ʼ����ʦʵ�����
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
		//��ȡ��Ӧ��
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('��ʦ��ӳɹ�');location.href='pages/jsp/TeacherInfo/AddTeach.jsp'</script>");
		}
		else {
			out.print("<script>alert('��ʦ���ʧ��');location.href='pages/jsp/TeacherInfo/AddTeach.jsp'</script>");
		}
		
	}
	/**
	 * �༭��Ⱦ����
	* @Title: xREditTeacherInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
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
		//���������request
		request.setAttribute("teachInfoList", teachTypeList);
		request.setAttribute("depInfoList", depInfoList);
		request.getRequestDispatcher("pages/jsp/TeacherInfo/EditTeach.jsp").forward(request, response);
	}
	/**
	 * ��ʦ��Ϣɾ������
	* @Title: detTeacherInfo 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void detTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tchInfoId=request.getParameter("Tchid");
		result=setTeach.detTeachInfo(tchInfoId);
		//��ȡ��Ӧ��
		PrintWriter out=response.getWriter();
		if(result!=0) {
			out.print("<script>alert('ɾ���ɹ�!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
		else {
			out.print("<script>alert('ɾ��ʧ��!');location.href='TeacherInfo.NewDo?method=List'</script>");
		}
	}
	
}
	

