<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Call Center Agent</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
table th
{
	padding:6px !important;
	font-size:13px !important
}
#agentsDiv{margin-bottom: 5px;margin-top: 5px;}
header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
footer
{
	background-color:#5c2d25;
	padding:30px;
	color:#fff
}

</style>
</head>
<body>
<!--<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<!--<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;">TRAINING PROGRAM</p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					   <!--<li><a href="dashBoardAction.action"><span> DASHBOARD</span></a> </li>
					   <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN')}">
							<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
						</c:if>
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN')}">
							<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
							<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
							<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						</c:if>
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN')}">
							<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
						</c:if>

						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT')}">
							<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						</c:if>
					    <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS')}">
							<li><a href="committeeDashBoardAction.action"><span>COMMITTEES DASHBOARD</span></a> </li>
							<li><a href="meetingList.action"><span>Party Meeting ATR & MOM</span></a> </li>
					    </c:if>
						
							
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>-->
<main>
<section style="margin-top:40px;">
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<div class="panel panel-default panel-custom">
                	<div class="panel-heading">
                    	<h4 class="panel-title">
                        	CALL CENTER AGENT DASHBOARD
                        </h4>
                    </div>
				 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN') || fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE')}">
					<div id="agentsDiv" class="col-md-4 col-md-offset-8">
					  <select id="agentId" onchange="getSelectedAgentDetails()" class="form-control">
					  <!--<option value="0">Select Agent</option> -->
					 </select>
					</div>
				 </c:if>
                    <div class="panel-body">
					 <div id="agentCallDetailsDiv">
						<!--<table class="table table-bordered">
							<tr>
								<td rowspan="2"><div id="donutchart" style="width:200px;height:150px"></div></td>
								<td>
									<h4>ALLOCATED CALLS - 700</h4>
								</td>
							</tr>
							<tr>
								<td class="pad_0">
									<table class="table table-bordered m_0">
										<tr>
											<td></td>
											<td>Calls</td>
											<td class="text-dialled">Not Dialled</td>
											<td class="text-dialled">Dialed</td>
											<td colspan="2">Call Back</td>
											<td class="text-custom">Interested</td>
											<td class="text-not-interested"> Later</td>
											<td class="text-totally-not">Not Interested</td>
										</tr>
										<tr>
											<td>Assigned to Agents</td>
											<td>52</td>
											<td class="text-dialled">04</td>
											<td class="text-dialled">09</td>
											<td>Talk to you later/TCB</td>
											<td>Confirm Later/TCB</td>
											<td class="text-custom">01</td>
											<td class="text-not-interested">05</td>
											<td class="text-totally-not">04</td>
										</tr>
										<tr>
											<td>Calendar Schedule</td>
											<td>52</td>
											<td class="text-dialled">04</td>
											<td class="text-dialled">09</td>
											<td>12/02</td>
											<td>10/01</td>
											<td class="text-custom">01</td>
											<td class="text-not-interested">05</td>
											<td class="text-totally-not">04</td>
										</tr>
										<tr>
											<td>Batch Confirmation</td>
											<td>52</td>
											<td class="text-dialled">04</td>
											<td class="text-dialled">09</td>
											<td>13/02</td>
											<td>06/01</td>
											<td class="text-custom">01</td>
											<td class="text-not-interested">05</td>
											<td class="text-totally-not">04</td>
										</tr>
									</table>
								</td>
							</tr>
						</table> -->
					</div>
                        <div class="row">
                            <div class="col-md-6">
                                <section>
								<div id="callStatusCountDiv"></div>
                                </section>
                            </div>
                            <div class="col-md-6">
                                <!--<section>
                                    <div class="panel panel-default panel-custom">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Call Back Day Wise Details</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="callBackDayDiv">
                                        	<table class="table table-bordered m_0">
                                            	<tr>
                                                	<td></td>
                                                    <td><p class="m_top20 m_bottom20">TALK TO YOU LATER/<span class="font-10">TCB</span></p></td>
                                                    <td><p class="m_top20 m_bottom20">CONFIRM LATER/<span class="font-10">TCB</span></p></td>
                                                </tr>
                                                <tr>
                                                	<td><p class="m_top20 m_bottom20">Schedule Confirmation</p></td>
                                                    <td><p class="m_top20 m_bottom20">150/10</p></td>
                                                    <td><p class="m_top20 m_bottom20">150/10</p></td>
                                                </tr>
                                                <tr>
                                                	<td><p class="m_top20 m_bottom20">Batch Confirmation</p></td>
                                                    <td><p class="m_top20 m_bottom20">150/10</p></td>
                                                    <td><p class="m_top20 m_bottom20">150/10</p></td>
                                                </tr>
                                            </table> -->
                                        	<!--<table class="table table-bordered m_0">
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
                                </section>-->
                            </div>
                            <section>
                            	<div class="col-md-12" id="ScheduleConforMainDiv">
                                	<div class="panel panel-default panel-custom">
                                    	<div class="panel-heading pad_5 pad_bottom0">
                                            <ul class="nav nav-tabs tab-list-sch" role="tablist">
                                                <li class="active"><a href="#scheduled" class="text-bold" data-toggle="tab">SCHEDULE CONFIRMATION</a></li>
                                                <!--<li><a href="#running" class="text-bold" data-toggle="tab">RUNNING</a></li>
                                                <li><a href="#completed" class="text-bold" data-toggle="tab">COMPLETED</a></li>
                                                <li><a href="#cancelled" class="text-bold" data-toggle="tab">CANCELLED</a></li> -->
                                            </ul>
                                        </div>
                                        <div class="panel-body pad_0">
                                            <div>
                                              <!-- Tab panes -->
                                              <div class="tab-content">
                                                <div role="tabpanel" class="tab-pane active" id="scheduled">
                                                  
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="running">
                                                  
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="completed">
                                                   
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="cancelled">
                                                   
                                                </div>
                                              </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <section>
                            	<div class="col-md-12">
                                	<div class="panel panel-default panel-custom">
                                    	<div class="panel-heading pad_5 pad_bottom0">
                                            <ul class="nav nav-tabs tab-list-sch" role="tablist">
                                                <li class="active"><a href="#bacthdate" class="text-bold" data-toggle="tab">BATCH  CONFIRMATION</a></li>
                                                <!--<li><a href="#running1" class="text-bold" data-toggle="tab" onclick="getMembersCountByBatchStatus('Progress','running1')">RUNNING</a></li>
                                                <li><a href="#completed1" class="text-bold" data-toggle="tab" onclick="getMembersCountByBatchStatus('Completed','completed1')">COMPLETED</a></li> -->
                                            </ul>
                                        </div>
                                        <div class="panel-body pad_0">
                                            <div>
                                              <!-- Tab panes -->
                                              <div class="tab-content">
                                                <div role="tabpanel" class="tab-pane active" id="bacthdate">
                                                 </div>
                                                <div role="tabpanel" class="tab-pane" id="running1">
												 </div>
                                                <div role="tabpanel" class="tab-pane" id="completed1">
                                                 </div>
                                              </div>
                                            </div>
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
  </section>
