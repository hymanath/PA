<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Panchayat Raj - Tax DashBoard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
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
					<h4 class="text-capital">Panchayat Raj</h4>
					<p>Tax - DashBoard</p>
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
<main>
	<div class="container-fluid">
		<div class="white-block pad_20">
			<div class="row">
				<div class="custom_border">
					<div class="row">	
						<div class="col-sm-12">
							<h4 class="font_weight">DEMAND COLLECTION BALANCE</h4>
						</div>
					</div>
						<div class="row">
							<!--<div class="col-sm-3 m_top10">
								<div class="panel" style="text-align:center; background-color:#F69961;">
									<div class="panel-heading">
										<h4 class="white_color">Arrears Balance</h4>
									</div>
									<div class="panel-body" style="background-color:#F57C3E;">
										<div class="row">
											<div class="Pad_14_10">
												<h2 class="white_color">47.77&nbsp;Cr</h2>
												<h5 class="m_top5 font_weight">Units:&nbsp;8343</h5>
												<hr class="m_top_bottom">
												<div class="row">
													<div class="col-sm-3 Bor_rgt_white">
														<h5 class="font_weight m_top5">2014</h5>
														<h5 class="font_weight m_top5 white_color">8.07&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">2624 Un</h6>
													</div>
													<div class="col-sm-3 Bor_rgt_white">
														<h5 class="font_weight m_top5">2015</h5>
														<h5 class="font_weight m_top5 white_color">12.70&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">1386 Un</h6>
													</div>
													<div class="col-sm-3 Bor_rgt_white">
														<h5 class="font_weight m_top5">2016</h5>
														<h5 class="font_weight m_top5 white_color">13.00&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">2800 Un</h6>
													</div>
													<div class="col-sm-3">
														<h5 class="font_weight m_top5">2017</h5>
														<h5 class="font_weight m_top5 white_color">14.00&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">1533 Un</h6>
													</div>
												</div>
											</div>	
										</div>	
									</div>
								</div>
							</div>-->
							<div class="col-sm-4 m_top10">
								<div class="panel" style="text-align:center; background-color:#3393CD;">
									<div class="panel-heading">
										<h4 class="white_color">Demand</h4>
									</div>
									<div class="panel-body" style="background-color:#2277AA;">
										<div class="row">
											<div class="Pad_14_10">
												<h2 class="white_color">47.77&nbsp;Cr</h2>
												<h5 class="m_top5 font_weight">Units:&nbsp;8343</h5>
												<hr class="m_top_bottom">
												<div class="row">
													<div class="col-sm-6 Bor_rgt_white">
														<h5 class="font_weight m_top5">Arrears</h5>
														<h5 class="font_weight m_top5 white_color">8.07&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">2624 Un</h6>
													</div>
													<div class="col-sm-6">
														<h5 class="font_weight m_top5">Arrears</h5>
														<h5 class="font_weight m_top5 white_color">12.70&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">1386 Un</h6>
													</div>
												</div>
											</div>	
										</div>	
									</div>
								</div>
							</div>
							<div class="col-sm-4 m_top10">
								<div class="panel" style="text-align:center; background-color:#74B747;">
									<div class="panel-heading">
										<h4 class="white_color">Collection</h4>
									</div>
									<div class="panel-body" style="background-color:#5D9440;">
										<div class="row">
											<div class="Pad_14_10">
												<h2 class="white_color">47.77&nbsp;Cr</h2>
												<h5 class="m_top5 font_weight">Units:&nbsp;8343</h5>
												<hr class="m_top_bottom">
												<div class="row">
													<div class="col-sm-6 Bor_rgt_white">
														<h5 class="font_weight m_top5">Arrears</h5>
														<h5 class="font_weight m_top5 white_color">8.07&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">2624 Un</h6>
													</div>
													<div class="col-sm-6">
														<h5 class="font_weight m_top5">Arrears</h5>
														<h5 class="font_weight m_top5 white_color">12.70&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">1386 Un</h6>
													</div>
												</div>
											</div>	
										</div>	
									</div>
								</div>
							</div>
							<div class="col-sm-4 m_top10">
								<div class="panel" style="text-align:center; background-color:#F26A69;">
									<div class="panel-heading">
										<h4 class="white_color">Balance</h4>
									</div>
									<div class="panel-body" style="background-color:#F1595B;">
										<div class="row">
											<div class="Pad_14_10">
												<h2 class="white_color">47.77&nbsp;Cr</h2>
												<h5 class="m_top5 font_weight">Units:&nbsp;8343</h5>
												<hr class="m_top_bottom">
												<div class="row">
													<div class="col-sm-6 Bor_rgt_white">
														<h5 class="font_weight m_top5">Arrears</h5>
														<h5 class="font_weight m_top5 white_color">8.07&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">2624 Un</h6>
													</div>
													<div class="col-sm-6">
														<h5 class="font_weight m_top5">Arrears</h5>
														<h5 class="font_weight m_top5 white_color">12.70&nbsp;<small class="white_color font_weight">Cr</small></h5>
														<h6 class="font_weight m_top5">1386 Un</h6>
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
		</div>
	</div>		
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
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript">

</script>
</body>
</html>