<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<title>doMyRegister</title>
<script type="text/javascript">
	var bl=false;
	$(function(){
		$("#userregId").click(function(){
			var name=$("#usernameId").val();
			var pwd=$("#userpwdId").val();
			var Tpwd=$("#tuserpwdId").val();
			var phone=$("#userphoneId").val();
			var email=$("#useremailId").val();
			var val=$("input[name='sex']:checked").val();
			var myimage=$("#userimageId").val();
			var tname=$("#usertnameId").val();
			if(bl==false){
				alert("请进行用户名检测");
				return;
			}
			if(name==""){
				alert("请输入用户名");
				return;
			}
			if(pwd==""){
				alert("请输入密码");
				return;
			}
			if(Tpwd==""){
				alert("请确认密码");
				return;
			}
			if(pwd!=Tpwd){
				alert("密码不一致");
				return;
			}
			if(phone==""){
				alert("请输入手机号码");
				return;
			}
			if(tname==""){
				alert("请输入真实姓名");
				return;
			}
			if(email==""){
				alert("请输入邮箱地址");
				return;
			}
			if(val==null){
				alert("请选择性别");
				return;
			}
			if(myimage==""){
				alert("请选择头像");
				return;
			}
			$("#regForm").submit();
		})
		$("#checkusernameId").click(function(){
			var name=$("#usernameId").val();
			if(name==""){
				alert("请输入用户名");
				return;
			}
			$.ajax({
				type:"post",
				url:"CkName.do",
				data:{adminName:name},
				success:function(data){
					if(data==1){
						alert("该用户已经存在,请更换用户");
						$("#usernameId").val('');
					}
					else{
						bl=true;
						alert("该用户暂未注册,可以进行注册");
						
					}
				}
			});
		})			
	})
</script>
<style type="text/css">
	div{
		width:300px;
		height:400px;
		background-color:Khaki;
		border:2px solid green;
		margin:0 auto;
	}
	div form{
		text-align: center;
	}
</style>
</head>
<body>
	<center><h3><font color="red">用户注册</font></h3></center>
	<div>
		<form action="Res.do" method="post" id="regForm" enctype="multipart/form-data">
			&nbsp;&nbsp;&nbsp;<span>用户名:</span>
			<input type="text" name="adminusername" id="usernameId" placeholder="请输入用户名" style="border-radius: 10px"><p>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>密码:</span>
			<input type="password" name="adminuserpwd" id="userpwdId" placeholder="请输入密码" style="border-radius: 10px"><p>
			<span>确认密码:</span>
			<input type="password" name="tuerpwd" id="tuserpwdId" placeholder="请确认密码" style="border-radius: 10px"><p>
			<span>手机号码:</span>
			<input type="text" name="phone" id="userphoneId" placeholder="请输入手机号码" style="border-radius: 10px"><p>
			<span>真实姓名:</span>
			<input type="text" name="truename" id="usertnameId" placeholder="请输入真实姓名" style="border-radius: 10px"><p>
			<span>电子邮件:</span>
			<input type="text" name="email" id="useremailId" placeholder="请输入电子邮件" style="border-radius: 10px"><p>
			<span>性别:</span>
			<input type="radio" name="sex" id="usermaleId" value="男" ><label for="usermaleId">男</label>
			<input type="radio" name="sex" id="userfemaleId" value="女"><label for="userfemaleId">女</label><p>
			<span>头像:</span>
			<input type="file" name="imagepath" id="userimageId" style="border-radius: 10px"><p>
			<input type="button" name="userreg" id="userregId" value="确认注册" style="position: relative;opacity: 0.7">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="checkusername" id="checkusernameId" value="用户名检测" style="opacity: 0.7">
		</form>
	</div>
</body>
</html>