</main>

<!--<footer>
		<p class="text-center">All &copy; 2015 Telugu Desam Party</p>
</footer>-->
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

function getScheduleCallStatusCount(campCallerId)
{
	$("#scheduled").html("<img src='images/icons/search.gif'>");
		var jObj={
		callPurposeId : 1,
		campCallerId  : campCallerId,
		task:"scheduleWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getScheduleCallStatusCountAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 
			   $("#scheduled").html("");
            if(result != null && result.length > 0)			  
			  buildScheduleWiseCount(result,jObj);
		  else
			 $("#scheduled").html("No Data Available");
		   });	
} 

var scheduleCampCallerId = 0;

function buildScheduleWiseCount(result,jObj) {
  scheduleCampCallerId = result[0].campCallerId;
  
var str='';
str+='<table class="table table-bordered m_0">';
str+='<tr>';
str+='<td>Program</td>';
str+=' <td>Training Camp </td>';
str+=' <td>Schedule</td>';
str+='<td>Allocated</td>';
str+='<td>Answered</td>';
str+='<td>Dialed/Not-Dialled</td>';
str+='<td class="pad_5 font-12">Interested</td>';
str+='<td class="pad_5 font-12">Not Interested</td>';
str+='<td class="pad_5 font-12">Switch off /<br/>User Busy</td>';
str+='<td>Call Back/TCB</td>';
str+='<td class="pad_5 font-12">Later/Pending</td>';
str+='<td class="pad_5 font-12">Wrong Mobile No</td>';
str+='</tr>';
for(var i in result) // program
{
str+=' <tr>';
var rowSpanCnt = result[i].subList.length;
str+='<td rowspan="'+result[i].spanCnt+'">'+result[i].name+'</td>';
for(var j=0;j< result[i].subList.length;j++) //camp
{
	var scheduleLength; 
	if(result[i].subList[j].subList !=null){
		scheduleLength=result[i].subList[j].subList.length;
	}
	
str+=' <td rowspan="'+scheduleLength+'">'+result[i].subList[j].name+'</td>';
for(var k=0;k<result[i].subList[j].subList.length;k++)//Schedule
{
var answered  = 0;
var userbusy  = 0;
var dialed = 0;
var allocated = 0;
var undialed = 0;
var callBack  = 0;
var interested  = 0;
var confirm  = 0;
var later = 0;
var notInterested = 0;
var todaycallBack =0;
var pending = 0;
var switchOff  = 0;
var other =0;
var wrong =0;
var invalid = 0;
str+=' <td>'+result[i].subList[j].subList[k].name+'</td>';
for(var l=0;l<result[i].subList[j].subList[k].subList.length;l++) //callStatus
{
if(result[i].subList[j].subList[k].subList[l].name == "Call Answered")
 answered = answered+result[i].subList[j].subList[k].subList[l].count;
 else if(result[i].subList[j].subList[k].subList[l].name == "SwitchOff")
switchOff = switchOff + result[i].subList[j].subList[k].subList[l].count;
 else if(result[i].subList[j].subList[k].subList[l].name == "User Busy")
userbusy = userbusy + result[i].subList[j].subList[k].subList[l].count;
else
other = other + result[i].subList[j].subList[k].subList[l].count;
}
dialed = answered + userbusy + switchOff +other;
allocated = result[i].subList[j].subList[k].total;
undialed = allocated - dialed;

for(var m=0;m<result[i].subList[j].subList[k].scheduleStatusList.length;m++) // invitee Status
{
if((result[i].subList[j].subList[k].scheduleStatusList[m].id == 6 && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0 )|| (result[i].subList[j].subList[k].scheduleStatusList[m].id == 7 && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0))
{
callBack = callBack + result[i].subList[j].subList[k].scheduleStatusList[m].count;
todaycallBack = todaycallBack + result[i].subList[j].subList[k].scheduleStatusList[m].todayCnt;
}
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Interested")
interested = interested+result[i].subList[j].subList[k].scheduleStatusList[m].count ;
if(result[i].subList[j].subList[k].scheduleStatusList[m].batchId > 0 && result[i].subList[j].subList[k].scheduleStatusList[m].name != "Interested")
confirm = confirm + result[i].subList[j].subList[k].scheduleStatusList[m].count ;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Not Interested" && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0)
notInterested = notInterested+result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Later" && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0)
later = later+result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Pending" && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0)
pending = pending + result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Wrong Mobile No" && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0)
wrong = wrong + result[i].subList[j].subList[k].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].scheduleStatusList[m].name == "Invalid Mobile No" && result[i].subList[j].subList[k].scheduleStatusList[m].batchId == 0)
invalid = invalid + result[i].subList[j].subList[k].scheduleStatusList[m].count;
}
if(allocated > 0)
str+=' <td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'allocated\',\'callStatus\',\'\');">'+allocated+'</a></td>';
else
str+=' <td>'+allocated+'</td>';
if(answered > 0)
str+='<td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'answered\',\'callStatus\',\'\');">'+answered+'</a></td>';
else
str+='<td>'+answered+'</td>';
if(dialed > 0 || undialed > 0)
{
str+=' <td>';
if(dialed > 0) 
{
str+='<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'dialed\',\'callStatus\',\'\');">'+dialed+'</a>';
}
else
str+=''+dialed+'';
if(undialed > 0)
{
str+='/<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'undialed\',\'callStatus\',\'\');">'+undialed+'</a>';
}
else
str+='/'+undialed+'';
str+='</td>';
}
else
{
str+='<td>'+dialed+'/'+undialed+'</td>';
}
if(interested > 0)
str+=' <td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'interested\',\'scheduleCallStatus\',\'\');">'+interested+'</a></td>';
else
str+=' <td>'+interested+'</td>';
if(notInterested > 0)
str+='<td><a style="cursor:pointer;" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'notInterested\',\'scheduleCallStatus\',\'\');">'+notInterested+'</a></td>';
else
str+='<td>'+notInterested+'</td>';


if(switchOff > 0 || userbusy > 0)
{
str+='<td>';
if(switchOff > 0)
{
str+='<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'Switchoff\',\'callStatus\',\'\');">'+switchOff+'</a>';
}
else
{
str+='0';
}
if(userbusy > 0)
{
str+='/<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'busy\',\'callStatus\',\'\');">'+userbusy+'</a>';
}
else{
str+='/0';
}
str+='</td>';
}
else
str+='<td>'+userbusy+'</td>';


if(callBack > 0)
{
   //str+=' <td>'+callBack+'/'+todaycallBack+'</td>';
   str += '<td>';
   str += '<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'callback\',\'scheduleCallStatus\',\'\');">'+callBack+'</a>/';
   if(todaycallBack > 0)
	 str += '<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'callback\',\'scheduleCallStatus\',\'today\');">'+todaycallBack+'</a>';
   else
	 str += ''+todaycallBack+'';
   str += '</td>';
}
else
str+=' <td>'+callBack+'</td>';
if(later > 0 || pending > 0)
{
str+='<td>';
if(later > 0)
{
str+='<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'later\',\'scheduleCallStatus\',\'\');">'+later+'</a>';
}
else
{
str+='0';
}
if(pending > 0)
{
str+='/<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'Pending\',\'scheduleCallStatus\',\'\');">'+pending+'</a>';
}
else
{
str+='/0';
}
str+='</td>';
}
else
str+='<td>'+later+'/'+pending+'</td>';

str+='<td>';
if(wrong > 0)
{
str+='<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgent(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'Wrong Mobile No\',\'scheduleCallStatus\',\'\');">'+wrong+'</a>';
}
else
{
str+='0';
}
str+='</td>';
str+='</tr>';

}
}

}
str+=' </table>';
$("#scheduled").html(str);
}
function getBatchWiseCallStatusCount(campCallerId)
{
	$("#bacthdate").html("<img src='images/icons/search.gif'>");
		var jObj={
		callPurposeId : 2,
		campCallerId  : campCallerId,
		task:"batchWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getBatchCallStatusCountAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){
				  $("#bacthdate").html("");
            if(result != null && result.length > 0)				  
			  buildBatchWiseCount(result,jObj);
		  else
		   $("#bacthdate").html("No Data Available.");
		   });	
} 

