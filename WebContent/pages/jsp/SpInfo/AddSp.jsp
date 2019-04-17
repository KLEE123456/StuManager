<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@page isELIgnored="false" %>
<%@taglib uri="../../../WEB-INF/c.tld" prefix="c"%>
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
		var depinfoid=$("#select").val();
		if(depinfoid==""){
			alert("请选择院系");
			return;
		}
		var spilinfocode=$("#spilinfocode").val();
		if(spilinfocode==""){
			alert("请输入专业代码");
			return;
		}
		if(flag==false){
			alert("请进行专业代码检测");
			return;
		}
		var spilinfoname=$("#spilinfoname").val();
		if(spilinfoname==""){
			alert("请输入专业名称!");
			return;
		}
		var spilinfoaim=$("#spilinfoaim").val();
		if(spilinfoaim==""){
			alert("请输入专业培养目标!");
			return;
		}
		var spilinfoprodire=$("#spilinfoprodire").val();
		if(spilinfoprodire==""){
			alert("请输入专业的职业面向!");
			return;
		}
		$("#info").submit();
	})
})
function cKSpCode(){
		var spilinfocode=$("#spilinfocode").val();
		if(spilinfocode==""){
			alert("请输入专业代码");
			return;
		}
		$.ajax({
			type:'get',
			url:'<%=path %>/Spinfo.newDo?method=ajaxCKCode',
			data:{spilcode:spilinfocode},
			success:function(data){
				if(data=='1'){
					alert('专业代码检测成功,可以进行添加');
					flag=true;
				}
				if(data=='0'){
					alert('专业代码已存在，请进行更换');
					$("#spilinfocode").val('');
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">专业管理&gt;&gt;新增专业</span>
</div>
<form id="info" name="info" action="<%=path %>/Spinfo.newDo" method="post" > 
<input type="hidden" name="method" value="Add" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增专业信息</TD>
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
          <td align="right" style="padding-right:5px"><font color="red">*</font>院系名称：</td>
          <td colspan="3">
       <select name="select" value="" id="select" >
      			<option value="">请选择院系名称</option>
      			<c:forEach items="${Deplist }" var="dep" varStatus="status">
      			
   				<option  value="${dep.depinfoId }" >${dep.depinfoname}</option>
      			
      			</c:forEach>
      </select>
          </td> 
        </tr>  
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>专业代码：</td>
          <td colspan="3">
            <input type="text" id="spilinfocode" name="spilinfocode" value="${newdep.depinfoname }"/>
            <input type="button" id="CkSpCodeBtn" name="CkSpCodeBtn" value="专业代码检测" onclick="cKSpCode()" />
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>专业名称：</td>
          <td colspan="3">
            <input type="text" id="spilinfoname" name="spilinfoname" value="${newdep.depinfoname }"/>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>培养目标：</td>
          <td colspan="3">
            <input type="text" id="spilinfoaim" name="spilinfoaim" value="${newdep.depinfoPreOfTech }"/>
          </td> 
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>职业面向：</td>
          <td colspan="3">
            <input type="text" id="spilinfoprodire" name="spilinfoprodire" value="${newdep.depinfoAssTeach }"/>
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