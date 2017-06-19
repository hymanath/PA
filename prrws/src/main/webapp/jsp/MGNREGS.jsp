<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mahatma Gandhi National Rural EGS</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
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
					<p>MNREGS - AP</p>
				</div>
				<div class="col-sm-1 hidden-xs col-sm-offset-5">
					<img src="Assests/images/lokesh.png" class="m_top10 pull-right"/>
				</div>
				<div class="col-sm-1">
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
										<select class="chosenSelect"><option>2017</option></select>
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
		
	</div>
</main>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/MGNREGS/MGNREGS.js" type="text/javascript"></script>
</body>
</html>