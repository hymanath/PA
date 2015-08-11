<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Call Center Training Admin </title>
<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>

</head>
<style>
    header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
.circle-text, .circle-info, .circle-text-half, .circle-info-half 
{
	font-size:22px;
	top:35%;
	left:0;
}
/*.invited{background-color:rgb(238,130,238);}
.pending{background-color:rgb(255,0,0);}
.interested{background-color:rgb(64,181,191);}
.confirmed{background-color:rgb(0,255,0);}*/

.invited-text{color:rgb(238,130,238) !important;}
.pending-text{color:rgb(255,0,0) !important;}
.interested-text{color:rgb(64,181,191) !important;}
.confirmed-text{color:rgb(0,255,0) !important;}
</style>
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
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>
<section>
	<div class="container">
		<div class="col-md-12">
			<div class="panel panel-default panel-custom">
				<div class="panel-heading">
					<h4 class="panel-title"><b>CALL CENTER ADMIN DASHBOARD</b>
						<span class="pull-right col-md-3" style="margin-top:-8px">
							<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
									<span class="caret"></span>
								</span>
								<input type="text" class="form-control" id="reportrange">
							</div>
						</span>
                    <!--	<span class="pull-right">
                        	<label class="checkbox-inline">
                            	<input type="checkbox"> Planned
                            </label>
                            <label class="checkbox-inline">
                            	<input type="checkbox">All
                            </label>
                        </span> -->
					</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-8">
							<table class="table table-bordered">
								<tr>
									<td>
										<center><img id="dataLoadingsImgForDonutchartStatus" src="images/icons/loading.gif" style="width: 40px; height: 40px;margin-top:50px;"/></center>
										<div id="donutchartForStatus" class="display-style" style="height: 160px;float:left;width:190px;"></div>
									</td>
									<td class="pad_0">
										<table class="table table-bordered m_0">
											<tr>
												<td colspan="3" class="pad_15">
													<h3 class="m_0">ALLOCATED CALLS TO CALLER - <span id="totalCallsPerCallerId">
														<img id="dataLoadingsImgForTotalCallerCount" src="images/icons/loading.gif" style="width: 15px; height: 15px;"/>
													</span></h3>
													<span class="pull-right font-12">Allocated Today - <span id="todayCallsPerCallerId">
													<img id="dataLoadingsImgForTodayCount" src="images/icons/loading.gif" style="width: 10px; height: 10px;"/>
													</span></span>
												</td>
											</tr>
											<tr>
												<td colspan="2" class="pad_0" id="callPurposeCountDivId">
												<center><img id="dataLoadingsImgForPurposeCountId" src="images/icons/loading.gif" style="width: 10px; height: 10px;margin-top:30px;"/></center>
													
												</td>
												<td class="pad_0" id="statusWiseCountArraId">
												<center><img id="dataLoadingsImgForStatus" src="images/icons/loading.gif" style="width: 25px; height: 25px;margin_top:30px;"/></center>
													
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div class="col-md-4">
							<table class="table table-bordered">
								<tr>
									<td>
										<ul class="list-inline m_bottom0">
											<li><h4>TRAINING <br/>PROGRAMS</h4></li>
											<li class="show-dropdown">
												<div><h1 class="m_0"><span id="totalProgramsCountId">
												<img id="dataLoadingsImgFortotalProgramsCountId" src="images/icons/loading.gif" style="width: 15px; height: 15px;"/>
												</span></h1></div>
												<ul class="count-hover up-arrow">
													<li>
														<div class="count-hover-scroll" id="batchCountOfProgramId">
														
															<center><img id="dataLoadingsImgForBatchOfProgramId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
														
														</div>
													</li>
												</ul>
											</li>
										</ul>
									</td>
									<td>
										<ul class="list-inline m_bottom0">
											<li><h4>TRAINING <br/>CAMPS</h4></li>
											<li class="show-dropdown">
												<div><h1 class="m_0"><span id="totalCampsCountId">
												<img id="dataLoadingsImgFortotalCampsCountId" src="images/icons/loading.gif" style="width: 15px; height: 15px;"/>
												</span></h1></div>
												<ul class="count-hover up-arrow">
													<li>
													<div class="count-hover-scroll" id="batchCountOfCampId"> 
														
													</div>
													</li>
												</ul>
											</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td colspan="2">
									<center><img id="dataLoadingsImgForCircleForConfirmed" src="images/icons/loading.gif" style="width: 40px; height: 40px;margin-top:50px;"/></center>
										<div id="circleForConfirmed"></div>
										<!--<div id="myStathalf"  class="text-center" data-info="600" data-dimension="150px" data-percent="35" data-fgcolor="#40b6c0" data-bgcolor="#cccccc" data-type="half" ></div>-->
										<p style="margin-top:-70px;margin-bottom:0px;color:#40b6c0;text-align:center;">MEMBERS FILLED IN CALENDAR BATCHES</p>
									</td>
								</tr>
							</table>
						</div>
					</div>
                    <div class="row">
                    	<div class="col-md-12">
                        	<div class="panel panel-default">
								<div class="panel-heading pad_5 pad_bottom0">
									<ul class="nav nav-tabs tab-list-sch searchTypeCls" role="tablist">
										<li class="active"><a href="#scheduled" class="text-bold" data-toggle="tab">SCHEDULED</a></li>
										<li><a href="#running" class="text-bold" data-toggle="tab">RUNNING BATCH</a></li>
										<li><a href="#completed" class="text-bold" data-toggle="tab">COMPLETED BATCH</a></li>
										<li><a href="#cancelled" class="text-bold" data-toggle="tab">CANCELLED BATCH</a></li>
                                       
									</ul>
								</div>
								<div class="panel-body pad_0">
									<div>
									  <!-- Tab panes -->
									  <div class="tab-content">
									  <div role="tabpanel" class="tab-pane active" id="scheduled"></div>									
										<div role="tabpanel" class="tab-pane" id="running"> </div>
                                        <div role="tabpanel" class="tab-pane" id="completed">   </div>
                                        <div role="tabpanel" class="tab-pane" id="cancelled"></div>
									  </div>
									</div>
								</div>
							</div>
                        </div>
                    </div>
					<div class="row">
						<div class="col-md-4">
							<div class="assign_box">
								<!--<label class="checkbox-inline font-10">
                                	<input type="checkbox">
                                    Assign Members <br/>to check availability
                                </label>
                                <label class="checkbox-inline font-10">
                                	<input type="checkbox">
                                    Assign Members<br/>For acceptance
                                </label>-->
								<h5>Select Member/Batch</h5>
								<select class="form-control" id="memeberBatchConformationId" onchange="getAllCampBatches(this.value);">
									<option value="0" selected>Select Member/Batch</option>
									<option value="1">Member Confirmation</option>
									<option value="2">Batch Confirmation</option>
								</select>
								<h5>Select Camp</h5>
								<select class="form-control" id="campId" onchange="getAllProgramsList(this.value);">
									<option value="0">Select Camp</option>
								</select>
                                <h5>Select Program Name</h5>
								<!--<input class="form-control" placeholder="Leasdership Skills">-->
								<select class="form-control" id="programId" onchange="getAllSchedulesDatesList(this.value);">
									<option value="0">Select Program</option>
								</select>
                                <h5>Select Calendar Schedule Dates</h5>
								<select class="form-control" id="scheduleId" onchange="getAvailableCountForMemberConfirmation(this.value);">
									<option value="0">Select Schedule</option>
								</select>
								<div id="memberConfirmationDivId">
								<h5 class="m_top20">
									Select no of calls
									<!--<span class="text-danger pull-right text-italic">Avail Calls 500</span>-->
									<br> <div id="availableCallsDivId" style="display:none">
									<label><input type="radio"><span class="text-danger pull-right text-italic">Avail Calls <span id="availCountId"></span></span></input></label>
									</div>
								</h5>
								<input class="form-control" type="text" id="membersCountId">
								<h5>Select Call Center Caller Name</h5>
								<select class="form-control callerId">
									<option value="0">Select Caller</option>
								</select>
								</div>
								<div id="batchConfirmationDivId" style="display:none">
								<h5>Select Call Center Caller Name</h5>
								<select class="form-control callerId" onchange="getAvailableMembersCountDetails(this.value);">
									<option value="0">Select Caller</option>
									<option value="1">Harish</option>
								</select>
								<h5 class="m_top20">
									Select no of calls
									<br> <div id="availableCounstDivId" style="display:none;">
									<label><input type="radio"><span class="text-danger pull-right text-italic">Avail Own Calls <span id="ownCountId"></span></span></input></label>
									<label><input type="radio"> <span class="text-danger pull-right text-italic">Avail Others Calls <span id="othersCountId"></span></span></label>
									</div>
								</h5>
								<input class="form-control" type="text" id="membersCountId">
								</div>
								<div id="searchErrDivID"></div>
								<button class="btn btn-success btn-block m_top20" type="button" onclick="saveAllDetails();">ASSIGN TO CALLER</button>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane active InterestedMembersCountDivId table-responsive" id="interestedDivId">
						<center><img id="dataLoadingsImgForInterestedMembersCountId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
						</div>
						<!--<div class="col-md-8">
							<div class="table-scroll">
								<table class="table table-bordered m_0">
									<thead class="bg_d">
									    <th>TRAINING <br/> PROGRAM NAME</th>
										<th>TRAINING CAMP<br/> NAME</th>
										<th>START <br/> DATE</th>
										<th>END <br/> DATE</th>											
										<th>MEMBERS <br/>ACCEPTED</th>
									</thead>
									<tbody>
										<tr>
										<td rowspan="4">Leadership Skills</td>
											<td>SVV Batch Campus</td>
											<td></td>											
											<td></td>											
											<td>100</td>
										</tr>
										<tr>
											<td>EWK Batch Campus</td>
											<td></td>											
											<td></td>
											<td>100</td>
										</tr>
										<tr>
											<td>GPN Batch Campus</td>
											<td></td>											
											<td></td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td></td>											
											<td></td>
											<td>100</td>
										</tr>
										<tr>
											<td rowspan="4">Election Year</td>
											<td>SVV Batch Campus</td>
											<td></td>											
											<td></td>											
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td></td>											
											<td></td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td></td>											
											<td></td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td></td>											
											<td></td>
											<td>100</td>
										</tr>
										
									</tbody>
								</table>
							</div>
						</div>-->
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 m_top20">
							<div class="panel panel-default">
								<div class="panel-heading pad_5 pad_bottom0">
									<ul class="nav nav-tabs tab-list" role="tablist">
										<li class="active"><a href="#area" class="text-bold" data-toggle="tab">CALLERS CALL DATA PROGRESS - MEMBERS AVAILABILITY</a></li>
										<!--<li><a href="#participated" class="text-bold" data-toggle="tab">MEMBERS ACCEPTANCE</a></li>-->
									</ul>
								</div>
								<div class="panel-body pad_0">
									<div>
									  <div class="tab-content">
										<div role="tabpanel" class="tab-pane active memberAvailabilityDivCls table-responsive" id="area">
										<center><img id="dataLoadingsImgForProgressOfAgentCountId" src="images/icons/loading.gif" style="width: 30px; height: 30px;margin-top:30px;"/></center>
											<!--<table class="table table-bordered m_0">
												<thead>
													<th>Agent Name</th>
													<th>Assigned Calls</th>
													<th>Completed Calls</th>
													<th>Pending Calls</th>
													<th class="font-12">
                                                    	Scheduled Camp <br/>Interested Members
                                                        
                                                    </th>
													<th>Interested Later</th>
													<th>Not interested</th>
												</thead>
												<tbody>
													<tr>
														<td>Agent 1	</td>
														<td>100</td>
														<td>80</td>
														<td>20</td>
														<td>70</td>
														<td>20</td>
														<td>70</td>
													</tr>
													<tr>
														<td>Agent 2</td>
														<td>100</td>
														<td>80</td>
														<td>20</td>
														<td>70</td>
														<td>20</td>
														<td>70</td>
													</tr>
													<tr>
														<td>Agent 3</td>
														<td>100</td>
														<td>80</td>
														<td>20</td>
														<td>70</td>
														<td>20</td>
														<td>70</td>
													</tr>
													<tr>
														<td>Agent 4</td>
														<td>100</td>
														<td>80</td>
														<td>20</td>
														<td>70</td>
														<td>20</td>
														<td>70</td>
													</tr>
													<tr>
														<td>Agent 5</td>
														<td>100</td>
														<td>80</td>
														<td>20</td>
														<td>70</td>
														<td>20</td>
														<td>70</td>
													</tr>
												</tbody>
											</table>-->
										</div>
										<div role="tabpanel" class="tab-pane" id="participated">
											
										</div>
									  </div>
									</div>
								</div>
							</div>
						</div> 
					</div>  
				</div>
			</div>
		</div>
	</div>
