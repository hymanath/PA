<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PRIS Survey Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>

</head>
<body>
<style>
.bg_color{
	background-color : #ddd;
}
</style>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9 headingBlock">
					<h4 class="text-capital">PRIS Survey</h4>
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
														<h3>RD</h3>
														<p>Rural Development Dashboard</p>
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
				<div class="col-sm-3">
					<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" style="font-size:13px;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;" attr_levelid="2" attr_id="-1" title="Location Level">Andhra Pradesh </span></h4>
					<div class="multi-level-selection-menu arrow_box_top"></div>
				</div>
				<div class="col-sm-9">
					<ul class="list-inline pull-right calendar_active_cls">
						<li attr_val="Overall"><img src="Assests/icons/Overall_icon.png"/>&nbsp;&nbsp;<b><span>Overall</span></b></li>
						<li attr_val="Today"><img src="Assests/icons/Today_icon.png"/>&nbsp;&nbsp;<b><span>Today</span></b></li>
						<li attr_val="Week"><img src="Assests/icons/Week_icon.png"/>&nbsp;&nbsp;<b><span> Week</span></b></li>
						<li class="active" attr_val="Month"><img src="Assests/icons/Month_icon.png"/>&nbsp;&nbsp;<b><span>Month</span></b></li>
						<li attr_val="3Months"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>3Months</span></b></li>
						<li attr_val="6Months"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>6Months</span></b></li>
						<li attr_val="Year"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>Year</span></b></li>
						<li attr_val="custom" id="singleDateRangePicker"><img src="Assests/icons/CustomRange_icon.png"/>&nbsp;&nbsp;<b><span>Custom Range</span></b></li>
						</li>
					</ul>  
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<div class="container-fluid">
	<section>
			<div class="row m_top5">
				<div class="col-sm-12">
					<div id="levelBlocksDivId"></div>
				</div>
			</div>
			<div class="row m_top5">
				<div class="col-sm-3 mainBlock">
					<div class="white-block">
						<div class="media blockHeights block_styles">
						  <div class="media-left img_middle">
							  <img class="media-object" src="Assests/icons/house_icon.png" alt="house_icon">
						  </div>
						  <div class="media-body">
							<h4 class="">TOTAL HOUSEHOLDS</h4>
							<h4 class="m_top10 title_align" id="totalHouseHolds"></h4>
						  </div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 mainBlock">
					<div class="white-block">
						<div class="media blockHeights block_styles">
						  <div class="media-left img_middle">
							  <img class="media-object" src="Assests/icons/Target_icon.png" alt="Target_icon">
						  </div>
						  <div class="media-body">
							<h4 class="panel-title">TARGET <span class="pull-right color_Tlabel" id="targetOverallPercent"></span></h4>
							<small>Overall</small>
							<h4 class="title_align" id="targetOverall"></h4>
						  </div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 mainBlock">
					<div class="white-block">
						<div class="media blockHeights block_styles">
						  <div class="media-left img_middle">
							  <img class="media-object" src="Assests/icons/Achived_icon.png" alt="Achived_icon">
						  </div>
						  <div class="media-body">
							<h4 class="">ACHIEVED <span class="pull-right color_Alabel" id="achievedOverallpercent"></span></h4>
							<small>Overall</small>
							<h4 class="m_top10 title_align" id="achievedOverall"></h4>
						  </div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 mainBlock">
					<div class="white-block">
						<div class="media blockHeights block_styles">
							<p> <span class="thisMonthOverview text-capital">JUNE</span> </p>
							<div class="row m_top5">
								<div class="col-sm-6">
									<img class="img_width" src="Assests/icons/Target_icon.png" alt="Achived_icon">
									<span class="pull-right color_Tlabel" id="subTargetPercentage"></span>
									<h5>TARGET</h5>
									<h4 class="m_top5"><b id="subTarget"></b></h4>
								</div>
								<div class="col-sm-6 border_right border_adjust_align">
									<img  class="img_width" src="Assests/icons/Achived_icon.png" alt="Achived_icon">
									<span class="pull-right color_Alabel" id="subAchievedPercentage"></span>
									<h5>ACHIVED</h5>
									<h4 class="m_top5"><b id="subAchieved"></b></h4>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
		</section>
		<section>
			<div id="levelWiseBlockId" class="m_top20"></div>
		</section>
	</div>
</main>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/js/locationHierarchy.js"></script>
<script src="Assests/prisDashBoard/prisDashBoard.js" type="text/javascript"></script>
</body>
</html>