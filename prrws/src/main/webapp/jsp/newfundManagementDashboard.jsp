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
							<ul>
								<li>
									<a href="fundManagementDashboard">
										<h3>FMS</h3>
										<p>Fund Management System</p>
									</a>
								</li>
								<li>
									<a href="MGNREGSDashboard">
										<h3>MGNREGS</h3>
										<p>Mahatma Gandhi Rural employement guarantee scheme</p>
									</a>
								</li>
								<li>
									<a href="ruralWaterSupplyDashBoard">
										<h3>RWS</h3>
										<p>rural water <br/> supply</p>
									</a>
								</li>
								<li>
									<a href="prisDashBoard">
										<h3>PRIS</h3>
										<p>PRIS <br/>Survey DashBoard</p>
									</a>
								</li>
								<li>
									<a href="">
										<h3>ENC</h3>
										<p>engineering department</p>
									</a>
								</li>
							</ul>
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
						<li style="position:relative;top:-13px;">
							<span id="selectedName">Andhra Pradesh</span>
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
				<div id="overviewBlock"></div>
			</div>
			<div class="row m_top20">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<table class="table">
								<thead>
									<th><select id="overviewSelect"></select></th>
									<th><i class="rounded-circle fa fa-inr"></i><p class="text-capital">total amount</p></th>
									<th><i class="rounded-circle">D</i><p class="text-capital">district</p></th>
									<th><i class="rounded-circle">C</i><p class="text-capital">constituency</p></th>
									<th><i class="rounded-circle">M</i><p class="text-capital">Mandal</p></th>
								</thead>
								<tbody id="overViewTableId"></tbody>
							</table>
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
					<div class="panel panel-default panel-black" >
					 	<div class="panel-heading">
								<div class="row">
									<div class="col-sm-12" >
										<a class="panelCollapseIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
											<h4 class="panel-title text-capital">State level overview</h4>
										</a>
									</div>
								</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<!--<ul class="switch-btn" tab-switch="stateLevel">
											<li class="active" attr_type="overview">overview</li>
											<li attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept_Scheme Level</li>
										</ul>-->
										<ul class="list-inline">
											<li class="col-sm-2">
												<select class="form-control chosenSelect" id="programNamesState" >
												</select>
											</li>
											<li class="col-sm-2">
												<select class="form-control chosenSelect" id="subProgramNamesState" >
												</select>
											</li>
										</ul>
										<ul class="nav nav-tabs pull-right" role="tablist" id="tabSt">
											<li role="presentation" ><a href="#stateLevelGraph" aria-controls="stateLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
											<li role="presentation" class="active"><a href="#stateLevelTable" aria-controls="stateLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
										</ul>
									</div>
								</div>
								<div class="row m_top20">
									<div class="col-sm-12">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane " id="stateLevelGraph">
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
				<div class="col-sm-12">
					<div class="panel panel-default panel-black" id="headingTwo">
						<div class="panel-heading">
							
							<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
										<h4 class="panel-title text-capital">district level overview</h4>
									</a>
								</div>
							</div>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<!--<ul class="switch-btn distLevelActive" tab-switch="distLevel">
											<li class="active" attr_type="overview">overview</li>
											<li attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept_Scheme Level</li>
										</ul>-->
										<div class="row">
											<!-- <div class="col-sm-2">
												<ul class="switch-btn-New distLevelActive" tab-switch="distLevel">
													<li class="active" attr_type="districtType">District</li>
													<li attr_type="parliamentType">Parliament</li>
												</ul>
											</div>-->
											<div class="col-sm-6 ">
												<ul class="list-inline selectboxsShowHide">
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="programNamesDistrict" >
														</select>
													</li>
													<li class="col-sm-5">
														<select class="form-control chosenSelect" id="subProgramNamesDistrict" >
														</select>
													</li>
												</ul>
											</div>
											<div class="col-sm-6">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right" role="tablist" id="tabDis">
													<li role="presentation" ><a href="#distLevelGraph" aria-controls="distLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
													<li role="presentation" class="active"><a href="#distLevelTable" aria-controls="distLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
													<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelDist" aria-controls="comaprisionLevelDist" role="tab" data-toggle="tab">Comparision</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="row selectboxsShowHide m_top10">
									<div class="col-sm-3 distLevelCls distLevelCls1" >
										<select class="form-control chosenSelect" id="distLevelDistrictNames" >
										</select>
									</div>
									<div class="col-sm-3 distLevelparliamentCls distLevelCls1" style="display:none;">
										<select class="form-control chosenSelect" id="distLevelParliamentNames" >
										</select>
									</div>
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<!-- Tab panes -->
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane " id="distLevelGraph">
												<div class="row">
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
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="panel panel-default panel-black" id="headingThree">
						<div class="panel-heading">
						 	<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed"  role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										<h4 class="panel-title text-capital">constituency level overview</h4>
									</a>
								</div>
							</div>
						</div>
						 <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<!--<ul class="switch-btn consLevelActive" tab-switch="consLevel">
											<li class="active" attr_type="overview">overview</li>
											<li attr_type="scheme">Scheme level</li>
											<li attr_type="deptscheme">Dept_Scheme Level</li>
										</ul>-->
										<div class="row">
											<div class="col-sm-2">
												<ul class="switch-btn-New consLevelActive selectboxsShowHide" tab-switch="consLevel">
													<li class="active" attr_type="districtType">District</li>
													<li attr_type="parliamentType">Parliament</li>
												</ul>
											</div>
											<div class="col-sm-5">
												<ul class="list-inline selectboxsShowHide">
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="programNamesConst" >
														</select>
													</li>
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="subProgramNamesConst" >
														</select>
													</li>
												</ul>
											</div>
											<div class="col-sm-5">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right" role="tablist" id="tabCons">
													<li role="presentation" ><a href="#consLevelGraph" aria-controls="consLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
													<li role="presentation" class="active"><a href="#consLevelTable" aria-controls="consLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
													<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelConst" aria-controls="comaprisionLevelConst" role="tab" data-toggle="tab">Comparision</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="row selectboxsShowHide m_top10">
										<div class="col-sm-3 constiLevelDistCls " >
											<select class="form-control chosenSelect " id="constLevelDistNames" >
												<option value="0"> SELECT DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3 parlaiLevelDistCls " style="display:none;">
											<select class="form-control chosenSelect" id="constLevelParliaNames" >
											</select>
										</div>
										<div class="col-sm-3 constiLevelCls " >
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
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>	
				<div class="col-sm-12">
					<div class="panel panel-default panel-black" id="headingFour">
						<div class="panel-heading">
						
							<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
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
											<div class="col-sm-2">
												<ul class="switch-btn-New mandalLevelActive selectboxsShowHide" tab-switch="mandalLevel">
													<li class="active" attr_type="districtType">District</li>
													<li attr_type="parliamentType">Parliament</li>
												</ul>
											</div>
											<div class="col-sm-5">
												<ul class="list-inline selectboxsShowHide">
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="programNamesMandal" >
														</select>
													</li>
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="subProgramNamesMandal" >
														</select>
													</li>
												</ul>
											</div>
											<div class="col-sm-5">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right" role="tablist" id="tabMan">
													<li role="presentation" ><a href="#mandalLevelGraph" aria-controls="mandalLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
													<li role="presentation" class="active"><a href="#mandalLevelTable" aria-controls="mandalLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table "></i></a></li>
													<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelMandal" aria-controls="comaprisionLevelMandal" role="tab" data-toggle="tab">Comparision</a></li>   
												</ul>  
											</div>
										</div>
									</div>
								</div>
								<div class="row selectboxsShowHide m_top10">
									<div class="col-sm-3 mandalLevelDistCls" >
										<select class="form-control chosenSelect" id="mandalLevelDistNames" >
											<option value="0"> SELECT DISTRICT</option>
										</select>
									</div>
									<div class="col-sm-3 levelparliamentConstiCls" style="display:none">
										<select class="form-control chosenSelect" id="parliamentLevelConstNames" >
										</select>
									</div>
									<div class="col-sm-3 levelmandalConstiCls" >
										<select class="form-control chosenSelect" id="mandalLevelConstNames" >
										</select>
									</div>
									<div class="col-sm-3 mandalLevelCls" >
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
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
				
				
				<div class="col-sm-12">
					<div class="panel panel-default panel-black" id="headingFive">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-12">
									<a class="panelCollapseIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
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
											<div class="col-sm-2">
												<ul class="switch-btn-New villageLevelActive selectboxsShowHide" tab-switch="villageLevel">
													<li class="active" attr_type="districtType">District</li>
													<li attr_type="parliamentType">Parliament</li>
												</ul>
											</div>
											<div class="col-sm-5">
												<ul class="list-inline selectboxsShowHide">
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="programNamesVillage" >
														</select>
													</li>
													<li class="col-sm-4">
														<select class="form-control chosenSelect" id="subProgramNamesVillage" >
														</select>
													</li>
												</ul>
											</div>
											<div class="col-sm-5">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs pull-right" role="tablist" id="tabVill">
													<!--<li role="presentation" ><a href="#villageLevelGraph" aria-controls="villageLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>-->
													<li role="presentation" class="active"><a href="#villageLevelTable" aria-controls="villageLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table "></i></a></li>
													<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelVillage" aria-controls="comaprisionLevelVillage" role="tab" data-toggle="tab">Comparision</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="row selectboxsShowHide m_top10">
									<div class="col-sm-2 villageLevelDistCls" >
										<select class="form-control chosenSelect" id="villageLevelDistNames" >
											<option value="0"> SELECT DISTRICT</option>
										</select>
									</div>
									<div class="col-sm-2 villageLevelParliCls" style="display:none">
										<select class="form-control chosenSelect" id="villageLeveParliNames" >
										</select>
									</div>
									<div class="col-sm-2 villageLevelConstiCls" >
										<select class="form-control chosenSelect" id="villageLevelConstNames" >
										</select>
									</div>
									<div class="col-sm-2 villageLevelMandalCls" >
										<select class="form-control chosenSelect" id="villageLevelMandalNames" >
										</select>
									</div>
									<div class="col-sm-2 villageLevelCls" >
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
					<div class="col-md-6 col-xs-12 col-sm-6">
						<h4 class="modal-title text-capitalize" id="diptNameId">Location Wise Fund Overview</h4>
						<h6 id="officeNameId"></h6>
					</div>
					<div class="col-md-3 col-md-offset-3 col-xs-12 col-sm-6">
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