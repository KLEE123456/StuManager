
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
<title>新增教师信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
var flag=false;
$(document).ready(function(){
});	


function submitForm(){
	var Depselect=$("#Depselect").val();
	if(Depselect==""){
		alert("请选择院系!");
		return;
	}
	var TeachTypeselect=$("#TeachTypeselect").val();
	if(TeachTypeselect==""){
		alert("请选择教师类别!");
		return;
	}
	var teachinfocode=$("#teachinfocode").val();
	if(teachinfocode==""){
		alert("请输入教师代号");
		return;
	}
	if(flag==false){
		alert("请进行教师代号检测");
		return;
	}
	var teachinfoname=$("#teachinfoname").val();
	if(teachinfoname==""){
		alert("请输入教师姓名");
		return;
	}
	var teachinfosex=$("#teachinfosex").val();
	if(teachinfosex==""){
		alert("请输入教师性别");
		return;
	}
	var teachknowl=$("#teachknowl").val();
	
	if(teachknowl==""){
		alert("请输入学历");
		return;
	}
	var teachinfodeg=$("#teachinfodeg").val();

	if(teachinfodeg==""){
		alert("请输入学位");
		return;
	}

	var teachinfospec=$("#teachinfospec").val();

	if(teachinfospec==""){
		alert("请输入所学专业");
		return;
	}

	var teachinfotitle=$("#teachinfotitle").val();

	if(teachinfotitle==""){
		alert("请输入职称");
		return;
	}

	var teachinformk=$("#teachinformk").val();

	if(teachinformk==""){
		alert("请输入备注");
		return;
	}
	//提交表单
	$("#info").submit();
	
}
function cKTchCode(){
	var teachinfocode=$("#teachinfocode").val();
	if(teachinfocode==""){
		alert("请输入教师代号");
		return;
	}
	$.ajax({
		type:'get',
		url:'<%=path %>/TeacherInfo.NewDo?method=ajaxCKCode',
		data:{tchcode:teachinfocode},
		success:function(data){
			if(data=='1'){
				alert('教师代号检测成功,可以进行添加');
				flag=true;
			}
			if(data=='0'){
				alert('教师代号已存在，请进行更换');
				$("#teachinfocode").val('');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教师管理&gt;&gt;新增教师信息</span>
</div>
<form id="info" name="info" action="<%=path %>/TeacherInfo.NewDo" method="post">  
<input type="hidden" name="method" value="Add">  
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增教师信息</TD>
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
     	<tr >
          <td align="right" style="padding-right:5px">所在院系：</td>
          <td>
             <select name="Depselect" id="Depselect" >
             		<option value="">请选择院系名称</option>
           		<c:forEach items="${depInfoList}" var="dp" varStatus="status">
    				<option value="${dp.depinfoId}">${dp.depinfoname}</option>
    			</c:forEach>
             </select>
          </td>
			
        </tr>
        <tr >
          <td align="right" style="padding-right:5px">教师类别：</td>
          <td>
             <select name="TeachTypeselect" id="TeachTypeselect" >
             		<option value="">请选择教师类别</option>
           		<c:forEach items="${teachInfoList}" var="ty" varStatus="status">
    				<option value="${ty.teachtypeid}">${ty.teachtypename}</option>
    			</c:forEach>
             </select>
          </td>
			
        </tr>
          <tr>
          <td width="15%" align="right" style="padding-right:5px">教师代号：</td>
          <td width="85%">
          		<input type="text" name="teachinfocode" id="teachinfocode"/>
          		<input type="button" id="CkTchCodeBtn" name="CkTchCodeBtn" value="教师代号检测" onclick="cKTchCode()" />
          </td>
         </tr> 
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px">教师姓名：</td>
          <td width="85%">
          		<input type="text" name="teachinfoname" id="teachinfoname"/>
          </td>
         </tr> 
            
         <tr>
          <td align="right" style="padding-right:5px">性别：</td>
          <td colspan="3">
            <input type="text" id="teachinfosex" name="teachinfosex" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">学历：</td>
          <td colspan="3">
            <input type="text" id="teachknowl" name="teachknowl" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">学位：</td>
          <td colspan="3">
            <input type="text" id="teachinfodeg" name="teachinfodeg" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">所学专业：</td>
          <td colspan="3">
            <input type="text" id="teachinfospec" name="teachinfospec" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">职称：</td>
          <td colspan="3">
            <input type="text" id="teachinfotitle" name="teachinfotitle" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">备注：</td>
          <td colspan="3">
            <input type="text" id="teachinformk" name="teachinformk" />
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
</form>
</body>
</html>