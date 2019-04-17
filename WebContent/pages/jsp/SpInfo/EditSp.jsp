<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑专业信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	$("#saveBtn").click(function(){
		$("#info").submit();
	})
})
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">专业管理&gt;&gt;编辑专业</span>
</div>
<form id="info" name="info" action="<%=path %>/Spinfo.newDo" method="post" > 
<input type="hidden" name="method" value="Edit" />
<input type="hidden" name="Spid" value="${spList.get(0).spilinfoid }" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑专业信息</TD>
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
          <td align="right" style="padding-right:5px"><font color="red">*</font>专业代码：</td>
          <td colspan="3">
            <input type="text" id="spilinfocode" readonly="readonly" name="spilinfocode" value="${spList.get(0).spilinfocode} " />
           
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>专业名称：</td>
          <td colspan="3">
            <input type="text" id="spilinfoname" name="spilinfoname" value="${spList.get(0).spilinfoname }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>培养目标：</td>
          <td colspan="3">
            <input type="text" id="spilinfoaim" name="spilinfoaim" value="${spList.get(0).spilinfoaim }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>职业面向：</td>
          <td colspan="3">
            <input type="text" id="spilinfoprodire" name="spilinfoprodire" value="${spList.get(0).spilinfoprodire }" />
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