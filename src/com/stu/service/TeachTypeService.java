package com.stu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Paging;
import com.stu.bean.TeachType;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * 项目名称：StuManager
 * 类名称：TeachTypeService 
 * 类描述： 教师类别服务类
 * 创建人：kk
 * 创建时间：2019年1月7日 下午2:41:02
 * 修改人：kk
 * 修改时间：2019年1月7日 下午2:41:02
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
public class TeachTypeService extends DBConnection{
	
	private int result=0;
	
	/**
	 * 教师类别查询方法
	* @Title: selectTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param typ
	* @param @return    设定文件 
	* @return List    返回类型  返回存储教师类别对象的集合
	* @throws
	 */
	public List selectTchType(TeachType typ,Paging page) {
		//创建存储教师类别对象的集合
		List<TeachType> tchTypeList=new ArrayList<TeachType>();
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT teachtype.*,COUNT(teachinfo.teachtypeid) FROM teachtype");
		sb.append(" left JOIN teachinfo ON teachtype.teachtypeid=teachinfo.teachtypeid WHERE 1=1");
		//如果教师类别名称不为空，则进行追加
		if(CheckStr.isEmpty(typ.getTeachtypename())) {
			sb.append(" and teachtypename like '%"+typ.getTeachtypename()+"%'");
		}
		sb.append(" GROUP BY teachtype.teachtypeid");
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
				TeachType ty=new TeachType();
				ty.setTeachtypeid(rs.getInt(1)+"");
				ty.setTeachtypename(rs.getString(2));
				ty.settNum(rs.getInt(3)+"");
				
				tchTypeList.add(ty);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return tchTypeList;
	}
	public int selectTchTypeCount(TeachType typ) {
		int flag=0;
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM teachtype WHERE 1=1");
		//如果教师类别名称不为空，则进行追加
		if(CheckStr.isEmpty(typ.getTeachtypename())) {
			sb.append(" and teachtypename like '%"+typ.getTeachtypename()+"%'");
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
	* @Title: addTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param typ
	* @param @return    设定文件 
	* @return int    返回类型  返回0添加失败，否则添加成功
	* @throws
	 */
	public int addTchType(TeachType typ) {
		//获取数据库连接
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		//拼组SQL语句
		sb.append("insert into teachtype(teachtypename) values(?)");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setString(1, typ.getTeachtypename());
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
	* @Title: XRTchtype 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tchTypeId
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws
	 */
	public List XRTchtype(String tchTypeId) {
		List<TeachType> typList=new ArrayList<TeachType>();
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("select * from teachtype where teachtypeid=?");
		try {
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(tchTypeId));
			rs=pst.executeQuery();
			while(rs.next()) {
				TeachType ty=new TeachType();
				ty.setTeachtypeid(rs.getInt(1)+"");
				ty.setTeachtypename(rs.getString(2));
				typList.add(ty);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return typList;
	}
	/**
	 *编辑教师类别的方法
	* @Title: editTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param typ
	* @param @return    设定文件 
	* @return int    返回类型  返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editTchType(TeachType typ) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("update teachtype set teachtypename=? where teachtypeid=?");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setString(1, typ.getTeachtypename());
			pst.setInt(2, Integer.parseInt(typ.getTeachtypeid()));
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
	* @Title: detTchType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param typId
	* @param @return    设定文件 
	* @return int    返回类型  返回0则删除失败，否则删除成功
	* @throws
	 */
	public int detTchType(String typId) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("delete from teachtype where teachtypeid=?");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(typId));
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
