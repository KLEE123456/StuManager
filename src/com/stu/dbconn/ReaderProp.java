package com.stu.dbconn;
//读取数据库的配置文件

import java.io.IOException;
import java.util.Properties;

public class ReaderProp {
	private static Properties _pop=new Properties();
	static {
		try {
			
			_pop.load( ReaderProp.class.getResourceAsStream("sql.pro"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String DRIVER=_pop.getProperty("jdbc.driverClassName");
	public static String URL=_pop.getProperty("jdbc.url");
	public static String username=_pop.getProperty("jdbc.username");
	public static String pwd=_pop.getProperty("jdbc.password");
}
