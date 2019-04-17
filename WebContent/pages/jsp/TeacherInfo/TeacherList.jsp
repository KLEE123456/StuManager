<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <%String path = request.getContextPath(); %>
 <%@ page isELIgnored="false" %>
<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师信息列表</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	$(function(){
		$("#research").click(function(){
			$("#info").submit();
		})
		if($("#Depselect").val()!=""){
			//专业和班级回填
			loadSp();
			loadClass();
		}
	})
	//教师添加
	function addTeach(){
		window.location.href="<%=path %>/TeacherInfo.NewDo?method=DepAndTypeList"; 
	}
	function checkClass(tchName,tchId,clsNum){
		if(confirm("你确定要删除"+tchName+"老师吗?")){

			if(clsNum>0){
				alert("该教师不能 删除");
				return;
			}
			else{
				window.location.href='<%=path %>/TeacherInfo.NewDo?method=Det&Tchid='+tchId;
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教师管理&gt;&gt;教师查询</span>
</div>
<form id="info" name="info" action="<%=path %>/TeacherInfo.NewDo" method="post">
<input type="hidden" name="paging" id="paging" value="1">
<input type="hidden" value="List" name="method">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  
  <tr>
    <td width="">教师列表</td>
    <td width="" align="right">
 	 院系名称:
    <select name="Depselect" id="Depselect" >
    	
    	<option value="">请选择院系名称</option>
    	<c:forEach items="${DepInfoList}" var="dep" varStatus="status">
    		<option <c:if test="${dep.depinfoId==HTTeachInfo.depinfoid}">selected="selected"</c:if> value="${dep.depinfoId}" >${dep.depinfoname}</option>
    	</c:forEach>
    </select>  
   
   
    教师名称:
    <input type="text" name="TeachName" id="TeachNameId" value="${HTTeachInfo.teachinfoname}">
      <input type="button" value="搜索" id="research" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" id="Add" class="btnstyle" onclick="addTeach()"/> &nbsp;
      <input type="hidden" name="method" value="List"> 
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">教师代号</td>
     <td width="" align="center">教师名称</td>
     <td width="" align="center">教师性别</td>
     <td width="" align="center">学历</td>
     <td width="" align="center">学位</td>
     <td width="" align="center">所学专业</td>
     <td width="" align="center">职称</td>
     <td width="" align="center">备注</td>
     <td width="" align="center">教师类别</td>
     <td width="" align="center">院系名称</td>
     <td width="" align="center">任课班级数</td>
      <td width="" align="center">操作</td>
   </tr>
   <!-- 使用c标签进行列表渲染 -->
  	<c:choose>
  		<!-- 如果集合长度为0，则显示以下内容 -->
  		<c:when test="${teachList.size()==0}">
  			 <tr>
     			<td height="60" colspan="13" align="center"><b>&lt;不存在该教师信息&gt;</b></td>
   			</tr>
  		</c:when>
  		<!-- 不为空则对集合中的元素进行遍历 -->
  		<c:otherwise>
  			<c:forEach items="${teachList}" var="tchList" varStatus="status">
  				<tr>
     				<td width="" align="center">${status.count }</td>
     				<td width="" align="center">${tchList.teachinfocode }</td>
     				<td width="" align="center">${tchList.teachinfoname }</td>
     				<td width="" align="center">${tchList.teachinfosex }</td>
     				<td width="" align="center">${tchList.teachknowl }</td>
     				<td width="" align="center">${tchList.teachinfodeg }</td>
     				<td width="" align="center">${tchList.teachinfospec }</td>
     				<td width="" align="center">${tchList.teachinfotitle }</td>
     				<td width="" align="center">${tchList.teachinformk }</td>
     				<td width="" align="center">${tchList.teachtypename }</td>
     				<td width="" align="center">${tchList.depinfoname }</td>
     				<td width="" align="center">${tchList.clsNum }</td>
     				<!-- 编辑进入到Servlet -->
     				<td width="" align="center"><a href="<%=path %>/TeacherInfo.NewDo?method=Edit&tchid=${tchList.teachinfoid }">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
     				<!-- 点击删除通过js中的方法进行逻辑判断，如果专业不为0，则不允许删除，否则将本行id传到Servlet -->
     				<a href="javascript:void(0)" onclick="checkClass('${tchList.teachinfoname}','${tchList.teachinfoid  }','${tchList.clsNum}')">删除</a></td>
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