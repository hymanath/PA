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
								<input type="text" class="form-control" id="CustomCalendar">
							</div>
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
													<h3 class="m_0">ALLOCATED CALLS TO CALLER - 1000</h3>
													<span class="pull-right font-12">Allocated Today - 100</span>
												</td>
											</tr>
											<tr>
												<td>
													<h2 class="m_0">800</h2>
													<p>Assigned <br/>to agents</p>
												</td>
												<td class="text-warning">
													<h2 class="m_0">800</h2>
													<p>Agents<br/>dialled calls</p>
												</td>
												<td class="pad_0">
													<table class="table table-bordered m_0">
														<tr>
															<td class="text-custom">600 - interested</td>
														</tr>
														<tr>
															<td class="text-custom">80 - interested in next time</td>
														</tr>
														<tr>
															<td class="text-danger">20 - totally not interested</td>
														</tr>
													</table>
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
											<li>TRAINING <br/>PROGRAMS</li>
											<li class="show-dropdown">
												<div class="font-30">02</div>
												<ul class="count-hover up-arrow">
													<li>
														<div class="count-hover-scroll">
															<table class="table table-hover">
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
															</table>
														</div>
													</li>
												</ul>
											</li>
										</ul>
									</td>
									<td>
										<ul class="list-inline m_bottom0">
											<li>TRAINING <br/>CAMPS</li>
											<li class="show-dropdown">
												<div class="font-30">02</div>
												<ul class="count-hover up-arrow">
													<li>
														<table class="table table-hover">
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
														</table>
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
							<table class="table table-bordered">
								<thead class="bg_d">
									<th>TRAINING CAMP<br/> NAME</th>
									<th>CALENDAR <br/>DATES</th>
									<th>INTERESTED <br/> MEMBERS</th>
									<th>INTERESTED IN NEW <br/> CALENDAR DATES</th>
									<th>NOT <br/>INTERESTED</th>
									<th>ASSIGNED TO <br/> TRAINING BATCHES</th>
									<th>AVAILABLE MEMBERS IN <br/>CALENDAR DATES</th>
								</thead>
								<tbody>
									<tr>
										<td>SVV Batch Campus</td>
										<td>Sep_2015_01 to 15</td>
										<td>300</td>
										<td>300</td>
										<td>200</td>
										<td>200</td>
										<td>100</td>
									</tr>
									<tr>
										<td>EWK Batch Campus</td>
										<td>Sep_2015_01 to 15</td>
										<td>300</td>
										<td>300</td>
										<td>200</td>
										<td>200</td>
										<td>100</td>
									</tr>
									<tr>
										<td>GPN Batch Campus</td>
										<td>Jan_2016_01 to 15</td>
										<td>300</td>
										<td>300</td>
										<td>200</td>
										<td>200</td>
										<td>100</td>
									</tr>
									<tr>
										<td>AKKC Batch Campus</td>
										<td>Jan_2016_15 to 30</td>
										<td>300</td>
										<td>300</td>
										<td>200</td>
										<td>200</td>
										<td>100</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="assign_box">
								<input type="checkbox" style="margin-top:-16px;" class="checkbox-inline">
								<label>Assign Members <br/>to check availability</label>
								<input type="checkbox" style="margin-top:-16px;" class="checkbox-inline">
								<label>Assign Members<br/>For acceptance	</label>
								<h5>Select Camp</h5>
								<select class="form-control">
									<option>SVV Batch Camp</option>
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
								<table class="table table-bordered">
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
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top20">
							<div class="panel panel-default">
								<div class="panel-heading bg_d">
									<h4 class="panel-title">
											PLANNED PROGRAM DETAILS
									</h4>
								</div>
								<div class="panel-body pad_0">
									<div class="panel-group m_0" id="accordion" role="tablist" aria-multiselectable="true">
									  <div class="panel panel-default">
										<div class="panel-heading  bg_e9" role="tab" id="headingOne">
										  <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
											<h4 class="panel-title text-bold">
											 Leadership Skills
											  <span class="pull-right">
												<span class="text-warning font-12">180<span class="text-italic"> members ready for training b/w 01-15 dates</span></span>
												<i class="glyphicon glyphicon-chevron-up"></i>
											  </span>
											</h4>
										  </a>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
										  <div class="panel-body  pad_5">
											<table class="table table-bordered m_0">
												<tr>
													<td>BATCH NAME</td>
													<td>MEMBERS ACCEPTED</td>
													<td>TRAINING DATE</td>
													<td>BATCH TRAINER</td>
												</tr>
												<tr>
													<td>SVV_Batch_201503</td>
													<td>100</td>
													<td>Aug_2015_1 to 3</td>
													<td>Trainer name</td>
												</tr>
												<tr>
													<td>EWK_Batch_201503</td>
													<td>100</td>
													<td>Aug_2015_1 to 3</td>
													<td>Trainer name</td>
												</tr>
												<tr>
													<td>GPN_Batch_201503</td>
													<td>100</td>
													<td>Aug_2015_1 to 3</td>
													<td>Trainer name</td>
												</tr>
											</table>
										  </div>
										</div>
									  </div>
									  <div class="panel panel-default">
										<div class="panel-heading bg_e9" role="tab" id="headingTwo">
											<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
												<h4 class="panel-title text-bold">
													Election Year
												  <span class="pull-right">
													<span class="text-warning font-12">180<span class="text-italic"> members ready for training b/w 01-15 dates</span></span>
													<i class="glyphicon glyphicon-chevron-up"></i>
												  </span>
												</h4>
											</a>
										</div>
										<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
										  <div class="panel-body" style="padding-bottom:0px;">
											
										  </div>
										</div>
									  </div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading pad_5 pad_bottom0">
									<ul class="nav nav-tabs tab-list display-style" role="tablist">
										<li class="active"><a href="#area" class="text-bold" data-toggle="tab">AGENTS CALL DATA PROGRESS - MEMBERS AVAILABILITY</a></li>
										<li><a href="#participated" class="text-bold" data-toggle="tab">MEMBERS ACCEPTANCE</a></li>
										<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span>
									</ul>
								</div>
								<div class="panel-body pad_0">
									<div>
									  <!-- Nav tabs -->
									  
									  <!-- Tab panes -->
									  <div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="area">
											<table class="table table-bordered m_0">
												<thead>
													<th>Agent Name</th>
													<th>Assigned Calls</th>
													<th>Completed Calls</th>
													<th>Pending Calls</th>
													<th>Interested Members</th>
													<th>Interested in next time</th>
													<th>Totally not interested</th>
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
											</table>
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
	setHeight:350,
	theme:"minimal-dark"
});
$(document).ready(function() {
  $('.CustomCalendar').daterangepicker(null, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  });
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
getCallerWiseCallsDetails();
function getCallerWiseCallsDetails(){
	
	var jsObj={
		searchType:"",
		fromdate:"08/02/2015",
		toDate :"08/05/2015"
	}
	$.ajax({
			type:'POST',
			 url: 'getCallerWiseCallsDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					
			});
	
}
getTrainingProgramMembersBatchCount();
function getTrainingProgramMembersBatchCount(){
	
	var jsObj={
		fromdate:"09/01/2015",
		toDate :"09/30/2015"
	}
	$.ajax({
			type:'POST',
			 url: 'getTrainingProgramMembersBatchCountAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					
			});
	
}

</script>
</body>
</html>