var batchCampCallerId = 0;
function buildBatchWiseCount(result,jObj) {
batchCampCallerId = result[0].campCallerId;
var str='';
str+='<table class="table table-bordered m_0">';
str+='<tr>';
str+='<td>Program</td>';
str+=' <td>Training Camp </td>';
str+=' <td>Schedule</td>';
str+=' <td>Batch</td>';
str+='<td class="pad_5 font-12">Allocated</td>';
str+='<td>Answered</td>';
str+='<td>Dialled/Not-Dialled</td>';
str+='<td class="pad_5 font-12">Confirmed</td>';
str+='<td class="pad_5 font-12">Not Interested</td>';
//str+='<td class="pad_5 font-12">Interested</td>';
//str+='<td class="pad_5 font-12">Interested</td>';
//str+='<td>Allocated</td>';

str+='<td class="pad_5 font-12">Switch off /<br/>User Busy</td>';
str+='<td>Call Back/TCB</td>';
str+='<td class="pad_5 font-12">Later/Pending</td>';
str+='</tr>';
for(var i in result) // program
{
str+=' <tr>';
var rowSpanCnt = result[i].subList.length;
str+='<td rowspan="'+result[i].spanCnt+'">'+result[i].name+'</td>';
for(var j=0;j< result[i].subList.length;j++) //camp
{
str+=' <td rowspan="'+result[i].subList[j].spanCnt+'">'+result[i].subList[j].name+'</td>';
for(var k=0;k<result[i].subList[j].subList.length;k++)//Schedule
{
str+=' <td rowspan="'+result[i].subList[j].subList[k].spanCnt+'">'+result[i].subList[j].subList[k].name+'</td>';
for(var p=0;p<result[i].subList[j].subList[k].subList.length;p++) //batch
{
var answered  = 0;
var userbusy  = 0;
var dialed = 0;
var allocated = 0;
var undialed = 0;
var callBack  = 0;
var interested  = 0;
var later = 0;
var notInterested = 0;
var accepted = 0;
var todaycallBack  = 0;
var pending = 0;
var switchOff  = 0;
var other =0;
str+=' <td>'+result[i].subList[j].subList[k].subList[p].name+'</td>';
for(var l=0;l<result[i].subList[j].subList[k].subList[p].subList.length;l++) //callStatus
{
	if(result[i].subList[j].subList[k].subList[p].subList[l].name == "Call Answered")
		answered = answered+result[i].subList[j].subList[k].subList[p].subList[l].count;
	else if(result[i].subList[j].subList[k].subList[p].subList[l].name == "SwitchOff")
		switchOff = switchOff + result[i].subList[j].subList[k].subList[p].subList[l].count;
		else if(result[i].subList[j].subList[k].subList[p].subList[l].name == "User Busy")
		userbusy = userbusy + result[i].subList[j].subList[k].subList[p].subList[l].count;
		else
		other = other + result[i].subList[j].subList[k].subList[p].subList[l].count;
}
dialed = answered + switchOff +other; ;
allocated = result[i].subList[j].subList[k].subList[p].total;
undialed = allocated - dialed;

//str+=' <td>'+allocated+'</td>';

for(var m=0;m<result[i].subList[j].subList[k].subList[p].scheduleStatusList.length;m++) // invitee Status
{
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].id == 6 || result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].id == 7)
{
callBack = callBack + result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
todaycallBack = todaycallBack + result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].todayCnt;
}
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Interested")
interested = interested+result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Not Interested")
notInterested = notInterested+result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Confirmed")
accepted = accepted+result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Later")
later = later+result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
if(result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].name == "Pending")
pending = pending + result[i].subList[j].subList[k].subList[p].scheduleStatusList[m].count;
}
if(allocated > 0)
str+=' <td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'allocated\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+allocated+'</a></td>';
else
str+=' <td>'+allocated+'</td>';
if(answered > 0)
str+='<td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'answered\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+answered+'</a></td>';
else
str+='<td>'+answered+'</td>';

