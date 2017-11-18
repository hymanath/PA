<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FMS DASHBOARD</title>
<link href="Assests/css/bootstrap.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" type="text/less" rel="stylesheet"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
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
					<h4 class="text-capital">Panchayati Raj & RD & RWS</h4>
					<p>Fund Management System - AP</p>
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
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<ul class="list-inline navbar-header-custom">
						<li style="position:relative;top:-13px;cursor: pointer;">
							<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" style="font-size:13px;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;">Andhra Pradesh </span></h4>
							<div class="multi-level-selection-menu arrow_box_top"></div>
						</li>
						<li>
							<div class="form-horizontal">
								<div class="form-group form-group-sm">
									<label class="col-sm-4 control-label" for="formGroupInputLarge">Financial Year: </label>
									<div class="col-sm-8">
										<select id="financialYearId" class="chosenSelect" multiple></select>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="form-horizontal">
								<div class="form-group form-group-sm">
									<label class="col-sm-4 control-label" for="formGroupInputLarge">Department: </label>
									<div class="col-sm-8">
										<select id="DepartmentsId"  class="chosenSelect" multiple></select>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerAUM"/>
							</div>
						</li>
					</ul>
					
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-6">
					<p class="text-danger">Note: Amount in Crores</p>
				</div>
				<div class="col-sm-6">
					<ul class="list-inline pull-right defaultColorView">
						<li><p class="text-primary"><i class="rounded-circle fa fa-inr"></i>High</p></li>
						<li><p class="text-warning"><i class="rounded-circle fa fa-inr"></i>Average</p></li>
						<li><p class="text-danger"><i class="rounded-circle fa fa-inr"></i>Low</p></li>
					</ul>
				</div>
			</div>
			
			<div class="panel-group" id="accordion122" role="tablist" aria-multiselectable="true">
				<div class="panel panel-default panel-black">
					<div class="panel-heading" role="tab" id="headi123">
						<a role="button"  class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion122" href="#coll123" aria-expanded="true" aria-controls="coll123">
							<h4 class="panel-title text-capital">Funds Overview</h4>
						</a>
					</div>
					<div id="coll123" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headi123">
						<div class="panel-body">
							<div class="row">
								<div id="overviewBlock"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-group" id="accordion1234" role="tablist" aria-multiselectable="true">
				<div class="panel panel-default panel-black">
					<div class="panel-heading" role="tab" id="headi1234">
						<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion1234" href="#coll1234" aria-expanded="false" aria-controls="coll1234">
							<h4 class="panel-title text-capital">Program Wise Location Highlights</h4>
						</a>
					</div>
					<div id="coll1234" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headi1234">
						<div class="panel-body">
							<div class="row">
								<div id="overViewTableId"></div>
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
				<div class="col-sm-12 stateBlock">
					<div class="panel panel-default panel-black" >
					 	<div class="panel-heading">
								<div class="row">
									<div class="col-sm-12" >
										<a class="panelCollapseIcon collapseActiveStateCls" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
											<h4 class="panel-title text-capital">State level overview</h4>
										</a>
									</div>
								</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12 panalActiveCls">
										<div class="row">
											<div class="col-sm-6">
												<ul class="switch-btn-New tabCummulativeStateCls" role="tabCummulative" attr_switch = "stateLevel">
													<li class="active ActiveStateCls" attr_type="cummulativeView">Cummulative View</li>
													<li  attr_type="normalView">Year Wise</li>
												</ul>
												
											</div>
											<div class="col-sm-6">
												<ul class="nav nav-tabs pull-right tabTypeStateCls" role="tablist" id="" >
													<li role="presentation" attr_tab_type = "stateGraph" attr_level_type="state"><a href="#stateLevelGraph" aria-controls="stateLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
													<li role="presentation" class="active" attr_tab_type = "stateTable" attr_level_type="state"><a href="#stateLevelTable" aria-controls="stateLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
													<!--<li role="presentation" class="" attr_tab_type = "stateCummulative" attr_level_type="state"><a href="#stateLevelCummulative" aria-controls="stateLevelCummulative" role="tab" data-toggle="tab">Cummulative</a></li>-->
													<li role="presentation"><a class="advancedSearchStyle stateProSubProCls" attr_switch = "stateLevel"><i class="fa fa-search-plus"></i></a></li>
												</ul>
											</div>
											<div class="col-sm-12 showHideStatePgramsCls m_top10" style="display:none;">
												<div class="row">
													<div class="col-sm-3">
														<label>PROGRAMS</label>
														<select class="form-control chosenSelect" id="programNamesState" >
														</select>
													</div>
													<div class="col-sm-3">
														<label>SUB PROGRAMS</label>
														<select class="form-control chosenSelect" id="grantTypeState" >
														</select>
														
													</div>
													<div class="col-sm-3">
														<label>SCHEMS</label>
														<select class="form-control chosenSelect" id="subProgramNamesState" >
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row m_top20">
									<div class="col-sm-12">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane m_top10" id="stateLevelGraph">
												<div class="row">
													<div class="col-sm-12">
														<div id="stateLevlOvervw"></div>	
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane active" id="stateLevelTable">
												<div class="row">
													<div class="col-sm-12">
														<div id="stateLevlOvervwTable"></div>
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
				<div class="col-sm-12 districtBlock">
					<div class="panel panel-default panel-black" id="headingTwo">
						<div class="panel-heading">
							
							<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed collapseActiveDistCls" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
										<h4 class="panel-title text-capital">district level overview</h4>
									</a>
								</div>
							</div>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">
								<div class="row">
										<!--<ul class="switch-btn distLevelActive" tab-switch="distLevel" >
											<li class="active" attr_type="overview">overview</li>
											<li attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept_Scheme Level</li>
										</ul>-->
										
										<div class="col-sm-6 panalActiveCls">
											<ul class="switch-btn-New distLevelActive selectboxsShowHide" role="tablevelValue" tab-switch="distLevel" attr_switch = "distLevel">
												<li class="active districtActiveCls" attr_type="districtType" attr_result_type="district">District</li>
												<li attr_type="parliamentType" class="parliamentCls" attr_result_type="parliament">Parliament</li>
											</ul>
											<ul class="switch-btn-New tabCummulativeDistrict selectboxsShowHide" role="tabCummulative" attr_switch = "distLevel">
												<li class="active ActiveDistrictCls" attr_type="cummulativeView">Cummulative View</li>
												<li  attr_type="normalView">Year View</li>
											</ul>
										</div>
										<div class="col-sm-6">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs pull-right tabTypeDistCls" role="tablist" id="tabDis">
												<li role="presentation" attr_tab_type="distGraph" attr_level_type="district"><a href="#distLevelGraph" aria-controls="distLevelGraph" role="tab" data-toggle="tab" title="graphical view"><i class="fa fa-bar-chart"></i></a></li>
												<li role="presentation" class="active" attr_tab_type="distTable" attr_level_type="district"><a href="#distLevelTable" aria-controls="distLevelTable" role="tab" data-toggle="tab" title="table view"><i class="fa fa-table"></i></a></li>
												<li role="presentation" class="comaprisionViewShow" attr_tab_type="distcompar" attr_level_type="district"><a href="#comaprisionLevelDist" aria-controls="comaprisionLevelDist" role="tab" data-toggle="tab" title="comparison" attr_compision = "Comparision"><i class="fa fa-code-fork"></i></a></li>
												<!--<li role="presentation" class="" attr_tab_type="distCummulative" attr_level_type="district"><a href="#distLevelCummulative" aria-controls="distLevelCummulative" role="tab" data-toggle="tab">Cummulative</a></li>-->
												<li role="presentation"><a class="advancedSearchStyle stateProSubProCls selectboxsShowHide" attr_switch = "distLevel"><i class="fa fa-search-plus"></i></a></li>
											</ul>
										</div>
									</div>
								<div class="row selectboxsShowHide m_top20">
									<div class="col-sm-6 showHideDistrictPgramsCls" style="display:none;" >
										<div class="row">
											<div class="col-sm-4">
												<label>PROGRAMS</label>
												<select class="form-control chosenSelect" id="programNamesDistrict" >
												</select>
											</div>
											<div class="col-sm-4">
												<label>SUB PROGRAMS</label>
												<select class="form-control chosenSelect" id="grantTypeDistrict" >
												</select>
												
											</div>
											<div class="col-sm-4">
												<label>SCHEMS</label>
												<select class="form-control chosenSelect" id="subProgramNamesDistrict" >
												</select>
											</div>
										</div>
									</div>
									<div class="col-sm-3 distLevelCls distLevelCls1 selectBoxCommonCls" >
										<label>DISTRICT</label>
										<select class="form-control chosenSelect" id="distLevelDistrictNames" >
										</select>
									</div>
									<div class="col-sm-3 distLevelparliamentCls distLevelCls1 selectBoxCommonCls" style="display:none;">
										<label>PARLIAMENT</label>
										<select class="form-control chosenSelect" id="distLevelParliamentNames" >
										</select>
									</div>
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<!-- Tab panes -->
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane " id="distLevelGraph">
												<div class="row m_top10">
													<div class="col-sm-3">
														<ul class="list-inline activeUlCls sortingDivDistCls constituencyUl">
															<li class="active " attr_sorting_type="count" attr_order_type="desc">
																<i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="count" attr_order_type="asc">
																<i class="fa fa-sort-amount-asc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="asc">
																A-Z
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="desc">
																Z-A
															</li>
														</ul>
													</div>
													<div class="col-sm-12 m_top10 ">
														<div id="distLevlOvervw"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane active" id="distLevelTable">
												<div class="row">
													<div class="col-sm-12">
														<div id="distLevlOvervwTable"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="comaprisionLevelDist">
												<div class="row">
													<div class="col-sm-12 m_top10">
														<div class="row">
															<div class="col-sm-2 pull-right">
																<button type="button" class="btn btn-sm btn-success comapreFinancialYearCls" attr_type="distComp">submit</button>
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compSingleFinancialYear distYearCls"  ></select>
															</div>
															<div class="col-sm-1 pull-right">
															VS
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compMultiFinancialYear multiDistYearCls"></select>
															</div>
														</div>
														<div id="comparionDistLevlOvervwTable" class="m_top10"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane " id="distLevelCummulative">
												<div class="row">
													<div class="col-sm-12">
														<div id="distLevelCummulativeOvervw"></div>
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
				
				<div class="col-sm-12 constituencyBlock">
					<div class="panel panel-default panel-black" id="headingThree">
						<div class="panel-heading">
						 	<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed collapseActiveConstCls"  role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										<h4 class="panel-title text-capital">constituency level overview</h4>
									</a>
								</div>
							</div>
						</div>
						 <div id="collapseThree" class="panel-collapse collapse collapseActiveCls" role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<!--<ul class="switch-btn consLevelActive" tab-switch="consLevel">
											<li class="active" attr_type="overview">overview</li>
											<li attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept_Scheme Level</li>
										</ul>-->
										<div class="row">
											<div class="col-sm-6 panalActiveCls">
												<ul class="switch-btn-New consLevelActive selectboxsShowHide" role="tablevelValue" tab-switch="consLevel" attr_switch = "consLevel">
													<li  class="active constituencyActiveCls" attr_type="districtType" attr_result_type="district">District</li>
													<li   class="parliamentCls" attr_type="parliamentType" attr_result_type="parliament">Parliament</li>
												</ul>
												<ul class="switch-btn-New tabCummulativeConstituency selectboxsShowHide" role="tabCummulative" attr_switch = "consLevel">
													<li class="active ActiveConstituencyCls" attr_type="cummulativeView">Cummulative View</li>
													<li  attr_type="normalView">Year Wise</li>
												</ul>
											</div>
											<div class="col-sm-6">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right tabTypeConsCls" role="tablist" id="tabCons">
													<li role="presentation" attr_tab_type="consGraph" attr_level_type="constituency"><a href="#consLevelGraph" aria-controls="consLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
													<li role="presentation" class="active" attr_tab_type="consTable" attr_level_type="constituency"><a href="#consLevelTable" aria-controls="consLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
													<li role="presentation" class="comaprisionViewShow" attr_tab_type="consCompar" attr_level_type="constituency"><a href="#comaprisionLevelConst" aria-controls="comaprisionLevelConst" role="tab" data-toggle="tab" attr_compision = "Comparision"><i class="fa fa-code-fork"></i></a></li>
													<!--<li role="presentation" class="" attr_tab_type="consCummulative" attr_level_type="constituency"><a href="#consLevelCummulative" aria-controls="consLevelCummulative" role="tab" data-toggle="tab">Cummulative</a></li>-->
													<li role="presentation"><a class="advancedSearchStyle stateProSubProCls selectboxsShowHide" attr_switch ="consLevel"><i class="fa fa-search-plus"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="row selectboxsShowHide m_top10 ">
										<div class="col-sm-3 showHideConstituencyPgramsCls" style="display:none;">
											<label>PROGRAMS</label>
											<select class="form-control chosenSelect" id="programNamesConst" >
											</select>
										</div>
										<div class="col-sm-3 showHideConstituencyPgramsCls" style="display:none;">
											<label>SUB PROGRAMS</label>
											<select class="form-control chosenSelect" id="grantTypeConst" >
											</select>
											
										</div>
										<div class="col-sm-3 showHideConstituencyPgramsCls" style="display:none;">
											<label>SCHEMS</label>
											<select class="form-control chosenSelect" id="subProgramNamesConst" >
											</select>
										</div>
										<div class="col-sm-3 constiLevelDistCls selectBoxCommonCls" >
											<label>DISTRICT</label>
											<select class="form-control chosenSelect " id="constLevelDistNames" >
												<option value="0"> SELECT DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3 parlaiLevelDistCls selectBoxCommonCls" style="display:none;">
											<label>PARLIAMENT</label>
											<select class="form-control chosenSelect" id="constLevelParliaNames" >
											</select>
										</div>
										<div class="col-sm-3 constiLevelCls selectBoxCommonCls" >
											<label>CONSTITUENCY</label>
											<select class="form-control chosenSelect" id="constLevelConstNames" >
											</select>
										</div>
									
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane " id="consLevelGraph">
												<div class="row">
													<div class="col-sm-3">
														<ul class="list-inline activeUlCls sortingDivConstCls constituencyUl">
															<li class="active" attr_sorting_type="count" attr_order_type="desc">
																<i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="count" attr_order_type="asc">
																<i class="fa fa-sort-amount-asc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="asc">
																A-Z
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="desc">
																Z-A
															</li>
														</ul>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-12">
														<div id="consLevlOvervw"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane active" id="consLevelTable">
												<div class="row">
													<div class="col-sm-12">
														<div id="consLevlOvervwTable"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="comaprisionLevelConst">
												<div class="row">
													<div class="col-sm-12 m_top10">
														<div class="row"> 
															<div class="col-sm-2 pull-right">
																<button type="button" class="btn btn-sm btn-success comapreFinancialYearCls" attr_type="constComp">submit</button>
															</div>													
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compSingleFinancialYear conYearCls"  ></select>
															</div>
															<div class="col-sm-1 pull-right">
															VS
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compMultiFinancialYear multiConYearCls" ></select>
															</div>
															
														</div>
														<div id="comparionConstLevlOvervwTable" class="m_top10"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane " id="consLevelCummulative">
												<div class="row">
													<div class="col-sm-12">
														<div id="consLevelCummulativeOvervw"></div>
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
				<div class="col-sm-12 mandalBlock">
					<div class="panel panel-default panel-black" id="headingFour">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed collapseActiveMandalCls" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
										<h4 class="panel-title text-capital">mandal level overview</h4>
									</a>
								</div>
							</div>
						</div>
						 <div id="collapseFour" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingFour">
						<div class="panel-body">
							<div>
								<div class="row">
									<div class="col-sm-12">
										<!--<ul class="switch-btn mandalLevelActive" tab-switch="mandalLevel">
											<li class="active" attr_type="overview">overview</li>
											<li attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept_Scheme Level</li>
										</ul>-->
										<div class="row">
											<div class="col-sm-6 panalActiveCls">
												<ul class="switch-btn-New mandalLevelActive selectboxsShowHide" role="tablevelValue" tab-switch="mandalLevel" attr_switch = "mandalLevel">
													<li  class="active mandalActiveCls" attr_type="districtType" attr_result_type="district">District</li>
													<li  attr_type="parliamentType"  class="parliamentCls" attr_result_type="parliament">Parliament</li>
												</ul>
												<ul class="switch-btn-New tabCummulativeMandal selectboxsShowHide" role="tabCummulative" attr_switch = "mandalLevel">
													<li class="active ActiveMandalCls" attr_type="cummulativeView">Cummulative View</li>
													<li  attr_type="normalView">Year Wise</li>
												</ul>
											</div>
											<div class="col-sm-6">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right tabTypeMandalCls" role="tablist" id="tabMan">
													<li role="presentation" attr_tab_type="mandalGraph" attr_level_type="mandal"><a href="#mandalLevelGraph" aria-controls="mandalLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
													<li role="presentation" class="active" attr_tab_type="mandalTable" attr_level_type="mandal"><a href="#mandalLevelTable" aria-controls="mandalLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table "></i></a></li>
													<li role="presentation" class="comaprisionViewShow" attr_tab_type="mandalComp" attr_level_type="mandal"><a href="#comaprisionLevelMandal" aria-controls="comaprisionLevelMandal" role="tab" data-toggle="tab" attr_compision = "Comparision"><i class="fa fa-code-fork"></i></a></li>
													<!--<li role="presentation" class="" attr_tab_type="mandalCummulative" attr_level_type="mandal"><a href="#mandalLevelCummulative" aria-controls="mandalLevelCummulative" role="tab" data-toggle="tab">Cummulative</a></li>-->	
													<li role="presentation"><a class="advancedSearchStyle stateProSubProCls selectboxsShowHide" attr_switch ="mandalLevel"><i class="fa fa-search-plus"></i></a></li>
												</ul>  
											</div>
										</div>
									</div>
								</div>
								<div class="row selectboxsShowHide m_top10">
										<div class="col-sm-3 showHideMandalPgramsCls" style="display:none;">
											<label>PROGRAMS</label>
											<select class="form-control chosenSelect" id="programNamesMandal" >
											</select>
										</div>
										<div class="col-sm-3 showHideMandalPgramsCls" style="display:none;">
											<label>SUB PROGRAMS</label>
											<select class="form-control chosenSelect" id="grantTypeMandal" >
											</select>
											
										</div>
										<div class="col-sm-3 showHideMandalPgramsCls" style="display:none;">
											<label>SCHEMS</label>
											<select class="form-control chosenSelect" id="subProgramNamesMandal" >
											</select>
										</div>
										<div class="col-sm-3 mandalLevelDistCls selectBoxCommonCls" >
											<label>DISTRICT</label>
											<select class="form-control chosenSelect" id="mandalLevelDistNames" >
												<option value="0"> SELECT DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3 levelparliamentConstiCls selectBoxCommonCls" style="display:none">
											<label>PARLIAMENT</label>
											<select class="form-control chosenSelect" id="parliamentLevelConstNames" >
											</select>
										</div>
										<div class="col-sm-3 levelmandalConstiCls selectBoxCommonCls" >
											<label>CONSTITUENCY</label>
											<select class="form-control chosenSelect" id="mandalLevelConstNames" >
											</select>
										</div>
										<div class="col-sm-3 mandalLevelCls selectBoxCommonCls" >
											<label>MANDAL</label>
											<select class="form-control chosenSelect" id="mandalLevelMandalNames" >
											</select>
										</div>
									
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane " id="mandalLevelGraph">
												<div class="row">
													<div class="col-sm-3">
														<ul class="list-inline activeUlCls sortingDivMandalCls constituencyUl">
															<li class="active" attr_sorting_type="count" attr_order_type="desc">
																<i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="count" attr_order_type="asc">
																<i class="fa fa-sort-amount-asc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="asc">
																A-Z
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="desc">
																Z-A
															</li>
														</ul>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-12">
														<div id="mandalLevlOvervw"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane active" id="mandalLevelTable">
												<div class="row">
													<div class="col-sm-12">
														<div id="mandalLevlOvervwTable"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="comaprisionLevelMandal">
												<div class="row">
													<div class="col-sm-12 m_top10">
														<div class="row">
															<div class="col-sm-2 pull-right">
																<button type="button" class="btn btn-sm btn-success comapreFinancialYearCls" attr_type="mandalComp">submit</button>
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compSingleFinancialYear mandalYearCls" ></select>
															</div>
															<div class="col-sm-1 pull-right">
															VS
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compMultiFinancialYear multiMandalYearCls"  ></select>
															</div>
														</div>
														<div id="comparionMandalLevlOvervwTable" class="m_top10"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane " id="mandalLevelCummulative">
												<div class="row">
													<div class="col-sm-12">
														<div id="mandalLevelCummulativeOvervw"></div>
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
				
				
				<div class="col-sm-12 villageBlock">
					<div class="panel panel-default panel-black" id="headingFive">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed collapseActiveVillageCls" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
										<h4 class="panel-title text-capital">Village level overview</h4>
									</a>
								</div>
							</div>
						</div>
						<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
						<div class="panel-body">
							<div>
								<div class="row">
									<div class="col-sm-12">
										<!--<ul class="switch-btn villageLevelActive" tab-switch="villageLevel">
											<li class="active" attr_type="overview">overview</li>
											<li  attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept & Scheme Level</li>
										</ul>-->
										<div class="row">
											<div class="col-sm-6 panalActiveCls">
												<ul class="switch-btn-New villageLevelActive selectboxsShowHide" role="tablevelValue" tab-switch="villageLevel" attr_switch = "villageLevel">
													<li class="active villageActiveCls" attr_type="districtType" attr_result_type="district">District</li>
													<li attr_type="parliamentType" class="parliamentCls" attr_result_type="parliament">Parliament</li>
												</ul>
												<ul class="switch-btn-New tabCummulativeVillage selectboxsShowHide" role="tabCummulative" attr_switch = "villageLevel">
													<li class="active ActiveVillageCls" attr_type="cummulativeView">Cummulative View</li>
													<li  attr_type="normalView" class="panchyatLevelYearWiseViewCls">Year Wise</li>
												</ul>
											</div>
											<div class="col-sm-6">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right tabTypeVillageCls" role="tablist" id="tabVill">
													<!--<li role="presentation" ><a href="#villageLevelGraph" aria-controls="villageLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>-->
													<li role="presentation" class="active" attr_tab_type="villageTable" attr_level_type="village"><a href="#villageLevelTable" aria-controls="villageLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table "></i></a></li>
													<li role="presentation" class="comaprisionViewShow" attr_tab_type="villageComp" attr_level_type="village"><a href="#comaprisionLevelVillage" aria-controls="comaprisionLevelVillage" role="tab" data-toggle="tab"attr_compision = "Comparision" ><i class="fa fa-code-fork"></i></a></li>
													<!--<li role="presentation" class="" attr_tab_type="villageCummulative" attr_level_type="village"><a href="#villageLevelCummulative" aria-controls="villageLevelCummulative" role="tab" data-toggle="tab">Cummulative</a></li>-->	
													<li role="presentation"><a class="advancedSearchStyle stateProSubProCls selectboxsShowHide" attr_switch ="villageLevel"><i class="fa fa-search-plus"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							 
								<div class="row selectboxsShowHide m_top10">
										<div class="col-sm-3 showHideVillagePgramsCls" style="display:none;">
											<label>PROGRAMS</label>
											<select class="form-control chosenSelect" id="programNamesVillage" >
											</select>
										</div>
										<div class="col-sm-3 showHideVillagePgramsCls" style="display:none;">
											<label>SUB PROGRAMS</label>
											<select class="form-control chosenSelect" id="grantTypeVillage" >
											</select>
											
										</div>
										<div class="col-sm-3 showHideVillagePgramsCls" style="display:none;">
											<label>SCHEMS</label>
											<select class="form-control chosenSelect" id="subProgramNamesVillage" >
											</select>
										</div>
								
										<div class="col-sm-3 villageLevelDistCls selectBoxCommonCls" >
											<label>DISTRICT</label>
											<select class="form-control chosenSelect" id="villageLevelDistNames" >
												<option value="0"> SELECT DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3 villageLevelParliCls selectBoxCommonCls" style="display:none">
											<label>PARLIAMENT</label>
											<select class="form-control chosenSelect" id="villageLeveParliNames" >
											</select>
										</div>
										<div class="col-sm-2 villageLevelConstiCls selectBoxCommonCls" >
											<label>CONSTITUENCY</label>
											<select class="form-control chosenSelect" id="villageLevelConstNames" >
											</select>
										</div>
										<div class="col-sm-3 villageLevelMandalCls selectBoxCommonCls " >
											<label>MANDAL</label>
											<select class="form-control chosenSelect" id="villageLevelMandalNames" >
											</select>
										</div>
										<div class="col-sm-3 villageLevelCls selectBoxCommonCls" >
											<label>VILLAGE</label>
											<select class="form-control chosenSelect" id="villageLevelNames" >
											</select>
										</div>
									
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane " id="villageLevelGraph">
												<div class="row">
													<div class="col-sm-3">
														<ul class="list-inline activeUlCls sortingDivVillageCls constituencyUl">
															<li class="active" attr_sorting_type="count" attr_order_type="desc">
																<i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="count" attr_order_type="asc">
																<i class="fa fa-sort-amount-asc" aria-hidden="true"></i>
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="asc">
																A-Z
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="desc">
																Z-A
															</li>
														</ul>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-12">
														<div id="villageLevlOvervw"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane active" id="villageLevelTable">
												<div class="row">
													<div class="col-sm-12">
														<div id="villageLevlOvervwTable"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="comaprisionLevelVillage">
												<div class="row">
													<div class="col-sm-12 m_top10">
														<div class="row">
															<div class="col-sm-2 pull-right">
																<button type="button" class="btn btn-sm btn-success comapreFinancialYearCls" attr_type="villageComp">submit</button>
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compSingleFinancialYear villageYearCls" ></select>
															</div>
															<div class="col-sm-1 pull-right">
															VS
															</div>
															<div class="col-sm-2 pull-right">
																<select class="form-control chosenSelect compMultiFinancialYear multiVillageYearCls"  ></select>
															</div>
														</div>
														<div id="comparionVillageLevlOvervwTable" class="m_top10"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane " id="villageLevelCummulative">
												<div class="row">
													<div class="col-sm-12">
														<div id="villageLevelCummulativeOvervw"></div>
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
	</section>
