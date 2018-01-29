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
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
 <link rel="stylesheet" type="text/css" href="Assests/SimplePagination/simplePagination.css"/>
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
					<h4 class="text-capital">Panchayat Raj & RD & RWS</h4>
					<p>Rural Water Supply - AP</p>
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
			<div class="col-sm-12">
				<div class="row">
					<div class="col-md-12" style="padding-right: 5px;">      
						<div class="white-block">
							<h5 style="padding:5px"><span class="chartTitleAlign">Habitation Coverage Status</span></h5>
							<div class="chart" id="habitation"></div>
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
<div class="modal fade" id="modalHablitationDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="modalHabliHeadingId">Modal title</h4>
      </div>
      <div class="modal-body">
		<div id="modalHablitationTable"></div>
		<div id="modalAlertTable"></div>
		<div id="modalAssetsTable"></div>
		<div id="modalWaterSourceTable"></div>
		<div id="modalIvrStatusTable"></div>
		<div id="modalKpiTable"></div>
		<div id="modalSchemsTable"></div>
		<div class="row">
			<div class="col-md-10 col-md-offset-5 m_top20">
				<div class="paginationId"></div>
			</div>
		</div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="sbModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="">Swatch Bharat - IHHL</h4>
      </div>
      <div class="modal-body">
        <div id="sbDataDivId"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="sbPaymentModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width: 95%;">
    <div class="modal-content modal-custom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#fff">&times;</span></button>
        <h4 class="modal-title" id="">Swatch Bharat Payments</h4>
      </div>
      <div class="modal-body">
        <div id="sbPaymentDataDivId"></div>
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
<script src="Assests/SimplePagination/simplePagination3.js" type="text/javascript"></script>
<!--<script src="Assests/ruralWaterSupply/custom.js" type="text/javascript"></script>-->
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
<script src="Assests/habitationCoverageStatus/habitationCoverageStatusDashboard.js" type="text/javascript"></script>                
</body>
</html>