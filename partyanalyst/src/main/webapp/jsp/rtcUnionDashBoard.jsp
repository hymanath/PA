<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>2016 Union Registration Drive</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<link href="daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/dataTables.fixedHeader.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<style type="text/css">
.bg_ff{background:#fff !important}
.bg_F5{background:#f5f5f5 !important}
.pad_0{padding:0px;}
.cursorH{cursor:pointer}
body{color:#666 !important}
.bg_cc
{
	background:#ccc !important
}
.thumbnail{margin:0px;}
.cursorH{cursor:pointer}
.bg_EF
{
	background:#EFF3F4
}
.m_0{margin:0px !important}
.bg_ff{background:#fff !important}
.bg_F5{background:#F5F5F5 !important}
.m_top10{margin-top:10px !important;}
.text-valign{vertical-align:middle !important}
.pad_0{padding:0px;}
</style>
</head>
<body>
<section>

<div class="container">
<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading bg_cc">
						<!--<img src="dist/2016DashBoard/img/affliatedCom.jpg" style="width:100px;display:inline-block">-->
						AFFLIATED UNIONS DASHBOARD
					</div>
					<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-12">
											<div class="panel-body bg_EF">
											<div id="allTotalCountsId"></div>
												<table class="table table-bordered bg_ff">
													<tr>
														<td colspan="4" class="text-center bg_cc	"><h4 class="m_0">ALL AFFILIATED UNION MEMBERS REGISTERED</h4></td>
													</tr>
													<tr>
														<td class="text-center">
															<h4 class="m_0">TOTAL</h4>
															<h3 class="m_0"><span id="tTCount"></span><center><img id="tTCountLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
															<p class="m_0"><span>TAB:<span id="tTTCount"></span></span> | <span>ONLINE:<span id="tTOLCount"></span></span></p>
														</td>
														<td class="text-center">
															<h4 class="m_0">TODAY</h4>
															<h3 class="m_0"><span id="tTLCount"></span><center><img id="tTLCountLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
															<p class="m_0"><span>TAB:<span id="tTLTCount"></span></span> | <span>ONLINE:<span id="tTLOLCount"></span></span></p>
														</td>
														<td class="text-center">
															<h4 class="m_0">LAST 07 DAYS</h4>
															<h3 class="m_0"><span id="tL7Count"></span><center><img id="tL7CountLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
															<p class="m_0"><span>TAB:<span id="tL7TCount"></span></span> | <span>ONLINE:<span id="tL7OLCount"></span></span></p>
														</td>
														<td class="text-center">
															<h4 class="m_0">LAST 30 DAYS</h4>
															<h3 class="m_0"><span id="tL30Count"></span><center><img id="tL30CountLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
															<p class="m_0"><span>TAB:<span id="tL30TCount"></span></span> | <span>ONLINE:<span id="tL30OLCount"></span></span></p>
														</td>
													</tr>
												</table>
												
												<table class="table table-condensed m_top10 bg_ff">
													<thead class="bg_cc">
														<th class="text-center">AFFILIATED UNION NAME</th>
														<th class="text-center">TOTAL REGISTER</th>
														<th class="text-center">TODAY</th>
														<th class="text-center">LAST 07 DAYS</th>
														<th class="text-center"	>LAST 30 DAYS</th>
													</thead>
													<tr class="trClass" id="teachersAllId">
															<td class="text-valign"><h3 class="m_0"> TEACHERS</h3></td>
															<td class="text-center"><h3 class="m_0"><span id="teachersTotalId">0</span><center><img id="teachersTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB:<span id="teachersTotalTabId">0</span> | ONLINE:<span id="teachersTotalOnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="teachersTodayId">0</span><center><img id="teachersTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB:<span id="teachersTodayTabId">0</span> | ONLINE:<span id="teachersTodayOnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="teachersLast7Id">0</span><center><img id="teachersLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB:<span id="teachersLast7TabId">0</span> | ONLINE:<span id="teachersLast7OnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="teachersLast30Id">0</span><center><img id="teachersLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB:<span id="teachersLast30TabId">0</span> | ONLINE:<span id="teachersLast30OnlineId">0</span></p>
															</td>
														</tr>
														<tr class="trClass" id="anganwadiAllId">
															<td class="text-valign"><h3 class="m_0">ANGANWADI</h3></td>
															<td class="text-center"><h3 class="m_0"><span id="anganwadiTotalId">0</span><center><img id="anganwadiTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span id="anganwadiTotalTabId">0</span> | ONLINE: <span id="anganwadiTotalOnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="anganwadiTodayId">0</span><center><img id="anganwadiTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span id="anganwadiTodayTabId">0</span> | ONLINE: <span id="anganwadiTodayOnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="anganwadiLast7Id">0</span><center><img id="anganwadiLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span id="anganwadiLast7TabId">0</span> | ONLINE: <span id="anganwadiLast7OnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="anganwadiLast30Id">0</span><center><img id="anganwadiLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
															<p class="m_0">TAB:<span id="anganwadiLast30TabId">0</span> | ONLINE: <span id="anganwadiLast30OnlineId">0</span></p>
															</td>
														</tr>
														<tr class="trClass" id="motorWorkersAllId">
															<td class="text-valign"><h3 class="m_0">GRADUATES</h3></td>
															<td class="text-center"><h3 class="m_0"><span id="motorTotalId">0</span><center><img id="motorTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span 
																id="motorTotalTabId">0</span> | ONLINE: <span id="motorTotalOnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="motorTodayId">0</span><center><img id="motorTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span 
																id="motorTodayTabId">0</span> | ONLINE: <span id="motorTodayOnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="motorLast7Id">0</span><center><img id="motorLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span 
																id="motorLast7TabId">0</span> | ONLINE: <span id="motorLast7OnlineId">0</span></p>
															</td>
															<td class="text-center"><h3 class="m_0"><span id="motorLast30Id">0</span><center><img id="motorLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
																<p class="m_0">TAB: <span 
																id="motorLast30TabId">0</span> | ONLINE: <span id="motorLast30OnlineId">0</span></p>
															</td>
														</tr>
												</table>
												
												<!--<div class="panel panel-default m_top10 m_0">
													<div class="panel-heading bg_ff">
														<div class="row">
															<div class="col-md-3">
																<label class="radio-inline"><input type="radio">District</label>
																<label class="radio-inline"><input type="radio">Constituency</label>
															</div>
															<div class="col-md-3">
																<select id="userMembersId" onchange="refreshDetails()" multiple>
																</select>
															</div>
															<div class="col-md-12 m_top10">
																<h4 class="panel-title"><b>DISTRICT WISE REGISTRATION DETAILS</b></h4>
															</div>
														</div>
													</div>
													<div class="panel-body bg_ff pad_0">
														<table class="table table-bordered">
															<thead class="bg_F5">
																<th>DISTRICT NAME</th>
																<th>TOTAL REGISTER</th>
																<th>WEB</th>
																<th>TAB</th>
															</thead>
															<tbody>
																<tr>
																	<td class="cursorH" data-toggle="modal" data-target="#myModal">ANANTHAPUR</td>
																	<td>100</td>
																	<td>40</td>
																	<td>60</td>
																</tr>
																<tr>
																	<td class="cursorH" data-toggle="modal" data-target="#myModal">CHITTOOR</td>
																	<td>100</td>
																	<td>40</td>
																	<td>60</td>
																</tr>
																<tr>
																	<td class="cursorH" data-toggle="modal" data-target="#myModal">KRISHNA</td>
																	<td>100</td>
																	<td>40</td>
																	<td>60</td>
																</tr>
																<tr>
																	<td class="cursorH" data-toggle="modal" data-target="#myModal">ANANTHAPUR</td>
																	<td>100</td>
																	<td>40</td>
																	<td>60</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>-->
											</div>
										</div>

									</div>
									<div id="tableDivsId">
										<div class="panel panel-default m_top10">
											<div class="panel-heading bg_dd">
											
											<!--<div class="col-xs-5">
											<label class="radio-inline">
												<input type="radio" name="district" value="total" checked="true" id="districtTotalId" class="districtRadioCls">Total
											</label>
											<label class="radio-inline">
												<input type="radio" name="district" value="today" id="districtTodayId" class="districtRadioCls">Today
											</label>
											<label class="radio-inline">
												<input type="radio" name="district" value="last 7 days" id="district7daysId" class="districtRadioCls">Last 7 Days
											</label>
											<label class="radio-inline">
												<input type="radio" name="district" value="last 30 days" id="district30DaysId" class="districtRadioCls">Last 30 Days
											</label>
											</div> -->
											<div class="row">
												<div class="col-xs-3" style="margin-top:6px;">
													<label class="radio-inline"><input type="radio" value="district" class="locationTypeRadioCls" name="locationTypeRadio" checked>District</label>
													<label class="radio-inline"><input type="radio" value="constituency" class="locationTypeRadioCls" name="locationTypeRadio" >Constituency</label>
												</div>
												<div class="col-md-3">
													<select id="userMembersId" multiple data-placeholder="Select Union Type">
													</select>
													<img id="userMembersIdLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/>
												</div>
												<div class="pull-right col-md-3">
													<div class="input-group">
														<input class="form-control getDate" type="text">
														<span class="input-group-addon">
															<i class="glyphicon glyphicon-calendar"></i>
														</span>
													</div>
												</div>
											</div>
												<div class="row">
												
													<!--<div class="col-md-6">
														<div class="block">
															<h4 class="m_0"><b>DISTRICT WISE AFFLIATED CADRE</b></h4>
														</div>
													</div>
													<div class="col-md-6">
														<div class="block">
															<h4 class="m_0"><b>CONSTITUENCY WISE AFFLIATED CADRE</b></h4>
														</div>
													</div>-->
													
													<div class="col-md-12 m_top10 bg_cc" style="padding: 8px">
													   <h4 class="panel-title" id="districtWiseTitleId"><b>CONSTITUENCY WISE REGISTRATION DETAILS</b></h4>
													</div>
												</div>
											</div>
											<div class="panel-body pad_0 bg_ff">
												<div class="row">
													<div class="col-md-12 districtCls" style="display:none;">
														<div class="pad_10">
															
															<div id="districtWiseRegistredCountId">
															
															</div>
														</div>
													</div>
													<div class="col-md-12 constituencyCls">
														<div class="block pad_10">
															
															<div id="constituencyWiseRegistredCountId">
															
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
      </div>
      </div>
      </div>
      </div>
      </div>
      </div>
      
	  </section>
	  <!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<div class="row">
			<div class="col-md-6">
				<label class="radio-inline"><input type="radio">TOTAL REG</label>
				<label class="radio-inline"><input type="radio">TODAY REG</label> | 
				<label class="checkbox-inline"><input type="checkbox">WEB</label>	
				<label class="checkbox-inline"><input type="checkbox">TAB</label>	
			
			
			</div>
			<div class="row m_top10 zonewiseCls" style="display:none">
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="zoneClass" checked="true" name="zoneRdi" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="zoneClass" name="zoneRdi" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" >
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">zone wise registrations</h4>
							<span class="themeColor font-12" id="zoneTotalId"></span>
							<span class="themeColor font-12" id="zoneStartedId"></span>
							<span class="themeColor font-12" id="zoneNotStartedId"></span>
							<span class="themeColor font-12" id="todayZoneTotalId" style="display:none"></span>
							<span class="themeColor font-12" id="todayZoneStartedId" style="display:none"></span>
							<span class="themeColor font-12" id="todayZoneNotStartedId" style="display:none"></span>
						</div>
						<center><img id="dataLoadingsImgForZone" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
						<div id="zoneWiseTotalDetailsDiv" style="display:none;"></div>
						<div id="zoneWiseTodayDetailsDiv" style="display:none;"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="regionClass" checked="true" name="regionRdi" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="regionClass" name="regionRdi" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" >
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">region wise registrations</h4>
							<span class="themeColor font-12" id="totalReionsId"></span>
							<span class="themeColor font-12" id="startedRegionsId"></span>
							<span class="themeColor font-12" id="nonStartedRegionsId"></span>
							<span class="themeColor font-12" id="todayTotalReionsId" style="display:none"></span>
							<span class="themeColor font-12" id="todayStartedRegionsId" style="display:none"></span>
							<span class="themeColor font-12" id="todayNonStartedRegionsId" style="display:none"></span>
						</div>
						<center><img id="dataLoadingsImgForRegion" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
						<div id="regionWiseTotalDetailsDiv" style="display:none;"></div>
						<div id="regionWiseTodayDetailsDiv" style="display:none;"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="depotRadioCls" checked="true" name="depotRadio" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="depotRadioCls" name="depotRadio" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" >
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">depot wise registrations</h4>
							<span class="themeColor font-12" id="totlDepotId"></span>
							<span class="themeColor font-12" id="startedDepotId"></span>
							<span class="themeColor font-12" id="notStartedDepotId"></span>
							<span class="themeColor font-12" id="todayTotlDepotId" style="display:none"></span>
							<span class="themeColor font-12" id="todayStartedDepotId" style="display:none"></span>
							<span class="themeColor font-12" id="todayNotStartedDepotId" style="display:none"></span>
						</div>
						<center><img id="dataLoadingsImgForDepot" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
						<div id="depotWiseTotalDetailsDiv" style="display:none;"></div>
						<div id="depotWiseTodayDetailsDiv" style="display:none;"></div>
					</div>
				</div>
			</div>
			<div class="row m_top10 todayOperationalCls" style="display:none">
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="nonOpernationalCls" name="nonOpertional" checked="true" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="nonOpernationalCls" name="nonOpertional" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" >
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">non operational units</h4>
							<center><img id="dataLoadingsImgForNonOperational" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
							<span class="color66 font-12" id="nonOperationalId"></span>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="othersCls" name="others" checked="true" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="othersCls" name="others" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" >
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">other</h4>
							<center><img id="dataLoadingsImgForOthers" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
							<span class="color66 font-12" id="othersId"></span>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div id="onlineAndTabUsersDiv"></div>
					<center style="margin-top: 50px;"><img id="dataLoadingsImgForOnlineAndTab" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
				</div>
			</div>
		</div>
		

					</div>
      <div class="modal-body">
        <!--<table class="table table-bordered">
			<thead class="bg_cc">
				<th></th>
				<th>NAME</th>
				<th>MOBILE NUMBER</th>
				<th>VOTER NUMBER</th>
				<th>EMPLOYEE ID</th>
				<th>REGISTERED THROUGH</th>
			</thead>
			<tbody>
				<tr>
					<td><img src="dist/img/profileIcon.jpg" class="thumbnail" style="width:75px;height:75px;"></td>
					<td>RAMESH</td>
					<td>9848012345</td>
					<td>ixr5464</td>
					<td>31256</td>
					<td>Tab</td>
				</tr>
				<tr>
					<td><img src="dist/img/profileIcon.jpg" class="thumbnail" style="width:75px;height:75px;"></td>
					<td>RAMESH</td>
					<td>9848012345</td>
					<td>ixr5464</td>
					<td>31256</td>
					<td>Tab</td>
				</tr>
			</tbody>
		</table>-->
      </div>
    </div>
  </div>
  </div>
	<!-- Model-->
	<div class="modal fade" id="myModalDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
	  
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 id="registrationCadreDetailsTitle" class="modal-title"></h4>
      <!--  <div class="row">
			<div class="col-md-6">
				<label class="radio-inline"><input type="radio">TOTAL REG</label>
				<label class="radio-inline"><input type="radio">TODAY REG</label> | 
				<label class="checkbox-inline"><input type="checkbox">WEB</label>	
				<label class="checkbox-inline"><input type="checkbox">TAB</label>	
			</div>
		</div>-->
		
      </div>
      <div class="modal-body" id="registrationCadreDetailsPopupDiv">
        <!--<table class="table table-bordered">
			<thead class="bg_cc">
				<th></th>
				<th>NAME</th>
				<th>MOBILE NUMBER</th>
				<th>VOTER NUMBER</th>
				<th>EMPLOYEE ID</th>
				<th>REGISTERED THROUGH</th>
			</thead>
			<tbody>
				<tr>
					<td><img src="dist/img/profileIcon.jpg" class="thumbnail" style="width:75px;height:75px;"></td>
					<td>RAMESH</td>
					<td>9848012345</td>
					<td>ixr5464</td>
					<td>31256</td>
					<td>Tab</td>
				</tr>
				<tr>
					<td><img src="dist/img/profileIcon.jpg" class="thumbnail" style="width:75px;height:75px;"></td>
					<td>RAMESH</td>
					<td>9848012345</td>
					<td>ixr5464</td>
					<td>31256</td>
					<td>Tab</td>
				</tr>
			</tbody>
		</table>-->
      </div>
    </div>
  </div>
</div>
	<!-- End -->
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="daterangepicker/moment.js" type="text/javascript"></script>
<script src="daterangepicker/daterangepicker.js" type="text/javascript"></script>

<script src="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/dataTables.fixedHeader.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script>

//getRtcUnionRegisteredBasicDetails();
//getRtcUnionAllLocationDetails();
//getRtcUnionZoneWiseTotalDetails();
//getRtcUnionTotalLocationWiseDetails();
//getRtcUnionTotalLocationWiseDetails(2);
//getOnlineAndTabUsersCount();

function getRtcUnionRegisteredBasicDetails(){
$("#dataLoadingsImgForTotalWebCount").show();
$("#dataLoadingsImgForTotalTabCount").show();
$("#dataLoadingsImgForTodayWebCount").show();
$("#dataLoadingsImgForTodayTabCount").show();
var jObj={
	task : "basicDetails"
};
$.ajax({
	type:"POST",
	url:"getRtcUnionRegisteredBasicDetailsAction.action",
	dataType: 'json',
	data:{task:JSON.stringify(jObj)}	
}).done(function(result) {
	if(result != null){
		if(result.webCount == null)result.webCount=0;
		$("#totalWebCountId").html(result.webCount);
		if(result.tabCount == null)result.tabCount=0;
		$("#totalTabCountId").html(result.tabCount);
		if(result.todayWebCount == null)result.todayWebCount=0;
		$("#todayWebCountId").html(result.todayWebCount);
		if(result.todayTabCount == null)result.todayTabCount=0;
		$("#todayTabCountId").html(result.todayTabCount);
	}
	$("#dataLoadingsImgForTotalWebCount").hide();
	$("#dataLoadingsImgForTotalTabCount").hide();
	$("#dataLoadingsImgForTodayWebCount").hide();
	$("#dataLoadingsImgForTodayTabCount").hide();
});
}

function getRtcUnionZoneWiseTotalDetails(){
	$("#dataLoadingsImgForZone").show();
	$("#todayZoneTotalId,#todayZoneStartedId,#todayZoneNotStartedId").hide();
	$("#zoneTotalId,#zoneStartedId,#zoneNotStartedId").show();
	$("#zoneWiseTotalDetailsDiv").html("");
	$("#zoneWiseTotalDetailsDiv").show();
	$("#zoneWiseTodayDetailsDiv").hide();
  var jObj={
    task : "zoneDetails"
  };
  $.ajax({
    type:"POST",
    url:"getRtcUnionRegisteredBasicDetailsAction.action",
    dataType: 'json',
    data:{task:JSON.stringify(jObj)}  
  }).done(function(result) {
    if(result != null){
	var totalZones = 0;
	var startedZones = 0;
	var notStartedZones = 0;
	var todayTotalZones = 0;
	var todayStartedZones = 0;
	var todayNotStartedZones = 0;
      var str='';
      str+='<table class="table tableCustom Dattable5">';
        str+='<thead class="bg_ee">';
          str+='<th>Zone Name</th>';
          str+='<th>Total</th>';
          str+='<th>Web</th>';
          str+='<th>Tab</th>';
        str+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
			if(i==0){
				if(result.rtcUnionVoList1[i].totalDataCount != null)
					totalZones = result.rtcUnionVoList1[i].totalDataCount;
				if(result.rtcUnionVoList1[i].startedCount != null)
					startedZones = result.rtcUnionVoList1[i].startedCount;
				notStartedZones = parseInt(totalZones)-parseInt(startedZones);
			}
          str+='<tr>';
            str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
          str+='</tr>';
        }
      }
      str+='</table>';
	  $("#zoneTotalId").html("Total Zone - "+totalZones);
	  $("#zoneStartedId").html("Started - "+startedZones);
	  $("#zoneNotStartedId").html("Not Started - "+notStartedZones);
	  
      $("#zoneWiseTotalDetailsDiv").html(str);
	  $(".Dattable5").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			//"scrollY":   "100px",
			"sDom": '<"top"i>rt<"bottom"flp><"clear">',
			fixedHeader: true
		})
	  
	  var str1='';
      str1+='<table class="table tableCustom Dattable4">';
        str1+='<thead class="bg_ee">';
          str1+='<th>Zone Name</th>';
          str1+='<th>Total</th>';
          str1+='<th>Web</th>';
          str1+='<th>Tab</th>';
        str1+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
		if(i==0){
			if(result.rtcUnionVoList1[i].toDayTotalDataCount != null)
				todayTotalZones = result.rtcUnionVoList1[i].toDayTotalDataCount;
			if(result.rtcUnionVoList1[i].toDayStartedCount != null)
				todayStartedZones = result.rtcUnionVoList1[i].toDayStartedCount;
			todayNotStartedZones = parseInt(todayTotalZones)-parseInt(todayStartedZones);
		}
          str1+='<tr>';
            str1+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
          str1+='</tr>';
        }
      }
      str1+='</table>';
	  $("#todayZoneTotalId").html("Total Zone - "+todayTotalZones);
	  $("#todayZoneStartedId").html("Started - "+todayStartedZones);
	  $("#todayZoneNotStartedId").html("Not Started - "+todayNotStartedZones);
      $("#zoneWiseTodayDetailsDiv").html(str1);
	  $(".Dattable4").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			//"scrollY":   "100px",
			"sDom": '<"top"i>rt<"bottom"flp><"clear">',
			fixedHeader: true
		})
	$("#dataLoadingsImgForZone").hide();
    }
  });
}

