<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <%String path = request.getContextPath(); %>
 <%@ page isELIgnored="false" %>
<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息列表</title>
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
			
		}
	})
	//学生添加
	function addStu(){
		window.location.href="<%=path %>/StuInfo.newDO?method=DepSpClsList"; 
	}
	function checkClass(stuName,stuId){
		if(confirm("你确定要删除"+stuName+"吗?")){
			
			
			window.location.href='<%=path %>/StuInfo.newDO?method=Det&stuid='+stuId;
	
		}
	}
	function doPage(pagging){
		if(pagging!=undefined){
			$("#paging").val(pagging);
		}
		$("#info").submit();
	}
	//动态加载专业
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
					if(bean.spilinfoid=='${HTStuInfo.spilinfoid}'){
						$("#spselect").append('<option value='+bean.spilinfoid+' selected="selected">'+bean.spilinfoname+'</option>');
						
					}
					else{
						$("#spselect").append('<option value='+bean.spilinfoid+'>'+bean.spilinfoname+'</option>');
					}
				});

				loadClass();
			}
		});
	}
	//动态加载班级
	function loadClass(){
		var val=$("#spselect").val();
		$.ajax({
			type:'get',
			url:'<%=path %>/ClassInfo.newDo?method=ajaxList',
			data:{'spInfoId':val},
			success:function(data){
				var obj=JSON.parse(data);
				$("#classselect").empty();
				$("#classselect").append('<option value="">请选择班级名称</option>');
				$.each(obj,function(n,bean){
					if(bean.classinfoid=='${HTStuInfo.classinfoid}'){
						$("#classselect").append('<option value='+bean.classinfoid+' selected="selected">'+bean.classinfoname+'</option>');
					}
					else{
						$("#classselect").append('<option value='+bean.classinfoid+'>'+bean.classinfoname+'</option>');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生管理&gt;&gt;学生查询</span>
</div>
<form id="info" name="info" action="<%=path %>/StuInfo.newDO" method="post">
<input type="hidden" name="paging" id="paging" value="1">
<input type="hidden" value="List" name="method">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  
  <tr>
    <td width="">学生列表</td>
    <td width="" align="right">
 	 院系名称:
    <select name="Depselect" id="Depselect" onchange="loadSp()">
    	
    	<option value="">请选择院系名称</option>
    	<c:forEach items="${DepInfoList}" var="dep" varStatus="status">
    		<option <c:if test="${dep.depinfoId==HTStuInfo.depinfoId}">selected="selected"</c:if> value="${dep.depinfoId}" >${dep.depinfoname}</option>
    	</c:forEach>
    </select>  
     专业名称:
    <select name="Spselect" id="spselect" onchange="loadClass()">
  		<option value="">请选择专业名称</option>
  		
    </select>
          班级名称：
    <select name="classselect" id="classselect" >
    	<option value="">请选择班级名称</option>
    </select>
    学生名称:
    <input type="text" name="stuName" id="stuNameId" value="${HTStuInfo.stdinfoname}">
      <input type="button" value="搜索" id="research" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" id="Add" class="btnstyle" onclick="addStu()"/> &nbsp;
      <input type="hidden" name="method" value="List"> 
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">学生代号</td>
     <td width="" align="center">学生名称</td>
     <td width="" align="center">学生性别</td>
     <td width="" align="center">身份证号</td>
     <td width="" align="center">出生日期</td>
     <td width="" align="center">民族</td>
     <td width="" align="center">联系电话</td>
     <td width="" align="center">电子邮件</td>
     <td width="" align="center">入学年份</td>
     <td width="" align="center">院系名称</td>
     <td width="" align="center">专业名称</td>
     <td width="" align="center">班级名称</td>
      <td width="" align="center">操作</td>
   </tr>
   <!-- 使用c标签进行列表渲染 -->
  	<c:choose>
  		<!-- 如果集合长度为0，则显示以下内容 -->
  		<c:when test="${stuList.size()==0}">
  			 <tr>
     			<td height="60" colspan="14" align="center"><b>&lt;不存在该学生信息&gt;</b></td>
   			</tr>
  		</c:when>
  		<!-- 不为空则对集合中的元素进行遍历 -->
  		<c:otherwise>
  			<c:forEach items="${stuList}" var="stuList" varStatus="status">
  				<tr>
     				<td width="" align="center">${status.count }</td>
     				<td width="" align="center">${stuList.stdinfocode }</td>
     				<td width="" align="center">${stuList.stdinfoname }</td>
     				<td width="" align="center">${stuList.stdinfosex }</td>
     				<td width="" align="center">${stuList.stdinfocard }</td>
     				<td width="" align="center">${stuList.stdinfobirthd }</td>
     				<td width="" align="center">${stuList.stdinfonatns }</td>
     				<td width="" align="center">${stuList.stdinfotel }</td>
     				<td width="" align="center">${stuList.stdinfoemail }</td>
     				<td width="" align="center">${stuList.stdinfoyear }</td>
     				<td width="" align="center">${stuList.depinfoname }</td>
     				<td width="" align="center">${stuList.spilinfoname }</td>
     				<td width="" align="center">${stuList.classinfoname }</td>
     				<!-- 编辑进入到Servlet -->
     				<td width="" align="center"><a href="<%=path %>/StuInfo.newDO?method=Edit&stuid=${stuList.stdinfoid }">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
     				<!-- 点击删除通过js中的方法进行逻辑判断，如果专业不为0，则不允许删除，否则将本行id传到Servlet -->
     				<a href="javascript:void(0)" onclick="checkClass('${stuList.stdinfoname}','${stuList.stdinfoid  }')">删除</a></td>
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