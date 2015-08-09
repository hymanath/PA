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
<body>
<header>
	<img src="css/Training/img/header.jpg" width="100%">
</header>
<section>
	<div class="container">
		<div class="col-md-12">
			<div class="panel panel-default panel-custom">
				<div class="panel-heading">
					<h4 class="panel-title">CALL CENTER ADMIN DASHBOARD
						<span class="pull-right col-md-3" style="margin-top:-8px">
							<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
									<span class="caret"></span>
								</span>
								<input type="text" class="form-control" id="reportrange">
							</div>
						</span>
                    	<span class="pull-right">
                        	<label class="checkbox-inline">
                            	<input type="checkbox"> Planned
                            </label>
                            <label class="checkbox-inline">
                            	<input type="checkbox">All
                            </label>
                        </span>
					</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-8">
							<table class="table table-bordered">
								<tr>
									<td>
										<div id="donutchart" class="display-style" style="height: 160px;float:left;width:190px;"></div>
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
													<!--<table class="table table-bordered m_0">
                                                    	<tr>
                                                        	<td class="pad_5">
                                                            	<h4 class="display-style m_0">Calls Assigned <br/>to agents</h4>
                                                                <span class="pull-right"><h2 class="m_0">800</h2></span>
                                                            </td>
                                                            <td class="pad_5 text-yellow">
                                                            	<p class="m_0 font-10">Agents <br/>Dialled</p>
                                                                <h4 class="m_0 text-yellow">700</h4>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                        	<td class="pad_5">
                                                                	Batch Scheduled Calls
                                                                    <span class="pull-right">-500</span>
                                                            </td>
                                                            <td class="pad_5">
                                                            	<h4 class="m_0 text-yellow">400</h4>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                        	<td class="pad_5">
                                                                	Batch Conformation Calls
                                                                    <span class="pull-right">-300</span>
                                                            </td>
                                                            <td class="pad_5">
                                                            	<h4 class="m_0 text-yellow">300</h4>
                                                            </td>
                                                        </tr>
                                                    </table>  -->
												</td>
												<td class="pad_0" id="statusWiseCountArraId">
												<center><img id="dataLoadingsImgForStatus" src="images/icons/loading.gif" style="width: 25px; height: 25px;margin_top:30px;"/></center>
													<!--<table class="table table-bordered m_0">
														<tr>
															<td>
                                                            	<h4 class="m_bottom0 text-custom">600 - interested</h4>
                                                            </td>
														</tr>
														
													</table> -->
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
															<!--<table class="table table-hover">
																<thead>
																	<th>PROGRAM NAME</th>
																	<th>MEMBERS</th>
																	<th>BATCHES</th>
																</thead>
																<tbody>
																	<tr>
																		<td>Leadership Skills</td>
																		<td>300</td>
																		<td>02</td>
																	</tr>
																	<tr>
																		<td>Election Year</td>
																		<td>300</td>
																		<td>02</td>
																	</tr>
																</tbody>
															</table> -->
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
														<!--<table class="table table-hover">
															<thead>
																<th>PROGRAM NAME</th>
																<th>MEMBERS</th>
																<th>BATCHES</th>
															</thead>
															<tbody>
																<tr>
																	<td>Leadership Skills</td>
																	<td>300</td>
																	<td>02</td>
																</tr>
																<tr>
																	<td>Election Year</td>
																	<td>300</td>
																	<td>02</td>
																</tr>
															</tbody>
														</table> -->
													</div>
													</li>
												</ul>
											</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="myStathalf"  class="text-center" data-info="600" data-dimension="150px" data-percent="35" data-fgcolor="#40b6c0" data-bgcolor="#cccccc" data-type="half" ></div>
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
									<ul class="nav nav-tabs tab-list-sch" role="tablist">
										<li class="active"><a href="#scheduled" class="text-bold" data-toggle="tab">SCHEDULED</a></li>
										<li><a href="#running" class="text-bold" data-toggle="tab">RUNNING BATCH</a></li>
										<li><a href="#completed" class="text-bold" data-toggle="tab">COMPLETED BATCH</a></li>
										<li><a href="#cancelled" class="text-bold" data-toggle="tab">CANCELLED BATCH</a></li>
                                        <li class="col-md-3 col-md-offset-1">
                                        	<div>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-calendar"></i>
                                                        <span class="caret"></span>
                                                    </span>
                                                    <input type="text" class="form-control" id="CustomCalendar1">
                                                </div>
                                            </div>
                                        </li>
									</ul>
								</div>
								<div class="panel-body pad_0">
									<div>
									  <!-- Tab panes -->
									  <div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="scheduled">
											<table class="table table-bordered m_0">
												<thead>
													<th>TRAINING CAMP <br/> NAME</th>
													<th>TRAINING PROGRAM <br/> NAME</th>
													<th>SCHEDULED <br/> CALENDAR DATES</th>
													<th>INTERESTED <br/> MEMBERS</th>
													<th>LATER</th>
													<th>NOT <br/> INTERESTED</th>
													<th>ASSIGNED TO <br/> <span class="font-12">BATCH CONFORMATION</span> </th>
                                                    <th>AVAILABLE MEMBERS <br/> IN CALENDAR DATES</th>
												</thead>
												<tbody>
                                                	<tr>
                                                    	<td>SVV Batch Campus</td>
                                                        <td rowspan="4">Leadership Skills</td>
                                                        <td>Sep_2015_1 to 15</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>200</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>EWK Batch Campus</td>
                                                        <td>Sep_2015_1 to 15</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>200</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>GPN Batch Campus</td>
                                                        <td>Sep_2015_1 to 15</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>200</td>
                                                        <td>100</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>AKKC Batch Campus</td>
                                                        <td>Sep_2015_1 to 15</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>300</td>
                                                        <td>200</td>
                                                        <td>100</td>
                                                    </tr>
												</tbody>
											</table>
										</div>
										<div role="tabpanel" class="tab-pane" id="running">
											<table class="table table-bordered m_0">
                                                        <thead>
                                                            <th>Program Name</th>
                                                            <th>Training Camp Name</th>
                                                            <th>Schedule</th>
                                                            <th>Batch Name & Date</th>
                                                            <th>Batch Members</th>
                                                            <th>Absent</th>
                                                        </thead>
                                                        <tbody>
                                                        	<tr>
                                                            	<td rowspan="2">Leadership Skills</td>
                                                                <td>SVV Camp</td>
                                                                <td>SVV_Sep_01 to 15</td>
                                                                <td>SVV Sep-1<p class="m_0 font-10">sep-1-15 to sep-2-15</p></td>
                                                                <td>200</td>
                                                                <td>2</td>
                                                            </tr>
                                                            <tr>
                                                                <td>EWK Camp</td>
                                                                <td>SVV_Sep_01 to 15</td>
                                                                <td>SVV Sep-1<p class="m_0 font-10">sep-1-15 to sep-2-15</p></td>
                                                                <td>200</td>
                                                                <td>270</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="completed">
                                                    <table class="table table-bordered m_0">
                                                        <thead>
                                                            <th>Program Name</th>
                                                            <th>Training Camp Name</th>
                                                            <th>Schedule</th>
                                                            <th>Batch Name & Date</th>
                                                            <th>Completed</th>
                                                        </thead>
                                                        <tbody>
                                                        	<tr>
                                                            	<td rowspan="2">Leadership Skills</td>
                                                                <td>SVV Camp</td>
                                                                <td>SVV_Sep_01 to 15</td>
                                                                <td>SVV Sep-1<p class="m_0 font-10">sep-1-15 to sep-2-15</p></td>
                                                                <td>200</td>
                                                            </tr>
                                                            <tr>
                                                                <td>EWK Camp</td>
                                                                <td>SVV_Sep_01 to 15</td>
                                                                <td>SVV Sep-1<p class="m_0 font-10">sep-1-15 to sep-2-15</p></td>
                                                                <td>200</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="cancelled">
                                                    <table class="table table-bordered m_0">
                                                        <thead>
                                                            <th>Program Name</th>
                                                            <th>Training Camp Name</th>
                                                            <th>Schedule</th>
                                                            <th>Batch Name & Date</th>
                                                            <th>Status</th>
                                                        </thead>
                                                        <tbody>
                                                        	<tr>
                                                            	<td rowspan="2">Leadership Skills</td>
                                                                <td>SVV Camp</td>
                                                                <td>SVV_Sep_01 to 15</td>
                                                                <td>SVV Sep-1<p class="m_0 font-10">sep-1-15 to sep-2-15</p></td>
                                                                <td>Cancelled</td>
                                                            </tr>
                                                            <tr>
                                                                <td>EWK Camp</td>
                                                                <td>SVV_Sep_01 to 15</td>
                                                                <td>SVV Sep-1<p class="m_0 font-10">sep-1-15 to sep-2-15</p></td>
                                                                <td>Postponed</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
										</div>
									  </div>
									</div>
								</div>
							</div>
                        </div>
                    </div>
					<div class="row">
						<div class="col-md-4">
							<div class="assign_box">
								<label class="checkbox-inline font-10">
                                	<input type="checkbox">
                                    Assign Members <br/>to check availability
                                </label>
                                <label class="checkbox-inline font-10">
                                	<input type="checkbox">
                                    Assign Members<br/>For acceptance
                                </label>
								<h5>Select Camp</h5>
								<select class="form-control">
									<option>SVV Batch Camp</option>
								</select>
                                <h5>Select Program Name</h5>
								<input class="form-control" placeholder="Leasdership Skills">
                                <h5>Select Calendar Schedule Dates</h5>
								<select class="form-control">
									<option>Sep-2015_1 to 15</option>
								</select>
								<h5 class="m_top20">
									Select no of calls
									<span class="text-danger pull-right text-italic">Avail Calls 500</span>
								</h5>
								<input class="form-control" type="text">
								<h5>Select Call Center Agent Name</h5>
								<select class="form-control">
									<option>Harish</option>
								</select>
								<button class="btn btn-success btn-block m_top20">ASSIGN TO AGENT</button>
							</div>
						</div>
						<div class="col-md-8">
							<div class="table-scroll">
								<table class="table table-bordered m_0">
									<thead class="bg_d">
										<th>TRAINING CAMP<br/> NAME</th>
										<th>TRAINING <br/> DATE</th>
										<th>TRAINING <br/> PROGRAM NAME</th>
										<th>MEMBERS <br/>ACCEPTED</th>
									</thead>
									<tbody>
										<tr>
											<td>SVV Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td rowspan="4">Leadership Skills</td>
											<td>100</td>
										</tr>
										<tr>
											<td>EWK Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>GPN Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>SVV Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td rowspan="4">Election Year</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
                                        <tr>
											<td>AKKC Batch Campus</td>
											<td>Sep_2015_01 to 3</td>
											<td>100</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 m_top20">
							<div class="panel panel-default">
								<div class="panel-heading pad_5 pad_bottom0">
									<ul class="nav nav-tabs tab-list" role="tablist">
										<li class="active"><a href="#area" class="text-bold" data-toggle="tab">AGENTS CALL DATA PROGRESS - MEMBERS AVAILABILITY</a></li>
										<li><a href="#participated" class="text-bold" data-toggle="tab">MEMBERS ACCEPTANCE</a></li>
									</ul>
								</div>
								<div class="panel-body pad_0">
									<div>
									  <div class="tab-content">
										<div role="tabpanel" class="tab-pane active memberAvailabilityDivCls" id="area">
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
$('#myStathalf').circliful();
$(".count-hover-scroll").mCustomScrollbar({
	setHeight:140,
	theme:"minimal-dark"
});
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
  
	getCallerWiseCallsDetails();
	getTrainingProgramMembersBatchCount();
	getScheduleAndConfirmationCallsOfCallerToAgent();

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
                innerSize: 80,
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

