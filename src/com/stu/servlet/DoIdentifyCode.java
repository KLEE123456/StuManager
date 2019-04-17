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
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�DoIdentifyCode 
 * �������� MD5����Servlet
 * �����ˣ�kk
 * ����ʱ�䣺2018��12��21�� ����10:54:02
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2018��12��21�� ����10:54:02
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2018��12��21��
 */
public class DoIdentifyCode extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg"); //��������ContentTypeΪimage/jpeg
	     response.setHeader("Pragma","No-cache"); 
	     response.setHeader("Cache-Control","no-cache"); 
	     response.setDateHeader("Expires",0); 
	     //����ͼƬ�ĳ���  ��֤�볤��
	     int width=76, height=20,len=4;
	     String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     int length = base.length();
	     //�����ڴ�ͼ�� 
	     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	     //��ȡͼ�������� 
	     Graphics g = image.getGraphics();
	     //����������ʵ�� 
	     Random random = new Random();
	     //�趨ͼ�񱳾�ɫ(��Ϊ��������������ƫ��) 
	     g.setColor(getRandColor(random,200,250)); 
	     g.fillRect(0, 0, width, height);
	     //��ѡ���� 
	     String[] fontTypes = {"tahoma","Atlantic Inline","fantasy","Times New Roman","Georgia","Arial", "Helvetica", "sans-serif","System"}; 
	     int fontTypesLength = fontTypes.length; 
	     //��ͼƬ������������� 
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
	       //�����������ɫ 
	       g.setColor(getRandColor(random,10,150)); 
	       //�������� 
	       g.setFont(new Font(fontTypes[random.nextInt(fontTypesLength)],Font.BOLD,16)); 
	       //�������֤�뻭��ͼƬ�� 
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
	      
	     //����֤�����session 
	     HttpSession session=request.getSession();
	     session.setAttribute("rand", sRand);
	     g.dispose();
	     //���ͼ��ҳ�� 
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
