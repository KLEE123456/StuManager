package com.stu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Depinfo;
import com.stu.dbconn.DBConnection;

public class SpCountService extends DBConnection{
	public List selectSpCount() {
		List<Depinfo> CountList=new ArrayList<Depinfo>();
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT depinfo.depinfoname,COUNT(*) FROM depinfo ");
		sb.append(" JOIN specilinfo ON depinfo.depinfoid=specilinfo.depinfoid");
		sb.append(" GROUP BY specilinfo.depinfoid");
		try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Depinfo dep=new Depinfo();
				dep.setDepinfoname(rs.getString(1));
				dep.setSpnum(rs.getInt(2)+"");
				CountList.add(dep);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return CountList;
	}
}
