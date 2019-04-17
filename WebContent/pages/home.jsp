<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    
    <title>学生管理系统--首页</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<frameset rows="66,374" cols="*" frameborder="no" id="top">
		<frame src="topFrame.jsp" scrolling="no" name="topframe">
  <frameset rows="*" cols="190,827"  bordercolor="#FFA838" id="bottom">
    <frameset rows="*" cols="*,12">
      <frame src="leftFrame.jsp" scrolling="no" noresize name="leftframe">
      <frame src="ctrl.jsp" scrolling="no">
    </frameset>
    <frameset rows="*,11%" cols="*">
    	 <frame src="welcome.jsp" scrolling="yes" bordercolor="#FFA838" name="bottomFrame">
		<frame src="downFrame.jsp" name="mainName"  scrolling="no">
	</frameset>
  </frameset>
</frameset><noframes></noframes>
</html>