if(dialed > 0 || undialed > 0)
{
str+='<td>';
if(dialed > 0) 
{
str+=' <a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'dialed\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+dialed+'</a>';
}
else{
str+=''+dialed+'';
}
/*if(answered > 0)
str+='<td><a style="cursor:pointer;" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'answered\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+answered+'</a></td>';
else
str+='<td>'+answered+'</td>';*/
/*
if(interested > 0)
str+=' <td><a style="cursor:pointer;" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'interested\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'\');">'+interested+'</a></td>';
else
str+=' <td>'+interested+'</td>';

if(accepted > 0)
str+='<td><a style="cursor:pointer;" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'confirmed\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'\');">'+accepted+'</a></td>';
else*/
if(undialed > 0){
str+='/<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'undialed\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+undialed+'</a>';
}
else
{
str+='/'+undialed+'';
}
str+='</td>';
}
else{
str+='<td>'+dialed+'/'+undialed+'</td>';
}
str+='<td>'+accepted+'</td>';

if(notInterested > 0)
str+='<td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'notInterested\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'\');">'+notInterested+'</a></td>';
else
str+='<td>'+notInterested+'</td>';
if(userbusy > 0 || switchOff > 0)
{
str+='<td>';
if(switchOff > 0)
{
str+='<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'Switchoff\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+switchOff+'</a>';
}
else
{
str+='0';
}
if(userbusy > 0)
{
str+='/<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'busy\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'callStatus\',\'\');">'+userbusy+'</a>';
}
else
{
str+='/0';
}

str+='</td>';
}
else
str+='<td>'+userbusy+'</td>';