</script>
<script>
	
	function getCallerWiseCallsDetails(){
		
		$("#memberAvailabilityDivCls").html("");
		$("#statusWiseCountArraId").html("");
		$("#totalCallsPerCallerId").html("");
		$("#todayCallsPerCallerId").html("");
		
		var fromDate=$(".dp_startDate").val();
		var toDate=$(".dp_endDate").val();
		
		var jsObj={
			searchType:"",
			/* fromdate:"08/02/2015",
			toDate :"08/05/2015" */
			fromdate : fromDate,
			toDate   : toDate
		}
		$.ajax({
				type:'POST',
				 url: 'getCallerWiseCallsDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
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
				if(result.trainingCampVOList !=null){
						str+='<table class="table table-bordered m_0" id="dataNotAvailableDiv">';
							str+='<thead >';
								str+='<th>Agent Name</th>';
								str+='<th>Assigned Calls</th>';
								str+='<th>Completed Calls</th>';
								str+='<th>Pending Calls</th>';
								
									if(result.trainingCampVOList[0].trainingCampVOList !=null){
									for(var i in result.trainingCampVOList[0].trainingCampVOList){
										str+='<th class="font-12" id='+result.trainingCampVOList[0].trainingCampVOList[i].statusId+'>'+result.trainingCampVOList[0].trainingCampVOList[i].status+'</th>';	 
									}
								 }
								
							str+='</thead>';
							str+='<tbody>';
							
							if(result.trainingCampScheduleVOList !=null && result.trainingCampScheduleVOList.length>0){
								buildingStatusWiseCountForUpper(result.trainingCampScheduleVOList);
							}
							
							if(result.trainingCampVOList !=null && result.trainingCampVOList.length>0){
								for(var i in result.trainingCampVOList){
									str+='<tr>';
									str+='<td id="'+result.trainingCampVOList[i].id+'">'+result.trainingCampVOList[i].id+'</td>';
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
			/* fromdate:"09/01/2015",
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
		str+='<table class="table table-hover">';
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
		}
	else{
		$("#batchCountOfProgramId").html("Program Data Not Available.");
	}
		
		
	}
	
	function buildingCampMembersBatchCount(result){
		var str='';
		if(result !=null){
				str+='<table class="table table-hover">';
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
									str+='<h4 class="display-style m_0">Calls Assigned <br/>to agents</h4>';
									str+='<span class="pull-right"><h2 class="m_0">'+result[0].totalAssignedCount+'</h2></span>';
								str+='</td>';
								str+='<td class="pad_5 text-yellow">';
									str+='<p class="m_0 font-10">Agents <br/>Dialled</p>';
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
			for(var i in result){
				str+='<table class="table table-bordered m_0">';
					str+='<tr>';
						str+='<td>';
							str+='<h4 class="m_bottom0 text-custom" id='+result[i].statusId+'>'+result[i].count+' - '+result[i].status+'</h4>';
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			}
			$("#statusWiseCountArraId").html(str);
		}
	}
	
	
	
	$(document).on("click",".ranges li",function(){
		getCallerWiseCallsDetails();
		getTrainingProgramMembersBatchCount();
		getScheduleAndConfirmationCallsOfCallerToAgent();
	});	
	$(document).on("click",".newsSubmitBtn",function(){
		getCallerWiseCallsDetails();
		getTrainingProgramMembersBatchCount();
		getScheduleAndConfirmationCallsOfCallerToAgent();
	})

</script>


</body>
</html>