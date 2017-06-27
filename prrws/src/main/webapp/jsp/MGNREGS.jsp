<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mahatma Gandhi National Rural EGS</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>

</head>
<body>
<!-- 
<sidebar>
<div class="rightNavigationMenu">
	<div class="backgroundBlock"></div>
	<ul style="display:none"></ul>
	<button class="rightNavigationMenuRes">
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	</button>
</div>
</sidebar>
-->
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
				<div class="col-sm-2 border_right m_top5">
					Note: All Amount in Lakhs
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
								<input type="text" class="form-control" id="dateRangePickerMGNT" style="width: 200px;"/>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 m_top5">
					<ul class="list-inline">
						<li><span class="color-label color-label-success"></span> 80% and above</li>
						<li><span class="color-label color-label-default"></span> 50% - 79%</li>
						<li><span class="color-label color-label-danger"></span> 0% - 49%</li>
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
	
</main>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/MGNREGS/MGNREGS.js" type="text/javascript"></script>
</body>
</html>