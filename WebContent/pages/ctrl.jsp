<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <html:base />
    
    <title>学生管理系统</title>
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	background-color: #72A2D2;
}
-->
</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/Style.css" rel="stylesheet" type="text/css"/>
	<script language="javascript"  type="text/javascript">
	function onbur()
	{
		var image=document.getElementById("imag");
		image.src="../image/toleft2.gif";
	}
	function onfa()
	{
		var image=document.getElementById("imag");
		image.src="../image/toright.gif";
	}
</script>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body>
    <p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<a href="#" onClick="javascript:if(hidden.id.value=='1'){parent.parent.bottom.cols='12,*';hidden.id.value='0';  }else{parent.parent.bottom.cols='220,*';hidden.id.value='1';}" onBlur="javascript:onbur();" ><img id="imag" src="../image/toleft.gif" width="9" height="63" border="0" onClick="onfa()"></a>
<form name="hidden"><input name="id" value="1" type="hidden"></form>
  </body>
</html>
