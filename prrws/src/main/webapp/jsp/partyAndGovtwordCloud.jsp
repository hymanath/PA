<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WORD CLOUD ARTICLES</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/wordCloud.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="Assests/Plugins/Scroller/bootstrap-multiselect.css" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
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
				<div class="col-sm-10 m_top10 col-xs-9">
					<h4 class="text-capital" style="margin-top: 14px;">WORD CLOUD</h4>
				</div>
				<div class="col-sm-1 col-xs-12">
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
												<div class="menu-block" style="background-color:#8A2BE2">
													<a href="newsArticles?deptId=2171">
														<h3>RWS News</h3>
														<p>Rural&nbsp;Water&nbsp;Supply&nbsp;News</p>
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
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#483D8B">
													<a href="vehicleTrackingDashboard">
														<h3>VT</h3>
                           								 <p>Vehicle Tracking</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#59bf82">
													<a href="jalavaniAlertsDashBoard">
														<h3>JALAVANI</h3>
														 <p>Jalavani Alert Dashboard</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#EE6CA9">
													<a href="worksDashBoard">
														<h3>RWS WORKS</h3>
														 <p>RWS Works Dashboard</p>
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
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="EncDevelopmentDashboard">
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
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="solidWasteManagementDashboard">
														<h3>SWM</h3>
                           								 <p>Solid Waste Management</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#FF1493">
													<a href="panchayatTaxDashboard">
														<h3>Taxes</h3>
                           								 <p>Panchayat Raj Taxes</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#7B68EE">
													<a href="eMeetingsDashboard">
														<h3>E Meetings</h3>
                           								 <p>Panchayat Raj eMeetings</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#008000">
													<a href="newsArticles?deptId=1699">
														<h3>PR News</h3>
                           								 <p>Panchayat Raj News</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="EncWorksDashboard">
														<h3>ENC WORKS</h3>
                           								 <p>ENC Works DashBoard</p>
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
											<div class="row">
											<div class="col-sm-6">
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>RD</h3>
														<p>Rural&nbsp;Development&nbsp;Dashboard</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="menu-block" style="background-color:#8B0000">
													<a href="newsArticles?deptId=2170">
														<h3>RD News</h3>
														<p>Rural Development News</p>
													</a>
												</div>
											</div>
											</div>
											</div>
										</div>
									</div>
								</div>
								<!--<div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#de4524 ">
										<a href="itcDashboard">
											<h3>IT E & C</h3>
											<p>Dashboard</p>
										</a>
									</div>
								</div>-->
								<!-- <div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#989820">
										<a href="newsArticles">
											<h3>News Articles</h3>
											<p>Dashboard</p>
										</a>
									</div>
								</div>-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
	
</header>
<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
	<div class="white-block" style="padding: 30px;">
		<div class="row">
			<div class="col-sm-12">
				<div id="chart">
					<svg id="svg">
					</svg>
				</div>
			</div>
		</div>
		<div class="row align-items-center" style="margin-left: 250px; margin-right: 0px;">
			<div class="col-sm-2 offset-2 labels-row">
				<label for="crit" class="label-padding">Critical</label>
				<div id="crit" class="box-color critical"></div>
			</div>
			<div class="col-sm-2 labels-row">
				<label for="neg" class="label-padding">Negative</label>
				<div id="neg" class="box-color negative"></div>
			</div>
			<div class="col-sm-2 labels-row">
				<label for="pos" class="label-padding">Positive</label>
				<div id="pos" class="box-color positive"></div>
			</div>
			<div class="col-sm-2 labels-row">
				<label for="neu" class="label-padding">Neutral</label>
				<div id="neu" class="box-color neutral"></div>
			</div>
		</div>
		<div class="bg_color m_top10" style="border:1px solid #ddd;border-radius:5px">
			<div class="row">
				<div class="col-sm-12" style="padding-bottom: 8px;">
					<ul class="list-inline switch-btn alertCategoryWiseCls pull-right m_top5" style=" background-color: #337ab7;">
					<li class="active" attr_type="N" >Party</li>
					<li attr_type="Y">Govt</li>
					<li attr_type="">ALL</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<label for="startDate">Start Date</label>
					<div class="input-group dateRangePickerCls" >
						<input type="text" id="startDate" class="form-control" style="width: 230px;"/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
				<div class="col-sm-3">
					<label for="endDate">End Date</label>
						<div class="input-group dateRangePickerCls" >
							<input type="text" id="endDate" class="form-control" style="width: 230px;"/>
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</div>
					</span>
				</div>
				<div class="col-sm-3">
					<label for="wordCloudDistrict">District</label>
					<select class="" multiple="multiple" id="wordCloudDistrict">
						
					</select>
				</div>
				<div class="col-sm-3">
					<label for="wordCloudConstituency">Constituency</label>
					<select class="" multiple="multiple" id="wordCloudConstituency">
						<option selected>Constituency</option>
					</select>
				</div>
				<div class="col-sm-3">
					<label for="newspapers">News Paper</label>
					<select class="" multiple="multiple" id="newspapers">
					</select>
				</div>
				<div class="col-sm-3">
					<label>Edition Type</label>
					<select class="" multiple="multiple" id="editionType">
						<option selected> Main </option>
						<option selected> District </option>
						<option selected> Online </option>
					</select>
				</div>
				<div class="col-sm-3">
					<label for="wordCloudDepartmentNames" >Department Name</label>
					<select class="" multiple="multiple" id="wordCloudDepartmentNames">
						<option selected>Department</option>
					</select>
				</div>
				<div class="col-sm-3">
					<button class="btn btn-primary" style="margin-top: 22px;" onclick="fetchDataForWordCloud('fromPage')">Submit</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="empty-alert">
				<div class="alert alert-danger" role="alert">
					Results are empty!
				</div>
			</div>
			<div class="error-alert">
				<div class="alert alert-danger" role="alert">
					Error in server!
				</div>
			</div>
			<div class="data-sent-alert">
				<div class="alert alert-success" role="alert">
					<div class="row">
						<div class="col-10">
							Request sent!
						</div>
						<div class="col-2">
							<div class="loader-2"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="data-processing-alert">
				<div class="alert alert-primary" role="alert">
					<div class="row">
						<div class="col-10">
							Data received and processing word cloud!
						</div>
						<div class="col-2">
							<div class="loader"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="card headline-div">
				<div class="card-header" style="background-color: blanchedalmond;">
					<div class="row">
						<div class="col-sm-11" style="padding-left: 33px;"> News Article Titles </div>
						<div class="col-1 offset-4">
							<i class="fa fa-times close-button" onclick="closeDiv()"></i>
						</div>
					</div>
				</div>
				<ul class="list-group list-group-flush headline-div-content">
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModalShowNews"></div> 
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/Plugins/Scroller/bootstrap-multiselect.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js" integrity="sha256-dsOXGNHAo/syFnazt+KTBsCQeRmlcW1XKL0bCK4Baec="
        crossorigin="anonymous"></script>
		
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-cloud/1.2.4/d3.layout.cloud.min.js" integrity="sha256-+U6evHIlf3gdG4NC/P4v3g4JpbLdSdYHAu/z0w2nZ4I="
	crossorigin="anonymous"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js" integrity="sha256-8E6QUcFg1KTnpEU8TFGhpTGHw5fJqB9vCms3OhAYLqw="
	crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>

<script type="text/javascript" src="Assests/js/partywordCloud.js"></script>
<script type="text/javascript">
var globalDeptId = "${param.deptId}";
var globalfavName = "${param.name}";
</script>
</body>
</html>