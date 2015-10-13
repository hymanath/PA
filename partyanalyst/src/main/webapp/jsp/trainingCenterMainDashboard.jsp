
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Center</title>
<link href="training/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="training/dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="training/dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
.panel-group .panel
{
	margin-top:0px !important;
}
.panel .panel-default
{
	border-width:1px !important
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
.summaryCls{color:#252D47;text-decoration: underline;}
.summaryCls:hover{text-decoration: underline;}
</style>
</head>
<body>
<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;"></p>               
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
					     <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
						    <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN')}">
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
								<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN')}">
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER')}">
								<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
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
	
	
</header>
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<div class="panel panel-default">
                	<div class="panel-heading bg_c">
                    	<h4 class="panel-title" style="font-weight:bold;">
                        	TRAINING CENTER MAIN DASHBOARD
							<span class="pull-right col-md-3" style="margin-top: -8px;">
								<div class="input-group" >
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									<input class="form-control" id="selectDate">
								</div>
							</span>
                        </h4>
                    </div>
                    <div class="panel-body">
						<div class="pull-right font-12"><span style="color:red">*</span>
												<span>C-Confirmed, IA-Invitee Attended, NIA-Non Invitee Attended</span></div>
                    	<section>
                        	<div class="row">
                            	<div class="col-md-12">
                                	<table class="table table-bordered">
                                    	<tr>
                                        	<td style="vertical-align:middle">
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0" id="totalTariningPrograms">0</h1></li>
                                                </ul>
                                                <p class="m_0">Training Programs</p>
                                            </td>
                                            <td style="vertical-align:middle">
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0" id="totalTrainingCenters">0</h1></li>
                                                </ul>
                                                <p class="m_0">Training Center</p>
                                            </td>
                                            <td style="vertical-align:middle">
												<table class="table-condensed m_0" align="center" style="padding:5px;">
													<tr>
														<td>
															<h4>TOTAL MEMBERS</h4>
															<ul style="padding-left:10px;">
																<li><span id="totalMembers">0</span> CONFIRMED</li>
																<li><span id="totalMembersIn">0</span> INVITEE ATTENDED</li>
																<li><span id="totalMembersNonIn">0</span> NON INVITEE ATTENDED</li>
															</ul>
															<!--<h1 class="m_0" id="totalMembers">0</h1> 
															<p class="m_0">Total Members</p>	-->
														</td>
														<td>
															<table class="font-12 m_0 table-bordered table table-condensed text-center">
																<tr>
																	<td></td>
																	<td>Confirmed</td>
																	<td>Invitees<br>Attended</td>
																	<td>Non-Invitees<br>Attended</td>
																</tr>
																<tr class="text-danger">
																	<td >UPCOMING - </td>
																	<td id="upcomingMembers" style="font-weight:bold;">0</td>
																	<td id="upcomingMembersAte" style="font-weight:bold;">0</td>
																	<td id="upcomingMembersNonAte" style="font-weight:bold;">0</td>
																</tr>
																<tr class="text-yellow">
																	<td >RUNNING - </td>
																	<td id="runningMembers" style="font-weight:bold;">0</td>
																	<td id="runningMembersAte" style="font-weight:bold;">0</td>
																	<td id="runningMembersNonAte" style="font-weight:bold;">0</td>
																</tr>
																<tr class="text-success">
																	<td >COMPLETED - </td>
																	<td id="completedMembers" style="font-weight:bold;">0</td>
																	<td id="completedMembersAte" style="font-weight:bold;">0</td>
																	<td id="completedMembersNonAte" style="font-weight:bold;">0</td>
																</tr>
															</table>
															 <!--<ul class="ul-top-block m_0 font-12">
																<li class="text-danger">UPCOMING -<span class="pull-right" id="upcomingMembers">0</span></li>
																<li class="text-yellow">RUNNING - <span class="pull-right" id="runningMembers">0</span></li>
																<li class="text-success">COMPLETED - <span class="pull-right" id="completedMembers">0</span></li>
															</ul>-->
														</td>
													</tr>
												</table>
                                            </td>
                                            <td style="vertical-align:middle">
												<table class="table-condensed">
													<tr>
														<td>
															<div id="donutchart" style="height: 66px;width:100px;"></div>
														</td>
														<td>
															<h4 class="m_0">TOTAL BATCHES - <span id="totalTrainingBatches" style="font-weight:bold;">0</span></h4>
															<ul class="m_0 pad_0 font-12">
																<li class="text-danger" style="padding:2px; font-weight:bold;">UPCOMING BATCHES - <span id="upcomingBatches">0</span></li>
																<li class="text-yellow" style="padding:2px; font-weight:bold;">RUNNING BATCHES - <span id="runningBatches">0</span> </li>
																<li class="text-success" style="padding:2px; font-weight:bold;">COMPLETED BATCHES - <span id="completedBatches">0</span></li>
															</ul>
														</td>
													</tr>
												</table>
                                            	
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </section>
						<section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">DAY WISE BATCH COUNTS FOR CURRENTLY RUNNING TRAINING PROGRAMS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="runningTrainingProgramsDayWiseAttendence">
										</div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    	<section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">ALL TRAINING PROGRAM WISE DETAILS</h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div class="table-responsive table-scroll" id="allProgramWiseDetailsDIv">0</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">UPCOMING TRAINING PROGRAM DETAILS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="upComingTrainingPrograms">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">CURRENTLY RUNNING TRAINING PROGRAMS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="runningTrainingPrograms">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
						
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">COMPLETED TRAINING PROGRAMS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="completedTrainingPrograms">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
						
                        <section>
                        	<div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title" style="font-weight:bold;">
                                            	PARTY DESIGNATION WISE MEMBERS PARTICIPATED - COMPLETED TRAINING PROGRAMS
                                            </h4>
                                        </div>
										
                                        <div class="panel-body pad_0">
										  <img id="partyDesgWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
										  <div id="partyDesgWisedivId"></div>
											
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
						
                        <section>
                        	<div class="row">
								<div class="col-md-12">
									<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
											<h4 class="panel-title">
												<b><span id="headingDiv">DISTRICT </span>WISE TOTAL MEMBERS PARTICIPATED</b>
												<div class="pull-right">
													<input type="radio" name="distConst" class="constDistRadio" checked value="dist"><label>District</label>
													<input type="radio" name="distConst" class="constDistRadio" value="const"><label>Constituency</label>
												</div>
											</h4>
										</div>
										<div class="panel-body pad_0">
											<div class="row">
												<div class="col-md-12" id="distDivId">
													<div class="panel panel-default">
														<!--<div class="panel-heading bg_d">
															<h4 class="panel-title" style="font-weight:bold;">DISTRICTS WISE TOTAL MEMBERS PARTICIPATED</h4>
														</div>-->
														<div class="panel-body pad_0">
														  <img id="distWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
														  <div id="distWiseDivId"></div>
														</div>
													</div>
													
												</div>
												<div class="col-md-12" id="constDivId" style="display:none;">
													<div class="panel panel-default">
														<!--<div class="panel-heading bg_d">
															<h4 class="panel-title" style="font-weight:bold;">CONSTITUENCY WISE TOTAL MEMBERS PARTICIPATED</h4>
														</div>-->
														
														<div class="panel-body pad_0">
														  <img id="constWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
														  <div id="constWiseDivId"></div>
														</div>
														
														
													</div>
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
</main>
<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>
<script src="training/dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="training/dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="training/dist/scroll/jquery.mCustomScrollbar.js"></script>
<script type="text/javascript" src="training/dist/scroll/jquery.mousewheel.js"></script>
<script src="training/dist/DateRange/moment.js" type="text/javascript"></script>
<script src="training/dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="training/dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/>
<script type="text/javascript">
var fromTypeGlob;
$(function () {
	var cb = function(start, end, label) {
	//console.log(start.toISOString(), end.toISOString(), label);
	$('#selectDate span').html(start.format('D MMMM, YYYY')- + ' - ' + end.format('D MMMM, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }
  var optionSet1 = {
	startDate: moment().startOf('month'),
	endDate: moment().endOf('month'),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   //'Today': [moment(), moment()],
	   //'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   //'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [ moment().subtract(30, 'days'),moment()],
	   'Lat 60 Days': [moment().subtract(60, 'days'),moment()],
	   'Last 180 Days': [moment().subtract(6, 'months'),moment()],
	   'Last 365 Days': [moment().subtract(1, 'year'),moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   //'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'DD/MM/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
  $('#selectDate span').html(moment().subtract(29, 'days').format('D MMMM, YYYY') + ' - ' + moment().format('D MMMM, YYYY'));

  $('#selectDate').daterangepicker(optionSet1, cb);

  $('#selectDate').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#selectDate').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  /*$('#selectDate').on('apply.daterangepicker', function(ev, picker) { 
	 console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('D MMMM, YYYY') 
	  + " to " 
	  + picker.endDate.format('D MMMM , YYYY')
	);  
  });*/
  $('#selectDate').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
	fromTypeGlob="onLoad";
	getTrainingCenterDetailsBasedOnDates("onLoad");
	getAttendedCountForBatchesByLocation("onLoad");
	getInvitedAttendedCadreCountByBatchIds("onLoad");
	getDayWiseCountsForRunningBatches("onLoad");
	//$(".ranges ul li:nth-child(5)").trigger("click");
	
});

 $("#mainheading").html(" TRAINING CENTER DASHBOARD ");
 
function getTrainingCenterDetailsBasedOnDates(fromType){
	
	var dates;
	if(fromType=="onLoad"){
		dates="";
	}else{
		dates=$("#selectDate").val();
	}
  
	var jsObj=
	{	
		selectedDate : dates
	}
    $.ajax({
          type:'GET',
          url: 'getTrainingCenterDetailsBasedOnDatesAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#totalTariningPrograms").html("0");
		$("#totalTrainingCenters").html("0");
		$("#totalMembers").html("0");
		$("#upcomingMembers").html("0");
		$("#runningMembers").html("0");
		$("#completedMembers").html("0");
		$("#totalTrainingBatches").html("0");
		$("#upcomingBatches").html("0");
		$("#runningBatches").html("0");
		$("#completedBatches").html("0");
	   if(result!=null && result.completed!=null && result.completed.programDetails!=null && result.completed.programDetails.length>0){
		  var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	
			
			var obj={};
			
			//for programwise batch count
			for(var i in result.completed.programDetails){
				var temp=0;;
				var myResult = result.completed.programDetails;
				if(result.completed.completedBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.completed.programDetails){
				var myResult = result.completed.programDetails;
				if(result.completed.completedBatchIds!=null){
					str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_batch_id='0' attr_camp_id='0' style='cursor:pointer' title='Click Here TO View Program Summary'>"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"' attr_batch_id='0' style='cursor:pointer' title='Click Here View Camp Summary'>"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td class='summaryCls' title='Click Here to View Batch Summary' style='cursor:pointer' attr_batch_id='"+schedule.batchDetails[m].batchId+"' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"'>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										str+='<td><b>'+schedule.batchDetails[m].completedMemberAttendeeCount+'</b> C - <b>'+schedule.batchDetails[m].completedMemberCount+'</b> IA - <b>'+schedule.batchDetails[m].completedAttendenceNonIn+'</b> NIA</td>';
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#completedTrainingPrograms").html(str);
		}else{
			$("#completedTrainingPrograms").html("<h4 style='font-weight:bold;margin-left:10px;'>No Completed Training Programs Are Available</h4>");
		}
		
		if(result!=null && result.running!=null && result.running.programDetails!=null && result.running.programDetails.length>0){
						
			var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	
			
			var obj={};
			//for programwise batch count
			for(var i in result.running.programDetails){
				var temp=0;;
				var myResult = result.running.programDetails;
				if(result.running.runningBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.running.programDetails){
				var myResult = result.running.programDetails;
				str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_batch_id='0' attr_camp_id='0' style='cursor:pointer' title='Click Here TO View Program Summary'>"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"' attr_batch_id='0' style='cursor:pointer' title='Click Here View Camp Summary'>"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td class='summaryCls' title='Click Here to View Batch Summary' style='cursor:pointer' attr_batch_id='"+schedule.batchDetails[m].batchId+"' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"'>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].runningMemberCount!=null){
											str+='<td><b>'+schedule.batchDetails[m].runningMemberCount+'</b> C - <b>'+schedule.batchDetails[m].runningAttendenceMemberCount+'</b> IA - <b>'+schedule.batchDetails[m].runningAttendenceNonIn+'</b> NTA</td>';
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#runningTrainingPrograms").html(str);
		}else{
			$("#runningTrainingPrograms").html("<h4 style='font-weight:bold;margin-left:10px;'>No Running Training Programs Are Available</h4>");
		}
		
		/*//day wise attendence for currently running batches
		if(result!=null && result.completed!=null && result.completed.simpleVoList!=null && result.completed.simpleVoList.length>0){
			var str='';
			str+='<table class="table table-bordered">';
			str+='<thead class="bg_d">';
			str+='<tr>';
			str+='<th>Center</th>';
			str+='<th>Batch</th>';
			str+='<th>Day 1 Count</th>';
			str+='<th>Day 2 Count</th>';
			str+='<th>Day 3 Count</th>';
			str+='<th>1 Day Attended Cadres</th>';
			str+='<th>2 Days Attended Cadres</th>';
			str+='<th>3 Days Attended Cadres</th>';
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.completed.simpleVoList){
				str+='<tr>';
				str+='<td>'+result.completed.simpleVoList[i].centerName+'</td>';
				str+='<td>'+result.completed.simpleVoList[i].batchName+'</td>';
				for(var j in result.completed.simpleVoList[i].simpleVOList1){
					if(result.completed.simpleVoList[i].simpleVOList1[j].total!=null){
						str+='<td>'+result.completed.simpleVoList[i].simpleVOList1[j].total+'</td>'
					}else{
						str+='<td>0</td>'
					}
				}
				str+='<td>'+result.completed.simpleVoList[i].day1Count+'</td>';
				str+='<td>'+result.completed.simpleVoList[i].day2Count+'</td>';
				str+='<td>'+result.completed.simpleVoList[i].day3Count+'</td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#runningTrainingProgramsDayWiseAttendence").html(str);
		}else{
			$("#runningTrainingProgramsDayWiseAttendence").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
		}*/
		
		if(result!=null && result.upcoming!=null && result.upcoming.programDetails!=null && result.upcoming.programDetails.length>0){
			var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	

			var obj={};
			//for programwise batch count
			for(var i in result.upcoming.programDetails){
				var temp=0;;
				var myResult = result.upcoming.programDetails;
				if(result.upcoming.upComingBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.upcoming.programDetails){
				var myResult = result.upcoming.programDetails;
				if(result.upcoming.upComingBatchIds!=null){
					str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_batch_id='0' attr_camp_id='0' style='cursor:pointer' title='Click Here TO View Program Summary'>"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+" class='summaryCls' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"' attr_batch_id='0' style='cursor:pointer' title='Click Here View Camp Summary'>"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td class='summaryCls' title='Click Here to View Batch Summary' style='cursor:pointer' attr_batch_id='"+schedule.batchDetails[m].batchId+"' attr_prog_id='"+myResult[i].programId+"' attr_camp_id='"+myResult[i].campDetails[j].campId+"'>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].upCommingMemberCount!=null){
											str+='<td><b>'+schedule.batchDetails[m].upCommingMemberCount+'</b> C</td>';
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				}
				
				
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#upComingTrainingPrograms").html(str);
		}else{
			$("#upComingTrainingPrograms").html("<h4 style='font-weight:bold;margin-left:10px;'>No Upcomming Training Programs Are Available</h4>");
		}
		
		
		if(result!=null && result.completed!=null && result.completed.programWiseDetails!=null && result.completed.programWiseDetails.length>0){
			var totalMembersGlob=0,upcomingBatchesGlob=0,compltedBatchesGlob=0,runningBatchesGlob=0,upcomingMemGlob=0,upcomingMemGlob1=0,upcomingMemGlob2=0,runningMemGlob=0,runningMemGlob1=0,runningMemGlob2=0,compltedMemGlob=0,compltedMemGlob1=0,compltedMemGlob2=0,totalTrainingBatchesGlob=0;
			var str='';
			
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>Training Program Name</th>';
			str+='<th>Training Center Name</th>';
			str+='<th>Total<br/> Batches</th>';
			str+='<th class="text-danger">Upcoming<br/> Batches</th>';
			str+='<th class="text-warning">Running<br/> Batches</th>';
			str+='<th class="text-success">Completed <br/>Batches</th>';
			str+='<th class="text-muted">Total <br/>Members</th>';
			str+='</thead>';
			str+='<tbody>';
			$("#totalTariningPrograms").html(result.completed.programWiseDetails.length);
			$("#totalTrainingCenters").html(result.completed.totalTrainingCenters);
			for(var i in result.completed.programWiseDetails){
				str+='<tr>';
				str+='<td class="summaryCls" title="Click Here To View Program Summary" style="Cursor:Pointer" attr_prog_id="'+result.completed.programWiseDetails[i].programId+'" attr_batch_id="0" attr_camp_id="0" rowspan='+result.completed.programWiseDetails[i].campDetails.length+'>'+result.completed.programWiseDetails[i].programName+'</td>';
				if(result.completed.programWiseDetails[i].campDetails!=null && result.completed.programWiseDetails[i].campDetails.length>0){
					for(var j in result.completed.programWiseDetails[i].campDetails){
						str+='<td class="summaryCls" title="Click Here TO View Camp Summary" style="cursor:pointer" attr_prog_id="'+result.completed.programWiseDetails[i].programId+'" attr_batch_id="0" attr_camp_id="'+result.completed.programWiseDetails[i].campDetails[j].campId+'">'+result.completed.programWiseDetails[i].campDetails[j].campName+'</td>';
						
						if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails.length>0){
							var TotalBatches=0,upcomingDetails=0,upcomingMem=0,upcomingMem1=0,upcomingMem2=0,completedDetails=0,completedMem=0,completedMem1=0,completedMem2=0,runningDetails=0,runningMem=0,runningMem1=0,runningMem2=0,TotalMembers=0,TotalMembers1=0,TotalMembers2=0;
							for(var k in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails){
								
								TotalBatches=TotalBatches+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
								
								//totalTrainingBatchesGlob=totalTrainingBatchesGlob+TotalBatches;
								totalTrainingBatchesGlob=totalTrainingBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length>0){
									var temp=0;
									var temp1=0;
									var temp2=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCount;
										temp1=temp1+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCountAttendee;
										temp2=temp2+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCountNonIn;
										
									}
									TotalMembers=TotalMembers+temp;
									TotalMembers1=TotalMembers1+temp1;
									TotalMembers2=TotalMembers2+temp2;
									upcomingMemGlob=upcomingMemGlob+temp;
									upcomingMemGlob1=upcomingMemGlob1+temp1;
									upcomingMemGlob2=upcomingMemGlob2+temp2;
									upcomingMem=upcomingMem+temp;
									upcomingMem1=upcomingMem1+temp1;
									upcomingMem2=upcomingMem2+temp2;
									upcomingBatchesGlob=upcomingBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length;
									
									upcomingDetails=upcomingDetails+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length;
									
								}
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length>0){
									var temp=0;
									var temp1=0;
									var temp2=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCount;
										temp1=temp1+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCountAttendee;
										temp2=temp2+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCountNonIn;
									}
									TotalMembers=TotalMembers+temp;
									TotalMembers1=TotalMembers1+temp1;
									TotalMembers2=TotalMembers2+temp2;
									runningMemGlob=runningMemGlob+temp;
									runningMemGlob1=runningMemGlob1+temp1;
									runningMemGlob2=runningMemGlob2+temp2;
									runningMem=runningMem+temp;
									runningMem1=runningMem1+temp1;
									runningMem2=runningMem2+temp2;
									runningBatchesGlob=runningBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length;
									runningDetails=runningDetails+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length
									
								}
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length>0){
									var temp=0;
									var temp1=0;
									var temp2=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCount;
										temp1=temp1+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCountAttendee;
										temp2=temp2+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCountNonIn;
									}
									TotalMembers=TotalMembers+temp1;
									TotalMembers1=TotalMembers1+temp;
									TotalMembers2=TotalMembers2+temp2;
									compltedMemGlob=compltedMemGlob+temp;
									compltedMemGlob1=compltedMemGlob1+temp1;
									compltedMemGlob2=compltedMemGlob2+temp2;
									completedMem=completedMem+temp;
									completedMem1=completedMem1+temp1;
									completedMem2=completedMem2+temp2;
									compltedBatchesGlob=compltedBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
									completedDetails=completedDetails+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
									
								}
								totalMembersGlob=totalMembersGlob+TotalMembers;
								
							}
							
							str+='<td>'+TotalBatches+'</td>';
							str+='<td class="text-danger"><b>'+upcomingDetails+'</b><br>(<b>'+upcomingMem+'</b> C - <b>'+upcomingMem1+'</b> IA - <b>'+upcomingMem2+'</b> NIA)</td>';
							str+='<td class="text-warning"><b>'+runningDetails+'</b><br>(<b>'+runningMem+'</b> C - <b>'+runningMem1+'</b> IA - <b>'+runningMem2+'</b> NIA)</td>';
							str+='<td class="text-success"><b>'+completedDetails+'</b><br>(<b>'+completedMem1+'</b> C - <b>'+completedMem+'</b> IA - <b>'+completedMem2+'</b> NIA)</td>';
							str+='<td class="text-muted"><b>'+TotalMembers+'</b> C - <b>'+TotalMembers1+'</b> IA - <b>'+TotalMembers2+'</b> NIA</td>';
						}	
						str+='</tr>';
					}
				}
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#totalMembers").html(upcomingMemGlob+runningMemGlob+compltedMemGlob1);
			$("#totalMembersIn").html(upcomingMemGlob1+runningMemGlob1+compltedMemGlob);
			$("#totalMembersNonIn").html(upcomingMemGlob2+runningMemGlob2+compltedMemGlob2);
			//$("#upcomingMembers").html(upcomingMemGlob+" ("+upcomingMemGlob1+" - Members)"+"");
			//$("#runningMembers").html(runningMemGlob+" ("+runningMemGlob1+" - Members)"+"");
			//$("#completedMembers").html(compltedMemGlob1+" ("+compltedMemGlob+" - Members)"+"");
			$("#upcomingMembers").html(upcomingMemGlob);
			$("#upcomingMembersAte").html(upcomingMemGlob1);
			$("#upcomingMembersNonAte").html(upcomingMemGlob2);
			$("#runningMembers").html(runningMemGlob);
			$("#runningMembersAte").html(runningMemGlob1);
			$("#runningMembersNonAte").html(runningMemGlob2);
			$("#completedMembers").html(compltedMemGlob1);
			$("#completedMembersAte").html(compltedMemGlob);
			$("#completedMembersNonAte").html(compltedMemGlob2);
			$("#totalTrainingBatches").html(totalTrainingBatchesGlob);
			$("#upcomingBatches").html(upcomingBatchesGlob);
			$("#runningBatches").html(runningBatchesGlob);
			$("#completedBatches").html(compltedBatchesGlob);
			$("#allProgramWiseDetailsDIv").html(str);
			
			Highcharts.setOptions({
				colors: ['#40b5bf', '#999967', '#089bf8', '#ac69ae' , '#cccccc']
			});
			$('#donutchart').highcharts({
				chart: {
					type: 'pie',
					backgroundColor: 'transparent',
					options3d: {
						enabled: false,
						alpha: 30
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
						innerSize: 50,
						depth: 10,
						dataLabels: {
							enabled: false,
						}
					}, 
				},
				
				series: [{
					name: ' ',
					data: [
						['Upcoming Batchs', upcomingBatchesGlob],
						['Running Batchs', runningBatchesGlob],
						['Completed Batchs', compltedBatchesGlob],
					]
				}]
			});
		}else{
			$("#allProgramWiseDetailsDIv").html("No Data Found");
		}
   });
}

	function getAttendedCountForBatchesByLocation(fromType){
		
		$('#partyDesgWisedivId').html('');
		
		var dates;
		if(fromType=="onLoad"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
	
		var jObj={
			selectedDate : dates
	     }
		$.ajax({
		   type:'POST',
		   url :'getAttendedCountForBatchesByLocationAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			  $('#partyDesgWiseImg').hide();
			  if(result!=null && result.length>0){
				  var str='';
				  str+='<table class="table table-bordered m_0">';
				  for(var i in result){
				    str+='<tr>';
                      str+='<td>'+result[i].name.toUpperCase()+'</td>';
                      str+='<td>'+result[i].districtid+' ATTENDED</td>';
                      str+='</tr>';
				  }
				 str+='</table>';
				 $('#partyDesgWisedivId').html(str);
			  }else{
				   $('#partyDesgWisedivId').html('NO DATA AVAILABLE');
			  }
	    });
	}
	
	function getDayWiseCountsForRunningBatches(fromType){
		var dates;
		if(fromType=="onLoad"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
		
		var jObj={
			selectedDate : dates
	     }
		
		$.ajax({
		   type:'POST',
		   url :'getDayWiseCountsForRunningBatchesAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			//day wise attendence for currently running batches
			if(result!=null && result.length>0){
				var str='';
				str+='<table class="table table-bordered m_0">';
				str+='<thead class="bg_d">';
				str+='<tr>';
				str+='<th>Center</th>';
				str+='<th>Batch</th>';
				str+='<th>Day 1 Count</th>';
				str+='<th>Day 2 Count</th>';
				str+='<th>Day 3 Count</th>';
				str+='<th>1 Day Attended Cadres</th>';
				str+='<th>2 Days Attended Cadres</th>';
				str+='<th>3 Days Attended Cadres</th>';
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
					str+='<td>'+result[i].centerName+'</td>';
					str+='<td>'+result[i].batchName+'</td>';
					for(var j in result[i].simpleVOList1){
						if(result[i].simpleVOList1[j].total!=null){
							str+='<td>'+result[i].simpleVOList1[j].total+'</td>'
						}else{
							str+='<td>0</td>'
						}
					}
					str+='<td>'+result[i].day1Count+'</td>';
					str+='<td>'+result[i].day2Count+'</td>';
					str+='<td>'+result[i].day3Count+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
				str+='</table>';
				$("#runningTrainingProgramsDayWiseAttendence").html(str);
			}else{
				$("#runningTrainingProgramsDayWiseAttendence").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
			}
		});
	}
	
	function getInvitedAttendedCadreCountByBatchIds(fromType){
		
		$('#distWiseDivId').html('');
		$('#constWiseDivId').html('');
		
		var dates;
		if(fromType=="onLoad"){
			dates="";
		}else{
			dates=$("#selectDate").val();
		}
		
		var jObj={
		  selectedDate : dates
	     }
		$.ajax({
		   type:'POST',
		   url :'getInvitedAttendedCadreCountByBatchIdsAction.action',
		    data: {task:JSON.stringify(jObj)},
		}).done(function(result){
		       $('#distWiseImg').hide(); 
			   $('#constWiseImg').hide(); 
			   
		 	   if(result!=null && result.simpleVOList1!=null && result.simpleVOList1.length>0){
				   buildDistData(result.simpleVOList1);
			   }else{
				   $('#distWiseDivId').html('<p style="text-align:center;font-weight:bold;">NO DATA AVAILABLE</p>');
			   }
			   
			   if(result!=null && result.simpleVOList2!=null && result.simpleVOList2.length>0){
				  buildConstData(result.simpleVOList2);
			   }else{
				  $('#constWiseDivId').html('<p style="text-align:center;font-weight:bold;">NO DATA AVAILABLE</p>');
			   }
	    });
	}
	function buildDistData(results){
		
		var str='';
		str+='<table class="table table-bordered m_0" id="distTable">';
			str+='<thead class="bg_d">';
			str+='<th>DISTRICTS NAME</th>';
			str+='<th>INVITED</th>';
			str+='<th>ATTENDED</th>';
		    str+='<th>INVITEES ATTENDED</th>';
			str+='<th>INVITEES ATTENDED PERCENTAGE</th>';
			str+='<th>NON INVITEES ATTENDED</th>';
			str+='</thead>';
			str+='<tbody>';
		for(var i in results){
			str+='<tr>';
				str+='<td>'+results[i].name+'</td>';
				
				if(results[i].total==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].total+'</td>';
				}
				
				if(results[i].count==0){
					str+='<td> - </td>';
				}else{
                   str+='<td>'+results[i].count+'</td>';
				}
				if(results[i].locValue==0){
					str+='<td> - </td>';
				}else{
                   str+='<td>'+results[i].locValue+'</td>';
				}
				if(results[i].percentage==null){
					str+='<td>0.00</td>';
				}else{
					str+='<td>'+results[i].percentage+'</td>';
				}
				if(results[i].totalCount==0){
					str+='<td> - </td>';
				}else{
                   str+='<td>'+results[i].totalCount+'</td>';
				}
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#distWiseDivId').html(str);
		$("#distTable").dataTable({"aaSorting": [[ 1, "desc" ]],
		   "aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]});
		$("#distTable").removeClass('dataTable');
	}
	function buildConstData(results){
		
		var str='';
		str+='<table class="table table-bordered m_0" id="constTable">';
			str+='<thead class="bg_d">';
			str+='<th>CONSTITUENCY NAME</th>';
			str+='<th>INVITED</th>';
			str+='<th>ATTENDED</th>';
			str+='<th>INVITEES ATTENDED</th>';
			str+='<th>INVITEES ATTENDED PERCENTAGE</th>';
			str+='<th>NON INVITEES ATTENDED</th>';
			str+='</thead>';
			str+='<tbody>';
		for(var i in results){
			str+='<tr>';
				str+='<td>'+results[i].name+'</td>';
				
				if(results[i].total==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].total+'</td>';
				}
				
				if(results[i].count==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].count+'</td>';
				}
				if(results[i].locValue==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].locValue+'</td>';
				}
				if(results[i].percentage==null){
					str+='<td>0.00</td>';
				}else{
					str+='<td>'+results[i].percentage+'</td>';
				}
				
				if(results[i].totalCount==0){
					str+='<td> - </td>';
				}else{
					str+='<td>'+results[i].totalCount+'</td>';
				}
				
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#constWiseDivId').html(str);
		
		$("#constTable").dataTable({"aaSorting": [[ 1, "desc" ]],
		   "aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]});
		$("#constTable").removeClass("dataTable");
	}
	
	$(document).on('click', '.applyBtn', function(){
		fromTypeGlob="onClick";
		getTrainingCenterDetailsBasedOnDates("onClick");
		getAttendedCountForBatchesByLocation("onClick");
		getInvitedAttendedCadreCountByBatchIds("onClick");
		getDayWiseCountsForRunningBatches("onClick");
	});
	
	$(document).on("click",".ranges li",function(){
		fromTypeGlob="onClick";
		getTrainingCenterDetailsBasedOnDates("onClick");
		getAttendedCountForBatchesByLocation("onClick");
		getInvitedAttendedCadreCountByBatchIds("onClick");
		getDayWiseCountsForRunningBatches("onClick");
	});
	
	$(document).on("click",".summaryCls",function(){
		var progId = $(this).attr("attr_prog_id");
		var campId = $(this).attr("attr_camp_id");
		var batchId = $(this).attr("attr_batch_id");
		var dates = $("#selectDate").val();
		if(fromTypeGlob=="onClick"){
			dates = $("#selectDate").val();
		}else{
			dates="";
		}
		var win = window.open('trainingProgramMainDashBoardAction.action?pd='+progId+'&cd='+campId+'&bd='+batchId+'&dts='+dates+'', '_blank');
	});
	
	$(".constDistRadio").click(function(){
		if($(this).val()=="const"){
			$("#constDivId").show();
			$("#headingDiv").html("CONSTITUENCY ");
			$("#distDivId").hide();
		}else if($(this).val()=="dist"){
			$("#constDivId").hide();
			$("#headingDiv").html("DISTRICT ");
			$("#distDivId").show();
		}
	});
</script>
</body>
</html>	