<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CAll Center Admin</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
	<img src="dist/img/header.jpg" width="100%">
</header>
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<div class="panel panel-default panel-custom">
                	<div class="panel-heading">
                    	<h4 class="panel-title">
                        	CALL CENTER AGENT DASHBOARD
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-6">
                                <section>
                                    <table class="table table-bordered">
                                        <tr>
                                            <td rowspan="7">
                                                <div id="donutchart" style="width:200px;height:250px"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td><h4 class="text-bold">ALLOCATED CALLS - 1000</h4></td>
                                        </tr>
                                        <tr>
                                        	<td class="text-dialled"><span class="font-30">800</span> Dialled Calls</td>
                                        </tr>
                                        <tr>
                                        	<td class="pad_0">
                                            	<table class="table table-bordered m_0">
                                                	<tr>
                                                    	<td class="text-dialled">700 Call<br/>Answered</td>
                                                        <td class="text-switchoff">100 Call<br/>Switched Off / Userbusy</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<span class="text-custom">600 - Members Interested</span>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<p class="text-not-interested">80 - Currently not interested</p>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<p class="text-totally-not">20 - Totally not interested</p>
                                            </td>
                                        </tr>
                                    </table>
                                </section>
                            </div>
                            <div class="col-md-6">
                                <section>
                                    <div class="panel panel-default panel-custom">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Call Back Day Wise Details</h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<table class="table table-bordered m_0">
                                            	<tr>
                                                	<td colspan="2" class="text-center">TALK TO YOU LATER</td>
                                                    <td colspan="2" class="text-center">CONFIRM LATER</td>
                                                </tr>
                                                <tr>
                                                	<td>Date / Day</td>
                                                    <td>Time</td>
                                                    <td>Date / Day</td>
                                                    <td>Time</td>
                                                </tr>
                                                <tr>
                                                	<td>Today</td>
                                                    <td>
                                                    	<p class="m_0">Morning<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Afternoon<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Evening<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Night<span class="pull-right text-underline">-0</span></p>
                                                    </td>
                                                    <td>Today</td>
                                                    <td>
                                                    	<p class="m_0">Morning<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Afternoon<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Evening<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Night<span class="pull-right text-underline">-0</span></p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                	<td>Tomorrow</td>
                                                    <td>
                                                    	<p class="m_0">Morning<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Afternoon<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Evening<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Night<span class="pull-right text-underline">-0</span></p>
                                                    </td>
                                                    <td>Tomorrow</td>
                                                    <td>
                                                    	<p class="m_0">Morning<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Afternoon<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Evening<span class="pull-right text-underline">-0</span></p>
                                                        <p class="m_0">Night<span class="pull-right text-underline">-0</span></p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </section>
                            </div>
                            <section>
                            	<div class="col-md-12">
                                	<div class="panel panel-default panel-custom">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Schedule Confirmation</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="scheduleWiseCountDiv">
                                        	
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <section>
                            	<div class="col-md-12">
                                	<div class="panel panel-default panel-custom">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Batch Date Confirmation</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="batchWiseCountDiv">
                                        	
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                 </div>
             </div>
        </div>
    </div>
</main>

