<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BIOMETRIC - AP</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
.table-employeeAtten {
	border:none;
	border-spacing: 4px 0px;
}
.table-employeeAtten td,.table-employeeAtten th{
	border:none;
}
.table-employeeAtten tr th:nth-child(1){
	background-color:#FFFFFF;
}
.table-employeeAtten tr th:nth-child(n+2){
	background-color:#00D1B5;
	color:#FFFFFF;
}
.table-employeeAtten tr th:nth-child(n+6){
	border-top:2px solid  #898989;
	background-color:#EDEBEB;
	color:#00BD13;
}

.table-employeeAtten tr th:nth-child(2){
	border-top:2px solid #3FDCC7;
	border-left:2px solid #3FDCC7;
	border-top-left-radius:10px;
}
.table-employeeAtten tr td:nth-child(2){
	border-left:2px solid #3FDCC7;
}
.table-employeeAtten tr th:nth-child(5){
	border-top:2px solid #3FDCC7;
	border-right:2px solid #3FDCC7;
	border-top-right-radius:10px;
}
.table-employeeAtten tr td:nth-child(5){
	border-right:2px solid #3FDCC7;
}
.table-employeeAtten tr th:nth-child(6){
	border-top:2px solid #898989;
	border-left:2px solid #898989;
	border-top-left-radius:10px;
}
.table-employeeAtten tr td:nth-child(6){
	border-left:2px solid #898989;
}
.table-employeeAtten tr td:nth-child(9){
	border-right:2px solid #898989;
}
.table-employeeAtten tr:last-child td:nth-child(9){
	border-bottom:2px solid  #898989;
	border-right:2px solid #898989;
	border-bottom-right-radius:10px;
}
.table-employeeAtten tr:last-child th:nth-child(9){
	border-top:2px solid  #898989;
	border-right:2px solid #898989;
	border-top-right-radius:10px;
}
.table-employeeAtten tr:last-child td:nth-child(6){
	border-bottom:2px solid  #898989;
	border-left:2px solid #898989;
	border-bottom-left-radius:10px;
}
.table-employeeAtten tr:last-child td:nth-child(n+2){
	border-bottom:2px solid #3FDCC7;
	
}
.table-employeeAtten tr:last-child td:nth-child(n+6){
	border-bottom:2px solid  #898989;

}
.table-employeeAtten tr:last-child td:nth-child(2){
	border-bottom-left-radius:10px;
}
.table-employeeAtten tr:last-child td:nth-child(5){
	border-bottom-right-radius:10px;
}
</style>
</head>
<body>

<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayati Raj, RD & RWS</h4>
					<p>BIOMETRIC - AP</p>
				</div>
				
				<div class="col-sm-3 col-xs-12 pull-right">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<div class="row">
								<!--<div class="col-sm-12">
									<div class="menu-block" style="background-color:#FFBA00">
										<a href="newfundManagementDashboard">
											<h3>FMS</h3>
											<p>Fund Management System</p>
										</a>
									</div>
								</div>  -->
								<div class="col-sm-12">
									<div class="menu-heading-block">
										<h4 class="text-capital">Rural Water Supply</h4>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#56A3C5">
													<a href="ruralWaterSupplyDashBoard">
														<h3>RWS</h3>
														<p>Rural Water Supply</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1e92b2">
													<a href="swachhBharatMissionIHHL">
														<h3>IHHL</h3>
														<p>Swatch Bharat Mission</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>PANCHAYATI RAJ</h4>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#0F685C">
													<a href="prisDashBoard">
														<h3>PRIS</h3>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#31B8B7">
													<a href="drainDashBoard">
														<h3>DRAINS</h3>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
														<h3>ENC</h3>
														<p>Engineering Dept</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#512507">
													<a href="getdailySpikeReport">
														<h3>SA</h3>
														<p>Spike Analysis</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#888420">
													<a href="getlightsMonitoringDashboard">
														<h3>LED</h3>
														<p>Light Monitoring Dashboard</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="prExpenditureDashboard">
														<h3>PR EXP</h3>
														<p>Panchayat Raj <br/>Expenditure</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="waterTanksClorinationDashBoard">
														<h3>WTC</h3>
														<p>Water Tank Clorination</p>
													</a>
												</div>
											</div>
										</div>
										
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>RURAL DEVELOPMENT</h4>
										<div class="row">
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#88186B">
													<a href="MGNREGSDashboard">
														<h3>MGNREGS</h3>
														<p>Mahatma Gandhi Rural employement guarantee scheme</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>RD</h3>
														<p>Rural Development Dashboard</p>
													</a>
												</div>
											</div>
											
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#de4524 ">
										<a href="itcDashboard">
											<h3>IT E & C</h3>
											<p>Dashboard</p>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
