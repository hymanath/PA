<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booth Commitee Dashboard</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/css">
<script src="Assests/Plugins/Less/less.js"></script>
</head>
<body>
   <header></header>
    <main>
        <section class="mtop_20">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12 blocks">
                        <div class="col-sm-3">
							<div class="subBlock text-center">
                                <p>TOTAL BOOTHS</p>
                                <h4>74059</h4>
							</div>
                        </div>
                        <div class="col-sm-3">
                            <div class="subBlock text-center">
                            <p>STARTED BOOTHS</p>
                            <h4>74059</h4>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="subBlock text-center">
                            <p>NOT-STARTED BOOTHS</p>
                            <h4>74059</h4>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="subBlock text-center">
                            <p>COMPLETED BOOTHS</p>
                            <h4>74059</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="mtop_20">
            <div class="container-fluid">
                <div class="col-sm-12">
                        <div class="committeeMembers">
                            <div class="subBlock col-sm-12 text-center">
                                <h5 class="">Booth Committee Members Voter ID Serial Numbers wise</h5>
                            <ul class="">
                                <li>
                                    <p>0-10</p>
                                    <h4>8752</h4>
                                </li>
                                <li>
                                    <P>11-20</P>
                                    <h4>8752</h4>
                                </li>
                                <li>
                                    <P>21-30</P>
                                    <h4>8752</h4>
                                </li>
                                <li>
                                    <P>31-40</P>
                                    <h4>8752</h4>
                                </li>
                                <li>
                                    <P>41-50</P>
                                    <h4>8752</h4>
                                </li>
                                <li>
                                    <P>51-60</P>
                                    <h4>8752</h4>
                                </li>
                                 <li>
                                     <P>61-70</P>
                                     <h4>8752</h4>
                                </li>
                                  
                                <li>
                                    <P>71-80</P>
                                    <h4>8752</h4>
                                </li>
                                 <li>
                                     <P>81-90</P>
                                     <h4>8752</h4>
                                </li>
                                 <li>
                                     <P>91-100</P>
                                     <h4>8752</h4>
                                </li>
                                 <li>
                                     <P>Above 100</P>
                                     <h4>8752</h4>
                                </li>
                            </ul></div>
                        </div>
                    </div>
            </div>
        </section>
        <section class="mtop_20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title text-capital">DISTRICT WISE</h4>
								<ul class="nav navbar-nav table-menu pull-right">
									<li class="active">DISTRICT</li>
									<li>PARLAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
							   <div id="districtLevelBoothDtlsDivId"></div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
        <section class="mtop_20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default">
							  <div class="panel-heading">
									<h4 class="panel-title text-capital">CONSTITUENCY WISE</h4>
									<ul class="nav navbar-nav table-menu pull-right">
										<li class="active">DISTRICT</li>
										<li>PARLAMENT</li>
									</ul>
								</div>
							  <div class="panel-body">
								<div class="row">
										<div class="col-sm-12">
											<div id="selectDistrict" class="col-sm-3"></div>
										</div>
								</div>
							   <div id="constituencyLevelBoothDtlsDivId"></div>
							 </div>
						</div>
                    </div>
                </div>
            </div>
        </section>
        <section class="mtop_20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title text-capital">MANDAL WISE</h4>
								<ul class="nav navbar-nav table-menu pull-right">
									<li class="active">DISTRICT</li>
									<li>PARLAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
							  <div id="mandalLevelBoothDtlsDivId"></div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
		 <section class="mtop_20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title text-capital">PANCHAYAT WISE</h4>
								<ul class="nav navbar-nav table-menu pull-right">
									<li class="active">DISTRICT</li>
									<li>PARLAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
							  <div id="panchaytLevelBoothDtlsDivId"></div>
							</div>
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
<script src="js/BoothPage/boothInchargeDashboard.js" type="text/javascript"></script>
<script src="Assests/js/custom.js"></script>
</body>
</html>