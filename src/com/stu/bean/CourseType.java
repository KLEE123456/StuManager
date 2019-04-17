package com.stu.bean;
/**
 * 
 * 项目名称：StuManager
 * 类名称：CourseType 
 * 类描述： 课程类别实体类
 * 创建人：kk
 * 创建时间：2019年1月7日 下午3:19:44
 * 修改人：kk
 * 修改时间：2019年1月7日 下午3:19:44
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月7日
 */
public class CourseType {
	private String coursetypeid;
	
	private String coursetypename;
	
	private String courseNum;

	public String getCoursetypeid() {
		return coursetypeid;
	}

	public void setCoursetypeid(String coursetypeid) {
		this.coursetypeid = coursetypeid;
	}

	public String getCoursetypename() {
		return coursetypename;
	}

	public void setCoursetypename(String coursetypename) {
		this.coursetypename = coursetypename;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	
	
}
