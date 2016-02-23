<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>2016 Affiliated DashBoard</title>
<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/dataTables.fixedHeader.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
.mCSB_container
{
	margin:0px !important;
}
.tableDiff
{
	margin:0px;
}
.tableDiff tr:nth-child(odd) td
{
	background-color:#F5F2ED !important;
	text-align:center
}
.tableDiff thead th
{
	font-size:20px;
	text-align:center;
	font-weight:400
}
.tableDiff tr td
{
	padding:0px !important;
}
.tableDiff tr:nth-child(even) td
{
	background:#FDFDF3 !important;
	text-align:center
}
.tableDiff tr td:first-child:hover , .tableDiff tr td:first-child
{
	box-shadow:none;
	color:#996533;
	font-size:20px;
	vertical-align:middle
}
.tableDiff tr td:hover
{
	box-shadow:0px 0px 25px rgba(0,0,0,0.4)
}
.bg_F9
{
	background-color:#F9F5EA;
	margin:0px !important
}
.bg_F4
{
	background-color:#F4EEE0;
	margin:0px !important
}
.bg_CC
{
	background-color:#CCC !important
}
.bg_EF
{
	background:#EFF3F4
}
.bg_ff
{
	background-color:#fff !important
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<div class="well wellCustom">
						<img src="dist/2016DashBoard/img/headpart.jpg" class="img-block img-responsive">
						<h4 class="panel-title text-center"><b>DASHBOARD</b></h4>
						<div class="col-md-4 pull-right" style="margin-top: -25px;">
							<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userMembersId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue="All" style="width:460px;" onchange="refreshDetails();"/>
						</div>
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading bg_CC">
							<h4 class="panel-title"><b>AFFILIATED MEMBERS ENROLLMENT DRIVE</b></h4>
						</div>
						<div class="panel-body bg_EF">
							<table class="table table-bordered bg_ff tableDiff m_0">
								<thead>
									<th></th>
									<th>TOTAL REGISTERED</th>
									<th>TODAY</th>
									<th>LAST 7 DAYS</th>
									<th>LAST 30 DAYS</th>
								</thead>
								<tr class="trClass" id="teachersAllId">
									<td>TEACHERS</td>
									<td><h3><span id="teachersTotalId"></span><center><img id="teachersTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="teachersTotalWebId"></span></p>
										<p class="bg_F9">TAB: <span id="teachersTotalTabId"></span></p>
									</td>
									<td><h3><span id="teachersTodayId"></span><center><img id="teachersTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="teachersTodayWebId"></span></p>
										<p class="bg_F9">TAB: <span id="teachersTodayTabId"></span></p>
									</td>
									<td><h3><span id="teachersLast7Id"></span><center><img id="teachersLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="teachersLast7WebId"></span></p>
										<p class="bg_F9">TAB: <span id="teachersLast7TabId"></span></p>
									</td>
									<td><h3><span id="teachersLast30Id"></span><center><img id="teachersLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="teachersLast30WebId"></span></p>
										<p class="bg_F9">TAB: <span id="teachersLast30TabId"></span></p>
									</td>
								</tr>
								<tr class="trClass" id="anganwadiAllId">
									<td>ANGANWADI</td>
									<td><h3><span id="anganwadiTotalId"></span><center><img id="anganwadiTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="anganwadiTotalWebId"></span></p>
										<p class="bg_F9">TAB: <span id="anganwadiTotalTabId"></span></p>
									</td>
									<td><h3><span id="anganwadiTodayId"></span><center><img id="anganwadiTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="anganwadiTodayWebId"></span></p>
										<p class="bg_F9">TAB: <span id="anganwadiTodayTabId"></span></p>
									</td>
									<td><h3><span id="anganwadiLast7Id"></span><center><img id="anganwadiLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="anganwadiLast7WebId"></span></p>
										<p class="bg_F9">TAB: <span id="anganwadiLast7TabId"></span></p>
									</td>
									<td><h3><span id="anganwadiLast30Id"></span><center><img id="anganwadiLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="anganwadiLast30WebId"></span></p>
										<p class="bg_F9">TAB: <span id="anganwadiLast30TabId"></span></p>
									</td>
								</tr>
								<tr class="trClass" id="motorWorkersAllId">
									<td>MOTOR WORKERS</td>
									<td><h3><span id="motorTotalId"></span><center><img id="motorTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="motorTotalWebId"></span></p>
										<p class="bg_F9">TAB: <span id="motorTotalTabId"></span></p>
									</td>
									<td><h3><span id="motorTodayId"></span><center><img id="motorTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="motorTodayWebId"></span></p>
										<p class="bg_F9">TAB: <span id="motorTodayTabId"></span></p>
									</td>
									<td><h3><span id="motorLast7Id"></span><center><img id="motorLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="motorLast7WebId"></span></p>
										<p class="bg_F9">TAB: <span id="motorLast7TabId"></span></p>
									</td>
									<td><h3><span id="motorLast30Id"></span><center><img id="motorLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="motorLast30WebId"></span></p>
										<p class="bg_F9">TAB: <span id="motorLast30TabId"></span></p>
									</td>
								</tr>
								<tr class="trClass" id="apsrtcAllId">
									<td>APSRTC</td>
									<td><h3><span id="apsrtcTotalId"></span><center><img id="apsrtcTotalLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="apsrtcTotalWebId"></span></p>
										<p class="bg_F9">TAB: <span id="apsrtcTotalTabId"></span></p>
									</td>
									<td><h3><span id="apsrtcTodayId"></span><center><img id="apsrtcTodayLoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="apsrtcTodayWebId"></span></p>
										<p class="bg_F9">TAB: <span id="apsrtcTodayTabId"></span></p>
									</td>
									<td><h3><span id="apsrtcLast7Id"></span><center><img id="apsrtcLast7LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="apsrtcLast7WebId"></span></p>
										<p class="bg_F9">TAB: <span id="apsrtcLast7TabId"></span></p>
									</td>
									<td><h3><span id="apsrtcLast30Id"></span><center><img id="apsrtcLast30LoadingId" src="images/icons/loading.gif" style="width:20px;height:20px;display:none"/></center></h3>
										<p class="bg_F4">WEB: <span id="apsrtcLast30WebId"></span></p>
										<p class="bg_F9">TAB: <span id="apsrtcLast30TabId"></span></p>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!--<div class="row" id="rtcShowId">
				<div class="col-md-12">
					<div class="block pad_10 rtchideCls">
						<div class="row">
							<div class="col-md-5 col-md-offset-1">
								<div class="row">
									<div class="col-md-6">
										<table class="">
											<tr>
											<td class="pad_10">
												<br/><br/><br/><br/><br/>
												<img src="dist/2016DashBoard/img/online.jpg" style="width:50px;">
											<span class="themeColor" style="font-size:18px">WEB</span>
											</td>
											</tr>
											<tr>
											<td class="pad_10">
												<img src="dist/2016DashBoard/img/mobile.jpg" style="width:50px;">
											<span class="themeColor" style="font-size:18px">TAB</span>
											</td>
											</tr>
										</table>						
									</div>
									<div class="col-md-6">
										<table class="table table-bordered tableCust2">
											<tr><td class="text-center">
												<h4 class="m_bottom0 themeColor">TOTAL</h4>
												<p class="text-italic">Registered Members</p>
											</td></tr>
											<tr><td class="bg_tab text-center" id="totalWebCountId"><center><img id="dataLoadingsImgForTotalWebCount" src="images/icons/loading.gif" style="width:20px;height:20px;display:none;"/></center></td></tr>
											<tr><td class="bg_tab text-center" id="totalTabCountId"><center><img id="dataLoadingsImgForTotalTabCount" src="images/icons/loading.gif" style="width:20px;height:20px;display:none;"/></center></td></tr>
										</table>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-5">
										<table class="table table-bordered tableCust2">
											<tr><td class="text-center">
												<h4 class="m_bottom0 themeColor">TODAY</h4>
												<p class="text-italic">Registered Members</p>
											</td></tr>
											<tr><td class="bg_tab text-center" id="todayWebCountId"><center><img id="dataLoadingsImgForTodayWebCount" src="images/icons/loading.gif" style="width:20px;height:20px;display:none;"/></center></td></tr>
											<tr><td class="bg_tab text-center" id="todayTabCountId"><center><img id="dataLoadingsImgForTodayTabCount" src="images/icons/loading.gif" style="width:20px;height:20px;display:none;"/></center></td></tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>-->
			<div class="row m_top10" id="tableDivsId">
				<div class="col-md-6">
					<div class="block">
						<h4 class="m_0"><b>DISTRICT WISE AFFLIATED CADRE</b></h4>
					</div>
				</div>
				<div class="col-md-6">
					<div class="block">
						<h4 class="m_0"><b>CONSTITUENCY WISE AFFLIATED CADRE</b></h4>
					</div>
				</div>
			</div>
			
			<div class="row m_top10">
				<div class="col-md-6">
					<div class="block pad_10">
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
						<table class="table table-bordered">
							<thead>
								<th>District Name</th>
								<th>Total</th>
								<th>Tab</th>
								<th>Web</th>
							</thead>
							<tbody id="districtWiseRegistredCountId">
								<!--<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>-->
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-6">
					<div class="block pad_10">
						<label class="radio-inline">
							<input type="radio" name="constituency" value="total" checked="true"
							id="constituencyTotalId" class="constituecnyRadioCls" >Total
						</label>
						<label class="radio-inline">
							<input type="radio" name="constituency" value="today" class="constituecnyRadioCls">Today
						</label>
						<label class="radio-inline">
							<input type="radio" name="constituency" value="last 7 days" class="constituecnyRadioCls">Last 7 Days
						</label>
						<label class="radio-inline">
							<input type="radio" name="constituency" value="last 30 days" class="constituecnyRadioCls">Last 30 Days
						</label>
						<table class="table table-bordered">
							<thead>
								<th>Constituency Name</th>
								<th>Total</th>
								<th>Tab</th>
								<th>Web</th>
							</thead>
							<tbody id="constituencyWiseRegistredCountId">
								<!--<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>-->
							</tbody>
						</table>
					</div>
				</div>
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
			<div class="row depotWiseRegistrationCls" style="display:none">
				<div class="col-md-12">
					<div class="block m_top10">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="depotClass" checked="true" name="depot" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="depotClass" name="depot" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" >
							</span>
						</div>
						<div class="text-left pad_10">
							<h4 class="text-capitalize m_bottom0">depot wise registrations</h4>
							<span class="themeColor font-12" id="depotTotalId"></span>
							<span class="themeColor font-12" id="depotStartedId"></span>
							<span class="themeColor font-12" id="depotNotStartedId"></span>
							<span class="themeColor font-12" id="todayDepotTotalId" style="display:none"></span>
							<span class="themeColor font-12" id="todayDepotStartedId" style="display:none"></span>
							<span class="themeColor font-12" id="todayDepotNotStartedId" style="display:none"></span>
						</div>
						<center><img id="dataLoadingsImgForDepot1" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
						<div id="depotWiseTotalDetailsId" style="display:none;"></div>
						<div id="depotWiseTodayDetailsId" style="display:none;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="dist/2016DashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/dataTables.fixedHeader.js" type="text/javascript"></script>

<script>

getRtcUnionRegisteredBasicDetails();
getRtcUnionAllLocationDetails();
getRtcUnionZoneWiseTotalDetails();
getRtcUnionTotalLocationWiseDetails();
//getRtcUnionTotalLocationWiseDetails(2);
getOnlineAndTabUsersCount();

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

/*function getCadreRegistrationTotalCount(){
	var jObj={};
	alert(1221);
	$.ajax({
		type:"GET",
		url:"getCadreRegistrationAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		
	});
}*/
getCadreRegistrationTotalCount("total","District");
getCadreRegistrationTotalCount("total","Constituency");
function getCadreRegistrationTotalCount(searchType,locationLevel) {
	var membereTypeIds = new Array();
	membereTypeIds.push(2);
	//membereTypeIds.push(3);
	//var searchTypeStr = "Constituency";
    var startDate = "";
	var toDate = "";
	//searchTypeStr replace With locationLevel
	var jObj={
		membereTypeIds:membereTypeIds,
		searchTypeStr:locationLevel,
		startDate:startDate,
		toDate:toDate,
		searchDatType:searchType
	};
	$.ajax({
		type:"Post",
		url:'getCadreRegistrationAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		var str ='';
		if(result !=null){
			
			for(var i in result){
				str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							str+='<td>'+result[i].totalCount+'</td>';
							str+='<td>'+result[i].tabCount+'</td>';	
							str+='<td>'+result[i].webCount+'</td>';
				str+='</tr>';
			}
			
			if (locationLevel !=null && locationLevel =="District"){
				$("#districtWiseRegistredCountId").html(str);
			}else {
				$("#constituencyWiseRegistredCountId").html(str);
			}
			
		} 
		
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
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcTotalId").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#anganwadiTotalId").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#teachersTotalId").html(result.affiliatedCadreVoList[i].count);
					$("#teachersTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorTotalId").html(result.affiliatedCadreVoList[i].count);
					$("#motorTotalWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorTotalTabId").html(result.affiliatedCadreVoList[i].tabCount);
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
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#anganwadiTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#teachersTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#teachersTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorTodayId").html(result.affiliatedCadreVoList[i].count);
					$("#motorTodayWebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorTodayTabId").html(result.affiliatedCadreVoList[i].tabCount);
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
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#anganwadiLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#teachersLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#teachersLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorLast7Id").html(result.affiliatedCadreVoList[i].count);
					$("#motorLast7WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorLast7TabId").html(result.affiliatedCadreVoList[i].tabCount);
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
				if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 2){
					$("#apsrtcLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#apsrtcLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#apsrtcLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 3){
					$("#anganwadiLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#anganwadiLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#anganwadiLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 4){
					$("#teachersLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#teachersLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#teachersLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
				else if(result.affiliatedCadreVoList[i].tdpMemberTypeId == 5){
					$("#motorLast30Id").html(result.affiliatedCadreVoList[i].count);
					$("#motorLast30WebId").html(result.affiliatedCadreVoList[i].webCount);
					$("#motorLast30TabId").html(result.affiliatedCadreVoList[i].tabCount);
				}
			}
		}
		$("#teachersLast30LoadingId").hide();
		$("#anganwadiLast30LoadingId").hide();
		$("#motorLast30LoadingId").hide();
		$("#apsrtcLast30LoadingId").hide();
	});
}

$(document).on("click",".districtRadioCls",function(){
	var searchType = $(this).val();
	getCadreRegistrationTotalCount(searchType,"District");
});
$(document).on("click",".constituecnyRadioCls",function() {
	var searchType = $(this).val();
	getCadreRegistrationTotalCount(searchType,"Constituency");
});
function refreshDetails(){
	$(".trClass").hide();
	var id = $("#userMembersId").val();
	if(id == 3){
		$("#anganwadiAllId").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
	else if(id == 5){
		$("#motorWorkersAllId").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
	else if(id == 2){
		$("#apsrtcAllId").show();
		$("#tableDivsId").hide();
		$(".zonewiseCls").show();
		$(".depotWiseRegistrationCls").show();
		$(".todayOperationalCls").show(); 
	}
    else if(id == 4){
		$("#teachersAllId").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
	else if(id == 0){
		$(".trClass").show();
		$("#tableDivsId").show();
		$(".zonewiseCls").hide();
		$(".depotWiseRegistrationCls").hide();
		$(".todayOperationalCls").hide();
	}
}


</script>
</body>
</html>