</main>
<div class="modal fade" id="locDivModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:80%;margin:auto">
		<div class="modal-content">
			<div class="modal-header bg_EE">
				<div class="row">
					<div class="col-md-6 col-xs-12 col-sm-6">
						<h4 class="modal-title text-capitalize" id="">Location Wise Fund Overview</h4>
						<h6 id=""></h6>
					</div>
					<div class="col-md-3 col-md-offset-3 col-xs-12 col-sm-6">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">      
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="locationsModal" style="height:250px"></div>
					</div>      
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="fundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:80%;margin:auto">
		<div class="modal-content">
			<div class="modal-header bg_EE">
				<div class="row">
					<div class="col-md-8">
						<h4 class="modal-title text-capitalize" id="diptNameId">Location Wise Fund Overview</h4>
						<h6 id="officeNameId"></h6>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				   
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
		 
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="fundSanctionModal"></div>
					</div>  
		  
				</div>
			</div>
		</div>
	</div>
</div>	
<!-- Document Model -->
<div class="modal fade" tabindex="-1" id="govtOrderDocumentId" role="dialog" style="z-index:99999;">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Govt Order Document</h4>  
			</div>
			<div class="modal-body" id="govtOrderDocumentBodyId"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->  
</div><!--  /.modal -->

<div class="modal fade" tabindex="-1" id="rwsModalDivId" role="dialog" style="z-index:99999;">
	<div class="modal-dialog" style="width:90%;">
		<div class="modal-content modal-custom">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="modalTitleRwsId"></h4>  
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-12">
						<div id="habilitationsBlockId"></div>
					</div>	
					<div class="col-sm-12 m_top20">
						<div id="kpiBlockId"></div>
					</div>
					<div class="col-sm-12 m_top20">
						<div id="assestsBlockId"></div>
					</div>
					<div class="col-sm-12 m_top20">
						<div id="waterSchemsBlockId"></div>
					</div>
				</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->  
