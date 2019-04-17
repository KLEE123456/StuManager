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
 * 项目名称：StuManager
 * 类名称：DoRegister 
 * 类描述： 用户注册实现Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:39:13
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:39:13
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
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
			//获取请求对象集合为FileItem
			List<FileItem> itemList=servletUpload.parseRequest(request);
			//遍历对象集合
			for(FileItem fileItem : itemList) {
				//如果对象为普通表单控件
				if(fileItem.isFormField()) {
					//获取当前对象的name属性内容
					String name=fileItem.getFieldName();
					//获取控件内容
					String val=fileItem.getString("utf-8");
					Method m = adm.getClass().getMethod("set"+name.substring(0, 1).toUpperCase() + name.substring(1),String.class);
					m.invoke(adm,val);
				}else {
					String filename=fileItem.getName();
					String strs[]=filename.split("\\.");
					filename=adm.getAdminusername()+"."+strs[1];
					//创建文件对象
					File f=new File(saveFile+"/"+filename);	
					//写入
					fileItem.write(f);
					adm.setImagepath("/fileupload/"+filename);
				}	
			}
			int result;
			AdminInfoService setAdmin=new AdminInfoService();
			result=setAdmin.addMessage(adm);
			if(result!=0) {
				out.print("<script>alert('注册成功');window.location.href='index.jsp'</script>");
			}
			else {
				out.print("<script>alert('注册失败');history.back()</script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		
	}
	
}
