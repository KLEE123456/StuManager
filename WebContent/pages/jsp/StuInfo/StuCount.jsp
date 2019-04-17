<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path=request.getContextPath(); %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

	<body style="height:600%; margin: 0">
       <div id="container" style="height: 600%"></div>
       <div id="container2" style="height: 600%"></div>
       <div id="container3" style="height: 600%"></div>
		<script type="text/javascript" src="<%=path %>/js/echarts.common.min.js" >
       </script>
       <script type="text/javascript">
	   var clsSumListArr = new Array();
	   var clsSumListArrVal = new Array();
       var clsSumList = JSON.parse('${clsSumList1}');
      
   	   for(var i=0;i<clsSumList.length;i++){
       	   clsSumListArr.push(clsSumList[i].classinfoname);

   			var teml = {
   						name:clsSumList[i].classinfoname,
   						value:clsSumList[i].classinfosum
   					}
   			clsSumListArrVal.push(teml);
          }

var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
var weatherIcons = {
    'Sunny': './data/asset/img/weather/sunny_128.png',
    'Cloudy': './data/asset/img/weather/cloudy_128.png',
    'Showers': './data/asset/img/weather/showers_128.png'
};

option = {
    title: {
        text: '专业班级人数统计情况',
        subtext: '虚构数据',
        left: 'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        // orient: 'vertical',
        // top: 'middle',
        bottom: 10,
        left: 'center',
        data: clsSumListArr
    },
    series : [
        {
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data:clsSumListArrVal,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>
      <script type="text/javascript">
var dom = document.getElementById("container2");
var myChart = echarts.init(dom);
var clsMaleSumListArr = new Array();
var clsMaleSumListArrVal = new Array(); 
var clsSumList = JSON.parse('${clsSumList1}');
var clsMaleSum=JSON.parse('${clsMaleSumList}');
for(var i=0;i<clsSumList.length;i++){
	clsMaleSumListArr.push(clsSumList[i].classinfoname);

   }
for(var i=0;i<clsMaleSum.length;i++){
	
	var teml={
		name:clsSumList[i].classinfoname,
		value:clsMaleSum[i].clsMaleNum
	}
	clsMaleSumListArrVal.push(teml);
}
var app = {};
option = null;
var weatherIcons = {
    'Sunny': './data/asset/img/weather/sunny_128.png',
    'Cloudy': './data/asset/img/weather/cloudy_128.png',
    'Showers': './data/asset/img/weather/showers_128.png'
};

option = {
    title: {
        text: '专业班级男生人数统计情况',
        subtext: '虚构数据',
        left: 'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        // orient: 'vertical',
        // top: 'middle',
        bottom: 10,
        left: 'center',
        data: clsMaleSumListArr
    },
    series : [
        {
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data:clsMaleSumListArrVal,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>
      <script type="text/javascript">
var dom = document.getElementById("container3");
var myChart = echarts.init(dom);
var clsFeMaleSumListArr = new Array();
var clsFeMaleSumListArrVal = new Array(); 
var clsSumList = JSON.parse('${clsSumList1}');
var clsFeMaleSum=JSON.parse('${clsFeMaleSumList}');
for(var i=0;i<clsSumList.length;i++){
	clsFeMaleSumListArr.push(clsSumList[i].classinfoname);

   }
for(var i=0;i<clsFeMaleSum.length;i++){
	var telm={
		name:clsSumList[i].classinfoname,
		value:clsFeMaleSum[i].clsFeMaleNum
	}
	clsFeMaleSumListArrVal.push(telm);
}
var app = {};
option = null;
var weatherIcons = {
    'Sunny': './data/asset/img/weather/sunny_128.png',
    'Cloudy': './data/asset/img/weather/cloudy_128.png',
    'Showers': './data/asset/img/weather/showers_128.png'
};

option = {
    title: {
        text: '专业班级女生数统计情况',
        subtext: '虚构数据',
        left: 'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        // orient: 'vertical',
        // top: 'middle',
        bottom: 10,
        left: 'center',
        data: clsFeMaleSumListArr
    },
    series : [
        {
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data:clsFeMaleSumListArrVal,     
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>
   </body>

</html>