</div><!--  /.modal -->
</div><!--  /.modal -->
<div class="modal fade" id="fundCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:98%;margin:auto">
		<div class="modal-content">
			<div class="modal-header bg_EE">
				<div class="row">
					<div class="col-md-8">
						<h4 class="modal-title text-capitalize" id="diptCatNameId">Location Wise Fund Overview</h4>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				   
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
		 
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="fundSanctionCategoryModal"></div>
					</div>  
		  
				</div>
			</div>
		</div>
	</div>
</div>

<form id="menuselectionFormId" name="menuselectionFormId">
	<input type="hidden" value="2" id="searchLevelId" name="searchLevelId" />
	<input type="hidden" value="1" id="searchLevelValue" name="searchLevelValue" />
	<input type="hidden" value="RWS" id="fromPage" name="fromPage" />
	<input type="hidden" value="RWS" id="toPage" name="toPage" />	
</form>  
<footer></footer>
<script type="text/javascript" src="Assests/js/jquery-1.11.3.js"></script>        
<script type="text/javascript" src="Assests/js/bootstrap.js"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script> 
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/js/locationHierarchy.js"></script>
<script type="text/javascript" src="Assests/fundManagament/newfundManagementDashboard.js"></script>
<!--Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations-->
<script>
var glSearchLevelId =2;
var glSearchLevelValue ="1";
var glFromPage="FMS";
var glToPage="";
</script>
</body>
</html>