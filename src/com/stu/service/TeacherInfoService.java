package com.stu.service;
/**
 * 
 * 项目名称：StuManager
 * 类名称：TeacherInfoService 
 * 类描述：教师信息服务类
 * 创建人：kk
 * 创建时间：2019年1月1日 下午11:11:49
 * 修改人：kk
 * 修改时间：2019年1月1日 下午11:11:49
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月1日
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.bean.TeacherInfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * 项目名称：StuManager
 * 类名称：TeacherInfoService 
 * 类描述： 教师服务类
 * 创建人：kk
 * 创建时间：2019年1月1日 下午11:23:30
 * 修改人：kk
 * 修改时间：2019年1月1日 下午11:23:30
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月1日
 */
public class TeacherInfoService extends DBConnection{
	private int result=0;
	/**
	 * 教师查询方法
	* @Title: selectTeachInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param teach
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws
	 */
	public List selectTeachInfo(TeacherInfo teach,Paging page) {
		//声明存放教师对象的集合对象
		List<TeacherInfo> TeachInfoList=new ArrayList<TeacherInfo>();
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL
		sb.append("SELECT teachinfo.*,teachtype.teachtypename,depinfo.depinfoname,(SELECT count(*) FROM teachcourse ");
		sb.append(" JOIN studentcourse ON teachcourse.teachcourseid=studentcourse.teachcourseid");
		sb.append(" JOIN studentinfo ON studentcourse.studentinfoid=studentinfo.stdinfoid");
		sb.append(" JOIN classinfo ON studentinfo.classinfoid=classinfo.classinfoid where teachcourse.teachinfoid=teachinfo.teachinfoid) cnum FROM teachtype");
		sb.append(" JOIN teachinfo ON teachinfo.teachtypeid=teachtype.teachtypeid");
		sb.append(" JOIN depinfo ON teachinfo.depinfoid=depinfo.depinfoid WHERE 1=1");
		//判断院系ID是否为空，不为空则进行 条件的追加
		if(CheckStr.isEmpty(teach.getDepinfoid())) {
			sb.append(" and depinfo.depinfoid="+teach.getDepinfoid());
		}
		//判断教师姓名是否为空，不为空则进行条件追加
		if(CheckStr.isEmpty(teach.getTeachinfoname())) {
			sb.append(" and teachinfo.teachinfoname LIKE '%"+teach.getTeachinfoname()+"%'");
		}
		//判断教师ID是否为空，不为空则进行条件追加
		if(CheckStr.isEmpty(teach.getTeachinfoid())) {
			sb.append(" and teachinfo.teachinfoid="+teach.getTeachinfoid());
		}
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
			//遍历查询结果集
			while(rs.next()) {
				TeacherInfo tch=new TeacherInfo();
				tch.setTeachinfoid(rs.getInt(1)+"");
				tch.setTeachtypeid(rs.getInt(2)+"");
				tch.setDepinfoid(rs.getInt(3)+"");
				tch.setTeachinfocode(rs.getString(4));
				tch.setTeachinfoname(rs.getString(5));
				tch.setTeachinfosex(rs.getString(6));
				tch.setTeachknowl(rs.getString(7));
				tch.setTeachinfodeg(rs.getString(8));
				tch.setTeachinfospec(rs.getString(9));
				tch.setTeachinfotitle(rs.getString(10));
				tch.setTeachinformk(rs.getString(11));
				tch.setTeachtypename(rs.getString(12));
				tch.setDepinfoname(rs.getString(13));
				tch.setClsNum(rs.getString(14));
				//将教师对象加入集合
				TeachInfoList.add(tch);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return TeachInfoList;
	}
	public int selectTeachInfoCount(TeacherInfo teach) {
		int flag=0;
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL
		sb.append("SELECT COUNT(*) FROM teachinfo WHERE 1=1");
		//判断院系ID是否为空，不为空则进行 条件的追加
		if(CheckStr.isEmpty(teach.getDepinfoid())) {
			sb.append(" and depinfo.depinfoid="+teach.getDepinfoid());
		}
		//判断教师姓名是否为空，不为空则进行条件追加
		if(CheckStr.isEmpty(teach.getTeachinfoname())) {
			sb.append(" and teachinfo.teachinfoname LIKE '%"+teach.getTeachinfoname()+"%'");
		}
		//判断教师ID是否为空，不为空则进行条件追加
		if(CheckStr.isEmpty(teach.getTeachinfoid())) {
			sb.append(" and teachinfo.teachinfoid="+teach.getTeachinfoid());
		}
		
		try {
			//获取预编译对象
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
	 * 教师类别查询方法
	* @Title: selectTeachType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws
	 */
	public List selectTeachType() {
		//存放教师类别集合
		List<TeachType> tyList=new ArrayList<TeachType>();
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL
		sb.append(" SELECT * FROM teachtype");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//执行查询语句
			rs=pst.executeQuery();
			while(rs.next()) {
				TeachType teachType=new TeachType();
				teachType.setTeachtypeid(rs.getInt(1)+"");
				teachType.setTeachtypename(rs.getString(2));
				//将对象放入集合中
				tyList.add(teachType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return tyList;
	}
	
	/**
	 * 教师信息添加方法
	* @Title: addTeachInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tchInfo
	* @param @return    设定文件 
	* @return int    返回类型   返回0添加失败，否则成功
	* @throws
	 */
	public int addTeachInfo(TeacherInfo tchInfo) {
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL
		sb.append(" insert into teachinfo(teachtypeid,depinfoid,teachinfocode,teachinfoname,teachinfosex,teachknowl,teachinfodeg,teachinfospec,teachinfotitle,teachinformk)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?)");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(tchInfo.getTeachtypeid()));
			pst.setInt(2, Integer.parseInt(tchInfo.getDepinfoid()));
			pst.setString(3, tchInfo.getTeachinfocode());
			pst.setString(4, tchInfo.getTeachinfoname());
			pst.setString(5, tchInfo.getTeachinfosex());
			pst.setString(6, tchInfo.getTeachknowl());
			pst.setString(7, tchInfo.getTeachinfodeg());
			pst.setString(8, tchInfo.getTeachinfospec());
			pst.setString(9, tchInfo.getTeachinfotitle());
			pst.setString(10, tchInfo.getTeachinformk());
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
	 * 编辑教师信息方法
	* @Title: editTeachInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tchInfo
	* @param @return    设定文件 
	* @return int    返回类型   返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editTeachInfo(TeacherInfo tchInfo) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append(" update teachinfo set teachtypeid=?,depinfoid=?,teachinfocode=?,teachinfoname=?,");
		sb.append("teachinfosex=?,teachknowl=?,teachinfodeg=?,teachinfospec=?,teachinfotitle=?,teachinformk=?");
		sb.append(" where teachinfoid=?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(tchInfo.getTeachtypeid()));
			pst.setInt(2, Integer.parseInt(tchInfo.getDepinfoid()));
			pst.setString(3, tchInfo.getTeachinfocode());
			pst.setString(4, tchInfo.getTeachinfoname());
			pst.setString(5, tchInfo.getTeachinfosex());
			pst.setString(6, tchInfo.getTeachknowl());
			pst.setString(7, tchInfo.getTeachinfodeg());
			pst.setString(8, tchInfo.getTeachinfospec());
			pst.setString(9, tchInfo.getTeachinfotitle());
			pst.setString(10, tchInfo.getTeachinformk());
			pst.setInt(11, Integer.parseInt(tchInfo.getTeachinfoid()));
			//执行修改SQL语句
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * 教师信息删除方法
	* @Title: detTeachInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tchId
	* @param @return    设定文件 
	* @return int    返回类型  返回0删除失败，否则删除成功
	* @throws
	 */
	public int detTeachInfo(String tchId) {
		//获取数据库连接
		conn=getDBconn();
		//拼组Sql语句
		StringBuffer sb=new StringBuffer();
		sb.append(" delete from  teachinfo where teachinfoid=?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(tchId));
			//执行SQL语句
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * 教师代号检测方法
	* @Title: doAjaxCKCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return int    返回类型  返回1表示代号已存在，0则不存在
	* @throws
	 */
	public int doAjaxCKCode(String code) {
		int b=0;
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from teachinfo where teachinfocode=?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setString(1, code);
			//执行查询语句
			rs=pst.executeQuery();
			if(rs.next()) {
				b=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return b;
	}
}
