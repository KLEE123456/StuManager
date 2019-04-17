package com.stu.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	protected Connection conn=null;
	protected Statement st=null;
	protected PreparedStatement pst=null;
	protected ResultSet rs=null;
	/**
	 * ��ȡ���ݿ�����
	* @Title: getDBconn 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @return    �趨�ļ� 
	* @return Connection    ��������  �������ݿ�����
	* @throws
	 */
	public Connection getDBconn() {
		try {
			Class.forName(ReaderProp.DRIVER);
			conn = DriverManager.getConnection(ReaderProp.URL,ReaderProp.username,ReaderProp.pwd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void closeConn() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
