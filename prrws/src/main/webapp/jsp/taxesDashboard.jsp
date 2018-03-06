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
<link href="Assests/Plugins/SlickSliderNew/slick.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/SlickSliderNew/slick-theme.less" type="text/less" rel="stylesheet"/>
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
<section class="" style="padding: 10px;background-color: #DFDFE0;">
		<div class="container-fluid">
			<div class="row">
				<!--<div class="col-sm-2 m_top5">
					<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" id="selectedName1" style="font-size:13px;cursor:pointer;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;" attr_distId="" attr_levelid="2" attr_id="-1" title="Location Level">Andhra Pradesh </span></h4>
					<div class="multi-level-selection-menu arrow_box_top"></div>
				</div>-->
				<div class="col-sm-3  pull-right">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
						<input type="text" class="form-control" id="dateRangePicker" style="width: 200px;"/>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
		<div class="white-block" style="padding: 30px;">
			<div class="row">
				<div class="custom_border">
					<div class="row">	
						<div class="col-sm-12">
							<h4 class="font_weight">DEMAND COLLECTION BALANCE</h4>
						</div>
					</div>
						<div class="row">
							<div id="completeOverviewDivId"></div>
						</div>
					</div>
					<div class="custom_border m_top10">
						<div class="row">	
							<div class="col-sm-12">
								<h4 class="font_weight">CATEGORY - TAX</h4>
							</div>
						</div>
						<div class="row">
							<div id="categoryTaxOverViewDivId"></div>

						</div>
					</div>
					
					<div class="custom_border m_top10">
						<div class="row">	
							<div class="col-sm-12">
								<h4 class="font_weight">CATEGORY - FEE</h4>
							</div>
						</div>
						<div class="row">
							<div id="categoryFeeOverViewDivId"></div>
						</div>
					</div>
					
					
					<div class="custom_border m_top10">
						<div class="row">	
							<div class="col-sm-12">
								<h4 class="font_weight">INDICATOR OVERVIEW</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12" style="padding-left: 30px;padding-right: 30px;">
								<div id="indicatorsOverviewDivId"></div>
							</div>
						</div>
					</div>
					
					
					<div class="custom_border m_top10">
						<div class="row">	
							<div class="col-sm-12">
								<h4 class="font_weight">DEFAULTERS OVERVIEW</h4>
							</div>
						</div>
						<div class="row">
							<div id="defaulterOverviewDivId"></div>
							
						</div>
					</div>
					<div class="custom_border m_top10">
						<div class="bg_yash_color_10">
							<div class="row">
								<div class="col-sm-2 taxCls">
									<label>Select Tax</label>
									<select class="form-control chosen-select active" id="taxId">
										<option value="0">All</option>
										<option value="1">House</option>
										<option value="2">Kolagaaram</option>
										<option value="3">Advertisement</option>
									</select>
								</div>
								<div class="col-sm-2 feecls">
									<label>Select Fee</label>
									<select class="form-control chosen-select active" id="feeId">
										<option value="0">All</option>
										<option value="1">Trade License</option>
										<option value="2">Auctions</option>
										<option value="3">Private Tap</option>
										<option value="111">Others</option>
									</select>
								</div>
								<div class="col-sm-2">
								<div class="row">
									<div class="col-sm-12">
										<label>Year</label>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="white_bg">
											<label class="checkbox-inline">
											  <input type="checkbox" class="yearTypeCls" id="inlineCheckbox1" value="arrears" checked>Arrears
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" class="yearTypeCls" id="inlineCheckbox2" value="current" checked>Current
											</label>
										</div>
									</div>
									
								</div>
								</div>
								<div class="col-sm-3">
									<div class="row">
										<div class="col-sm-12">
											<label>Data Type</label>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="white_bg">
												<label class="radio-inline">
												  <input type="radio" name="inlineRadioOptionsUnits" id="inlineRadio1" value="unit">Unit wise
												</label>
												<label class="radio-inline">
												  <input type="radio" name="inlineRadioOptionsUnits" id="inlineRadio2" value="amount">Amount wise
												</label>
												<label class="radio-inline">
												  <input type="radio" name="inlineRadioOptionsUnits" id="inlineRadio3" value="all" checked>All
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="row">
										<div class="col-sm-12">
											<label>Defaulters</label>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="white_bg">
												<label class="checkbox-inline">
												  <input type="checkbox" class="defaulterCls" name="inlineRadioOptions1" id="inlineCheckbox1" value="defaulters">Defaulters
												</label>
												<label class="checkbox-inline">
												  <input type="checkbox" class="defaulterCls" name="inlineRadioOptions1"  id="inlineCheckbox2" value="indicators">Indicators
												</label>
											</div>
											<h5 class="f_11 m_top5">(Both UnSelect Display LocationWise Details)</h5>
										</div>
										
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Location Level</label>
									<select class="form-control chosen-select locationLevelCls">
										<option value="0">Select Level</option>
										<!--<option value="State">State</option>-->
										<option value="district" selected>District</option>
										<option value="assembly">Constituency</option>
										<option value="mandal">Mandal</option>
									</select>
								</div>
								<div class="col-sm-2 districtCls">
									<label>District</label>
									<select class="form-control chosen-select" id="districtId">
									</select>
								</div>
								<div class="col-sm-2 constituencyCls" style="display:none;">
									<label>Constituency</label>
									<select class="form-control chosen-select" id="constituencyId">
									</select>
								</div>
								<div class="col-sm-2 mandalCls" style="display:none;">
									<label>Mandal</label>
									<select class="form-control chosen-select" id="mandalId">
									</select>
								</div>
								<div class="col-sm-2">
									<button class="btn btn-primary btn-sm font_weight getDetailsCls" style="margin-top: 26px;">SUBMIT</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<div id="tableBuildDivId"></div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/SlickSliderNew/slick.js" type="text/javascript"></script>
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
<script type="text/javascript" src="Assests/js/locationHierarchy.js"></script>
<script src="Assests/PanchayatTax/taxesDashboard.js" type="text/javascript"></script>
<script type="text/javascript">
$(".chosen-select").chosen();
</script>
</body>
</html>