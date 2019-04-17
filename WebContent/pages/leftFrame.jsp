<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    
    <title>办公自动化管理系统</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/Style.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
<!--
body,td,th {
	font-size: 9pt;
}
body {
	background-color: #72A2D2;
}
a:link {
	text-decoration: none;
	color: #000000;
}
a:visited {
	text-decoration: none;
	color: #000000;
}
a:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	text-decoration: none;
	color: #000000;
}
.style1 {
	color: #00FF00;
	font-weight: bold;
}
.style2 {
	color: #FFFF00;
	font-weight: bold;
}
.style3 {color: #99CC66}
.STYLE4 {color: #000000}
.STYLE5 {color: #1A1712}
-->
</style>
<script type="text/javascript" language="javascript">
	function show(d1,m)
	{
		
		if(document.getElementById(d1).style.display=='none')
		{
			document.getElementById(d1).style.display='block';
			var image=document.getElementById(m);
			image.src="../image/bclass2.gif";
			
		}
		else
		{
			document.getElementById(d1).style.display='none';
			var image=document.getElementById(m);
			image.src="../image/bclass1.gif";
			
		}
	}
</script>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body>
    <div>
  <h3 class="style1 STYLE4">学生管理系统：</h3>
</div>
<a href="javascript:onClick=show('1','img1')" ><img src="../image/bclass1.gif" id="img1" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle">个人信息</a>
<div id="1" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="jsp/modifyInfo.jsp" target="bottomFrame" title="我的信息">我的信息</a><br>
&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" alt="" width="19" height="9"><a href="jsp/imageInfo.jsp" target="bottomFrame" title="修改头像">修改头像</a><br>
&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" alt="" width="19" height="9"><a href="jsp/modifyPwd.jsp" target="bottomFrame" title="修改密码">修改密码</a><br>
</div>
<div><a href="javascript:onClick=show('a','aimg2')" ><img src="../image/bclass1.gif" id="aimg2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >院系管理</a></div>
<div id="a" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../Depinfo.newDo?method=List" target="bottomFrame" >院系信息</a><br>
</div>
<div><a href="javascript:onClick=show('b','bimg2')" ><img src="../image/bclass1.gif" id="aimg2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >专业管理</a></div>
<div id="b" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../Spinfo.newDo?method=List" target="bottomFrame" >专业信息</a><br>
</div>
<div><a href="javascript:onClick=show('c','cimg2')" ><img src="../image/bclass1.gif" id="img2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >班级管理</a></div>
<div id="c" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../ClassInfo.newDo?method=List" target="bottomFrame" >班级信息</a><br>
</div>
<div><a href="javascript:onClick=show('d','dimg2')" ><img src="../image/bclass1.gif" id="img2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >学生管理</a></div>
<div id="d" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../StuInfo.newDO?method=List" target="bottomFrame" >学生信息</a><br>
</div>
<div><a href="javascript:onClick=show('e','eimg2')" ><img src="../image/bclass1.gif" id="img2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >教师管理</a></div>
<div id="e" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../TeacherInfo.NewDo?method=List" target="bottomFrame" >教师信息</a><br>
</div>
<div><a href="javascript:onClick=show('f','fimg2')" ><img src="../image/bclass1.gif" id="img2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >教师类别管理</a></div>
<div id="f" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../teachType.do?method=List" target="bottomFrame" >教师类别信息</a><br>
</div>
<div><a href="javascript:onClick=show('j','jimg2')" ><img src="../image/bclass1.gif" id="img2" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >课程类别管理</a></div>
<div id="j" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../CourseType.NewDO?method=List" target="bottomFrame" >课程类别信息</a><br>
</div>
</div>
<div><a href="javascript:onClick=show('3','img3')"><img src="../image/bclass1.gif" id="img3" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle">成绩管理</a></div>
<div id="3" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="" target="bottomFrame" title="成绩信息">成绩信息</a><br>
</div>
<div><a href="javascript:onClick=show('4','img4')"><img src="../image/bclass1.gif" id="img4" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle" >课程管理</a></div>
<div id="4" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../courseInfo.newDo?method=List" target="bottomFrame" title="消息管理">课程信息</a><br>
</div>
<div><a href="javascript:onClick=show('5','img5')"><img src="../image/bclass1.gif" id="img5" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle">系统管理</a></div>

<div><a href="javascript:onClick=show('6','img6')"><img src="../image/bclass1.gif" id="img6" border="0" align="absmiddle"/><img src="../image/f_new.gif" border="0" align="absmiddle">报表管理</a></div>
<div id="6" style="display:none">&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../spcount.do" target="bottomFrame" title="院系专业统计">院系专业统计</a><br>
&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../clsCount.do" target="bottomFrame" title="专业班级统计">专业班级统计</a><br>
&nbsp;&nbsp;&nbsp;<img src="../image/z-top.gif" align="absmiddle"><img src="../image/f_hot.gif" width="19" height="9"><a href="../stuCount.newdo" target="bottomFrame" title="班级人员统计">班级人员统计</a><br>	
</div>
<div><a href="../login.do" target="_parent"><img src="../image/f_new.gif" border="0" align="absmiddle">退出系统</a></div>
  </body>
</html>
