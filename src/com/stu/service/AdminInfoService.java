package com.stu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stu.bean.Adminuser;
import com.stu.dbconn.DBConnection;
import com.stu.untils.Md5Encrypt;

/**
 * 
 * 项目名称：StuManager
 * 类名称：AdminInfoService 
 * 类描述： 用户信息编辑类
 * 创建人：kk
 * 创建时间：2018年12月21日 下午10:32:50
 * 修改人：kk
 * 修改时间：2018年12月21日 下午10:32:50
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2018年12月21日
 */
public class AdminInfoService extends DBConnection{
	private int result=0;
	private ResultSet rs;
	/**
	 * 添加用户实现方法
	* @Title: addMessage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param users
	* @param @return    设定文件 
	* @return int    返回类型   返回值为0添加失败，否则添加成功
	* @throws
	 */
	public int addMessage(Adminuser users) {
		//创建数据库连接
		conn=getDBconn();
		//拼接SQL语句
		String ins="insert into adminuser(adminusername,adminuserpwd,adminusertime,truename,sex,imagepath,phone,email) values(?,?,Now(),?,?,?,?,?)";
		try {
			//获取预编译对象
			pst=conn.prepareStatement(ins);
			pst.setString(1,users.getAdminusername());
			pst.setString(2,Md5Encrypt.MD5(users.getAdminuserpwd()));
			pst.setString(3, users.getTruename());
			pst.setString(4, users.getSex());
			pst.setString(5, users.getImagepath());
			pst.setString(6, users.getPhone());
			pst.setString(7,users.getEmail());
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
	 * 注册用户名检测实现方法
	* @Title: checkReg 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型   返回0则该用户可以添加，返回1则该用户已经存在不能注册
	* @throws
	 */
	public int checkReg(String user) {
		int  b=0;
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句，检测用户名
		String sel="select * from adminuser where adminusername=?";
		try {
			//获取预编译对象
			 pst=conn.prepareStatement(sel);
			//设置占位符
			pst.setString(1, user);
			//执行查询语句
			rs=pst.executeQuery();
			//查询到记录则返回true
			if(rs.next()) {
				b=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.getDBconn();
		}
		return b;
	}
	/**
	 * 编辑用户密码实现方法
	* @Title: editUserPwd 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型  返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editUserPwd(Adminuser user) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String upd="update adminuser set adminuserpwd=? where adminuserid=?";
		try {
			//获取预编译对象
			pst=conn.prepareStatement(upd);
			//设置占位符
			pst.setString(1, user.getAdminuserpwd());
			pst.setInt(2, Integer.parseInt(user.getAdminuserid()));
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
	 * 用户名真实姓名和性别编辑实现方法
	* @Title: editUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param users
	* @param @return    设定文件 
	* @return int    返回类型   返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editUser(Adminuser users) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String upd="update adminuser set truename=?,sex=? where adminuserid=?";
		try {
			//获取预编译对象
			pst=conn.prepareStatement(upd);
			//设置占位符
			pst.setString(1, users.getTruename());
			pst.setString(2, users.getSex());
			pst.setInt(3, Integer.parseInt(users.getAdminuserid()));
			//执行修改语句
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
	 * 编辑用户头像实现方法
	* @Title: editUserImg 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型   返回值为0编辑失败，否则编辑成功
	* @throws
	 */
	public int editUserImg(Adminuser user) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String upd="update adminuser set adminusername=?,imagepath=? where adminuserid=?";
		try {
			//获取预编译对象
			pst=conn.prepareStatement(upd);
			//设置占位符
			pst.setString(1, user.getAdminusername());
			pst.setString(2, user.getImagepath());
			pst.setInt(3, Integer.parseInt(user.getAdminuserid()));
			//执行update语句
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
	 * 登录名和密码检测方法
	* @Title: selectMsg 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user
	* @param @return    设定文件 
	* @return Adminuser    返回类型  返回null则不存在该用户，否则存在
	* @throws
	 */
	public Adminuser selectMsg(Adminuser user) {
		//设置一个空的用户对象
		Adminuser adminuser=null;
		//创建数据库连接
		conn=getDBconn();
		//拼接SQL语句
		String sel="select * from adminuser where adminusername=? and adminuserpwd=?";
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sel);
			//设置占位符
			pst.setString(1, user.getAdminusername());
			pst.setString(2, user.getAdminuserpwd());
			//执行查询语句
			rs=pst.executeQuery();
			//如果查询到了用户则建立该用户实体
			if(rs.next()) {
				adminuser=new Adminuser();
				//设置用户属性
				adminuser.setAdminuserid(rs.getString(1));
				adminuser.setAdminusername(rs.getString(2));
				adminuser.setAdminuserpwd(rs.getString(3));
				adminuser.setAdminusertime(rs.getDate(4));
				adminuser.setTruename(rs.getString(5));
				adminuser.setSex(rs.getString(6));
				adminuser.setImagepath(rs.getString(7));
				adminuser.setPhone(rs.getString(8));
				adminuser.setEmail(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//断开数据库连接
			super.closeConn();
		}
		return adminuser;
	}
}
