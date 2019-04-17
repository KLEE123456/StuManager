<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>办公自动化管理系统</title>
	<meta content="120" http-equiv="refresh" />
	<link href="../css/Style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
body {
	background-color: #72A2D2;
}
.STYLE3 {
	font-size: 24pt;
	font-weight: bold;
}
body,td,th {
	font-size: 9pt;
}
-->
</style>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
  
  <body>
    <table width="1178" height="27" border="1" bgcolor="#72A2D2" bordercolor="#72A2D2" bordercolordark="#72A2D2" bordercolorlight="#72A2D2">
  <tr>
    <td width="205"><img src="../image/logo.jpg" width="148" height="55"></td>
    <td width="239"><span class="STYLE3">学生管理系统</span></td>
    <td width="712" align="left" valign="middle"><table width="59%"  >
      <tr>
        <td width="7%" align="right"><img src="../image/home.gif" width="16" height="16" border="0" class="buttonCss"></td>
        <td width="9%" align="left">主页</td>
        <td width="5%" align="right"><a href="../userInfo.do?oper=dogoLogin" target="_parent"> <img src="../image/index.gif" width="17" height="16" border="0" class="buttonCss"></a></td>
        <td width="14%"><a href="../login.do" target="_parent">重新登录</a></td>
        <td width="6%" align="right"><img src="../image/userkey.gif" width="16" height="16" border="0" class="buttonCss"></td>
        <td width="14%"><a href="jsp/modifyPwd.jsp" target="bottomFrame">修改密码</a></td>
       
        </tr>
    </table>
      <div align="center"></div>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>欢迎</strong>: ${admins.truename }
      <input name="imageField4" type="image" src="../image/smile.gif" width="15" height="15" border="0" align="absmiddle"> 
    今天是:
    	<script type="text/javascript">
    		var date=new Date();
    		var arr=new Array("星期天","星期一","星期二","星期三","星期四","星期五","星期六");
    		document.write(date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日 "+arr[date.getDay()]);
    	</script>
    </p>
    <p></p>
    </td>
  </tr>
</table>
  </body>
</html>
