package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Classinfo;
import com.stu.bean.Paging;
import com.stu.bean.Spilinfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;
/**
 * 
 * 项目名称：StuManager
 * 类名称：ClassInfoService 
 * 类描述： 班级服务类
 * 创建人：kk
 * 创建时间：2019年1月1日 下午8:32:03
 * 修改人：kk
 * 修改时间：2019年1月1日 下午8:32:03
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月1日
 */
public class ClassInfoService extends DBConnection{
	private int result=0;
	/**
	 * 
	* @Title: selectClassNum 
	* @Description: TODO(专业下的班级数查询方法) 
	* @param @param spId
	* @param @return    设定文件 
	* @return int    返回类型    返回查询到的班级数，否则返回0
	* @throws
	 */
	public int selectClassNum(String spId) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		String sel="SELECT count(*) pnum FROM classinfo WHERE spilinfoid=?";
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sel);
			pst.setInt(1,Integer.parseInt(spId));
			rs=pst.executeQuery();
			if(rs.next()) {
				//获取记录中的班级数
				result = rs.getInt(1);
			}
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
	* @Title: selectClassInfo 
	* @Description: TODO(班级信息查询方法) 
	* @param @param cf
	* @param @return    设定文件 
	* @return List    返回类型   返回存放班级实体对象的集合
	* @throws
	 */
	public List selectClassInfo(Classinfo cf,Paging page) {
		//创建存放班级实体对象的集合
		List<Classinfo> classList=new ArrayList<Classinfo>();
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT classinfo.*,specilinfo.spilinfoname,depinfo.depinfoname,depinfo.depinfoid FROM classinfo ");
		sb.append(" JOIN specilinfo ON classinfo.spilinfoid=specilinfo.spilinfoid ");
		sb.append(" JOIN depinfo ON depinfo.depinfoid=specilinfo.depinfoid WHERE 1=1 ");
		//判断班级名称是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getClassinfoname())) {
			sb.append(" and classinfoname like '%"+cf.getClassinfoname()+"%'");
		}
		//判断院系ID是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+cf.getDepinfoId());
		}
		//判断专业ID是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+cf.getSpilinfoid());
		}
		//判断班级ID是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getClassinfoid())) {
			sb.append(" and classinfoid="+cf.getClassinfoid());
		}
		if(page!=null)
		sb.append(" limit ?,?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			if(page!=null) {
				pst.setInt(1, (page.getPage()-1)*page.getPagesize());
				pst.setInt(2, page.getPagesize());
			}
			
			//执行查询语句
			rs=pst.executeQuery();
			//遍历查询到的结果集
			while(rs.next()) {
				Classinfo csf=new Classinfo();
				csf.setClassinfoid(rs.getInt(1)+"");
				csf.setSpilinfoid(rs.getInt(2)+"");
				csf.setClassinfocode(rs.getString(3));
				csf.setClassinfoname(rs.getString(4));
				csf.setClassinfosum(rs.getInt(5)+"");
				csf.setClassinformk(rs.getString(6));
				csf.setSpilinfoname(rs.getString(7));
				csf.setDepinfoname(rs.getString(8));
				csf.setDepinfoId(rs.getInt(9)+"");
				//将班级实体对象存入集合中
				classList.add(csf);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		
		return classList;
	}
	/**
	 * 班级数查询
	* @Title: selectClassInfoCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cf
	* @param @return    设定文件 
	* @return int    返回类型  返回查到的班级数
	* @throws
	 */
	public int selectClassInfoCount(Classinfo cf) {
		int result = 0;
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT count(*) pnum FROM classinfo ");
		sb.append(" JOIN specilinfo ON classinfo.spilinfoid=specilinfo.spilinfoid ");
		sb.append(" JOIN depinfo ON depinfo.depinfoid=specilinfo.depinfoid WHERE 1=1 ");
		//判断班级名称是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getClassinfoname())) {
			sb.append(" and classinfoname like '%"+cf.getClassinfoname()+"%'");
		}
		//判断院系ID是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+cf.getDepinfoId());
		}
		//判断专业ID是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+cf.getSpilinfoid());
		}
		//判断班级ID是否为空，不为空则进行追加
		if(CheckStr.isEmpty(cf.getClassinfoid())) {
			sb.append(" and classinfoid="+cf.getClassinfoid());
		}
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//执行查询语句
			rs=pst.executeQuery();
			//遍历查询到的结果集
			while(rs.next()) {
				result = rs.getInt(1);
			}
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
	* @Title: selectSpName 
	* @Description: TODO(专业名称查询方法) 
	* @param @param spinfo
	* @param @return    设定文件 
	* @return List    返回类型  返回存放专业实体对象的集合
	* @throws
	 */
	public List selectSpName(Spilinfo spinfo) {
		List<Spilinfo> spList=new ArrayList<Spilinfo>();
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM specilinfo where 1=1 ");
		if(CheckStr.isEmpty(spinfo.getDepinfoid())) {
			sb.append(" and depinfoid="+spinfo.getDepinfoid());
		}
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//执行查询语句
			rs=pst.executeQuery();
			//遍历查询结果集
			while(rs.next()) {
				Spilinfo sp=new Spilinfo();
				sp.setSpilinfoid(rs.getInt(1)+"");
				sp.setDepinfoid(rs.getInt(2)+"");
				sp.setSpilinfocode(rs.getString(3));
				sp.setSpilinfoname(rs.getString(4));
				sp.setSpilinfoaim(rs.getString(5));
				sp.setSpilinfoprodire(rs.getString(6));
				//将专业对象存入集合中
				spList.add(sp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return spList;
	}
	public int addClassInfo(Classinfo cls) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("insert into classinfo(spilinfoid,classinfocode,classinfoname,classinfosum,classinformk) ");
		sb.append(" values(?,?,?,?,?)");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(cls.getSpilinfoid()));
			pst.setString(2, cls.getClassinfocode());
			pst.setString(3, cls.getClassinfoname());
			pst.setInt(4, Integer.parseInt(cls.getClassinfosum()));
			pst.setString(5, cls.getClassinformk());
			//执行插入语句
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
	* @Title: editClassInfo 
	* @Description: TODO(班级信息编辑方法) 
	* @param @param cls
	* @param @return    设定文件 
	* @return int    返回类型   修改失败返回0，否则修改成功
	* @throws
	 */
	public int editClassInfo(Classinfo cls) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("update classinfo set spilinfoid=?,classinfocode=?,classinfoname=?,classinfosum=?,classinformk=?");
		sb.append(" where classinfoid=?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(cls.getSpilinfoid()));
			pst.setString(2, cls.getClassinfocode());
			pst.setString(3, cls.getClassinfoname());
			pst.setString(4, cls.getClassinfosum());
			pst.setString(5, cls.getClassinformk());
			pst.setInt(6, Integer.parseInt(cls.getClassinfoid()));
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
	 * 
	* @Title: detClassInfo 
	* @Description: TODO(班级信息删除方法) 
	* @param @param classId
	* @param @return    设定文件 
	* @return int    返回类型  返回0删除失败，否则删除成功
	* @throws
	 */
	public int detClassInfo(String classId) {
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("delete from classinfo where classinfoid=?");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(classId));
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
	public int doAjaxCKCode(String code) {
		int b=0;
		//创建数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from classinfo where classinfocode=?");
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
