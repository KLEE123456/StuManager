package com.stu.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.CourseType;
import com.stu.bean.Paging;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;

/**
 * 
 * 项目名称：StuManager
 * 类名称：CourseTypeService 
 * 类描述： 课程类别服务类
 * 创建人：kk
 * 创建时间：2019年1月7日 下午3:22:36
 * 修改人：kk
 * 修改时间：2019年1月7日 下午3:22:36
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
public class CourseTypeService extends DBConnection{
	private int result=0;
	
	/**
	 * 课程类别查询方法
	* @Title: selectCourseType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cyp
	* @param @return    设定文件 
	* @return List    返回类型  返回存储课程类别对象的集合
	* @throws
	 */
	public List selectCourseType(CourseType cyp,Paging page) {
		//创建存储课程类别对象的集合
		List<CourseType> CorTypeList=new ArrayList<CourseType>();
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT coursetype.*,COUNT(courseinfo.coursetypeid) FROM coursetype");
		sb.append(" LEFT JOIN courseinfo ON coursetype.coursetypeid=courseinfo.coursetypeid where 1=1");
		//如果教师类别名称不为空，则进行追加
		if(CheckStr.isEmpty(cyp.getCoursetypename())) {
			sb.append(" and coursetype.coursetypename like '%"+cyp.getCoursetypename()+"%'");
		}
		sb.append(" GROUP BY coursetype.coursetypeid");
		if(page!=null) {
			sb.append(" limit ?,?");
		}
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			//执行查询语句
			rs=pst.executeQuery();
			//遍历查询结果集
			while(rs.next()) {
				CourseType cy=new CourseType();
				cy.setCoursetypeid(rs.getInt(1)+"");
				cy.setCoursetypename(rs.getString(2));
				cy.setCourseNum(rs.getInt(3)+"");
				CorTypeList.add(cy);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return CorTypeList;
	}
	public int selectCourseTypeCount(CourseType cyp) {
		int flag=0;
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM coursetype WHERE 1=1");
		//如果教师类别名称不为空，则进行追加
		if(CheckStr.isEmpty(cyp.getCoursetypename())) {
			sb.append(" and coursetype.coursetypename like '%"+cyp.getCoursetypename()+"%'");
		}
		
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			
			//执行查询语句
			rs=pst.executeQuery();
			//遍历查询结果集
			if(rs.next()) {
				flag=rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return flag;
	}
	/**
	 * 教师类别添加方法
	* @Title: addCourseType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cyp
	* @param @return    设定文件 
	* @return int    返回类型  返回0添加失败，否则添加成功
	* @throws
	 */
	public int addCourseType(CourseType cyp) {
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("insert into coursetype(coursetypename) values(?)");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setString(1, cyp.getCoursetypename());
			//执行新增语句
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * 
	 * 编辑页面渲染方法
	* @Title: XRCoursetype 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param courseTypeId
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws
	 */
	public List XRCoursetype(String courseTypeId) {
		List<CourseType> csyList=new ArrayList<CourseType>();
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("select * from coursetype where coursetypeid=?");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(courseTypeId));
			rs=pst.executeQuery();
			while(rs.next()) {
				CourseType cy=new CourseType();
				cy.setCoursetypeid(rs.getInt(1)+"");
				cy.setCoursetypename(rs.getString(2));
				csyList.add(cy);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return csyList;
	}
	/**
	 *编辑教师类别的方法
	* @Title: editCourseType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cyp
	* @param @return    设定文件 
	* @return int    返回类型  返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editCourseType(CourseType cyp) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("update coursetype set coursetypename=? where coursetypeid=?");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setString(1, cyp.getCoursetypename());
			pst.setInt(2, Integer.parseInt(cyp.getCoursetypeid()));
			//执行修改语句
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return result;
	}
	/**
	 * 教师类别删除方法
	* @Title: detCourseType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cypId
	* @param @return    设定文件 
	* @return int    返回类型  返回0则删除失败，否则删除成功
	* @throws
	 */
	public int detCourseType(String cypId) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("delete from coursetype where coursetypeid=?");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(cypId));
			//执行删除语句
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		
		return result;
	}
}