</header>
<main>
	<div class="container-fluid">
		<div class="white-block" style="padding:10px;">
			<h4><span class="employeeSqCss"></span> <span class="employeeCss">EMP ATTENDANCE Overview </span></h4>
			<div class="row m_top10">
				<div class="col-sm-12">
					<div style="border:1px solid #ccc;padding:10px;border-radius:5px;">
							<div class="row">
								<div class="col-sm-12">
									<h5 class="pull-right todayBio">Today</h5>
								</div>
							</div>
							<div id="empOverViewBlockId"></div>
						
							<h5 class="font_weight m_top10" style="margin-left:15px">IN-TIME STATISTICS</h5>
							<div id="inTimeStatisticsDivId" style="height:180px;" class="m_top10"></div>
					</div>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-sm-12">
					<div style="border:1px solid #ccc;padding:10px;border-radius:5px;">
					   <h5 class="font_weight" id="daysReportHeadingId"></h5>
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group pull-right dateRangePickerCls">
									<input type="text" id="dateRangeId" style="width:190px" class="form-control"/>
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</div>
								</div>
							</div>
							<div id="lastDaysStatisticsDivId" style="height:250px;" class="m_top10"></div>
						
							<h5 class="font_weight m_top10">EMPLOYEE ATTENDANCE</h5>
							<div id="attandanceOverviewDetailsId" class="m_top10"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<div class="modal fade" id="openModalDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:95%">
		<div class="modal-content">
			<div class="modal-header">
				<div class="row" >
					<div class="col-md-8">
						<h4 class="theme-title-color text-capital font_weight" id="bioMetricTitleId">Title</h4>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">      
					<div class="row">
						<div class="col-sm-12">
							<div class="input-group pull-right dateRangePickerCls">
							<input type="text" id="dateRangePopupId" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						 </div>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<div id="empDetailsModalDivId"></div>
						</div>		
					</div>	
					<div class="row m_top20">
						<div class="col-sm-12">
							<div style="border:1px solid #ccc;padding:10px;border-radius:5px;">
								<h5 class="font_weight" style="margin-left:15px">IN-TIME STATISTICS</h5>
								<div id="empInTimeGraphModalDivId" style="height:180px;" class="m_top10"></div>
							</div>
						</div>		
					</div>	
					<div class="row m_top10">
						<div class="col-sm-12">
							<div style="border:1px solid #ccc;padding:10px;border-radius:5px;">
							 <h5 class="font_weight" id="individaualEmpdaysReportHeadingId"></h5>
								<div id="empLastDaysStatisticsDivId" style="height:250px;" class="m_top10"></div>
							</div>
						</div>		
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
  <div class="modal fade" id="empDetailsModalId" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document" style="width:85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title text-capital"><b id="employeeDetaislHeadingId">Employee Details</b></h4>
				</div>
				<div class="modal-body">
					<div id="employeeDetailsDivId"></div>
				</div>
			</div>
		</div>
	</div>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/bioMetric/bioMetric.js" type="text/javascript"></script>
 <script type = "text/javascript">
 </script>
</body>
</html>