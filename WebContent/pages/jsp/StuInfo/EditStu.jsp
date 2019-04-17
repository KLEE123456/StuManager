
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@page isELIgnored="false" %>

<%@ taglib uri="../../../WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑学生信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>

<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	document.getElementById("stdinfosex${stuInfoList.get(0).stdinfosex}").checked="checked";
});	 

$(function(){
	$("#saveBtn").click(function(){
		$("#info").submit();
	})
	//回填
	$("#Depselect").val('${stuInfoList.get(0).depinfoId}');
	loadSp();
	
})
//动态加载专业
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
				if('${stuInfoList.get(0).spilinfoid}'==bean.spilinfoid){
					$("#Spselect").append('<option value='+bean.spilinfoid+' selected="selected">'+bean.spilinfoname+'</option>');
				}else{
					$("#Spselect").append('<option value='+bean.spilinfoid+'>'+bean.spilinfoname+'</option>');
				}
				
			});
			loadClass();
		}
	});
}
//动态加载班级
function loadClass(){
	var val=$("#Spselect").val();
	$.ajax({
		type:'get',
		url:'<%=path %>/ClassInfo.newDo?method=ajaxList',
		data:{'spInfoId':val},
		success:function(data){
			var obj=JSON.parse(data);
			$("#classselect").empty();
			$("#classselect").append('<option value="">请选择班级名称</option>');
			$.each(obj,function(n,bean){
				if('${stuInfoList.get(0).classinfoid}'==bean.classinfoid){
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生管理&gt;&gt;编辑学生信息</span>
</div>
<form id="info" name="info" action="<%=path %>/StuInfo.newDO" method="post" > 
<input type="hidden" name="method" value="Edit" />
<input type="hidden" name="stuid" value="${stuInfoList.get(0).stdinfoid }" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑学生信息</TD>
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
    				<option value="${dp.depinfoId}" >${dp.depinfoname}</option>
    			</c:forEach>
             </select>
          </td>
          
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">专业课程：</td>
          <td>
             <select name="Spselect" id="Spselect">
             		<option value="">请选择专业课程</option>
             </select>
          </td>
          
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">班级名称：</td>
          <td>
             <select name="classselect" id="classselect">
             		<option value="">请选择班级名称</option>
             		
             </select>
          </td>
          
        </tr>  
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学生代号：</td>
          <td colspan="3">
            <input type="text" id="stdinfocode" readonly="readonly" name="stdinfocode" value="${stuInfoList.get(0).stdinfocode} " />
           
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学生姓名：</td>
          <td colspan="3">
            <input type="text" id="stdinfoname" name="stdinfoname" value="${stuInfoList.get(0).stdinfoname }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学生性别：</td>
          <td colspan="3">
            <input type="radio" id="stdinfosex男" name="stdinfosex"  /><label for="stdinfosex男">男</label>
            <input type="radio" id="stdinfosex女" name="stdinfosex" /><label for="stdinfosex女">女</label>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>身份证号：</td>
          <td colspan="3">
            <input type="text" id="stdinfocard" name="stdinfocard" value="${stuInfoList.get(0).stdinfocard }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>出生日期：</td>
          <td colspan="3">
            <input type="text" id="stdinfobirthd" name="stdinfobirthd" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" value="${stuInfoList.get(0).stdinfobirthd }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>民族：</td>
          <td colspan="3">
            <input type="text" id="stdinfonatns" name="stdinfonatns" value="${stuInfoList.get(0).stdinfonatns }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>联系电话：</td>
          <td colspan="3">
            <input type="text" id="stdinfotel" name="stdinfotel" value="${stuInfoList.get(0).stdinfotel }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>电子邮件：</td>
          <td colspan="3">
            <input type="text" id="stdinfoemail" name="stdinfoemail" value="${stuInfoList.get(0).stdinfoemail }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>入学年份：</td>
          <td colspan="3">
            <input type="text" id="stdinfoyear" name="stdinfoyear" onClick="WdatePicker({el:this,dateFmt:'yyyy'})" value="${stuInfoList.get(0).stdinfoyear }" />
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
            <input type="button" id="saveBtn" Class="btnstyle" value="保存" /> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>