function getRtcUnionAllLocationDetails(){
	$("#dataLoadingsImgForRegion").show();
	$("#todayTotalReionsId,#todayStartedRegionsId,#todayNonStartedRegionsId").hide();
	$("#totalReionsId,#startedRegionsId,#nonStartedRegionsId").show();
	$("#regionWiseTotalDetailsDiv").html("");
	$("#regionWiseTodayDetailsDiv").hide();
	$("#regionWiseTotalDetailsDiv").show();
  $.ajax({
    type:"POST",
    url:"getRtcUnionAllLocationDetailsAction.action",
    dataType: 'json',
    data:{}  
  }).done(function(result) {
    if(result != null){
	var totalRegions = 0;
	var startedRegions = 0;
	var notStartedRegions = 0;
	var todayTotalRegions = 0;
	var todayStartedRegions = 0;
	var todayNotStartedRegions = 0;
      var str='';
	  str+='<div class="tableBlockScroll0">';
      str+='<table class="table tableCustom Dattable3">';
        str+='<thead class="bg_ee">';
          str+='<th>Region Name</th>';
          str+='<th>Total</th>';
          str+='<th>Web</th>';
          str+='<th>Tab</th>';
        str+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
		if(i==0){
			if(result.rtcUnionVoList1[i].totalDataCount != null)
				totalRegions = result.rtcUnionVoList1[i].totalDataCount;
			if(result.rtcUnionVoList1[i].startedCount != null)
				startedRegions = result.rtcUnionVoList1[i].startedCount;
			notStartedRegions = parseInt(totalRegions)-parseInt(startedRegions);
		}
          str+='<tr>';
            str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
          str+='</tr>';
        }
      }
      str+='</table>';
	  str+='</div>';
	  $("#totalReionsId").html("Total Region - "+totalRegions);
	  $("#startedRegionsId").html("Started - "+startedRegions);
	  $("#nonStartedRegionsId").html("Not Started - "+notStartedRegions);
      $("#regionWiseTotalDetailsDiv").html(str);
	  $(".tableBlockScroll0").mCustomScrollbar();
	  $(".Dattable3").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			//"scrollY":   "100px",
			"sDom": '<"top"i>rt<"bottom"flp><"clear">',
			fixedHeader: true
		})
	  var str1='';
	  str1+='<div class="tableBlockScroll1">';
      str1+='<table class="table tableCustom Dattable2">';
        str1+='<thead class="bg_ee">';
          str1+='<th>Region Name</th>';
          str1+='<th>Total</th>';
          str1+='<th>Web</th>';
          str1+='<th>Tab</th>';
        str1+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
		if(i==0){
			if(result.rtcUnionVoList1[i].toDayTotalDataCount != null)
				todayTotalRegions = result.rtcUnionVoList1[i].toDayTotalDataCount;
			if(result.rtcUnionVoList1[i].toDayStartedCount != null)
				todayStartedRegions = result.rtcUnionVoList1[i].toDayStartedCount;
			todayNotStartedRegions = parseInt(todayTotalRegions)-parseInt(todayStartedRegions);
		}
          str1+='<tr>';
            str1+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
			str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
          str1+='</tr>';
        }
      }
      str1+='</table>';
	  str1+='</div>';
	  $("#todayTotalReionsId").html("Total Region - "+todayTotalRegions);
	  $("#todayStartedRegionsId").html("Started - "+todayStartedRegions);
	  $("#todayNonStartedRegionsId").html("Not Started - "+todayNotStartedRegions);
      $("#regionWiseTodayDetailsDiv").html(str1);
	  $(".tableBlockScroll1").mCustomScrollbar();
	  $(".Dattable2").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			//"scrollY":   "100px",
			"sDom": '<"top"i>rt<"bottom"flp><"clear">',
			fixedHeader: true
		})
		$("#dataLoadingsImgForRegion").hide();
    }
  });
}

