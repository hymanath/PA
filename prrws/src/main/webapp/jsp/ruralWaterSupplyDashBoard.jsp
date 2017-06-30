<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rural Water Supply Dashboard</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
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
					<p>Rural Water Supply - AP</p>
				</div>
				<div class="col-sm-1 hidden-xs col-sm-offset-5">
					<img src="Assests/images/lokesh.png" class="m_top10 pull-right"/>
				</div>
				<div class="col-sm-1 col-xs-12">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
				</div>
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
	</nav>
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 border_right">
					Note: All Amount in Lakhs
				</div>
				<div class="col-sm-3 border_right">
					<div class="form-horizontal">
						<div class="form-group form-group-sm">
							<label class="col-sm-4 control-label" for="formGroupInputLarge">Financial Year: </label>
							<div class="col-sm-8">
								<select id="financialYearId" class="chosenSelect"></select>
							</div>
						</div>
					</div>
				</div>
				<!--<div class="col-sm-6">
					<ul class="list-inline">
						<li><span class="color-label color-label-success"></span> 80% and above</li>
						<li><span class="color-label color-label-default"></span> 50% - 79%</li>
						<li><span class="color-label color-label-danger"></span> 0% - 49%</li>
					</ul>
				</div>-->
				<div class="col-sm-3">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
						<input type="text" class="form-control" id="dateRangePickerAUM" style="width: 200px;"/>
					</div>
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-10">
					<div class="row">
						<div class="col-sm-3">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Habitation Coverage</span></h5>
								<div class="chart" id="totalValues"></div>
								<h5 style="text-align: center; font-weight: bold;" id="totalCntTtlValues"></h5>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Water Source</span></h5>
								<div class="chart" id="waterSources"></div>
								<h5 style="text-align: center; font-weight: bold;" id="waterSourcesTtlValues"></h5>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Level Of Supply (MLD)</span></h5>
								<div class="chart" id="levelOfSupply1"></div>
								<h5 style="text-align: center; font-weight: bold;" id="levelSupplyTtlValues"></h5>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Schemes</span></h5>
								<div class="chart" id="schemes"></div>
								<h5 style="text-align: center; font-weight: bold;" id="schemesTtlValues"></h5>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Habitation Coverage Status</span><!--<p class="pull-right" style="margin-right: 20px;"><span class="roundClr" style="background-color:#14BAAD"></span>&nbsp;&nbsp;&nbsp;<span>2014-2015</span></p>--></h5>
								<div class="chart2" id="habitation"></div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Works</span></h5>
								<div class="chart2" id="habitationWorks"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Key Performance Indicators - <small>Habitations through Schemes</small></span></h5>
								<div class="chart2" id="keyPerformance"></div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Alert Status - Jalavani</span><!--<p class="pull-right" style="margin-right: 20px;"><span class="roundClr" style="background-color:#FC5049"></span>&nbsp;&nbsp;&nbsp;<span>2014-2015</span></p>--></h5>
								<div class="chart2" id="alertStatus"></div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Assets</span><!--<p class="pull-right" style="margin-right: 20px;"><span class="roundClr" style="background-color:#14BAAD"></span>&nbsp;&nbsp;&nbsp;<span>2014-2015</span></p>--></h5>
								<div class="chart2" id="assets"></div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="white-block">
								<h5 style="padding:5px"><span class="chartTitleAlign">Stressed Habitations<small>Water Budget has to be prepared for all Habitations</small></span></h5>
								<div class="chart2" id="planOfAction" class="m_top20"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="white-block">
						<h5 style="padding:5px"><span class="chartTitleAlign">Lab Test Water Sources</span></h5>
						<div class="chart2" id="overView"></div>
					</div>
					<div class="white-block">
						<h5 style="padding:5px"><span class="chartTitleAlign">Water&nbsp;Satisfaction&nbsp;Level</span></h5>
						<div class="chart2" id="drinkingWater" class="m_top20"></div>
					</div>
					<div class="white-block">
						<h5 style="padding:5px"><span class="chartTitleAlign">feedback&nbsp;Level</span></h5>
						<div class="chart2" id="feedbackId" class="m_top20"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="container-fluid m_top20">
			<div class="row">
				<div class="col-sm-12">
					<div id="stateBlockId"></div>
				</div>
				<div class="col-sm-12">
					<div id="districtBlockId"></div>
				</div>
				<div class="col-sm-12">
					<div id="constituencyBlockId"></div>
				</div>
				<div class="col-sm-12">
					<div id="mandalBlockId"></div>
				</div>
			</div>
		</div>
	</section>
</main>
<div class="modal fade" id="modalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="modalHeadingId">Modal title</h4>
      </div>
      <div class="modal-body">
        <div id="modalTable"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<!--<script src="Assests/ruralWaterSupply/custom.js" type="text/javascript"></script>-->
<script src="Assests/ruralWaterSupply/ruralWaterSupplyDashBoard.js" type="text/javascript"></script>
</body>
</html>