<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">
$(".table-scroll").mCustomScrollbar({
	setHeight:850,
	theme:"minimal-dark"
});
</script>
<script type="text/javascript">
$(function () {
	Highcharts.setOptions({
        colors: ['#40b5bf', '#999967', '#089bf8', '#ac69ae' , '#cccccc']
    });
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 120,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
            data: [
                ['Interested', 157],
                ['Not Interested', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
});

function getScheduleCallStatusCount()
{
		var jObj={
		callPurposeId : 2,
		
		task:"scheduleWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getScheduleCallStatusCountAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			  buildScheduleWiseCount(result);
		   });	
} 
function buildScheduleWiseCount(result) {
var str='';
str+='<table class="table table-bordered m_0">';
str+='<tr>';
str+='<td>Program</td>';
str+=' <td>Traing Camp </td>';
str+=' <td>Schedule</td>';
str+='<td>Allocated</td>';
str+='<td>Dialled</td>';
str+='<td>UN Dialled</td>';
str+='<td>Answered</td>';
str+='<td class="pad_5 font-12">Switch off /<br/>User Busy / Not Ans</td>';
str+='<td>Call Back/TCB</td>';
str+='<td class="pad_5 font-12">Interested</td>';
str+='<td class="pad_5 font-12">Later</td>';
str+='<td class="pad_5 font-12">Not Interested</td>';
str+='</tr>';
for(var i in result) // program
{
str+=' <tr>';
var rowSpanCnt = result[i].subList.length;
str+='<td rowspan="'+rowSpanCnt+'">'+result[i].name+'</td>';
for(var j=0;j< result[i].subList.length;j++) //camp
{
var rowSpanCnt1 = result[i].subList.length;
str+=' <td>'+result[i].subList[j].name+'</td>';
for(var k=0;k<result[i].subList[j].subList.length;k++)//Schedule
{
var answered  = 0;
var userbusy  = 0;
var dialed = 0;
var allocated = 0;
var undialed = 0;
str+=' <td>'+result[i].subList[j].subList[k].name+'</td>';
for(var l=0;l<result[i].subList[j].subList[k].subList.length;l++) //callStatus
{
if(result[i].subList[j].subList[k].subList[l].name == "Call Answered")
 answered = result[i].subList[j].subList[k].subList[l].count;
 else
userbusy = userbusy + result[i].subList[j].subList[k].subList[l].count;
}
dialed = answered + userbusy;
allocated = result[i].subList[j].subList[k].total;
undialed = allocated - dialed;
str+=' <td>'+allocated+'</td>';
str+=' <td>'+dialed+'</td>';
str+='<td>'+undialed+'</td>';
str+='<td>'+answered+'</td>';
str+='<td>'+userbusy+'</td>';

var callBack  = 0;
var interested  = 0;
var later = 0;
var notInterested = 0;
for(var m=0;m<result[i].subList[j].subList[k].scheduleStatusList.length;m++) // invitee Status
{
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Call Back")
callBack = result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Attending")
interested = result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Not Interested")
notInterested = result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Not Now")
later = result[i].subList[j].subList[k].scheduleStatusList[m].count;
}
str+=' <td>'+callBack+'</td>';
str+=' <td>'+interested+'</td>';
str+='<td>'+notInterested+'</td>';
str+='<td>'+later+'</td>';
str+='</tr>';

}
}

}
str+=' </table>';
$("#scheduleWiseCountDiv").html(str);
}
function getBatchWiseCallStatusCount()
{
		var jObj={
		callPurposeId : 2,
		
		task:"batchWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getBatchCallStatusCountAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			  buildBatchWiseCount(result);
		   });	
} 

function buildBatchWiseCount(result) {
var str='';
str+='<table class="table table-bordered m_0">';
str+='<tr>';
str+='<td>Program</td>';
str+=' <td>Traing Camp </td>';
str+=' <td>Schedule</td>';
str+=' <td>Batch</td>';
str+='<td>Allocated</td>';
str+='<td>Dialled</td>';
str+='<td>UN Dialled</td>';
str+='<td class="pad_5 font-12">Switch off /<br/>User Busy / Not Ans</td>';
str+='<td>Call Back/TCB</td>';
str+='<td class="pad_5 font-12">Interested</td>';
str+='<td class="pad_5 font-12">Later</td>';
str+='<td class="pad_5 font-12">Not Interested</td>';
str+='</tr>';
for(var i in result) // program
{
str+=' <tr>';
var rowSpanCnt = result[i].subList.length;
str+='<td rowspan="'+rowSpanCnt+'">'+result[i].name+'</td>';
for(var j=0;j< result[i].subList.length;j++) //camp
{
var rowSpanCnt1 = result[i].subList.length;
str+=' <td>'+result[i].subList[j].name+'</td>';
for(var k=0;k<result[i].subList[j].subList.length;k++)//Schedule
{
str+=' <td>'+result[i].subList[j].subList[k].name+'</td>';
for(var p=0;p<result[i].subList[j].subList[k].subList.length;p++) //batch
{
var answered  = 0;
var userbusy  = 0;
var dialed = 0;
var allocated = 0;
var undialed = 0;
str+=' <td>'+result[i].subList[j].subList[k].subList[p].name+'</td>';
for(var l=0;l<result[i].subList[j].subList[k].subList[p].subList.length;l++) //callStatus
{
if(result[i].subList[j].subList[k].subList[p].subList[l].name == "Call Answered")
 answered = result[i].subList[j].subList[k].subList[p].subList[l].count;
 else
userbusy = userbusy + result[i].subList[j].subList[k].subList[p].subList[l].count;
}
dialed = answered + userbusy;
allocated = result[i].subList[j].subList[k].subList[p].total;
undialed = allocated - dialed;
str+=' <td>'+allocated+'</td>';
str+=' <td>'+dialed+'</td>';
str+='<td>'+undialed+'</td>';
str+='<td>'+userbusy+'</td>';

var callBack  = 0;
var interested  = 0;
var later = 0;
var notInterested = 0;
for(var m=0;m<result[i].subList[j].subList[k].subList[p].scheduleStatusList.length;m++) // invitee Status
{
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Call Back")
callBack = result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Interested")
interested = result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Not Interested")
notInterested = result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Interested in next Time")
later = result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
}
str+=' <td>'+callBack+'</td>';
str+=' <td>'+interested+'</td>';
str+='<td>'+notInterested+'</td>';
str+='<td>'+later+'</td>';
str+='</tr>';

}
}
}
}
str+=' </table>';
$("#batchWiseCountDiv").html(str);
}
</script>
<script>
getScheduleCallStatusCount();
getBatchWiseCallStatusCount();
</script>
</body>
</html>