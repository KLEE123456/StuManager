package com.stu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Depinfo;
import com.stu.bean.Paging;
import com.stu.bean.Spilinfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;

/**
 * 
 * 项目名称：StuManager
 * 类名称：SpInfoService 
 * 类描述：专业信息服务类 
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:29:29
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:29:29
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class SpInfoService extends DBConnection{
	//修改，删除，新增返回变量
	private int result=0;
	/**
	 * 专业信息查询实现方法
	* @Title: selectSpInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sp
	* @param @return    设定文件 
	* @return List<Spilinfo>    返回类型   返回保存专业实体对象的集合
	* @throws
	 */
	public List<Spilinfo> selectSpInfo(Spilinfo sp,Paging page){
		//存放专业信息的集合
		List<Spilinfo> spList=new ArrayList<Spilinfo>();
		//创建数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT s.*,(SELECT d.depinfoname FROM depinfo d WHERE d.depinfoid=s.depinfoid) as '所属院系' FROM specilinfo s WHERE 1=1");
		//接收专业名称作为查询条件
		if(CheckStr.isEmpty(sp.getSpilinfoname())) {
			sb.append(" and spilinfoname like '%"+sp.getSpilinfoname()+"%'");
		}
		if(CheckStr.isEmpty(sp.getDepinfoid())) {
			sb.append(" and depinfoid="+sp.getDepinfoid());
		}
		if(CheckStr.isEmpty(sp.getSpilinfoid())) {
			sb.append(" and spilinfoid="+sp.getSpilinfoid());
		}

		if(page!=null) {
			sb.append(" limit ?,?");
		}
		try {
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			rs=pst.executeQuery();
			while(rs.next()) {
				Spilinfo Sp=new Spilinfo();
				Sp.setSpilinfoid(rs.getInt(1)+"");
				Sp.setDepinfoid(rs.getInt(2)+"");
				Sp.setSpilinfocode(rs.getString(3));
				Sp.setSpilinfoname(rs.getString(4));
				Sp.setSpilinfoaim(rs.getString(5));
				Sp.setSpilinfoprodire(rs.getString(6));
				Sp.setDepinfoname(rs.getString(7));
				//将查询到的 记录封装成实体放入集合中
				spList.add(Sp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.closeConn();
		}
		return spList;
	}
	public int selectSpInfoCount(Spilinfo sp){
		//记录总记录条数
		int flag=0;
		//创建数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM specilinfo WHERE 1=1");
		//接收专业名称作为查询条件
		if(CheckStr.isEmpty(sp.getSpilinfoname())) {
			sb.append(" and spilinfoname like '%"+sp.getSpilinfoname()+"%'");
		}
		if(CheckStr.isEmpty(sp.getDepinfoid())) {
			sb.append(" and depinfoid="+sp.getDepinfoid());
		}
		if(CheckStr.isEmpty(sp.getSpilinfoid())) {
			sb.append(" and spilinfoid="+sp.getSpilinfoid());
		}
		try {
			pst=conn.prepareStatement(sb.toString());
			
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
	 * 院系名称查询
	* @Title: selectDepName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List    返回类型   返回保存院系实体对象的集合
	* @throws
	 */
	public List selectDepName() {
		//创建List对象
		List<Depinfo> depList=new ArrayList<Depinfo>();
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String sel="SELECT * FROM depinfo";
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sel);
			//执行查询语句
			rs=pst.executeQuery();
			//遍历结果集
			while(rs.next()) {
				Depinfo depInfo=new Depinfo();
				depInfo.setDepinfoId(rs.getInt(1)+"");
				depInfo.setDepInfoCode(rs.getString(2));
				depInfo.setDepinfoname(rs.getString(3));
				depInfo.setDepinfoPreOfTech(rs.getInt(4)+"");
				depInfo.setDepinfoAssTeach(rs.getInt(5)+"");
				//将查询到的 记录封装成实体放入集合中
				depList.add(depInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return depList;
	}
	/**
	 *专业信息添加方法
	* @Title: addSpInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sp
	* @param @return    设定文件 
	* @return int    返回类型    返回0则表示添加失败，否则添加成功 
	* @throws
	 */
	public int addSpInfo(Spilinfo sp) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String ins="insert into specilinfo(depinfoid,spilinfocode,spilinfoname,spilinfoaim,spilinfoprodire)"+
		"values(?,?,?,?,?)";
		//获取预编译对象
		try {
			pst=conn.prepareStatement(ins);
			//设置占位符
			pst.setInt(1, Integer.parseInt(sp.getDepinfoid()));
			pst.setString(2, sp.getSpilinfocode());
			pst.setString(3, sp.getSpilinfoname());
			pst.setString(4, sp.getSpilinfoaim());
			pst.setString(5, sp.getSpilinfoprodire());
			//执行新增语句
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
	 * 院系编辑方法
	* @Title: editSpInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sp
	* @param @return    设定文件 
	* @return int    返回类型  返回0则表示编辑失败，否则编辑成功 
	* @throws
	 */
	public int editSpInfo(Spilinfo sp) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String upt="update specilinfo set spilinfoname=?,spilinfoaim=?,spilinfoprodire=?"+
		"where spilinfoid=?";
		//获取预编译对象
		try {
			pst=conn.prepareStatement(upt);
			//设置占位符
			pst.setString(1, sp.getSpilinfoname());
			pst.setString(2, sp.getSpilinfoaim());
			pst.setString(3, sp.getSpilinfoprodire());
			pst.setInt(4, Integer.parseInt(sp.getSpilinfoid()));
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
	 *专业信息删除方法
	* @Title: DetSpInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param spId
	* @param @return    设定文件 
	* @return int    返回类型  返回0则删除失败，否则删除成功
	* @throws
	 */
	public int DetSpInfo(String spId) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String det="delete from specilinfo where spilinfoid=?";
		//获取预编译对象
		try {
			pst=conn.prepareStatement(det);
			//设置占位符
			pst.setInt(1, Integer.parseInt(spId));
			//执行删除语句
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
	 * 专业代码检测方法
	* @Title: doAjaxCKCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return int    返回类型   返回1表示专业代码已存在，0则表示不存在
	* @throws
	 */
	public int doAjaxCKCode(String code) {
		int b=0;
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from specilinfo where spilinfocode=?");
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
