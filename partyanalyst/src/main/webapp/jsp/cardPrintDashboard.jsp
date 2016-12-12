<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cards Print Dashboard</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/dropkick.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<style>

.slick-prev {
	margin-left : 35px !important;
}
.slick-next{
	margin-right:35px !important;
}
.printDataReadyColor {
    background-color: #B091BB;

    display: inline-block;
    height: 10px;
    width: 15px;
}
.printPending {
    background-color: #9CCBCC;

    display: inline-block;
    height: 10px;
    width: 15px;
}
.printCompleted {
    background-color: #C8CA92;

    display: inline-block;
    height: 10px;
    width: 15px;
}
.printPassed {
    background-color: #71F0CC;

    display: inline-block;
    height: 10px;
    width: 15px;
}
.printFailed {
    background-color: #FF6F9B;

    display: inline-block;
    height: 10px;
    width: 15px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3 col-md-offset-3 col-sm-offset-3 col-xs-offset-0">
				<label class="radio-inline">
					<input type="radio" name="radio" class="stateClass" value="1"/>AP
				</label>
				<label class="radio-inline">
					<input type="radio" name="radio" class="stateClass" value="36"/>TS
				</label>
				<label class="radio-inline">
					<input type="radio" name="radio" checked class="stateClass" value="0"/>ALL
				</label>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
					<s:select theme="simple" cssClass="select" id="vendorId" list="vendorList" listKey="id" listValue="name"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control singleDate"/>
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title"><b>DISPATCH OF SMART CARD</b></h4>
					</div>
					<div class="panel-body">
						<div class="row" id="statusWisePrintingDiv">
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title text-capital"><b>today (<span id="todayHeadingId"></span>) complete print overview</b></h4>
					</div>
					<div class="panel-body">
						<div class="row" id="todayStatusWisePrintingDiv">
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital"><b>district wise contituencies cards print & dispatch status overview</b></h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h4>Andhra Pradesh</h4>
									</div>
									<div class="col-md-9 col-xs-12 col-sm-3">
										<ul class="list-inline">
											
											<li ><span class="printDataReadyColor"></span> Print Data Ready</li>
											<li class=""><span class="printPending"></span> Print Pending</li>
											<li class=""><span class="printCompleted"></span> Print Completed</li>
											<li class=""><span class="printPassed"></span> QA Passed</li>
											<li class=""><span class="printFailed"></span> QA Failed</li>
										</ul>
									</div>
								</div>
								
								<div id="distWiseCards" ></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h4>Telangana</h4>
									</div>
									<div class="col-md-9 col-xs-12 col-sm-3">
										<ul class="list-inline">
											
											<li ><span class="printDataReadyColor"></span> Print Data Ready</li>
											<li class=""><span class="printPending"></span> Print Pending</li>
											<li class=""><span class="printCompleted"></span> Print Completed</li>
											<li class=""><span class="printPassed"></span> QA Passed</li>
											<li class=""><span class="printFailed"></span> QA Failed</li>
										</ul>
									</div>
								</div>
								
								<div id="distWiseCards1" ></div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-md-4 col-xs-12 col-sm-4">
						<table class="table bg_ff tablePrinting">
							<tr>
								<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
							</tr>
							<tr>
								<td>&ge; 1 Day</td>
								<td>04</td>
							</tr>
							<tr>
								<td>&ge; 2 Day</td>
								<td>01</td>
							</tr>
							<tr>
								<td>&ge; 3 Day</td>
								<td>5</td>
							</tr>
							<tr>
								<td>&ge; 5 Day</td>
								<td>10</td>
							</tr>
							<tr>
								<td>> 10 Day</td>
								<td>-</td>
							</tr>
						</table>
					</div>
					<div class="col-md-4 col-xs-12 col-sm-4">
						<table class="table bg_ff tablePrinting">
							<tr>
								<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
							</tr>
							<tr>
								<td>&ge; 1 Day</td>
								<td>04</td>
							</tr>
							<tr>
								<td>&ge; 2 Day</td>
								<td>01</td>
							</tr>
							<tr>
								<td>&ge; 3 Day</td>
								<td>5</td>
							</tr>
							<tr>
								<td>&ge; 5 Day</td>
								<td>10</td>
							</tr>
							<tr>
								<td>> 10 Day</td>
								<td>-</td>
							</tr>
						</table>
					</div>
					<div class="col-md-4 col-xs-12 col-sm-4">
						<table class="table bg_ff tablePrinting">
							<tr>
								<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
							</tr>
							<tr>
								<td>&ge; 1 Day</td>
								<td>04</td>
							</tr>
							<tr>
								<td>&ge; 2 Day</td>
								<td>01</td>
							</tr>
							<tr>
								<td>&ge; 3 Day</td>
								<td>5</td>
							</tr>
							<tr>
								<td>&ge; 5 Day</td>
								<td>10</td>
							</tr>
							<tr>
								<td>> 10 Day</td>
								<td>-</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title text-capital">constituencies wise cards print status</h4>
					</div>
					<div class="panel-body" style="padding: 1px;">
						<div class=" bg_EE" style="padding: 10px;">
							<div class="row">
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<table class="table bg_ff">
									<thead class="text-capital">
										<th>constituency name</th>
										<th>print data ready</th>
										<th>cards print status</th>
										<th>QA status</th>
										<th>Dispatched</th>
									</thead>
									<tr>
										<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
									</tr>
									<tr>
										<td>&ge; 1 Day</td>
										<td>04</td>
									</tr>
									<tr>
										<td>&ge; 2 Day</td>
										<td>01</td>
									</tr>
									<tr>
										<td>&ge; 3 Day</td>
										<td>5</td>
									</tr>
									<tr>
										<td>&ge; 5 Day</td>
										<td>10</td>
									</tr>
									<tr>
										<td>> 10 Day</td>
										<td>-</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title text-capital">vendor wise print overview</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<table class="table table-bordered" style="border: 0px;">
									<tr>
										<td><h4>Vendor - 1</h4></td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Total Avg Per Day Print Capacity</small>
											<h4>10000</h4>
											<span class="text-success">View Day Wise</span>
										</td>
									</tr>
									<tr>
										<td class="bg_EE pos_relative"><div class="arrowTOp">Today <br/><small>17 Nov 2016</small></div></td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
									</tr>
									<tr>
										<td>Vendor - 1</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Total Avg Per Day Print Capacity</small>
											<h4>10000</h4>
											<span class="text-success">View Day Wise</span>
										</td>
									</tr>
									<tr>
										<td class="bg_EE pos_relative"><div class="arrowTOp">Today <br/><small>17 Nov 2016</small></div></td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="js/CardPrint/cardPrintDashboard.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();
$(".singleDate").daterangepicker({
			opens: 'left',
			locale: {
			  format: 'MM/DD/YYYY'
			},
			
		});
$(document).ready(function(){
	getStatusWisePrintingConstituencyDetails();
	getDistrictWiseStatusWiseConstituenciesCounts();
});
if($(window).width() < 500)
{
	$("table").wrap( "<div class='table-responsive'></div>" );
}
</script>
</body>
</html>
