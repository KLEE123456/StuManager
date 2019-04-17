package com.stu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.bean.Spilinfo;
import com.stu.dbconn.DBConnection;

public class ClsCountService extends DBConnection{
	public List selectClsCount() {
		List<Spilinfo> CountList=new ArrayList<Spilinfo>();
		conn=getDBconn();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT specilinfo.spilinfoname,COUNT(*) FROM specilinfo");
		sb.append(" JOIN classinfo ON specilinfo.spilinfoid=classinfo.spilinfoid");
		sb.append(" GROUP BY specilinfo.spilinfoid");
		try {
			pst=conn.prepareStatement(sb.toString());
			rs=pst.executeQuery();
			while(rs.next()) {
				Spilinfo sp=new Spilinfo();
				sp.setSpilinfoname(rs.getString(1));
				sp.setClsNum(rs.getInt(2)+"");
				CountList.add(sp);
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
