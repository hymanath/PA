<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rural Development Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<script type="text/javascript">
var searchParams = new URLSearchParams(window.location.search);
	searchParams = searchParams.get("component");
</script>
</head>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-10 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayat Raj, RD & RWS</h4>
					<p>Rural Development - AP</p>
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
	
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 border_right m_top5">
					<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" style="font-size:13px;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;" attr_levelid="2" attr_id="-1" title="Location Level">Andhra Pradesh </span></h4>
					<div class="multi-level-selection-menu arrow_box_top"></div>
				</div>
				<div class="col-sm-3 border_right">
					<div class="row">
						<div class="col-sm-4 m_top5">
							<label>FROM DATE</label>
						</div>
						<div class="col-sm-8">
							<div class="input-group inline-block">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerMGNF"/>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 border_right">
					<div class="row">
						<div class="col-sm-4 m_top5">
							<label>TO DATE</label>
						</div>
						<div class="col-sm-8">
							<div class="input-group inline-block">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerMGNT"/>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<ul class="switch-btn pull-right" role="tabCummulative">
						<li class="active" attr_type="thisFin" style="font-size:12px !important;">This Fin. Year</li>
						<li  attr_type="prevFin" style="font-size:12px !important;">Prev Fin. Year</li>
					</ul>
				</div>
				<!--<div class="col-sm-6 m_top5 pad_right0">
					<ul class="list-inline pull-left">
						<li><span class="color-label color-label-gold"></span> 100% and above</li>
						<li><span class="color-label color-label-success"></span> 90% and above</li>
						<li><span class="color-label color-label-default"></span> 60% to Below 90%</li>
						<li><span class="color-label color-label-danger"></span> less than 60% &nbsp;&nbsp;&nbsp;</li>
					</ul>
				</div>-->
			</div>
		</div>
	</section>
</header>
<main>
<div class="col-sm-6 m_top5 pad_right0 pull-right">
	<ul class="list-inline pull-left">
		<li><span class="color-label color-label-gold"></span> 100% and above</li>
		<li><span class="color-label color-label-success"></span> 90% and above</li>
		<li><span class="color-label color-label-default"></span> 60% to Below 90%</li>
		<li><span class="color-label color-label-danger"></span> less than 60% &nbsp;&nbsp;&nbsp;</li>
	</ul>
</div>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div id="overViewAbstract"></div>
				</div>
				<div class="col-sm-12 m_top20">
					<div id="projectOverviewBlock"></div>
				</div>
				<div class="col-sm-12 m_top20">
					<div id="projectData"></div>
				</div>
			</div>
		</div>
	</section>
</main>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
<script src="Assests/MGNREGS/ruralDevelopment.js" type="text/javascript"></script>
</body>
</html>