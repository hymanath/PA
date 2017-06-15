<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
					<p>Funds Management System</p>
				</div>
				<div class="col-sm-1 pull-right hidden-xs">
					<img src="Assests/images/lokesh.png" class="m_top10"/>
				</div>
			</div>
		</div>
	</nav>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<ul class="list-inline navbar-header-custom">
						<li><label>Note: All Amount in Lakhs</label></li>
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
				<div class="col-sm-12">
					<table class="table table-bordered table-overview">
						<tr>
							<td>
								<h4 class="panel-title text-muted">High Funded District</h4>
								<div id="highFundDist" attr_id="highFundDist" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">High Funded Constituency</h4>
								<div id="highFundCons" attr_id="highFundCons" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">High Funded Mandal</h4>
								<div id="highFundMandal" attr_id="highFundMandal" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">High Funded Village</h4>
								<div id="highFundVillage" attr_id="highFundVillage" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">High Funded Grant Type</h4>
								<div id="highFundSource" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">High Funded Scheme</h4>
								<div id="highFundScheme" attr_id="highFundScheme" class="m_top5"></div>
							</td>
						</tr>
						<tr>
							<td>
								<h4 class="panel-title text-muted">Low Funded District</h4>
								<div id="lowFundDist" attr_id="lowFundDist" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Low Funded Costituency</h4>
								<div id="lowFundCons" attr_id="lowFundCons" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Low Funded Mandal</h4>
								<div id="lowFundMandal" attr_id="lowFundMandal" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Low Funded Village</h4>
								<div id="lowFundVillage" attr_id="lowFundVillage" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Low Funded Grant Type</h4>
								<div id="lowFundSource" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Low Funded Scheme</h4>
								<div id="lowFundScheme" attr_id="lowFundScheme" class="m_top5"></div>
							</td>
						</tr>
						<tr>
							<td>
								<h4 class="panel-title text-muted">Average Funded District</h4>
								<div id="avgFundDist" attr_id="avgFundDist" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Average Funded Costituency</h4>
								<div id="avgFundCons" attr_id="avgFundCons" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Average Funded Mandal</h4>
								<div id="avgFundMandal" attr_id="avgFundMandal" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Average Funded Village</h4>
								<div id="avgFundVillage" attr_id="avgFundVillage" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Average Funded Grant Type</h4>
								<div id="avgFundSource" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Average Funded Scheme</h4>
								<div id="avgFundScheme" attr_id="avgFundScheme" class="m_top5"></div>
							</td>
						</tr>
						<tr>
							<td>
								<h4 class="panel-title text-muted">Total Funds</h4>
								<div id="totFund" attr_id="totFund" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Total Funded Costituency</h4>
								<div id="totFundCons" attr_id="totFundCons" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Total Funded Mandal</h4>
								<div id="totFundMandal" attr_id="totFundMandal" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Total Funded Village</h4>
								<div id="totFundVillage" attr_id="totFundVillage" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Total Funded Grant Types</h4>
								<div id="totFundSource" class="m_top5"></div>
							</td>
							<td>
								<h4 class="panel-title text-muted">Total Funded Scheme</h4>
								<div id="totFundScheme" attr_id="totFundScheme" class="m_top5"></div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	<section>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital">State level overview</h4>
								</div>
								<div class="col-sm-6">
									<ul class="switch-btn pull-right" tab-switch="stateLevel">
										<li class="active" attr_type="overview">overview</li>
										<li attr_type="scheme">Scheme level</li>
										<!--<li attr_type="deptscheme">Dept_Scheme Level</li>-->
									</ul>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<ul class="nav nav-tabs pull-right" role="tablist">
										<li role="presentation" class="active"><a href="#stateLevelGraph" aria-controls="stateLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
										<li role="presentation"><a href="#stateLevelTable" aria-controls="stateLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
									</ul>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="stateLevelGraph">
											<div class="row">
												<div class="col-sm-12">
													<div id="stateLevlOvervw"></div>	
												</div>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane" id="stateLevelTable">
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
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital">district level overview</h4>
								</div>
								<div class="col-sm-6">
									<ul class="switch-btn pull-right distLevelActive" tab-switch="distLevel">
										<li class="active" attr_type="overview">overview</li>
										<li attr_type="scheme">Scheme level</li>
										<li attr_type="deptscheme">Dept_Scheme Level</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs pull-right" role="tablist" id="tabDis">
										<li role="presentation" class="active"><a href="#distLevelGraph" aria-controls="distLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
										<li role="presentation"><a href="#distLevelTable" aria-controls="distLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
										<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelDist" aria-controls="comaprisionLevelDist" role="tab" data-toggle="tab">Comparision</a></li>
									</ul>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<!-- Tab panes -->
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="distLevelGraph">
											<div class="row">
												<div class="col-sm-3">
													<ul class="list-inline activeUlCls sortingDivDistCls constituencyUl">
														<li class="active " attr_sorting_type="count" attr_order_type="desc">
															<i class="glyphicon glyphicon-sort-by-attributes" ></i>
														</li>
														<li class="" attr_sorting_type="count" attr_order_type="asc">
															<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
														</li>
														<li class="" attr_sorting_type="name" attr_order_type="asc">
															A-Z
														</li>
														<li class="" attr_sorting_type="name" attr_order_type="desc">
															Z-A
														</li>
													</ul>
												</div>
												<div class="col-sm-3 distLevelCls" >
													<select class="form-control chosenSelect" id="distLevelDistrictNames" >
													</select>
												</div>
												<div class="col-sm-12 m_top10">
													<div id="distLevlOvervw"></div>
												</div>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane" id="distLevelTable">
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
															<select class="form-control chosenSelect compSingleFinancialYear"  ></select>
														</div>
														<div class="col-sm-1 pull-right">
														VS
														</div>
														<div class="col-sm-3 pull-right">
															<select class="form-control chosenSelect compMultiFinancialYear" multiple ></select>
														</div>
														
													</div>
													<div id="comparionDistLevlOvervwTable" class=""></div>
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
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital">constituency level overview</h4>
								</div>
								<div class="col-sm-6">
									<ul class="switch-btn pull-right consLevelActive" tab-switch="consLevel">
										<li class="active" attr_type="overview">overview</li>
										<li attr_type="scheme">Scheme level</li>
										<li attr_type="deptscheme">Dept_Scheme Level</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<ul class="nav nav-tabs pull-right" role="tablist" id="tabCons">
										<li role="presentation" class="active"><a href="#consLevelGraph" aria-controls="consLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
										<li role="presentation"><a href="#consLevelTable" aria-controls="consLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table"></i></a></li>
										<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelConst" aria-controls="comaprisionLevelConst" role="tab" data-toggle="tab">Comparision</a></li>
									</ul>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="consLevelGraph">
											<div class="row">
												<div class="col-sm-3">
													<ul class="list-inline activeUlCls sortingDivConstCls constituencyUl">
														<li class="active" attr_sorting_type="count" attr_order_type="desc">
															<i class="glyphicon glyphicon-sort-by-attributes" ></i>
														</li>
														<li class="" attr_sorting_type="count" attr_order_type="asc">
															<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
														</li>
														<li class="" attr_sorting_type="name" attr_order_type="asc">
															A-Z
														</li>
														<li class="" attr_sorting_type="name" attr_order_type="desc">
															Z-A
														</li>
													</ul>
												</div>
												<div class="col-sm-3 constiLevelDistCls" >
													<select class="form-control chosenSelect" id="constLevelDistNames" >
														<option value="0"> SELECT DISTRICT</option>
													</select>
												</div>
												<div class="col-sm-3 constiLevelCls" >
													<select class="form-control chosenSelect" id="constLevelConstNames" >
													</select>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div id="consLevlOvervw"></div>
												</div>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane" id="consLevelTable">
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
															<select class="form-control chosenSelect compSingleFinancialYear"  ></select>
														</div>
														<div class="col-sm-1 pull-right">
														VS
														</div>
														<div class="col-sm-3 pull-right">
															<select class="form-control chosenSelect compMultiFinancialYear" multiple ></select>
														</div>
														
													</div>
													<div id="comparionConstLevlOvervwTable" class=""></div>
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
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital">mandal level overview</h4>
								</div>
								<div class="col-sm-6">
									<ul class="switch-btn pull-right mandalLevelActive" tab-switch="mandalLevel">
										<li class="active" attr_type="overview">overview</li>
										<li attr_type="scheme">Scheme level</li>
										<li attr_type="deptscheme">Dept_Scheme Level</li>
									</ul>
								</div>
							</div>
							
						</div>
						<div class="panel-body">
							<div>
								<div class="row">
									<div class="col-sm-12">
										<ul class="nav nav-tabs pull-right" role="tablist" id="tabMan">
											<li role="presentation" class="active"><a href="#mandalLevelGraph" aria-controls="mandalLevelGraph" role="tab" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
											<li role="presentation"><a href="#mandalLevelTable" aria-controls="mandalLevelTable" role="tab" data-toggle="tab"><i class="fa fa-table "></i></a></li>
											<li role="presentation" class="comaprisionViewShow"><a href="#comaprisionLevelMandal" aria-controls="comaprisionLevelConst" role="tab" data-toggle="tab">Comparision</a></li>
										</ul>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="mandalLevelGraph">
												<div class="row">
													<div class="col-sm-3">
														<ul class="list-inline activeUlCls sortingDivMandalCls constituencyUl">
															<li class="active" attr_sorting_type="count" attr_order_type="desc">
																<i class="glyphicon glyphicon-sort-by-attributes" ></i>
															</li>
															<li class="" attr_sorting_type="count" attr_order_type="asc">
																<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="asc">
																A-Z
															</li>
															<li class="" attr_sorting_type="name" attr_order_type="desc">
																Z-A
															</li>
														</ul>
													</div>
													<div class="col-sm-3 mandalLevelDistCls" >
														<select class="form-control chosenSelect" id="mandalLevelDistNames" >
															<option value="0"> SELECT DISTRICT</option>
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
												<div class="row">
													<div class="col-sm-12">
														<div id="mandalLevlOvervw"></div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="mandalLevelTable">
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
																<select class="form-control chosenSelect compSingleFinancialYear" ></select>
															</div>
															<div class="col-sm-1 pull-right">
															VS
															</div>
															<div class="col-sm-3 pull-right">
																<select class="form-control chosenSelect compMultiFinancialYear" multiple ></select>
															</div>
														</div>
														<div id="comparionMandalLevlOvervwTable" class=""></div>
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
						<div id="fundSanctionModal" style="height:250px"></div>
					</div>  
		  
				</div>
			</div>
		</div>
	</div>
</div>	
</main>
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
<script type="text/javascript" src="Assests/fundManagament/fundManagementDashboard.js"></script>

<!--Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations-->
</body>
</html>