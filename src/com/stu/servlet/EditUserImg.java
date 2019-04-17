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
 * 项目名称：StuManager
 * 类名称：EditUserImg 
 * 类描述：用户头像修改Servlet 
 * 创建人：kk
 * 创建时间：2018年12月21日 下午11:22:56
 * 修改人：kk
 * 修改时间：2018年12月21日 下午11:22:56
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class EditUserImg extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveFile=request.getServletContext().getRealPath("/fileupload");
		//创建文件工厂和解析器
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload servletUpload=new ServletFileUpload(factory);
		HttpSession session=request.getSession();
		Adminuser adms=(Adminuser)session.getAttribute("admins");
		PrintWriter out=response.getWriter();
		try {
			List<FileItem> itemList=servletUpload.parseRequest(request);
			for (FileItem fileItem : itemList) {
				//如果为普通控件
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
			//调用实现类
			AdminInfoService editImg=new AdminInfoService();
			result=editImg.editUserImg(adms);
			if(result!=0) {
				out.print("<script>alert('头像修改成功');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
				//request.getRequestDispatcher("pages/jsp/modifyInfo.jsp").forward(request, response);
			}
			else {
				out.print("<script>alert('头像修改失败');window.location.href='pages/jsp/imageInfo.jsp'</script>");
				//request.getRequestDispatcher("pages/jsp/imageInfo.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