var nonOperationalTotalCount = 0;
var nonOperationalTodayCount = 0;
var othersTotalCount = 0;
var othersTodayCount = 0;

function getRtcUnionTotalLocationWiseDetails(){
$("#dataLoadingsImgForDepot").show();
$("#dataLoadingsImgForDepot1").show();
$("#dataLoadingsImgForNonOperational").show();
$("#dataLoadingsImgForOthers").show();

$("#todayTotlDepotId,#todayStartedDepotId,#todayNotStartedDepotId").hide();
$("#totlDepotId,#startedDepotId,#notStartedDepotId").show();
$("#todayDepotTotalId,#todayDepotStartedId,#todayDepotNotStartedId").hide();
$("#depotTotalId,#depotStartedId,#depotNotStartedId").show();

$("#depotWiseTotalDetailsDiv").html("");
$("#depotWiseTodayDetailsDiv").hide();
$("#depotWiseTotalDetailsDiv").show();

$("#depotWiseTotalDetailsId").html("");
$("#depotWiseTodayDetailsId").hide();
$("#depotWiseTotalDetailsId").show();
	
var jObj={
	task:"depot",
	locationId:0
};
$.ajax({
	type:"POST",
	url:"getRtcUnionLocationWiseDetailsAction.action",
	dataType: 'json',
	data:{task:JSON.stringify(jObj)}	
}).done(function(result) {
	if(result != null){
		buildDepotWiseDetails1(result);
		buildDepotWiseDetails2(result);
	}
});
}

