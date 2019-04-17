
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增院系信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 	
	var flag=false;
	$(function(){
		$("#saveBtn").click(function(){
			var depInfoCode=$("#depinfocode").val();
			if(depInfoCode==""){
				alert("请输入院系代号");
				return;
			}
			if(flag==false){
				alert("请进行院系代号检测");
				return;
			}
			var depinfoname=$("#depinfoname").val();
			if(depinfoname==""){
				alert("请输入院系名称");
				return;
			}
			var depinfoPreOfTech=$("#depinfoPreOfTech").val();
			if(depinfoPreOfTech==""){
				alert("请输入教学编制");
				return;
			}
			var depinfoAssTeach=$("#depinfoAssTeach").val();
			if(depinfoAssTeach==""){
				alert("请输入教辅编制");
				return;
			}
			$("#info").submit();
		})
	})
	function cKDepCode(){
		var depCode=$("#depinfocode").val();
		if(depCode==""){
			alert("请输入院系代号");
			return;
		}
		$.ajax({
			type:'get',
			url:'<%=path %>/Depinfo.newDo?method=ajaxCKCode',
			data:{depInfoCode:depCode},
			success:function(data){
				if(data=='1'){
					alert('院系代号检测成功,可以进行添加');
					flag=true;
				}
				if(data=='0'){
					alert('院系代号已存在，请进行更换');
					$("#depinfocode").val('');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">院系管理&gt;&gt;新增院系</span>
</div>
<form id="info" name="info" action="<%=path %>/Depinfo.newDo" method="post" > 
<input type="hidden" name="method" value="Add" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增院系信息</TD>
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
          <td align="right" style="padding-right:5px"><font color="red">*</font>院系代号：</td>
          <td colspan="3">
            <input type="text" id="depinfocode" name="depinfocode" value="${newdep.depInfoCode}">
            <input type="button" id="CkDepCodeBtn" name="CkDepCodeBtn" value="院系代号检测" onclick="cKDepCode()" > 
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>院系名称：</td>
          <td colspan="3">
            <input type="text" id="depinfoname" name="depinfoname" value="${newdep.depinfoname }"/>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教学编制：</td>
          <td colspan="3">
            <input type="text" id="depinfoPreOfTech" name="depinfoPreOfTech" value="${newdep.depinfoPreOfTech }"/>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教辅编制：</td>
          <td colspan="3">
            <input type="text" id="depinfoAssTeach" name="depinfoAssTeach" value="${newdep.depinfoAssTeach }"/>
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