
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增班级信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
var flag=false;
$(document).ready(function(){
});	 
//动态无刷新加载专业
function loadSp(){
	var val=$("#Depselect").val();
	$.ajax({
		type:'get',
		url:'<%=path %>/Spinfo.newDo?method=ajaxList',
		data:{'depInfoId':val},
		success:function(data){
			var obj=JSON.parse(data);
			$("#Spselect").empty();
			$("#Spselect").append('<option value="">请选择专业名称</option>');
			$.each(obj,function(n,bean){
				$("#Spselect").append('<option value='+bean.spilinfoid+'>'+bean.spilinfoname+'</option>');
			});
		}
	});
}	
function submitForm(){
	var Depselect=$("#Depselect").val();
	if(Depselect==""){
		alert("请选择院系");
		return;
	}
	var Spselect=$("#Spselect").val();
	if(Spselect==""){
		alert("请选择专业");
		return;
	}
	var classcode=$("#classcode").val();
	if(classcode==""){
		alert("请输入班级代号");
		return;
	}
	if(flag==false){
		alert("请进行班级代号检测");
		return;
	}
	var classname=$("#classname").val();
	if(classname==""){
		alert("请输入班级名称");
		return;
	}
	var pnum=$("#pnum").val();
	if(pnum==""){
		alert("请输入班级人数");
		return;
	}

	var classmk=$("#classmk").val();
	if(classmk==""){
		alert("请输入备注");
		return;
	}
	//提交表单
	$("#info").submit();
	
}

function cKClsCode(){
	var classcode=$("#classcode").val();
	if(classcode==""){
		alert("请输入班级代号");
		return;
	}
	$.ajax({
		type:'get',
		url:'<%=path %>/ClassInfo.newDo?method=ajaxCKCode',
		data:{clsInfoCode:classcode},
		success:function(data){
			if(data=='1'){
				alert('班级代号检测成功,可以进行添加');
				flag=true;
			}
			if(data=='0'){
				alert('班级代号已存在，请进行更换');
				$("#classcode").val('');
			}
		}
	});
}

</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;新增班级信息</span>
</div>
<form id="info" name="info" action="<%=path %>/ClassInfo.newDo" method="post">  
<input type="hidden" name="method" value="Add">  
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增班级信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
     	<tr>
          <td align="right" style="padding-right:5px">所在院系：</td>
          <td>
             <select name="Depselect" id="Depselect" onchange="loadSp()">
             		<option value="">请选择院系名称</option>
           		<c:forEach items="${depInfoList}" var="dp" varStatus="status">
    				<option value="${dp.depinfoId}">${dp.depinfoname}</option>
    			</c:forEach>
             </select>
          </td>
          
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">专业课程：</td>
          <td>
             <select name="Spselect" id="Spselect">
             		<option value="">请选择专业课程</option>
             	<c:forEach items="${spList}" var="sp" varStatus="status">
    				<option value="${sp.spilinfoid}">${sp.spilinfoname}</option>
    			</c:forEach>
             </select>
          </td>
          
        </tr>
          <tr>
          <td width="15%" align="right" style="padding-right:5px">班级代号：</td>
          <td width="85%">
          		<input type="text" name="classcode" id="classcode"/>
          		  <input type="button" id="CkClsCodeBtn" name="CkClsCodeBtn" value="班级代号检测" onclick="cKClsCode()" > 
          </td>
         </tr> 
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px">班级名称：</td>
          <td width="85%">
          		<input type="text" name="classname" id="classname"/>
          </td>
         </tr> 
            
         <tr>
          <td align="right" style="padding-right:5px">班级人数：</td>
          <td colspan="3">
            <input type="text" id="pnum" name="pnum" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">班级备注：</td>
          <td colspan="3">
            <input type="text" id="classmk" name="classmk" />
          </td> 
        </tr> 
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="保存" onclick="submitForm()"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>