function buildDepotWiseDetails1(result){
	var totalDepots = 0;
	var startedDepots = 0;
	var notStartedDepots = 0;
	var todayTotalDepots = 0;
	var todayStartedDepots = 0;
	var todayNotStartedDepots = 0;
	
	var str='';
		str+='<div class="tableBlockScroll2">';
		str+='<table class="table tableCustom Dattable0">';
			str+='<thead class="bg_ee">';
				str+='<th>Depot Name</th>';
				str+='<th>Total</th>';
				str+='<th>Web</th>';
				str+='<th>Tab</th>';
			str+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
			if(i==0){
				if(result.rtcUnionVoList1[i].totalDataCount != null)
					totalDepots = result.rtcUnionVoList1[i].totalDataCount;
				if(result.rtcUnionVoList1[i].startedCount != null)
					startedDepots = result.rtcUnionVoList1[i].startedCount;
				notStartedDepots = parseInt(totalDepots)-parseInt(startedDepots);
			}
				str+='<tr>';
					str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
					
					if(result.rtcUnionVoList1[i].name == "Non_Operational" && result.rtcUnionVoList1[i].totalCount != null)
						nonOperationalTotalCount = result.rtcUnionVoList1[i].totalCount;
					if(result.rtcUnionVoList1[i].name == "Others" && result.rtcUnionVoList1[i].totalCount != null)
						othersTotalCount = result.rtcUnionVoList1[i].totalCount;
						
					str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
				str+='</tr>';
			}
		}
		str+='</table>';
		str+='</div>';
		$("#totlDepotId").html("Total Depot - "+totalDepots);
		$("#startedDepotId").html("Started - "+startedDepots);
		$("#notStartedDepotId").html("Not Started - "+notStartedDepots);
		$("#depotTotalId").html("Total Depot - "+totalDepots);
		$("#depotStartedId").html("Started - "+startedDepots);
		$("#depotNotStartedId").html("Not Started - "+notStartedDepots);
		$("#depotWiseTotalDetailsDiv").html(str);
		$(".tableBlockScroll2").mCustomScrollbar();
		$(".Dattable0").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			//"scrollY":   "100px",
			"sDom": '<"top"i>rt<"bottom"flp><"clear">',
			fixedHeader: true
		})
		var str1='';
		str1+='<div class="tableBlockScroll3">';
		str1+='<table class="table tableCustom Dattable1">';
			str1+='<thead class="bg_ee">';
				str1+='<th>Depot Name</th>';
				str1+='<th>Total</th>';
				str1+='<th>Web</th>';
				str1+='<th>Tab</th>';
			str1+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
			if(i==0){
				if(result.rtcUnionVoList1[i].toDayTotalDataCount != null)
					todayTotalDepots = result.rtcUnionVoList1[i].toDayTotalDataCount;
				if(result.rtcUnionVoList1[i].toDayStartedCount != null)
					todayStartedDepots = result.rtcUnionVoList1[i].toDayStartedCount;
				todayNotStartedDepots = parseInt(todayTotalDepots)-parseInt(todayStartedDepots);
			}
				str1+='<tr>';
					str1+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
					
					if(result.rtcUnionVoList1[i].name == "Non_Operational" && result.rtcUnionVoList1[i].todayTotalCount != null)
						nonOperationalTodayCount = result.rtcUnionVoList1[i].todayTotalCount;
					if(result.rtcUnionVoList1[i].name == "Others" && result.rtcUnionVoList1[i].todayTotalCount != null)
						othersTodayCount = result.rtcUnionVoList1[i].todayTotalCount;
					
					str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
				str1+='</tr>';
			}
		}
		str1+='</table>';
		str1+='</div>';
		$("#todayTotlDepotId").html("Total Depot - "+todayTotalDepots);
	    $("#todayStartedDepotId").html("Started - "+todayStartedDepots);
	    $("#todayNotStartedDepotId").html("Not Started - "+todayNotStartedDepots);
	    $("#todayDepotTotalId").html("Total Zone - "+todayTotalDepots);
	    $("#todayDepotStartedId").html("Started - "+todayStartedDepots);
	    $("#todayDepotNotStartedId").html("Not Started - "+todayNotStartedDepots);
		$("#depotWiseTodayDetailsDiv").html(str1);
		$(".tableBlockScroll3").mCustomScrollbar();
		$(".Dattable1").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			//"scrollY":   "100px",
			"sDom": '<"top"i>rt<"bottom"flp><"clear">',
			fixedHeader: true
		})
		$("#dataLoadingsImgForDepot").hide();
		$("#dataLoadingsImgForNonOperational").hide();
		$("#dataLoadingsImgForOthers").hide();
		
		$("#nonOperationalId").html("Registered "+nonOperationalTotalCount+" Members");
		$("#othersId").html("Registered "+othersTotalCount+" Members");
}

function buildDepotWiseDetails2(result){
	var str='';
		str+='<table class="table tableCust1">';
			str+='<thead class="bg_ee">';
				str+='<th>Depot Name</th>';
				str+='<th>Total</th>';
				str+='<th>Web</th>';
				str+='<th>Tab</th>';
			str+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
				str+='<tr>';
					str+='<td onclick="getAffiliatedCadreDetails('+result.rtcUnionVoList1[i].id+',\'depotDetailsId'+i+'\',\'depotTrId'+i+'\',\'All\',\'depotRadioDetailsId'+i+'\',\'dataLoadingsImgForCadre'+i+'\',\'dataLoadingsImgForCadreDetails'+i+'\');">'+result.rtcUnionVoList1[i].name+'</td>';
						
					str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
				str+='</tr>';
				str+='<tr class="depotTrCls" id="depotTrId'+i+'" style="display:none;"><td colspan="4" class="insideTable arrow_box"><center><img id="dataLoadingsImgForCadre'+i+'" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center><div id="depotRadioDetailsId'+i+'"></div><center><img id="dataLoadingsImgForCadreDetails'+i+'" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center><div id="depotDetailsId'+i+'"></div></td></tr>';
			}
		}
		str+='</table>';
		$("#depotWiseTotalDetailsId").html(str);
		//$(".tableBlockScroll").mCustomScrollbar();	
		var str1='';
		str1+='<table class="table tableCust1">';
			str1+='<thead class="bg_ee">';
				str1+='<th>Depot Name</th>';
				str1+='<th>Total</th>';
				str1+='<th>Web</th>';
				str1+='<th>Tab</th>';
			str1+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
				str1+='<tr>';
					str1+='<td onclick="getAffiliatedCadreDetails('+result.rtcUnionVoList1[i].id+',\'depotDetailsTdyId'+i+'\',\'depotTrTdyId'+i+'\',\'All\',\'depotRadioDetailsTdyId'+i+'\',\'dataLoadingsImgForTdyCadre'+i+'\',\'dataLoadingsImgForTdyCadreDetails'+i+'\');">'+result.rtcUnionVoList1[i].name+'</td>';
					
					str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
				str1+='</tr>';
				str1+='<tr class="depotTrTdyCls" id="depotTrTdyId'+i+'" style="display:none;"><td colspan="4" class="insideTable arrow_box"><center><img id="dataLoadingsImgForTdyCadre'+i+'" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center><div id="depotRadioDetailsTdyId'+i+'"></div><center><img id="dataLoadingsImgForTdyCadreDetails'+i+'" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center><div id="depotDetailsTdyId'+i+'"></div></td></tr>';
			}
		}
		str1+='</table>';
	$("#depotWiseTodayDetailsId").html(str1);
	$("#dataLoadingsImgForDepot1").hide();
}

