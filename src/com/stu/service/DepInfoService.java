package com.stu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * 项目名称：StuManager
 * 类名称：DepInfoService 
 * 类描述： 院系信息服务类
 * 创建人：kk
 * 创建时间：2018年12月21日 下午8:15:52
 * 修改人：kk
 * 修改时间：2018年12月21日 下午8:15:52
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class DepInfoService extends DBConnection{
	private int result=0;
	/**
	 * 院系信息查询
	* @Title: selectDepInfoList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deps
	* @param @return    设定文件 
	* @return List<Depinfo>    返回类型  返回查询到记录(实体)
	* @throws
	 */
	public List<Depinfo> selectDepInfoList(Depinfo deps,Paging page){
		//存放院系信息的集合
		List<Depinfo> depList=new ArrayList<Depinfo>();
		//创建数据库连接
		conn=getDBconn();
		
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("select d.*," + 
				"(select count(*) from specilinfo p where p.depinfoid=d.depinfoid) spnum," + 
				"(SELECT COUNT(*) FROM specilinfo a JOIN classinfo b ON a.spilinfoId=b.spilinfoId WHERE a.depinfoid=d.depinfoid) classnum" + 
				" from depinfo d where 1=1 ");
		//接收院系名称作为查询条件
		if(CheckStr.isEmpty(deps.getDepinfoname())) {
			sb.append(" and depinfoname like '%"+deps.getDepinfoname()+"%'");
		}
		//接收院系ID作为查询条件
		if(CheckStr.isEmpty(deps.getDepinfoId())) {
			sb.append(" and depinfoid="+deps.getDepinfoId());
		}
		if(page!=null) {
			sb.append(" limit ?,?");
		}
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, (page.getPage()-1)*page.getPagesize());
			pst.setInt(2,page.getPagesize());
			//执行查询语句
			rs=pst.executeQuery();
			while(rs.next()) {
				Depinfo dep=new Depinfo();
				dep.setDepinfoId(rs.getInt(1)+"");
				dep.setDepInfoCode(rs.getString(2));
				dep.setDepinfoname(rs.getString(3));
				dep.setDepinfoPreOfTech(rs.getInt(4)+"");
				dep.setDepinfoAssTeach(rs.getInt(5)+"");
				dep.setSpnum(rs.getInt(6)+"");
				dep.setClassnum(rs.getInt(7)+"");
				//将查询到的 记录封装成实体放入集合中
				depList.add(dep);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.closeConn();
		}
		return depList;
	}
	public int selectDepInfoListCount(Depinfo deps){
		//创建数据库连接
		conn=getDBconn();
		int flag=0;
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("SELECT COUNT(*) FROM depinfo WHERE 1=1 ");
		//接收院系名称作为查询条件
		if(CheckStr.isEmpty(deps.getDepinfoname())) {
			sb.append(" and depinfoname like '%"+deps.getDepinfoname()+"%'");
		}
		//接收院系ID作为查询条件
		if(CheckStr.isEmpty(deps.getDepinfoId())) {
			sb.append(" and depinfoid="+deps.getDepinfoId());
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
			//断开数据库连接
			super.closeConn();
		}
		return flag;
	}
	/**
	 * 院系添加
	* @Title: addDepInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param dep
	* @param @return    设定文件 
	* @return int    返回类型    返回0添加失败，否则添加成功
	* @throws
	 */
	public int addDepInfo(Depinfo dep) {
		//创建数据库连接
		conn=getDBconn();
		String ins="insert into depinfo(depinfocode,depinfoname,depinfopreoftech,depinfoassteach) values(?,?,?,?)";
		try {
			//获取预编译对象
			 pst=conn.prepareStatement(ins);
			//设置占位符
			pst.setString(1, dep.getDepInfoCode());
			pst.setString(2,dep.getDepinfoname());
			pst.setString(3, dep.getDepinfoPreOfTech());
			pst.setString(4,  dep.getDepinfoAssTeach());
			//执行SQL语句
			result=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.closeConn();
		}
		return result;
	}
	/**
	 * 院系修改
	* @Title: updateDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param dep
	* @param @return    设定文件 
	* @return int   返回类型   返回0更新失败，否则更新成功
	* @throws
	 */
	public int updateDep(Depinfo dep) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String upd="update depinfo set depinfoname=?,depinfopreoftech=?,depinfoassteach=?"+
		"where depinfoid=?";
		try {
			//获取预编译对象
			pst=conn.prepareStatement(upd);
			//设置站位符
			pst.setString(1, dep.getDepinfoname());
			pst.setString(2, dep.getDepinfoPreOfTech());
			pst.setString(3,  dep.getDepinfoAssTeach());
			pst.setInt(4,Integer.parseInt(dep.getDepinfoId()));
			//执行修改语句
			result=pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.closeConn();
		}
		return result;
	}
	/**
	 * 删除院系信息
	* @Title: detDep 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param depId
	* @param @return    设定文件 
	* @return int    返回类型  返回0删除失败，否则删除成功
	* @throws
	 */
	public int detDep(String depId) {
		//创建数据库连接
		conn=getDBconn();
		//拼接SQL语句
		String del="delete from depinfo where depinfoid=?";
		try {
			//获取预编译对象
			PreparedStatement ps=conn.prepareStatement(del);
			//设置站位符
			ps.setInt(1, Integer.parseInt(depId));
			//执行SQL语句
			result=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.closeConn();
		}
		
		return result;
	}
	/**
	 * 代号检测实现方法
	* @Title: doAjaxCKCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return int    返回类型   返回1则代号已存在，0则代号不存在
	* @throws
	 */
	public int doAjaxCKCode(String code) {
		int b=0;
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from depinfo where depinfocode=?");
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
