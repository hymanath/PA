<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DAILY SPIKE REPORT</title>
<link href="Assests/css/bootstrap.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" type="text/less" rel="stylesheet"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
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
					<h4 class="text-capital">DAILY SPIKE REPORT</h4>
					<p>DASHBOARD</p>
				</div>
				<div class="col-sm-1 col-xs-12 col-sm-offset-5">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<div class="row">
								<div class="col-sm-12">
									<div class="menu-block" style="background-color:#FFBA00">
										<a href="newfundManagementDashboard">
											<h3>FMS</h3>
											<p>Fund Management System</p>
										</a>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="menu-block" style="background-color:#56A3C5">
										<a href="ruralWaterSupplyDashBoard">
											<h3>RWS</h3>
											<p>RURAL WATER SUPPLY</p>
										</a>
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
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
														<h3>ENC</h3>
														<p>ENGINEERING DEPARTMENT</p>
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
													<h3>MGNREGS</h3>
													<p>Mahatma Gandhi Rural employement guarantee scheme</p>
													<div class="row">
														<div class="col-sm-6 m_top10">
															<div class="menu-block" style="background-color:#FFBA00">
																<a href="MGNREGSDashboard">
																	<p>Dashboard</p>
																</a>
															</div>
														</div>
														<div class="col-sm-6 m_top10">
															<div class="menu-block" style="background-color:#56A3C5">
																<a href="NregaConsolidatedDashboard">
																	<p>Consolidated</p>
																</a>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>IWMP</h3>
														<p>Integrated Watershed Management Programme</p>
													</a>
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
	</nav>
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-6">
					<ul class="list-inline calendar_active_cls">
						<li attr_val="Overall"><img src="Assests/icons/Overall_icon.png"/>&nbsp;&nbsp;<b><span>Overall</span></b></li>
						<li attr_val="Day" class="active"><img src="Assests/icons/Today_icon.png"/>&nbsp;&nbsp;<b><span>Day</span></b></li>
						<li attr_val="Week"><img src="Assests/icons/Week_icon.png"/>&nbsp;&nbsp;<b><span> Week</span></b></li>
						<li attr_val="Month"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>Month</span></b></li>
						</li>
					</ul>  
				</div>
				<div class="col-sm-6"> 
					<div class="input-group dateRangePickerCls pull-right" >
						<span class="input-group-addon" >
							<i class="glyphicon glyphicon-calendar" ></i>
						</span>
						<input type="text" class="" style="width:200px;" id="reportrange">
					</div>
				</div>
			</div>
		</div>
	</section>
</header>
<div class="container-fluid m_top20">
	<!-- all  -->
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-default panel-black">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor">Total Overview</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row bg_ED pad_5">
						<div class="col-xs-12 col-md-6">
							<span><h3>Total Dengue and Malaria Cases Registered : <span id="totalCases">0</span></h3></span>
						</div>
					</div>    
					<div class="row m_top20">   
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Districts</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12" id="allDiseasesTotDist"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" >
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Mandals</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12" id="allDiseasesTotMandal"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- disease wise  -->
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-default panel-black">
				<div class="panel-heading" >
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor">Diseases Wise Overview</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row bg_ED pad_5">
						<span><h3  style="margin-left:15px;">Total Dengue Registered : <span id="dengueTotalCases">0</span>(<span id="dengueTotalPercent">0</span>%)</h3></span>
					</div>
					<div class="row m_top20">         
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" >
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Districts</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12" id="topDistForDengue"></div>
									</div>  
								</div>
							</div>   
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" >
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Mandals</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12" id="topMandalForDengue"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row bg_ED pad_5">
						<span><h3  style="margin-left:15px;">Total Malaria Registered : <span id="malariaTotalCases">0</span>(<span id="malariaTotalPercent">0</span>%)</h3></span>
					</div>
					<div class="row m_top20">   
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" >
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Districts</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12" id="topDistForMalaria"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" >
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Mandals</h4>
										</div>
									</div>
								</div>  
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12" id="topMandalForMalaria"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- day, week and month wise -->
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-default panel-black">
				<div class="panel-heading" >
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor">Chronological</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12" id="chronologicalId"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Location wise -->
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-default panel-black">
				<div class="panel-heading" >
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor">Location Wise</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12" id="locationWiseCaseCount"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="Assests/js/jquery-1.11.3.js"></script>        
<script type="text/javascript" src="Assests/js/bootstrap.js"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/dailySpileReport/dailySpileReport.js" type="text/javascript"></script> 
<script>      
</script>
</body>
</html>