package com.stu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.stu.bean.Adminuser;
import com.stu.service.AdminInfoService;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�DoRegister 
 * �������� �û�ע��ʵ��Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:39:13
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:39:13
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class DoRegister extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveFile=request.getServletContext().getRealPath("/fileupload");
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload servletUpload=new ServletFileUpload(factory);
		Adminuser adm=new Adminuser();
		PrintWriter out;
		out=response.getWriter();
		try {
			//��ȡ������󼯺�ΪFileItem
			List<FileItem> itemList=servletUpload.parseRequest(request);
			//�������󼯺�
			for(FileItem fileItem : itemList) {
				//�������Ϊ��ͨ���ؼ�
				if(fileItem.isFormField()) {
					//��ȡ��ǰ�����name��������
					String name=fileItem.getFieldName();
					//��ȡ�ؼ�����
					String val=fileItem.getString("utf-8");
					Method m = adm.getClass().getMethod("set"+name.substring(0, 1).toUpperCase() + name.substring(1),String.class);
					m.invoke(adm,val);
				}else {
					String filename=fileItem.getName();
					String strs[]=filename.split("\\.");
					filename=adm.getAdminusername()+"."+strs[1];
					//�����ļ�����
					File f=new File(saveFile+"/"+filename);	
					//д��
					fileItem.write(f);
					adm.setImagepath("/fileupload/"+filename);
				}	
			}
			int result;
			AdminInfoService setAdmin=new AdminInfoService();
			result=setAdmin.addMessage(adm);
			if(result!=0) {
				out.print("<script>alert('ע��ɹ�');window.location.href='index.jsp'</script>");
			}
			else {
				out.print("<script>alert('ע��ʧ��');history.back()</script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		
	}
	
}
