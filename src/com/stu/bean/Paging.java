package com.stu.bean;

import java.util.List;
/**
 *
 * 项目名称：StuManager
 * 类名称：Paging 
 * 类描述： 分页实体类
 * 创建人：kk
 * 创建时间：2019年1月4日 下午5:44:25
 * 修改人：kk
 * 修改时间：2019年1月4日 下午5:44:25
 * 公司名称: xx公司
 * 修改备注： 
 * 版本号: V1.0
 * 日期: 2019年1月4日
 */
public class Paging {
	private int page;//当前页
	
	private int pagesize;//分页大小
	
	private int indexpage;//首页
	
	private int endpage;//尾页
	
	private int count;//数据条数
	
	private int pagenum;//总页面数
	
	private List<Classinfo> list;//查询到的数据

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getIndexpage() {
		return indexpage;
	}

	public void setIndexpage(int indexpage) {
		this.indexpage = indexpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public List<Classinfo> getList() {
		return list;
	}

	public void setList(List<Classinfo> list) {
		this.list = list;
	}
	
	
	
	
}