if(callBack > 0)
{
  str += '<td><a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'callback\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'\');">'+callBack+'</a>/';
   if(todaycallBack > 0)
	str += '<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'callback\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'today\');">'+todaycallBack+'</a>';
	else
	  str += ''+todaycallBack+'';
	str += '</td>';
}
else
str+=' <td>'+callBack+'</td>';
if(later > 0 || pending > 0)
{
str+='<td>';
if(later > 0)
{
str+='<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'later\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'\');">'+later+'</a>';
}
else
{
str+='0';
}
if(pending > 0)
{
str+='/<a style="cursor:pointer;text-decoration:underline" onclick="redirectToAgentwithBatch(\''+jObj.callPurposeId+'\',\''+result[i].id+'\',\''+result[i].subList[j].id+'\',\''+result[i].subList[j].subList[k].id+'\',\'Pending\',\''+result[i].subList[j].subList[k].subList[p].id+'\',\'scheduleCallStatus\',\'\');">'+pending+'</a>';
}
else
{
str+='/0';
}
str+='</td>';
}
else
str+='<td>'+later+'/'+pending+'</td>';
str+='</tr>';

}
}
}
}
str+=' </table>';
$("#bacthdate").html(str);
}
function redirectToAgent(purposeId,programId,campId,scheduleId,status,statusType,todayDate)
{
if(todayDate != null && todayDate.length > 0)
 {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!
	  var yyyy = today.getFullYear();
		if(dd<10){
			dd='0'+dd
		} 
		if(mm<10){
			mm='0'+mm
		} 
		var todayDate = dd+'/'+mm+'/'+yyyy;
 }
var browser1 = window.open("callCenterTrainingAgent.action?purposeId="+purposeId+"&programId="+programId+"&campId="+campId+"&scheduleId="+scheduleId+"&status="+status+"&statusType="+statusType+"&today="+todayDate+"&campCallerId="+scheduleCampCallerId+"");
    browser1.focus();
}
function redirectToAgentwithBatch(purposeId,programId,campId,scheduleId,status,batchId,statusType,todayDate)
{

 if(todayDate != null && todayDate.length > 0)
 {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!
	  var yyyy = today.getFullYear();
		if(dd<10){
			dd='0'+dd
		} 
		if(mm<10){
			mm='0'+mm
		} 
		var todayDate = dd+'/'+mm+'/'+yyyy;
 }
  
  
  var browser1 = window.open("callCenterTrainingAgent.action?purposeId="+purposeId+"&programId="+programId+"&campId="+campId+"&scheduleId="+scheduleId+"&status="+status+"&batchId="+batchId+"&statusType="+statusType+"&today="+todayDate+"&campCallerId="+batchCampCallerId+"");
    browser1.focus();
}


function getCallStatusCountByTrainingCampCallerId()
{
	$("#callStatusCountDiv").html("<img src='images/icons/search.gif'>");
	
	$.ajax({
		type   : "POST",
		url    : "getCallStatusCountByTrainingCampCallerIdAction.action"
		
	}).done(function(result){
		 buildCallStatusCountByTraCampCallerId(result);
	});
}

