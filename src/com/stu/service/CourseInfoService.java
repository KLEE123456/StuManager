package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.CourseInfo;
import com.stu.bean.Paging;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * 项目名称：StuManager
 * 类名称：CourseInfoService 
 * 类描述： 课程信息服务类
 * 创建人：kk
 * 创建时间：2019年1月7日 下午8:55:32
 * 修改人：kk
 * 修改时间：2019年1月7日 下午8:55:32
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
public class CourseInfoService extends DBConnection{
	private int result=0;
	/**
	 * 课程信息查询方法
	* @Title: selectCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cInfo
	* @param @return    设定文件 
	* @return List    返回类型   返回存放课程实体对象的集合
	* @throws
	 */
	public List selectCourseInfo(CourseInfo cInfo,Paging page) {
		//创建存放课程实体对象的集合
		List<CourseInfo> CInfoList=new ArrayList<CourseInfo>();
		conn=getDBconn();//获取数据库连接
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("SELECT courseinfo.*,COUNT(teachcourse.courseinfoid), coursetype.coursetypename FROM coursetype");
		sb.append(" JOIN courseinfo ON coursetype.coursetypeid=courseinfo.coursetypeid");
		sb.append(" LEFT JOIN teachcourse ON courseinfo.courseinfoid=teachcourse.courseinfoid where 1=1");
		if(CheckStr.isEmpty(cInfo.getCourseinfoname())) {
			sb.append(" AND courseinfo.courseinfoname like '%"+cInfo.getCourseinfoname()+"%'");
		}
		if(CheckStr.isEmpty(cInfo.getCourseinfoid())) {
			sb.append(" and courseinfo.courseinfoid="+cInfo.getCourseinfoid());
		}
		sb.append(" GROUP BY courseinfo.courseinfoid");
		if(page!=null) {
			sb.append(" limit ?,?");
		}
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			//执行查询语句
			rs=pst.executeQuery();
			while(rs.next()) {
				CourseInfo courseInfo=new CourseInfo();
				courseInfo.setCourseinfoid(rs.getInt(1)+"");
				courseInfo.setCoursetypeid(rs.getInt(2)+"");
				courseInfo.setCourseinfocode(rs.getString(3));
				courseInfo.setCourseinfoname(rs.getString(4));
				courseInfo.setCourseinfoproj(rs.getString(5));
				courseInfo.setCourseinforstper(rs.getInt(6)+"");
				courseInfo.setCourseinfopraper(rs.getInt(7)+"");
				courseInfo.setCourseinfocrehor(rs.getInt(8)+"");
				courseInfo.setCourseinformk(rs.getString(9));
				courseInfo.setTchNum(rs.getInt(10)+"");
				courseInfo.setCoursetypename(rs.getString(11));
				//将课程实体对象追加到集合
				CInfoList.add(courseInfo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return CInfoList;
	}
	public int selectCourseInfoCount(CourseInfo cInfo) {
		int flag=0;
		conn=getDBconn();//获取数据库连接
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("SELECT COUNT(*) FROM courseinfo WHERE 1=1");
		if(CheckStr.isEmpty(cInfo.getCourseinfoname())) {
			sb.append(" AND courseinfo.courseinfoname like '%"+cInfo.getCourseinfoname()+"%'");
		}
		if(CheckStr.isEmpty(cInfo.getCourseinfoid())) {
			sb.append(" and courseinfo.courseinfoid="+cInfo.getCourseinfoid());
		}
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//执行查询语句
			rs=pst.executeQuery();
			if(rs.next()) {
				flag=rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return flag;
	}
	/**
	 * 检查课程代号方法
	* @Title: checkCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return int    返回类型   返回0代号不存在，1代号存在
	* @throws
	 */
	public int checkCode(String code) {
		int b=0;
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("select * from courseinfo where courseinfocode=?");
		
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setString(1, code);
			rs=pst.executeQuery();
			//如果代号存在，则将b的值设置为1
			if(rs.next()) {
				b=1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return b;
	}
	/**
	 * 课程新增方法
	* @Title: addCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cInfo
	* @param @return    设定文件 
	* @return int    返回类型  返回0新增失败，否则新增成功
	* @throws
	 */
	public int addCourseInfo(CourseInfo cInfo) {
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("insert into courseinfo(coursetypeid,courseinfocode,courseinfoname,courseinfoproj,courseinforstper,courseinfopraper,courseinfocrehor,courseinformk)");
		sb.append(" values(?,?,?,?,?,?,?,?)");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1,Integer.parseInt(cInfo.getCoursetypeid()));
			pst.setString(2, cInfo.getCourseinfocode());
			pst.setString(3, cInfo.getCourseinfoname());
			pst.setString(4,cInfo.getCourseinfoproj());
			pst.setInt(5, Integer.parseInt(cInfo.getCourseinforstper()));
			pst.setInt(6, Integer.parseInt(cInfo.getCourseinfopraper()));
			pst.setInt(7, Integer.parseInt(cInfo.getCourseinfocrehor()));
			pst.setString(8, cInfo.getCourseinformk());
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
	 * 课程编辑方法
	* @Title: editCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cInfo
	* @param @return    设定文件 
	* @return int    返回类型  返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editCourseInfo(CourseInfo cInfo) {
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("update courseinfo set coursetypeid=?,courseinfocode=?,courseinfoname=?,courseinfoproj=?,courseinforstper=?");
		sb.append(",courseinfopraper=?,courseinfocrehor=?,courseinformk=?");
		sb.append(" where courseinfoid=?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(cInfo.getCoursetypeid()));
			pst.setString(2, cInfo.getCourseinfocode());
			pst.setString(3, cInfo.getCourseinfoname());
			pst.setString(4,cInfo.getCourseinfoproj());
			pst.setInt(5, Integer.parseInt(cInfo.getCourseinforstper()));
			pst.setInt(6, Integer.parseInt(cInfo.getCourseinfopraper()));
			pst.setInt(7, Integer.parseInt(cInfo.getCourseinfocrehor()));
			pst.setString(8, cInfo.getCourseinformk());
			pst.setInt(9, Integer.parseInt(cInfo.getCourseinfoid()));
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
	 * 课程信息删除方法
	* @Title: detCourseInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param courseId
	* @param @return    设定文件 
	* @return int    返回类型  返回0删除失败，否则删除成功
	* @throws
	 */
	public int detCourseInfo(String courseId) {
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("delete from courseinfo where courseinfoid=?");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(courseId));
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
