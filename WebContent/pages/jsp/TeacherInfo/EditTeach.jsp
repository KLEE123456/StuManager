
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
<title>编辑教师信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	document.getElementById("teachinfosex${tList.get(0).teachinfosex}").checked="checked";
});	 

$(function(){
	$("#saveBtn").click(function(){
		$("#info").submit();
	})
	//回填
	$("#Depselect").val('${tList.get(0).depinfoid}');
	$("#tchTypeselect").val('${tList.get(0).teachtypeid}')
})


</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教师管理&gt;&gt;编辑教师信息</span>
</div>
<form id="info" name="info" action="<%=path %>/TeacherInfo.NewDo" method="post" > 
<input type="hidden" name="method" value="Edit" />
<input type="hidden" name="tchid" value="${tList.get(0).teachinfoid }" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑教师信息</TD>
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
          <td align="right" style="padding-right:5px">教师类别：</td>
          <td>
             <select name="tchTypeselect" id="tchTypeselect">
             		<option value="">请选择教师类别</option>
        		<c:forEach items="${teachInfoList}" var="ty" varStatus="status">
    				<option value="${ty.teachtypeid}" >${ty.teachtypename}</option>
    			</c:forEach>
             </select>
          </td>
          
        </tr>  
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教师代号：</td>
          <td colspan="3">
            <input type="text" id="teachinfocode" readonly="readonly" name="teachinfocode" value="${tList.get(0).teachinfocode} " />
           
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教师姓名：</td>
          <td colspan="3">
            <input type="text" id="teachinfoname" name="teachinfoname" value="${tList.get(0).teachinfoname }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>性别：</td>
          <td colspan="3">
            <input type="radio" id="teachinfosex男" name="teachinfosex"/><label for="teachinfosex男">男</label>
               <input type="radio" id="teachinfosex女" name="teachinfosex"/><label for="teachinfosex女">女</label>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学历：</td>
          <td colspan="3">
            <input type="text" id="teachknowl" name="teachknowl" value="${tList.get(0).teachknowl }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学位：</td>
          <td colspan="3">
            <input type="text" id="teachinfodeg" name="teachinfodeg" value="${tList.get(0).teachinfodeg }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>所学专业：</td>
          <td colspan="3">
            <input type="text" id="teachinfospec" name="teachinfospec" value="${tList.get(0).teachinfospec }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>职称：</td>
          <td colspan="3">
            <input type="text" id="teachinfotitle" name="teachinfotitle" value="${tList.get(0).teachinfotitle }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>备注：</td>
          <td colspan="3">
            <input type="text" id="teachinformk" name="teachinformk" value="${tList.get(0).teachinformk }" />
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