function buildCallStatusCountByTraCampCallerId(result)
{
	var str = '';
	str += '<table class="table table-bordered">';
	str += '<tr>';
	str += '<td rowspan="7">';
    str += '<div id="donutchart" style="width:200px;height:250px"></div>';
    str += '</td>';
	str += '</tr>';
	str += '<tr>';
    str += '<td><h4 class="text-bold m_0" id="allocatedCallId">ALLOCATED CALLS - '+result.allocatedCallsCount+'</h4></td>';
    str += '</tr>';
	str += '<tr>';
    str += '<td class="text-dialled"><h1 class="m_0 display-style text-dialled" id="dialledCallId">'+result.dialledCallsCount+'</h1> Dialled Calls</td>';
    str += '</tr>';
	str += '<tr>';
    str += '<td class="pad_0">';
    str += '<table class="table table-bordered m_0">';
    str += '<tr>';
    str += '<td class="text-dialled"><p id="answeredCallsId">'+result.answerdeCallsCount+' Call<br/>Answered</p></td>';
    str += '<td class="text-switchoff" id="switchoffId">'+result.userBusyCallsCount+' Call<br/>Switched Off / Userbusy</td>';
    str += '</tr>';
    str += '</table>';
    str += '</td>';
    str += '</tr>';
    str += '<tr>';
    str += '<td>';
    str += '<span class="text-custom">'+result.interestedMemCount+' - Members Interested</span>';
    str += '</td>';
    str += '</tr>';
    str += '<tr>';
    str += '<td>';
    str += '<p class="text-not-interested m_0">'+result.currentlyNotIntMemCount+' - Currently not interested</p>';
    str += '</td>';
    str += '</tr>';
    str += '<tr>';
    str += '<td>';
    str += '<p class="text-totally-not m_0">'+result.notIntereMemCount+' - Totally not interested</p>';
    str += '</td>';
    str += '</tr>';
    str += '</table>';
	$("#callStatusCountDiv").html(str);
	buildChart(result);
}


function buildChart(result)
{
	$('#donutchart').html("");
	if(result.interestedMemCount == 0 && result.currentlyNotIntMemCount == 0)
	{
		if(result.notIntereMemCount = 0)
		{
	       $('#donutchart').html("No Data Available.");
           return;	   
		}
	}
	$(function () {
	Highcharts.setOptions({
        colors: ['#3eb6c4', '#079bf3', '#ac68b1']
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
			name : 'Count',
            data: [
                [' Members Interested', result.interestedMemCount],
                ['Currently not interested', result.currentlyNotIntMemCount],
                ['Totally not interested', result.notIntereMemCount],
                
            ]
        }]
    });
});
}

function getMembersCountByBatchStatus(batchStatus,divId)
{
  $("#"+divId+"").html("");
  $("#"+divId+"").html("<img src='images/icons/search.gif'>");
  
  $.ajax({
	data : {batchStatus : batchStatus}, 
	type : "POST",
    url  : "getMembersCountByBatchStatusAction.action"	
  }).done(function(result){
	  $("#"+divId+"").html("");
	  buildMembersCountByBatchStatus(result,divId);
  })	
}

function buildMembersCountByBatchStatus(result,divId)
{
	
	if(result != null && result.length > 0)
	{
	  var str = "";
	  str += '<table class="table table-bordered m_0">';
	  str += '<thead>';
	  str += '<tr>';
      str += '<th>Program Name</th>';
      str += '<th>Training Camp Name</th>';
      str += '<th>Schedule</th>';
      str += '<th>Batch Name & Date</th>';
      str += '<th>Batch Members</th>';
      str += '</tr>';
      str += '</thead>';
	  str += '<tbody>';
	  for(var i in result)
	  {
		str += '<tr>';
        str += '<td rowspan="'+result[i].spanCnt+'">'+result[i].name+'</td>';
		
		for(var j in result[i].subList)
		{
          str += '<td rowspan="'+result[i].subList[j].spanCnt+'">'+result[i].subList[j].name+'</td>';
		  for(var k in result[i].subList[j].subList)
		  {
			str += '<td rowspan="'+result[i].subList[j].subList[k].spanCnt+'">'+result[i].subList[j].subList[k].name+'</td>';  
			
			for(var l in result[i].subList[j].subList[k].subList)
			{
			  str += '<td rowspan="'+result[i].subList[j].subList[k].subList[l].spanCnt+'">'+result[i].subList[j].subList[k].subList[l].name+'</td>';
			  
			  str += '<td rowspan="'+result[i].subList[j].subList[k].subList[l].spanCnt+'">'+result[i].subList[j].subList[k].subList[l].count+'</td>';
			 
			  str += '</tr>';
			  str += '<tr>';
			}
		  }
		}
        str += '</tr>';
	  }
	   str += '</tbody>';
	  str += '</table>';
	  $("#"+divId+"").html(str);
	}
	else
     $("#"+divId+"").html("<div style='text-align:center;margin-top:10px;margin-bottom:10px'>No Data Available.</div>");
}

