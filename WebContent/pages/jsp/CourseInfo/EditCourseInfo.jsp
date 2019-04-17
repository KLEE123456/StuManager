
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
<title>编辑课程信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript">  

$(function(){
	$("#saveBtn").click(function(){
		$("#info").submit();
	})
	//回填
	
	$("#CTypeselect").val('${xRCInfoList.get(0).coursetypeid}');
})


</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">课程管理&gt;&gt;编辑课程信息</span>
</div>
<form id="info" name="info" action="<%=path %>/courseInfo.newDo" method="post" > 
<input type="hidden" name="method" value="Edit" />
<input type="hidden" name="courseid" value="${xRCInfoList.get(0).courseinfoid}" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑课程信息</TD>
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
          <td align="right" style="padding-right:5px">课程类别：</td>
          <td>
             <select name="CTypeselect" id="CTypeselect">
             		<option value="">请选择课程类别</option>
        		<c:forEach items="${cyList}" var="cy" varStatus="status">
    				<option value="${cy.coursetypeid}" >${cy.coursetypename}</option>
    			</c:forEach>
             </select>
          </td>
          
        </tr>  
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程代号：</td>
          <td colspan="3">
            <input type="text" id="courseinfocode" readonly="readonly" name="courseinfocode" value="${xRCInfoList.get(0).courseinfocode} " />
           
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程名称：</td>
          <td colspan="3">
            <input type="text" id="courseinfoname" name="courseinfoname" value="${xRCInfoList.get(0).courseinfoname }" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程介绍：</td>
          <td colspan="3">
            <input type="text" id="courseinfoproj" name="courseinfoproj" value="${xRCInfoList.get(0).courseinfoproj }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>理论课时：</td>
          <td colspan="3">
            <input type="text" id="courseinforstper" name="courseinforstper" value="${xRCInfoList.get(0).courseinforstper }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>实践课时：</td>
          <td colspan="3">
            <input type="text" id="courseinfopraper" name="courseinfopraper" value="${xRCInfoList.get(0).courseinfopraper }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程学分：</td>
          <td colspan="3">
            <input type="text" id="courseinfocrehor" name="courseinfocrehor" value="${xRCInfoList.get(0).courseinfocrehor }" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>备注：</td>
          <td colspan="3">
            <input type="text" id="courseinformk" name="courseinformk" value="${xRCInfoList.get(0).courseinformk }" />
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