<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <%String path = request.getContextPath(); %>
 <%@ page isELIgnored="false" %>
<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级信息列表</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	$(function(){
		if($("#Depselect").val()!=""){
			loadSp();
		}
	})
	/* 控制表单提交方法 */
	function ctrFormSmt(pageNum){
		
		if(pageNum!=undefined){
			$("#paging").val(pageNum);
		}
		$("#info").submit();
	}
	//班级添加方法
	function addClass(){
		window.location.href="<%=path %>/ClassInfo.newDo?method=DepSpList"; 
	}
	//检测是否要删除
	function checkClass(classSum,className,classId){
		if(confirm("你确定要删除"+className+"吗?")){
			//班级人数 大于0则不能删除
			if(classSum>0){
				alert('该班级不能删除!');
				return;
			}
			//执行删除
			else{
				window.location.href='<%=path %>/ClassInfo.newDo?method=Det&classid='+classId;
			}
		}
	}
	//ajax无刷新加载专业
	function loadSp(){
		var val=$("#Depselect").val();
		$.ajax({
			type:'get',
			url:'<%=path %>/Spinfo.newDo?method=ajaxList',
			data:{'depInfoId':val},
			success:function(data){
				var obj=JSON.parse(data);
				$("#spselect").empty();
				$("#spselect").append('<option value="">请选择专业名称</option>');
				$.each(obj,function(n,bean){
					if(bean.spilinfoid=='${HTClassInfo.spilinfoid}'){
						$("#spselect").append('<option value='+bean.spilinfoid+' selected="selected">'+bean.spilinfoname+'</option>');
					}
					else{
						$("#spselect").append('<option value='+bean.spilinfoid+'>'+bean.spilinfoname+'</option>');
					}
				});
			}
		});
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;班级查询</span>
</div>
<form id="info" name="info" action="<%=path %>/ClassInfo.newDo" method="post">
<input type="hidden" value="List" name="method">
<input type="hidden" value="1" name="paging" id="paging">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  
  <tr>
    <td width="">班级列表</td>
    <td width="" align="right">
 	 院系名称:
    <select name="Depselect" id="Depselect" onchange="loadSp('${DepInfoList.get(0).depinfoId}')">
    	<option value="">请选择院系名称</option>
    	<c:forEach items="${DepInfoList}" var="dep" varStatus="status">
    		<option <c:if test="${dep.depinfoId==HTClassInfo.depinfoId}">selected="selected"</c:if> value="${dep.depinfoId}" >${dep.depinfoname}</option>
    	</c:forEach>
    </select>  
     专业名称:
    <select name="Spselect" id="spselect">
    	<option value="">请选择专业名称</option>
    	<c:forEach items="${SpList}" var="sp" varStatus="status">
    		<option <c:if test="${sp.spilinfoid==HTClassInfo.spilinfoid}">selected="selected"</c:if> value="${sp.spilinfoid}">${sp.spilinfoname}</option>
    	</c:forEach>
    </select>
          班级名称：
      <input type="text" id="classinfoname" name="classinfoname" value="${HTClassInfo.classinfoname}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" id="research" onclick="ctrFormSmt()" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" id="Add" class="btnstyle" onclick="addClass()"/> &nbsp;
      <input type="hidden" name="method" value="List"> 
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">班级名称</td>
     <td width="" align="center">班级人数</td>
     <td width="" align="center">专业名称</td>
     <td width="" align="center">院系名称</td>
     <td width="" align="center">操作</td>
   </tr>
   <!-- 使用c标签进行列表渲染 -->
  	<c:choose>
  		<!-- 如果集合长度为0，则显示以下内容 -->
  		<c:when test="${ClassList.size()==0}">
  			 <tr>
     			<td height="60" colspan="8" align="center"><b>&lt;不存在该班级信息&gt;</b></td>
   			</tr>
  		</c:when>
  		<!-- 不为空则对集合中的元素进行遍历 -->
  		<c:otherwise>
  			<c:forEach items="${ClassList}" var="classlist" varStatus="status">
  				<tr>
     				<td width="" align="center">${status.count }</td>
     				<td width="" align="center">${classlist.classinfoname }</td>
     				<td width="" align="center">${classlist.classinfosum }</td>
     				<td width="" align="center">${classlist.spilinfoname }</td>
     				<td width="" align="center">${classlist.depinfoname }</td>
     				<!-- 编辑进入到Servlet -->
     				<td width="" align="center"><a href="<%=path %>/ClassInfo.newDo?method=Edit&classid=${classlist.classinfoid }">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
     				<!-- 点击删除通过js中的方法进行逻辑判断，如果专业不为0，则不允许删除，否则将本行id传到Servlet -->
     				<a href="javascript:void(0)" onclick="checkClass('${classlist.classinfosum }','${classlist.classinfoname}','${classlist.classinfoid  }')">删除</a></td>
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
		<a  href="javascript:void(0)" onclick="ctrFormSmt(${paging.page+1})">下一页&gt;</a>
		<a  href="javascript:void(0)" onclick="ctrFormSmt(${paging.pagenum})">末页&gt;&gt;</a>
	</c:when>
	<c:when test="${paging.page==paging.pagenum}">
			<a  href="javascript:void(0)" onclick="ctrFormSmt(1)">首页</a>   <a  href="javascript:void(0)" onclick="ctrFormSmt(${paging.page-1})">上一页</a><strong>第${paging.page}页/共${paging.pagenum}页</strong> 下一页   末页
	</c:when>
	
	<c:otherwise>
		<a  href="javascript:void(0)" onclick="ctrFormSmt(1)">首页</a>   <a  href="javascript:void(0)" onclick="ctrFormSmt(${paging.page-1})">上一页</a> <strong>第${paging.page}页/共${paging.pagenum}页</strong>
		<a  href="javascript:void(0)" onclick="ctrFormSmt(${paging.page+1})">下一页&gt;</a>
		<a  href="javascript:void(0)" onclick="ctrFormSmt(${paging.pagenum})">末页&gt;&gt;</a>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>