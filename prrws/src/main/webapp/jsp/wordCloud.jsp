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
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<link href="Assests/css/wordCloud.css" rel="stylesheet" type="text/css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js" integrity="sha256-8E6QUcFg1KTnpEU8TFGhpTGHw5fJqB9vCms3OhAYLqw=" crossorigin="anonymous"></script>
<style>
.panel-black .panel-heading {
    background-color: #333;
    border-radius: 0;
    color: #fff;
}
.tooltip-inner {
    max-width: 170px;
    /* If max-width does not work, try using width instead */
    width: 170px; 
}
</style>
</head>
<body>
<header style="box-shadow: 0px 3px 1px 0px rgba(0, 0, 0, 0.3);">
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayat Raj, RD & RWS,IT E&C</h4>
					<p>News Articles</p>
				</div>
				<div class="col-sm-1 col-xs-12 pull-right">
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
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#008000">
													<a href="newsArticles?deptId=1699">
														<h3>PR News</h3>
                           								 <p>Panchayat Raj News</p>
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
<main style="margin-top:5px;background-color:#fff;padding-bottom:20px;">
	<div class="row m_top10" id="department">
		<div class="col-sm-6">
			<label> <h4 style="padding-left: 31px;">Department Name : <span id="wordCloudDepartmentName"/></h4></label>
		
		</div>
	</div>
	<div class="container-fluid">
		<div class="col-sm-12" style="border: 2px solid #d7dae0; border-radius: 5px; padding: 10px 0px 10px 25px;">
			<div class="col-sm-12 responsive">
				<div id="chart">
					<svg id="svg">
					</svg>
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
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 attendanceBlockMore m_top10" expand-block-right="newsLetters">
				
				<div class="row m_top10">	
					<div class="col-sm-6 offset-1">
						<label for="startDate">Start Date</label>
						<span class="input-group dateRangePickerCls" >
							<input type="text" id="startDate" class="form-control" style="width: 577px; height: 30px;" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>
					</div>
					<div class="col-sm-6">
						<label for="endDate">End Date</label>
							<span class="input-group dateRangePickerCls" >
								<input type="text" id="endDate" class="form-control" style="width: 577px; height: 30px;"/>
								<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-sm-6">
						<label for="wordCloudDistrict">District</label>
						
						<select class="form-control chosen-select" multiple="multiple" id="wordCloudDistrict">
						</select>
					</div>
					<div class="col-sm-6">
						<label for="wordCloudConstituency">Constituency</label>
						<select class="form-control chosen-select" multiple="multiple" id="wordCloudConstituency">
						</select>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-sm-6">
						<label for="newspapers">News Paper</label>
					<select class="form-control chosen-select" multiple="multiple" id="newspapers">
						</select>
					</div>
					<div class="col-sm-6">
						<label>Edition Type</label>
						<select class="form-control chosen-select" multiple="multiple" id="editionType">
							<option value ="0" selected> ALL Editions </option>
							<option> Main </option>
							<option> District </option>
							<option> Online </option>
						</select>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-sm-6">
						<label for="wordCloudDepartmentNames" >Department Name</label>
					<select class="form-control chosen-select" multiple="multiple" id="wordCloudDepartmentNames" disabled>
						</select>
					</div>
					<div class="col-sm-6">	
						<div class="submit-button">
							<div class="text-center">
								<button class="btn btn-primary" style="margin-top: 22px;" onclick="fetchDataForWordCloud('fromPage')">Submit</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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
</main>
</body>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">
var globalDeptId = "${param.deptId}";
var globalfavName = "${param.name}";

</script>
<script type="text/javascript" src="Assests/js/wordCloud.js"></script>
</body>
</html>