function getAffiliatedCadreDetails(id,divId,trId,type,rdDivId,ldngImgId,loadingImg){
$("#"+divId).html("");
$("#"+rdDivId).html("");
$(".depotTrCls").hide();
$(".depotTrTdyCls").hide();
$("#"+trId).show();
$("#"+ldngImgId).show();

var jObj={
	type:"depot",
	searchType:type,
	locationId:id
};
$.ajax({
	type:"POST",
	url:"getAffiliatedCadreDetailsAction.action",
	dataType: 'json',
	data:{task:JSON.stringify(jObj)}	
}).done(function(result) {
	if(result != null && result.length > 0){
		var str='';
		
			str+='<div class="pad_10">';
				str+='<label class="radio-inline">';
					str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" attr_ldngImg="'+loadingImg+'" checked="true" name="details" value="All">All';
				str+='</label>';
				str+='<label class="radio-inline">';
					str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" attr_ldngImg="'+loadingImg+'" name="details" value="today">Today';
				str+='</label>';
				str+='<span class="b_left">';
					str+='<label class="radio-inline">';
						str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" attr_ldngImg="'+loadingImg+'" name="details" value="web">Web';
					str+='</label>';
					str+='<label class="radio-inline">';
						str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" attr_ldngImg="'+loadingImg+'" name="details" value="tab">Tab';
					str+='</label>';
				str+='</span>';
				str+='<span class="closeIcon" attr_divId="'+trId+'">X</span>';
			str+='</div>';
			$("#"+rdDivId).html(str);
			var str1='';
			str1+='<div  class="tableBlockScroll4 bShadow">';
			str1+='<table class="table bShadow">';
				str1+='<thead class="bg_ee">';
					str1+='<th></th>';
					str1+='<th>NAME</th>';
					str1+='<th>MOBILE NUMBER</th>';
					str1+='<th>VOTER NUMBER</th>';
					str1+='<th>EMPLOYE ID</th>';
					str1+='<th>REGISTERED THROUGH</th>';
				str1+='</thead>';
				str1+='<tbody>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td><img src="dist/2016DashBoard/img/profile.png" class="profileIcon"></td>';
						str1+='<td>'+result[i].firstName+'</td>';
						str1+='<td>'+result[i].mobileNo+'</td>';
						str1+='<td>'+result[i].voterCardId+'</td>';
						str1+='<td>'+result[i].idCardNo+'</td>';
						str1+='<td>'+result[i].dataSourceType+'</td>';
					str1+='</tr>';
				}
				str1+='</tbody>';
			str1+='</table>';
			str1+='</div>';
		
		$("#"+divId).html(str1);
		$(".tableBlockScroll4").mCustomScrollbar();
		$("#"+ldngImgId).hide();
	}
	else{
		$("#"+ldngImgId).hide();
		$("#"+divId).html("NO DATA AVAILABLE...");
	}
});
}

function getCadreDetails(id,divId,trId,type,ldngImg){
	$("#"+divId).html("");
	$("#"+ldngImg).show();
	var jObj={
	type:"depot",
	searchType:type,
	locationId:id
	};
	$.ajax({
		type:"POST",
		url:"getAffiliatedCadreDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		if(result != null && result.length > 0){
			var str1='';
			
			str1+='<div  class="tableBlockScroll5 bShadow">';
			str1+='<table class="table bShadow">';
				str1+='<thead class="bg_ee">';
					str1+='<th></th>';
					str1+='<th>NAME</th>';
					str1+='<th>MOBILE NUMBER</th>';
					str1+='<th>VOTER NUMBER</th>';
					str1+='<th>EMPLOYE ID</th>';
					str1+='<th>REGISTERED THROUGH</th>';
				str1+='</thead>';
				str1+='<tbody>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td><img src="dist/2016DashBoard/img/profileIcon.jpg" class="profileIcon"></td>';
						str1+='<td>'+result[i].firstName+'</td>';
						str1+='<td>'+result[i].mobileNo+'</td>';
						str1+='<td>'+result[i].voterCardId+'</td>';
						str1+='<td>'+result[i].idCardNo+'</td>';
						str1+='<td>'+result[i].dataSourceType+'</td>';
					str1+='</tr>';
				}
				str1+='</tbody>';
			str1+='</table>';
			str1+='</div>';
		
		$("#"+divId).html(str1);
		$(".tableBlockScroll5").mCustomScrollbar();
		$("#"+ldngImg).hide();
		}
		else{
			$("#"+ldngImg).hide();
			$("#"+divId).html("NO DATA AVAILABLE...");
		}
	});
}

$(document).on("click",".depotRadioCls",function() {
var value = $(this).val();
if(value == "All"){
	$("#todayTotlDepotId,#todayStartedDepotId,#todayNotStartedDepotId").hide();
	$("#totlDepotId,#startedDepotId,#notStartedDepotId").show();
	$("#depotWiseTodayDetailsDiv").hide();
	$("#depotWiseTotalDetailsDiv").show();
}
else if(value == "Today"){
	$("#todayTotlDepotId,#todayStartedDepotId,#todayNotStartedDepotId").show();
	$("#totlDepotId,#startedDepotId,#notStartedDepotId").hide();
	$("#depotWiseTotalDetailsDiv").hide();
	$("#depotWiseTodayDetailsDiv").show();
}
});

$(document).on("click",".depotClass",function() {
var value = $(this).val();
if(value == "All"){
	$("#todayDepotTotalId,#todayDepotStartedId,#todayDepotNotStartedId").hide();
	$("#depotTotalId,#depotStartedId,#depotNotStartedId").show();
	$("#depotWiseTodayDetailsId").hide();
	$("#depotWiseTotalDetailsId").show();
}
else if(value == "Today"){
	$("#todayDepotTotalId,#todayDepotStartedId,#todayDepotNotStartedId").show();
	$("#depotTotalId,#depotStartedId,#depotNotStartedId").hide();
	$("#depotWiseTotalDetailsId").hide();
	$("#depotWiseTodayDetailsId").show();
}
});

$(document).on("click",".depoRdoCls",function() {
var value = $(this).val();
var divId = $(this).attr("attr_divId");
var id = $(this).attr("attr_id");
var trId = $(this).attr("attr_trId");
var ldngImgId = $(this).attr("attr_ldngImg");
getCadreDetails(id,divId,trId,value,ldngImgId);
});

$(document).on("click",".closeIcon",function() {
var divId = $(this).attr("attr_divId");
$("#"+divId).hide();
});

$(document).on("click",".zoneClass",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#todayZoneTotalId,#todayZoneStartedId,#todayZoneNotStartedId").hide();
	$("#zoneTotalId,#zoneStartedId,#zoneNotStartedId").show();
	$("#zoneWiseTotalDetailsDiv").show();
	$("#zoneWiseTodayDetailsDiv").hide();
  }
  else if(value == "Today"){
	$("#todayZoneTotalId,#todayZoneStartedId,#todayZoneNotStartedId").show();
	$("#zoneTotalId,#zoneStartedId,#zoneNotStartedId").hide();
	$("#zoneWiseTotalDetailsDiv").hide();
	$("#zoneWiseTodayDetailsDiv").show();
  }
});
$(document).on("click",".regionClass",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#todayTotalReionsId,#todayStartedRegionsId,#todayNonStartedRegionsId").hide();
	$("#totalReionsId,#startedRegionsId,#nonStartedRegionsId").show();
	$("#regionWiseTodayDetailsDiv").hide();
	$("#regionWiseTotalDetailsDiv").show();
  }
  else if(value == "Today"){
	$("#todayTotalReionsId,#todayStartedRegionsId,#todayNonStartedRegionsId").show();
	$("#totalReionsId,#startedRegionsId,#nonStartedRegionsId").hide();
	$("#regionWiseTotalDetailsDiv").hide();
	$("#regionWiseTodayDetailsDiv").show();
  }
});

$(document).on("click",".nonOpernationalCls",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#nonOperationalId").html("");
	$("#nonOperationalId").html("Registered "+nonOperationalTotalCount+" Members");
  }
  else if(value == "Today"){
	$("#nonOperationalId").html("");
	$("#nonOperationalId").html("Registered "+nonOperationalTodayCount+" Members");
  }
});

$(document).on("click",".othersCls",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#othersId").html("");
	$("#othersId").html("Registered "+othersTotalCount+" Members");
  }
  else if(value == "Today"){
	$("#othersId").html("");
	$("#othersId").html("Registered "+othersTodayCount+" Members");
  }
});

