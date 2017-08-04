<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LED Dashboard</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/css">
<script src="Assests/Plugins/Less/less.js"></script>
    <style>
        
    </style>
</head>
<body>
    
    <header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 pad_left0">
					<img src="Assests/img/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10">
					<h4 class="text-capital">Panchayati Raj</h4>
					<p>Andhra Pradesh</p>
				</div>
				<div class="col-sm-1 pull-right">
					<i class="fa fa-th fa-2x" aria-hidden="true" style="padding-top:20px;"></i>
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
            <div class="col-sm-12 border_top padding_10" style="background-color:#F9F9F9;">
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
                        
                    </div>
        
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
<script src="Assests/lightMonitoringDashBoard/lightMonitoringDashBoard.js" type="text/javascript"></script>
</body>
</html>