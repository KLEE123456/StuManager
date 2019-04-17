<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <% String path = request.getContextPath(); %>
 <%@ page isELIgnored="false" %>
<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息列表</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	/* 控制表单提交方法 */
	$(function(){
		$("#research").click(function(){
			var courseinfoname=$("#courseinfoname").val();
			if(courseinfoname==""){
				alert("请输入搜索内容!");
				return;
			}
			$("#info").submit();
		})
		/* 新增的点击事件 */
		$("#Add").click(function(){
			window.location.href="<%=path %>/courseInfo.newDo?method=selCourseInfo";
		})
	})
	/* 检查课程类别是否能被删除的方法 */
	function checkcyp(cname,num,id){
		if(confirm("你确定要删除课程名称为"+cname+"的一条记录吗吗")){
			if(num!=0){
				alert('该课程类别不能删除！');
				return;
			}
			else{
				window.location.href='<%=path %>/courseInfo.newDo?method=Det&cid='+id;
			}
		}
	}
	function doPage(pagging){
		if(pagging!=undefined){
			$("#paging").val(pagging);
		}
		$("#info").submit();
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">课程管理&gt;&gt;课程信息查询</span>
</div>
<form id="info" name="info" action="<%=path %>/courseInfo.newDo" method="post">
<input type="hidden" name="method" value="List">
<input type="hidden" value="1" name="paging" id="paging">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">课程信息列表</td>
    <td width="" align="right">
         课程名称：
      <input type="text" id="courseinfoname" name="courseinfoname" value="${courseName}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" id="research" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" id="Add" class="btnstyle"/> &nbsp;
      <input type="hidden" name="method" value="List"> 
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">课程代码</td>
      <td width="" align="center">课程名称</td>
      <td width="" align="center">课程介绍</td>
      <td width="" align="center">理论学时</td>
      <td width="" align="center">实践学时</td>
      <td width="" align="center">课程学分</td>
       <td width="" align="center">备注</td>
       <td width="" align="center">授课教师数</td>
         <td width="" align="center">课程类别</td>
     <td width="" align="center">操作</td>
   </tr>
   <!-- 使用c标签进行列表渲染 -->
  	<c:choose>
  		<!-- 如果集合长度为0，则显示以下内容 -->
  		<c:when test="${courseInfoList.size()==0}">
  			 <tr>
     			<td height="60" colspan="10" align="center"><b>&lt;不存在该课程信息&gt;</b></td>
   			</tr>
  		</c:when>
  		<!-- 不为空则对集合中的元素进行遍历 -->
  		<c:otherwise>
  			<c:forEach items="${courseInfoList}" var="courselist" varStatus="status">
  				<tr>
     				<td width="" align="center">${status.count }</td>
     				<td width="" align="center">${courselist.courseinfocode }</td>
     				<td width="" align="center">${courselist.courseinfoname }</td>
     				<td width="" align="center">${courselist.courseinfoproj }</td>
     				<td width="" align="center">${courselist.courseinforstper }</td>
     				<td width="" align="center">${courselist.courseinfopraper }</td>
     				<td width="" align="center">${courselist.courseinfocrehor }</td>
     				<td width="" align="center">${courselist.courseinformk }</td>
     				<td width="" align="center">${courselist.tchNum }</td>
     				<td width="" align="center">${courselist.coursetypename}</td>
     				<!-- 编辑进入到Servlet -->
     				<td width="" align="center"><a href="<%=path %>/courseInfo.newDo?method=Edit&cid=${courselist.courseinfoid }">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
     				<!-- 点击删除通过js中的方法进行逻辑判断，如果专业不为0，则不允许删除，否则将本行id传到Servlet -->
     				<a href="javascript:void(0)" onclick="checkcyp('${courselist.courseinfoname}','${courselist.tchNum }','${courselist.courseinfoid}')">删除</a></td>
   				</tr>
  			</c:forEach>
  		</c:otherwise>	
  	</c:choose> 
</table>
</form>
<div style="width:300px;height:100px;margin:0 auto" >
<c:choose>
	
	<c:when test="${paging.pagenum==1}">
			首页   上一页 <strong>第${paging.page}页/共${paging.pagenum}页</strong>下一页   末页
	</c:when>
	<c:when test="${paging.page==1}">
		首页   上一页 <strong>第${paging.page}页/共${paging.pagenum}页</strong>
		<a  href="javascript:void(0)" onclick="doPage(${paging.page+1})">下一页&gt;</a>
		<a  href="javascript:void(0)" onclick="doPage(${paging.pagenum})">末页&gt;&gt;</a>
	</c:when>
	<c:when test="${paging.page==paging.pagenum}">
			<a  href="javascript:void(0)" onclick="doPage(1)">首页</a>   <a  href="javascript:void(0)" onclick="doPage(${paging.page-1})">上一页</a><strong>第${paging.page}页/共${paging.pagenum}页</strong> 下一页   末页
	</c:when>
	
	<c:otherwise>
		<a  href="javascript:void(0)" onclick="doPage(1)">首页</a>   <a  href="javascript:void(0)" onclick="doPage(${paging.page-1})">上一页</a> <strong>第${paging.page}页/共${paging.pagenum}页</strong>
		<a  href="javascript:void(0)" onclick="doPage(${paging.page+1})">下一页&gt;</a>
		<a  href="javascript:void(0)" onclick="doPage(${paging.pagenum})">末页&gt;&gt;</a>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>