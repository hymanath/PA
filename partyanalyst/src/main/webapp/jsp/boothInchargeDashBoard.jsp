<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booth Commitee Dashboard</title>
<link href="Assets/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assets/css/boothIncharge.less" rel="stylesheet" type="text/less">
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assets/js/less.js"></script>
<style type="text/css">
.footerCls
{
	position:static;
}

</style>
</head>
<body>
   <header></header>
   
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
								<ul class="nav navbar-nav table-menu pull-right" table-menu="">
									<li class="active">DISTRICT</li>
									<li>PARLIAMENT</li>
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
									<ul class="nav navbar-nav table-menu pull-right" table-menu="">
										<li class="active ttt">DISTRICT</li>
										<li class="ttt">PARLIAMENT</li>
									</ul>
								</div>
							  <div class="panel-body">
								<div class="row">
										<div class="col-sm-12" id="selectConstituencyDistrict">
											<div id="" class="col-sm-3">
												<select>
													<option>Ananthapur District</option>
												</select>
											</div>
											<div id="" class="col-sm-3">
												<select>
													<option>Select Constituency</option>
												</select>
											</div>
										</div>
										<div class="col-sm-12" id="selectParliament">
											<div id="" class="col-sm-3">
												<select>
													<option>Select Parliament</option>
												</select>
											</div>
											<div id="" class="col-sm-3">
												<select>
													<option>Select Constituency</option>
												</select>
											</div>
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
									<li>PARLIAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
								<div id="" class="col-sm-3">
									<select>
										<option>Ananthapur District</option>
									</select>
								</div>
								<div id="" class="col-sm-3">
									<select>
										<option>Ananthapur Constituency</option>
									</select>
											</div>
								<div id="" class="col-sm-3">
									<select>
										<option>Select Mandal</option>
									</select>
								</div>
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
								<ul class="nav navbar-nav table-menu pull-right" table-menu="">
									<li class="active">DISTRICT</li>
									<li>PARLIAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
								<div id="" class="col-sm-3">
									<select>
										<option>Ananthapur District</option>
									</select>
								</div>
								<div id="" class="col-sm-3">
									<select>
										<option>Ananthapur Constituency</option>
									</select>
											</div>
								<div id="" class="col-sm-3">
									<select>
										<option>Select Mandal</option>
									</select>
								</div>
								<div id="" class="col-sm-3">
									<select>
										<option>Select Village</option>
									</select>
								</div>
							  <div id="panchaytLevelBoothDtlsDivId"></div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
   
<script src="Assets/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assets/js/bootstrap.js" type="text/javascript"></script>
<script src="Assets/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assets/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assets/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assets/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assets/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/BoothPage/boothInchargeDashboard.js" type="text/javascript"></script>

</body>
</html>