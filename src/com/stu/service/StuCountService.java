package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Classinfo;
import com.stu.dbconn.DBConnection;
/**
 * 学生数查询服务类
 * 项目名称：StuManager
 * 类名称：StuCountService 
 * 类描述： 
 * 创建人：kk
 * 创建时间：2019年1月11日 下午7:35:41
 * 修改人：kk
 * 修改时间：2019年1月11日 下午7:35:41
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月11日
 */
public class StuCountService extends  DBConnection{
	/**
	 * 各班级学生数查询方法
	* @Title: selectStuCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws
	 */
	 public List selectStuCount() {
		 List<Classinfo> clsSumList=new ArrayList<Classinfo>();
		 conn=getDBconn();
		 StringBuffer sb=new StringBuffer();
		 sb.append("SELECT classinfo.classinfoname,classinfo.classinfosum FROM classinfo");
		 try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Classinfo cls=new Classinfo();
				cls.setClassinfoname(rs.getString(1));
				cls.setClassinfosum(rs.getInt(2)+"");
				clsSumList.add(cls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		 return clsSumList;
	 }
	 /**
	  *各班级男生数查询方法
	 * @Title: selectStuMale 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return List    返回类型 
	 * @throws
	  */
	 public List selectStuMale() {
		 List<Classinfo> MaleList=new ArrayList<Classinfo>();
		 conn=getDBconn();
		 StringBuffer sb=new StringBuffer();
		 sb.append("SELECT count(*) FROM studentinfo");
		 sb.append(" WHERE classinfoid IN (SELECT classinfoid FROM classinfo) AND studentinfo.stdinfosex='男'");
		 sb.append(" GROUP BY studentinfo.classinfoid");
		 try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Classinfo cls=new Classinfo();
				cls.setClsMaleNum(rs.getInt(1)+"");
				MaleList.add(cls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		 return MaleList;
	 }
	 /**
	  * 查询各班级女生人数实现方法
	 * @Title: selectStuFemale 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return List    返回类型 
	 * @throws
	  */
	 public List selectStuFemale() {
		 List<Classinfo> FeMaleList=new ArrayList<Classinfo>();
		 conn=getDBconn();
		 StringBuffer sb=new StringBuffer();
		 sb.append("SELECT count(*) FROM studentinfo");
		 sb.append(" WHERE classinfoid IN (SELECT classinfoid FROM classinfo) AND studentinfo.stdinfosex='女'");
		 sb.append(" GROUP BY studentinfo.classinfoid");
		 try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Classinfo cls=new Classinfo();
				cls.setClsFeMaleNum(rs.getInt(1)+"");
				FeMaleList.add(cls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		 return FeMaleList;
	 }
}
