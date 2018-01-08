<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rural Water Supply Dashboard || Works Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/getorgchart/getorgchart.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>

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
					<h4 class="text-capital">Panchayat Raj & RD & RWS</h4>
					<p>Rural Water Supply - AP</p>
				</div>
				<div class="col-sm-1 col-xs-12 col-sm-offset-5">
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
										<h4>PANCHAYAT RAJ</h4>
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
					Note: All Amount in Lakhs
				</div>
				<div class="col-sm-4 col-md-3 border_right">
					<div class="form-horizontal">
						<div class="form-group form-group-sm">
							<label class="col-sm-5 col-md-5 control-label" for="formGroupInputLarge">Financial Year: </label>
							<div class="col-sm-6  col-md-6">
								<select id="financialYearId" class="chosenSelect"></select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
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
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="white-block" style="padding:10px;background-color: #ececec;border-bottom-style:inset;">
						<h4>Works -  STATE LEVEL OVERVIEW</h4>
					</div>
					<div class="white-block" style="padding:10px;">	
						<div class="row">
							<div class="col-sm-6">
								<h5 style="padding:5px"><span class="chartTitleAlign">Works&nbsp;&nbsp;-&nbsp;&nbsp;PWS</span></h5>
								<div class="chart2" id="habitationWorksPWS" style="height:315px;"></div>
							</div>
							<div class="col-sm-6">
								<h5 style="padding:5px"><span class="chartTitleAlign">Works&nbsp;&nbsp;-&nbsp;&nbsp;CPWS</span></h5>
								<div class="chart2" id="habitationWorksCPWS" style="height:315px;"></div>
							</div>
						</div>
						<div id="graphTable" style="display:none;"></div>
						<div id="graphTable2" style="display:none;"></div>
					</div>
				</div>
			</div>
				<div class="white-block" style="padding:10px;">	
					<div class="row m_top10">
						<div class="col-sm-12">
							<div class="panel-group" id="accordionON">
							  <div class="panel panel-default panel-black">
								<div class="panel-heading" id="headingON">
									<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordionON" href="#collapseON" aria-expanded="true" aria-controls="collapseON">
										<div class="row">
											<div class="col-sm-3">
												<h4 class="panel-title text-capital">Exceeded Works Details</h4>
											</div>
										</div>
									</a>	
								</div>
								<div id="collapseON" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingON">
									
									<div class="panel-body">
									
										<div class="white-block" style="padding:10px;">
											<div class="row">
												<div class="col-sm-4 pull-right">
													<div class="">
														<label class="radio-inline m_top5">
															<input type="radio" class="exceedWorkTypeCls"  name="optradio1" value="ongoing"checked> OnGoing Exceeded Works
														</label>
														<label class="radio-inline m_top5">
															<input type="radio"  class="exceedWorkTypeCls"  name="optradio1" value="" > All Exceeded Works
														</label>
																						
													</div>
												</div>	
											</div><br/><br/>
											<div class="row">
												<div class="col-sm-3">
													<div id="ExceededTargetDetailsTotal"></div>
												</div>
												<div class="col-sm-9">
													<div id="ExceededTargetDetails"></div>
												</div>
											</div>
										</div>
								  </div>
								</div>
							  </div>
						  </div>
					  </div>
				  </div>
				  <div class="row m_top10">
					<div class="col-sm-12">
						  <div class="panel-group" id="accordionEX1">
							  <div class="panel panel-default panel-black">
								<div class="panel-heading" id="headingEX1">
									<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordionEX1" href="#collapseEX1" aria-expanded="true" aria-controls="collapseEX1">
										<h4 class="panel-title text-capital">Not Grounded Work Details</h4>
									</a>	
								</div>
								<div id="collapseEX1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading1">
								  <div class="panel-body">
										<div class="white-block" style="padding:10px;">
											<div class="row">
												<div class="col-sm-3">
													<div id="ExceededNotGroundedTotal"></div>
												</div>
												<div class="col-sm-9">
													<div id="NotGroundedTargetDetails"></div>
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
	<section>
		<div class="container-fluid m_top20">
			<div class="row" id="accordion">
				<div class="col-sm-12">
					<div id="stateBlockId"></div>
				</div>
				<div class="col-sm-12">
					<div id="districtBlockId"></div>
				</div>
				<div class="col-sm-12">
					<div id="constituencyBlockId"></div>
				</div>
				<div class="col-sm-12">
					<div id="mandalBlockId"></div>
				</div>
			</div>
		</div>
	</section>
</main>
<div class="modal fade" id="modalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="modalHeadingId">Modal title</h4>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="modalHablitationDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="modalHabliHeadingId">Modal title</h4>
      </div>
      <div class="modal-body">
		
		<div id="modalSchemsTable"></div>
		<div id="modalSchemsExceedTable"></div>
		
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
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/ruralWaterSupply/worksDashBoard.js" type="text/javascript"></script>
</body>
</html>