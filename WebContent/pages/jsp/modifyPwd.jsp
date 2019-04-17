<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码修改</title>
<link href="../../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
<script>
	$(function(){
		$("#saveBtn").click(function(){
			var pwd=$("#newuserId").val();
			if(pwd==""){
				alert("请输入新密码");
				return;
			}
			var tpwd=$("#TnewuserId").val();
			if(tpwd==""){
				alert("请确认密码!");
				return;
			}
			if(pwd!=tpwd){
				alert("密码不一致,请重新输入");
				$("#newuserId").val('');
				$("#TnewuserId").val('');
				return;
			}
			$.ajax({
				type:"post",
				url:"../../EditPwd.do",
				data:{truePwd:tpwd},
				success:function(data){
					if(data=='0'){
						alert('修改失败');
						window.location.href='modifyPwd.jsp';
					}
					else if(data=='1'){
						alert('修改成功');
						window.location.href='modifyPwd.jsp';
					}
				}
			});
		})
	})
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="../../image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统管理&gt;&gt;个人信息</span>
</div>
<form id="pwdinfoId" name="pwdinfo" action="" method="post"> 
	<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
		<tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">密码修改</TD>
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
		          <td width="15%" align="right" style="padding-right:5px">原密码：</td>
		          <td width="85%">${admins.adminuserpwd }</td>
		        </tr> 
	     		<tr>
		          <td width="15%" align="right" style="padding-right:5px">新密码：</td>
		          <td width="85%"><input type="password" id="newuserId" name="adminuserpwd"/></td>
		        </tr> 
		        <tr>
		          <td width="15%" align="right" style="padding-right:5px">确认密码：</td>
		          <td width="85%"><input type="password" id="TnewuserId" name="adminuserpwd"/></td>
		        </tr> 
		     </table>
	     </td>
	   </tr> 
	    <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="编 辑" /> 
          </td>
        </tr>
      </table>
     </td>
   </tr> 
	</table>
</form>
</body>
</html>