</section>
<footer>
		<img src="css/Training/img/footer.jpg" width="100%">
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
	<!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
	<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/highcharts/js/highcharts_cadre.js"></script>
	<script type="text/javascript" src="js/TrainingProgram/callCenterTrainingAdmin.js"></script>
<!--
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>

<script src="dist/js/jquery.circliful.min.js" type="text/javascript"></script>

<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>

<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>  -->
<script type="text/javascript">
$(".table-scroll").mCustomScrollbar({
	setHeight:470,
	theme:"minimal-dark"
});
$(document).ready(function() {
  /* var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
	$('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }

  var optionSet1 = {
	startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
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
  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#reportrange').daterangepicker(optionSet1, cb);

  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
});

 */

   var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  }

  var optionSet1 = {
	startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	minDate: '01/01/2012',
	maxDate: '12/31/2015',
	//dateLimit: { days: 60 },
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary newsSubmitBtn',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
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

  var optionSet2 = {
	startDate: moment().subtract(7, 'days'),
	endDate: moment(),
	opens: 'left',
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
  };

  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#reportrange').daterangepicker(optionSet1, cb);

  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

  $('#options1').click(function() {
	$('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
  });

  $('#options2').click(function() {
	$('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
  });

  $('#destroy').click(function() {
	$('#reportrange').data('daterangepicker').remove();
  });
  
  	getAllUserIdsByUserType();
	getCallerWiseCallsDetails();
	getTrainingProgramMembersBatchCount();
	getScheduleAndConfirmationCallsOfCallerToAgent();
	getCampusWiseDateWiseInterestedMembersDetails();
	getCampusWiseBatchWiseMembersDetails('notStarted','scheduled');

});

</script>
<script>

	function getAllUserIdsByUserType(){
		
		$('.callerId').find('option').remove();
		
		$('.callerId').append('<option value="0">Select Caller</option>');
		
		$.ajax({
			type:'POST',
			 url: 'getUsersofUserTypeAction.action',
			 data : {task:JSON.stringify()} ,
			}).done(function(result){
				var str='';
				if(result !=null){
					for(var i in result){
						str+='<option val="'+result[i].id+'">'+result[i].name+'</option>';
					}
					
					$(".callerId").append(str);
				}
			});
		
	}
	
	function getCallerWiseCallsDetails(){
		
		$("#memberAvailabilityDivCls").html("");
		$("#statusWiseCountArraId").html("");
		$("#totalCallsPerCallerId").html("");
		$("#todayCallsPerCallerId").html("");
		$("#donutchartForStatus").html("");
		$("#circleForConfirmed").html("");
		//data loading images  showing for total counts of allocated callers
		$("#dataLoadingsImgForTotalCallerCount").show();
		$("#dataLoadingsImgForTodayCount").show();
		$("#dataLoadingsImgForDonutchartStatus").show();
		$("#dataLoadingsImgForCircleForConfirmed").show();
		
		setcolorsForTrainingchart();
		
		var fromDate=$(".dp_startDate").val();
		var toDate=$(".dp_endDate").val();
		
		var jsObj={
			searchType:"",
			 /* fromdate:"09/01/2015",
			toDate :"09/11/2015" */
			fromdate : fromDate,
			toDate   : toDate
		}
		$.ajax({
				type:'POST',
				 url: 'getCallerWiseCallsDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					//data loading images  hiding for total counts of allocated callers
					$("#dataLoadingsImgForTotalCallerCount").hide();
					$("#dataLoadingsImgForTodayCount").hide();
					$("#dataLoadingsImgForDonutchartStatus").hide();
					$("#dataLoadingsImgForCircleForConfirmed").hide();
					
					if(result !=null){ 
						$("#totalCallsPerCallerId").html(result.totalCount);
						$("#todayCallsPerCallerId").html(result.todayAllocatedCalls);
						buildingCallerWiseCallsDetails(result);
					}
					else{
						$("#memberAvailabilityDivCls").html("problem occured.please contact admin.");
					}
				});
		
	}
	function buildingCallerWiseCallsDetails(result){
		var str='';
		var str1='';
				if(result.trainingCampVOList !=null){
						str+='<table class="table table-bordered m_0" id="dataNotAvailableDiv">';
							str+='<thead >';
								str+='<th>Caller Name</th>';
								str+='<th>Assigned Calls</th>';
								str+='<th>Completed Calls</th>';
								str+='<th>Pending Calls</th>';
								
									if(result.trainingCampVOList[0].trainingCampVOList !=null){
									for(var i in result.trainingCampVOList[0].trainingCampVOList){
										str+='<th id='+result.trainingCampVOList[0].trainingCampVOList[i].statusId+'>'+result.trainingCampVOList[0].trainingCampVOList[i].status+'</th>';	 
									}
								 }
								
							str+='</thead>';
							str+='<tbody>';
							
							if(result.trainingCampScheduleVOList !=null && result.trainingCampScheduleVOList.length>0){
								buildingHighchartForStatus(result.trainingCampScheduleVOList);
								buildingStatusWiseCountForUpper(result.trainingCampScheduleVOList);
								buildingMembersFilledInCalenderBatch(result.trainingCampScheduleVOList);
							}
							else{
								$("#donutchartForStatus").html("<div style='margin-top:100px;text-align:center;'>Status Data Not Available.</div>");
								
								str1+='<div id="myStathalf"  class="text-center" data-info="0" data-dimension="150px" data-percent="0" data-fgcolor="blue" data-bgcolor="#cccccc" data-type="half" >';
										str1+='</div>';
									
									$("#circleForConfirmed").html(str1);
									$('#myStathalf').circliful();
							}
							
							if(result.trainingCampVOList !=null && result.trainingCampVOList.length>0){
								for(var i in result.trainingCampVOList){
									str+='<tr>';
									str+='<td id="'+result.trainingCampVOList[i].id+'">'+result.trainingCampVOList[i].name+'</td>';
									str+='<td>'+result.trainingCampVOList[i].assignedCallsCount+'</td>';
									str+='<td>'+result.trainingCampVOList[i].completedCallsCount+'</td>';
									str+='<td>'+result.trainingCampVOList[i].pendingCallsCount+'</td>';
									
									if(result.trainingCampVOList[i].trainingCampVOList !=null && result.trainingCampVOList[i].trainingCampVOList.length>0){
										for(var j in result.trainingCampVOList[i].trainingCampVOList){
											str+='<td>'+result.trainingCampVOList[i].trainingCampVOList[j].count+'</td>';
										}
									}
									str+='</tr>';
								}
							}
							else{
								str+='<tr>Data Not Available</tr>';
							}
								
							str+='</tbody>';
						str+='</table>';
					$(".memberAvailabilityDivCls").html(str);	
				}else{
					$(".memberAvailabilityDivCls").html("Data Not Available.");
					
					//highchart and circle filling
					
					$("#donutchartForStatus").html("<div style='margin-top:100px;text-align:center;'>Status Data Not Available.</div>");
								
					str1+='<div id="myStathalf"  class="text-center" data-info="0" data-dimension="150px" data-percent="0" data-fgcolor="blue" data-bgcolor="#cccccc" data-type="half" >';
						str1+='</div>';
					
					$("#circleForConfirmed").html(str1);
					$('#myStathalf').circliful();
				}
	}
	
	//balu chart
var trainingColorArr = [];
var statusArr = ['Invited','Pending','Not Now','Interested','Not Interested','Call Back - Busy','Call Back - Confirm Later','Wrong Mobile No','Invalid Mobile No','Confirmed'];  
function setcolorsForTrainingchart()
	{
		
		trainingColorArr = new Array();
		var colorStatic = new Array('#EE82EE', '#FF0000', '#D2B48C', '#40B5BF' , '#FA8072','#DDA0DD','#BC8F8F','#A9A9A9','#FDF5E6','#00FF00');
		
		var colorCount = 0;
		
		for(var i in statusArr)
		{
		 
				var obj = {
				 status : statusArr[i],
				 color : colorStatic[colorCount]
				}
				trainingColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return trainingColorArr;
	}
function getColorCodeByType(status)
	{

		if(trainingColorArr != null && trainingColorArr.length > 0)
		{
			for(var i in trainingColorArr)
			{
				if(trainingColorArr[i].status == status)
					return trainingColorArr[i].color;
			}
		}
	}
function buildingHighchartForStatus(result){
	var statusArray=[];
	var colorsArr = [];
	if(result !=null && result.length>0){
		for(var i in result){
			var data=new Array();
			var statusName = result[i].status;
			var count      = result[i].count;
			data.push(statusName,count);
			statusArray.push(data);
			colorsArr.push(getColorCodeByType(result[i].status));
		}
	}
	
	Highcharts.setOptions({
        colors: colorsArr
    });
    $('#donutchartForStatus').highcharts({
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
                innerSize: 80,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
			name : 'Count',
            data:statusArray
        }]
    });
}

function buildingMembersFilledInCalenderBatch(result){
	
	$("#circleForConfirmed").html("");
	var str='';
	if(result !=null){
		
		var statusPercentage=0;
		for(var i in result){
			statusPercentage=statusPercentage+result[i].count;
		}
		confirmedCount=0;
		for(var i in result){
			if(result[i].status == "Interested"){
				confirmedCount = result[i].count;
			}
		}
		//percentage Calculation
		var resultPerc = 0;
		if(confirmedCount !=null && confirmedCount !=0){
			resultPerc = parseInt((confirmedCount) * 100)/ parseInt(statusPercentage);
		}
		
		//assigning percentage to Circle
		for(var i in result){
			
			if(result[i].status == "Confirmed"){
				str+='<div id="myStathalf"  class="text-center" data-info="'+result[i].count+'" data-dimension="150px" data-percent="'+resultPerc+'" data-fgcolor="#00FF00" data-bgcolor="#cccccc" data-type="half" >';
				str+='</div>';
			}else{
				str+='<div id="myStathalf"  class="text-center" data-info="0" data-dimension="150px" data-percent="0" data-fgcolor="blue" data-bgcolor="#cccccc" data-type="half" >';
				str+='</div>';
			}
			
			$("#circleForConfirmed").html(str);
			$('#myStathalf').circliful();
		}
	}
	
}
	 //totalProgramsCountId totalCampsCountId 
	 //batchCountOfProgramId batchCountOfCampId
	
	function getTrainingProgramMembersBatchCount(){
		
		var fromDate=$(".dp_startDate").val();
		var toDate=$(".dp_endDate").val();
		
		var jsObj={
			fromdate:fromDate,
			toDate :toDate
		/* 	fromdate:"09/01/2015",
			toDate:"09/30/2015" */
		}
		$.ajax({
				type:'POST',
				 url: 'getTrainingProgramMembersBatchCountAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					var str='';
					if(result !=null){
						$("#totalProgramsCountId").html(result.programCount);
						$("#totalCampsCountId").html(result.campCount);
						
						if(result.trainingCampVOList !=null){
							buildingProgramMembersBatchCount(result);
						}
						else{
							$("#batchCountOfProgramId").html("Program Data  Not Available.")
						}
						if(result.trainingCampScheduleVOList !=null){
							buildingCampMembersBatchCount(result.trainingCampScheduleVOList);
						}
						else{
							$("#batchCountOfCampId").html("Camp Data Not Available");	
						}
						
					}
						
				});
		
	}
	function buildingProgramMembersBatchCount(result){
		
		var str='';
		if(result.trainingCampVOList !=null){
		str+='<table class="table table-hover table-bordered">';
										str+='<thead>';
											str+='<th>PROGRAM NAME</th>';
											str+='<th>MEMBERS</th>';
											str+='<th>BATCHES</th>';
										str+='</thead>';
										str+='<tbody>';
										for(var i in result.trainingCampVOList){
											str+='<tr>';
												str+='<td id="'+result.trainingCampVOList[i].id+'">'+result.trainingCampVOList[i].name+'</td>';
												str+='<td>'+result.trainingCampVOList[i].totalCount+'</td>';
												str+='<td>'+result.trainingCampVOList[i].count+'</td>';
											str+='</tr>';
										}	
									str+='</tbody>';
						str+='</table>';
			$("#batchCountOfProgramId").html(str);
			//scroll
			$(".count-hover-scroll").mCustomScrollbar({
				setHeight:140,
				theme:"minimal-dark"
			});
		}
	else{
		$("#batchCountOfProgramId").html("Program Data Not Available.");
	}
		
		
	}
	
	function buildingCampMembersBatchCount(result){
		var str='';
		if(result !=null){
				str+='<table class="table table-hover table-bordered">';
					str+='<thead>';
						str+='<th>PROGRAM NAME</th>';
						str+='<th>MEMBERS</th>';
						str+='<th>BATCHES</th>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td id="'+result[i].id+'">'+result[i].name+'</td>';
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].count+'</td>';
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
				
				$("#batchCountOfCampId").html(str);
				
				//scroll
				$(".count-hover-scroll").mCustomScrollbar({
				setHeight:140,
					theme:"minimal-dark"
				});
		}
		else{
			$("#batchCountOfCampId").html("Camp Data Not Availale");
		}
		
	}
	
	
	function getScheduleAndConfirmationCallsOfCallerToAgent(){
		
		$("#callPurposeCountDivId").html("");
		
		var fromDate=$(".dp_startDate").val();
		var toDate=$(".dp_endDate").val();
		
		var jsObj={
				fromdate:fromDate,
				toDate :toDate
				/* fromdate:"09/01/2015",
				toDate :"09/11/2015" */
		}
		$.ajax({
				type:'POST',
				 url: 'getScheduleAndConfirmationCallsOfCallerToAgentAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(results){
					var str='';
					if(results !=null && results.trainingCampScheduleVOList.length>0){
						var result = results.trainingCampScheduleVOList;
						str+='<table class="table table-bordered m_0">';
							str+='<tr>';
								str+='<td class="pad_5">';
									str+='<h4 class="display-style m_0"> Calls Assigned <br/> to Caller </h4>';
									str+='<span class="pull-right"><h2 class="m_0">'+result[0].totalAssignedCount+'</h2></span>';
								str+='</td>';
								str+='<td class="pad_5 text-yellow">';
									str+='<p class="m_0 font-10"> Caller <br/>Dialled</p>';
									str+='<h4 class="m_0 text-yellow">'+result[0].totalDialedCallsCount+'</h4>';
								str+='</td>';
							str+='</tr>';
							for(var i in result){
								str+='<tr>';
									str+='<td class="pad_5" id="'+result[i].id+'">'+result[i].name+'<span class="pull-right">'+result[i].count+'</span>';
									str+='</td>';
									str+='<td class="pad_5">';
										str+='<h4 class="m_0 text-yellow">'+result[i].dialedCallsCount+'</h4>';
									str+='</td>';
								str+='</tr>';
							}
						str+='</table>';
						
						$("#callPurposeCountDivId").html(str);
					}
					else{
						$("#callPurposeCountDivId").html("Some problem occured while processing data.Please contact Admin.")
					}
				});
		
	}
	
	//status Wise Count showing   
	function buildingStatusWiseCountForUpper(result){
		var str='';
		if(result !=null){
			str+='<table class="table table-bordered m_0">';
			for(var i in result){
				if(result[i].status == "Interested" || result[i].status == "Confirmed" || result[i].status == "Invited" ){
					str+='<tr>';
						str+='<td>';
						if(result[i].status == "Interested")
						{
							str+='<h4 class="m_bottom0 interested-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						else if(result[i].status == "Confirmed"){
							str+='<h4 class="m_bottom0 confirmed-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						else if(result[i].status == "Invited"){
							str+='<h4 class="m_bottom0 invited-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						else if(result[i].status == "Pending"){
							str+='<h4 class="m_bottom0 pending-text" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						}
						str+='</td>';
					str+='</tr>';
				}
					
			}
			str+='</table>';
			$("#statusWiseCountArraId").html(str);
		}
	}
	
	$(document).on("click",".searchTypeCls li",function(){
		 var text=$(this).text();
		 var searchType="";
		 var divId='';
		 if(text == "SCHEDULED"){
		 searchType = "notStarted";
		 divId ='scheduled';
		 }
		 else if(text == "RUNNING BATCH"){
		 searchType = "running";
		 divId ='running';
		 }else if(text == "COMPLETED BATCH"){
		 searchType = "completed";
		 divId ='completed';
		 }else if(text == "CANCELLED BATCH"){
		 searchType = "cancelled";
		 divId ='cancelled';
		 }
		 getCampusWiseBatchWiseMembersDetails(searchType,divId);
		 
	});
 
	$(document).on("click",".ranges li",function(){
		if($(this).text() == "Custom"){
			return;
		}
	
		getCallerWiseCallsDetails();
		getTrainingProgramMembersBatchCount();
		getScheduleAndConfirmationCallsOfCallerToAgent();
		getCampusWiseDateWiseInterestedMembersDetails();
		getCampusWiseBatchWiseMembersDetails('notStarted','scheduled');
	});	
	$(document).on("click",".newsSubmitBtn",function(){
		getCallerWiseCallsDetails();
		getTrainingProgramMembersBatchCount();
		getScheduleAndConfirmationCallsOfCallerToAgent();
		getCampusWiseDateWiseInterestedMembersDetails();
		getCampusWiseBatchWiseMembersDetails('notStarted','scheduled');
	})
	
	$("#mainheading").html("TRAINING PROGRAM");
</script>
<script>
</script>


</body>
</html>