function getOnlineAndTabUsersCount(){
	$("#dataLoadingsImgForOnlineAndTab").show();

	var jObj={};
	$.ajax({
		type:"POST",
		url:"getTodayTabAndWebUsersCountAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		if(result != null){
			var str='';
			
			str+='<div class="block">';
				str+='<table class="table table-bordered">';
					str+='<tr>';
						str+='<td class="text-center">';
							str+='<h4>WEB <br/>USERS</h4>';
							if(result.webCount != null)
								str+='<p>'+result.webCount+'</p>';
							else
								str+='<p> 0 </p>';
						str+='</td>';
						str+='<td class="text-center">';
							str+='<h4>TAB <br/>USERS</h4>';
							if(result.tabCount != null)
								str+='<p>'+result.tabCount+'</p>';
							else
								str+='<p> 0 </p>';
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			
			$("#dataLoadingsImgForOnlineAndTab").hide();
			$("#onlineAndTabUsersDiv").html(str);
		}
	});
}


$(document).on('click', '.locationTypeRadioCls', function(){
	
	var selectedVal = $(this).val();
	if(selectedVal == "district")
	{
		$(".districtCls").css("display","block");
		$(".constituencyCls").css("display","none");
		getCadreRegistrationTotalCount("total","District");
		$("#districtWiseTitleId").html('<b>DISTRICT WISE REGISTRATION DETAILS</b>');
		$("#districtWiseRegistredCountId").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
		$("#constituencyWiseRegistredCountId").html('');
	}
	else
	{
		$(".districtCls").css("display","none");
		$(".constituencyCls").css("display","block");
		getCadreRegistrationTotalCount("total","Constituency");
		$("#districtWiseTitleId").html('<b>CONSTITUENCY WISE REGISTRATION DETAILS</b>');
		$("#districtWiseRegistredCountId").html('');
		$("#constituencyWiseRegistredCountId").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
		
	}
	
})
/* $(document).on("click",".districtRadioCls",function(){
	
	var searchType = $(this).val();
	getCadreRegistrationTotalCount(searchType,"District");
}); */
/* $(document).on("click",".constituecnyRadioCls",function() {
	
	var searchType = $(this).val();
	getCadreRegistrationTotalCount(searchType,"Constituency");
}); */

/* $(document).on("click",".applyBtn",function(){
	
	var selectedVal = $("input[name='locationTypeRadio']:checked").val();	
	getCadreRegistrationTotalCount("",selectedVal);
	$(".constituecnyRadioCls").removeAttr("checked");
}); */
/* function getSelectedMemberType(){
	
	var selectedVal = $("input[name='locationTypeRadio']:checked").val();	
    getCadreRegistrationTotalCount("",selectedVal);

} */


//getCadreRegistrationTotalCount("total","Constituency");
var cadreInput;
function getCadreRegistrationTotalCount(searchType,locationLevel) {
	cadreInput = '';
	if(locationLevel == "District"){
		
		$(".districtCls").css("display","block");
		$(".constituencyCls").css("display","none");
		$("#districtWiseRegistredCountId").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
		
	}else{
	
		$(".districtCls").css("display","none");
		$(".constituencyCls").css("display","block");
		$("#constituencyWiseRegistredCountId").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
	}
	
	var membereTypeIds = new Array();
	membereTypeIds = $('#userMembersId').val();
	
	if(membereTypeIds == null || membereTypeIds.length==0)
	{
		membereTypeIds = memberTypesIdsArr;
	}
	var startDate = "";
	var toDate = "";
	var dates = $(".getDate").val();
	
	if(dates != null && dates.length > 0){
		var datesArr = dates.split("to");
		startDate = datesArr[0].trim();
		toDate = datesArr[1].trim();
	}
	//searchTypeStr replace With locationLevel
	var jObj={
		membereTypeIds:membereTypeIds,
		searchTypeStr:locationLevel,
		startDate:startDate,
		toDate:toDate,
		//searchDatType:searchType
	};
	cadreInput = jObj;
	$.ajax({
		type:"Post",
		url:'getCadreRegistrationAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		var str ='';
		if(result !=null){
			
		str+='<table class="table table-bordered dataTableId">';
		str+='<thead class="bg_F5">';
		if(locationLevel == "District")
		str+='<th>DISTRICT NAME</th>';
		else
		str+='<th>CONSTITUENCY NAME</th>';
		str+='<th>TOTAL</th>';
		str+='<th>TAB</th>';
		//str+='<th>WEB</th>';
		str+='<th>ONLINE</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
				str+='<tr>';
				if(locationLevel == "District")
					str+='<td class="cursorH" data-toggle="modal" data-target="#myModalDiv" onclick="getRegistrationDetailsSourceWise(\''+result[i].id+'\',\''+result[i].name+'\',\'0\')"><a href="javascript:{};" style="color: red;font-weight: bold"><span style="color:mediumseagreen;">'+result[i].name.toUpperCase()+'</span></a></td>';
				else
					str+='<td class="cursorH" data-toggle="modal" data-target="#myModalDiv"  onclick="getRegistrationDetailsSourceWise(\''+result[i].id+'\',\''+result[i].name+'\',\'0\')"><a href="javascript:{};" style="color: red;font-weight: bold"><span style="color:mediumseagreen;">'+result[i].name.toUpperCase()+'</span></a></td>';
					if(result[i].totalCount==0){
						str+='<td>'+result[i].totalCount+'</td>';
					}else{
						str+='<td class="cursorH" data-toggle="modal" data-target="#myModalDiv" onclick="getRegistrationDetailsSourceWise(\''+result[i].id+'\',\''+result[i].name+'\',\'0\')"><a href="javascript:{};" style="color: red;font-weight: bold"><span style="color:mediumseagreen;">'+result[i].totalCount+'</span></a></td>';
					}
					if(result[i].tabCount==0){
						str+='<td>'+result[i].tabCount+'</td>';  
					}else{
						str+='<td class="cursorH" data-toggle="modal" data-target="#myModalDiv" onclick="getRegistrationDetailsSourceWise(\''+result[i].id+'\',\''+result[i].name+'\',\'TAB\')"><a href="javascript:{};" style="color: red;font-weight: bold"><span style="color:mediumseagreen;">'+result[i].tabCount+'</span></a></td>';	
					}
					/* if(result[i].webCount==0){
						str+='<td>'+result[i].webCount+'</td>';
					}else{
						str+='<td class="cursorH" data-toggle="modal" data-target="#myModalDiv" onclick="getRegistrationDetailsSourceWise(\''+result[i].id+'\',\''+result[i].name+'\',\'WEB\')"><a href="javascript:{};" style="color: red;font-weight: bold"><span style="color:mediumseagreen;">'+result[i].webCount+'</span></a></td>';
					} */
					if(result[i].onlineCount==0){
						str+='<td>'+result[i].onlineCount+'</td>';
					}else{
						str+='<td class="cursorH" data-toggle="modal" data-target="#myModalDiv" onclick="getRegistrationDetailsSourceWise(\''+result[i].id+'\',\''+result[i].name+'\',\'ONLINE\')"><a href="javascript:{};" style="color: red;font-weight: bold"><span style="color:mediumseagreen;">'+result[i].onlineCount+'</span></a></td>';
					}
				str+='</tr>';
			}
			
			if (locationLevel !=null && locationLevel =="District"){
				$("#districtWiseRegistredCountId").html(str);
			}else {
				$("#constituencyWiseRegistredCountId").html(str);
			}
			$('.dataTableId').dataTable({
				"aaSorting": [[ 1, "asc" ]],
				"iDisplayLength" : 20	,
				 "bDestroy": true,
				"aLengthMenu": [[20,50,100, 200, 500, -1], [20,50,100, 200, 500, "All"]]		
	  });
		} 
		
	});
}
function getRegistrationDetailsSourceWise(locationId,title,source) {
	$("#registrationCadreDetailsTitle").html(' '+title+' '+cadreInput.searchTypeStr+' ');
	$("#registrationCadreDetailsPopupDiv").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
	// Search With MemberType 
	var membereTypeIds = new Array();
	membereTypeIds = $('#userMembersId').val();
	if(membereTypeIds == null || membereTypeIds.length==0)
	{
		membereTypeIds = memberTypesIdsArr; 
	}else{
		for(var i in membereTypeIds){
		if(jQuery.inArray( membereTypeIds[i], memberTypesIdsArr ) > 0){
			alert(membereTypeIds)
		membereTypeIds = membereTypeIds;
		}
		}  
	}
	var jObj={      
		sourceType:source,         
		membereTypeIds:membereTypeIds,
		searchTypeStr:cadreInput.searchTypeStr,
		startDate:cadreInput.startDate,
		toDate:cadreInput.toDate,
		searchDateType:cadreInput.searchDatType,
		locationId:locationId,
		appType:""
	};

	$.ajax({
		type:"Post",
		url:'getRegistrationDetailsSourceWiseAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		buildRegisteredCadresSourceWise(result,title,jObj,source);
	})
}
function buildRegisteredCadresSourceWise(result,title,jObj,source){
	if(result == null || result.length == 0)
	{
	
		$("#registrationCadreDetailsPopupDiv").html('No Data Available');
		return;	
	}
	var str='';
	str+='<table class="table table-bordered" id="popupTable">';
	str+='<thead class="bg_cc">';
	str+='<th>Image</th>';
	str+='<th>NAME</th>';
	str+='<th>MEMBERSHIP NO</th>';
	str+='<th>MEMBER TYPE</th>';
	str+='<th>MOBILE NUMBER</th>';
	str+='<th>VOTER NUMBER</th>';
	str+='<th>REGISTERED THROUGH</th>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		
			str+='<tr>';
			if(result[i].imgStr != null){
				
			str+='<td><img src="'+result[i].imgStr+'" class="thumbnail" style="width:75px;height:75px;"></td>';
			}
			else{
				
			str+='<td><img src="dist/2016DashBoard/img/profile.png" class="thumbnail" style="width:75px;height:75px;"></td>';
			}

			str+='<td>'+result[i].name+' </td>';
			str+='<td>'+result[i].memberShipNo+' - (2016)';

			if(result[i].cadreMembershipno !=null && result[i].cadreMembershipno.length>0){	
			  str+='<br>'+result[i].cadreMembershipno+' - (2014)';
			}
			str+='</td>';

			str+='<td>'+result[i].memberType+'</td>';
			str+='<td>'+result[i].mobileNo+'</td>';
			str+='<td>'+result[i].voterCardNo+'</td>';
			str+='<td>'+result[i].regThrough+'</td>';
			str+='</tr>'
	
	}
	str+='</tbody>';
	str+='</table>';

	$("#registrationCadreDetailsPopupDiv").html(str);
	$("#popupTable").dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength" : 20	,
		"bDestroy": true,
		"aLengthMenu": [[20,40,60, 80, -1], [20,40,60, 80, "All"]]	
	});
}
function getRegistrationDetails(locationId,title) {
	$("#registrationCadreDetailsTitle").html(' '+title+' '+cadreInput.searchTypeStr+' ');
	$("#registrationCadreDetailsPopupDiv").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
	// Search With MemberType 
	var membereTypeIds = new Array();
	membereTypeIds = $('#userMembersId').val();
	if(membereTypeIds == null || membereTypeIds.length==0)
	{
		membereTypeIds = memberTypesIdsArr;
	}else{
		for(var i in membereTypeIds){
		if(jQuery.inArray( membereTypeIds[i], memberTypesIdsArr ) > 0){
			alert(membereTypeIds)
		membereTypeIds = membereTypeIds;
		}
		}
	}
	var jObj={
		membereTypeIds:membereTypeIds,
		searchTypeStr:cadreInput.searchTypeStr,
		startDate:cadreInput.startDate,
		toDate:cadreInput.toDate,
		searchDateType:cadreInput.searchDatType,
		locationId:locationId,
		appType:""
	};
	$.ajax({
		type:"Post",
		url:'getRegistrationDetailsAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		buildRegisteredCadres(result,title,jObj);
	})
}

