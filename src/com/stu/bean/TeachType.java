package com.stu.bean;
/**
 * 
 * 项目名称：StuManager
 * 类名称：TeachType 
 * 类描述： 教师类别实体类
 * 创建人：kk
 * 创建时间：2019年1月7日 下午3:20:14
 * 修改人：kk
 * 修改时间：2019年1月7日 下午3:20:14
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
public class TeachType {
	private String teachtypeid;
	
	private String teachtypename;
	
	private String tNum;
	

	public String getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(String teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public String getTeachtypename() {
		return teachtypename;
	}

	public void setTeachtypename(String teachtypename) {
		this.teachtypename = teachtypename;
	}

	public String gettNum() {
		return tNum;
	}

	public void settNum(String tNum) {
		this.tNum = tNum;
	}
	
	
}
