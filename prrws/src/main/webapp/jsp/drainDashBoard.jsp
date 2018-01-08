<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Drains Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet"
	type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less" />
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet" />
<link href="http://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" type="text/css">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css"
	rel="stylesheet" />
<link
	href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less"
	type="text/less" rel="stylesheet" />
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css"
	rel="stylesheet" />
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css"
	rel="stylesheet" />
<link href="Assests/Plugins/SlickSliderNew/slick.less" type="text/less"
	rel="stylesheet" />
<link href="Assests/Plugins/SlickSliderNew/slick-theme.less"
	type="text/less" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css"
	type="text/css" rel="stylesheet" />
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
</head>
<body>
	<style>
.bg_color {
	background-color: #ddd;
}
</style>
	<header> <nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-1 col-xs-3 pad_left0">
				<img src="Assests/images/aplogo.png" class="logo" />
			</div>
			<div class="col-sm-4 m_top10 col-xs-9">
				<h4 class="text-capital">Drains</h4>
				<p>DASHBOARD</p>
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
	</nav> <section class="navbar-section">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3">
				<h4 class="arrowIconChanged">
					<i class="glyphicon glyphicon-menu-hamburger"
						style="font-size: 13px;"></i>&nbsp;&nbsp;<span id="selectedName"
						style="text-transform: uppercase; cursor: pointer;"
						attr_levelid="2" attr_id="-1" title="Location Level">Andhra
						Pradesh </span>
				</h4>
				<div class="multi-level-selection-menu arrow_box_top"></div>
			</div>
			<div class="col-sm-9">
				<ul class="list-inline pull-right calendar_active_cls">
					<li attr_val="Overall"><img
						src="Assests/icons/Overall_icon.png" />&nbsp;&nbsp;<b><span>Overall</span></b></li>
					<li attr_val="Today"><img src="Assests/icons/Today_icon.png" />&nbsp;&nbsp;<b><span>Today</span></b></li>
					<li attr_val="Week"><img src="Assests/icons/Week_icon.png" />&nbsp;&nbsp;<b><span>
								Week</span></b></li>
					<li class="active" attr_val="Month"><img
						src="Assests/icons/Month_icon.png" />&nbsp;&nbsp;<b><span>Month</span></b></li>
					<li attr_val="3Months"><img
						src="Assests/icons/3612months_icon.png" />&nbsp;&nbsp;<b><span>3Months</span></b></li>
					<li attr_val="6Months"><img
						src="Assests/icons/3612months_icon.png" />&nbsp;&nbsp;<b><span>6Months</span></b></li>
					<li attr_val="Year"><img
						src="Assests/icons/3612months_icon.png" />&nbsp;&nbsp;<b><span>Year</span></b></li>
					<li attr_val="custom" id="singleDateRangePicker"><img
						src="Assests/icons/CustomRange_icon.png" />&nbsp;&nbsp;<b>Custom
							Range</b></li>
					</li>
				</ul>
			</div>
		</div>
	</div>
	</section> </header>
	<main>
	<div class="container-fluid">
		<section>
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-3 block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-10">
									<h4 class="text-center">TOTAL</h4>
								</div>
								<div class="col-sm-2">
									<span id="totalSpinnerId" class="pull-right"></span>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div id="totalBodyId">
								<div class="row">
									<div class="col-sm-6 subBlock">
										<h5>AVAILABLE</h5>
										<h4 id="totalAvailableCountId">0</h4>
										<span id="totalAvailableKmId">0 KM</span>
									</div>
									<div class="col-sm-6 subBlock">
										<h5>CLEANED</h5>
										<h4 id="totalCleanedCountId">
											0<small class="pull-right" id="totalPercentageId">0.00%</small>
										</h4>
										<span id="totalCleanedKmId">0 KM</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-10">
									<h4 class="text-center">KACHA</h4>
								</div>
								<div class="col-sm-2">
									<span id="kachaSpinnerId" class="pull-right"></span>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div id="kachaBodyId">
								<div class="row">
									<div class="col-sm-6 subBlock">
										<h5>AVAILABLE</h5>
										<h4 id="kachaAvailableCountId">0</h4>
										<span id="kachaAvailableKmId">0 KM</span>
									</div>
									<div class="col-sm-6 subBlock">
										<h5>CLEANED</h5>
										<h4 id="kachaCleanedCountId">
											0<small class="pull-right" id="kachaPercentageId">0.00%</small>
										</h4>
										<span id="kachaCleanedKmId">0 KM</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-10">
									<h4 class="text-center">PAKKA</h4>
								</div>
								<div class="col-sm-2">
									<span id="pakkaSpinnerId" class="pull-right"></span>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div id="pakkaBodyId">
								<div class="row">
									<div class="col-sm-6 subBlock">
										<h5>AVAILABLE</h5>
										<h4 id="pakkaAvailableCountId">0</h4>
										<span id="pakkaAvailableKmId">0 KM</span>
									</div>
									<div class="col-sm-6 subBlock">
										<h5>CLEANED</h5>
										<h4 id="pakkaCleanedCountId">
											0<small class="pull-right" id="pakkaPercentageId">0.00%</small>
										</h4>
										<span id="pakkaCleanedKmId">0 KM</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-10">
									<h4 class="text-center">UNDERGROUND</h4>
								</div>
								<div class="col-sm-2">
									<span id="undergroundSpinnerId" class="pull-right"></span>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div id="ugBodyId">
								<div class="row">
									<div class="col-sm-6 subBlock">
										<h5>AVAILABLE</h5>
										<h4 id="ugAvailableCountId">0</h4>
										<span id="ugAvailableKmId">0 KM</span>
									</div>
									<div class="col-sm-6 subBlock">
										<h5>CLEANED</h5>
										<h4 id="udCleanedCountId">
											0<small class="pull-right" id="ugPercentageId">0.00%</small>
										</h4>
										<span id="ugCleanedKmId">0 KM</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<section class="m_top20">
		<div class="container-fluid">
			<div class="row" id="accordion">
				<div class="col-sm-12">
					<div class="panel panel-black panel-default"
						overview-level="district">
						<div class="panel-heading">
							<a class="panelCollapseIcon" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">
								<h4 class="panel-title text-capital">DISTRICT LEVEL
									OVERVIEW</h4>
							</a>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<ul class="nav navbar-nav list_inline tableMenu"
											role="tabDrains_menu" attr_blockId="3">
											<li class="active" attr_location_type="districts">Districts</li>
											<li class="" attr_location_type="parliament">Parliament</li>
										</ul>
									</div>
								</div>
								<div id="districtViewTableDivId" class="row"></div>
								<!--<div class="panel panel-black panel-default">
									<div class="panel-heading ivrReponseStyle" >
										<div class="row">
											<div class="col-sm-4">
												<h4 class="text-capital">IVR RESPONSE OVERVIEW</h4>
											</div>
											<div class="col-sm-3 pull-right">
												<div id="districtViewivrQuestions"></div>
											</div>
										</div>
										<div class="row">
											<div id="districtViewivrResponseDatesDivId"></div>
										</div>
									</div>
									<div class="panel-body">
										<h4 id="districtViewselectedQuestion"></h4>
										<div id="districtViewtotalPanchayatResponse"></div>
										<div class="callerBKClr">
											<div id="districtViewtotalIvrResponse"></div>
										</div>
										<div class="row m_top10">
											<div class="col-sm-12">
												<div id="districtViewivrTableView"></div>
											</div>
										</div>
									</div>
								</div>-->
							</div>
						</div>
					</div>
					<div class="panel panel-black panel-default"
						overview-level="constituency">
						<div class="panel-heading">
							<a class="panelCollapseIcon collapsed" role="button"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo" aria-expanded="true"
								aria-controls="collapseTwo">
								<h4 class="panel-title text-capital">CONSTITUENCY LEVEL
									OVERVIEW</h4>
							</a>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse "
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<div class="col-sm-3">
											<ul
												class="nav navbar-nav list_inline tableMenu tableMenuCons"
												role="tabDrains_menu" attr_blockId="4">
												<li class="active" attr_location_type="districts">Districts</li>
												<li class="" attr_location_type="parliament">Parliament</li>
											</ul>
										</div>
										<div class="col-sm-3">
											<select class="form-control chosen-select"
												id="constituencyDistrictNames">
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div id="assemblyViewTableDivId"></div>
									</div>
								</div>
								<!--<div class="row">
									<div class="col-sm-12">
										<div class="panel panel-black panel-default">
											<div class="panel-heading ivrReponseStyle" >
												<div class="row">
													<div class="col-sm-4">
														<h4 class="text-capital">IVR RESPONSE OVERVIEW</h4>
													</div>
													<div class="col-sm-3 pull-right">
														<div id="assemblyViewivrQuestions"></div>
													</div>
												</div>
												<div class="row">
													<div id="assemblyViewivrResponseDatesDivId"></div>
												</div>
											</div>
											<div class="panel-body">
												<h4 id="assemblyViewselectedQuestion"></h4>
												<div id="assemblyViewtotalPanchayatResponse"></div>
												<div class="callerBKClr">
													<div id="assemblyViewtotalIvrResponse"></div>
												</div>
												<div class="row m_top10">
												
													<div class="col-sm-12">
														<div id="assemblyViewivrTableView"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>-->
							</div>
						</div>
					</div>
					<div class="panel panel-black panel-default"
						overview-level="mandal">
						<div class="panel-heading">
							<a class="panelCollapseIcon collapsed" role="button"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree" aria-expanded="true"
								aria-controls="collapseThree">
								<h4 class="panel-title text-capital">MANDAL LEVEL OVERVIEW</h4>
							</a>
						</div>
						<div id="collapseThree" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<div class="col-sm-3">
											<ul
												class="nav navbar-nav list_inline tableMenu tableMenuMandal"
												role="tabDrains_menu" attr_blockId="5">
												<li class="active" attr_location_type="districts">Districts</li>
												<li class="" attr_location_type="parliament">Parliament</li>
											</ul>
										</div>
										<div class="col-sm-3">
											<select class="form-control chosen-select"
												id="mandalDistrictNames">
											</select>
										</div>
										<div class="col-sm-3">
											<select class="form-control chosen-select"
												id="mandalConstituencyNames">
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div id="mandalViewTableDivId"></div>
									</div>
								</div>
								<!--<div class="panel panel-black panel-default">
									<div class="panel-heading ivrReponseStyle" >
										<div class="row">
											<div class="col-sm-4">
												<h4 class="text-capital">IVR RESPONSE OVERVIEW</h4>
											</div>
											<div class="col-sm-3 pull-right">
												<div id="mandalViewivrQuestions"></div>
											</div>
										</div>
										<div class="row">
											<div id="mandalViewivrResponseDatesDivId"></div>
										</div>
									</div>
									<div class="panel-body">
										<h4 id="mandalViewselectedQuestion"></h4>
										<div id="mandalViewtotalPanchayatResponse"></div>
										<div class="callerBKClr">
											<div id="mandalViewtotalIvrResponse"></div>
										</div>
										<div class="row m_top10">
										
											<div class="col-sm-12">
												<div id="mandalViewivrTableView"></div>
											</div>
										</div>
									</div>
								</div>-->
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div id="blocksData"></div>
		</section>

	</div>
	</main>
	<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
	<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
	<script src="Assests/Plugins/Chosen/chosen.jquery.js"
		type="text/javascript"></script>
	<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
	<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js"
		type="text/javascript"></script>
	<script src="Assests/Plugins/Date/daterangepicker.js"
		type="text/javascript"></script>
	<script src="Assests/Plugins/DataTable/dataTable.js"
		type="text/javascript"></script>
	<script src="Assests/Plugins/SlickSliderNew/slick.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="Assests/js/locationHierarchy.js"></script>
	<script src="Assests/drainDashBoard/drainDashBoard.js"
		type="text/javascript"></script>
</body>
</html>