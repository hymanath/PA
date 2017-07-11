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
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>

</head>
<body>
<style>
.bg_color{
	background-color : #ddd;
}
</style>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayati Raj & RD & RWS</h4>
					<p>MNREGS - AP</p>
				</div>
				<div class="col-sm-1 col-xs-12 col-sm-offset-5">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<ul>
								<li>
									<a href="newfundManagementDashboard">
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
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 border_right m_top5">
					<h4 class="arrowIconChanged"><i class="glyphicon glyphicon-menu-hamburger" style="font-size:13px;"></i>&nbsp;&nbsp;<span id="selectedName" style="text-transform: uppercase;cursor:pointer;" attr_levelid="2" attr_id="-1" title="Location Level">Andhra Pradesh </span><i class="fa fa-chevron-down" aria-hidden="true"></i></h4>
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
					<ul class="list-inline">
						<li><span class="color-label color-label-success"></span> 80% and above</li>
						<li><span class="color-label color-label-default"></span> 50% - 79%</li>
						<li><span class="color-label color-label-danger"></span> 0% - 49%&nbsp;&nbsp;&nbsp;</li>
						<li><button type="button" class="btn btn-primary btn-xs" title="Webservice Details" id="getWebserviceDetailsId">WS Details</button> </li>
					</ul>  
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<div class="container-fluid">
		<section>
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
									<div id="projectsOverview"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<div id="nregasAvgOverData"></div>
		<div id="nregasOverData"></div>
		<div id="projectOverviewBlock"></div>
		<div id="projectData"></div>
		<div id="projectDetails"></div>
		
	</div>
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
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/MGNREGS/MGNREGS.js" type="text/javascript"></script>
</body>
</html>