function buildRegisteredCadres(result,jObj)
{
	
	if(result == null || result.length == 0)
	{
	
	$("#registrationCadreDetailsPopupDiv").html('No Data Available');
		return;	
	}
	var str='';
str+='<table class="table table-bordered" id="popupTable">';
str+='<thead class="bg_cc">';
str+='<th>Image</th>';
str+='<th>NAME</th>';
str+='<th>MEMBERSHIP NO</th>';
str+='<th>MEMBER TYPE</th>';
str+='<th>MOBILE NUMBER</th>';
str+='<th>VOTER NUMBER</th>';
str+='<th>REGISTERED THROUGH</th>';
str+='</thead>';
str+='<tbody>';
for(var i in result)
{
str+='<tr>';
if(result[i].imgStr != null){
	
str+='<td><img src="'+result[i].imgStr+'" class="thumbnail" style="width:75px;height:75px;"></td>';
}
else{
	
str+='<td><img src="dist/2016DashBoard/img/profile.png" class="thumbnail" style="width:75px;height:75px;"></td>';
}

str+='<td>'+result[i].name+' </td>';
str+='<td>'+result[i].memberShipNo+' - (2016)';

if(result[i].cadreMembershipno !=null && result[i].cadreMembershipno.length>0){	
  str+='<br>'+result[i].cadreMembershipno+' - (2014)';
}
str+='</td>';

str+='<td>'+result[i].memberType+'</td>';
str+='<td>'+result[i].mobileNo+'</td>';
str+='<td>'+result[i].voterCardNo+'</td>';
str+='<td>'+result[i].regThrough+'</td>';
str+='</tr>';
}
str+='</tbody>';
str+='</table>';

$("#registrationCadreDetailsPopupDiv").html(str);
 $("#popupTable").dataTable({
		  "aaSorting": [[ 1, "asc" ]],
		  "iDisplayLength" : 20	,
		  "bDestroy": true,
		  "aLengthMenu": [[20,40,60, 80, -1], [20,40,60, 80, "All"]]	
	});
}
getTotalCounts();
getTodayCounts();
getLast7DaysCounts();
getLast30DaysCounts();
function getTotalCounts(){
	$("#teachersTotalLoadingId").show();
	$("#anganwadiTotalLoadingId").show();
	$("#apsrtcTotalLoadingId").show();
	$("#motorTotalLoadingId").show();
	var jObj={
		searchType:"Total"
	};
	$.ajax({
		type:"GET",
		url:'getCadreCountsByTdpMemberTypeAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		if(result.affiliatedCadreVoList != null && result.affiliatedCadreVoList.length > 0){
			for(var i in result.affiliatedCadreVoList){
				/* if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcTotalId").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
				} */
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#anganwadiTotalId").html(result.affiliatedCadreVoList[i].count);
					//$("#anganwadiTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#anganwadiTotalOnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#teachersTotalId").html(result.affiliatedCadreVoList[i].count);
					//$("#teachersTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#teachersTotalOnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorTotalId").html(result.affiliatedCadreVoList[i].count);
					//$("#motorTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#motorTotalOnlineId").html(result.affiliatedCadreVoList[i].onlineCount);  
				}
			}
		}
		$("#teachersTotalLoadingId").hide();
		$("#anganwadiTotalLoadingId").hide();
		$("#apsrtcTotalLoadingId").hide();
		$("#motorTotalLoadingId").hide();
	});
}
function getTodayCounts(){
	$("#teachersTodayLoadingId").show();
	$("#anganwadiTodayLoadingId").show();
	$("#motorTodayLoadingId").show();
	$("#apsrtcTodayLoadingId").show();
	var jObj={
		searchType:"Today"
	};
	$.ajax({
		type:"GET",
		url:'getCadreCountsByTdpMemberTypeAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		if(result.affiliatedCadreVoList != null && result.affiliatedCadreVoList.length > 0){
			for(var i in result.affiliatedCadreVoList){
				/* if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
				} */
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#anganwadiTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#anganwadiTodayOnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#teachersTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#teachersTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#teachersTodayOnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#motorTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#motorTodayOnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
			}
		}
		$("#teachersTodayLoadingId").hide();
		$("#anganwadiTodayLoadingId").hide();
		$("#motorTodayLoadingId").hide();
		$("#apsrtcTodayLoadingId").hide();
	});
}
function getLast7DaysCounts(){
	$("#teachersLast7LoadingId").show();
	$("#anganwadiLast7LoadingId").show();
	$("#motorLast7LoadingId").show();
	$("#apsrtcLast7LoadingId").show();
	var jObj={
		searchType:"Last 7 days"
	};
	$.ajax({
		type:"GET",
		url:'getCadreCountsByTdpMemberTypeAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		if(result.affiliatedCadreVoList != null && result.affiliatedCadreVoList.length > 0){
			for(var i in result.affiliatedCadreVoList){
				/* if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
				} */
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#anganwadiLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#anganwadiLast7OnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#teachersLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#teachersLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#teachersLast7OnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#motorLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#motorLast7OnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
			}
		}
		$("#teachersLast7LoadingId").hide();
		$("#anganwadiLast7LoadingId").hide();
		$("#motorLast7LoadingId").hide();
		$("#apsrtcLast7LoadingId").hide();
	});
}
function getLast30DaysCounts(){
	$("#teachersLast30LoadingId").show();
	$("#anganwadiLast30LoadingId").show();
	$("#motorLast30LoadingId").show();
	$("#apsrtcLast30LoadingId").show();
	var jObj={
		searchType:"Last 30 days"
	};
	$.ajax({
		type:"GET",
		url:'getCadreCountsByTdpMemberTypeAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		if(result.affiliatedCadreVoList != null && result.affiliatedCadreVoList.length > 0){
			for(var i in result.affiliatedCadreVoList){
				/* if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
				} */
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#anganwadiLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#anganwadiLast30OnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#teachersLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#teachersLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#teachersLast30OnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#motorLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
					$("#motorLast30OnlineId").html(result.affiliatedCadreVoList[i].onlineCount);
				}
			}
		}
		$("#teachersLast30LoadingId").hide();
		$("#anganwadiLast30LoadingId").hide();
		$("#motorLast30LoadingId").hide();
		$("#apsrtcLast30LoadingId").hide();
	});
}
getAllTotalCounts();
function getAllTotalCounts()
{
	$("#tTCountLoadingId").show();
    var jsObj = {
		searchType:"Total"
    }
  $.ajax(
  {
        type: "POST",
        url:"getAllTotalCountsAction.action",
        data:{task :JSON.stringify(jsObj)}
  }
  ).done(function(result){   
  if(result != null){
	  $("#tTCount").html(result.count);
	  //$("#tTWCount").html(result.webCount);
	  $("#tTTCount").html(result.tabCount);
	  $("#tTOLCount").html(result.onlineCount);
	  
  }
	  $("#tTCountLoadingId").hide();
   });
}
getAllTotalTodayCounts();
function getAllTotalTodayCounts()
{
	$("#tTLCountLoadingId").show();
    var jsObj = {
		searchType:"Today"
    }
  $.ajax(
  {
        type: "POST",
        url:"getAllTotalCountsAction.action",
        data:{task :JSON.stringify(jsObj)}
  }
  ).done(function(result){
	  if(result != null){
	  $("#tTLCount").html(result.count);
	  //$("#tTLWCount").html(result.webCount);
	  $("#tTLTCount").html(result.tabCount);
	  $("#tTLOLCount").html(result.onlineCount);
  }
	  $("#tTLCountLoadingId").hide();
	  });
}
getAllTotalLast7DaysCounts();
function getAllTotalLast7DaysCounts()
{
	$("#tL7CountLoadingId").show();
    var jsObj = {
		searchType:"Last 7 days"
    }
  $.ajax(
  {
        type: "POST",
        url:"getAllTotalCountsAction.action",
        data:{task :JSON.stringify(jsObj)}
  }
  ).done(function(result){
	  if(result != null){
	  $("#tL7Count").html(result.count);
	  //$("#tL7WCount").html(result.webCount);
	  $("#tL7TCount").html(result.tabCount);
	  $("#tL7OLCount").html(result.onlineCount);
  }
	  $("#tL7CountLoadingId").hide();
	  });
}
getAllTotalLast30DaysCounts();
function getAllTotalLast30DaysCounts()
{
	$("#tL30CountLoadingId").show();
    var jsObj = {
		searchType:"Last 30 days"
    }
  $.ajax(
  {
        type: "POST",
        url:"getAllTotalCountsAction.action",
        data:{task :JSON.stringify(jsObj)}
  }
  ).done(function(result){
	   if(result != null){
	  $("#tL30Count").html(result.count);
	  //$("#tL30WCount").html(result.webCount);
	  $("#tL30TCount").html(result.tabCount);
	  $("#tL30OLCount").html(result.onlineCount);
  }
	  $("#tL30CountLoadingId").hide();
	  });
}

