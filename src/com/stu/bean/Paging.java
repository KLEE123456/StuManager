package com.stu.bean;

import java.util.List;
/**
 *
 * ��Ŀ���ƣ�StuManager
 * �����ƣ�Paging 
 * �������� ��ҳʵ����
 * �����ˣ�kk
 * ����ʱ�䣺2019��1��4�� ����5:44:25
 * �޸��ˣ�kk
 * �޸�ʱ�䣺2019��1��4�� ����5:44:25
 * ��˾����: xx��˾
 * �޸ı�ע�� 
 * �汾��: V1.0
 * ����: 2019��1��4��
 */
public class Paging {
	private int page;//��ǰҳ
	
	private int pagesize;//��ҳ��С
	
	private int indexpage;//��ҳ
	
	private int endpage;//βҳ
	
	private int count;//��������
	
	private int pagenum;//��ҳ����
	
	private List<Classinfo> list;//��ѯ��������

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
