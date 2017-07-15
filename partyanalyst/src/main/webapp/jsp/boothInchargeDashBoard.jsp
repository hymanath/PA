<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booth Commitee Dashboard</title>
<link href="Assets/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
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
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-3 pull-right">
							<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<input type="text" class="form-control pull-right"  name="daterangePicker" id="daterangePickerId"/>
							</div>
						</div>
					</div>
				</div>
			
                <div class="row">
				  <div id="overAllBoothDlstDivId"></div>
                </div>
            </div>
        </section>
        <section class="mtop_20">
            <div class="container-fluid">
                <div class="col-sm-12">
                        <div class="committeeMembers">
                            <div class="subBlock col-sm-12 text-center">
                                <h5 class="">Booth Committee Members Voter ID Serial Numbers wise</h5>
								 <div id="overAllSerialRangeWiseVoterDivId"></div>
								 <img id="ajaximageId"  style="width:50px;height:50px; margin-left: 346px; margin-top: -26px;" src="images/icons/loading.gif">
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
								<h4 class="panel-title text-capital districtParliamentLevleHadingCls">DISTRICT WISE</h4>
								<ul class="nav navbar-nav table-menu pull-right" table-menu="">
									<li class="active districtLevelCls" style="cursor:pointer;" attr_level_value="DISTRICT">DISTRICT</li>
									<li class="districtLevelCls" style="cursor:pointer;" attr_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
							   <div id="dstrctParlmntLvlBoothDtlsDivId"></div>
							   <img id="ajaximageId"  style="width:50px;height:50px; margin-left: 346px; margin-top: -26px;" src="images/icons/loading.gif">
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
									<li class="active" style="cursor:pointer;" id="constituencyDistrictId">DISTRICT</li>
									<li id="constituencyParliamentId" style="cursor:pointer;">PARLIAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="row" id="constituencyWiseDistrictId">
										<div class="col-sm-12" id="selectConstituencyDistrict">
											<div id="" class="col-sm-3">
												<select id="constituencySelectId">
													<option>Ananthapur District</option>
												</select>
											</div>
											<div id="" class="col-sm-3">
												<select>
													<option>Select Constituency</option>
												</select>
											</div>											
										</div>										
								</div>
								<div class="row" id="constituencyWiseParliamentId">
										<div class="col-sm-12" id="selectConstituencyDistrict">
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
									<li class="active" id="mandalDistrictId" style="cursor:pointer;">DISTRICT</li>
									<li id="mandalParliamentId" style="cursor:pointer;">PARLIAMENT</li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="row" id="mandalWiseDistrictId" style="cursor:pointer;">
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
											<div id="" class="col-sm-3">
												<select>
													<option>Select Mandal</option>
												</select>
											</div>
										</div>										
								</div>
								<div class="row" id="mandalWiseParliamentId">
										<div class="col-sm-12" id="selectConstituencyDistrict">
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
											<div id="" class="col-sm-3">
												<select>
													<option>Select Mandal</option>
												</select>
											</div>
										</div>										
								</div>
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
								<h4 class="panel-title text-capital" >PANCHAYAT WISE</h4>
								<ul class="nav navbar-nav table-menu pull-right" table-menu="">
									<li class="active" id="panchayatDistrictId" style="cursor:pointer;">DISTRICT</li>
									<li id="panchayatParliamentId" style="cursor:pointer;">PARLIAMENT</li>
								</ul>
							</div>
							<div class="row" id="villegeWiseDistrictId">
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
											<div id="" class="col-sm-3">
												<select>
													<option>Select Mandal</option>
												</select>
											</div>
											<div id="" class="col-sm-3">
												<select>
													<option>Select Villege</option>
												</select>
											</div>
										</div>										
								</div>
								<div class="row" id="villegeWiseParliamentId">
										<div class="col-sm-12" id="selectConstituencyDistrict">
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
											<div id="" class="col-sm-3">
												<select>
													<option>Select Mandal</option>
												</select>
											</div>
											<div id="" class="col-sm-3">
												<select>
													<option>Select Villege</option>
												</select>
											</div>
										</div>										
								</div>
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
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assets/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assets/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assets/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/BoothPage/boothInchargeDashboard.js" type="text/javascript"></script>

</body>
</html>