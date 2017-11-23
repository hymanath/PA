<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Swatch Bharat Mission IHHL</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style type="text/css">
.DTFC_LeftBodyWrapper
{
	top:-13px !important;
}
.DTFC_LeftBodyWrapper tr td
{
	background-color:#fff;
}
.sacnotsacClass
{
	background-color:green !important;
	color:#fff;
}
.notstartinprogresscomClass
{
	background-color:brown !important;
	color:#fff;
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
					<p>Swatch Bharat Mission IHHL</p>
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
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#56A3C5">
													<a href="ruralWaterSupplyDashBoard">
														<h3>RWS</h3>
														<p>Rural Water Supply</p>
													</a>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1e92b2">
													<a href="swachhBharatMissionIHHL">
														<h3>IHHL</h3>
														<p>Swatch Bharat Mission</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="waterTanksClorinationDashBoard">
														<h3>WTC</h3>
														<p>Water Tank chlorination</p>
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
											<!-- <div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
														<h3>ENC</h3>
														<p>Engineering Dept</p>
													</a>
												</div>
											</div>-->
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
														<p>Light Monitoring</p>
													</a>
												</div>
											</div>
											<!-- <div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="prExpenditureDashboard">
														<h3>PR EXP</h3>
														<p>Panchayat Raj <br/>Expenditure</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="waterTanksClorinationDashBoard">
														<h3>WTC</h3>
														<p>Water Tank Clorination</p>
													</a>
												</div>
											</div>-->
										</div>
										<div class="row">
  											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="solidWasteManagementDashboard">
														<h3>SWM</h3>
                           								 <p>Solid Waste Management</p>
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
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 border_right">
					<!--Note: All Amount in Lakhs-->
				</div>
				
				<div class="col-sm-6">
					<ul class="list-inline pull-right">
						<li><span class="categoryRondedCss" style="background-color:#009587">A</span> >=&nbsp 80% </li>
						<li><span class="categoryRondedCss" style="background-color:#99B95F">B</span>  60% TO <&nbsp80%</li>
						<li><span class="categoryRondedCss" style="background-color:#E67401">C</span>  40% TO <&nbsp60%</li>
						<li><span class="categoryRondedCss" style="background-color:#FD403A">D</span>&nbsp<&nbsp40%</li>
					</ul>
				</div>
				<div class="col-sm-3">
					<div class="input-group pull-right">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
						<input type="text" class="form-control" id="dateRangePickerAUM" style="width: 200px;"/>
					</div>
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<div class="container-fluid">
		<div class="white-block">
			<div class="row">
				<div class="col-sm-12">
				  <h3>&nbsp&nbsp&nbspOVERALL PROGRESS DETAILS UP TO 31 MARCH&nbsp&nbsp<span><i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:green;" class="glyphicon glyphicon-info-sign tooltipCls"  data-toggle="tooltip" data-placement="top" title="Total Status Up To 31'st March which includes before OCT 1'st completed works."></i></span><h4>
					<div class="col-sm-3 m_top10">
						<div id="overAllIHHLPerformanceId" style="height:280px;border:1px solid #ccc"></div>
					</div>
					<div class="col-sm-9 m_top10">
						<div id="statusWiseIHHLPerformanceId" style="height:280px;border:1px solid #ccc;margin-left: -5px;"></div>
					</div>
				</div>
			</div>
			<div class="row m_top20"  style="padding: 11px; margin-left: 0px; margin-right: 0px;">
				<div class="col-sm-12" style="border: 1px solid #ccc;">
					<h5 class="m_top10"><b>CATEGORY WISE ANALYSIS&nbsp&nbsp<span><i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:green;" class="glyphicon glyphicon-info-sign tooltipCls"  data-toggle="tooltip" data-placement="top" title="Total Status Up To 31'st March which includes before OCT 1'st completed works."></i></span></b>  <span class="pull-right">Note : D - DISTRICT , C - CONSTITUENCY , M - MANDAL</span></h5>
					<div id="categoryWiseDataId"></div>
				</div>
				<div class="col-sm-12" style="border: 1px solid #ccc;">
				<h5 class="m_top10"><b>CATEGORY WISE ANALYSIS&nbsp&nbsp(<span style="color:green;" id="dailyCategoryWiseAnalysisHeadinId" ></span>)</h5>
					<div id="selectedDatecategoryWiseDataId"></div>
				</div>
			</div>
			<div class="row m_top20"  style="padding: 11px; margin-left: 0px; margin-right: 0px;">
				<div class="col-sm-12" style="border: 1px solid #ccc;">
					<h5 class="m_top10"><b>IHHL Achivement Progress</b></h5>
					<div class="row">
							<ul class="list-inline pull-right calendar_active_IHHL_cls ihhlAchivementProgressCls" attr_level_type="graph">
								<li class="defaultActiveClsDay commpnliCls" attr_val="day"><img src="Assests/icons/Today_icon.png"/>&nbsp;&nbsp;<b><span>Day</span></b></li>
								<li class="active commpnliCls weeklicls" attr_val="week"><img src="Assests/icons/Week_icon.png" />&nbsp;&nbsp;<b><span> Week</span></b></li>
								<li class="commpnliCls" attr_val="month"><img src="Assests/icons/CustomRange_icon.png" />&nbsp;&nbsp;<b><span>Month</span></b></li>
								</li>
							</ul>  
					</div>
					<div id="IHHLAchivementProgress" style="height:300px;"></div>
				</div>
			</div>
		</div>
		<div class="white-block m_top20" style="background-color:#F0F0F0;padding:10px">
			<div class="row">
				<div class="col-sm-12">
					<ul class="list-inline pull-right calendar_active_IHHL_cls" attr_level_type="table">
						<li attr_val="view" style="cursor:none;"><b><span>View wise&nbsp:</span></b></li>
						<li class="defaultActiveClsDay commpnliCls timePeriodCommonCls" attr_val="day"><img src="Assests/icons/Today_icon.png"/>&nbsp;&nbsp;<b><span>Day</span></b></li>
						<li class="active commpnliCls weeklicls locationLevelWeekCls timePeriodCommonCls" attr_val="week"><img src="Assests/icons/Week_icon.png" />&nbsp;&nbsp;<b><span> Week</span></b></li>
						<li class="commpnliCls timePeriodCommonCls" attr_val="month"><img src="Assests/icons/CustomRange_icon.png" />&nbsp;&nbsp;<b><span>Month</span></b></li>
						<!--<li attr_val="custom" id="singleDateRangePicker"><b><span>Custom Range</span></b></li>-->
						</li>
					</ul>  
				</div>
			</div>
			<div class="row m_top20">
				<div class="col-sm-12">
					<div id="levelWiseDetailsBlockId"></div>
				</div>
			</div>
		</div>
	</div>
</main>
<div class="modal fade" id="categoryWiseAnalysisModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="modalHeadingId"></h4>
      </div>
      <div class="modal-body">
	   <div id="categoryWiseAnalysisTableDivId"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="Assests/swachhBharatMissionIHHL/swachhBharatMissionIHHL.js" type="text/javascript"></script>
</body>
</html>