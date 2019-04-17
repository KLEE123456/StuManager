
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@page isELIgnored="false" %>
<%@taglib uri="../../../WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增课程信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 	
	var flag=false;
	$(function(){
		$("#saveBtn").click(function(){
			var Courseselect=$("#Courseselect").val();
			if(Courseselect==""){
				alert("请选择课程类别");
				return;
			}
			var courseinfocode=$("#courseinfocode").val();
			if(courseinfocode==""){
				alert("请输入课程代号");
				return;
			}
			if(flag==false){
				alert("请进行课程代号检测");
				return;
			}
			var courseinfoname=$("#courseinfoname").val();
			if(courseinfoname==""){
				alert("请输入课程名称");
				return;
			}
			var courseinfoproj=$("#courseinfoproj").val();
			if(courseinfoproj==""){
				alert("请输入课程介绍");
				return;
			}
			var courseinforstper=$("#courseinforstper").val();
			if(courseinforstper==""){
				alert("请输入理论课时");
				return;
			}
			var courseinfopraper=$("#courseinfopraper").val();
			if(courseinfopraper==""){
				alert("请输入实践课时");
				return;
			}
			var courseinfocrehor=$("#courseinfocrehor").val();
			if(courseinfocrehor==""){
				alert("请输入课程学分");
				return;
			}
			var courseinformk=$("#courseinformk").val();
			if(courseinformk==""){
				alert("请输入备注");
				return;
			}
			$("#info").submit();
		})
	})
	function cKCInfoCode(){
		var courseinfocode=$("#courseinfocode").val();
		if(courseinfocode==""){
			alert("请输入院系代号");
			return;
		}
		$.ajax({
			type:'get',
			url:'<%=path %>/courseInfo.newDo?method=ajaxCKCode',
			data:{courseCode:courseinfocode},
			success:function(data){
				if(data=='1'){
					alert('课程代号检测成功,可以进行添加');
					flag=true;
				}
				if(data=='0'){
					alert('课程代号已存在，请进行更换');
					$("#courseinfocode").val('');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">课程管理&gt;&gt;新增课程</span>
</div>
<form id="info" name="info" action="<%=path %>/courseInfo.newDo" method="post" > 
<input type="hidden" name="method" value="Add" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增课程信息</TD>
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
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程类别：</td>
         <td>
             <select name="Courseselect" id="Courseselect" >
             		<option value="">请选择课程类别名称</option>
           		<c:forEach items="${courseInfo}" var="cf" varStatus="status">
    				<option value="${cf.coursetypeid}">${cf.coursetypename}</option>
    			</c:forEach>
             </select>
          </td>
        </tr>     
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程代号：</td>
          <td colspan="3">
            <input type="text" id="courseinfocode" name="courseinfocode" >
            <input type="button" id="CkCourseCodeBtn" name="CkCourseCodeBtn" value="课程代号检测" onclick="cKCInfoCode()" > 
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程名称：</td>
          <td colspan="3">
            <input type="text" id="courseinfoname" name="courseinfoname" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程介绍：</td>
          <td colspan="3">
            <input type="text" id="courseinfoproj" name="courseinfoproj" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>理论课时：</td>
          <td colspan="3">
            <input type="text" id="courseinforstper" name="courseinforstper" />
          </td> 
        </tr>
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>实践课时：</td>
          <td colspan="3">
            <input type="text" id="courseinfopraper" name="courseinfopraper" />
          </td>    
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程学分：</td>
          <td colspan="3">
            <input type="text" id="courseinfocrehor" name="courseinfocrehor" />
          </td>    
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>备注：</td>
          <td colspan="3">
            <input type="text" id="courseinformk" name="courseinformk" />
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