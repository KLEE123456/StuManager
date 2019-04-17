package com.stu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Paging;
import com.stu.bean.Stuinfo;
import com.stu.dbconn.DBConnection;
import com.stu.untils.CheckStr;

/**
 * 
 * 项目名称：StuManager
 * 类名称：StuService 
 * 类描述： 学生服务类
 * 创建人：kk
 * 创建时间：2019年1月1日 下午8:17:18
 * 修改人：kk
 * 修改时间：2019年1月1日 下午8:17:18
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月1日
 */
public class StuService extends DBConnection{
	private int result=0;
	/**
	 * 
	* @Title: selectStuInfo 
	* @Description: TODO(学生信息查询实现方法) 
	* @param @param stu
	* @param @return    设定文件 
	* @return List    返回类型     查询到记录返回存放学生实体对象的集合
	* @throws
	 */
	public List selectStuInfo(Stuinfo stu,Paging page) {
		List<Stuinfo> stuList=new ArrayList<Stuinfo>();
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT studentinfo.*,depinfo.depinfoname,specilinfo.spilinfoname,classinfo.classinfoname,depinfo.depinfoid,specilinfo.spilinfoid FROM studentinfo ");
		sb.append(" JOIN classinfo ON studentinfo.classinfoid=classinfo.classinfoid ");
		sb.append(" JOIN specilinfo ON classinfo.spilinfoid=specilinfo.spilinfoid");
		sb.append(" JOIN depinfo ON  specilinfo.depinfoid=depinfo.depinfoid where 1=1");
		//判断院系ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+stu.getDepinfoId());
		}
		//判断专业ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+stu.getSpilinfoid());
		}
		//判断班级ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getClassinfoid())) {
			sb.append(" and classinfo.classinfoid="+stu.getClassinfoid());
		}
		//判断学生 姓名是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getStdinfoname())) {
			sb.append(" and studentinfo.stdinfoname like '%"+stu.getStdinfoname()+"%'");
		}
		//判断学生ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getStdinfoid())) {
			sb.append(" and studentinfo.stdinfoid="+stu.getStdinfoid());
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
			
			//执行select语句
			rs=pst.executeQuery();
			//遍历结果集
			while(rs.next()) {
				//创建存放结果集的对象
				Stuinfo stuInfo=new Stuinfo();
				stuInfo.setStdinfoid(rs.getInt(1)+"");
				stuInfo.setClassinfoid(rs.getInt(2)+"");
				stuInfo.setStdinfocode(rs.getString(3));
				stuInfo.setStdinfoname(rs.getString(4));
				stuInfo.setStdinfosex(rs.getString(5));
				stuInfo.setStdinfocard(rs.getString(6));
				stuInfo.setStdinfobirthd(rs.getString(7));
				stuInfo.setStdinfonatns(rs.getString(8));
				stuInfo.setStdinfotel(rs.getString(9));
				stuInfo.setStdinfoemail(rs.getString(10));
				stuInfo.setStdinfoyear(rs.getString(11));
				stuInfo.setDepinfoname(rs.getString(12));
				stuInfo.setSpilinfoname(rs.getString(13));
				stuInfo.setClassinfoname(rs.getString(14));
				stuInfo.setDepinfoId(rs.getInt(15)+"");
				stuInfo.setSpilinfoid(rs.getInt(16)+"");
				//将对象添加到集合中
				stuList.add(stuInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			super.closeConn();
		}
		return stuList;
	}
	public int selectStuInfoCount(Stuinfo stu) {
		int flag=0;
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM studentinfo WHERE 1=1");
		//判断院系ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getDepinfoId())) {
			sb.append(" and depinfo.depinfoid="+stu.getDepinfoId());
		}
		//判断专业ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getSpilinfoid())) {
			sb.append(" and specilinfo.spilinfoid="+stu.getSpilinfoid());
		}
		//判断班级ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getClassinfoid())) {
			sb.append(" and classinfo.classinfoid="+stu.getClassinfoid());
		}
		//判断学生 姓名是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getStdinfoname())) {
			sb.append(" and studentinfo.stdinfoname like '%"+stu.getStdinfoname()+"%'");
		}
		//判断学生ID是否为空，不为空则追加
		if(CheckStr.isEmpty(stu.getStdinfoid())) {
			sb.append(" and studentinfo.stdinfoid="+stu.getStdinfoid());
		}
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//执行select语句
			rs=pst.executeQuery();
			//遍历结果集
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
	 * 
	* @Title: addStuInfo 
	* @Description: TODO(学生添加方法) 
	* @param @param stu
	* @param @return    设定文件 
	* @return int    返回类型  返回0则添加失败，否则添加成功
	* @throws
	 */
	public int addStuInfo(Stuinfo stu) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("insert into studentinfo(classinfoid,stdinfocode,stdinfoname,stdinfosex,stdinfocard,stdinfobirthd,stdinfonatns,stdinfotel,stdinfoemail,stdinfoyear) ");
		sb.append("  values(?,?,?,?,?,?,?,?,?,?)");
		//获取预编译对象
		try {
			pst=conn.prepareStatement(sb.toString());
			//设置个占位符
			pst.setInt(1, Integer.parseInt(stu.getClassinfoid()));
			pst.setString(2, stu.getStdinfocode());
			pst.setString(3, stu.getStdinfoname());
			pst.setString(4, stu.getStdinfosex());
			pst.setString(5, stu.getStdinfocard());
			pst.setString(6, stu.getStdinfobirthd());
			pst.setString(7, stu.getStdinfonatns());
			pst.setString(8, stu.getStdinfotel());
			pst.setString(9, stu.getStdinfoemail());
			pst.setString(10, stu.getStdinfoyear());
			//执行 插入语句
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
	* @Title: editStuInfo 
	* @Description: TODO(学生信息编辑方法) 
	* @param @param stu
	* @param @return    设定文件 
	* @return int    返回类型  返回0编辑失败，否则编辑成功
	* @throws
	 */
	public int editStuInfo(Stuinfo stu) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("update studentinfo set classinfoid=?,stdinfocode=?,stdinfoname=?,stdinfosex=?,stdinfocard=?,stdinfobirthd=?,stdinfonatns=?,stdinfotel=?,stdinfoemail=?,stdinfoyear=?");
		sb.append(" where stdinfoid=?");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			//设置占位符
			pst.setInt(1, Integer.parseInt(stu.getClassinfoid()));
			pst.setString(2, stu.getStdinfocode());
			pst.setString(3, stu.getStdinfoname());
			pst.setString(4, stu.getStdinfosex());
			pst.setString(5, stu.getStdinfocard());
			pst.setString(6, stu.getStdinfobirthd());
			pst.setString(7, stu.getStdinfonatns());
			pst.setString(8, stu.getStdinfotel());
			pst.setString(9, stu.getStdinfoemail());
			pst.setString(10, stu.getStdinfoyear());
			pst.setInt(11, Integer.parseInt(stu.getStdinfoid()));
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
	* @Title: detStuInfo 
	* @Description: TODO(学生信息删除方法) 
	* @param @param stuId
	* @param @return    设定文件 
	* @return int    返回类型    返回0删除失败，否则删除成功
	* @throws
	 */
	public int detStuInfo(String stuId) {
		//获取数据库连接
		conn=getDBconn();
		//拼组SQL语句
		StringBuffer sb=new StringBuffer();
		sb.append("delete from studentinfo where stdinfoid=?");
		try {
			//获取预编译对象
			pst=conn.prepareStatement(sb.toString());
			pst.setInt(1, Integer.parseInt(stuId));
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
	 * 学生代号检测方法
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
		sb.append(" select * from studentinfo where stdinfocode=?");
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
