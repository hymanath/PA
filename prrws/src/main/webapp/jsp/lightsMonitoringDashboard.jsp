<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LED Dashboard</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less">
<script src="Assests/Plugins/Less/less.js"></script>
    <style>
        
    </style>
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
								<div class="col-sm-12">
									<div class="menu-block" style="background-color:#FFBA00">
										<a href="newfundManagementDashboard">
											<h3>FMS</h3>
											<p>Fund Management System</p>
										</a>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="menu-block" style="background-color:#56A3C5">
										<a href="ruralWaterSupplyDashBoard">
											<h3>RWS</h3>
											<p>Rural Water Supply</p>
										</a>
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
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
	<section>
		<div class="container-fluid padding_10">
			
				<div class="col-sm-12">
                    <div class="col-sm-3">
                        <select>
                        <option>ANDHRA PRADESH</option>
                    </select>
                    </div>
					<div class="col-sm-9">
                        <ul class="nav navbar-nav pull-right sub-menu" style="background-color:lightgrey;">
                            <li class="active">LIVE</li>
                            <li id="historicalData">HISTORICAL</li>
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
					<div class=" white-block poles_block m_top20">
						<div class="row">
							<div id="overviewBlockId"></div>
						</div>
					</div>
				</div>
            </div>
            <!--<div class="col-sm-12 border_top padding_10" style="background-color:#F9F9F9;">
                        <div class="col-sm-2 media">
                            <div class="media-left">
                                <img src="Assests/icons/Start_Date_icon.png" alt="start_date">
                            </div>
                            <div class="media-body">
                                <h5>SURVEY START</h5>
                                <h3>01 JUN 2017</h3>
                            </div>
                        </div>
                        <div class="col-sm-2 media">
                            <div class="media-left">
                                <img src="Assests/icons/End_Date_icon.png" alt="end_date">
                            </div>
                            <div class="media-body">
                                <h5>SURVEY END</h5>
                                <p>Expected Date</p>
                                <h3>20 JUN 2017</h3>
                            </div>
                        </div>
                        <div class="col-sm-2 media">
                            <div class="media-left">
                                <img src="Assests/icons/District_Survy_icon.png" alt="start_date">
                            </div>
                            <div class="media-body">
                                <h5>NO OF <span style="color:#827C13;">DISTRICTS</span> SURVEY SATRTED</h5>
                                <h3>10</h3>
                            </div>
                        </div>
                        <div class="col-sm-2 media">
                            <div class="media-left">
                                <img src="Assests/icons/Constituency_Survy_icon.png" alt="start_date">
                            </div>
                            <div class="media-body">
                                <h5>NO OF <span style="color:#02B0AC;">CONSTITUENCIES</span>
SURVEY STARTED</h5>
                                <h3>120</h3>
                            </div>
                        </div>
                        <div class="col-sm-2 media">
                            <div class="media-left">
                                <img src="Assests/icons/Mandal_survy_icon.png" alt="start_date">
                            </div>
                            <div class="media-body">
                                <h5>NO OF <span style="color:#00BFE8;">MANDALS</span>
SURVEY SATRTED</h5>
                                    <h3>500</h3>
                            </div>
                        </div>
                        <div class="col-sm-2 media">
                            <div class="media-left">
                                <img src="Assests/icons/GPs_survey_icon.png" alt="start_date">
                            </div>
                            <div class="media-body">
                                <h5>NO OF <span style="color:#F45CB5;">GRAM PANCHAYAT</span>
SURVEY STARTED</h5>
                                <h3>1000</h3>
                            </div>
                        </div>
                        
                    </div> -->
				<div id="ledOverViewDiv"></div>
        
        </div>
        </section>
        <section class="m_top20">
            <div class="container-fluid">
				<div id="projectData"></div>
            </div>
        </section>
</main>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
<script src="Assests/lightMonitoringDashBoard/lightMonitoringDashBoard.js" type="text/javascript"></script>
</body>
</html>