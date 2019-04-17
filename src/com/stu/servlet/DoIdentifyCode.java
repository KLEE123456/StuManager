package com.stu.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 项目名称：StuManager
 * 类名称：DoIdentifyCode 
 * 类描述： MD5加密Servlet
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:54:02
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:54:02
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class DoIdentifyCode extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg"); //必须设置ContentType为image/jpeg
	     response.setHeader("Pragma","No-cache"); 
	     response.setHeader("Cache-Control","no-cache"); 
	     response.setDateHeader("Expires",0); 
	     //设置图片的长宽  验证码长度
	     int width=76, height=20,len=4;
	     String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     int length = base.length();
	     //创建内存图像 
	     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	     //获取图形上下文 
	     Graphics g = image.getGraphics();
	     //创建随机类的实例 
	     Random random = new Random();
	     //设定图像背景色(因为是做背景，所以偏淡) 
	     g.setColor(getRandColor(random,200,250)); 
	     g.fillRect(0, 0, width, height);
	     //备选字体 
	     String[] fontTypes = {"tahoma","Atlantic Inline","fantasy","Times New Roman","Georgia","Arial", "Helvetica", "sans-serif","System"}; 
	     int fontTypesLength = fontTypes.length; 
	     //在图片背景上增加噪点 
	     g.setColor(getRandColor(random,160,200)); 
	     g.setFont(new Font("Times New Roman",Font.PLAIN,12)); 
	     for (int i=0;i<6;i++) 
	     { 
	       g.drawString("!@#$%^,.;'[javawind.net]/<&*()>:5277",0,5*(i+2)); 
	     }
	     
	     String sRand="",pStr=""; 
	     for (int i=0;i<len;i++) 
	     { 
	       int start = random.nextInt(length); 
	       String rand=base.substring(start,start+1); 
	       sRand+=rand;       
	       //设置字体的颜色 
	       g.setColor(getRandColor(random,10,150)); 
	       //设置字体 
	       g.setFont(new Font(fontTypes[random.nextInt(fontTypesLength)],Font.BOLD,16)); 
	       //将随机验证码画到图片上 
	       //g.drawString(rand,15*i,18);
	       pStr = sRand.substring(i,i+1);
	       if(i==0){ 
	        g.drawString(pStr,2,14);
	       }
	       if(i==1){ 
	        g.drawString(pStr,15,16);
	       }
	       if(i==2){ 
	        g.drawString(pStr,30,15);
	       }
	       if(i==3){ 
	        g.drawString(pStr,45,13);
	       }  
	     }
	      
	     //将认证码存入session 
	     HttpSession session=request.getSession();
	     session.setAttribute("rand", sRand);
	     g.dispose();
	     //输出图象到页面 
	     ImageIO.write(image,"JPEG",response.getOutputStream());
	}
	protected Color getRandColor(Random random,int fc,int bc)
	 { 
	  if(fc>255) fc=255; 
	  if(bc>255) bc=255; 
	  int r=fc+random.nextInt(bc-fc); 
	  int g=fc+random.nextInt(bc-fc); 
	  int b=fc+random.nextInt(bc-fc); 
	  return new Color(r,g,b); 
	 }
	
}
