<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<title>myLogin</title>
<script type="text/javascript">
		document.onkeydown=function(event){
		var e=event || window.event;
		if(e&&e.keyCode==13){
			var name=$("#usernameId").val();
			var pwd=$("#userpwdId").val();
			var srand=$("#mysrandId").val();
			if(name==""){
				alert("请输入用户名");
				return;		
			}			
			if(pwd==""){
				alert("请输入密码");
				return;
			}
			if(srand==""){
				alert("请输入验证码");
				return;
			}
			$.ajax({
				type:'post',
				url:'login.do',
				data:{uName:name,uPwd:pwd,uSrand:srand},
				success:function(data){
						if(data=='0'){
							alert('验证码错误');
							$("#mysrandId").val('');
							document.getElementById("myimg").src="srand.do?"+Math.random();
						}
						if(data=='1'){
							alert('登录失败,请检查用户名和密码是否正确');
							$("#userpwdId").val('');
							$("#mysrandId").val('');
							document.getElementById("myimg").src="srand.do?"+Math.random();
						}
						if(data=='2'){
							window.location.href='pages/home.jsp';
						}
					}
				});
			
		}
	}
	$(function(){
		$("#myA").click(function(){
			document.getElementById("myimg").src="srand.do?"+Math.random();
		})
		$("#btnId").click(function(){
			var name=$("#usernameId").val();
			var pwd=$("#userpwdId").val();
			var srand=$("#mysrandId").val();
			if(name==""){
				alert("请输入用户名");
				return;		
			}			
			if(pwd==""){
				alert("请输入密码");
				return;
			}
			if(srand==""){
				alert("请输入验证码");
				return;
			}
			$.ajax({
				type:'post',
				url:'login.do',
				data:{uName:name,uPwd:pwd,uSrand:srand},
				success:function(data){
						if(data=='0'){
							alert('验证码错误');
							$("#mysrandId").val('');
							document.getElementById("myimg").src="srand.do?"+Math.random();
						}
						if(data=='1'){
							alert('登录失败,请检查用户名和密码是否正确');
							$("#userpwdId").val('');
							$("#mysrandId").val('');
							document.getElementById("myimg").src="srand.do?"+Math.random();
						}
						if(data=='2'){
							window.location.href='pages/home.jsp';
						}
					}
				});
		
		})
	})
</script>
<style type="text/css">
	div{
		width:300px;
		height:200px;
		margin:0 auto;
		background-color:linen;
		border:3px solid red;
	}
	div form:nth-child(1){
		text-align:center;
	}
	div form:nth-child(2) {
		opacity: 0.5;
		position:absolute;
	}
</style>
</head>
<body>
	<center><h3><font color="blue">用户登录</font></h3></center>
	<div>
		<form action="login.do" method="post" id="myform">
			<span>用户名:</span>
			<input type="text" name="username" id="usernameId" placeholder="请输入用户名" style="border-radius: 10px"><p>
			&nbsp;&nbsp;&nbsp;<span>密码:</span>
			<input type="password" name="userpwd" id="userpwdId" placeholder="请输入密码" style="border-radius: 10px"><p>
			<span>验证码:</span><input type="text" name="mysrand" id="mysrandId" placeholder="请输入验证码" style="width:80px">
			<a href="javascript:void(0)" id="myA"><img src="srand.do" title="点击刷新验证码" id="myimg" width="80px" height="30px"></a><p>
			<input type="button" value="登录" id="btnId" class="btnstyle"> 
		</form>
		<form action="myRegister.jsp" method="post" id="zcform">
			<input type="submit" value="注册" id="zhuceId" >
		</form> 
		<form action="" method="post" id="pwdform" style="margin-left:205px;opacity: 0.3;">
			<input type="submit" value=">>>忘记密码" id="notpwdId" >
		</form> 
	</div>
</body>
</html>