function getCallBackDayWiseDetails()
{
	$("#callBackDayDiv").html("");
	$("#callBackDayDiv").html("<img src='images/icons/search.gif'>");
	
	$.ajax({
		type : "POST",
		url  : "getCallBackDayWiseDetailsAction.action"
	}).done(function(result){
		$("#callBackDayDiv").html("");
		if(result != null && result.length > 0)
		 buildCallBackDayWiseDetails(result);
	 else
	  $("#callBackDayDiv").html("No Data Available.");
		
	});
}

function buildCallBackDayWiseDetails(result)
{
	var str = '';
	str += '<table class="table table-bordered m_0">';
	str += '<tr>';
	str += '<td></td>';
    str += '<td><p class="m_top20 m_bottom20">TALK TO YOU LATER/<span class="font-10">TCB</span></p></td>';
    str += '<td><p class="m_top20 m_bottom20">CONFIRM LATER/<span class="font-10">TCB</span></p></td>';
	str += '</tr>';
	str += '<tr>';
    str += '<td><p class="m_top20 m_bottom20">Schedule Confirmation</p></td>';
    str += '<td><p class="m_top20 m_bottom20">'+result.scheduleConfirmationCount+'/'+result.todayScheduleConCount+'</p></td>';
    str += '<td><p class="m_top20 m_bottom20">'+result.scheduleConfirmLaterCount+'/'+result.todayScheduleConfirmLaterCount+'</p></td>';
    str += '</tr>';
    str += '<tr>';
    str += '<td><p class="m_top20 m_bottom20">Batch Confirmation</p></td>';
    str += '<td><p class="m_top20 m_bottom20">'+result.batchConfirmationCount+'/'+result.todaybatchConCount+'</p></td>';
    str += '<td><p class="m_top20 m_bottom20">'+result.batchConfirmLaterCount+'/'+result.todaybatchConfirmLaterCount+'</p></td>';
    str += '</tr>';
	str += '</table>';
	$("#callBackDayDiv").html(str);
}


function getAgentCallDetailsByCampCallerId(campCallerId)
{
	$("#agentCallDetailsDiv").html("");
	$("#agentCallDetailsDiv").html("<img src='images/icons/search.gif'>");
	$.ajax({
		data : {campCallerId:campCallerId},
		type:"POST",
		url : "getAgentCallDetailsByCampCallerIdAction.action"
	}).done(function(result){
		$("#agentCallDetailsDiv").html("");
		if(result != null && result.length > 0)
		  buildAgentCallDetailsByCampCallerId(result);
	  else
		$("#agentCallDetailsDiv").html("No Data Avaliable");
		
	})
}
function buildAgentCallDetailsByCampCallerId(result)
{
	
	var str = '';
	str +='<table class="table table-bordered">';
	str +='<tr>';
	str +='<td rowspan="2"><div id="donutchart" style="width:200px;height:150px"></div></td>';
	str +='<td><h4>ALLOCATED CALLS - '+result[0].total+'</h4></td>';
	str +='</tr>';
	str +='<tr>';
	str +='<td class="pad_0">';
	str +='<table class="table table-bordered m_0">';
	str +='<tr>';
	str +='<td></td>';
	str +='<td> Assigned Calls </td>';
	str +='<td class="text-dialled">Answered</td>';
	str +='<td class="text-dialled">Dialed/Not-Dialled</td>';
	str +='<td class="text-dialled">Switch off /User Busy</td>';
	str +='<td colspan="2" style="padding: 0px; text-align: center;">Call Back';
	str +='<table class="table table-bordered table-condenced" style="margin-bottom: 0px;">';
str +='<tbody>';
  str +='<tr>';
    str +='<td style="padding: 0px;"><small>Call Me back / TCB</small></td>';
    str +='<td style="padding: 0px;"><small>Confirm Later / TCB</small></td>';
 str +=' </tr>';
  str +='</tbody><tbody>';
str +='</tbody></table></td>';
	str +='<td class="text-custom">Interested / Confirmed </td>';
	str +='<td class="text-not-interested"> Later / Pending</td>';
	str +='<td class="text-totally-not">Not Interested</td>';
	str +='</tr>';
	for(var i in result)
	{
	if(result[i].name.trim() !="Total")
	{
		str +='<tr>';
		str +='<td>'+result[i].name+'</td>';
		str += '<td>'+result[i].total+'</td>';
	
		
		var dialled = 0;
		var answered = 0;
		var notdialled = 0;
		var switchOff = 0;
		var userBusy = 0;
		var callBackBusy = 0;
		var callBackConfirm = 0;
		var todayCallBackBusy = 0;
		var todayCallBackConfirm = 0;
		var interested = 0;
		var later = 0;
		var pending = 0;
		var notInterested = 0;
		var confirmCnt = 0;
		for(var j in result[i].subList)
		{
			dialled += result[i].subList[j].count;
			if(result[i].subList[j].id == 1)
			answered+=result[i].subList[j].count;
			if(result[i].subList[j].id == 2)
			switchOff+=result[i].subList[j].count;
			if(result[i].subList[j].id == 3)
			userBusy+=result[i].subList[j].count;
		}
		notdialled = result[i].total-dialled;
		str += '<td>'+answered+'</td>';
		str += '<td>'+dialled+'/'+notdialled+'</td>';
		str += '<td>'+switchOff+'/'+userBusy+'</td>';
		for(var k in result[i].scheduleStatusList)
		{
			if(result[i].scheduleStatusList[k].id == 6)
			{
			 callBackBusy += result[i].scheduleStatusList[k].count;
			 todayCallBackBusy += result[i].scheduleStatusList[k].todayCnt;
			}
		    if(result[i].scheduleStatusList[k].id == 7)
			{
			 callBackConfirm += result[i].scheduleStatusList[k].count;
			 todayCallBackConfirm += result[i].scheduleStatusList[k].todayCnt;
			}
		    if( i==1 && k == 9 && result[i].scheduleStatusList[9].id == 10)
			{
				interested += result[i].scheduleStatusList[k].count;
			}
			else if((i==0 || i==2)  && result[i].scheduleStatusList[k].id == 4)
			{
				interested += result[i].scheduleStatusList[k].count;
			}
		   if(result[i].scheduleStatusList[k].id == 3)
			 later += result[i].scheduleStatusList[k].count;
			 if(result[i].scheduleStatusList[k].id == 2)
			 pending += result[i].scheduleStatusList[k].count;
		   if(result[i].scheduleStatusList[k].id == 5)
			 notInterested += result[i].scheduleStatusList[k].count;
		  if(result[i].scheduleStatusList[k].id == 10)
			 confirmCnt += result[i].scheduleStatusList[k].count;
		}
		str +='<td>'+callBackBusy+'/'+todayCallBackBusy+'</td>';
		str +='<td>'+callBackConfirm+'/'+todayCallBackConfirm+'</td>';
		str +='<td class="text-custom">'+interested+'/'+confirmCnt+'</td>';
		str +='<td class="text-not-interested">'+later+'/'+pending+'</td>';
		str +='<td class="text-totally-not">'+notInterested+'</td>';
		str +='</tr>';
		}
	}
	
	str += '</table>';
	$("#agentCallDetailsDiv").html(str);
	buildChartForCallStatus(result);
}

