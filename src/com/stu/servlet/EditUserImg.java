package com.stu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.stu.bean.Adminuser;
import com.stu.service.AdminInfoService;

/**
 * 
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�EditUserImg 
 * ���������û�ͷ���޸�Servlet 
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����11:22:56
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����11:22:56
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class EditUserImg extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveFile=request.getServletContext().getRealPath("/fileupload");
		//�����ļ������ͽ�����
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload servletUpload=new ServletFileUpload(factory);
		HttpSession session=request.getSession();
		Adminuser adms=(Adminuser)session.getAttribute("admins");
		PrintWriter out=response.getWriter();
		try {
			List<FileItem> itemList=servletUpload.parseRequest(request);
			for (FileItem fileItem : itemList) {
				//���Ϊ��ͨ�ؼ�
				if(fileItem.isFormField()) {
					String name=fileItem.getFieldName();
					String val=fileItem.getString("utf-8");
					Method m=adms.getClass().getMethod("set"+name.substring(0, 1).toUpperCase()+name.substring(1), String.class);
					m.invoke(adms, val);
				}
				else {
					String filename=fileItem.getName();
					String[] buf=filename.split("\\.");
					filename=adms.getAdminusername()+"."+buf[1];
					File f=new File(saveFile+"/"+filename);
					fileItem.write(f);
					adms.setImagepath("/fileupload/"+filename);
				}
			}
			int result=0;
			//����ʵ����
			AdminInfoService editImg=new AdminInfoService();
			result=editImg.editUserImg(adms);
			if(result!=0) {
				out.print("<script>alert('ͷ���޸ĳɹ�');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
				//request.getRequestDispatcher("pages/jsp/modifyInfo.jsp").forward(request, response);
			}
			else {
				out.print("<script>alert('ͷ���޸�ʧ��');window.location.href='pages/jsp/imageInfo.jsp'</script>");
				//request.getRequestDispatcher("pages/jsp/imageInfo.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
