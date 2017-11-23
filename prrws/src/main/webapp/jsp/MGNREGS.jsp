<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mahatma Gandhi National Rural EGS</title>
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
<link href="Assests/css/print.css" rel="stylesheet" type="text/css"/>
<style>
.bg_color{
	background-color : #ddd;
}
.DTFC_LeftBodyWrapper
{
	top:-13px !important;
}
.DTFC_LeftBodyWrapper tr td
{
	background-color:#fff;
}
</style>
</head>
<body>
<script type="text/javascript">
var searchParams = new URLSearchParams(window.location.search);
	searchParams = searchParams.get("component");
</script>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayati Raj, RD & RWS</h4>
					<p>MGNREGS - AP</p>
				</div>
				<div class="col-sm-2 col-xs-12 col-sm-offset-3">
					<img src="Assests/images/NREGS-LOGO.png" class="m_top5"/>
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
										<div class="row">
  											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="solidWasteManagementDashboard">
														<h3>SWM</h3>
                           								 <p>Solid Waste Management</p>
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
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 border_right m_top5">
					<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" style="font-size:13px;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;" attr_distId="" attr_levelid="2" attr_id="-1" title="Location Level">Andhra Pradesh </span></h4>
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
				<div class="col-sm-4 m_top5">
					<ul class="list-inline pull-left">
						<li><span class="color-label color-label-success"></span> 80% and above</li>
						<li><span class="color-label color-label-default"></span> 50% - 79%</li>
						<li><span class="color-label color-label-danger"></span> 0% - 49%&nbsp;&nbsp;&nbsp;</li>
						<li><button type="button" class="btn btn-primary btn-xs" title="Webservice Details" id="getWebserviceDetailsId">WS</button> </li>
					</ul>  
					
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="menu-top-selection pull-right" style="padding: 6px 7px;border: 1px solid #333;border-radius: 50%;margin-left: 10px;margin-top: 3px;">
					<i class="glyphicon glyphicon-cog menu-top-selection-icon" title="Setup Components For Consolidated View"></i>
					<div class="arrow_box_top">
						<div class="row">
							<div id="navTabsMenuSelectionId"></div>
						</div>
					</div>
				</div>
				<ul class="nav navbar-nav tableMenu list-inline pull-right" id="viewSwitchBtn">
					<li class="active" attr_name="consolidated">Consolidated</li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 text-right">
				<p class="text-danger">Note: Amount in lakhs</p>
			</div>
			<div class="col-sm-12">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-default panel-black">
						<div class="panel-heading" role="tab" id="headingOne">
							<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
								<h4 class="panel-title">MGNREGS PROJECTS</h4>
							</a>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<!--<div id="projectsOverviewConsolidated"></div>-->
								<div id="projectsOverview"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<section id="dashboardView">
		<div class="container-fluid">
			<div id="nregasAvgOverData"></div>
			<div id="nregasOverData"></div>
			<div id="projectOverviewBlock"></div>
			<div id="projectData"></div>
			<div id="projectDetails"></div>
		</div>
	</section>
	<section id="consolidatedView">
		<div class="container-fluid m_top20">
			<div id="projectDataConsolidated" class="m_top20"></div>
		</div>
	</section>
	
	<div class="modal fade" id="webserviceDetailsModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width:80%;margin:auto">
			<div class="modal-content">    
				<div class="modal-header bg_EE">          
					<div class="row">
						<div class="col-md-8 col-xs-12 col-sm-4">
							<h4 class="modal-title text-capitalize" id="">Web Service Status Details</h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-4">  
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerAUM"/>
							</div>
						</div>
						<div class="col-md-1 col-xs-12 col-sm-4">       
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>    
						</div>        
					</div>
				</div>
				<div class="modal-body">
					<div id="webserviceDetailsModalId"></div>
				</div>  
			</div>
		</div>
	</div>        
	<div class="modal fade" tabindex="-1" id="nregsConsitenModalId" role="dialog" style="z-index:99999;">
		<div class="modal-dialog" style="width:90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="modalHeadingDivId"></h4>  
				</div>
				<div class="modal-body">
					<div id="nregsOverviewBodyId"></div>
					<div id="nregsConsitenBodyId"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
				</div>
			</div><!--  /.modal-content -->  
		</div><!--  /.modal-dialog -->
	</div>
   <!-- PopUp For LabourBudget PanchayatExp -->
	<div class="modal fade" tabindex="-1" id="nregsPanExpModalId" role="dialog" style="z-index:99999;">
		<div class="modal-dialog" style="width:90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="larBudExpHeadingId"></h4>  
				</div>
				<div class="modal-body">
					<div id="LabBudgtPanExBodyOverviewId"></div>
					<div id="LabBudgtPanExBodyId"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
				</div>
			</div><!--  /.modal-content -->  
		</div><!--  /.modal-dialog -->
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
<script type="text/javascript" src="http://cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js"></script>
<script src="Assests/MGNREGS/MGNREGS.js" type="text/javascript"></script>
<script src="Assests/MGNREGS/NregaConsolidated.js" type="text/javascript"></script>
<script type="text/javascript">

$(".menu-top-selection-icon").tooltip();
onLoadCallsConsolidated();
onLoadCalls();
$(document).on("click","#viewSwitchBtn li",function(){
	$("[overview-state],[overview-district]").removeClass("active");
	$(this).addClass("active");
	$("#consolidatedView").show();
	$("#projectData,#projectOverviewBlock").hide();
});
</script>
</body>
</html>