
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
<title>新增学生信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
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
//动态无刷新加载班级
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
				if(false){
					$("#classselect").append('<option value='+bean.classinfoid+' selected="selected">'+bean.classinfoname+'</option>');
				}
				else{
					$("#classselect").append('<option value='+bean.classinfoid+'>'+bean.classinfoname+'</option>');
				}
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

	var classselect=$("#classselect").val();

	if(classselect==""){
		alert("请选择班级");
		return;
	}

	var stdinfocode=$("#stdinfocode").val();

	if(stdinfocode==""){
		alert("请输入学生代号");
		return;
	}
	if(flag==false){
		alert("请进行学生代号检测");
		return;
	}
	var stdinfoname=$("#stdinfoname").val();

	if(stdinfoname==""){
		alert("请输入学生姓名");
		return;
	}

	var stdinfosex=$("input[name='stdinfosex']:checked").val();
	if(stdinfosex==null){
		alert("请选择学生性别");
		return;
	}

	var stdinfocard=$("#stdinfocard").val();

	if(stdinfocard==""){
		alert("请输入身份证号");
		return;
	}

	var stdinfobirthd=$("#stdinfobirthd").val();

	if(stdinfobirthd==""){
		alert("请输入点击选择出生日期");
		return;
	}
	var stdinfonatns=$("#stdinfonatns").val();

	if(stdinfonatns==""){
		alert("请输入民族");
		return;
	}

	var stdinfotel=$("#stdinfotel").val();

	if(stdinfotel==""){
		alert("请输入学生联系电话");
		return;
	}

	var stdinfoemail=$("#stdinfoemail").val();

	if(stdinfoemail==""){
		alert("请输入电子邮件");
		return;
	}

	var stdinfoyear=$("#stdinfoyear").val();

	if(stdinfoyear==""){
		alert("请点击选择入学年份");
		return;
	}
	//提交表单
	$("#info").submit();
	
}
function cKStuCode(){
	var stdinfocode=$("#stdinfocode").val();
	if(stdinfocode==""){
		alert("请输入学生代码");
		return;
	}
	$.ajax({
		type:'get',
		url:'<%=path %>/StuInfo.newDO?method=ajaxCKCode',
		data:{stucode:stdinfocode},
		success:function(data){
			if(data=='1'){
				alert('学生代码检测成功,可以进行添加');
				flag=true;
			}
			if(data=='0'){
				alert('学生代码已存在，请进行更换');
				$("#stdinfocode").val('');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生管理&gt;&gt;新增学生信息</span>
</div>
<form id="info" name="info" action="<%=path %>/StuInfo.newDO" method="post">  
<input type="hidden" name="method" value="Add">  
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增学生信息</TD>
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
             <select name="Spselect" id="Spselect" onchange="loadClass()">
             
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
          <td width="15%" align="right" style="padding-right:5px">学生代号：</td>
          <td width="85%">
          		<input type="text" name="stdinfocode" id="stdinfocode"/>
          		<input type="button" id="CkStuCodeBtn" name="CkStuCodeBtn" value="学生代号检测" onclick="cKStuCode()" />
          </td>
         </tr> 
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px">学生姓名：</td>
          <td width="85%">
          		<input type="text" name="stdinfoname" id="stdinfoname"/>
          </td>
         </tr> 
            
         <tr>
          <td align="right" style="padding-right:5px">学生性别：</td>
          <td colspan="3">
            <input type="radio" id="stdinfosexMale" name="stdinfosex" /><label for="stdinfosexMale">男</label>
            <input type="radio" id="stdinfosexFemale" name="stdinfosex" /><label for="stdinfosexFemale">女</label>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">身份证号：</td>
          <td colspan="3">
            <input type="text" id="stdinfocard" name="stdinfocard" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px" >出生日期：</td>
          <td colspan="3">
            <input type="text" id="stdinfobirthd" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" name="stdinfobirthd" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">民族：</td>
          <td colspan="3">
            <input type="text" id="stdinfonatns" name="stdinfonatns" />
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">联系电话：</td>
          <td colspan="3">
            <input type="text" id="stdinfotel" name="stdinfotel" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">电子邮件：</td>
          <td colspan="3">
            <input type="text" id="stdinfoemail" name="stdinfoemail" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">入学年份：</td>
          <td colspan="3">
            <input type="text" id="stdinfoyear" onClick="WdatePicker({el:this,dateFmt:'yyyy'})" name="stdinfoyear" />
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