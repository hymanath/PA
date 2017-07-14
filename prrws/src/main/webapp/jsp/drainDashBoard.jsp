<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Drains Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
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
					<h4 class="text-capital">Drains</h4>
					<p>DASHBOARD</p>
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
									<a href="DrainsDashBoard">
										<h3>Drains</h3>
										<p>Drains<br/>Drains DashBoard</p>
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
			<div class="row navbar_section_whitebk">
				<div class="col-sm-12 m_top5 ">
					<ul class="list-inline pull-right calendar_active_cls">
						<li attr_val="Overall"><img src="Assests/icons/Overall_icon.png"/>&nbsp;&nbsp;<b><span>Overall</span></b></li>
						<li attr_val="Today"><img src="Assests/icons/Today_icon.png"/>&nbsp;&nbsp;<b><span>Today</span></b></li>
						<li attr_val="Week"><img src="Assests/icons/Week_icon.png"/>&nbsp;&nbsp;<b><span> Week</span></b></li>
						<li class="active" attr_val="Month"><img src="Assests/icons/Month_icon.png"/>&nbsp;&nbsp;<b><span>Month</span></b></li>
						<li attr_val="3Months"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>3Months</span></b></li>
						<li attr_val="6Months"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>6Months</span></b></li>
						<li attr_val="Year"><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b><span>Year</span></b></li>
						<li attr_val="custom" id="singleDateRangePicker"><img src="Assests/icons/CustomRange_icon.png"/>&nbsp;&nbsp;<b>Custom Range</b></li>
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
                    <div class="col-sm-3 block">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="row">
									<div class="col-sm-10">
										<h4 class="text-center">TOTAL</h4>
									</div>
									<div class="col-sm-2">
										<span id="totalSpinnerId" class="pull-right"></span>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div id="totalBodyId">
									 <div class="row">
										<div class="col-sm-6 subBlock">
											<h5>AVAILABLE</h5>
											<h4 id="totalAvailableCountId">0</h4>
											<span id="totalAvailableKmId">0 KM</span>
										</div>
										<div class="col-sm-6 subBlock">
											<h5>CLEANED</h5>
											<h4 id="totalCleanedCountId">0<small class="pull-right" id="totalPercentageId">0.00%</small></h4>
											<span id="totalCleanedKmId">0 KM</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>
						<div class="col-sm-3 block">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="row">
									<div class="col-sm-10">
										<h4 class="text-center">KACHA</h4>
									</div>
									<div class="col-sm-2">
										<span id="kachaSpinnerId" class="pull-right"></span>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div id="kachaBodyId">
									<div class="row">
										<div class="col-sm-6 subBlock">
											<h5>AVAILABLE</h5>
											<h4 id="kachaAvailableCountId">0</h4>
											<span id="kachaAvailableKmId">0 KM</span>
										</div>
										<div class="col-sm-6 subBlock">
											<h5>CLEANED</h5>
											<h4 id="kachaCleanedCountId">0<small class="pull-right" id="kachaPercentageId">0.00%</small></h4>
											<span id="kachaCleanedKmId">0 KM</span>
										</div>
									</div>
								</div>	
							</div>
						</div>
						</div>
						<div class="col-sm-3 block">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="row">
										<div class="col-sm-10">
											<h4 class="text-center">PAKKA</h4>
										</div>
										<div class="col-sm-2">
											<span id="pakkaSpinnerId" class="pull-right"></span>
										</div>
								</div>
							</div>
							<div class="panel-body">
								<div id="pakkaBodyId"> 
									<div class="row">
										<div class="col-sm-6 subBlock">
											<h5>AVAILABLE</h5>
											<h4 id="pakkaAvailableCountId">0</h4>
											<span id="pakkaAvailableKmId">0 KM</span>
										</div>
										<div class="col-sm-6 subBlock">
											<h5>CLEANED</h5>
											<h4 id="pakkaCleanedCountId">0<small class="pull-right" id="pakkaPercentageId">0.00%</small></h4>
											<span id="pakkaCleanedKmId">0 KM</span>
										</div>
									</div>
								</div>	
							</div>
						</div>
						</div>
						<div class="col-sm-3 block">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="row">
										<div class="col-sm-10">
											<h4 class="text-center">UNDERGROUND</h4>
										</div>
										<div class="col-sm-2">
											<span id="undergroundSpinnerId" class="pull-right"></span>
										</div>
								</div>
							</div>
							<div class="panel-body">
								<div id="ugBodyId"> 
									<div class="row">
										<div class="col-sm-6 subBlock">
											<h5>AVAILABLE</h5>
											<h4 id="ugAvailableCountId">0</h4>
											<span id="ugAvailableKmId">0 KM</span>
										</div>
										<div class="col-sm-6 subBlock">
											<h5>CLEANED</h5>
											<h4 id="udCleanedCountId">0<small class="pull-right" id="ugPercentageId">0.00%</small></h4>
											<span id="ugCleanedKmId">0 KM</span>
										</div>
									</div>
								</div>	
							</div>
						</div>
						</div>
				</div>
			</div>
		</section>
        <section class="m_top20">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                <div class="panel panel-black panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capital">DISTRICT LEVEL OVERVIEW</h4>
                        </div>
                    <div class="panel-body">
					<div class="row">
						<div class="col-sm-12">
							<ul class="nav navbar-nav list_inline tableMenu" role="tabDrains_menu" attr_blockId="3">
								<li class="active" attr_location_type="districts">Districts</li>
								<li class="" attr_location_type="parliament">Parliament</li>
							</ul>
						</div>
					</div>
					<div id="districtViewTableDivId"></div>
                    </div>
                    </div>
                    <div class="panel panel-black panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capital">CONSTITUENCY LEVEL OVERVIEW</h4>
                        </div>
                    <div class="panel-body">
					<div class="row">
						<div class="col-sm-3">
							<ul class="nav navbar-nav list_inline tableMenu tableMenuCons" role="tabDrains_menu" attr_blockId="4">
								<li class="active" attr_location_type="districts">Districts</li>
								<li class="" attr_location_type="parliament">Parliament</li>
							</ul>
						</div>
						<div class="col-sm-3">
							<select class="form-control chosen-select" id="constituencyDistrictNames">
							</select>
						</div>
					</div>
					<div id="assemblyViewTableDivId"></div>
                    </div>
                    </div>
                    <div class="panel panel-black panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capital">MANDAL LEVEL OVERVIEW</h4>
                        </div>
                    <div class="panel-body">
					<div class="row">
						<div class="col-sm-3">
							<ul class="nav navbar-nav list_inline tableMenu tableMenuMandal" role="tabDrains_menu" attr_blockId="5">
								<li class="active" attr_location_type="districts">Districts</li>
								<li class="" attr_location_type="parliament">Parliament</li>
							</ul>
						</div>
						<div class="col-sm-3">
							<select class="form-control chosen-select" id="mandalDistrictNames">
							</select>
						</div>
						<div class="col-sm-3">
							<select class="form-control chosen-select" id="mandalConstituencyNames">
							</select>
						</div>
					</div>
					<div id="mandalViewTableDivId"></div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
		<div id="blocksData"></div>
    </section>
       
    </div>
</main>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/js/Pris.js" type="text/javascript"></script>
<script src="Assests/drainDashBoard/drainDashBoard.js" type="text/javascript"></script>
<script src="Assests/Plugins/Less/less.js"></script>
</body>
</html>