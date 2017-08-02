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
            <div class="col-sm-12 datesBlock" style="border:1px solid grey;">
                
                        <div class="col-sm-offset-3 col-sm-1 media">
                            <p class="media-left"><img src="Assests/icons/Overall_icon.png"></p>
                            <p class="media-body">Overall</p>
                        </div>   
						<div class="col-sm-1 media">
							<p class="media-left"><img src="Assests/icons/Today_icon.png"></p>
                            <p class="media-body">Today</p>
						</div>
                        <div class="col-sm-1 media">
							<p class="media-left"><img src="Assests/icons/Week_icon.png"></p>
                            <p class="media-body">Week</p>
						</div>
                        <div class="col-sm-1 media active">
							<p class="media-left"><img src="Assests/icons/3612months_icon.png"></p>
							<p class="media-body">Month</p>
						</div>
                        <div class="col-sm-1 media">
							<p class="media-left"><img src="Assests/icons/3612months_icon.png"></p>
                            <p class="media-body">3Months</p>
						</div>
                        <div class="col-sm-1 media">
							<p class="media-left"><img src="Assests/icons/3612months_icon.png"></p>
                            <p class="media-body">6Months</p>
						</div>
                        <div class="col-sm-1 media">
							<p class="media-left"><img src="Assests/icons/3612months_icon.png"></p>
                            <p class="media-body">Year</p>
						</div>
                        <div class="col-sm-2 media">
							<p class="media-left"><img src="Assests/icons/CustomRange_icon.png"></p>
                            <p class="media-body">Custom Range</p>
						</div>
                   
			</div>
			<div class="col-sm-12 background_white poles_block mtop_20">
                
                <div class="">
                    <div class="col-sm-2 lightsBlock">
                        <img src="Assests/icons/On_Off_light_icon.png">
                            <p>ON/OFF LIGHTS</p>
                            <h4>10/690</h4>
                    </div>
                    <div class="col-sm-10" style="padding-top:13px;">
                      <div class="col-sm-2 media">
                                    <div class="media-left">
                                    <img src="Assests/icons/Poles_icon.png" alt="poles_icon">
                                    </div>
                                    <div class="media-body">
                                        <h5 style="color:#669FF5;">TOTAL POLES</h5>
                                        <h3>1000</h3>
                                    </div>
                                </div>
                                <div class="col-sm-3 media">
                                    <div class="media-left">
                                    <img src="Assests/icons/CCMS_Box_icon.png" alt="poles_icon">
                                    </div>
                                    <div class="media-body">
                                        <h5 style="color:#8E51Db">TOTAL CCMS-BOX/ PANELS</h5>
                                        <h3>100</h3>
                                    </div>
                                </div>
                                <div class="col-sm-2 media">
                                    <div class="media-left">
                                    <img src="Assests/icons/Total_Led_lights_iocn.png" alt="poles_icon">
                                    </div>
                                    <div class="media-body">
                                        <h5 style="color:#827C13">TOTAL LIGHTS</h5>
                                        <h3>800</h3>
                                    </div>
                                </div>
                            
                                <div class="col-sm-2 media">
                                    <div class="media-left">
                                    <img src="Assests/icons/Operational_LED_Light_Icon.png" alt="poles_icon">
                                    </div>
                                    <div class="media-body">
                                        <h5 style="color:#339900;">OPERATIONAL</h5>
                                        <h3>700</h3>
                                    </div>
                                </div>
                            
                                <div class="col-sm-2 media">
                                    <div class="media-left">
                                    <img src="Assests/icons/Non_Operational_LED_Light_Ico.png" alt="poles_icon">
                                    </div>
                                    <div class="media-body">
                                        <h5 style="color:#FF3333;">NON-OPERATIONAL</h5>
                                        <h3>800</h3>
                                    </div>
                                </div>
                                <div class="col-sm-12 border_dashed padding_10">
                                    <ul class="nav navbar-nav">
                                        <li><b>24W = 400</b></li>
                                        <li><b>32W = 200</b></li>
                                        <li><b>75W = 200</b></li>
                                    </ul>
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
        <section class="mtop_20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    
                    <div class="panel panel-default panel-black">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capital">DISTRICT LEVEL OVERVIEW</h4>
                            
                        </div>
                    <div class="panel-body">
                      <div class="row">
                        <div class="col-sm-12">
                            <ul class="nav navbar-nav table-menu">
                                <li class="active">District</li>
                                <li>Parliament</li>
                            </ul>
                          </div> 
                       </div>  
					
                    <table class="table" id="districtTable">
                        <thead>
                            
                           <tr>
                                <th>DISTRICT</th>
                                <th><img src="Assests/icons/mandals_icon.png"><br/>TOTAL MANDALS</th>
                               <th><img src="Assests/icons/Mandal_survy_icon.png"><br/>SURVEY STARTED MANDALS</th>
                               <th><img src="Assests/icons/GPs_icon.png"><br/>TOTAL GPs</th>
                               <th><img src="Assests/icons/GPs_survey_icon.png"><br/>SURVEY STARTEDGPs</th>
                               <th><img src="Assests/icons/Poles_icon.png"><br/>TOTAL POLES SURVEYED</th>
                               <th><img src="Assests/icons/CCMS_Box_icon.png"><br/>TOTAL CCMS-BOX/ PANELS INSTALLED</th>
                               <th><img src="Assests/icons/Total_Led_lights_iocn.png"><br/>TOTAL LED LIGHTS INSTALLED</th>
                               <th><img src="Assests/icons/Operational_LED_Light_Icon.png"><br/>OPERATIONAL</th>
                               <th><img src="Assests/icons/On_light_icon.png"><br/>ON</th>
                               <th><img src="Assests/icons/Off_Light_Icon.png"><br/>OFF</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Srikakulam</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                
                            </tr>
                        </tbody>
                    </table>
					
                    </div>
                    </div>
                        
                    
                </div>
                <div class="col-sm-12 tableBlock">
                    
                    <div class="panel panel-default panel-black">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capital">CONSTITUENCY LEVEL OVERVIEW</h4>
                            
                        </div>
                    <div class="panel-body">
                      <div class="row">
                        <div class="col-sm-12">
                            <ul class="nav navbar-nav table-menu">
                                <li class="active">District</li>
                                <li>Parliament</li>
                            </ul>
                            <div class="col-sm-3">
                                <select>
                                    <option>Ananthapur District</option>
                                </select>
                            </div>
                        </div> 
                       </div>  
					
                    <table class="table" id="constituencyTable">
                        <thead>
                           <tr>
                                <th>MANDAL</th>
                                <th><img src="Assests/icons/mandals_icon.png"><br/>TOTAL MANDALS</th>
                               <th><img src="Assests/icons/Mandal_survy_icon.png"><br/>SURVEY STARTED MANDALS</th>
                               <th><img src="Assests/icons/GPs_icon.png"><br/>TOTAL GPs</th>
                               <th><img src="Assests/icons/GPs_survey_icon.png"><br/>SURVEY STARTEDGPs</th>
                               <th><img src="Assests/icons/Poles_icon.png"><br/>TOTAL POLES SURVEYED</th>
                               <th><img src="Assests/icons/CCMS_Box_icon.png"><br/>TOTAL CCMS-BOX/ PANELS INSTALLED</th>
                               <th><img src="Assests/icons/Total_Led_lights_iocn.png"><br/>TOTAL LED LIGHTS INSTALLED</th>
                               <th><img src="Assests/icons/Operational_LED_Light_Icon.png"><br/>OPERATIONAL</th>
                               <th><img src="Assests/icons/On_light_icon.png"><br/>ON</th>
                               <th><img src="Assests/icons/Off_Light_Icon.png"><br/>OFF</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Srikakulam</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                
                            </tr>
                        </tbody>
                    </table>
					
                    </div>
                    </div>
                        
                    
                </div>
                <div class="col-sm-12 tableBlock">
                    
                    <div class="panel panel-default panel-black">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capital">MANDAL LEVEL OVERVIEW</h4>
                            
                        </div>
                    <div class="panel-body">
                      <div class="row">
                        <div class="col-sm-12">
                            <ul class="nav navbar-nav table-menu">
                                <li class="active">District</li>
                                <li>Parliament</li>
                            </ul>
                            <div class="col-sm-3">
                                <select>
                                    <option>Ananthapur District</option>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <select>
                                    <option>Select Constituency</option>
                                </select>
                            </div>
                          </div> 
                       </div>  
					
                    <table class="table" id="mandalTable">
                        <thead>
                           <tr>
                                <th>MANDAL</th>
                                <th><img src="Assests/icons/mandals_icon.png"><br/>TOTAL MANDALS</th>
                               <th><img src="Assests/icons/Mandal_survy_icon.png"><br/>SURVEY STARTED MANDALS</th>
                               <th><img src="Assests/icons/GPs_icon.png"><br/>TOTAL GPs</th>
                               <th><img src="Assests/icons/GPs_survey_icon.png"><br/>SURVEY STARTEDGPs</th>
                               <th><img src="Assests/icons/Poles_icon.png"><br/>TOTAL POLES SURVEYED</th>
                               <th><img src="Assests/icons/CCMS_Box_icon.png"><br/>TOTAL CCMS-BOX/ PANELS INSTALLED</th>
                               <th><img src="Assests/icons/Total_Led_lights_iocn.png"><br/>TOTAL LED LIGHTS INSTALLED</th>
                               <th><img src="Assests/icons/Operational_LED_Light_Icon.png"><br/>OPERATIONAL</th>
                               <th><img src="Assests/icons/On_light_icon.png"><br/>ON</th>
                               <th><img src="Assests/icons/Off_Light_Icon.png"><br/>OFF</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Srikakulam</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                                <td>38</td>
                            </tr>
                        </tbody>
                    </table>
					
                    </div>
                    </div>
                </div>
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
<script src="Assests/js/custom.js" type="text/javascript"></script>
</body>
</html>