function buildChartForCallStatus(result)
{
	//$('#donutchart').html("<img src='images/icons/search.gif'>");
	var interestedMemCount = 0;
	var laterMembersCount = 0;
	var notIntereMemCount = 0;
	
	for(var i in result[0].scheduleStatusList)
	{
	  if(result[0].scheduleStatusList[i].id == 4)
		interestedMemCount = result[0].scheduleStatusList[i].count;
	   if(result[0].scheduleStatusList[i].id == 3)
		 laterMembersCount = result[0].scheduleStatusList[i].count;
	  if(result[0].scheduleStatusList[i].id == 5)
		 notIntereMemCount = result[0].scheduleStatusList[i].count;
	}
	if(interestedMemCount == 0 && laterMembersCount == 0)
	{
		if(notIntereMemCount == 0)
		{
	       $('#donutchart').html("No Data Available.");
           return;	   
		}
	}

	Highcharts.setOptions({
        colors: ['#3eb6c4', '#079bf3', '#ac68b1']
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
			name : 'Count',
            data: [
                [' Members Interested', interestedMemCount],
                ['Later', laterMembersCount],
                ['Not Interested', notIntereMemCount],
                
            ]
        }]
    });

}

function getAgentsByCampCallerAdminId()
{
	$.ajax({
		type : "POST",
		url  : "getAgentsByCampCallerAdminIdAction.action"
		
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			for(var i in result)
			{
			  $("#agentId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
			}
			getSelectedAgentDetails();
		}
		
	});
}
function getSelectedAgentDetails()
{
	var agentId = $("#agentId").val();
	if(agentId != null && agentId > 0)
	{
		getAgentCallDetailsByCampCallerId(agentId);
		getScheduleCallStatusCount(agentId);
		getBatchWiseCallStatusCount(agentId);
	}
	
}
</script>
<script>
<c:if test="${!fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN') || !fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE')}">
	getScheduleCallStatusCount(0);
	getBatchWiseCallStatusCount(0);
	//getCallStatusCountByTrainingCampCallerId();
	//getCallBackDayWiseDetails();
	getAgentCallDetailsByCampCallerId(0);
</c:if>

<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN') || fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE')}">
 getAgentsByCampCallerAdminId();
</c:if>


</script>
</script>
</body>
</html>