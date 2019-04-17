<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <% String path = request.getContextPath(); %>
 <%@ page isELIgnored="false" %>
<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院系信息列表</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	/* 控制表单提交方法 */
	$(function(){
		$("#research").click(function(){
			var depinfoname=$("#depinfoname").val();
			if(depinfoname==""){
				alert("请输入搜索内容!");
				return;
			}
			$("#info").submit();
		})
		/* 新增的点击事件 */
		$("#Add").click(function(){
			window.location.href="pages/jsp/DepInfo/AddDep.jsp";
		})
	})
	function doPage(pagging){
		if(pagging!=undefined){
			$("#paging").val(pagging);
		}
		$("#info").submit();
	}
	/* 检查院系是否能被删除的方法 */
	function checkSp(num,id,depname){
		if(confirm("你确定要删除"+depname+"吗")){
			if(num!=0){
				alert('该院系不能删除！');
				return;
			}
			else{
				window.location.href='<%=path %>/Depinfo.newDo?method=Det&depid='+id;
			}
		}
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">院系管理&gt;&gt;院系查询</span>
</div>
<form id="info" name="info" action="<%=path %>/Depinfo.newDo" method="post">
<input type="hidden" value="1" name="paging" id="paging">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">院系列表</td>
    <td width="" align="right">
          院系名称：
      <input type="text" id="depinfoname" name="depinfoname" value="${depName}" class="inputstyle"/>&nbsp;&nbsp;
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
     <td width="" align="center">院系代号</td>
     <td width="" align="center">院系名称</td>
     <td width="" align="center">教学编制</td>
     <td width="" align="center">教辅编制</td>
      <td width="" align="center">专业数</td>
     <td width="" align="center">班级数</td>
     <td width="" align="center">操作</td>
   </tr>
   <!-- 使用c标签进行列表渲染 -->
  	<c:choose>
  		<!-- 如果集合长度为0，则显示以下内容 -->
  		<c:when test="${DepList.size()==0}">
  			 <tr>
     			<td height="60" colspan="8" align="center"><b>&lt;不存在该院系信息&gt;</b></td>
   			</tr>
  		</c:when>
  		<!-- 不为空则对集合中的元素进行遍历 -->
  		<c:otherwise>
  			<c:forEach items="${DepList}" var="deplist" varStatus="status">
  				<tr>
     				<td width="" align="center">${status.count }</td>
     				<td width="" align="center">${deplist.depInfoCode }</td>
     				<td width="" align="center">${deplist.depinfoname }</td>
     				<td width="" align="center">${deplist.depinfoPreOfTech }</td>
     				<td width="" align="center">${deplist.depinfoAssTeach }</td>
     				<td width="" align="center">${deplist.spnum }</td>
     				<td width="" align="center">${deplist.classnum }</td>
     				<!-- 编辑进入到Servlet -->
     				<td width="" align="center"><a href="<%=path %>/Depinfo.newDo?method=Edit&depid=${deplist.depinfoId }">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
     				<!-- 点击删除通过js中的方法进行逻辑判断，如果专业不为0，则不允许删除，否则将本行id传到Servlet -->
     				<a href="javascript:void(0)" onclick="checkSp('${deplist.spnum }','${deplist.depinfoId }','${deplist.depinfoname}')">删除</a></td>
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