function refreshDetails(){
	var id = [];
	id = $("#userMembersId").val();
	if(jQuery.inArray("3", id) > -1)
	{
		$("#anganwadiAllId").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
	else{
		$("#anganwadiAllId").hide();
	}
	if(jQuery.inArray("6", id) > -1){
		$("#motorWorkersAllId").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
	else{
		$("#motorWorkersAllId").hide();
	}
	if(jQuery.inArray("2", id) > -1){
		$("#apsrtcAllId").show();
		$("#tableDivsId").hide();
		$(".zonewiseCls").show();
		$(".depotWiseRegistrationCls").show();
		$(".todayOperationalCls").show(); 
	}
	else{
		$("#apsrtcAllId").hide();
	}
	if(jQuery.inArray("4", id) > -1){
    
		$("#teachersAllId").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
	else {
		$("#teachersAllId").hide();
	}
	if(jQuery.inArray("0", id) > -1){
		$(".trClass").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
}
var memberTypesIdsArr = [];
function getMemberTypeSelectedValues(){
	
	$("#userMembersId").html("");
	$("#userMembersIdLoadingId").show();
	var jObj={
	};
	$.ajax({
		type:"GET",
		url:'getMemberTypeSelectedValuesAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		 if(result!=null && result.length>0){
				//$("#userMembersId").append('<option value="0">TEACHERS AFFLIATED UNION</option>');
				for(var i in result){
					$("#userMembersId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					
					memberTypesIdsArr.push(result[i].id);
				}
				
				$(".districtCls").css("display","block");
				$(".constituencyCls").css("display","none");
				getCadreRegistrationTotalCount("total","District");
				$("#districtWiseTitleId").html('<b>DISTRICT WISE REGISTRATION DETAILS</b>');
				$("#districtWiseRegistredCountId").html('<center><img style="width: 20px; height: 20px;" src="images/icons/loading.gif"></center>');
				$("#constituencyWiseRegistredCountId").html('');
			} 
			$("#userMembersId").chosen();
			$("#userMembersIdLoadingId").hide();
		
});
}
getMemberTypeSelectedValues();

initiateDateRangePicker();
function initiateDateRangePicker(){
	
	//$(".getDate").daterangepicker({opens:"left"});
var cb = function(start, end, label) {
			  $('.getDate').html(start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
			  }

		 var optionSet1 = {
		 startDate: moment(),
		 endDate: moment(),
		 //dateLimit: { days: 60 },
		 showDropdowns: true,
		 showWeekNumbers: true,
		 timePicker: false,
		 timePickerIncrement: 1,
		 timePicker12Hour: true,
		 ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		 },
		 opens: 'left',
		 buttonClasses: ['btn btn-default'],
		 applyClass: 'btn-sm btn-success btn-custom newsSubmitBtn',
		 cancelClass: 'btn-sm btn-cancel',
		 format: 'MM/DD/YYYY',
		 separator: ' to ',
		 locale: {
		 applyLabel: 'Submit',
		 cancelLabel: 'Clear',
		 fromLabel: 'From',
		 toLabel: 'To',
		 customRangeLabel: 'Custom',
		 daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		 monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		 firstDay: 1
		 }
		 };

		 var optionSet2 = {
		 opens: 'left',
		  startDate: moment(),
		 endDate: moment(),
		 ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		 }
		 };

		 // $('.getDate').val(moment().format('MM/DD/YYYY') + ' to  ' + moment().format('MM/DD/YYYY'));

		 $('.getDate').daterangepicker(optionSet1, cb);

		 $('.getDate').on('show.daterangepicker', function() { console.log("show event fired"); });
		 $('.getDate').on('hide.daterangepicker', function() { console.log("hide event fired"); });
		 $('.getDate').on('apply.daterangepicker', function(ev, picker) { 
		
		 });
		 $('.getDate').on('cancel.daterangepicker', function(ev, picker) {  });

		 $('#options1').click(function() {
		 $('.getDate').data('daterangepicker').setOptions(optionSet1, cb);
		 });

		 $('#options2').click(function() {
		 $('.getDate').data('daterangepicker').setOptions(optionSet2, cb);
		 });

		 $('#destroy').click(function() {
		 $('.getDate').data('daterangepicker').remove();
		 });
		 $('.daterangepicker').css("right","0px !important;");
}


$(".ranges").find("ul").prepend('<li class="activeCls">Total</li>');
$(".ranges").find("ul li").removeClass("active");

$(document).on("click",".ranges li",function(){

	$(".ranges").find("ul li").removeClass("active");
	$(this).addClass("active");
	
	var selectedDay=$(this).html().trim();
	if(selectedDay == 'Total'){
		$(".getDate").val('');
	}
	
	if(selectedDay != 'Custom'){
	var selectedVal = $("input[name='locationTypeRadio']:checked").val();	
    getCadreRegistrationTotalCount("",selectedVal);
	}
	
})

$(".ranges li:nth-child(1)").addClass("active");
$(document).on("click",".activeCls",function(){
$(".daterangepicker").css("display","none");	
});	
$(document).on("click",".getDate",function(){
$(".daterangepicker").css("display","block");	
});	
$(document).on("change","#userMembersId",function(){
	var selectedVal = $("input[name='locationTypeRadio']:checked").val();	
    getCadreRegistrationTotalCount("",selectedVal);
});	
</script>
</body>
</html>
