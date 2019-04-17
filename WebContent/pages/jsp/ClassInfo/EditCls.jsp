
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
<title>编辑班级信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	$("#saveBtn").click(function(){
		$("#info").submit();
	})
	//院系回填
	$("#Depselect").val('${classList.get(0).depinfoId}');
	//专业回填
	loadSp();
})
//ajax无刷新加载专业
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
				if('${classList.get(0).spilinfoid}'==bean.spilinfoid){
					$("#Spselect").append('<option value='+bean.spilinfoid+' selected="selected">'+bean.spilinfoname+'</option>');
				}else{
					$("#Spselect").append('<option value='+bean.spilinfoid+'>'+bean.spilinfoname+'</option>');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;编辑班级</span>
</div>
<form id="info" name="info" action="<%=path %>/ClassInfo.newDo" method="post" > 
<input type="hidden" name="method" value="Edit" />
<input type="hidden" name="clsid" value="${classList.get(0).classinfoid }" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑班级信息</TD>
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
             	<c:forEach items="${spList}" var="sp" varStatus="status">
    				<option value="${sp.spilinfoid}">${sp.spilinfoname}</option>
    			</c:forEach>
             </select>
          </td>
          
        </tr>   
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>班级代号：</td>
          <td colspan="3">
            <input type="text" id="classinfocode" readonly="readonly" name="classinfocode" value="${classList.get(0).classinfocode} " />
           
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>班级名称：</td>
          <td colspan="3">
            <input type="text" id="classinfoname" name="classinfoname" value="${classList.get(0).classinfoname }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>班级人数：</td>
          <td colspan="3">
            <input type="text" id="classinfosum" name="classinfosum" value="${classList.get(0).classinfosum }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>班级备注：</td>
          <td colspan="3">
            <input type="text" id="classinformk" name="classinformk" value="${classList.get(0).classinformk }" />
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